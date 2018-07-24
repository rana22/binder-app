package enterprise.binder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import enterprise.binder.domain.AppStatEvents;
import enterprise.binder.repository.AppStatEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Optional;

@Slf4j
@Component
public class SystemStatsService {

    @Autowired
    private HealthEndpoint healthEndpoint;

    @Autowired
    private MetricsEndpoint metricsEndpoint;

    private static final String SYS_STATS = "SYS_STATS";
    private static final String CPU_USAGE = "system.cpu.usage";
    private static final String PROCESS_UP_TIME = "process.uptime";
    private static final String SYSTEM_LOAD = "system.load.average.1m";
    private static final String JVM_MEMORY = "jvm.memory.committed";
    private static final String JVM_MEMORY_USED = "jvm.memory.used";
    private static final String PROCESS_CPU_USAGE = "process.cpu.usage";

    private AppStatEventRepository appStatsEventRepository;
    /**
     * @param appStatsEventRepository the appStatsEvent Repository
     */
    public SystemStatsService(AppStatEventRepository appStatsEventRepository) {
        this.appStatsEventRepository = appStatsEventRepository;
    }

    /**
     * This service will update the system stats as per the configuration in
     * QuarantineSchedulingConfiguration for statsCronSchedule
     */
    @Transactional
    public void updateStats() {
        log.debug("Saving application stats at {}",
                LocalDateTime.now().truncatedTo(ChronoUnit.HOURS));

        LocalDateTime statsTime = getTimeForProcessing();
        LocalDateTime endTime = LocalDateTime.now();

        if (java.time.Duration.between(statsTime, endTime).toHours() >= 1) {
            statsTime = endTime.truncatedTo(ChronoUnit.HOURS);
        }

        log.debug("Saving application for the hour at {}", statsTime);
        if (statsTime.isBefore(LocalDateTime.now())) {
            updateSystemStatsForPastHour(statsTime);
        }
    }

    /**
     * @param startTime the start time for which we want to update all stats
     */
    public void updateSystemStatsForPastHour(LocalDateTime startTime) {
        HashMap<String, Object> data = new HashMap<>();

        data.put("HEALTH", getHealthData());
        data.put("CPU_USAGE", metricsEndpoint.metric(CPU_USAGE, null)
                .getMeasurements().get(0).getValue());
        data.put("UPTIME", metricsEndpoint.metric(PROCESS_UP_TIME, null)
                .getMeasurements().get(0).getValue());
        data.put("SYSTEM_LOAD", metricsEndpoint.metric(SYSTEM_LOAD, null)
                .getMeasurements().get(0).getValue());
        data.put("JVM_MEMORY", metricsEndpoint.metric(JVM_MEMORY, null)
                .getMeasurements().get(0).getValue());
        data.put("JVM_MEMORY_USED", metricsEndpoint.metric(JVM_MEMORY_USED, null)
                .getMeasurements().get(0).getValue());
        data.put("PROCESS_CPU_USAGE", metricsEndpoint.metric(PROCESS_CPU_USAGE, null)
                .getMeasurements().get(0).getValue());

        Optional<AppStatEvents> appStatsEvent = appStatsEventRepository
                .findTopByEventStatsDateIsAndEventType(startTime, SYS_STATS);

        AppStatEvents event = appStatsEvent.orElse(new AppStatEvents(startTime,
                SYS_STATS));
        event.setData(data);

        appStatsEventRepository.save(event);
    }

    /**
     * Gets health status such as available disk space, database running status
     *
     * @return parses the health endpoint of actuator to get health status.
     */
    public HashMap<String, Object> getHealthData() {
        HashMap<String, Object> details = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String healthDetails = mapper.writeValueAsString(healthEndpoint.health().getDetails());

            JsonNode rootNode = mapper.readTree(healthDetails);
            details.put("DISKSPACE_TOTAL", rootNode.get("diskSpace").get("details")
                    .get("total").asLong());
            details.put("DISKSPACE_FREE", rootNode.get("diskSpace").get("details")
                    .get("free").asLong());
            details.put("DATABASE_STATUS", rootNode.get("db").get("status").textValue());
        } catch (JsonProcessingException e) {
            log.info("Error occurred in parsing json string {}", e.getMessage());
        } catch (IOException e) {
            log.info("IO error occurred {}", e.getMessage());
        }
        return details;
    }

    /**
     * @return returns the latest time + 1hour from the stats table
     */
    public LocalDateTime getTimeForProcessing() {
        Optional<AppStatEvents> appStatsEvent = appStatsEventRepository
                .findTopByEventTypeOrderByEventStatsDateDesc(SYS_STATS);
        if (appStatsEvent.isPresent()) {
            return appStatsEvent.get().getEventStatsDate().plusHours(1)
                    .truncatedTo(ChronoUnit.HOURS);
        }
        return LocalDateTime.now().minusHours(1).truncatedTo(ChronoUnit.HOURS);
    }


}

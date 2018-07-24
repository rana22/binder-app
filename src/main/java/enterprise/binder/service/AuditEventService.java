package enterprise.binder.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import enterprise.binder.domain.AppAuditEvent;
import enterprise.binder.repository.AppAuditEventRepository;
import enterprise.binder.util.annotation.AuditAction;
import enterprise.binder.util.annotation.AuditEventConverter;
import enterprise.binder.util.annotation.AuditLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for managing audit events.
 */
@Service
@Transactional
@Slf4j
public class AuditEventService {

    private final AppAuditEventRepository appAuditEventRepository;
    private final AuditEventConverter auditEventConverter;
//    private ConfigService configService;

    /**
     * @param appAuditEventRepository the repository for auditlog
     * @param auditEventConverter the audit event converter
     */
    public AuditEventService(AppAuditEventRepository appAuditEventRepository,
                             AuditEventConverter auditEventConverter) {
        this.appAuditEventRepository = appAuditEventRepository;
        this.auditEventConverter = auditEventConverter;
    }

    /**
     * Retrieve all the audit events.
     *
     * @param pageable the page definition
     * @return the events
     */
    public Page<AuditEvent> findAll(Pageable pageable) {
        return appAuditEventRepository.findAll(pageable)
                .map(auditEventConverter::convertToAuditEvent);
    }

    /**
     * Find audit events within a date range.
     *
     * @param fromDate the starting date
     * @param toDate the ending date
     * @param pageable the page definition
     * @return the events
     */
    public Page<AuditEvent> findByDates(Instant fromDate, Instant toDate, Pageable pageable) {
        return appAuditEventRepository
                .findAllByEventDateBetween(fromDate, toDate, pageable)
                .map(auditEventConverter::convertToAuditEvent);
    }

    /**
     * Find a single audit event.
     *
     * @param id the id of the event to find
     * @return the event
     */
    public Optional<AuditEvent> find(Long id) {
        return Optional.of(appAuditEventRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(auditEventConverter::convertToAuditEvent);
    }

    /**
     * Creates and saves an audit event.
     *
     * @param eventType the type of event for this audit event
     * @param data the data for this audit event
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addEvent(String eventType, Map<String, String> data) {
        AppAuditEvent auditEvent = new AppAuditEvent();
//        auditEvent.setUsername(SecurityUtils.getCurrentUsernameOrSystem());
//        auditEvent.setClientIp(HttpRequestUtil.getRemoteAddress());
        auditEvent.setEventType(eventType);
        auditEvent.setEventDate(Instant.now());
        auditEvent.setData(data);
        appAuditEventRepository.save(auditEvent);
    }

    /**
     * Scheduled task to cleanup old auditlog's on a periodic basis.
     * The schedule is defined in config table and is specified in a cron format
     * The qualifying age for deletion is also defined in application as number of days
     */
    @AuditLog(AuditAction.DELETE)
    public void cleanupAuditLogs() {
        log.debug("Cleanup audit logs ended {} purged {} logs ", LocalDateTime.now());
    }
}


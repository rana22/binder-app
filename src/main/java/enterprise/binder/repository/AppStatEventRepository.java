package enterprise.binder.repository;

import enterprise.binder.domain.AppStatEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppStatEventRepository  extends JpaRepository<AppStatEvents, Long> {

    @Query(value = "select * from shamwow.stats_event"
            + " where cast(data ->> :resultKey AS int) = :resultValue",
            nativeQuery = true)
    List<AppStatEvents> findAllIntByKeyValue(@Param("resultKey") String resultKey,
                                             @Param("resultValue") int resultValue);

    @Query(value = "select * from shamwow.stats_event"
            + " where cast(data ->> :resultKey AS DOUBLE PRECISION) = :resultValue",
            nativeQuery = true)
    List<AppStatEvents> findAllDoubleByKeyValue(@Param("resultKey") String resultKey,
                                                @Param("resultValue") Double resultValue);

    Optional<AppStatEvents> findTopByEventTypeOrderByEventStatsDateDesc(String eventType);

    Optional<AppStatEvents> findTopByEventStatsDateIsAndEventType(LocalDateTime eventDate,
                                                                  String eventType);

    List<AppStatEvents> findAllByEventType(String eventType);

}

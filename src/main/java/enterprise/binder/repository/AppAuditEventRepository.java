package enterprise.binder.repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import enterprise.binder.domain.AppAuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the AppAuditEvent entity.
 */
public interface AppAuditEventRepository extends JpaRepository<AppAuditEvent, Long> {

    List<AppAuditEvent> findByUsernameAndEventDateAfterAndEventType(
            String principle, Instant after, String type);

    Page<AppAuditEvent> findAllByEventDateBetween(Instant fromDate,
                                                  Instant toDate,
                                                  Pageable pageable);

    @Query(value = " select temp.data->> 'stats' as statsName,"
            + " count (*) as statsValue"
            + " from (select * from shamwow.audit_event"
            + "           where event_date BETWEEN :fromDate and :toDate"
            + " and event_type = 'APP_STATS') as temp"
            + " GROUP BY temp.data->> 'stats'",
            nativeQuery = true)
    List<Object[]> findAllControllerStatsByEventDateBetween(
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate);

    @Modifying
    @Query(value = "delete from shamwow.audit_event where event_date < :event_date ",
            nativeQuery = true)
    int purgeAuditLogsOlderThan(@Param("event_date") LocalDateTime createDate);
}



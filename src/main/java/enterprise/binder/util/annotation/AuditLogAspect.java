package enterprise.binder.util.annotation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import enterprise.binder.service.AuditEventService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import enterprise.binder.domain.AppAuditEvent;

/**
 * The annotated method should be a Create, Update, or Delete. This aspect
 * will log the following attributes of the context surrounding the method:
 * - method signature
 * - userId of the user logged in
 * - parameters being passed into the method
 *
 * <p>Annotation accepts an action of {@code AuditAction} to determine if saving or deleting log
 * should be used. Updates are considered saves.
 */
@Aspect
@Slf4j
@Component
class AuditLogAspect {

    private final AuditEventService auditEventService;

    public AuditLogAspect(AuditEventService auditEventService) {
        this.auditEventService = auditEventService;
    }

    /**
     * Emits a log message and generates an audit event with details on the audited action.
     *
     * @param joinPoint the method that was annotated / executed
     * @param auditLog the log parameters from the applied annotation
     */
    @Before("within(enterprise.binder.service..*) && @annotation(auditLog)")
    void logEntityAction(JoinPoint joinPoint, AuditLog auditLog) {
        log.info("User: ({}) performed action(s): ({}) on method: ({}) with param(s): ({})",
//                SecurityUtils.getCurrentUsernameOrSystem(),
                auditLog.value(),
                joinPoint.getSignature(),
                joinPoint.getArgs());

        Map<String, String> data = new HashMap<>();
        data.put("method", joinPoint.getSignature().toString());
        data.put("args", Arrays.toString(joinPoint.getArgs()));

        auditEventService.addEvent(auditLog.value().toString(), data);
    }

    /**
     * generates an audit event with details on the audited action.
     *
     * @param auditLog the log parameters from the applied annotation
     */
    @Before("within(enterprise.binder.controller.*) && @annotation(auditLog)")
    void logEntityAction(AuditLog auditLog) {
        Map<String, String> data = new HashMap<>();
        data.put("stats", auditLog.value().toString());
        auditEventService.addEvent("APP_STATS", data);
    }
}

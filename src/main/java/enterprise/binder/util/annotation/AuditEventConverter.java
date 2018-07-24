package enterprise.binder.util.annotation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import enterprise.binder.domain.AppAuditEvent;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * Utility method for convereting between the Spring AuditEvent and our internal
 * AppAuditEvent.
 *
 */
@Component
public class AuditEventConverter {

    /**
     * Convert a list of PersistentAuditEvent to a list of AuditEvent
     *
     * @param appAuditEventList the list to convert
     * @return the converted list.
     */
    public List<AuditEvent> convertToAuditEvent(Iterable<AppAuditEvent> appAuditEventList) {
        if (appAuditEventList == null) {
            return Collections.emptyList();
        }
        List<AuditEvent> auditEvents = new ArrayList<>();
        for (AppAuditEvent persistentAuditEvent : appAuditEventList) {
            auditEvents.add(convertToAuditEvent(persistentAuditEvent));
        }
        return auditEvents;
    }

    /**
     * Convert a PersistentAuditEvent to an AuditEvent
     *
     * @param appAuditEvent the event to convert
     * @return the converted list.
     */
    public AuditEvent convertToAuditEvent(AppAuditEvent appAuditEvent) {
        if (appAuditEvent == null) {
            return null;
        }
        return new AuditEvent(appAuditEvent.getEventDate(),
                appAuditEvent.getUsername(),
                appAuditEvent.getEventType(),
                convertDataToObjects(appAuditEvent.getData()));
    }

    /**
     * Internal conversion. This is needed to support the current SpringBoot actuator
     * AuditEventRepository interface.
     *
     * @param data the data to convert
     * @return a map of String, Object
     */
    private Map<String, Object> convertDataToObjects(Map<String, String> data) {
        Map<String, Object> results = new HashMap<>();

        if (data != null) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                results.put(entry.getKey(), entry.getValue());
            }
        }
        return results;
    }

    /**
     * Internal conversion. This method will allow to save additional data.
     * By default, it will save the object as string
     *
     * @param data the data to convert
     * @return a map of String, String
     */
    public Map<String, String> convertDataToStrings(Map<String, Object> data) {
        if (data == null) {
            return Collections.emptyMap();
        }

        Map<String, String> results = new HashMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            // Extract the data that will be saved.
            if (entry.getValue() instanceof WebAuthenticationDetails) {
                WebAuthenticationDetails details = (WebAuthenticationDetails) entry.getValue();
                results.put("remoteAddress", details.getRemoteAddress());
                results.put("sessionId", details.getSessionId());
            } else {
                results.put(entry.getKey(), Objects.toString(entry.getValue()));
            }
        }
        return results;
    }
}


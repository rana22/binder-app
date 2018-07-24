package enterprise.binder.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@Entity
@Table(name="app_stats")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class AppStatEvents {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    @Column(name = "event_date")
    private LocalDateTime eventStatsDate;


    @Column(name = "event_type")
    private String eventType;

    @Type(type = "jsonb")
    @Column(name = "data", columnDefinition = "jsonb")
    private Map<String, Object> data = new HashMap<>();

    /**
     *
     * @param eventStatsDate the stats event datetime
     * @param eventType the event type either application or system
     * @param data the Json of data
     */
    public AppStatEvents(LocalDateTime eventStatsDate, String eventType,
                         Map<String, Object> data) {
        this.eventStatsDate = eventStatsDate;
        this.eventType = eventType;
        this.data = data;
    }

    /**
     *
     * @param eventStatsDate the stats event datetime
     * @param eventType the event type either application or system
     */
    public AppStatEvents(LocalDateTime eventStatsDate, String eventType) {
        this.eventStatsDate = eventStatsDate;
        this.eventType = eventType;
    }

}

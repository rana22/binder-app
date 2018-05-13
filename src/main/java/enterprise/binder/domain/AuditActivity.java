/**
 * 
 */
package enterprise.binder.domain;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.usertype.UserType;

import org.hibernate.annotations.Type;
import org.joda.time.Instant;
//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ambarrana
 *
 */

@Entity
@Table(name="audit_activity")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
//@TypeDefs({@TypeDefs(name="jsonb", typeClass = JsonBinaryType.class)})
public class AuditActivity {
	
	@Id
	@GeneratedValue	
	private Long id;
	
	@Column(name="event_date")
	private Instant eventDate;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="user_id")
	private UserProfile userProfile;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="document_id")
	private Document document;
	
}

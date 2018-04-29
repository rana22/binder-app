/**
 * 
 */
package enterprise.binder.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author ambarrana
 *
 */

@Entity
@Table(name="document")
@Getter
@Setter
@RequiredArgsConstructor
public class Document {
	
	@Id
	@GeneratedValue
	@Column(name="document_id")
	private Long documentId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="user_id")
	private UserProfile userProfile;
}

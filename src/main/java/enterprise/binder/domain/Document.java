/**
 * 
 */
package enterprise.binder.domain;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
@RequiredArgsConstructor
public class Document {
	
	@Id
	@GeneratedValue
	@Column(name="document_id")
	private Long documentId;
	
	@Column(name="document_date")
	private Instant date;
	
	@Column(name="data")
	private byte[] image;
	
	@OneToOne(fetch = FetchType.LAZY)
	private DocumentDetails documentdetails;
}

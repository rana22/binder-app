/**
 * 
 */
package enterprise.binder.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="document_details")
@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
public class DocumentDetails implements Serializable {
	
	private static final long serialVersionUID = 4665420373371745526L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="user_id")
	private UserProfile userProfile;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="uploaded_date")
	private Date DateuploadedDate;
	
	@Column(name="doc_size")
	private long docSize;
	
	@Column(name="doc_type")
	private String docType;
	
	@Column(name="doc_id")
	private String docId;
	
	@Column(name="status")
	private boolean status;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	@Column(name="category")
	private String category;
	
	@Column(name="thumbnail")
	private byte[] thumbnail;
	
	@OneToOne(mappedBy ="documentdetails", optional= false, cascade = CascadeType.ALL)
	private Document document;

}

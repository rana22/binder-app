/**
 * 
 */
package enterprise.binder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.JPasswordField;

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
@Table(name="user_profile")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class UserProfile{
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;

}

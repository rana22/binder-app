/**
 * 
 */
package enterprise.binder.service;

import enterprise.binder.domain.Email;

/**
 * @author ambarrana
 *
 */
public interface EmailService {
	
	public boolean sendEmail(Email email);

}

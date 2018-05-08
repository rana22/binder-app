/**
 * 
 */
package enterprise.binder.service;

import enterprise.binder.domain.Document;
import enterprise.binder.domain.UserProfile;

/**
 * @author ambarrana
 *
 */
public abstract class AuditActivityService {
	
	
	public abstract void addEvent(String eventType, Document document, UserProfile userProfile);
	
	
}

/**
 * 
 */
package enterprise.binder.service;

import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import enterprise.binder.domain.AuditActivity;
import enterprise.binder.domain.Document;
import enterprise.binder.domain.UserProfile;
import enterprise.binder.repository.AuditActivityRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AuditActivityServiceImpl extends AuditActivityService {
	private final AuditActivityRepository auditActivityRepository;
	
	@Autowired
	public AuditActivityServiceImpl(final AuditActivityRepository auditActivityRepository) {
		this.auditActivityRepository = auditActivityRepository;
	}
		
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addEvent(String eventType, Document document, UserProfile userProfile) {
		log.info("Auditing action {} for document Id {} ", eventType, document.getDocumentId());
		AuditActivity event = new AuditActivity();
		event.setDocument(document);
		event.setEventDate(Instant.now());
		event.setUserProfile(userProfile);
		
		auditActivityRepository.save(event);
	}

}

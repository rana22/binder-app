/**
 * 
 */
package enterprise.binder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import enterprise.binder.domain.Email;
import enterprise.binder.service.EmailServiceImpl;

/**
 * @author ambarrana
 *
 */
@RestController
@RequestMapping("/binder/email")
public class EmailController {
	
	private static final Logger log = LoggerFactory.getLogger(EmailController.class);
	
	private final EmailServiceImpl emailService;
	
	@Autowired
	public EmailController(final EmailServiceImpl emailService) {
		this.emailService = emailService;
	}
	
	@PostMapping
	public ResponseEntity<Boolean> sendEmail(@RequestBody Email email){
		log.info("Start sending email for {} ", email.getEmailTo());
		boolean success = false;
		success = emailService.sendEmail(email);
		return ResponseEntity.ok(success);
	}

}

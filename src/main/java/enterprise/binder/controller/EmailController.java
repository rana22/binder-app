/**
 * 
 */
package enterprise.binder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import enterprise.binder.config.ApplicationProperties;
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
	
	private final ApplicationProperties applicationProperties;
	
	@Autowired
	public EmailController(final EmailServiceImpl emailService , final ApplicationProperties applicationProperties) {
		this.emailService = emailService;
		this.applicationProperties = applicationProperties;
	}
	
	@PostMapping
	public ResponseEntity<Boolean> postEmail(@RequestBody Email email){
		
		log.info("Alert email {} ", applicationProperties.getSmtpHost());
		log.info("Start sending email for {} ", email.getEmailTo().get(0));
		boolean success = false;
		success = emailService.sendEmail(email);
		return ResponseEntity.ok(success);
	}

}

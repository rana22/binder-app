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
	
	@GetMapping
	public ResponseEntity<Boolean> sendEmail(){
		Email email = new Email();
		email.setEmailTo("ambar.rana123@gmail.com");
		email.setSendFrom("msg.service111@gmail.com");
		email.setSubject("Alert email");
		log.info("Start sending email for {} ", email.getEmailTo());
		boolean success = false;
		success = emailService.sendEmail(email);
		return ResponseEntity.ok(success);
	}
	
	@PostMapping
	public ResponseEntity<Boolean> postEmail(@RequestBody Email email){
		email.setSubject("Alert email");
		log.info("Start sending email for {} ", email.getEmailTo());
		boolean success = false;
		success = emailService.sendEmail(email);
		return ResponseEntity.ok(success);
	}

}

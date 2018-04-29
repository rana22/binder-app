/**
 * 
 */
package enterprise.binder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import enterprise.binder.domain.Email;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ambarrana
 *
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService{

	private final JavaMailSender mailSender;
	
	@Autowired
	public EmailServiceImpl(final JavaMailSender mailSender) {
		this.mailSender= mailSender;
	}
	
	@Override
	public boolean sendEmail(Email email) {
		// TODO Auto-generated method stub
		boolean success = false;
		log.info("Sending email to {} ", email.getEmailTo());
		if(email.getEmailTo() != null) {
			SimpleMailMessage message = new SimpleMailMessage();
	        message.setSubject(email.getSubject());
	        message.setText(email.getContent());
	        message.setTo(email.getEmailTo());
	        message.setFrom(email.getSendFrom());
	        
	        try {
	        		mailSender.send(message);
	        		return !success;
	        }catch (MailException ex) {
	        		log.warn("Exception while sending email {}", ex);
	        }
		}else {
			log.warn("Email id should be provided !");
		}
		return success;
	}
	
}

/**
 * 
 */
package enterprise.binder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
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
	
//	@Scheduled(fixedDelay = 15000, initialDelay = 1000)
//	public void sendEmailTest() {
//		log.info("sending email scheduled");
//		sendEmail(new Email());
//	}
	
	@Override
	public boolean sendEmail(Email email) {
		// TODO Auto-generated method stub
		boolean success = false;
		log.info("Sending email to {} ", email.getEmailTo());
		if(email.getEmailTo() != null) {
			SimpleMailMessage message = new SimpleMailMessage();
	        message.setSubject("test example");
	        message.setTo("ambar.rana123@gmail.com");
	        message.setText("test content");
	        message.setFrom("msg.service111@gmail.com");
	        
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

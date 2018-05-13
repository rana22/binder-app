package enterprise.binder.service;

import java.util.List;
import java.util.ArrayList;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import enterprise.binder.domain.Email;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ambarrana implements EmailService, class used to consuming
 *         JavaMailSender for sending different Alert emails
 */

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender mailSender;

	@Autowired
	public EmailServiceImpl(final JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * method for sending email - java mail sender
	 * 
	 * @param email
	 *            provides email content (subject, content, sentfrom)
	 */
	@Override
	public boolean sendEmail(Email email) {

		boolean success = false;
		Address[] receivers = getReceiversAddress(email.getEmailTo());

		if (receivers != null && receivers.length > 0) {
			try {
				MimeMessage message = mailSender.createMimeMessage();
				message.setText(email.getContent());
				message.setSubject(email.getSubject());
				message.setFrom("localhost");
				message.setRecipients(Message.RecipientType.TO, receivers);

				mailSender.send(message);
				success = true;
			} catch (MessagingException ex) {
				log.warn("Exception sending email {}", ex.getMessage());
			}
		}

		return success;
	}

	/**
	 * methods converts string email address to Address[] for email to be sent
	 * 
	 * @param takes
	 *            string list and return Address list
	 */

	private Address[] getReceiversAddress(List<String> emailsTo) {

		List<Address> receivers = new ArrayList<>();
		for (String email : emailsTo) {
			try {
				InternetAddress emailId = new InternetAddress(email);
				emailId.validate();
				receivers.add(emailId);
			} catch (AddressException e) {
				log.warn("Invalid email address: {} with exception {} ", e.getMessage());
			}
		}
		return receivers.toArray(new InternetAddress[receivers.size()]);
	}

}

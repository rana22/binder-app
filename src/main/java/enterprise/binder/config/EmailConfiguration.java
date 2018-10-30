/**
 * 
 */
package enterprise.binder.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ambarrana Required to set up smtp running locally configure bean for
 *         javamail sender
 */

@Configuration
public class EmailConfiguration {

	private static final String TRANSPORT_PROTOCOL = "mail.transport.protocol";

	private static final String SMTP_AUTH = "mail.smtp.auth";

	private static final String STARTTLS_ENABLE = "mail.smtp.starttls.enable";

	private static final String MAIL_DEBUG = "mail.debug";

	private final ApplicationProperties applicationProperties;

	@Autowired
	public EmailConfiguration(final ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(applicationProperties.getSmtpHost());
		mailSender.setPort(applicationProperties.getSmtpPort());

		Properties props = mailSender.getJavaMailProperties();
		props.put(TRANSPORT_PROTOCOL, applicationProperties.isSmtpAuth());
		props.put(SMTP_AUTH, String.valueOf(applicationProperties.isSmtpAuth()));
		props.put(STARTTLS_ENABLE, String.valueOf(applicationProperties.getStartTlsEnable()));
		props.put(MAIL_DEBUG, String.valueOf(applicationProperties.isSmtpDebug()));
		mailSender.setJavaMailProperties(props);

		return mailSender;
	}
}

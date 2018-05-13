/**
 * 
 */
package enterprise.binder.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.Getter;

/**
 * @author ambarrana
 *
 */

@Data
@Configuration
public class ApplicationProperties {
	
	
	@Value("${application.emailSettings.portSmtp}")
	private String someProt;
	
	@Value("${application.emailSettings.smtpHost}")
	private String smtpHost;
	
	@Value("${application.emailSettings.smtpPort}")
	private int smtpPort;
	
	@Value("${application.emailSettings.smtpProtocol}")
	private String smtpProtocol;
	
	@Value("${application.emailSettings.startTlsEnable}")
	private Boolean startTlsEnable;
	
	@Value("${application.emailSettings.smtpDebug}")
	private boolean smtpDebug;

	@Value("${application.emailSettings.smtpAuth}")
	private boolean smtpAuth;

//	private Search search = new Search();
//	
//	private EmailSettings emailSettings = new EmailSettings();
//	
//	private int asynCorePoolsize = 1;
//	private int asynMaxCorePoolsize =1;
//	private int syncQueueCapacity = 1000;
//	
//	public Search getSearch() {
//		return search;
//	}
//	
//	public void setSearch(Search search) {
//		this.search = search;
//	}
//	
//	@Data
//	public static class Search{
//		private int deletionDay;
//		private String cronSchedule;
//		public String getCronSchedule() {
//			return cronSchedule;
//		}
//		public void setCronSchedult(String cronSchedule) {
//			this.cronSchedule = cronSchedule;
//		}
//	}
//	
//	public EmailSettings getEamilSettings() {
//		return emailSettings;
//	}
//	
//	public void setEmailSettings(EmailSettings emailSettings ) {
//		this.emailSettings = emailSettings;
//	}
//	
//	@Data
//	public class EmailSettings {
//		
//		@Value("${application.emailSettings.portSmtp}")
//		private String someProt;
//		
//		@Value("${application.emailSettings.smtpHost}")
//		private String smtpHost;
//		
//		@Value("${application.emailSettings.smtpPort}")
//		private String smtpPort;
//		
//		@Value("${application.emailSettings.smtpProtocol}")
//		private String smtpProtocol;
//		
//		@Value("${application.emailSettings.startTlsEnable}")
//		private Boolean startTlsEnable;
//		
//		@Value("${application.emailSettings.smtpDebug}")
//		private boolean smtpDebug;
//
//		@Value("${application.emailSettings.smtpAuth}")
//		private boolean smtpAuth;
//	}
//	
}

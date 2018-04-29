/**
 * 
 */
package enterprise.binder.config;

import lombok.Data;

/**
 * @author ambarrana
 *
 */
public class ApplicationProperties {

	private Search search = new Search();
	
	private int asynCorePoolsize = 1;
	private int asynMaxCorePoolsize =1;
	private int syncQueueCapacity = 1000;
	
	public Search getSearch() {
		return search;
	}
	
	public void setSearch(Search search) {
		this.search = search;
	}
	@Data
	public static class Search{
		private int deletionDay;
		private String cronSchedule;
		public String getCronSchedule() {
			return cronSchedule;
		}
		public void setCronSchedult(String cronSchedule) {
			this.cronSchedule = cronSchedule;
		}
	}
}

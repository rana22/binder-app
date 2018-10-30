package enterprise.binder.domain;

import java.util.Date;

import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class JobScheduler {
	
	private String jobName;
	private Class<? extends QuartzJobBean> jobClass;
	private Date date;
	private String cronExpression;

}

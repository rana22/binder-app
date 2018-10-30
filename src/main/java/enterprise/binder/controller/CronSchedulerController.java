package enterprise.binder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import enterprise.binder.domain.JobScheduler;
import enterprise.binder.service.JobService;
import enterprise.binder.service.SimpleJob;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/job/scheduler")
public class CronSchedulerController {

	private JobService jobService;

	public CronSchedulerController(JobService jobService) {
		this.jobService = jobService;
	}

	@PostMapping
	@RequestMapping("/add")
	public ResponseEntity addNewCronJob(@RequestBody JobScheduler job) {

		log.info("saving new cron job with name {} ", job.getJobName());
		try {
			jobService.scheduleCronJob(job.getJobName(), SimpleJob.class, job.getDate(), job.getCronExpression());
		} catch (Exception e) {
			log.warn("Exception while adding new job with name {} ,  {}", job.getJobName(), e.getMessage());
		}
		return ResponseEntity.ok(job);
	}

	@PutMapping
	@RequestMapping("/update")
	public ResponseEntity updateCronjob(@RequestBody JobScheduler job) {

		log.info("update cron job with name {} !", job.getJobName());
		try {
			jobService.updateCronJob(job.getJobName(), job.getDate(), job.getCronExpression());
		} catch (Exception e) {
			log.warn("Exception while updating cron job with name {} ,  {}", job.getJobName(), e.getMessage());
		}
		return ResponseEntity.ok(job);
	}

}

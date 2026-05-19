package com.turing.javaee8.jpamvc.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledTasks {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	//@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
	}
	//@Scheduled(fixedDelay = 3000)
	public void fixedDelay() {
		log.info("Fixed delay after 3 seconds now {}", dateFormat.format(new Date()));
	}
	
	//@Scheduled(initialDelay = 1000, fixedRate = 5000)
	public void initialDelay() {
		log.info("initialDelay  after 1 seconds now {}", dateFormat.format(new Date()));
	}
	//@Scheduled(initialDelay = 1000)
	public void runOnce() {
		log.info("runOnce  after 1 seconds now {}", dateFormat.format(new Date()));
	}
	//@Scheduled(cron="*/5 * * * * MON-FRI")
	@Scheduled(cron="0 13 20 * * MON-FRI")
	public void cronTask() {
		log.info("crontask  */5 * * * * MON-FRI seconds now {}", dateFormat.format(new Date()));
	}
}

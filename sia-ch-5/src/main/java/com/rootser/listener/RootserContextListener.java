package com.rootser.listener;


import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.rootser.quartz.SimpleExample;
import com.rootser.reports.ReportRunner;

public class RootserContextListener implements ServletContextListener {

	private Scheduler sched;
	Logger log = LoggerFactory.getLogger(SimpleExample.class);
	//final WebApplicationContext springContext;
	public void contextInitialized(ServletContextEvent sce) {
		final WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		ReportRunner.setContext(springContext);
		try {
			

			log.info("------- Initializing ----------------------");

			// First we must get a reference to a scheduler
			SchedulerFactory sf = new StdSchedulerFactory();
			sched = sf.getScheduler();

			log.info("------- Initialization Complete -----------");

			

			log.info("------- Scheduling Job  -------------------");

			// define the job and tie it to our HelloJob class
			JobDetail job = newJob(ReportRunner.class)
					.withIdentity("job1", "group1")
					.build();

			// Trigger the job to run on the next round minute
	        CronTrigger trigger = newTrigger()
	                .withIdentity("trigger1", "group1")
	                .withSchedule(cronSchedule("0 0 4 * * ?"))
	                .build();


			// Tell quartz to schedule the job using our trigger
			sched.scheduleJob(job, trigger);
			log.info(job.getKey() + " will run at: " + trigger.getNextFireTime());  

			// Start up the scheduler (nothing can actually run until the 
			// scheduler has been started)
			sched.start();

			log.info("------- Started Scheduler -----------------");

			// wait long enough so that the scheduler as an opportunity to 
			// run the job!
			//log.info("------- Waiting 65 seconds... -------------");
			// wait 65 seconds to show job
			//Thread.sleep(65L * 1000L); 
			// executing...
		} catch (SchedulerException e) {
			log.info("Scheduler exception");
		} 
	}

	public void contextDestroyed(ServletContextEvent sce){
		try {
			// shut down the scheduler
			log.info("------- Shutting Down ---------------------");
			sched.shutdown(true);
			log.info("------- Shutdown Complete -----------------");
		} catch (Exception ex) {
		}
	}
}
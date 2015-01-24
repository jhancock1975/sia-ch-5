package com.rootser.reports;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rootser.dao.RootserLogger;

public class ReportRunner implements Job, ApplicationContextAware{
	@Autowired
	@Qualifier("ReportSvcTextImpl")
	private ReportSvc reportSvc;
	private String fileName;
	private final Semaphore semaphore ;
	private  static ApplicationContext context;
	public ReportRunner(){
		semaphore = new Semaphore(1);
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void saveReport(String reportStr) {
		try {
			PrintWriter pw = new PrintWriter(new File(fileName));
			pw.print(reportStr);
			pw.close();
		} catch (IOException e) {
			RootserLogger.logger.error("unable to open file : '" + fileName
					+ "'");
		}
	}

	public static void main(String[] args) {
		RootserLogger.logger.debug("starting app");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"report.xml");
		ReportRunner runner = (ReportRunner) context.getBean("runner");
		Report report = runner.reportSvc.getReport();
		String reportStr = report.getReport();
		runner.saveReport(reportStr);
		((ClassPathXmlApplicationContext) context).close();
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			semaphore.acquire();
			ReportRunner runner = (ReportRunner) context.getBean("runner");
			runner.reportSvc.getReport().cacheWarmup();
			semaphore.release();
		} catch (InterruptedException e) {
			RootserLogger.logger.debug("interruption exception when tring to aqcuire semaphore.");
		}

	}
	@Override
	protected void finalize() throws Throwable {
		((ClassPathXmlApplicationContext) context).close();
		super.finalize();
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
		
	}
	public static void setContext(ApplicationContext applicationContext)
			{
		context = applicationContext;
		
	}
}

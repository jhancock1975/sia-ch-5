package com.rootser.reports;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:report.xml" })
public class ReportTest {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private ReportSvc reportSvc;
	List<ReportMessage> unnaceptableMsgs = new ArrayList<ReportMessage>();
	{
		unnaceptableMsgs.add(ReportMessage.NOT_IMPLEMENTED);
		unnaceptableMsgs.add(ReportMessage.ERROR_READING_TICKERS_FROM_DB);
		unnaceptableMsgs.add(ReportMessage.ERROR_SCRAPING_DATA);
		unnaceptableMsgs.add(ReportMessage.DIVIDE_BY_ZERO);
		unnaceptableMsgs.add(ReportMessage.NULL_TO_FLOAT_CONVERT_ERROR);
	}

	@Test
	public void reportTest() {
		Report report = reportSvc.getReport();
		String reportStr = report.getReport();
		assertTrue("report blank or empty",
				reportStr != null && reportStr.length() > 0);
		for (ReportTitles title : ReportTitles.values()) {
			String failMsg = "title " + title + " does not appear in report";
			assertTrue(failMsg, reportStr.contains(title.toString()));
		}
		List<ReportMessage> messages = report.getMessages();
		for (ReportMessage msg : messages) {
			if (unnaceptableMsgs.contains(msg)) {
				fail("report has unacceptable message" + msg.toString());
			}
		}
	}
}

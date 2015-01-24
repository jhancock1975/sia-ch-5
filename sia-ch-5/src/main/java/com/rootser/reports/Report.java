package com.rootser.reports;

import java.util.List;

public interface Report {
	public String getReport();

	public void setReport(String report);

	public List<ReportMessage> getMessages();

	public void addMessage(ReportMessage msg);
	
	public void cacheWarmup();
}

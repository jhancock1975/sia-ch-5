package com.rootser.reports;

import java.util.List;

import com.rootser.scraper.ScrapeData;

public interface ReportList {
	public List<ScrapeData> getReportList();
	public void setReportList(List<ScrapeData> reportList);
	public String getTitle();
	public void setTitle(String title);
}

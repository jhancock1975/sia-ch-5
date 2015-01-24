package com.rootser.reports;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.rootser.scraper.ScrapeData;

public class ReportListImpl implements ReportList {
	@Expose
	List<ScrapeData> reportList;
	@Expose
	String title;
	@Override
	public List<ScrapeData> getReportList() {
		return this.reportList;
	}

	@Override
	public void setReportList(List<ScrapeData> reportList) {
		this.reportList = reportList;

	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;

	}

}

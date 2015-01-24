package com.rootser.reports;

import java.util.Comparator;

import com.rootser.scraper.ScrapeData;

public interface ReportMetricInf extends Comparator<ScrapeData> {
	public Float getMetric(ScrapeData data);
}

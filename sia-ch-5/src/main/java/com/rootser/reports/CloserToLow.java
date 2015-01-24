package com.rootser.reports;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.rootser.scraper.ScrapeData;
@Component
@Qualifier("closerTo52WkLow")
public class CloserToLow implements ReportMetricInf {

	@Override
	public int compare(ScrapeData o1, ScrapeData o2) {
		return (int) (10000* (getMetric(o1) - getMetric(o2)));
	}

	@Override
	public Float getMetric(ScrapeData data) {
		return (data.getLast() - data.getFifty2WeekLow())/data.getFifty2WeekLow();
	}

}

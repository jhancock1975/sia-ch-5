package com.rootser.reports;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.rootser.scraper.ScrapeData;
@Component
@Qualifier("highDivHighYeild")
public class HighYeildHighDivReportMetric implements ReportMetricInf {
	@Override
	public int compare(ScrapeData o1, ScrapeData o2) {
		return (int) (100 * (getMetric(o1) - getMetric(o1))); 
	}
	Float yeildCoeff;
	Float pctFromLowCoeff;
	Float exp;
	public Float getYeildCoeff() {
		return yeildCoeff;
	}
	public void setYeildCoeff(Float yeildCoeff) {
		this.yeildCoeff = yeildCoeff;
	}
	public Float getPctFromLowCoeff() {
		return pctFromLowCoeff;
	}
	public void setPctFromLowCoeff(Float pctFromLowCoeff) {
		this.pctFromLowCoeff = pctFromLowCoeff;
	}
	public Float getExp() {
		return exp;
	}
	public void setExp(Float exp) {
		this.exp = exp;
	}
	public Float getMetric(ScrapeData data) {
		
		Float yeild = (data.getDivYear() / data.getLast());
		Float pctFromLow = (data.getLast() - data.getFifty2WeekLow())/data.getFifty2WeekLow();
		return (float) (yeildCoeff * yeild + pctFromLowCoeff * pctFromLow);
	}
	

}

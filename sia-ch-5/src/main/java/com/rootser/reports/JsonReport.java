package com.rootser.reports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import com.rootser.dao.RootserLogger;
import com.rootser.scraper.ScrapeData;
import com.google.gson.GsonBuilder;
@Component
@Qualifier("JsonReport")
public class JsonReport extends AbstractReport implements Report {

	@Autowired
	@Qualifier("closerTo52WkLow")
	ReportMetricInf closerToLowMetric;
	
	private ReportList packageReport(List<ScrapeData> currentStockData, ReportTitles title){
		ReportList report = new ReportListImpl();
		report.setReportList(currentStockData);
		report.setTitle(title.toString());
		return report;
	}
	private ReportList getCommishFreeEtfsByPctOff52WkLow() {
		List<ScrapeData> currentStockData = getCommishFreeScrapeData();
		addDescriptions(currentStockData);
		Collections.sort(currentStockData, closerToLowMetric);
		return packageReport(currentStockData, ReportTitles.COMMISH_FREE_ETFS_BY_PCTOFF_52_WK_LOW);

	}

	private void addDescriptions(List<ScrapeData> currentStockData) {
		Map<String, String> tickerToDescMap = hDao.getCommishFreeEtfsAndDesc();
		if ( CollectionUtils.isNotEmpty(currentStockData)){
			for(ScrapeData data: currentStockData){
				data.setDescription(tickerToDescMap.get(data.getTicker()));	
			}
		}
	}

	private ReportList getCommishFreeEtfsByPctOff52WkLowWithOver2PctDiv()
			throws ReportException {
		List<ScrapeData> currentStockData = getCommishFreeScrapeData();
		List<ScrapeData> filtered = new ArrayList<ScrapeData>();
		for (ScrapeData curData : currentStockData) {
			if (curData.getDivYear() > 2.0) {
				filtered.add(curData);
			}
		}
		addDescriptions(filtered);
		
		return packageReport(filtered, ReportTitles.GET_COMMISH_FREE_ETFS_BY_PCT_OFF_52_WK_LOW_WITH_OVER_2_PCT_DIV);
	}
	@Autowired
	@Qualifier("highDivHighYeild")
	ReportMetricInf reportMetric;
	private ReportList getCommishFreeEtfsByMetric()
			throws ReportException {
		List<ScrapeData> currentStockData = getCommishFreeScrapeData();
		List<ScrapeData> filtered = new ArrayList<ScrapeData>();
		Collections.sort(currentStockData, reportMetric);
		for (ScrapeData curData : currentStockData) {
			filtered.add(curData);
		}
		addDescriptions(filtered);
		return packageReport(filtered, ReportTitles.STOCKS_WITH_HIGH_YEILD);
	}
	private ReportList getStocksHeld20PctAbove52WkLow() throws ReportException {
		List<ScrapeData> stocksHeld = getStocksHeldScrapeData();
		List<ScrapeData> filtered = new ArrayList<ScrapeData>();
		for (ScrapeData curStock : stocksHeld) {
			if (is20PctAbove52WkLow(curStock)) {
				filtered.add(curStock);
			}
		}
		addDescriptions(filtered);
		return packageReport(filtered, ReportTitles.GET_STOCKS_HELD_20_PCT_ABOVE_52_WK_LOW);
	}

	

	private ReportList getStocksNotPayingDividends() throws ReportException {
		List<ScrapeData> stocksHeld = getStocksHeldScrapeData();
		List<ScrapeData> filtered = new ArrayList<ScrapeData>();
		try {
			for (ScrapeData curStock : stocksHeld) {
				if (curStock.getDivYear() <= 1.0F) {
					filtered.add(curStock);
				}
			}
		} catch (NullPointerException e) {
			addMessage(ReportMessage.NULL_TO_FLOAT_CONVERT_ERROR);
			throw new ReportException(
					"null pointer exception when in getStocksNotPayingDividends");
		}
		addDescriptions(filtered);
		return packageReport(filtered, ReportTitles.GET_STOCKS_NOT_PAYING_DIVIDENDS);
	}

	@Override
	@Cacheable(cacheName = "reportCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = { @Property(name = "includeMethod", value = "false") }))
	public String getReport() {
		List<ReportList> result = new ArrayList<ReportList>();

		try {
			result.add((ReportList) getCommishFreeEtfsByPctOff52WkLow());

		} catch (NullPointerException e){
			addMessage(ReportMessage.NullPointerException);
			RootserLogger.logger.debug("null pointer exception when generating report");
			RootserLogger.logger.debug(e.getMessage());
		}
		RootserLogger.logger.debug("done with commish free etfs");
		try {
			result.add( getCommishFreeEtfsByPctOff52WkLowWithOver2PctDiv());
		} catch (ReportException e) {
			addMessage(ReportMessage.ReportGenerateError);
		} catch (NullPointerException e){
			addMessage(ReportMessage.NullPointerException);
			RootserLogger.logger.debug("null pointer exception when generating report");
			RootserLogger.logger.debug(e.getMessage());
		}
		try {
			result.add( getStocksHeld20PctAbove52WkLow());
		} catch (ReportException e) {
			addMessage(ReportMessage.ReportGenerateError);
		} catch (NullPointerException e){
			addMessage(ReportMessage.NullPointerException);
			RootserLogger.logger.debug("null pointer exception when generating report");
			RootserLogger.logger.debug(e.getMessage());
		}
		try {
			result.add( getStocksNotPayingDividends());
		} catch (ReportException e) {
			addMessage(ReportMessage.ReportGenerateError);
		} catch (NullPointerException e){
			addMessage(ReportMessage.NullPointerException);
			RootserLogger.logger.debug("null pointer exception when generating report");
			RootserLogger.logger.debug(e.getMessage());
		}
		try {
			result.add( getCommishFreeEtfsByMetric());
		} catch(ReportException e){
			addMessage(ReportMessage.ReportGenerateError);
		} catch (NullPointerException e){
			addMessage(ReportMessage.NullPointerException);
			RootserLogger.logger.debug("null pointer exception when generating report");
			RootserLogger.logger.debug(e.getMessage());
		}		
		return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(result);
	}

	@Override
	public void setReport(String report) {
		// TODO Auto-generated method stub

	}

	

}

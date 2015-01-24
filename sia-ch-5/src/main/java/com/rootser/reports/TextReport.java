package com.rootser.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import com.rootser.dao.DbReadException;
import com.rootser.dao.RootserLogger;
import com.rootser.scraper.ScrapeData;
import com.rootser.scraper.ScrapeException;
import com.rootser.scraper.ScraperSvc;

@Component
@Qualifier("TextReport")
public class TextReport extends AbstractReport implements Report {


	TextReport(){
		super();
	}
	
	
	
	@Autowired
	@Qualifier("closerTo52WkLow")
	ReportMetricInf closerToLowMetric;
	private String getCommishFreeEtfsByPctOff52WkLow() {
		StringBuilder result = new StringBuilder();
		List<ScrapeData> currentStockData = getCommishFreeScrapeData();
		Collections.sort(currentStockData, closerToLowMetric);
		result.append(ReportTitles.COMMISH_FREE_ETFS_BY_PCTOFF_52_WK_LOW);
		result.append("\n");
		for (ScrapeData curData : currentStockData) {
			result.append(formatDataWithMetric(curData));
			
		}
		return result.toString();
	}

	private String getCommishFreeEtfsByPctOff52WkLowWithOver2PctDiv()
			throws ReportException {
		StringBuilder result = new StringBuilder();
		result.append(ReportTitles.GET_COMMISH_FREE_ETFS_BY_PCT_OFF_52_WK_LOW_WITH_OVER_2_PCT_DIV);
		result.append("\n");
		List<ScrapeData> currentStockData = getCommishFreeScrapeData();
		for (ScrapeData curData : currentStockData) {
			if (curData.getDivYear() > 2.0) {
				result.append(formatData(curData, true));
			}
		}
		return result.toString();
	}
	@Autowired
	@Qualifier("highDivHighYeild")
	ReportMetricInf reportMetric;
	private String getCommishFreeEtfsByMetric()
			throws ReportException {
		StringBuilder result = new StringBuilder();
		result.append(ReportTitles.GET_COMMISH_FREE_ETFS_BY_METRIC);
		result.append("\n");
		List<ScrapeData> currentStockData = getCommishFreeScrapeData();
		Collections.sort(currentStockData, reportMetric);
		for (ScrapeData curData : currentStockData) {
			result.append(formatDataWithMetric(curData));
		}
		return result.toString();
	}
	private String getStocksHeld20PctAbove52WkLow() throws ReportException {
		StringBuilder result = new StringBuilder();
		result.append(ReportTitles.GET_STOCKS_HELD_20_PCT_ABOVE_52_WK_LOW);
		result.append("\n");
		List<ScrapeData> stocksHeld = getStocksHeldScrapeData();
		for (ScrapeData curStock : stocksHeld) {
			if (is20PctAbove52WkLow(curStock)) {
				result.append(formatData(curStock, true));
			}
		}
		return result.toString();
	}


	private String getStocksNotPayingDividends() throws ReportException {
		StringBuilder result = new StringBuilder();
		result.append(ReportTitles.GET_STOCKS_NOT_PAYING_DIVIDENDS);
		List<ScrapeData> stocksHeld = getStocksHeldScrapeData();
		try {
			for (ScrapeData curStock : stocksHeld) {
				if (curStock.getDivYear() <= 1.0F) {
					result.append(formatData(curStock, true));
				}
			}
		} catch (NullPointerException e) {
			addMessage(ReportMessage.NULL_TO_FLOAT_CONVERT_ERROR);
			throw new ReportException(
					"null pointer exception when in getStocksNotPayingDividends");
		}
		return result.toString();
	}

	@Override
	@Cacheable(cacheName = "reportCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = { @Property(name = "includeMethod", value = "false") }))
	public String getReport() {
		StringBuilder result = new StringBuilder();

		result.append(getCommishFreeEtfsByPctOff52WkLow());
		try {
			result.append(getCommishFreeEtfsByPctOff52WkLowWithOver2PctDiv());
		} catch (ReportException e) {
			addMessage(ReportMessage.ReportGenerateError);
		}
		try {
			result.append(getStocksHeld20PctAbove52WkLow());
		} catch (ReportException e) {
			addMessage(ReportMessage.ReportGenerateError);
		}
		try {
			result.append(getStocksNotPayingDividends());
		} catch (ReportException e) {
			addMessage(ReportMessage.ReportGenerateError);
		}
		try {
			result.append(getCommishFreeEtfsByMetric());
		} catch(ReportException e){
			addMessage(ReportMessage.ReportGenerateError);
		}
		PrintWriter pw;
		try {
			pw = new PrintWriter(new File("/tmp/report.txt"));
			pw.print(result);
			pw.close();
		} catch (FileNotFoundException e) {
			RootserLogger.logger.debug("unable to open file /tmp/report.txt for saving");
		}
		return result.toString();
	}

	@Override
	public void setReport(String report) {
		// TODO Auto-generated method stub

	}

	
	private String formatData(ScrapeData data, boolean addLineBreak){
		StringBuilder result = new StringBuilder();
		Map<String, String> tickerToDescMap = hDao.getCommishFreeEtfsAndDesc();
		result.append("Ticker:\t")
		.append(data.getTicker())
		.append("\tDescription:\t")
		.append(tickerToDescMap.get(data.getTicker()))
		.append("\tLast:\t")
		.append(data.getLast())
		.append("\tHi:\t")
		.append(data.getFifty2WeekHigh())
		.append("\tLow:\t")
		.append(data.getFifty2WeekLow())
		.append("\tYeild:\t")
		.append(100 * (data.getDivYear()/data.getLast()));
		if (addLineBreak){
			result.append("\n");
		}
		return result.toString();
	}
	private String formatDataWithMetric(ScrapeData curData){
		StringBuilder result = new StringBuilder();
		result.append(formatData(curData, false));
		result.append("\tmetric\t");
		result.append(reportMetric.getMetric(curData));
		result.append("\n");
		return result.toString();
	}
}

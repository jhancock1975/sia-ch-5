package com.rootser.reports;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import com.rootser.dao.DbReadException;
import com.rootser.dao.Stock;
import com.rootser.scraper.ScrapeData;
import com.rootser.scraper.ScrapeException;
import com.rootser.scraper.ScraperSvc;

public class AbstractReport {
	AbstractReport() {
		this.messages = new ArrayList<ReportMessage>();
		
	}
private List<ReportMessage> messages;
	
	@Autowired
	ScraperSvc scraper;
	@Autowired
	protected com.rootser.dao.StockDao hDao;
	
	@Cacheable(cacheName = "commishFreeScrapeDataCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = { @Property(name = "includeMethod", value = "false") }))
	protected List<ScrapeData> getCommishFreeScrapeData() {
		List<ScrapeData> currentStockData = null;
		try {
			List<Stock> stocks = hDao.getCommishFreeEtfs();
			currentStockData = scraper.getLatest(stocks);
			if (currentStockData == null){
				addMessage(ReportMessage.ERROR_SCRAPING_DATA);
			}
			return currentStockData;
		} catch (DbReadException e) {
			addMessage(ReportMessage.ERROR_READING_TICKERS_FROM_DB);
		} catch (ScrapeException e) {
			addMessage(ReportMessage.ERROR_SCRAPING_DATA);
		}
		return currentStockData;
	}

	@Cacheable(cacheName = "stocksHeldScrapeDataCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = { @Property(name = "includeMethod", value = "false") }))
	protected List<ScrapeData> getStocksHeldScrapeData() {
		List<ScrapeData> currentStockData = null;
		try {
			List<Stock> tickers = hDao.getStocksHeld();
			currentStockData = scraper.getLatest(tickers);
			return currentStockData;
		} catch (ScrapeException e) {
			addMessage(ReportMessage.ERROR_SCRAPING_DATA);
		}
		return currentStockData;
	}
	public void cacheWarmup(){
		getStocksHeldScrapeData();
		getCommishFreeScrapeData();
	}
	
	
	public List<ReportMessage> getMessages() {
		return messages;
	}

	
	public void addMessage(ReportMessage msg) {
		messages.add(msg);

	}
	
	protected boolean is20PctAbove52WkLow(ScrapeData curStock)
			throws ReportException {
		try {
			Float low = curStock.getFifty2WeekLow();
			Float last = curStock.getLast();
			if (last == 0.0F) {
				addMessage(ReportMessage.DIVIDE_BY_ZERO);
				throw new ReportException(
						"is20PctAbove52WkLow: last is 0 - divide by 0 exception");
			}
			Float pctDiff = (last - low) / last;
			if (pctDiff >= 0.2F) {
				return true;
			}
		} catch (NullPointerException e) {
			addMessage(ReportMessage.NULL_TO_FLOAT_CONVERT_ERROR);
			throw new ReportException(
					"null pointer exception when in is20PctAbove52WkLow");
		}
		return false;
	}
}

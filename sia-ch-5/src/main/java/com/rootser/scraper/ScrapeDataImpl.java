package com.rootser.scraper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import com.rootser.csvparser.CsvParserSvc;
import com.rootser.dao.RootserLogger;

@Component
public class ScrapeDataImpl implements ScrapeData {
	private String rawData;
	private ScrapeMessage msg;
	@Expose
	private String ticker;
	@Expose
	private String description;
	@Expose
	private Float last;
	@Expose
	private Float fifty2WeekHigh;
	@Expose
	private Float fifty2WeekLow;
	@Expose
	private Float pctOff52WeekLow;
	@Expose
	private Float yield;
	
	private Float divYear;
	
	private CsvParserSvc parser;
	
	private List<ScrapeMessage> messages;
	@Expose
	private String broker;

	public ScrapeDataImpl() {
		messages = new ArrayList<ScrapeMessage>();
	}

	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	public List<ScrapeMessage> getMessages() {
		return messages;
	}

	public void addMessage(ScrapeMessage msg) {
		messages.add(msg);
	}

	public Float getFifty2WeekHigh() {
		return fifty2WeekHigh;
	}

	public void setFifty2WeekHigh(Float fifty2WeekHigh) {
		this.fifty2WeekHigh = nanize(fifty2WeekHigh);
	}

	private Float nanize(Float f) {
		if (f == null ){
			return Float.NaN;
		} else {
			return f;
		}
	}

	public Float getFifty2WeekLow() {
		return fifty2WeekLow;
	}

	public void setFifty2WeekLow(Float fifty2WeekLow) {
		this.fifty2WeekLow = nanize(fifty2WeekLow);
	}

	public Float getDivYear() {
		return divYear;
	}

	public void setDivYear(Float divYear) {
		this.divYear = nanize(divYear);
	}

	public CsvParserSvc getParser() {
		return parser;
	}

	public void setParser(CsvParserSvc parser) {
		this.parser = parser;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Float getLast() {
		return last;
	}

	public void setLast(Float last) {
		this.last = nanize(last);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ScrapeDataImpl [rawData=" + rawData + ", msg=" + msg
				+ ", fifty2WeekHigh=" + fifty2WeekHigh + ", fifty2WeekLow="
				+ fifty2WeekLow + ", divYear=" + divYear + ", parser=" + parser
				+ ", ticker=" + ticker + ", last=" + last + "]";
	}

	@Override
	@Cacheable(cacheName = "pctOff52WkLow", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = { @Property(name = "includeMethod", value = "false") }))
	public Float getPercentOff52WkLow() {
		RootserLogger.logger.debug("Not hitting cache for this value.");
		pctOff52WeekLow = ((last - fifty2WeekLow) / fifty2WeekLow) * 100;
		return pctOff52WeekLow;
	}

	@Override
	public int compareTo(ScrapeData o) {
		return (int) (this.getPercentOff52WkLow().compareTo(o.getPercentOff52WkLow()));
	}

	@Override
	public Float getDivYield() {
		return this.yield;
	}

	@Override
	public void setDivYield(Float yeild) {
		this.yield = nanize(yeild);
		
	}
	
	@Override
	public String getBroker() {
		return broker;
	}
	
	@Override
	public void setBroker(String broker) {
		this.broker = broker;
	}

	@Override
	public ScrapeData ERROR_SCRAPE(String ticker) {
		ScrapeDataImpl result = new ScrapeDataImpl();
		result.setTicker(ticker);
		result.setDescription("error scraping");
		result.setDivYear(0F);
		result.setDivYield(0F);
		result.setFifty2WeekHigh(0F);
		result.setFifty2WeekLow(0F);
		result.setLast(0F);
		return result;
	}

}
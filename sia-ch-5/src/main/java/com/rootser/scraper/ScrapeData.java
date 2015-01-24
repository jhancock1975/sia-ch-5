package com.rootser.scraper;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.rootser.csvparser.CsvParserSvc;

public interface ScrapeData extends Comparable<ScrapeData> {
	
	public String getRawData();

	public void setRawData(String rawData);
	
	public List<ScrapeMessage> getMessages();

	public void addMessage(ScrapeMessage msg);

	public void setParser(CsvParserSvc parser);

	public CsvParserSvc getParser();

	public Float getFifty2WeekHigh();

	public void setFifty2WeekHigh(Float fifty2weekHigh);

	public Float getFifty2WeekLow();

	public void setFifty2WeekLow(Float fifty2weekLow);

	public Float getLast();

	public void setLast(Float last);

	public Float getDivYear();

	public void setDivYear(Float divYear);

	public String getTicker();

	public void setTicker(String ticker);

	public String toString();

	public Float getPercentOff52WkLow();
	
	public void setDescription(String desc);
	
	public Float getDivYield();
	
	public void setDivYield(Float yeild);
	
	public String getBroker();
	
	public void setBroker(String broker);
	
	public ScrapeData ERROR_SCRAPE(String ticker);

}

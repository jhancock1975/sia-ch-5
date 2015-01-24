package com.rootser.scraper;

import java.util.List;

import com.rootser.dao.Stock;

public interface ScraperSvc {

	public List<ScrapeData> getLatest(List<Stock> tickers)
			throws ScrapeException;
}

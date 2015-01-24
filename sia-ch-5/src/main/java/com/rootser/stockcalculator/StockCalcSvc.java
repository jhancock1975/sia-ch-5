package com.rootser.stockcalculator;

import com.rootser.scraper.ScrapeException;

public interface StockCalcSvc {
	public Float calculatePresentPotentialGain(String ticker)
			throws ScrapeException;
}

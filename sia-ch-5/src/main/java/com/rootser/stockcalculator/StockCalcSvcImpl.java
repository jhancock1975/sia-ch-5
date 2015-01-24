package com.rootser.stockcalculator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rootser.dao.Stock;
import com.rootser.dao.StockDao;
import com.rootser.scraper.ScrapeData;
import com.rootser.scraper.ScrapeException;
import com.rootser.scraper.ScrapeMessage;
import com.rootser.scraper.ScraperSvc;

@Service
public class StockCalcSvcImpl implements StockCalcSvc {
	@Autowired
	private StockDao stockDao;
	@Autowired
	private ScraperSvc scraper;

	@Override
	public Float calculatePresentPotentialGain(String ticker)
			throws ScrapeException {
		Float result = null;
		Integer numSharesHeld = stockDao.getNumSharesHeld(ticker);
		if (numSharesHeld <= 0) {
			result = 0F;
		} else {
			ArrayList<Stock> tickerList = new ArrayList<Stock>();
			Stock stock = new Stock();
			stock.setTicker(ticker);
			tickerList.add(stock);

			List<ScrapeData> currentStockData = scraper.getLatest(tickerList);
			ScrapeData currentData = currentStockData.get(0); // list should
																// have one elt.
			if (!currentData.getMessages().contains(ScrapeMessage.PARSE_ERROR)) {
				Float presentValueOfShares = numSharesHeld
						* currentData.getLast();
				Float initialInvestment = stockDao.getAmountSpent(ticker);
				result = 100 * (presentValueOfShares - initialInvestment)
						/ initialInvestment;
			}
		}
		return result;
	}
}

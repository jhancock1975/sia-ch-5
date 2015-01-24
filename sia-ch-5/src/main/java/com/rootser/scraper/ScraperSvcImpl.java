package com.rootser.scraper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import com.rootser.dao.RootserLogger;
import com.rootser.dao.Stock;

@Service
public class ScraperSvcImpl implements ScraperSvc {
	@Autowired
	@Qualifier("priceUrl")
	UrlGenerator priceDataUrlGenerator;
	@Autowired
	@Qualifier("dividendUrl")
	UrlGenerator dividendDataUrlGenerator;
	@Autowired
	ScrapedCsv csv;
	@Autowired
	ScraperParser parser;

	/**
	 * generates list of scrape data for latest stock data for stock in list
	 * 
	 * @param stocks
	 *            - list of stock ticker symbols
	 */
	@Override
	@Cacheable(cacheName = "messageCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = { @Property(name = "includeMethod", value = "false") }))
	public List<ScrapeData> getLatest(List<Stock> stocks)
			throws ScrapeException {

		ArrayList<ScrapeData> result = new ArrayList<ScrapeData>();
		for (Stock stock : stocks) {
			ScrapeData curData;
			try {
				ScrapedCsvResponse priceResponse = getPriceScrapedCsvResponse(stock.getTicker());
				ScrapedCsvResponse dividendResponse = getDividendScrapedCsvResposne(stock.getTicker());
				curData = parser.parse(priceResponse,
						dividendResponse);
				curData.setTicker(stock.getTicker());
				curData.setBroker(stock.getBroker().getDescription());
				if (!hasErrors(curData.getMessages())) {
					result.add(curData);
				}
			} catch (Exception e) {
				//throw new ScrapeException(e.getMessage());
				RootserLogger.logger.debug("error scraping data for ticker " + stock.getTicker());
				curData = new ScrapeDataImpl().ERROR_SCRAPE(stock.getTicker());
			}
		}
		Collections.sort(result);
		return result;

	}

	private boolean hasErrors(List<ScrapeMessage> messages) {
		return messages.contains(ScrapeMessage.PRICE_DATA_NO_DIVIDENDS)
				|| messages.contains(ScrapeMessage.PARSE_ERROR)
				|| messages.contains(ScrapeMessage.CONNECT_ERROR);
	}

	private ScrapedCsvResponse getPriceScrapedCsvResponse(String ticker) {
		ScrapedCsvResponse response;
		String priceDataUrl = priceDataUrlGenerator.genUrl(ticker);
		response = csv.getContent(priceDataUrl);
		return response;
	}

	private ScrapedCsvResponse getDividendScrapedCsvResposne(String ticker) {
		ScrapedCsvResponse response;
		String dividendDataUrl = dividendDataUrlGenerator.genUrl(ticker);
		response = csv.getContent(dividendDataUrl);
		return response;
	}

	public ScraperSvcImpl() {

	}
}

package com.rootser.scraper;

public interface ScraperParser {

	ScrapeData parse(ScrapedCsvResponse dailyData,
			ScrapedCsvResponse dividendData);

}

package com.rootser.scraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScrapedCsvImpl implements ScrapedCsv {
	@Autowired
	private PageGrabber pageGrabber;

	@Override
	public ScrapedCsvResponse getContent(String address) {
		ScrapedCsvResponse response = new ScrapedCsvResponseImpl();
		try {
			response.setData(pageGrabber.getContent(address));
			response.addMessage(ScrapeMessage.SUCCESS);
		} catch (PageGrabberException e) {
			response.addMessage(ScrapeMessage.CONNECT_ERROR);
		}
		return response;
	}
}

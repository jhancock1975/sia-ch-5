package com.rootser.scraper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ScrapedCsvResponseImpl implements ScrapedCsvResponse {
	public ScrapedCsvResponseImpl() {
		messages = new ArrayList<ScrapeMessage>();
	}

	private List<ScrapeMessage> messages;
	private String data;

	@Override
	public String getData() {
		return data;
	}

	@Override
	public void setData(String data) {
		this.data = data;

	}

	@Override
	public List<ScrapeMessage> getMessages() {
		return messages;
	}

	@Override
	public void addMessage(ScrapeMessage msg) {
		messages.add(msg);

	}

}

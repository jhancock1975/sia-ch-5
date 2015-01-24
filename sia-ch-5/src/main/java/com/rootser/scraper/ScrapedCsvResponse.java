package com.rootser.scraper;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public interface ScrapedCsvResponse {
	List<ScrapeMessage> getMessages();

	void addMessage(ScrapeMessage msg);

	String getData();

	void setData(String data);
}

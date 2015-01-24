package com.rootser.scraper;

public abstract class AbstractUrlGenerator implements UrlGenerator {

	private String urlBase = "http://ichart.finance.yahoo.com/table.csv?s=";

	public String getUrlBase() {
		return urlBase;
	}

}

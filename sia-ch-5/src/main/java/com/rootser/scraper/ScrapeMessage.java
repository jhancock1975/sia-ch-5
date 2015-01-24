package com.rootser.scraper;

public enum ScrapeMessage {
	PARSE_ERROR, CONNECT_ERROR, SUCCESS, PRICE_DATA_NO_DIVIDENDS;
	public String toString() {
		switch (this) {
		case PARSE_ERROR:
			return "error parsing response";
		case CONNECT_ERROR:
			return "error connecting to remote data source";
		case SUCCESS:
			return "sucessfully downloaded data";
		case PRICE_DATA_NO_DIVIDENDS:
			return "got price data but no dividends";
		default:
			return null;
		}
	}
}

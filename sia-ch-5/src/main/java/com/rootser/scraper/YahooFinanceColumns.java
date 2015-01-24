package com.rootser.scraper;

public enum YahooFinanceColumns {
	DATE, OPEN, HIGH, LOW, CLOSE, VOLUME, ADJ_CLOSE, DIVIDEND;
	public Integer getIndex() {
		switch (this) {
		case DATE:
			return 0;
		case OPEN:
			return 1;
		case HIGH:
			return 2;
		case LOW:
			return 3;
		case CLOSE:
			return 4;
		case VOLUME:
			return 5;
		case ADJ_CLOSE:
			return 6;
		case DIVIDEND:
			return 1;
		default:
			return null;
		}
	}
}

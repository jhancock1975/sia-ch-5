package com.rootser.reports;

public enum ReportMessage {
	NOT_IMPLEMENTED, ERROR_READING_TICKERS_FROM_DB, ERROR_SCRAPING_DATA, DIVIDE_BY_ZERO, NULL_TO_FLOAT_CONVERT_ERROR, ReportGenerateError, NullPointerException;
	public String toString() {
		switch (this) {
		case NOT_IMPLEMENTED:
			return "not implemented";
		case ERROR_READING_TICKERS_FROM_DB:
			return "error reading tickers from datbase";
		case ERROR_SCRAPING_DATA:
			return "error scraping data";
		case ReportGenerateError:
			return "error generating report";
		case NullPointerException:
			return "null pointer exception when generating report";
		default:
			return "this enum's to string method is not implemented.";
		}
	}
}

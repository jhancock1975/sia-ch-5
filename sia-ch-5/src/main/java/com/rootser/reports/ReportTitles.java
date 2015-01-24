package com.rootser.reports;

public enum ReportTitles {
	COMMISH_FREE_ETFS_BY_PCTOFF_52_WK_LOW, 
	GET_COMMISH_FREE_ETFS_BY_PCT_OFF_52_WK_LOW_WITH_OVER_2_PCT_DIV, 
	GET_STOCKS_HELD_20_PCT_ABOVE_52_WK_LOW, 
	GET_STOCKS_NOT_PAYING_DIVIDENDS,
	GET_COMMISH_FREE_ETFS_BY_METRIC,
	STOCKS_WITH_HIGH_YEILD;
	public String toString() {
		switch (this) {
		case COMMISH_FREE_ETFS_BY_PCTOFF_52_WK_LOW:
			return "Commission Free ETFS by % off 52 WeeK Low";
		case GET_COMMISH_FREE_ETFS_BY_PCT_OFF_52_WK_LOW_WITH_OVER_2_PCT_DIV:
			return "Commission Free ETFS by % off 52 WeeK Low With Over 2 % Dividend";
		case GET_STOCKS_HELD_20_PCT_ABOVE_52_WK_LOW:
			return "Stocks held 20 % above 52 week low";
		case GET_STOCKS_NOT_PAYING_DIVIDENDS:
			return "Stocks not paying dividends";
		case GET_COMMISH_FREE_ETFS_BY_METRIC:
			return "Commission free ETF's by metric";
		case STOCKS_WITH_HIGH_YEILD:
			return "Stocks with high dividend yeild";
		default:
			return "toString not implemented for enum";
		}
	}
}

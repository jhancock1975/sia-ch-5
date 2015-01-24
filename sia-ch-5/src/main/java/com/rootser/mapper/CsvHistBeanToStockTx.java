package com.rootser.mapper;

import com.rootser.csvparser.CsvHistBean;
import com.rootser.dao.StockTx;

public interface CsvHistBeanToStockTx {
	public StockTx map(CsvHistBean source, StockTx dest);
	
}

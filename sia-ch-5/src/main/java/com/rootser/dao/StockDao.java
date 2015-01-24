package com.rootser.dao;

import java.util.List;
import java.util.Map;

public interface StockDao {
	public String addStock(Stock s);

	public void removeStock(Stock stock);

	public void removeStockTx(StockTx stockTx);

	public Integer addStockTx(StockTx tx);

	public Integer getNumSharesHeld(String ticker);

	public Float getAmountSpent(String ticker);

	public List<Stock> getStocksHeld();

	public List<Stock> getCommishFreeEtfs() throws DbReadException;
	
	public Map<String,String> getCommishFreeEtfsAndDesc() ;
}

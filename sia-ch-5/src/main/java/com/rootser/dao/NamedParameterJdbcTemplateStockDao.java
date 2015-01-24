package com.rootser.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class NamedParameterJdbcTemplateStockDao implements StockDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String SQL_INSERT_STOCK = "insert into stocks (ticker) "
			+ "values (:ticker)";

	public String addStock(Stock stock) {
		Integer result = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ticker", stock.getTicker());
		try {
			result = jdbcTemplate.update(SQL_INSERT_STOCK, params);
		} catch (DuplicateKeyException e) {
			// TODO use aop to do the logging
			RootserLogger.logger.debug("already inserted " + stock.getTicker());
		}
		return stock.getTicker();
	}

	public Integer addStockTx(StockTx tx) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getNumSharesHeld(String ticker) {
		return null;
	}

	@Override
	public Float getAmountSpent(String ticker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stock> getStocksHeld() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeStock(Stock stock) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeStockTx(StockTx stockTx) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Stock> getCommishFreeEtfs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getCommishFreeEtfsAndDesc() {
		// TODO Auto-generated method stub
		return null;
	}
}

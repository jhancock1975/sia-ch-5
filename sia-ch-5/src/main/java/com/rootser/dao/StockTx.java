package com.rootser.dao;

import java.sql.Timestamp;

import com.rootser.mapper.StockTxErrors;

public interface StockTx {

	public Integer getStockTxId();

	public void setStockTxId(Integer stockTxId);

	public String getTicker();

	public void setTicker(String ticker);

	public TxType getTxType();

	public void setTxType(TxType txType);

	public Integer getQty();

	public void setQty(Integer qty);

	public float getAmount();

	public void setAmount(float amount);

	public Timestamp getTxTime();

	public void setTxTime(Timestamp txTime);

	public String toString();

	public void setError(StockTxErrors errorCode);

	public StockTxErrors getErrorCode();

	public void setBroker(BrokerTypeEnum broker);

	public BrokerTypeEnum getBroker();

	public void setTxNum(String txNum);

	public String getTxNum();

}

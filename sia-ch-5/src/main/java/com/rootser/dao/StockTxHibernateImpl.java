package com.rootser.dao;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.rootser.mapper.StockTxErrors;

@Entity(name = "stockTxs")
public class StockTxHibernateImpl implements StockTx {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer stockTxId;
	private String ticker;
	@Transient
	private TxType txType;
	@Transient
	private StockTxErrors error;
	private Integer txTypeId;
	private Integer qty;
	private float amount;
	// note: hibernate puts in current time on insert without having to set
	// this field below
	private Timestamp txTime;
	@Transient
	private BrokerTypeEnum broker;
	private Integer brokerTypeId;
	private String txNum;

	public Timestamp getTxTime() {
		return txTime;
	}

	public void setTxTime(Timestamp txTime) {
		this.txTime = txTime;
	}

	public Integer getStockTxId() {
		return stockTxId;
	}

	public void setStockTxId(Integer stockTxId) {
		this.stockTxId = stockTxId;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public TxType getTxType() {
		return txType;
	}

	public void setTxType(TxType txType) {
		this.txType = txType;
		this.txTypeId = txType.intVal();
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "StockTxHibernateImpl [stockTxId=" + stockTxId + ",\n ticker="
				+ ticker + ",\n txType=" + txType + ",\n txTypeStr=" + txTypeId
				+ ",\n qty=" + qty + ",\n amount=" + amount + ",\n txTime="
				+ txTime + "]";
	}

	public void setErrorCode(StockTxErrors unmappedTxType) {
		// TODO Auto-generated method stub

	}

	public StockTxErrors getError() {
		return error;
	}

	public void setError(StockTxErrors error) {
		this.error = error;
	}

	@Override
	public StockTxErrors getErrorCode() {
		return error;
	}

	@Override
	public void setBroker(BrokerTypeEnum broker) {
		this.broker = broker;
		this.brokerTypeId = broker.toInt();
	}

	@Override
	public BrokerTypeEnum getBroker() {
		return broker;
	}

	public String getTxNum() {
		return txNum;
	}

	public void setTxNum(String txNum) {
		this.txNum = txNum;
	}

}

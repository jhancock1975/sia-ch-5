package com.rootser.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "stocks")
public class Stock {
	@Id
	private Integer stockId;
	private String ticker;
	private Boolean commishFreeEtf;
	private String description;
	@ManyToOne
	@JoinColumn(name = "brokerTypeId")
	private BrokerType broker;
	
	public Stock(String ticker) {
		this.ticker = ticker;
	}

	public Stock(){
		
	}
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Boolean getCommishFreeEtf() {
		return commishFreeEtf;
	}

	public void setCommishFreeEtf(Boolean commishFreeEtf) {
		this.commishFreeEtf = commishFreeEtf;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BrokerType getBroker() {
		return broker;
	}

	public void setBroker(BrokerType broker) {
		this.broker = broker;
	}

	@Override
	public String toString() {
		return "Stock [ticker=" + ticker + ", commishFreeEtf=" + commishFreeEtf
				+ ", description=" + description + ", broker=" + broker + "]";
	}

}

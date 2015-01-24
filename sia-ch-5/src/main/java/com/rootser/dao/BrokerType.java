package com.rootser.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="brokerTypes")
public class BrokerType {
	@Id
	Integer brokerTypeId;
	String description;
	public Integer getBrokerTypeId() {
		return brokerTypeId;
	}
	public void setBrokerTypeId(Integer brokerTypeId) {
		this.brokerTypeId = brokerTypeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}

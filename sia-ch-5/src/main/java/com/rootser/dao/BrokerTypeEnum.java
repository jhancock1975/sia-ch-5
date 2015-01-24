package com.rootser.dao;

public enum BrokerTypeEnum {
	SCOTTTRADE, TDAMERITRADE;
	public Integer toInt() {
		switch (this) {
		case SCOTTTRADE:
			return 1;
		case TDAMERITRADE:
			return 2;
		default:
			return null;
		}
	}
}

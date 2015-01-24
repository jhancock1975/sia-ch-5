package com.rootser.dao;

public enum TxType {
	buy, sell, deposit, dividend, withdraw, sell_short, short_cover;
	public Integer intVal() {
		switch (this) {
		case buy:
			return 1;
		case sell:
			return 2;
		case deposit:
			return 3;
		case dividend:
			return 4;
		case withdraw:
			return 5;
		case sell_short:
			return 6;
		case short_cover:
			return 7;
		}
		return null;
	}
}

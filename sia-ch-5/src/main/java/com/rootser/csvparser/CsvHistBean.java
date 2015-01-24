package com.rootser.csvparser;

public interface CsvHistBean {
	public String toString();

	String getTicker();

	String getQty();

	String getAmount();

	String getDate();

	boolean equals(Object obj);
}

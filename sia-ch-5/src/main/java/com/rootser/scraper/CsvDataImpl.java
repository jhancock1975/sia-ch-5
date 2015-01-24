package com.rootser.scraper;

import org.springframework.stereotype.Component;

@Component
public class CsvDataImpl implements CsvData {

	String open;
	String high;
	String low;
	String close;
	String volume;
	String dividend;
	String adjClose;
	String date;

	@Override
	public String getOpen() {
		return open;
	}

	@Override
	public void setOpen(String open) {
		this.open = open;
	}

	@Override
	public String getHigh() {
		return high;
	}

	@Override
	public void setHigh(String high) {
		this.high = high;
	}

	@Override
	public String getLow() {
		return low;
	}

	@Override
	public void setLow(String low) {
		this.low = low;
	}

	@Override
	public String getClose() {
		return close;
	}

	@Override
	public void setClose(String close) {
		this.close = close;
	}

	@Override
	public String getVolume() {
		return volume;
	}

	@Override
	public void setVolume(String volume) {
		this.volume = volume;
	}

	@Override
	public String getDividend() {
		return dividend;
	}

	@Override
	public void setDividend(String dividend) {
		this.dividend = dividend;
	}

	@Override
	public String getAdjClose() {
		return adjClose;
	}

	@Override
	public void setAdjClose(String adjClose) {
		this.adjClose = adjClose;
	}

	@Override
	public String getDate() {
		return date;
	}

	@Override
	public void setDate(String date) {
		this.date = date;
	}

}

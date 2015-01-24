package com.rootser.scraper;

public class ParseException extends Exception {

	private static final long serialVersionUID = -355162658459717467L;

	ParseException(String msg) {
		super(msg.toString());
	}
}

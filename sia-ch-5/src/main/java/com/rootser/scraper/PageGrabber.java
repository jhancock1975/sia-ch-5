package com.rootser.scraper;

public interface PageGrabber {
	public String getContent(String address) throws PageGrabberException;
}

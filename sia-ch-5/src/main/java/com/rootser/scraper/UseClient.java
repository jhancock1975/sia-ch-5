package com.rootser.scraper;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rootser.dao.RootserLogger;

public class UseClient {
	private HttpClient client;
	private ResponseHandler<String> responseHandler;
	private HttpGet get;
	private HttpGet divYear;

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"use-client.xml");
		UseClient me = (UseClient) context.getBean("useClient");
		RootserLogger.logger.debug("executing request " + me.getGet().getURI());
		try {

			// Create a response handler
			ResponseHandler<String> responseHandler = me.getResponseHandler();
			String responseBody = me.getClient().execute(me.getGet(),
					responseHandler);
			RootserLogger.logger
					.debug("----------------------------------------");
			RootserLogger.logger.debug(responseBody);
			RootserLogger.logger
					.debug("----------------------------------------");
			responseBody = me.getClient().execute(me.getDivYear(),
					responseHandler);
			RootserLogger.logger
					.debug("----------------------------------------");
			RootserLogger.logger.debug(responseBody);
			RootserLogger.logger
					.debug("----------------------------------------");

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			RootserLogger.logger.error(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			RootserLogger.logger.error(e.getMessage());
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			me.getClient().getConnectionManager().shutdown();
			((ClassPathXmlApplicationContext) context).close();
		}
	}

	public HttpClient getClient() {
		return client;
	}

	public void setClient(HttpClient client) {
		this.client = client;
	}

	public HttpGet getGet() {
		return get;
	}

	public void setGet(HttpGet get) {
		this.get = get;
	}

	public ResponseHandler<String> getResponseHandler() {
		return responseHandler;
	}

	public void setResponseHandler(ResponseHandler<String> responseHandler) {
		this.responseHandler = responseHandler;
	}

	public HttpGet getDivYear() {
		return divYear;
	}

	public void setDivYear(HttpGet divYear) {
		this.divYear = divYear;
	}
}

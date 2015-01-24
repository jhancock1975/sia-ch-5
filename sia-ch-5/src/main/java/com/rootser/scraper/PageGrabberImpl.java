package com.rootser.scraper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rootser.dao.RootserLogger;

@Service
public class PageGrabberImpl implements PageGrabber {

	@Override
	@Cacheable("name=getContent")
	public String getContent(String address) throws PageGrabberException {
		String responseBody = null;
		HttpClient client = new DefaultHttpClient();
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		HttpGet get = new HttpGet();
		try {
			get.setURI(new URI(address));
			RootserLogger.logger.debug("executing request " + get.getURI());
			responseBody = client.execute(get, responseHandler);
			RootserLogger.logger
					.debug("----------------------------------------");
			RootserLogger.logger.debug(responseBody);
			RootserLogger.logger
					.debug("----------------------------------------");

		} catch (ClientProtocolException e) {
			RootserLogger.logger.error(e.getMessage());
			throw new PageGrabberException(e.getMessage());
		} catch (IOException e) {
			throw new PageGrabberException(e.getMessage());
		} catch (URISyntaxException e) {
			throw new PageGrabberException(e.getMessage());
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			client.getConnectionManager().shutdown();
		}
		return responseBody;
	}
}

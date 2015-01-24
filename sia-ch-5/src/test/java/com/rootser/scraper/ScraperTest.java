package com.rootser.scraper;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import com.rootser.dao.RootserLogger;
import com.rootser.dao.Stock;

@RunWith(Theories.class)
@ContextConfiguration(locations = { "classpath:scraper-test.xml" })
public class ScraperTest {
	@Autowired
	ScraperSvc svc;
	@Autowired
	private ApplicationContext applicationContext;

	@DataPoints
	public static String[] getTickers() {
		String tickers[] = { "qqq", "jnk", "iih" };
		return tickers;
	}

	private TestContextManager testContextManager;

	@Before
	public void setUpStringContext() throws Exception {
		testContextManager = new TestContextManager(getClass());
		testContextManager.prepareTestInstance(this);
	}

	@Theory
	public void test(String ticker) {
		ArrayList<Stock> testParam = new ArrayList<Stock>();
		Stock stock = new Stock();
		stock.setTicker(ticker);
		testParam.add(stock);
		try {
			List<ScrapeData> data = svc.getLatest(testParam);
			assertTrue("data = " + data.toString(), passCond(data));
			for (ScrapeData curData : data) {
				RootserLogger.logger.debug(curData.toString());
			}
		} catch (NullPointerException e) {
			fail("test failed for ticker: " + ticker
					+ " with null pointer exception " + e.getMessage());
		} catch (ScrapeException e) {
			fail("test failed while scraping data for ticker " + ticker);
		}
	}

	private boolean passCond(List<ScrapeData> data) {
		return data != null && data.size() > 0;
	}

	@Test
	public void enumListTest() {
		ArrayList<ScrapeMessage> list = new ArrayList<ScrapeMessage>();
		list.add(ScrapeMessage.PARSE_ERROR);
		if (!list.contains(ScrapeMessage.PARSE_ERROR)) {
			fail("I just put parse error on the list");
		}
	}
}

package com.rootser.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rootser.scraper.ScrapeData;
import com.rootser.scraper.ScrapeException;
import com.rootser.scraper.ScraperSvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:stockman-hibernate-context.xml" })
public class HibernateDaoTest {
	@Autowired
	private com.rootser.dao.StockDao hDao;
	@Autowired
	private CacheManager cacheMan;

	public com.rootser.dao.StockDao getHDao() {
		return hDao;
	}

	public void setHDao(com.rootser.dao.StockDao hibernateDao) {
		this.hDao = hibernateDao;
	}

	@Autowired
	private com.rootser.dao.StockTx stockTx;

	public com.rootser.dao.StockTx getStockTx() {
		return stockTx;
	}

	public void setStockTx(com.rootser.dao.StockTx stockTx) {
		this.stockTx = stockTx;
	}

	@BeforeClass
	public static void setUp() {

	}

	@Autowired
	private ApplicationContext context;

	@Test
	public void test() {
		HibernateDaoTest self = (HibernateDaoTest) context.getBean("addStock");
		StockDao dao = self.getHDao();
		Stock testStock = new Stock("h2");
		dao.addStock(testStock);
		StockTx tx = self.getStockTx();
		tx.setTicker("h2");
		tx.setQty(2);
		tx.setTxType(TxType.buy);
		tx.setAmount(100F);
		dao.addStockTx(tx);
		dao.removeStockTx(tx);
		dao.removeStock(testStock);
	}

	@Test
	public void getNumSharesHeldTest() {
		HibernateDaoTest self = (HibernateDaoTest) context.getBean("addStock");
		StockDao dao = self.getHDao();
		Integer numShares = dao.getNumSharesHeld("xlf");
		assertTrue("expected num shares not equal to actual num shares",
				numShares.equals(370));
	}

	@Test
	public void getAmountSpentTest() {
		HibernateDaoTest self = (HibernateDaoTest) context.getBean("addStock");
		StockDao dao = self.getHDao();
		Float amountSpent = dao.getAmountSpent("xlf");
		assertTrue(
				"expected amount does not equal actual amount, expected: 8635.08F, actual:  "
						+ amountSpent, amountSpent.equals(8635.08F));
	}

	@Autowired
	private com.rootser.stockcalculator.StockCalcSvc calcSvc;

	@Test
	public void caclulatePresentPotentialGainTest() throws ScrapeException {
		Float gain = calcSvc.calculatePresentPotentialGain("xlf");

		assertTrue(
				"Gain is non-zero. Chances of that happening are very small.",
				gain != 0);
		RootserLogger.logger.debug("if sold today, gain would be " + gain);
	}

	@Test
	public void getPotentialGainReport() {

	}

	@Test
	public void getStocksHeldTest() {
		List<Stock> tickers = hDao.getStocksHeld();
		assertTrue(tickers.contains("JNK"));
	}

	@Test
	public void reportTest() throws ScrapeException {
		List<Stock> tickers = hDao.getStocksHeld();
		StringBuilder reportSb = new StringBuilder();
		for (Stock ticker : tickers) {
			try {
				Float potentialGain = calcSvc
						.calculatePresentPotentialGain(ticker.getTicker());
				if (potentialGain != null) {
					reportSb.append(ticker).append(": gain if sold ")
					.append(potentialGain).append("\n");
				}
			} catch (IndexOutOfBoundsException e) {
				fail("index out of bounds exception for symbol: " + ticker);
			} catch (NullPointerException e) {
				fail("null pointer exception for symbol: " + ticker
						+ ". You may need to run clean-up.sql");
			}
		}
		assertTrue(reportSb.toString().length() > 0);
	}

	@Test
	public void iihReportTest() throws ScrapeException {
		String ticker = "iih";
		StringBuilder reportSb = new StringBuilder();
		try {
			Float potentialGain = calcSvc.calculatePresentPotentialGain(ticker);
			if (potentialGain != null) {
				reportSb.append(ticker).append(": gain if sold ")
				.append(potentialGain).append("\n");
			}
		} catch (IndexOutOfBoundsException e) {
			fail("index out of bounds exception for symbol: " + ticker);
		} catch (NullPointerException e) {
			fail("null pointer exception for symbol: " + ticker
					+ ". You may need to run clean-up.sql");
		}
		assertTrue(reportSb.toString().length() > 0);
	}

	@Autowired
	CacheManager cacheManager;

	@Test
	public void cacheTest() {
		String ticker = "jnk";
		try {
			// call to force object to get cached.
			Float potentialGain = calcSvc.calculatePresentPotentialGain(ticker);
			assertTrue("cache does not exist",
					cacheManager.cacheExists("messageCache"));
			Cache cache = cacheManager.getCache("messageCache");
			assertTrue("cache does not have any keys", cache.getKeys() != null
					&& cache.getKeys().size() > 0);
			for (Object o : cache.getKeys()) {
				RootserLogger.logger.debug(cache.get(o).toString());
			}
		} catch (IndexOutOfBoundsException e) {
			fail("index out of bounds exception for symbol: " + ticker);
		} catch (NullPointerException e) {
			fail("null pointer exception for symbol: " + ticker
					+ ". You may need to run clean-up.sql");
		} catch (ScrapeException e) {
			fail("error scraping data from internet");
		}
	}

	@Autowired
	ScraperSvc scraper;

	@Test
	public void stocksNearestLowsTest() throws DbReadException, ScrapeException {
		List<Stock> tickers = hDao.getCommishFreeEtfs();
		List<ScrapeData> currentStockData = scraper.getLatest(tickers);

		assertTrue(
				"data not in order",
				currentStockData.get(0).compareTo(
						currentStockData.get(currentStockData.size() - 1)) < 0);
	}
	@Test
	public void commishFreeTickerDescMapTest()  {

		Map<String, String> map = hDao.getCommishFreeEtfsAndDesc();
		assertTrue("map is null", map != null);
		assertTrue("map doesn't have all the commish. free etfs " + map.keySet().size(), 
				map.keySet().size()==101);
		assertTrue("map didn't have right desc for etf", 
				map.get("WIP").equals("SPDR DB International Government") );

	}
}

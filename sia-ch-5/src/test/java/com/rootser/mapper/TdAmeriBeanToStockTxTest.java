package com.rootser.mapper;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.rootser.csvparser.tdameritrade.TdAmeriBean;
import com.rootser.dao.StockTx;
import com.rootser.dao.StockTxHibernateImpl;

@RunWith(Parameterized.class)
public class TdAmeriBeanToStockTxTest {
	private String ticker;
	private String qty;
	private String amount;
	private String date;
	private String description;

	public TdAmeriBeanToStockTxTest(String ticker, String qty, String amount,
			String date, String description) {
		super();
		this.ticker = ticker;
		this.qty = qty;
		this.amount = amount;
		this.date = date;
		this.description = description;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { "JNK", "1", "40.93", "01/04/2013",
				"bought" } };
		return Arrays.asList(data);
	}

	@Test
	public void mapTest() {
		TdAmeriBean sourceBean = setupBean();

		TdAmeriBeanToStockTx mapper = new TdAmeriBeanToStockTx();
		StockTxHibernateImpl stockTx = new StockTxHibernateImpl();
		mapper.map(sourceBean, stockTx);
		try {
			assertTrue("fields did not match tdAmeriBean: " + sourceBean
					+ "\n stockTx: " + stockTx,
					fieldsMatch(stockTx, sourceBean));
		} catch (NullPointerException e) {
			fail("unknown reason for a null pointer exception");
		}
	}

	private TdAmeriBean setupBean() {
		TdAmeriBean newBean = new TdAmeriBean();
		newBean.setAmount(amount);
		newBean.setDate(date);
		newBean.setQuantity(qty);
		newBean.setSymbol(ticker);
		newBean.setDescription(description);
		return newBean;
	}

	private boolean fieldsMatch(StockTx tx, TdAmeriBean sourceBean2) {
		return (tx.getTicker().equals(sourceBean2.getTicker()))
				&& (tx.getQty().equals(Integer.parseInt(sourceBean2.getQty())))
				&& (tx.getAmount() == Float.parseFloat(sourceBean2.getAmount()));
	}
}

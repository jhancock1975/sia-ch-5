package com.rootser.mapper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.rootser.csvparser.CsvHistBean;
import com.rootser.csvparser.scottrade.ScottTradeBean;
import com.rootser.dao.BrokerTypeEnum;
import com.rootser.dao.RootserLogger;
import com.rootser.dao.StockTx;
import com.rootser.dao.StockTxHibernateImpl;
import com.rootser.dao.TxType;

public class ScottTradeBeanToStockTx  implements
		CsvHistBeanToStockTx{

	@Override
	public StockTx map(CsvHistBean source, StockTx dest) {
		ScottTradeBean sBean = (ScottTradeBean) source;
		StockTxHibernateImpl hiberTx = (StockTxHibernateImpl) dest; 
		TxType txType = mapTxType(sBean.getDescription());
		if (txType != null) {
			// don't make transactions for unmapped transactions
			dest.setTicker(source.getTicker());
			dest.setAmount(Float.parseFloat(source.getAmount()));
			dest.setQty(Integer.parseInt(source.getQty()));
			dest.setTxTime(mapDate(source.getDate()));
			dest.setTxType(txType);
			dest.setBroker(BrokerTypeEnum.SCOTTTRADE);
			dest.setTxNum(sBean.getCUSIP() + sBean.getCUSIP() + "|"
					+ sBean.getActionId() + "|" + sBean.getTradeNumber());
		} else {
			dest.setError(StockTxErrors.UNMAPPED_TX_TYPE);
			return hiberTx;
		}
		return hiberTx;
	}

	private TxType mapTxType(String description) {
		description = description.toLowerCase();
		if (description.contains("bought")) {
			return TxType.buy;
		} else if (description.contains("sold")) {
			return TxType.sell;
		} else if (description.contains("dividend")) {
			return TxType.dividend;
		} else if (description.contains("deposit")) {
			return TxType.deposit;
		} else {
			return null;
		}
	}

	private Timestamp mapDate(String date) {
		Timestamp result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date dateObj;
		try {
			dateObj = sdf.parse(date);
			result = new Timestamp(dateObj.getTime());
		} catch (ParseException e) {
			RootserLogger.logger.error("problem converting date.");
		}
		return result;
	}

}

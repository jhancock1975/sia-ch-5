package com.rootser.mapper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.rootser.csvparser.CsvHistBean;
import com.rootser.csvparser.tdameritrade.TdAmeriBean;
import com.rootser.dao.BrokerTypeEnum;
import com.rootser.dao.HibernateStockDao;
import com.rootser.dao.RootserLogger;
import com.rootser.dao.StockTx;
import com.rootser.dao.StockTxHibernateImpl;
import com.rootser.dao.TxType;

public class TdAmeriBeanToStockTx  implements
		CsvHistBeanToStockTx {

	@Override
	public StockTx map(CsvHistBean source, StockTx dest) {
		TdAmeriBean tdBean = (TdAmeriBean) source;
		StockTxHibernateImpl hiberTx=  (StockTxHibernateImpl)dest;
		TxType txType = mapTxType(tdBean.getDescription());
		if (txType != null) {
			// don't create transactions for unmapped transactions
			copyFields(tdBean, hiberTx, txType);
		} else {
			dest.setError(StockTxErrors.UNMAPPED_TX_TYPE);
			return hiberTx;
		}
		return hiberTx;
	}

	private void copyFields(TdAmeriBean source, StockTxHibernateImpl dest,
			TxType txType) {
		dest.setTicker(source.getTicker());
		dest.setAmount(Float.parseFloat(source.getAmount()));
		try {
			dest.setQty(Integer.parseInt(source.getQty()));
		} catch (NumberFormatException e) {
			dest.setQty(1);
		}
		dest.setTxTime(mapDate(source.getDate()));
		dest.setTxType(txType);
		dest.setBroker(BrokerTypeEnum.TDAMERITRADE);
		dest.setTxNum(source.getTransaction_id());
	}

	private TxType mapTxType(String description) {
		TxType result = null;
		if (description != null) {
			description = description.toLowerCase();
			if (description.contains("bought")) {
				result = TxType.buy;
			} else if (description.contains("sold")) {
				result = TxType.sell;
			} else if (description.contains("dividend")) {
				result = TxType.dividend;
			} else if (description
					.contains("client requested electronic funding receipt (funds now)")
					|| description.contains("recurring electronic funding")) {
				result = TxType.deposit;
			}
		}
		return result;
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

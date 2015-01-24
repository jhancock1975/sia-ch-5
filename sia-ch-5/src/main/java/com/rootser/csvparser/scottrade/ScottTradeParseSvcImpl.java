package com.rootser.csvparser.scottrade;

import org.apache.commons.validator.GenericValidator;

import com.rootser.csvparser.AbstractParseSvcImpl;
import com.rootser.csvparser.ParseServiceResponse;

public class ScottTradeParseSvcImpl extends AbstractParseSvcImpl {

	public static final int SYMBOL = 0;
	public static final int QUANTITY = 1;
	public static final int PRICE = 2;
	public static final int ACTION_NAME_US = 3;
	public static final int TRADE_DATE = 4;
	public static final int SETTLED_DATE = 5;
	public static final int INTEREST = 6;
	public static final int AMOUNT = 7;
	public static final int COMMISSION = 8;
	public static final int FEES = 9;
	public static final int CUSIP = 10;
	public static final int DESCRIPTION = 11;
	public static final int ACTIONID = 12;
	public static final int TRADE_NUMBER = 13;
	public static final int RECORD_TYPE = 14;
	public static final int TAX_LOT_NUMBER = 15;

	protected ParseServiceResponse populateBean(ParseServiceResponse curPair, String[] splitLine) {
		curPair = new ParseServiceResponse();
		ScottTradeBean bean = new ScottTradeBean();
		if (splitLine.length > SYMBOL)
			bean.setSymbol(splitLine[SYMBOL]);
		if (splitLine.length > QUANTITY)
			bean.setQuantity(splitLine[QUANTITY]);
		if (splitLine.length > PRICE)
			bean.setPrice(splitLine[PRICE]);
		if (splitLine.length > ACTION_NAME_US)
			bean.setActionNameUS(splitLine[ACTION_NAME_US]);
		if (splitLine.length > TRADE_DATE)
			bean.setTradeDate(splitLine[TRADE_DATE]);
		if (splitLine.length > SETTLED_DATE)
			bean.setSettledDate(splitLine[SETTLED_DATE]);
		if (splitLine.length > INTEREST)
			bean.setInterest(splitLine[INTEREST]);
		if (splitLine.length > AMOUNT)
			bean.setAmount(splitLine[AMOUNT]);
		if (splitLine.length > COMMISSION)
			bean.setCommission(splitLine[COMMISSION]);
		if (splitLine.length > FEES)
			bean.setFees(splitLine[FEES]);
		if (splitLine.length > CUSIP)
			bean.setCUSIP(splitLine[CUSIP]);
		if (splitLine.length > DESCRIPTION)
			bean.setDescription(splitLine[DESCRIPTION]);
		if (splitLine.length > ACTIONID)
			bean.setActionId(splitLine[ACTIONID]);
		if (splitLine.length > TRADE_NUMBER)
			bean.setTradeNumber(splitLine[TRADE_NUMBER]);
		if (splitLine.length > RECORD_TYPE)
			bean.setRecordType(splitLine[RECORD_TYPE]);
		if (splitLine.length > TAX_LOT_NUMBER)
			bean.setTaxLotNumber(splitLine[TAX_LOT_NUMBER]);
		curPair.setHistBean(bean);
		return curPair;
	}
	
	static int[] requiredFields = {
		SYMBOL,
		QUANTITY,
		TRADE_DATE,
		ACTION_NAME_US,
		TRADE_NUMBER};
	
	protected int[] getRequiredFields(){
		return requiredFields;
	}
}

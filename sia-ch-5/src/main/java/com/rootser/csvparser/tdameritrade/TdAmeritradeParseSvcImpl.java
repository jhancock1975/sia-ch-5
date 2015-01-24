package com.rootser.csvparser.tdameritrade;

import org.apache.commons.validator.GenericValidator;

import com.rootser.csvparser.AbstractParseSvcImpl;
import com.rootser.csvparser.ParseErrMsg;
import com.rootser.csvparser.ParseServiceResponse;
public class TdAmeritradeParseSvcImpl extends AbstractParseSvcImpl {
	public static final int DATE = 0;
	public static final int TRANSACTION_ID = 1;
	public static final int DESCRIPTION = 2;
	public static final int QUANTITY = 3;
	public static final int SYMBOL = 4;
	public static final int PRICE = 5;
	public static final int COMMISSION = 6;
	public static final int AMOUNT = 7;
	public static final int NET_CASH_BALANCE = 8;
	public static final int REG_FEE = 9;
	public static final int SHORT_TERM_RDM_FEE = 10;
	public static final int FUND_REDEMPTION_FEE = 11;
	public static final int DEFERRED_SALES_CHARGE = 12;

	protected ParseServiceResponse populateBean(
			ParseServiceResponse curPair, String[] splitLine) {
		curPair = new ParseServiceResponse();
		TdAmeriBean tdAmeriBean = new TdAmeriBean();
		if (splitLine.length > DATE) {
			tdAmeriBean.setDate(splitLine[DATE]);
		}
		if (splitLine.length > TRANSACTION_ID) {
			tdAmeriBean.setTransaction_id(splitLine[TRANSACTION_ID]);
		}
		if (splitLine.length > DESCRIPTION) {
			tdAmeriBean.setDescription(splitLine[DESCRIPTION]);
		}
		if (splitLine.length > QUANTITY) {
			tdAmeriBean.setQuantity(splitLine[QUANTITY]);
		}
		if (splitLine.length > SYMBOL) {
			tdAmeriBean.setSymbol(splitLine[SYMBOL]);
		}
		if (splitLine.length > PRICE) {
			tdAmeriBean.setPrice(splitLine[PRICE]);
		}
		if (splitLine.length > COMMISSION) {
			tdAmeriBean.setCommission(splitLine[COMMISSION]);
		}
		if (splitLine.length > AMOUNT) {
			tdAmeriBean.setAmount(splitLine[AMOUNT]);
		}
		if (splitLine.length > NET_CASH_BALANCE) {
			tdAmeriBean.setNet_cash_balance(splitLine[NET_CASH_BALANCE]);
		}
		if (splitLine.length > REG_FEE) {
			tdAmeriBean.setReg_fee(splitLine[REG_FEE]);
		}
		if (splitLine.length > SHORT_TERM_RDM_FEE) {
			tdAmeriBean.setShort_term_rdm_fee(splitLine[SHORT_TERM_RDM_FEE]);
		}
		if (splitLine.length > FUND_REDEMPTION_FEE) {
			tdAmeriBean.setFund_redemption_fee(splitLine[FUND_REDEMPTION_FEE]);
		}
		if (splitLine.length > DEFERRED_SALES_CHARGE) {
			tdAmeriBean
					.setDeferred_sales_charge(splitLine[DEFERRED_SALES_CHARGE]);
		}
		curPair.setHistBean(tdAmeriBean);
		return curPair;
	}
	
	/**
	 * StockTxs has
	 * ticker qty amount txTime txTypeId brokerTypeId txNum 
	 * so csv needs to have something we can map to these elements, or we don't use it
	 */
	protected boolean isValidLine(String[] splitLine) {
		boolean cond =  (splitLine.length > SYMBOL &&
				! GenericValidator.isBlankOrNull(splitLine[SYMBOL]) &&
				splitLine.length > QUANTITY &&
				! GenericValidator.isBlankOrNull(splitLine[QUANTITY]) &&
				splitLine.length > AMOUNT &&
				! GenericValidator.isBlankOrNull(splitLine[AMOUNT]) &&
				splitLine.length > DATE &&
				! GenericValidator.isBlankOrNull(splitLine[DATE]) &&
				splitLine.length > DESCRIPTION &&
				! GenericValidator.isBlankOrNull(splitLine[DESCRIPTION]) &&
				splitLine.length > TRANSACTION_ID &&
				! GenericValidator.isBlankOrNull(splitLine[TRANSACTION_ID]));
		return cond;
	}
	int[] requiredFields = {
			SYMBOL, QUANTITY, AMOUNT, DATE, DESCRIPTION, TRANSACTION_ID};

	@Override
	protected int[] getRequiredFields() {
		return requiredFields;
	}
}

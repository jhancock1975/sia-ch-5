package com.rootser.csvparser.scottrade;

import com.rootser.csvparser.CsvHistBean;
import com.rootser.csvparser.ParseErrMsg;
import com.rootser.csvparser.ParseServiceResponse;

public class ScottTradeParseSvcResponseImpl extends ParseServiceResponse {
	private ScottTradeBean histBean;
	private ParseErrMsg parseErrMsg;

	@Override
	public CsvHistBean getHistBean() {
		return histBean;
	}

	@Override
	public void setHistBean(CsvHistBean histBean) {
		this.histBean = (ScottTradeBean) histBean;
	}

	@Override
	public ParseErrMsg getErrMsg() {
		return parseErrMsg;
	}

	@Override
	public void setErrMsg(ParseErrMsg parseErrMsg) {
		this.parseErrMsg = (ParseErrMsg) parseErrMsg;
	}

}

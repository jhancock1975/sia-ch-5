package com.rootser.csvparser.scottrade;

import com.rootser.csvparser.CsvHistBean;
import com.rootser.csvparser.ParseErrMsg;
import com.rootser.csvparser.ParseServiceResponse;

public class ScottTradeHistBeanParseErrPair extends ParseServiceResponse {
	private ScottTradeBean histBean;
	private ParseErrMsg errMsg;

	@Override
	public void setHistBean(CsvHistBean histBean) {
		this.histBean = (ScottTradeBean) histBean;

	}

	@Override
	public void setErrMsg(ParseErrMsg parseErrMsg) {
		this.errMsg = (ParseErrMsg) parseErrMsg;

	}

	@Override
	public ScottTradeBean getHistBean() {
		return histBean;
	}

	@Override
	public ParseErrMsg getErrMsg() {
		return errMsg;
	}
}

package com.rootser.csvparser.tdameritrade;

import com.rootser.csvparser.CsvHistBean;
import com.rootser.csvparser.ParseErrMsg;
import com.rootser.csvparser.ParseServiceResponse;

public class TdAmeriTradeHistBeanParseErrPair extends ParseServiceResponse {
	private TdAmeriBean histBean;
	private ParseErrMsg errMsg;

	@Override
	public void setHistBean(CsvHistBean histBean) {
		this.histBean = (TdAmeriBean) histBean;

	}

	@Override
	public void setErrMsg(ParseErrMsg parseErrMsg) {
		this.errMsg = (ParseErrMsg) parseErrMsg;

	}

	@Override
	public TdAmeriBean getHistBean() {
		return histBean;
	}

	@Override
	public ParseErrMsg getErrMsg() {
		return errMsg;
	}
}

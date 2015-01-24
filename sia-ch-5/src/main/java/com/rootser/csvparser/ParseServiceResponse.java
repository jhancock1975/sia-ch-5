package com.rootser.csvparser;

public class ParseServiceResponse {
	private CsvHistBean histBean;
	private ParseErrMsg errMsg;
	public CsvHistBean getHistBean() {
		return histBean;
	}
	public void setHistBean(CsvHistBean histBean) {
		this.histBean = histBean;
	}
	public ParseErrMsg getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(ParseErrMsg errMsg) {
		this.errMsg = errMsg;
	}
	

}

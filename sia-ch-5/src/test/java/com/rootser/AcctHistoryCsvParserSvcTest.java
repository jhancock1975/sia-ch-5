package com.rootser;

import com.rootser.csvparser.CsvParserSvc;
import com.rootser.csvparser.CsvHistBean;

public class AcctHistoryCsvParserSvcTest {

	private CsvParserSvc parseSvc;
	private String configFileName;
	int expectedLength;
	private String sourceBeanClassName;
	private CsvHistBean expectedCsvBean;

	public CsvHistBean getExpectedCsvBean() {
		return expectedCsvBean;
	}

	public void setExpectedCsvBean(CsvHistBean expectedBean) {
		this.expectedCsvBean = expectedBean;
	}

	public CsvParserSvc getParseSvc() {
		return parseSvc;
	}

	public void setParseSvc(CsvParserSvc tdParseSvc) {
		this.parseSvc = tdParseSvc;
	}

	public String getConfigFileName() {
		return configFileName;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	public int getExpectedLength() {
		return expectedLength;
	}

	public void setExpectedLength(int expectedLength) {
		this.expectedLength = expectedLength;
	}

	public String getSourceBeanClassName() {
		return sourceBeanClassName;
	}

	public void setSourceBeanClassName(String sourceBeanClassName) {
		this.sourceBeanClassName = sourceBeanClassName;
	}

}

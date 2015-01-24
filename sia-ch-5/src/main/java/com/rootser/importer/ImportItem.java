package com.rootser.importer;

import java.util.ArrayList;
import java.util.List;

import com.rootser.dao.StockTx;

public class ImportItem {
	StockTx stockTx;
	List<ImportMsg> messages;
	public ImportItem(){
		messages = new ArrayList<ImportMsg>();
	}
	public StockTx getStockTx() {
		return stockTx;
	}
	public void setStockTx(StockTx stockTx) {
		this.stockTx = stockTx;
	}
	public List<ImportMsg> getMessages() {
		return messages;
	}
	public void setMessages(List<ImportMsg> messages) {
		this.messages = messages;
	}
	
}

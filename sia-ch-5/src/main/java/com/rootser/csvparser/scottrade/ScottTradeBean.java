package com.rootser.csvparser.scottrade;

import com.rootser.csvparser.CsvHistBean;

public class ScottTradeBean implements CsvHistBean {
	private String Symbol;
	private String Quantity;
	private String Price;
	private String ActionNameUS;
	private String TradeDate;
	private String SettledDate;
	private String Interest;
	private String Amount;
	private String Commission;
	private String Fees;
	private String CUSIP;
	private String Description;
	private String ActionId;
	private String TradeNumber;
	private String RecordType;
	private String TaxLotNumber;

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getActionNameUS() {
		return ActionNameUS;
	}

	public void setActionNameUS(String actionNameUS) {
		ActionNameUS = actionNameUS;
	}

	public String getTradeDate() {
		return TradeDate;
	}

	public void setTradeDate(String tradeDate) {
		TradeDate = tradeDate;
	}

	public String getSettledDate() {
		return SettledDate;
	}

	public void setSettledDate(String settledDate) {
		SettledDate = settledDate;
	}

	public String getInterest() {
		return Interest;
	}

	public void setInterest(String interest) {
		Interest = interest;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getCommission() {
		return Commission;
	}

	public void setCommission(String commission) {
		Commission = commission;
	}

	public String getFees() {
		return Fees;
	}

	public void setFees(String fees) {
		Fees = fees;
	}

	public String getCUSIP() {
		return CUSIP;
	}

	public void setCUSIP(String cUSIP) {
		CUSIP = cUSIP;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getActionId() {
		return ActionId;
	}

	public void setActionId(String actionId) {
		ActionId = actionId;
	}

	public String getTradeNumber() {
		return TradeNumber;
	}

	public void setTradeNumber(String tradeNumber) {
		TradeNumber = tradeNumber;
	}

	public String getRecordType() {
		return RecordType;
	}

	public void setRecordType(String recordType) {
		RecordType = recordType;
	}

	public String getTaxLotNumber() {
		return TaxLotNumber;
	}

	public void setTaxLotNumber(String taxLotNumber) {
		TaxLotNumber = taxLotNumber;
	}

	@Override
	public String getTicker() {
		return Symbol;
	}

	@Override
	public String getQty() {
		return Quantity;
	}

	public void setDate(String date) {
		TradeDate = date;
	}

	@Override
	public String getDate() {
		return TradeDate;
	}

	public String getSymbol() {
		return Symbol;
	}

	public void setSymbol(String symbol) {
		Symbol = symbol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Amount == null) ? 0 : Amount.hashCode());
		result = prime * result
				+ ((Quantity == null) ? 0 : Quantity.hashCode());
		result = prime * result + ((Symbol == null) ? 0 : Symbol.hashCode());
		result = prime * result
				+ ((TradeDate == null) ? 0 : TradeDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScottTradeBean other = (ScottTradeBean) obj;
		if (Amount == null) {
			if (other.Amount != null)
				return false;
		} else if (!Amount.equals(other.Amount))
			return false;
		if (Quantity == null) {
			if (other.Quantity != null)
				return false;
		} else if (!Quantity.equals(other.Quantity))
			return false;
		if (Symbol == null) {
			if (other.Symbol != null)
				return false;
		} else if (!Symbol.equals(other.Symbol))
			return false;
		if (TradeDate == null) {
			if (other.TradeDate != null)
				return false;
		} else if (!TradeDate.equals(other.TradeDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ScottTradeBean [Symbol=" + Symbol + ", Quantity=" + Quantity
				+ ", TradeDate=" + TradeDate + ", Amount=" + Amount + "]";
	}

}

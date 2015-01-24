package com.rootser.csvparser.tdameritrade;

import com.rootser.csvparser.CsvHistBean;

public class TdAmeriBean implements CsvHistBean {
	

	private String date;
	private String transaction_id;
	private String description;
	private String quantity;
	private String symbol;
	private String price;
	private String commission;
	private String amount;
	private String net_cash_balance;
	private String reg_fee;
	private String short_term_rdm_fee;
	private String fund_redemption_fee;
	private String deferred_sales_charge;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getNet_cash_balance() {
		return net_cash_balance;
	}
	public void setNet_cash_balance(String net_cash_balance) {
		this.net_cash_balance = net_cash_balance;
	}
	public String getReg_fee() {
		return reg_fee;
	}
	public void setReg_fee(String reg_fee) {
		this.reg_fee = reg_fee;
	}
	public String getShort_term_rdm_fee() {
		return short_term_rdm_fee;
	}
	public void setShort_term_rdm_fee(String short_term_rdm_fee) {
		this.short_term_rdm_fee = short_term_rdm_fee;
	}
	public String getFund_redemption_fee() {
		return fund_redemption_fee;
	}
	public void setFund_redemption_fee(String fund_redemption_fee) {
		this.fund_redemption_fee = fund_redemption_fee;
	}
	public String getDeferred_sales_charge() {
		return deferred_sales_charge;
	}
	public void setDeferred_sales_charge(String deferred_sales_charge) {
		this.deferred_sales_charge = deferred_sales_charge;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((commission == null) ? 0 : commission.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime
				* result
				+ ((deferred_sales_charge == null) ? 0 : deferred_sales_charge
						.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime
				* result
				+ ((fund_redemption_fee == null) ? 0 : fund_redemption_fee
						.hashCode());
		result = prime
				* result
				+ ((net_cash_balance == null) ? 0 : net_cash_balance.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((reg_fee == null) ? 0 : reg_fee.hashCode());
		result = prime
				* result
				+ ((short_term_rdm_fee == null) ? 0 : short_term_rdm_fee
						.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result
				+ ((transaction_id == null) ? 0 : transaction_id.hashCode());
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
		TdAmeriBean other = (TdAmeriBean) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (commission == null) {
			if (other.commission != null)
				return false;
		} else if (!commission.equals(other.commission))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (deferred_sales_charge == null) {
			if (other.deferred_sales_charge != null)
				return false;
		} else if (!deferred_sales_charge.equals(other.deferred_sales_charge))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fund_redemption_fee == null) {
			if (other.fund_redemption_fee != null)
				return false;
		} else if (!fund_redemption_fee.equals(other.fund_redemption_fee))
			return false;
		if (net_cash_balance == null) {
			if (other.net_cash_balance != null)
				return false;
		} else if (!net_cash_balance.equals(other.net_cash_balance))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (reg_fee == null) {
			if (other.reg_fee != null)
				return false;
		} else if (!reg_fee.equals(other.reg_fee))
			return false;
		if (short_term_rdm_fee == null) {
			if (other.short_term_rdm_fee != null)
				return false;
		} else if (!short_term_rdm_fee.equals(other.short_term_rdm_fee))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (transaction_id == null) {
			if (other.transaction_id != null)
				return false;
		} else if (!transaction_id.equals(other.transaction_id))
			return false;
		return true;
	}
	@Override
	public String getTicker() {
		return this.symbol;
	}
	@Override
	public String getQty() {
		return this.amount;
	}
	@Override
	public String toString() {
		return "TdAmeriBean [date=" + date + ", quantity=" + quantity
				+ ", symbol=" + symbol + "]";
	}

	

}

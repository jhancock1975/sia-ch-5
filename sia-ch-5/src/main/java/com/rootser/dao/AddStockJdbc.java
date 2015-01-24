package com.rootser.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AddStockJdbc {
	private com.rootser.dao.StockDao dao;

	public StockDao getDao() {
		return dao;
	}

	public void setDao(StockDao dao) {
		this.dao = dao;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "stockman-jdbc-context.xml" });
		AddStockJdbc self = (AddStockJdbc) context.getBean("addStock");
		self.getDao().addStock(new Stock("jdbc"));
		((ClassPathXmlApplicationContext) context).close();
	}
}

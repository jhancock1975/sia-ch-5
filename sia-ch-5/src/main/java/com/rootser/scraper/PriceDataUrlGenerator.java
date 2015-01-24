package com.rootser.scraper;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("priceUrl")
public class PriceDataUrlGenerator extends AbstractUrlGenerator implements
		UrlGenerator {

	@Override
	public String genUrl(String ticker) {
		Calendar cal = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();
		sb.append(super.getUrlBase()).append(ticker).append("&a=")
				.append(cal.get(Calendar.MONTH)).append("&b=")
				.append(cal.get(Calendar.DAY_OF_MONTH)).append("&c=")
				.append(cal.get(Calendar.YEAR) - 1).append("&d=")
				.append(cal.get(Calendar.MONTH)).append("&e=")
				.append(cal.get(Calendar.DAY_OF_MONTH)).append("&f=")
				.append(cal.get(Calendar.YEAR)).append("&g=d&ignore=.csv");
		return sb.toString();
	}

}
// http://ichart.finance.yahoo.com/table.csv?s=JNK&a=03&b=13&c=2012&d=03&e=13&f=2013&g=d&ignore=.csv
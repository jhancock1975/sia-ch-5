package com.rootser.scraper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rootser.dao.RootserLogger;

@Service
public class ScraperParserImpl implements ScraperParser {

	@Override
	public ScrapeData parse(ScrapedCsvResponse dailyData,
			ScrapedCsvResponse dividendData) {
		// @TODO this could be a bean
		ScrapeData result = new ScrapeDataImpl();
		if (dailyData.getMessages().contains(ScrapeMessage.SUCCESS)) {
			doDailyData(dailyData, result);
			if (dividendData.getMessages().contains(ScrapeMessage.SUCCESS)) {
				doDividendData(dividendData, result);
				result.addMessage(ScrapeMessage.SUCCESS);
			} else {
				result.addMessage(ScrapeMessage.PRICE_DATA_NO_DIVIDENDS);
			}
		} else {
			result.addMessage(ScrapeMessage.PARSE_ERROR);
		}
		return result;
	}

	private void doDividendData(ScrapedCsvResponse dividendData,
			ScrapeData result) {
		String[] dividendLines = dividendData.getData().split("\n");
		List<CsvData> dividendCsvData = getCsvDataList(dividendLines);
		result.setDivYear(getDivYear(dividendCsvData));
		result.setDivYield((result.getDivYear()/result.getLast())*100);
	}

	private void doDailyData(ScrapedCsvResponse dailyData, ScrapeData result) {
		String[] dailyLines = dailyData.getData().split("\n");
		List<CsvData> dailyCsvData = getCsvDataList(dailyLines);
		result.setFifty2WeekHigh(get52WkHigh(dailyCsvData));
		result.setFifty2WeekLow(get52WkLow(dailyCsvData));
		result.setLast(getLast(dailyCsvData));
	}

	private Float getLast(List<CsvData> dailyCsvData) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Float result = null;
		try {
			Date maxDate = sdf.parse("1970-01-01");
			for (CsvData csv : dailyCsvData) {
				Date curDate = sdf.parse(csv.getDate());
				if (curDate.compareTo(maxDate) > 0) {
					maxDate = curDate;
					result = Float.parseFloat(csv.getClose());
				}
			}
		} catch (ParseException e) {
			RootserLogger.logger.debug("caught un-parseable date");
		}
		return result;
	}

	private Float get52WkLow(List<CsvData> dailyCsvData) {
		Float result = Float.MAX_VALUE;
		for (CsvData csv : dailyCsvData) {
			Float curClose = Float.parseFloat(csv.getLow());
			if (curClose < result) {
				result = curClose;
			}
		}
		return result;
	}

	private Float get52WkHigh(List<CsvData> dailyCsvData) {
		Float result = Float.MIN_VALUE;
		for (CsvData csv : dailyCsvData) {
			Float curClose = Float.parseFloat(csv.getHigh());
			if (curClose > result) {
				result = curClose;
			}
		}
		return result;
	}

	private Float getDivYear(List<CsvData> dividendCsvData) {
		Float sum = 0F;
		for (CsvData csv : dividendCsvData) {
			sum = sum + Float.parseFloat(csv.getDividend());
		}
		return sum;
	}

	private List<CsvData> getCsvDataList(String[] lines) {
		List<CsvData> csvList = new ArrayList<CsvData>();
		for (int i = 1; i < lines.length; i++) {
			String[] splitLine = lines[i].split(",");
			CsvData data = new CsvDataImpl();
			if (splitLine.length > 2) {
				data.setAdjClose(splitLine[YahooFinanceColumns.ADJ_CLOSE
						.getIndex()]);
				data.setClose(splitLine[YahooFinanceColumns.CLOSE.getIndex()]);
				data.setDate(splitLine[YahooFinanceColumns.DATE.getIndex()]);
				data.setHigh(splitLine[YahooFinanceColumns.HIGH.getIndex()]);
				data.setLow(splitLine[YahooFinanceColumns.LOW.getIndex()]);
				data.setOpen(splitLine[YahooFinanceColumns.OPEN.getIndex()]);
				data.setVolume(splitLine[YahooFinanceColumns.VOLUME.getIndex()]);
			} else if (splitLine.length == 2) {
				data.setDate(splitLine[YahooFinanceColumns.DATE.getIndex()]);
				data.setDividend(splitLine[YahooFinanceColumns.DIVIDEND
						.getIndex()]);
			}
			csvList.add(data);
		}
		return csvList;
	}

	private boolean bothSuccessful(List<ScrapeMessage> messages,
			List<ScrapeMessage> messages2) {
		return messages.contains(ScrapeMessage.SUCCESS)
				&& messages2.contains(ScrapeMessage.SUCCESS);
	}
}

package com.rootser.importer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.rootser.csvparser.CsvHistBean;
import com.rootser.csvparser.CsvParserSvc;
import com.rootser.csvparser.ParseErrMsg;
import com.rootser.csvparser.ParseServiceResponse;
import com.rootser.dao.RootserLogger;
import com.rootser.dao.Stock;
import com.rootser.dao.StockDao;
import com.rootser.dao.StockTx;
import com.rootser.dao.StockTxHibernateImpl;
import com.rootser.mapper.CsvHistBeanToStockTx;
import com.rootser.mapper.StockTxErrors;

public abstract class ImporterAbstract implements Importer{
	private static CsvParserSvc parseService;
	private static StockDao dao;
	CsvHistBeanToStockTx  mapper;
	public StockDao getDao() {
		return dao;
	}

	public void setDao(StockDao aDao) {
		dao = aDao;
	}

	public CsvParserSvc getParseService() {
		return parseService;
	}

	public void setParseService(CsvParserSvc aParseService) {
		parseService = aParseService;
	}

	
	public CsvHistBeanToStockTx getMapper() {
		return mapper;
	}

	public void setMapper(CsvHistBeanToStockTx mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<ImportItem> importCsv() {
		RootserLogger.logger.debug("starting " + parseService.getClass().getName() + " import");
		List<ParseServiceResponse> responseList;
		try {
			responseList = parseService.parseAndReportErrors();
			for(ParseServiceResponse response: responseList){
				if (response.getErrMsg() != ParseErrMsg.INVALID_CSV_LINE){
					StockTx tx = new StockTxHibernateImpl();
					tx = mapper.map((CsvHistBean) response.getHistBean(),  (StockTx) tx);
					if (tx.getErrorCode() != StockTxErrors.UNMAPPED_TX_TYPE){
						ImportItem item  = new ImportItem();
						item.setStockTx(tx);
						try {
							dao.addStockTx(tx);
						} catch(DataIntegrityViolationException e){
							RootserLogger.logger.debug("attempting to add new stock from tx: " + tx.toString());
							addNewStock(tx, item);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			RootserLogger.logger.debug("file not found " + e.getMessage());
		} catch (IOException e) {
			RootserLogger.logger.debug("i/o exception " + e.getMessage());
		}
		return null;
	}

	private ImportItem addNewStock(StockTx tx, ImportItem item) {
		try {
			Stock stock = new Stock();
			stock.setTicker(tx.getTicker());
			dao.addStock(stock);
			dao.addStockTx(tx);
		} catch(DataIntegrityViolationException e){
			item.getMessages().add(ImportMsg.UNABLE_TO_INSERT_TX);
			RootserLogger.logger.debug("unable to add stock tx: " + tx.toString());
			RootserLogger.logger.debug("exception thrown " + e.getMessage());
		}
		return item;
	}
}

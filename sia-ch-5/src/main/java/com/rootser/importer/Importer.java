package com.rootser.importer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rootser.mapper.CsvHistBeanToStockTx;

@Service
public interface Importer {
	public List<ImportItem> importCsv();
	public void setMapper(CsvHistBeanToStockTx mapper);
}

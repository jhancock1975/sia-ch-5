package com.rootser.csvparser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

public interface CsvParserSvc {
	public List<ParseServiceResponse> parseAndReportErrors()
			throws IOException, FileNotFoundException;
}

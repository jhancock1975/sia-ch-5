package com.rootser.csvparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.validator.GenericValidator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.rootser.dao.RootserLogger;

public abstract class AbstractParseSvcImpl implements CsvParserSvc {
	private PathMatchingResourcePatternResolver resolver;
	private String resourcePattrnStr;
	private String delimiter;

	
	@Override
	public ArrayList<ParseServiceResponse> parseAndReportErrors()
			throws IOException, FileNotFoundException {
		ArrayList<ParseServiceResponse> result = new ArrayList<ParseServiceResponse>();
		Resource[] csvFiles = resolver.getResources(resourcePattrnStr);

		for (Resource r : csvFiles) {
			RootserLogger.logger.debug(r.getFilename());
			URL url = r.getURL();
			String fileName = url.getFile();
			Scanner s = new Scanner(new File(fileName));
			while (s.hasNextLine()) {
				String curLine = s.nextLine();
				String[] splitLine = curLine.split(getDelimiter());
				ParseServiceResponse curPair = this.processLine(splitLine);
				result.add(curPair);
			}

		}
		return result;
	}

	protected  ParseServiceResponse processLine(String[] splitLine){
		ParseServiceResponse curPair =null;
		if ( isValidLine(getRequiredFields(), splitLine)){
			curPair = populateBean(curPair, splitLine);
	
		} else {
			ParseErrMsg msg = ParseErrMsg.INVALID_CSV_LINE;
			curPair = new ParseServiceResponse();
			curPair.setErrMsg(msg);
		}
		
		return curPair;
	}
	private boolean isValidLine(int[] fields, String[] splitLine){
		boolean cond = true;
		for (int i: fields){
			cond = cond && checkField(splitLine, i);
		}
		return cond;
	}
	private boolean checkField(String[] splitLine, int i){
		return splitLine.length > i && ! GenericValidator.isBlankOrNull(splitLine[i]);
	}
	
	protected abstract ParseServiceResponse populateBean(ParseServiceResponse curPair, String[] splitLine);
	
	protected abstract int[] getRequiredFields();
	
	public PathMatchingResourcePatternResolver getResolver() {
		return resolver;
	}

	public void setResolver(PathMatchingResourcePatternResolver resolver) {
		this.resolver = resolver;
	}

	public String getResourcePattrnStr() {
		return resourcePattrnStr;
	}

	public void setResourcePattrnStr(String resourcePattrnStr) {
		this.resourcePattrnStr = resourcePattrnStr;
	}

	public String getDelimiter() {
		return delimiter;
	}
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

}

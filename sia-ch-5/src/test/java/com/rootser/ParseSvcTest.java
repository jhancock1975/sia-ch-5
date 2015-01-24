package com.rootser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rootser.csvparser.CsvHistBean;
import com.rootser.csvparser.CsvParserSvc;
import com.rootser.csvparser.ParseServiceResponse;
import com.rootser.dao.RootserLogger;

public class ParseSvcTest {
	static ApplicationContext context;
	static CsvParserSvc tdSvc; //td amieritrade csv parser
	static CsvParserSvc stSvc; //scott trade csv parser
	static List<ParseServiceResponse> tdData;
	static List<ParseServiceResponse> stData;
	
	@BeforeClass
	public static void setup(){
		context = new ClassPathXmlApplicationContext(
				"parse-svc.xml");
		try {
			tdSvc=(CsvParserSvc)context.getBean("tdParser");
		 tdData = tdSvc.parseAndReportErrors();
		 stSvc = (CsvParserSvc) context.getBean("stParser");
		 printResults(tdData);
		} catch (FileNotFoundException e) {
			RootserLogger.logger.error(e.getMessage());
			fail("test should not throw file not found exception.");
		} catch (IOException e) {
			RootserLogger.logger.error(e.getMessage());
			fail("test should not throw file not found exception.");
		}
	}
	@Test
	public void testScottTrade() {
		try {
			List<ParseServiceResponse> results = stSvc.parseAndReportErrors();
			printResults(results);
		} catch (FileNotFoundException e) {
			RootserLogger.logger.error(e.getMessage());
			fail("test should not throw file not found exception.");
		} catch (IOException e) {
			RootserLogger.logger.error(e.getMessage());
			fail("test should not throw file not found exception.");
		}
		((ClassPathXmlApplicationContext) context).close();
	}

	
	@Test
	public void testTdAmeritradeListSize() {
		assertTrue("td data list empty", tdData.size() > 0);
	}
	@Test
	public void footerTest(){
		boolean containsFooter = false;
		for (ParseServiceResponse cur: tdData){
			if (cur.getHistBean() != null && cur.getHistBean().getDate().contains("***END OF FILE***")){
				containsFooter = true;
			}
		}
		assertTrue("td data has footer", ! containsFooter);
	}
	@Test
	public void nullTickerTest(){
		CsvHistBean badBean = null;
		for (ParseServiceResponse cur: tdData){
			if (cur.getHistBean() != null && cur.getHistBean().getTicker() == null){
				badBean = cur.getHistBean();
			}
		}
		assertTrue("null ticker for bean " + badBean, badBean == null);
	}
	@After
	public void tearDown(){
		((ClassPathXmlApplicationContext) context).close();
	}
	private static void printResults(List<ParseServiceResponse> results){
		for (ParseServiceResponse curItem : results) {
			RootserLogger.logger.debug(curItem.getHistBean() == null ? 
						"no item " 
						: curItem.getHistBean().toString()
					);
			RootserLogger.logger.debug(curItem.getErrMsg() == null ? 
						" no error" 
						: curItem.getErrMsg().toString()
					);
		}
	}
}

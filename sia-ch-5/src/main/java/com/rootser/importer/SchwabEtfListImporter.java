package com.rootser.importer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rootser.dao.RootserLogger;


public class SchwabEtfListImporter {
	
public static void main(String[] args){
		if (args.length < 2){
			System.out.println("usage: java [options] SchwabEtfListImporter inputFileName outputFileName.");
		} else {
			String inputFileName = args[0];
			String outputFileName = args[1];
			SchwabEtfListImporter importer = new SchwabEtfListImporter();
			importer.genSql(inputFileName, outputFileName);
		}
	}

	/*the output of this program won't be
	 * 100% correct, it should edited manually after
	 */
	private void genSql(String inputFileName, String outputFileName) {
		try {
			PrintWriter pw = new PrintWriter( new File (outputFileName));
			
			Scanner scanner = new Scanner ( new File (inputFileName));
			
			Pattern tickerPattern = Pattern.compile("[A-Z]{3,4}");
			
			StringBuilder sb = new StringBuilder();
			
			pw.println("insert into stocks (ticker, description, brokerTypeId) values ");
			
			while (scanner.hasNextLine()){
				Matcher tickerMatcher = tickerPattern.matcher(scanner.nextLine());
			
				if (tickerMatcher.find()){
					String ticker = tickerMatcher.group();
					String desc = scanner.nextLine();
					sb.setLength(0);
					sb.append("('")
					.append(ticker)
					.append("', '")
					.append(desc)
					.append("',2),");
					pw.println(sb.toString());
					
				}
			}
			pw.close();
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}

package com.rootser.importer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rootser.dao.RootserLogger;

public class SchwabEtfListImporter {
	
	public static void main(String args[]){
		if (args.length < 1){
			System.out.println("usage: java [options] SchwabEtfListImporter fileName.");
		} else {
			String fileName = args[0];
			SchwabEtfListImporter importer = new SchwabEtfListImporter();
			importer.genSql(fileName);
		}
	}

	private void genSql(String fileName) {
		try {
			Scanner scanner = new Scanner ( new File (fileName));
			Pattern tickerPattern = Pattern.compile("[A-Z]{3,4}");
			
			while (scanner.hasNextLine()){
				Matcher tickerMatcher = tickerPattern.matcher(scanner.nextLine());
				if (tickerMatcher.find()){
					RootserLogger.logger.debug(tickerMatcher.group());
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

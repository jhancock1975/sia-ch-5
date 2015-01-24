package com.rootser.importer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
@Qualifier("tdImporter")
@Service
public class TdImporter extends ImporterAbstract{
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:td-importer.xml");	
		Importer importer = (Importer) context.getBean("tdImporter");
		importer.importCsv();
		((ClassPathXmlApplicationContext) context).close();
	}
}

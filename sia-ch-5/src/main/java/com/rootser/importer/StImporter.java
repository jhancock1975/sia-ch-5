package com.rootser.importer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Qualifier("stImporter")
@Service
public class StImporter extends ImporterAbstract {

		public static void main(String[] args){
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath:st-importer.xml");	
			Importer importer = (Importer) context.getBean("stImporter");
			importer.importCsv();
			((ClassPathXmlApplicationContext) context).close();
		}
}

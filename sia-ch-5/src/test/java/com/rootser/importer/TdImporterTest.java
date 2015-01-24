package com.rootser.importer;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Theories.class)
@ContextConfiguration(locations = { "classpath:importer.xml" })
public class TdImporterTest {
	@Autowired
	@Qualifier("tdImporter")
	private Importer tdImporter;
	@Test
	public void test( ){
		assertTrue("importer not injected", tdImporter != null);
	}
}

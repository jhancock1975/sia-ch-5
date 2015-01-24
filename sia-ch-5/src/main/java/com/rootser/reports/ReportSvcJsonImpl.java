package com.rootser.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ReportSvcJsonImpl")
public class ReportSvcJsonImpl implements ReportSvc {
	@Autowired
	@Qualifier("JsonReport")
	Report report;

	public Report getReport() {
		return report;
	}

}

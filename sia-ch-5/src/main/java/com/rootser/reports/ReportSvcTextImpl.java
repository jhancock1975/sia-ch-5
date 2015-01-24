package com.rootser.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ReportSvcTextImpl")
public class ReportSvcTextImpl implements ReportSvc {
	@Autowired
	@Qualifier("TextReport")
	Report report;

	public Report getReport() {
		return report;
	}

}

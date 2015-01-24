package com.rootser.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rootser.reports.Report;
import com.rootser.reports.ReportSvc;

@Controller
public class ReportController {
	@Autowired
	@Qualifier("ReportSvcJsonImpl")
	ReportSvc reportSvc;
	//the request mapping applies to pages that are like Test.htm,
	//so I don't know why I don't have to put that, except that
	//maybe the DispatcherServlet strips off the .htm
	@RequestMapping(value={"/Report"}, method=RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Report report = reportSvc.getReport();
		report.getReport();
        ModelAndView mAndV = new ModelAndView("Report.jsp");
        mAndV.addObject(report);
        return mAndV;
    }
}

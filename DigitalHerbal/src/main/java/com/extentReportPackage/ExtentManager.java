package com.extentReportPackage;

import java.util.HashMap;
import java.util.Map;

import com.browserFactory.BrowserFactory;
import com.generic.Actions.ConstantValueProj;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	static ExtentReports extent;
	static Map extentReportMap=new HashMap();//report name,Extent Report object
	static Map extentReportMap_name=new HashMap();
	public synchronized static ExtentReports getReporter() {
		
		if(extentReportMap.get(BrowserFactory.reportNameMap.get((int)(long)(Thread.currentThread().getId())))==null)
		{ 
			System.out.println("Created report for "+BrowserFactory.reportNameMap.get((int)(long) (Thread.currentThread().getId())));
			extent=new ExtentReports(ConstantValueProj.reportpath+BrowserFactory.reportNameMap.get((int)(long)(Thread.currentThread().getId())), false, DisplayOrder.NEWEST_FIRST);
			extentReportMap.put(BrowserFactory.reportNameMap.get((int)(long)(Thread.currentThread().getId())), extent);
			
		}
		return (ExtentReports)extentReportMap.get(BrowserFactory.reportNameMap.get((int)(long)(Thread.currentThread().getId())));
		
	}

}

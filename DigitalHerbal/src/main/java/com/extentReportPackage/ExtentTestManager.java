package com.extentReportPackage;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentTest;

public class ExtentTestManager {
	static Map extentTestMap=new HashMap();
	
	public static synchronized ExtentTest getTest() {
		return (ExtentTest)extentTestMap.get((int)(long)(Thread.currentThread().getId()));
	}
	public static synchronized void endTest() {
		ExtentManager.getReporter().endTest((ExtentTest)extentTestMap.get((int)(long)(Thread.currentThread().getId())));
	}
	public static synchronized ExtentTest startTest(String testName) {
		return startTest(testName,"");
	}
	public static synchronized ExtentTest startTest(String testName,String desc) {
		ExtentTest test=ExtentManager.getReporter().startTest(testName, desc);
		extentTestMap.put((int)(long)(Thread.currentThread().getId()), test);
		return (ExtentTest)extentTestMap.get((int)(long)(Thread.currentThread().getId()));
	}

}

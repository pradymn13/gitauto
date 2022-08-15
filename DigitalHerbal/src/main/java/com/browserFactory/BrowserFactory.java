package com.browserFactory;

import java.lang.reflect.Method;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import com.extentReportPackage.ExtentManager;
import com.extentReportPackage.ExtentTestManager;
import com.extentReportPackage.Report;
import com.generic.Actions.ConstantValueProj;
import com.generic.Actions.Excel;
import com.generic.Actions.ExecutionDetails;
import com.generic.Actions.GenericFunction;

public class BrowserFactory {
	public WebDriver driver=null;
	public WebDriverWait wait;
	public Excel ex = new Excel();
	public static ArrayList<String> allexecutionSheetName;
	public static Map reportNameMap=new HashMap();
	public static String SuiteID=null,tcID=null;
	public static String executionsheetName;
	public static int rowNumber;
	public static String reportName="";
	public static String startTime="";
	public static String endTime="";
	
	public synchronized static String remporInit(final ITestContext testContext) {
		System.out.println(testContext.getCurrentXmlTest().getSuite().getName());
		
		if((String)reportNameMap.get((int)(long)(Thread.currentThread().getId()))==null
				|| (!(boolean) reportNameMap.get((int)(long)(Thread.currentThread().getId()))
						.equals(testContext.getCurrentXmlTest().getSuite().getName()+".html"))) {
			reportNameMap.put((int)(long)(Thread.currentThread().getId()), reportName);
			
		}
		return (String)reportNameMap.get((int)(long)(Thread.currentThread().getId()));
	}
	@BeforeTest
	public void beforeTest(final ITestContext testContext) throws Exception{
		
		System.out.println(testContext.getName());
		System.out.println(testContext.getStartDate());
		Date date = new Date(0);		
		startTime = date.toString();
		reportName="HumaraApnaPoject_Execution_Report_as_on_" + startTime.replace(":", "_").replace(" ", "_")+".html";
	}
	@BeforeMethod
    public void beforeMethod(Method method, final ITestContext testContext) throws Exception
    {
        driver = (new MultipleBroswers()).getBroswerDriver(ConstantValueProj.browser,"");
        remporInit(testContext);
        ExtentTestManager.startTest(method.getName()).assignCategory(GenericFunction.GetdateNow("dd-MMM-yyyy"))
        .assignCategory(testContext.getName())
        .assignCategory(ExecutionDetails.getInstances())
        .assignCategory(GenericFunction.getSysUserName());
        System.out.println(method.getName());
        System.out.println(ExtentTestManager.getTest());
        
        wait = new WebDriverWait(driver,60);
        driver.manage().window().maximize();
    }
	@AfterMethod
    public void AfterMethod(Method methods, final ITestContext testContext, ITestResult result) throws Exception
    {
    if(result.getStatus()==ITestResult.FAILURE)    
    {
        Report report = new Report(driver);
        report.fail("Exception Occured During the Excecution Please check the details","Please Re-Execute Again by checking -->" + result.getThrowable().getLocalizedMessage(),true);
        
    }
    int rowNum;
    String sheetName="";
    
   
    System.err.println("xlpath = " + GenericFunction.getProperty("xlPath"));
    System.err.println("testContext.getName() = " + testContext.getName());
    System.err.println("methods.getName() = " + methods.getName());
    System.err.println("executionSheetName = " + executionsheetName);
    
    if(ConstantValueProj.isDataProvider.equalsIgnoreCase("Yes"))
    {
        sheetName = executionsheetName;
        rowNum = rowNumber;
        
   }
    else
    {
    sheetName = testContext.getName();
    rowNum=ex.xlRead_GetRowCount(GenericFunction.getProperty("xlPath"),sheetName,"TestCaseName",methods.getName());
    }
    System.err.println("After Method Started....");
    System.err.println("xlpath = " + GenericFunction.getProperty("xlPath"));
    System.err.println("testContext.getName() = " + testContext.getName());
    System.err.println("execustionSheetName = " + executionsheetName);
    
    ex.xlWriteData(GenericFunction.getProperty("xlPath"),sheetName,"ExecutionStatus", rowNum, ExtentTestManager.getTest().getRunStatus().toString());
    
    ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
    ExtentManager.getReporter().flush();
    
    String status = ExtentTestManager.getTest().getRunStatus().toString();
    switch (status.toLowerCase())
    {
    case "pass" :
        result.setStatus(ITestResult.SUCCESS);
        break;
    case "fail" :
        result.setStatus(ITestResult.FAILURE);
        break;
    default:
        break;
    }
    driver.close();
   // driver.quit();
    
     }
	
	
	@AfterTest
	public void afterTest(final ITestContext testContext) {
		System.out.println(testContext.getName());
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		System.out.println(LocalDateTime.now().format(dateTimeFormatter).toString());
		Date date = new Date(0);
		endTime = date.toString();
	}
	
	
	

}

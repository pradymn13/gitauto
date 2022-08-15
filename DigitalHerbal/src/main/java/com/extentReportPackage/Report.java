package com.extentReportPackage;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.generic.Actions.GenericFunction;
import com.relevantcodes.extentreports.LogStatus;

public class Report {
	public WebDriver driver;
	
	public Report(WebDriver driver){
		this.driver=driver;
	}
	public void conditionUpdate(boolean condition,String stepName,String passMsg,String failMsg,boolean screenShotCapture) {
		if(condition) {
			pass(stepName,passMsg,screenShotCapture);
		}
		else {
			fail(stepName,failMsg,screenShotCapture);
		}
	}
	public void captureImageWithname(String screenName) {
		File toLocation=null;
		try {
			File scrfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			toLocation=new File("extent-reports/images/"+screenName+".png");
			FileUtils.copyFile(scrfile, toLocation);
		}catch(Exception e) {
			
		}
	}
	public void pass(String stepName,String details,boolean screenShotCapture) {
		if(screenShotCapture) {
			GenericFunction gf = new GenericFunction(driver);
			String img=gf.TakeScreenShot();
			String logimg=ExtentTestManager.getTest().addScreenCapture(img);
			
			ExtentTestManager.getTest().log(LogStatus.PASS,stepName, details+"\n"+logimg);
			gf=null;
			
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS,stepName, details);
		}
	}
	public void pass(String stepName,String details,boolean screenShotCapture,WebElement element) {
		GenericFunction gf = new GenericFunction(driver);
		gf.highlightElement(element);
		if(screenShotCapture) {
			
			String img=gf.TakeScreenShot();
			String logimg=ExtentTestManager.getTest().addScreenCapture(img);
			
			ExtentTestManager.getTest().log(LogStatus.PASS,stepName, details+"\n"+logimg);			
			
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.PASS,stepName, details);
		}
		gf.removeHighlightFromElement(element);
		gf=null;
	}
	public void fail(String stepName,String details,boolean screenShotCapture) {
		if(screenShotCapture) {
			GenericFunction gf = new GenericFunction(driver);
			String img=gf.TakeScreenShot();
			String logimg=ExtentTestManager.getTest().addScreenCapture(img);
			
			ExtentTestManager.getTest().log(LogStatus.FAIL,stepName, details+"\n"+logimg);
			gf=null;
			
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.FAIL,stepName, details);
		}
	}
	public void info(String stepName,String details,boolean screenShotCapture) {
		if(screenShotCapture) {
			GenericFunction gf = new GenericFunction(driver);
			String img=gf.TakeScreenShot();
			String logimg=ExtentTestManager.getTest().addScreenCapture(img);
			
			ExtentTestManager.getTest().log(LogStatus.INFO,stepName, details+"\n"+logimg);
			gf=null;
			
		}
		else {
			ExtentTestManager.getTest().log(LogStatus.INFO,stepName, details);
		}
	}
	public void scrollAndTakeScreenshot(String stepName,String details,WebElement element) {
		
			GenericFunction gf = new GenericFunction(driver);
			gf.scroolPageToExpectedid(element);			
			String img=gf.TakeScreenShot();
			String logimg=ExtentTestManager.getTest().addScreenCapture(img);
			
			ExtentTestManager.getTest().log(LogStatus.INFO,stepName, details+"\n"+logimg);
			gf=null;
			
		}
		
	}
	



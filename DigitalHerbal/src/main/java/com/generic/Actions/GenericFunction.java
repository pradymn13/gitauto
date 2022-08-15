package com.generic.Actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class GenericFunction {
	WebDriver driver;

	public GenericFunction(WebDriver driver) {
		this.driver = driver;
	}

	public static String getProperty(String property) {
		Properties pro = new Properties();
		try {
//	FileInputStream fis = new FileInputStream("Propertyfile.properties");
//	pro.load(fis);
			pro.load(new FileInputStream("Propertyfile.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pro.getProperty(property);
	}

	public String TakeScreenShot() {
		File toLocation = null;
		try {
			File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			toLocation = new File(ConstantValueProj.imagespath + scrfile.getName());
			FileUtils.copyFile(scrfile, toLocation);
		} catch (Exception e) {

		}
		return ConstantValueProj.imagespath.replace(ConstantValueProj.reportpath, "") + toLocation.getName();
	}

	public void scroolPageToExpectedid(WebElement element) {

		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500);
		} catch (Exception e) {
			System.out.println("Exception in scroolPageToExpectedid(),in AppLevelFunctions.java :: " + e.getMessage());
		}
	}

	public void highlightElement(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String javascript = "arguments[0].setAttribute('style',arguments[1]);";
		js.executeScript(javascript, element, "background-color: yellow; color: black; border: 2px solid red;");
	}

	public void removeHighlightFromElement(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String javascript = "arguments[0].setAttribute('style',arguments[1]);";
		js.executeScript(javascript, element, "");
	}
	public void mouseHover(WebElement element) {
		WebElement locator=element;
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	public String getText(WebElement element) {
		WebElement locator = element;
		return locator.getText();
	}
	public void waitforPageLoaded(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		}catch(InterruptedException e) {;
		System.out.println(e.getMessage());
	}
		ExpectedCondition<Boolean> expectation=new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return((JavascriptExecutor) driver ).executeScript(
						"return.document.readyState").equals("complete");
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		try {
			wait.until(expectation);
		}catch(Throwable error) {
			
		}
		
}
	public void waitforElementtobeClickable(WebElement element,int seconds) {
		WebElement locator=element;
		Wait<WebDriver> wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public boolean waitUntilVisible(WebElement element) {
		WebElement obj=null;
		try {
			WebDriverWait wait = new WebDriverWait(driver,120);
			wait.ignoring(ElementNotVisibleException.class);
			obj=wait.until(ExpectedConditions.visibilityOf(element));
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(obj == null)
			return false;
		else
			return true;
	}

	public static String getSysUserName() {
		
		return System.getProperty("user.name");
	}

	public static String GetdateNow(String dformat) {
		Calendar currentdate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(dformat);
		String dateNow = formatter.format(currentdate.getTime());
		return dateNow;
	}
}

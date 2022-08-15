package com.hlComSites.pages.unitedStates;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.extentReportPackage.Report;
import com.generic.Actions.ExecutionDetails;
import com.generic.Actions.GenericFunction;

public class HomePage {
	WebDriver driver;
	GenericFunction genericAction;
	Report report;
	boolean takeScreenshot = true;
	
	@FindBy(xpath="//*[@title=\"Home\"] ")
	private WebElement home;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		genericAction = new GenericFunction(driver);
		report = new Report(driver);
		PageFactory.initElements(driver, this);
	}

	public void urlLaunch(String className) {
		try {
			driver.get(ExecutionDetails.getUrl(className));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void verifyHomePageDisplayedUS() {
		try {
			genericAction.waitforElementtobeClickable(home, 10);
			genericAction.waitUntilVisible(home);
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.herbalife.com/","home page not displayed");
			report.pass("Verify Display of Home Page", "Home Page is displayed", true, home);
		}catch(Exception e) {
			e.printStackTrace();
			report.fail("Verify Display of Home Page", "Home Page is not displayed", true);
		}
	}

}

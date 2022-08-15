package com.test.hlComSites;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.browserFactory.BrowserFactory;
import com.generic.Actions.GenericFunction;
import com.hlComSites.pages.unitedStates.*;

public class UnitedStatesTest extends BrowserFactory{
	HomePage homepage;
	GenericFunction genericFunction;
	
	@BeforeMethod
	
	public void Intiatetest() {
		homepage = new HomePage(driver);
		genericFunction = new GenericFunction(driver);
		try {
			BrowserFactory.executionsheetName=this.getClass().getSimpleName();
			homepage.urlLaunch(this.getClass().getSimpleName());
			homepage.verifyHomePageDisplayedUS();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void VerifyHomePageofUSCountry() {
		homepage.verifyHomePageDisplayedUS();
	}
	

}

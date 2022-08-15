package com.browserFactory;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.generic.Actions.ConstantValueProj;

public class MultipleBroswers {
	
	public synchronized WebDriver getBroswerDriver(String browser,String testname) {
		DesiredCapabilities capability = null;
	    WebDriver driver = null;
	    try {
	    	if(ConstantValueProj.isGridrequired.equalsIgnoreCase("No")) 
	    		{
	    			switch(browser.toLowerCase()) {
	    			case "firefox":
	    				driver=new FirefoxDriver();
	    				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    				break;
	    			case "ie10":
	    				System.setProperty("webdriver.ie.driver","lib/IEDriverServer.exe");
	    				driver=new InternetExplorerDriver();
	    				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    				break;
	    			case "ie11":
	    				System.setProperty("webdriver.ie.driver","lib/IEDriverServer.exe");
	    				
	    				ChromeOptions options =new ChromeOptions();
	    				options.addArguments("chrome-switches","--disable--extensions");
	    				DesiredCapabilities d = DesiredCapabilities.internetExplorer();
	    				d.setCapability("nativrEvents", false);
	    				driver=new InternetExplorerDriver(d);
	    				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    				break;
	    			case "chrome":
	    				System.setProperty("webdriver.chrome.driver","lib/chromedriver.exe");
	    				
	    				ChromeOptions options1 =new ChromeOptions();
	    				options1.addArguments("--test-type");
	    				options1.addArguments("chrome.switches","--disable--extensions");
	    				options1.addArguments("--lang= locale-of-choice");   			
	    				
	    				driver=new ChromeDriver(options1);
	    				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    				break;
	    			case "edge":
	    				System.setProperty("webdriver.edge.driver","lib/msedgedriver.exe");
	    				driver=new EdgeDriver();
	    				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    				break;
	    			default:
	    				driver = null;
	    			}
	    		}
	    	
	    		else if(ConstantValueProj.isGridrequired.equalsIgnoreCase("Yes")) {
	    			
	    			switch(browser.toLowerCase()) {
	    			case "firefox":
	    				capability=new DesiredCapabilities().firefox();
	    				capability.setCapability("platform", "windows 7");
	    				capability.setCapability("version", "35.0");
	    				capability.setCapability("screen-resolution", "1280*800");
	    				capability.setCapability("name", testname+"_firefox");
	    				driver = new RemoteWebDriver(new URL(ConstantValueProj.hube_url),capability);
	    				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    				break;
	    			case "ie10":
	    				capability=new DesiredCapabilities().internetExplorer();
	    				capability.setCapability("platform", "windows 7");
	    				capability.setCapability("version", "35.0");
	    				capability.setCapability("screen-resolution", "1280*800");
	    				capability.setCapability("name", testname+"_ie10");
	    				driver = new RemoteWebDriver(new URL(ConstantValueProj.hube_url),capability);
	    				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    				break;
	    			case "ie11":
	    				capability=new DesiredCapabilities().internetExplorer();
	    				capability.setCapability("platform", "windows 7");
	    				capability.setCapability("version", "35.0");
	    				capability.setCapability("screen-resolution", "1280*800");
	    				capability.setCapability("name", testname+"_ie11");
	    				driver = new RemoteWebDriver(new URL(ConstantValueProj.hube_url),capability);
	    				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    				break;
	    			case "chrome":
	    				capability=new DesiredCapabilities().chrome();
	    				ChromeOptions options =new ChromeOptions();
	    				options.addArguments("no-sandbox");
	    				capability.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
	    				capability.setPlatform(Platform.ANY);
	    				driver = new RemoteWebDriver(new URL(ConstantValueProj.hube_url),capability);
	    				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    				break;
	    			case "edge":
	    				System.setProperty("webdriver.edge.driver","lib/msedgedriver.exe");
	    				capability=new DesiredCapabilities().edge();	    			
	    				capability.setBrowserName(DesiredCapabilities.edge().getBrowserName());
	    				capability.setCapability("plartform",Platform.WINDOWS);
	    				driver = new RemoteWebDriver(new URL(ConstantValueProj.hube_url),capability);
	    				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    				break;
	    			default:
	    				driver=null;
	    				
	    				
	    		}
	    	}
	    }catch(Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return driver;
		


}
}

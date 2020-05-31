package com.testautomation.StepDef;

import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.testautomation.Utility.BrowserUtility;
import com.testautomation.Utility.PropertiesFileReader;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	PropertiesFileReader obj = new PropertiesFileReader();
	public static WebDriver driver;


	  @Before
	  public void openBrowser() throws MalformedURLException, Throwable {
		  Properties properties = obj.getProperty();
		  driver = BrowserUtility.OpenBrowser(properties.getProperty("browser.name"),
					properties.getProperty("browser.baseURL"));
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
	  }

	  @After
	  public void embedScreenshot(Scenario scenario) {

	    if(scenario.isFailed()) {
	      try {
	        scenario.write("Current Page URL is " + driver.getCurrentUrl());
	        byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	        scenario.embed(screenshot, "image/png");
	      } catch (WebDriverException somePlatformsDontSupportScreenshots) {
	        System.err.println(somePlatformsDontSupportScreenshots.getMessage());
	      }

	    }
	    driver.quit();
	  }

}

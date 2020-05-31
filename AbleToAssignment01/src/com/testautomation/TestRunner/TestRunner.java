package com.testautomation.TestRunner;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testautomation.Utility.BrowserUtility;
import com.testautomation.Utility.PropertiesFileReader;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.api.testng.*;



@CucumberOptions (

        features = "./features/"

        ,glue = {"com.testautomation.StepDef"}

        ,tags = {"@EhabTesting"}

        ,monochrome = true)

public class TestRunner {
	
	//public static WebDriver driver;

    private TestNGCucumberRunner testNGCucumberRunner;

    

    @BeforeClass(alwaysRun = true)

    public void setUpClass() throws Exception {    	

        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

    }



    @Test(dataProvider = "features")    

    public void feature(PickleEventWrapper eventwrapper,CucumberFeatureWrapper cucumberFeature) throws Throwable {

    	//testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());

    	testNGCucumberRunner.runScenario(eventwrapper.getPickleEvent());

    }

    

    @DataProvider//(parallel=true)

    public Object[][] features() {   	

    	 return testNGCucumberRunner.provideScenarios();

    }

    @AfterClass(alwaysRun = true)

    public void tearDownClass() throws Exception {    	

        testNGCucumberRunner.finish();        

    }
    

}

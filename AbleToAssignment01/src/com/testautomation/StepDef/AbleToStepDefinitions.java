package com.testautomation.StepDef;

import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import com.testautomation.PageObjects.AddEmployeePage;
import com.testautomation.PageObjects.AdminDashBoardPage;
import com.testautomation.PageObjects.LoginPage;
import com.testautomation.TestRunner.TestRunner;
import com.testautomation.Utility.BrowserUtility;
import com.testautomation.Utility.PropertiesFileReader;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class AbleToStepDefinitions {
	private WebDriver driver = Hooks.driver;

	static final String ADMIN_USERNAME = "admin123";
	static final String ADMIN_PASSWORD = "foobar123";

	@Given("^an Admin$")
	public void loginAsAdmin() throws Throwable {
		new LoginPage(driver).loginAsAdmin(ADMIN_USERNAME, ADMIN_PASSWORD);
	}

	@And("^I am on the Admin Dashboard page$")
	public void inAdminDashBoardPage() throws Throwable {
		AdminDashBoardPage adminPage = new AdminDashBoardPage(driver);
		String actualHeaderText = adminPage.getActualHeaderText();
		String expectedHeaderText = adminPage.getExpectedHeaderText();
		Assert.assertEquals(expectedHeaderText, actualHeaderText);

	}

	@And("^I select Add New Employee$")
	public void clickAddNewEmployee() throws Throwable {
		new AdminDashBoardPage(driver).clickAddNewEmployee();
	}

	@When("I enter {string}, {string}, and {string} info")
	public void fillEmployeeInfo(String firstName, String lastName, String program) throws Throwable {
		new AddEmployeePage(driver).fillEmployeeInfo(firstName, lastName, program);
	}
	
	@When("I submit")
	public void i_click_submit_button() throws Throwable {
		new AddEmployeePage(driver).clickSubmit();
	}

	@Then("Employee {string}, {string}, {string}, {string}, {string} info should display correctly")
	public void employeeInfoShouldDisplayCorrectly(String firstName, String lastName, String program,
			String programBonus, String totalBiweeklyPay) throws Throwable {

		AdminDashBoardPage adminPage = new AdminDashBoardPage(driver);

		HashMap<String, String> actualInfo = adminPage.getRecordInfoContaining(lastName, firstName);
		System.out.println("Actual: "+ actualInfo);
		
		HashMap<String, String> expectedInfo = adminPage.getExpectedVAlues(firstName, lastName, Integer.parseInt(program));
		System.out.println("Expected: "+ expectedInfo);
		//Assert.assertEquals("Last Name is not correct", lastName, actualInfo.get("Last Name"));
		//Assert.assertEquals("First Name is not correct", firstName, actualInfo.get("First Name"));
		
		Assert.assertEquals("Base Annual Salary is not correct", expectedInfo.get("Base Annual Salary"),
				actualInfo.get("Base Annual Salary"));
		Assert.assertEquals("Program is not correct", program, actualInfo.get("Program"));
		Assert.assertEquals("Base Biweekly Pay is not correct", expectedInfo.get("Base Biweekly Pay"),
				actualInfo.get("Base Biweekly Pay"));
		Assert.assertEquals("Yearly Program Bonus is not correct", programBonus,
				actualInfo.get("Yearly Program Bonus"));
		Assert.assertEquals("Total Biweekly Pay is not correct", totalBiweeklyPay,
				actualInfo.get("Total Biweekly Pay"));
	}

}

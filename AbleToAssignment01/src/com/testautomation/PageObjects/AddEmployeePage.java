package com.testautomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddEmployeePage extends BasePage{

	public AddEmployeePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//*[@id=\"employees-form\"]/div[1]/div/input")
	private WebElement firstNameTextField;
	
	@FindBy(xpath="//*[@id=\"employees-form\"]/div[2]/div/input")
	private WebElement lastNameTextField;
	
	@FindBy(xpath="//*[@id=\"employees-form\"]/div[3]/div/input")
	private WebElement programTextField;
	
	@FindBy(xpath="//*[@id=\"employees-form\"]/div[4]/div/button[1]")
	private WebElement submitButton;
	
	public void fillEmployeeInfo(String firstName, String lastName, String program) {
		firstNameTextField.sendKeys(firstName);
		lastNameTextField.sendKeys(lastName);
		programTextField.sendKeys(program);
	}
	
	public void clickSubmit() throws InterruptedException {
		submitButton.click();
	}
	
	
	

}

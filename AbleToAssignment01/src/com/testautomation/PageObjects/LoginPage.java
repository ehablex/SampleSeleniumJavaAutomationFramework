package com.testautomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name="form-username")
	private WebElement userNameTextBox;
	
	@FindBy(name="form-password")
	private WebElement passWordTextField;
	
	@FindBy(id="btnLogin")
	private WebElement loginButton;
	
	public AdminDashBoardPage loginAsAdmin(String userName, String passWord) {
		userNameTextBox.sendKeys(userName);
		passWordTextField.sendKeys(passWord);
		loginButton.click();
		return new AdminDashBoardPage(driver);
	}

}
package com.testautomation.PageObjects;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.testautomation.Objects.Employee;

public class AdminDashBoardPage extends BasePage {
	static final String EMPLOYEE_TABLE_CELL_XPATH = "//*[@id=\"employee-table\"]/tbody/tr[%s]/td[%s]"; 
	static final String EXPECTED_HEADER_TEXT = "Admin Dashboard";

	public AdminDashBoardPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//*[@id=\"header\"]/h1")
	private WebElement headerText;
	
	@FindBy(id="btnAddEmployee")
	private WebElement addNewEmployeeButton;
	
	public void clickAddNewEmployee() {
		addNewEmployeeButton.click();
	}
	
	public String getActualHeaderText() {
		return headerText.getText();
	}
	public String getExpectedHeaderText() {
		return EXPECTED_HEADER_TEXT;
	}
	
	private String getTextFromCell(int row, int column) {
		String genericXpath = "//*[@id=\"employee-table\"]/tbody/tr[%s]/td[%s]";
		String actualXpath = String.format(genericXpath, row, column);
		
		return driver.findElement(By.xpath(actualXpath)).getText();
	}
	
	
	/**
	 * @Method getIndexOfRecordContaining
	 * @param firstName
	 * @param lastName
	 * @return the index of record containing first and last name (1 based) or -1 otherwise
	 */
	private int getIndexOfRecordContaining(String firstName, String lastName) {
		List<WebElement> rows = (List<WebElement>) driver.findElements(By.xpath("//*[@id=\"employee-table\"]/tbody/tr"));
		int numberOfRows = rows.size();
		for(int i=1; i<=numberOfRows; i++) {
			if(getTextFromCell(i, 2).equalsIgnoreCase(lastName) &&
					getTextFromCell(i, 3).equalsIgnoreCase(firstName)) {
				return i;
			}
		}
		return -1; //return -1 if no match
	}
	
	
	/**
	 * @Method getRecordInfoContaining, 
	 * @param firstName
	 * @param lastName
	 * @return a map with info in the record cells
	 */
	public HashMap<String, String> getRecordInfoContaining(String firstName, String lastName) {
		int rowIndex = getIndexOfRecordContaining(firstName, lastName); // 1 based
		if(rowIndex<=0) {
			return null;
		}
		
		// -- getting text of various fields for the record using customized xpath
		String lName = driver.findElement(By.xpath(String.format(EMPLOYEE_TABLE_CELL_XPATH, rowIndex, 2))).getText(); 
		String fName = driver.findElement(By.xpath(String.format(EMPLOYEE_TABLE_CELL_XPATH, rowIndex, 3))).getText();
		String baseAnnualSalary = driver.findElement(By.xpath(String.format(EMPLOYEE_TABLE_CELL_XPATH, rowIndex, 4))).getText();
		String program = driver.findElement(By.xpath(String.format(EMPLOYEE_TABLE_CELL_XPATH, rowIndex, 5))).getText();
		String baseBiweeklyPay = driver.findElement(By.xpath(String.format(EMPLOYEE_TABLE_CELL_XPATH, rowIndex, 6))).getText();
		String yearlyProgramBonus = driver.findElement(By.xpath(String.format(EMPLOYEE_TABLE_CELL_XPATH, rowIndex, 7))).getText();
		String totalBiweeklyPay = driver.findElement(By.xpath(String.format(EMPLOYEE_TABLE_CELL_XPATH, rowIndex, 8))).getText();
		
		HashMap<String, String> resultMap = new HashMap<>();
		resultMap.put("Last Name", lName);
		resultMap.put("First Name", fName);
		resultMap.put("Base Annual Salary", baseAnnualSalary);
		resultMap.put("Program", program);
		resultMap.put("Base Biweekly Pay", baseBiweeklyPay);
		resultMap.put("Yearly Program Bonus", yearlyProgramBonus);
		resultMap.put("Total Biweekly Pay", totalBiweeklyPay);
		
		return resultMap;
	}
	
	
	/**
	 * @Method getExpectedVAlues 
	 * @param firstName
	 * @param program
	 * @return returns a hash map that contains Program bonus and adjusted pay amount
	 */
	public HashMap<String, String> getExpectedVAlues(String firstName, String lastName, int program) {
		Employee emp = new Employee(firstName, lastName, program);
		
		// -- format into correct strings and add to map
		final DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.UP);
		
		HashMap<String, String> resultMap = new HashMap<>();
		resultMap.put("Program Bonus", df.format(emp.getAnnualProgramBonus()));
		resultMap.put("Total Biweekly Pay", df.format(emp.getTotalBiweeklyPay()));
		resultMap.put("Base Annual Salary", df.format(emp.getBaseAnnualSalary()));
		resultMap.put("Base Biweekly Pay", df.format(Employee.BASE_BIWEEKLY_PAY));
		
		return resultMap;
	}

}

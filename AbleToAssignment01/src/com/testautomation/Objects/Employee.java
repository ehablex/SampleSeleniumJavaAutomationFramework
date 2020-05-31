package com.testautomation.Objects;

public class Employee {
	
	public static final int NUMBER_OF_PAY_PERIODS_PER_YEAR;
	public static final double BASE_BIWEEKLY_PAY;
	public static final double ANNUAL_BONUS_ONE;
	private String firstName;
	private String lastName;
	private int program;
	
	static {
		NUMBER_OF_PAY_PERIODS_PER_YEAR = 26;
		BASE_BIWEEKLY_PAY = 2000.00;
		ANNUAL_BONUS_ONE = 5000.00;
	}
	
	public Employee(String firstName, String lastName, int program) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.program = program;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return firstName;
	}

	public int getProgram() {
		return program;
	}
	
	public double getAnnualProgramBonus() {
		if(firstName.charAt(0)=='a' || firstName.charAt(0)=='A')
			return ANNUAL_BONUS_ONE * program * 0.9; // 10% reduction
		return ANNUAL_BONUS_ONE * program;
	}
	
	public double getBaseAnnualSalary() {
		return BASE_BIWEEKLY_PAY * NUMBER_OF_PAY_PERIODS_PER_YEAR;
	}
	
	public double getTotalBiweeklyPay() {
		double biweeklyBonus = getAnnualProgramBonus()/NUMBER_OF_PAY_PERIODS_PER_YEAR;
		return biweeklyBonus + BASE_BIWEEKLY_PAY;
	}

}

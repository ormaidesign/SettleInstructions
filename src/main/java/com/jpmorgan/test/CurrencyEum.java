package com.jpmorgan.test;

public enum CurrencyEum {
	USD("MONDAY", "TUESDAY", "THURSDAY", "WEDNESDAY", "FRIDAY"),
	SGP("MONDAY", "TUESDAY", "THURSDAY", "WEDNESDAY", "FRIDAY"),
	AED("SUNDAY", "MONDAY", "TUESDAY", "THURSDAY"),
	SAR("SUNDAY", "MONDAY", "TUESDAY", "THURSDAY")
	;
	
	private String[] workdays;
	
	CurrencyEum(String... workdays) {
		this.workdays = workdays;
	}

	public String[] getWorkdays() {
		return workdays;
	}

	public void setWorkdays(String[] workdays) {
		this.workdays = workdays;
	}
	
}

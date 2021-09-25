package com.psl.gems.model;

public enum IssueStatus {
	RESERVATION("Reserved"),
	CANCELED("Canceled"),
	ISSUED("Issued"),
	RETURNED("Returned");
	
	private final String displayValue;
	
	private IssueStatus(String displayValue) {
		this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
}

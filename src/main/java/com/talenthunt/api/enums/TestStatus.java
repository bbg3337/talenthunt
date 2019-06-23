package com.talenthunt.api.enums;

public enum TestStatus {
	Completed("Completed"), InComplete("InComplete");
	private String value;
	TestStatus(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

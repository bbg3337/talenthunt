package com.talenthunt.api.enums;

public enum TestType {
	Preparation("Preparation"), Mock_Test("Mock_Test"), Exam_Simulation("Mock_Test");
	String value;

	TestType (String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}

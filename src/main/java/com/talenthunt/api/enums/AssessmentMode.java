package com.talenthunt.api.enums;

public enum AssessmentMode {
	RegularMode("Regular Mode"), 
	ExamMode("Exam Mode");
	private String code;

	AssessmentMode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}

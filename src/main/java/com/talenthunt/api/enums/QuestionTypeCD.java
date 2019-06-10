package com.talenthunt.api.enums;

public enum QuestionTypeCD {
	MULTI_SELECT("Multi-Select"),
	SINGLE_SELECT("Single-Select"),
	TRUE_FALSE("True-False"),
	SHORT_ANSWER("Short-Answer"),
	LONG_ANSWER("Long-Answer");
	String value;
	QuestionTypeCD(String value){
		this.value= value;
	}
}

package com.talenthunt.api.enums;

public enum QuestionTypeCD {
	Multi_Select("Multi_Select"),
	Single_Select("Single_Select"),
	True_False("True_False"),
	Short_Answer("Short_Answer"),
	Long_Answer("Long_Answer");
	String value;
	QuestionTypeCD(String value){
		this.value= value;
	}
}

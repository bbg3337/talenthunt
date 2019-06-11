package com.talenthunt.api.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AssessmentMode {
    @JsonProperty("Regular_Mode")
	Regular_Mode("Regular_Mode"), 
	@JsonProperty("Exam_Mode")
	Exam_Mode("Exam_Mode");
	private String code;

	AssessmentMode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}

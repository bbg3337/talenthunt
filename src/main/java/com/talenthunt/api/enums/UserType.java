package com.talenthunt.api.enums;

public enum UserType {
	Book_User("Book_User"),
	SUBSCRIPTION("Subscription"),
	Guest_User("Guest_User"),
	ADMIN("Admin"),
	Exam_Purchase("Exam_Purchase");
	
	String value;

	UserType(String value) {
		this.value = value;
	}
}

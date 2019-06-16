package com.talenthunt.api.enums;

public enum UserType {
	Book_User("Book_User"),
	Subscription("Subscription"),
	Guest_User("Guest_User"),
	Admin("Admin"),
	Exam_Purchase("Exam_Purchase");
	
	String value;

	UserType(String value) {
		this.value = value;
	}
}

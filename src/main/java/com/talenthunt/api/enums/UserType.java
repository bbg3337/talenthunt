package com.talenthunt.api.enums;

public enum UserType {
	BOOK_USER("Book User"),
	SUBSCRIPTION("Subscription"),
	GUEST_USER("Guest User"),
	ADMIN("Admin"),
	EXAM_PURCHASE("Exam Purchase");
	
	String value;

	UserType(String value) {
		this.value = value;
	}
}

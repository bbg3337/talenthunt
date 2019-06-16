package com.talenthunt.api.enums;

public enum UserStatus {
	active("active"), inactive("inactive"), renewal_due("renewal_due"), quit("quit");
	String value;

	UserStatus (String value){
		this.value = value;
	}
}

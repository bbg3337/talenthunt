package com.talenthunt.api.enums;

public enum UserStatus {
	ACTIVE("active"), INACTIVE("inactive"), RENEWAL_DUE("renewal_due"), QUIT("quit");
	String value;

	UserStatus (String value){
		this.value = value;
	}
}

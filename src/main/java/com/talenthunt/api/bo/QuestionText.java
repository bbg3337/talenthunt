package com.talenthunt.api.bo;

import java.util.ArrayList;
import java.util.Set;

public class QuestionText {
	private String que;
	ArrayList<String> opt;
	Set<String> ans;
	
	public String getQue() {
		return que;
	}
	public void setQue(String que) {
		this.que = que;
	}
	public ArrayList<String> getOpt() {
		return opt;
	}
	public void setOpt(ArrayList<String> opt) {
		this.opt = opt;
	}
	public Set<String> getAns() {
		return ans;
	}
	public void setAns(Set<String> ans) {
		this.ans = ans;
	}
	
}
package com.talenthunt.api.model;

import java.util.ArrayList;

public class QuestionFormat {
	private String que;
	private ArrayList<String> opt = new ArrayList<String>();
	private ArrayList<String> ans = new ArrayList<String>();
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
	public ArrayList<String> getAns() {
		return ans;
	}
	public void setAns(ArrayList<String> ans) {
		this.ans = ans;
	}
	
	
}
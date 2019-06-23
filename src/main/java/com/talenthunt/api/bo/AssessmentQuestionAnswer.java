package com.talenthunt.api.bo;

import java.util.Set;

public class AssessmentQuestionAnswer implements Comparable<AssessmentQuestionAnswer>{
	private Long questionId;
	private Set<String> answerList;
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public Set<String> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(Set<String> answerList) {
		this.answerList = answerList;
	}
	
	 @Override
     public int compareTo(AssessmentQuestionAnswer assessmnetQueAns) {
        if (this.questionId == assessmnetQueAns.getQuestionId()) {
            return 0;
        } else if (this.questionId < assessmnetQueAns.getQuestionId()) {
            return -1;
        }
        return 1;
     }
}
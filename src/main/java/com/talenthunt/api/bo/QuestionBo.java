/**
 * 
 */
package com.talenthunt.api.bo;

/**
 * @author Harmohan
 *
 */
public class QuestionBo 
{
	private long subjectId;
	private long categoryId;
	private long topicId;
	private OptionsBo optionBo;
	private String answer;
	private String question;
	private String hint;
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long getTopicId() {
		return topicId;
	}
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public OptionsBo getOptionBo() {
		return optionBo;
	}
	public void setOptionBo(OptionsBo optionBo) {
		this.optionBo = optionBo;
	}
	

}

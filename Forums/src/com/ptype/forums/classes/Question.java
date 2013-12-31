package com.ptype.forums.classes;

import java.util.ArrayList;

public class Question {

	private String question;
	private ArrayList<Comment> comments;
	private int numLikes, numViews, numComments;
	private Time timePosted;
	
	public Question() {
		comments = new ArrayList<Comment>(0);
		numLikes = 0;
		numComments = comments.size();
		
	}
	public Question(String question) {
		this.question = question;
		comments = new ArrayList<Comment>(0);
		numLikes = 0;
		numComments = comments.size();
	}
	
	public int getNumComments(){
		return numComments;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String newQuestion) {
		question = newQuestion;
	}
	
	public void addComment(String comment) {
		comments.add(new Comment(comment));
	}
	
	public void addComment(Comment comment) {
		comments.add(comment);
	}
	
	public void addComment(ArrayList<Comment> comments) {
		this.comments.addAll(comments);
	}
	
	public ArrayList<Comment> getComments() {
		return comments;
	}
	
	public int getNumLikes() {
		return numLikes;
	}
	
	public void incrementLikes() {
		numLikes++;
	}
	
	public void decrementLikes() {
		numLikes--;
	}
	
	public void incrementComments(){
		numComments++;
	}
	
	public void decrementComments(){
		numComments--;
	}
	
}

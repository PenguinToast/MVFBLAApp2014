package com.ptype.forums.classes;

import java.util.ArrayList;

public class Question {

	private String question;
	private ArrayList<Comment> comments;
	private int numLikes;
	
	public Question() {
		comments = new ArrayList<Comment>();
		numLikes = 0;
	}
	public Question(String question) {
		this.question = question;
		comments = new ArrayList<Comment>();
		numLikes = 0;
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
	
	public int getLikes() {
		return numLikes;
	}
	
	public void incrementLikes() {
		numLikes++;
	}
	
	public void decrementLikes() {
		numLikes--;
	}
	
}

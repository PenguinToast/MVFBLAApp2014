package com.ptype.forums.classes;

import java.util.ArrayList;
import android.text.format.Time;

public class Question implements Comparable<Question>{
	private String question;
	private ArrayList<Comment> comments;
	private int numLikes, numViews, numComments;
	private Time timePosted;
	
	public Question() {
		comments = new ArrayList<Comment>(0);
		numLikes = 0;
		numViews = 0;
		numComments = comments.size();
		timePosted = new Time();
		timePosted.setToNow();
	}
	
	public Question(String question) {
		this();
		this.question = question;
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
	
	public int getNumViews(){
		return numViews;
	}
	
	@Override
	public int compareTo(Question q){//allows questions to be sorted by time posted
		return Time.compare(timePosted, q.timePosted);
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
	
	public void incrementViews(){
		numViews++;
	}
	
	public void decrementViews(){
		numViews--;
	}
}

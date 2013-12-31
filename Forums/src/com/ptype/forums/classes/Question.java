package com.ptype.forums.classes;

import java.util.ArrayList;

import com.ptype.forums.ForumActivity;

import android.text.format.Time;

public class Question implements Comparable<Question>{
	private String question;
	private ArrayList<Comment> comments;
	private int numLikes, numViews, numComments, sortBy;
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
	
	public Time getTimePosted(){
		return this.timePosted;
	}
	
	public void setSortBy(int x){
		sortBy = x;
	}
	
	@Override
	public int compareTo(Question q){//allows questions to be sorted by time posted
//		return Time.compare(timePosted, q.timePosted);
		switch(sortBy){
		case ForumActivity.SORT_TIME:
			return Time.compare(q.getTimePosted(), this.getTimePosted());
//			break;
		case ForumActivity.SORT_VIEWS:
			return q.getNumViews() - this.getNumViews();
//			break;
		case ForumActivity.SORT_LIKES:
			return q.getNumLikes() - this.getNumLikes();
//			break;
		default://temporary just sort by number of likes
			return q.getNumLikes() - this.getNumLikes();
		}
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

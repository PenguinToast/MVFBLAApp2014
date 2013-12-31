package com.ptype.forums.classes;

public class Comment {
	private String comment;
	private int numLikes;
	
	public Comment() {
		
	}
	
	public Comment(String comment){
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}
	
	public int getLikes() {
		return numLikes;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void incrementLikes() {
		numLikes++;
	}
	
	public void decrementLikes() {
		if(numLikes > 0) {
			numLikes--;
		}
	}
}

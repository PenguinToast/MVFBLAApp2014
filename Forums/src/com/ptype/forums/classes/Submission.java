/* This class represents all submissions.
 * It includes comments and questions to the forum.
 */


package com.ptype.forums.classes;

import java.util.ArrayList;
import java.util.Date;

import android.text.format.Time;

public class Submission {
	protected String text;//the text displayed in the submission
	protected int numLikes, numReplies;//
	protected Date timePosted, timeReplied;//records when the submission was posted
	protected ArrayList<Reply> replies;//an ArrayList of all replies to this submission
	protected int parentID;
	protected int postID;
	
	public Submission() {//initialize class variables
		replies = new ArrayList<Reply>(0);//
		numLikes = 0;
		numReplies = replies.size();
		timePosted = new Date();
		timeReplied = new Date();
		parentID = -1;
		postID = 0;
	}
	
	public Submission(String text) {//overloaded
		this();
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getNumLikes() {
		return numLikes;
	}
	
	public int getNumReplies(){
		return numReplies;
	}
	
	public ArrayList<Reply> getReplies() {
		return replies;
	}
	
	//return when this question was posted
	public Date getTimePosted(){
		return this.timePosted;
	}
	
	public Date getTimeReplied() {
		return timeReplied;
	}
	
	//overloaded method
	public void addReply(String comment) {
		replies.add(new Reply(comment));
	}
	
	//overloaded method
	public void addReply(Reply comment) {
		replies.add(comment);
	}
	
	//overloaded method
	public void addReply(ArrayList<Reply> replies) {
		this.replies.addAll(replies);
	}
	
	public void setLikes(int numLikes) {
		this.numLikes = numLikes;
	}
	
	public void setTimePosted(Date time) {
		this.timePosted = time;
	}
	
	public void setTimeReplied(Date time) {
		this.timeReplied = time;
	}
	
	public int getParentID() {
		return parentID;
	}
	
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	
	public void setPostID(int postID) {
		this.postID = postID;
	}
	
	public int getPostID() {
		return postID;
	}
}

/* This class represents all submissions.
 * It includes comments and questions to the forum.
 */


package com.ptype.forums.classes;

import android.text.format.Time;
import java.util.*;

public class Submission {
	protected String text;
	protected int numLikes, numViews, numReplies;
	protected Time timePosted;
	protected ArrayList<Reply> replies;
	
	public Submission() {
		replies = new ArrayList<Reply>(0);
		numLikes = 0;
		numViews = 0;
		numReplies = replies.size();
		timePosted = new Time();
		timePosted.setToNow();
	}
	
	public Submission(String text) {
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
	
	public int getNumViews(){
		return numViews;
	}
	
	public int getNumReplies(){
		return numReplies;
	}
	
	public ArrayList<Reply> getReplies() {
		return replies;
	}
	
	//return when this question was posted
	public Time getTimePosted(){
		return this.timePosted;
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
		
	
	public void incrementLikes() {
		numLikes++;
	}
	
	public void decrementLikes() {
		numLikes--;
	}
	
	public void incrementReplies(){
		numReplies++;
	}
	
	public void decrementReplies(){
		numReplies--;
	}
	
	public void incrementViews(){
		numViews++;
	}
	
	public void decrementViews(){
		numViews--;
	}
}

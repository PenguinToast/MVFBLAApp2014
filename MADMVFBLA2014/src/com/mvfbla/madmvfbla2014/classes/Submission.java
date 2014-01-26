/* This class represents all submissions.
 * It includes comments and questions to the forum.
 */


package com.mvfbla.madmvfbla2014.classes;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.mvfbla.madmvfbla2014.R;
import com.mvfbla.madmvfbla2014.adapters.ExpandableListAdapter;
import com.mvfbla.madmvfbla2014.net.Network;
import com.mvfbla.madmvfbla2014.net.callback.PostVotesCallback;
import com.mvfbla.madmvfbla2014.net.data.NetVote;
import com.mvfbla.madmvfbla2014.net.data.NetVoteCount;

public class Submission {
	protected String text;//the text displayed in the submission
	protected int numLikes;//
	protected Date timePosted, timeReplied;//records when the submission was posted
	protected ArrayList<Submission> replies;//an ArrayList of all replies to this submission
	protected int parentID;
	protected int postID;
	protected int userID;
	protected boolean isLiked = false;
	
	public Submission() {//initialize class variables
		replies = new ArrayList<Submission>(0);//
		numLikes = 0;
		timePosted = new Date();
		timeReplied = new Date();
		parentID = -1;
		postID = 0;
	}
	
	public Submission(String text) {//overloaded
		this();
		this.text = text;
	}
	
	public void like(final Activity act, final ExpandableListAdapter expAdapter, final View view) {
		Network.setCallback(NetVoteCount.class, new PostVotesCallback() {
			@Override
			public void onResults(Object[] result) {
				setLikes((Integer) result[0]);
				isLiked = (Boolean) result[1];
				act.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						ImageView newView = (ImageView)view;
						if(isLiked) 
							newView.setImageResource(R.drawable.down_arrow);
						else
							newView.setImageResource(R.drawable.up_arrow);
						expAdapter.notifyDataSetChanged();
					}
				});
			};
		});
		Network.sendObject(new NetVote(postID));
		Network.sendObject(new NetVoteCount(postID));
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
	
	public ArrayList<Submission> getReplies() {
		return replies;
	}
	
	public Submission getReply(int position) {
		return replies.get(position);
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
		replies.add(new Submission(comment));
	}
	
	//overloaded method
	public void addReply(Submission comment) {
		replies.add(comment);
	}
	
	//overloaded method
	public void addReply(ArrayList<Submission> replies) {
		this.replies.addAll(replies);
	}
	
	public void setReplies(ArrayList<Submission> replies) {
		this.replies = replies;
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
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int id) {
		this.userID = id;
	}
	
	public void changeIsLiked() {
		isLiked = !isLiked;
	}
	
	public void setLiked(boolean liked) {
		this.isLiked = liked;
	}
	
	public boolean getIsLiked() {
		return isLiked;
	}
}

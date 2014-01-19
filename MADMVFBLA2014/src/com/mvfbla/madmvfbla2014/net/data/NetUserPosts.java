package com.mvfbla.madmvfbla2014.net.data;

import java.util.ArrayList;

import com.mvfbla.madmvfbla2014.classes.Submission;

public class NetUserPosts {
	public ArrayList<Submission> response;
	public int userID;

	public NetUserPosts() {
		userID = -1;
	}
	
	public NetUserPosts(int userID) {
		this.userID = userID;
	}
	
	public NetUserPosts(int userID, ArrayList<Submission> response) {
		this.response = response;
		this.userID = userID;
	}
}

package com.mvfbla.madmvfbla2014.net.data;

public class NetVoteCount {
	public int postID;
	public int points;

	public NetVoteCount() {
	}
	
	public NetVoteCount(int postID) {
		this.postID = postID;
	}
	
	public NetVoteCount(int postID, int points) {
		this.postID = postID;
		this.points = points;
	}
}

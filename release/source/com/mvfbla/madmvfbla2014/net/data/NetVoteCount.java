package com.mvfbla.madmvfbla2014.net.data;

public class NetVoteCount {
	public int postID;
	public int points;
	public boolean voted;

	public NetVoteCount() {
	}
	
	public NetVoteCount(int postID) {
		this.postID = postID;
	}
	
	public NetVoteCount(int postID, int points, boolean voted) {
		this.postID = postID;
		this.points = points;
		this.voted = voted;
	}
}

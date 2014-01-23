package com.mvfbla.madmvfbla2014.net.data;

public class NetUserPostCount {
	public int count;
	public int userID;

	public NetUserPostCount() {
		userID = -1;
	}
	
	public NetUserPostCount(int userID) {
		this.userID = userID;
	}

	public NetUserPostCount(int userID, int count) {
		this.count = count;
		this.userID = userID;
	}
}

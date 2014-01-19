package com.mvfbla.madmvfbla2014.net.data;

public class NetUserPoints {
	public int points;
	public int userID;

	public NetUserPoints() {
		userID = -1;
	}
	
	public NetUserPoints(int userID) {
		this.userID = userID;
	}

	public NetUserPoints(int userID, int points) {
		this.points = points;
		this.userID = userID;
	}
}

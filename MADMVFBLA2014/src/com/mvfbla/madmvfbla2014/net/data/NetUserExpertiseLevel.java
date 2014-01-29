package com.mvfbla.madmvfbla2014.net.data;

public class NetUserExpertiseLevel {
	public String level;
	public int userID;

	public NetUserExpertiseLevel() {
		userID = -1;
	}
	
	public NetUserExpertiseLevel(int userID) {
		this.userID = userID;
	}

	public NetUserExpertiseLevel(int userID, String level) {
		this.level = level;
		this.userID = userID;
	}
}

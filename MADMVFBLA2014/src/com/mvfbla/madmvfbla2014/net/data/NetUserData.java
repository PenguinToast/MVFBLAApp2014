package com.mvfbla.madmvfbla2014.net.data;

import com.mvfbla.madmvfbla2014.classes.UserData;

public class NetUserData {
	public UserData data;
	public int userID;

	public NetUserData() {
		userID = -1;
	}
	
	public NetUserData(int userID) {
		this.userID = userID;
	}

	public NetUserData(int userID, UserData data) {
		this.data = data;
		this.userID = userID;
	}
}

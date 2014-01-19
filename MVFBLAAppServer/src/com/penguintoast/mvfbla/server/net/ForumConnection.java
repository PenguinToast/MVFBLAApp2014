package com.penguintoast.mvfbla.server.net;

import com.esotericsoftware.kryonet.Connection;

public class ForumConnection extends Connection {
	private String fbID;
	private int userID;

	public ForumConnection() {
	}
	
	public String getFBID() {
		return fbID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setFBID(String fbID) {
		this.fbID = fbID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}

}

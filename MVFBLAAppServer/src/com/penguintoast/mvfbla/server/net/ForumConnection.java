package com.penguintoast.mvfbla.server.net;

import com.esotericsoftware.kryonet.Connection;

public class ForumConnection extends Connection {
	private int fbID;
	private int userID;

	public ForumConnection() {
	}
	
	public int getFBID() {
		return fbID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setFBID(int fbID) {
		this.fbID = fbID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}

}

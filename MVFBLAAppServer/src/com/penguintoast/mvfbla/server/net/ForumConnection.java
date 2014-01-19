package com.penguintoast.mvfbla.server.net;

import com.esotericsoftware.kryonet.Connection;

public class ForumConnection extends Connection {
	private int fbID;

	public ForumConnection() {
	}
	
	public int getFBID() {
		return fbID;
	}
	
	public void setFBID(int fbID) {
		this.fbID = fbID;
	}

}

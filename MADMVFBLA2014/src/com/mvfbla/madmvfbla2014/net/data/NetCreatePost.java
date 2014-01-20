package com.mvfbla.madmvfbla2014.net.data;

public class NetCreatePost {
	public String content;
	public int parent;

	public NetCreatePost() {
	}
	
	public NetCreatePost(String content, int parentID) {
		this.content = content;
		this.parent = parentID;
	}

}

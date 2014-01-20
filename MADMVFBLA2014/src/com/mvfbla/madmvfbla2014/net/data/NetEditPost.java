package com.mvfbla.madmvfbla2014.net.data;

public class NetEditPost {
	public String content;
	public int postID;

	public NetEditPost() {
	}

	public NetEditPost(int postID, String content) {
		this.content = content;
		this.postID = postID;
	}
}

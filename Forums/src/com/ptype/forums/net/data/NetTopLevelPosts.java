package com.ptype.forums.net.data;

import java.util.ArrayList;

import com.ptype.forums.classes.Submission;

public class NetTopLevelPosts {
	public ArrayList<Submission> response;

	public NetTopLevelPosts() {
	}
	
	public NetTopLevelPosts(ArrayList<Submission> response) {
		this.response = response;
	}

}

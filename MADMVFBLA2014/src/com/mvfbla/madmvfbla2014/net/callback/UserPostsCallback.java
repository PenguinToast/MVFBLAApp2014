package com.mvfbla.madmvfbla2014.net.callback;

import java.util.ArrayList;

import com.mvfbla.madmvfbla2014.classes.Submission;
import com.mvfbla.madmvfbla2014.net.data.NetUserPosts;

public abstract class UserPostsCallback implements Callback<NetUserPosts, ArrayList<Submission>> {

	@Override
	public void onCallback(NetUserPosts object) {
		onResults(object.response);
	}
}

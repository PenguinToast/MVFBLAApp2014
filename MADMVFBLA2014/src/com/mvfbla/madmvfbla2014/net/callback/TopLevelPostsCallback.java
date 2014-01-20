package com.mvfbla.madmvfbla2014.net.callback;

import java.util.ArrayList;

import com.mvfbla.madmvfbla2014.classes.Submission;
import com.mvfbla.madmvfbla2014.net.data.NetTopLevelPosts;

public abstract class TopLevelPostsCallback implements Callback<NetTopLevelPosts, ArrayList<Submission>> {

	@Override
	public void onCallback(NetTopLevelPosts object) {
		onResults(object.response);
	}
}

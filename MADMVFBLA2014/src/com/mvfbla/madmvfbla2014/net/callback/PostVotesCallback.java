package com.mvfbla.madmvfbla2014.net.callback;

import com.mvfbla.madmvfbla2014.net.data.NetVoteCount;

public abstract class PostVotesCallback implements Callback<NetVoteCount, Integer> {

	@Override
	public void onCallback(NetVoteCount object) {
		onResults(object.points);
	}
}

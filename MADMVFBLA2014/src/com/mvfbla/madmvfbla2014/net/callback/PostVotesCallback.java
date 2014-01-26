package com.mvfbla.madmvfbla2014.net.callback;

import com.mvfbla.madmvfbla2014.net.data.NetVoteCount;

public abstract class PostVotesCallback implements Callback<NetVoteCount, Object[]> {

	@Override
	public void onCallback(NetVoteCount object) {
		onResults(new Object[] {object.points, object.voted});
	}
}

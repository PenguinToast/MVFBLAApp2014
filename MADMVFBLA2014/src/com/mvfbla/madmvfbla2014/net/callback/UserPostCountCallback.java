package com.mvfbla.madmvfbla2014.net.callback;

import com.mvfbla.madmvfbla2014.net.data.NetUserPostCount;

public abstract class UserPostCountCallback implements Callback<NetUserPostCount, Integer> {

	@Override
	public void onCallback(NetUserPostCount object) {
		onResults(object.count);
	}
}

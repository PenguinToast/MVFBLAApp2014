package com.mvfbla.madmvfbla2014.net.callback;

import com.mvfbla.madmvfbla2014.net.data.NetUserPoints;
import com.mvfbla.madmvfbla2014.net.data.NetUserPostCount;

public abstract class UserPointsCallback implements Callback<NetUserPoints, Integer> {

	@Override
	public void onCallback(NetUserPoints object) {
		onResults(object.points);
	}
}

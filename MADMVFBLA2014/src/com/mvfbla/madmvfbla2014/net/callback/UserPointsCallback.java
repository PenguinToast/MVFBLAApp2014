package com.mvfbla.madmvfbla2014.net.callback;

import com.mvfbla.madmvfbla2014.net.data.NetUserPoints;

public abstract class UserPointsCallback implements Callback<NetUserPoints, Integer> {

	@Override
	public void onCallback(NetUserPoints object) {
		onResults(object.points);
	}
}

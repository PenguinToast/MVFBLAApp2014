package com.mvfbla.madmvfbla2014.net.callback;

import com.mvfbla.madmvfbla2014.net.data.NetUserExpertiseLevel;

public abstract class UserLevelCallback implements Callback<NetUserExpertiseLevel, String> {

	@Override
	public void onCallback(NetUserExpertiseLevel object) {
		onResults(object.level);
	}
}

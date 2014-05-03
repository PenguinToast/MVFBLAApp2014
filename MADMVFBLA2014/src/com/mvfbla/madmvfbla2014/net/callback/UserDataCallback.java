package com.mvfbla.madmvfbla2014.net.callback;

import com.mvfbla.madmvfbla2014.classes.UserData;
import com.mvfbla.madmvfbla2014.net.data.NetUserData;

public abstract class UserDataCallback implements Callback<NetUserData, UserData> {

	@Override
	public void onCallback(NetUserData object) {
		onResults(object.data);
	}
}

package com.mvfbla.madmvfbla2014.net.callback;

import java.util.ArrayList;

import com.mvfbla.madmvfbla2014.classes.UserData;
import com.mvfbla.madmvfbla2014.net.data.NetUsers;

public abstract class UsersCallback implements Callback<NetUsers, ArrayList<UserData>> {

	@Override
	public void onCallback(NetUsers object) {
		onResults(object.users);
	}
}

package com.mvfbla.madmvfbla2014;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.mvfbla.madmvfbla2014.classes.User;
import com.mvfbla.madmvfbla2014.net.Network;
import com.mvfbla.madmvfbla2014.net.callback.UserPointsCallback;
import com.mvfbla.madmvfbla2014.net.data.NetUserPoints;

public class ProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		TextView username = (TextView) findViewById(R.id.Username);
		username.setText(User.getUsername());

		final TextView points = (TextView) findViewById(R.id.NumPoints);
		points.setText("Loading...");

		Network.setCallback(NetUserPoints.class, new UserPointsCallback() {
			@Override
			public void onResults(final Integer result) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						points.setText("Points : " + Integer.toString(result));
					}
				});
			}
		});
		if (Network.isConnected()) {
			Network.sendObject(new NetUserPoints(3));
		}
	}

	public String getPointsText() {
		return "Points : " + User.getPoints();
	}
}

package com.mvfbla.madmvfbla2014;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mvfbla.madmvfbla2014.classes.User;
import com.mvfbla.madmvfbla2014.net.Network;
import com.mvfbla.madmvfbla2014.net.callback.UserPointsCallback;
import com.mvfbla.madmvfbla2014.net.data.NetUserPoints;

public class ProfileActivity extends DrawerActivity {
	
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
		Network.sendObject(new NetUserPoints());
	}

	public String getPointsText() {
		return "Points : " + User.getPoints();
	}
}

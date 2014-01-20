package com.mvfbla.madmvfbla2014;

import com.mvfbla.madmvfbla2014.classes.User;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		TextView username = (TextView)findViewById(R.id.Username);
		username.setText(User.getUsername());
		
		TextView points = (TextView)findViewById(R.id.NumPoints);
		points.setText(getPointsText());
		
	}

	public String getPointsText() {
		return "Points : " + User.getPoints();
	}
}

package com.mvfbla.madmvfbla2014;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;

import com.mvfbla.madmvfbla2014.adapters.LeaderboardAdapter;
import com.mvfbla.madmvfbla2014.classes.UserData;
import com.mvfbla.madmvfbla2014.net.Network;
import com.mvfbla.madmvfbla2014.net.callback.UsersCallback;
import com.mvfbla.madmvfbla2014.net.data.NetTopLevelPosts;
import com.mvfbla.madmvfbla2014.net.data.NetUsers;

public class LeaderboardActivity extends DrawerActivity {

	private ArrayList<UserData> leaderboard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leaderboard);
		super.initNavDrawer();
		leaderboard = new ArrayList<UserData>();
		ListView listView = (ListView)findViewById(R.id.Leaderboard);
		final LeaderboardAdapter leaderboardAdapter = new LeaderboardAdapter(LeaderboardActivity.this, leaderboard);
		
		Network.setCallback(NetUsers.class, new UsersCallback() {

			@Override
			public void onResults(ArrayList<UserData> result) {
				leaderboard.clear();
				leaderboard.addAll(result);
				
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						leaderboardAdapter.notifyDataSetChanged();
						System.out.println("user data num " + leaderboard.size());
						System.out.println("user data num " + leaderboard.size());
						System.out.println("user data num " + leaderboard.size());
						System.out.println("user data num " + leaderboard.size());
						System.out.println("user data num " + leaderboard.size());
					}
				});
			}
		});
		Network.sendObject(new NetUsers());
		listView.setAdapter(leaderboardAdapter);

	}
}

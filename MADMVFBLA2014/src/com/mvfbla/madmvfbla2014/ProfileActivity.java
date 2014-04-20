/* This class represents the activity
 * in which the user can view his or her own profile.
 */

package com.mvfbla.madmvfbla2014;

import android.os.Bundle;
import android.widget.TextView;

import com.mvfbla.madmvfbla2014.classes.User;
import com.mvfbla.madmvfbla2014.net.Network;
import com.mvfbla.madmvfbla2014.net.callback.UserPointsCallback;
import com.mvfbla.madmvfbla2014.net.callback.UserPostCountCallback;
import com.mvfbla.madmvfbla2014.net.data.NetUserPoints;
import com.mvfbla.madmvfbla2014.net.data.NetUserPostCount;

public class ProfileActivity extends DrawerActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);//call DrawerActivity's constructor
		setContentView(R.layout.activity_profile);

		TextView username = (TextView) findViewById(R.id.Name);
		username.setText(User.getUsername());//username given by Facebook login

		final TextView points = (TextView) findViewById(R.id.Points);
		points.setText("Loading...");
		
		final TextView posts = (TextView) findViewById(R.id.Posts);
		posts.setText("Loading...");
		
		final TextView expertise = (TextView)findViewById(R.id.Expertise);
		
		final TextView likes = (TextView)findViewById(R.id.Likes);
		likes.setText("Loading...");
		
		final TextView numAnswered = (TextView)findViewById(R.id.NumAnswered);
		final TextView numCorrect = (TextView)findViewById(R.id.NumCorrect);
		
	/*	final TextView location = (TextView) findViewById(R.id.NumPosts);
		location.setText("Current Location : ");
*/
		Network.setCallback(NetUserPoints.class, new UserPointsCallback() {
			@Override
			public void onResults(final Integer result) {//display the number
				runOnUiThread(new Runnable() {			//of points the user has
					@Override
					public void run() {
						points.setText("Points : " + Integer.toString(result));
						if (result < 10) {
							expertise.setText("Learning");
						} else if(result < 40) {
							expertise.setText("Inexperienced");
						} else if(result < 100) {
							expertise.setText("Experienced");
						} else if(result < 200) {
							expertise.setText("Expert");
						} else {
							expertise.setText("Master");
						}
					}
				});
			}
		});
		Network.sendObject(new NetUserPoints());
		
		Network.setCallback(NetUserPostCount.class, new UserPostCountCallback() {
			@Override
			public void onResults(final Integer result) {
				runOnUiThread(new Runnable() {//display the number of posts the user has
					@Override
					public void run() {
						posts.setText("Posts : " + Integer.toString(result));
					}
				});
			}
		});
		Network.sendObject(new NetUserPostCount());
		super.initNavDrawer();
		setTitle("Profile");//set the action bar to display "Profile"
	}

	public String getPointsText() {
		return "Points : " + User.getPoints();
	}
	
	/*public void checkIn(View view) {
		final TextView location = (TextView) findViewById(R.id.Location);
		final Session NewSession = Session.getActiveSession();
		if (NewSession != null && NewSession.isOpened()) {
			// If the session is open, make an API call to get user data
			// and define a new callback to handle the response
			Request request = Request.newMeRequest(NewSession,
					new Request.GraphUserCallback() {
						@Override
						public void onCompleted(GraphUser user,
								Response response) {
							// If the response is successful
							if (NewSession == Session.getActiveSession()) {
								if (user != null) {
									GraphLocation locations = user.getLocation();
									try {
										location.setText("Current Location : " + locations.getInnerJSONObject().get("name"));
									} catch (JSONException e) {
										e.printStackTrace();
									}
									Network.attemptConnect();
								}
								
							}
						}

					});
			Request.executeBatchAsync(request);
		}
	}*/
}

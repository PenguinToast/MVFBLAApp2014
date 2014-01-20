package com.mvfbla.madmvfbla2014;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.mvfbla.madmvfbla2014.classes.User;
import com.mvfbla.madmvfbla2014.fragments.MainFragment;

public class MainActivity extends FragmentActivity {

	private MainFragment mainFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					"com.mvfbla.madmvfbla2014",
					PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		} catch (NameNotFoundException e) {
			Log.e("KeyHash:", e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			Log.e("KeyHash:", e.getMessage());
		}
		if (savedInstanceState == null) {
			// Add the fragment on initial activity setup
			mainFragment = new MainFragment();
			getSupportFragmentManager()
					.beginTransaction()
					.add(android.R.id.content, mainFragment)
					.commit();
		} else {
			// Or set the fragment from restored state info
			mainFragment = (MainFragment) getSupportFragmentManager()
					.findFragmentById(android.R.id.content);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void toForumScreen (View view) {
		if(isLoggedIn()) {
			Intent intent = new Intent(this, ForumActivity.class);
			this.startActivity(intent);
			final Session session = Session.getActiveSession();
		    if (session != null && session.isOpened()) {
		        // If the session is open, make an API call to get user data
		        // and define a new callback to handle the response
		        Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
		            @Override
		            public void onCompleted(GraphUser user, Response response) {
		                // If the response is successful
		                if (session == Session.getActiveSession()) {
		                    if (user != null) {
		                        User.setId(user.getId());//user id
		                        User.setUsername(user.getFirstName(), user.getLastName());;//user's profile name
		                        System.out.println(User.getUsername());
		                    }   
		                }   
		            }
		        }); 
		        Request.executeBatchAsync(request);
		    }  
		}
	}

	public boolean isLoggedIn() {
	    Session session = Session.getActiveSession();
	    if (session != null && session.isOpened()) {
	        return true;
	    } else {
	        return false;
	    }
	}
}

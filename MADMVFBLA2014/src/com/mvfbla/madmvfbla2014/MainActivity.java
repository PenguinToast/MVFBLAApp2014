/* This class represents the 
 * initial screen the user encounters when opening the app.
 */

package com.mvfbla.madmvfbla2014;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
//import android.service.textservice.SpellCheckerService.Session;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;

import com.facebook.Session;
import com.mvfbla.madmvfbla2014.fragments.LoginDialogFragment;
import com.mvfbla.madmvfbla2014.fragments.MainFragment;
import com.mvfbla.madmvfbla2014.net.Network;

public class MainActivity extends FragmentActivity {

	private MainFragment mainFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Network.init();		//initialize the network to server
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


	
	//switch intent to Forums
	public void toForumScreen (View view) {
		this.toForumScreen();
	}
	
	//overloaded 
	public void toForumScreen () {
		if(isLoggedIn()) {
		    Intent intent = new Intent(MainActivity.this, ForumActivity.class);
			startActivity(intent);
		}
		else{
			LoginDialogFragment f = new LoginDialogFragment();
			
			f.show(getSupportFragmentManager(), "");
		}
	}

	//to determine whether to allow users access rest of app
	public static boolean isLoggedIn() {
	    Session session = Session.getActiveSession();
	    if (session != null && session.isOpened()) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	//switch intent to user's Profile
	public void toProfileScreen(View view) {
		if(isLoggedIn()) {
		    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
			startActivity(intent);
		}
		else{
			LoginDialogFragment f = new LoginDialogFragment();
			
			f.show(getSupportFragmentManager(), "");
		}

	}
	
	public void toLBScreen(View view) {
		if(isLoggedIn()) {
			Intent intent = new Intent(this, LeaderboardActivity.class);
			this.startActivity(intent);
		}
	}
	
	public void toTestScreen(View view) {
		if(isLoggedIn()) {
			Intent intent = new Intent(this, TestingActivity.class);
			this.startActivity(intent);
		}
	}
}

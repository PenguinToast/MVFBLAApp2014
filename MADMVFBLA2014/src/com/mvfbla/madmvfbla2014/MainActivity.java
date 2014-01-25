package com.mvfbla.madmvfbla2014;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.facebook.Session;
import com.mvfbla.madmvfbla2014.fragments.MainFragment;
import com.mvfbla.madmvfbla2014.net.Network;

public class MainActivity extends FragmentActivity {
	private String[] drawerTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	private MainFragment mainFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Network.init();		
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
		
		drawerTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, drawerTitles));
//         Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		
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
	
	public void toProfileScreen(View view) {
		if(isLoggedIn()) {
		    Intent intent = new Intent(this, ProfileActivity.class);
			this.startActivity(intent);
		}
	}
}

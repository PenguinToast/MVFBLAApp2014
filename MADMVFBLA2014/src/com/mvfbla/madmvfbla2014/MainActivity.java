package com.mvfbla.madmvfbla2014;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.facebook.Session;
import com.mvfbla.madmvfbla2014.fragments.MainFragment;
import com.mvfbla.madmvfbla2014.net.Network;

public class MainActivity extends FragmentActivity {
	private String[] drawerTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
    private CharSequence mTitle;


	private MainFragment mainFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Network.init();
		drawerTitles = getResources().getStringArray(R.array.planets_array);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, drawerTitles));
        // Set the list's click listener
//        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

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
	
//	private class DrawerItemClickListener implements ListView.OnItemClickListener {
//	    @Override
//	    public void onItemClick(AdapterView parent, View view, int position, long id) {
//	        selectItem(position);
//	    }
//	}
//
//	/** Swaps fragments in the main content view */
//	private void selectItem(int position) {
//	    // Create a new fragment and specify the planet to show based on position
////	    Fragment fragment = new PlanetFragment();
////	    Bundle args = new Bundle();
////	    args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
////	    fragment.setArguments(args);
////
////	    // Insert the fragment by replacing any existing fragment
////	    FragmentManager fragmentManager = getFragmentManager();
////	    fragmentManager.beginTransaction()
////	                   .replace(R.id.content_frame, fragment)
////	                   .commit();
//
//	    // Highlight the selected item, update the title, and close the drawer
//	    mDrawerList.setItemChecked(position, true);
//	    setTitle(drawerTitles[position]);
//	    mDrawerLayout.closeDrawer(mDrawerList);
//	}
//
//	@Override
//	public void setTitle(CharSequence title) {
//	    mTitle = title;
////	    getActionBar().setTitle(mTitle);
//	}


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

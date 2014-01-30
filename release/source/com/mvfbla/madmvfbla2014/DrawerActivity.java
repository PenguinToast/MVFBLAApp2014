/* This class is the parent class of all activities that use navigation drawers. 
 * Those classes extend this class so that they can use the navigation drawer.
 */

package com.mvfbla.madmvfbla2014;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrawerActivity extends Activity {
	protected String[] drawerTitles;//an array of all titles in the navigation drawer
	protected DrawerLayout mDrawerLayout;//DrawerLayout that handles the navigation drawer
	protected ListView mDrawerList;//the ListView that appears in the navigation drawer and has drawerTitles
	protected ActionBarDrawerToggle mDrawerToggle;//adds capability to toggle navigation drawer instead of only swiping
	protected CharSequence mDrawerTitle;//character sequences that display the current category 
	protected CharSequence mTitle;				//in the main bar
	
	//final ints for selection of intents to switch to
	protected static final int FORUM_VIEW = 0;
	protected static final int PROFILE_VIEW= 1;
	protected static final int LEADERBOARD_VIEW= 2;
	protected static final int TEST_VIEW = 3;

	//initialize the navigation drawer and its components
	@SuppressLint("NewApi")
	protected void initNavDrawer() {
		mTitle = mDrawerTitle = getTitle();//get current title 
		//obtain resources from xml files
		drawerTitles = getResources().getStringArray(R.array.drawer_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set the adapter for the list view
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, drawerTitles));
		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		//create the button toggle for drawer
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

//			 Called when a drawer has settled in a completely closed state. 
			public void onDrawerClosed(View view) {
				// super.onDrawerClosed(view);
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}

//			 Called when a drawer has settled in a completely open state. 
			public void onDrawerOpened(View drawerView) {
				// super.onDrawerOpened(drawerView);
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}

	// listener for the navigation drawer on left
	protected class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position,
				long id) {
			selectItem(position);//position determines which intent to switch to
		}
	}

	//Called by the system when the device configuration changes while your activity is running
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	//Called when activity start-up is complete 
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	//called whenever an item in your options menu is selected
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return false;
	}

//	Swaps fragments in the main content view
	protected void selectItem(int position) {
		Intent i = new Intent(DrawerActivity.this,DrawerActivity.class);
		switch (position) {
		case DrawerActivity.FORUM_VIEW: // create a new intent for Forums
			i = new Intent(DrawerActivity.this,ForumActivity.class);
			break;
		case DrawerActivity.PROFILE_VIEW:// create a new intent for Profile
			i = new Intent(DrawerActivity.this,ProfileActivity.class);
			break;
		case DrawerActivity.LEADERBOARD_VIEW://create new intent for Leaderboards
			i = new Intent(DrawerActivity.this,LeaderboardActivity.class);
			break;
		case DrawerActivity.TEST_VIEW://create new intent to practice testing
			i = new Intent(DrawerActivity.this,TestingActivity.class);
			break;
		default:
			break;
		}
		// Highlight the selected item, update the title, and close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(drawerTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
		startActivity(i);

	}

	//Change the title associated with this activity
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

}

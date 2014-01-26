package com.mvfbla.madmvfbla2014;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.Toast;

import com.mvfbla.madmvfbla2014.adapters.ExpandableListAdapter;
import com.mvfbla.madmvfbla2014.classes.Submission;
import com.mvfbla.madmvfbla2014.net.Network;
import com.mvfbla.madmvfbla2014.net.callback.TopLevelPostsCallback;
import com.mvfbla.madmvfbla2014.net.data.NetTopLevelPosts;

public class ForumActivity extends FragmentActivity {
	private String[] drawerTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	// Custom Adapter that allows the list to expand when a group is clicked on
	private ExpandableListAdapter expAdapter;
	// ArrayList of questions that are displayed through the custom adapter
	private ArrayList<Submission> questions;
	// The Expandable listview displayed on this activity for the forum
	private ExpandableListView expList;

	// final static ints that are available globally
	public static final int SORT_TIME = 1;
	public static final int SORT_VIEWS = 2;
	public static final int SORT_LIKES = 3;
	public static final int SORT_DEFAULT = 0;
	
	public static final int NEWPOST_VIEW = 0;
	public static final int FORUM_VIEW = 1;
	public static final int PROFILE_VIEW= 2;
	public static final int LEADERBOARD_VIEW= 3;
	

	// Constructor that initiates the entire activity
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum);

		drawerTitles = getResources().getStringArray(R.array.planets_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set the adapter for the list view
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, drawerTitles));
		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		expList = (ExpandableListView) findViewById(R.id.ExpList);
		questions = new ArrayList<Submission>();

		Network.setCallback(NetTopLevelPosts.class, new TopLevelPostsCallback() {
			@Override
			public void onResults(ArrayList<Submission> result) {
				questions.addAll(result);
			}
		});
		Network.sendObject(new NetTopLevelPosts());
		expAdapter = new ExpandableListAdapter(ForumActivity.this, questions);
		expList.setAdapter(expAdapter);

		expList.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return false;
			}

		});
		expList.setOnGroupExpandListener(new OnGroupExpandListener() {
			int previousGroup = 0;

			@Override
			public void onGroupExpand(int groupPosition) {
				if (groupPosition != previousGroup)
					expList.collapseGroup(previousGroup);
				previousGroup = groupPosition;
			}
		});

		expList.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
			}
		});
		expList.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				return false;
			}

		});
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	// listener for the navigation drawer on left
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
		// Create a new fragment and specify the planet to show based on
		// position
//		 Fragment fragment = new PlanetFragment();
		// Bundle args = new Bundle();
		// args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
		// fragment.setArguments(args);
		switch(position){
		case ForumActivity.FORUM_VIEW: 
			Intent a = new Intent(ForumActivity.this, ForumActivity.class);
			startActivity(a);
			
//			ForumActivity a = new ForumActivity();
//			FragmentManager fragmentManager = getFragmentManager();
//		    fragmentManager.beginTransaction()
//		                   .replace(R.id.content_frame, fragment)
//		                   .commit();

			break;
		case ForumActivity.PROFILE_VIEW:
			Intent b = new Intent(ForumActivity.this, ProfileActivity.class);
			startActivity(b);
			break;
		}

		// Insert the fragment by replacing any existing fragment
		// FragmentManager fragmentManager = getFragmentManager();
		// fragmentManager.beginTransaction()
		// .replace(R.id.content_frame, fragment)
		// .commit();

		// Highlight the selected item, update the title, and close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(drawerTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	// Creates the options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forum, menu);
		return true;
	}

	// Actionbar allows sorting for the menus
	public boolean onOptionsItemSelected(MenuItem item) {
		//
		// return true;
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...
		switch (item.getItemId()) {
			case R.id.SortByTime:
				sort(ForumActivity.SORT_TIME);
				break;
			case R.id.SortByLikes:
				sort(ForumActivity.SORT_LIKES);
				break;
			case R.id.SortByViews:
				sort(ForumActivity.SORT_VIEWS);
				break;
			default:
				Toast.makeText(this, "Action bar", Toast.LENGTH_SHORT).show();
		}

		expAdapter.notifyDataSetChanged();
		return super.onOptionsItemSelected(item);
	}

	// Sorts the Arraylist by time, likes, and views
	public void sort(int sortBy) {
		for (Submission q : questions) {
			// e.setSortBy(sortBy);
		}
		// Collections.sort(questions);
	}

	// Increments the number of likes for a certain question
	// when the up arrow is pressed
	public void addLikes(View view) {
		int groupPosition = (Integer) view.getTag();
		questions.get(groupPosition).like(this, expAdapter, view);
	}
	
	public void addLikeToReply(View view) {
		String values = (String)view.getTag();
		String [] positionString = values.split(",");
		int groupPosition = Integer.parseInt(positionString[0]);
		int childPosition = Integer.parseInt(positionString[1]);
		questions.get(groupPosition).getReply(childPosition).like(this, expAdapter, view);
	}

}

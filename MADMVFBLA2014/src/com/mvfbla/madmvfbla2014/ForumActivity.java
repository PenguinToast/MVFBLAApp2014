package com.mvfbla.madmvfbla2014;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.mvfbla.madmvfbla2014.adapters.ExpandableListAdapter;
import com.mvfbla.madmvfbla2014.classes.Submission;
import com.mvfbla.madmvfbla2014.net.Network;
import com.mvfbla.madmvfbla2014.net.callback.TopLevelPostsCallback;
import com.mvfbla.madmvfbla2014.net.data.NetCreatePost;
import com.mvfbla.madmvfbla2014.net.data.NetTopLevelPosts;

public class ForumActivity extends DrawerActivity {
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
	
	

	// Constructor that initiates the entire activity
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forum);

		
		expList = (ExpandableListView) findViewById(R.id.ExpList);
		questions = new ArrayList<Submission>();

		Network.setCallback(NetTopLevelPosts.class, new TopLevelPostsCallback() {
			@Override
			public void onResults(ArrayList<Submission> result) {
				questions.clear();
				questions.addAll(result);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						expAdapter.notifyDataSetChanged();
					}
				});
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
		super.initNavDrawer();
		setTitle("Forums");//set the action bar to display "Forums"
	}


	// Creates the options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forum, menu);
		return true;
	}

	// Actionbar allows sorting for the menus
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle other action bar items...
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
//		for (Submission q : questions) {
//			// e.setSortBy(sortBy);
//		}
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

	public void SubmitPost(View view) {
		EditText post = (EditText)findViewById(R.id.NewPost);
		String newPost = post.getText().toString();
		// Just change the -1 to the parent post ID for replies
		Network.sendObject(new NetCreatePost(newPost, -1));

		Network.setCallback(NetTopLevelPosts.class, new TopLevelPostsCallback() {
			@Override
			public void onResults(ArrayList<Submission> result) {
				questions.clear();
				questions.addAll(result);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						expAdapter.notifyDataSetChanged();
					}
				});
			}
		});
		Network.sendObject(new NetTopLevelPosts());
	}
}

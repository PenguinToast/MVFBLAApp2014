/* This class represents the activity in which
 * the user views the forums.
 */

package com.mvfbla.madmvfbla2014;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
	private int previousGroup, currentGroup, currentGroupId = -1;
	// Constructor that initiates the entire activity
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);//call DrawerActivity's constructor
		setContentView(R.layout.activity_forum);//using layout from layout.xml
		
		//create from resources in xml
		expList = (ExpandableListView) findViewById(R.id.ExpList);
		questions = new ArrayList<Submission>();
		
		expAdapter = new ExpandableListAdapter(ForumActivity.this, questions);
		expList.setAdapter(expAdapter);

		//get entire forum from server 
		Network.setCallback(NetTopLevelPosts.class, new TopLevelPostsCallback() {
			@Override
			public void onResults(ArrayList<Submission> result) {
				questions.clear();
				questions.addAll(result);//refreshes state of forum with updated forums
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						expAdapter.notifyDataSetChanged();//tell the adapter that the contents have changed
					}
				});
			}
		});
		Network.sendObject(new NetTopLevelPosts());
		
		expList.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				currentGroup = groupPosition;//store which post has been expanded
				currentGroupId = questions.get(previousGroup).getPostID();
				return false;
			}

		});
		expList.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				//only allows one post to be expanded. closes last opened if new post is expanded
				if (groupPosition != previousGroup)
					expList.collapseGroup(previousGroup);
				previousGroup = groupPosition;
				currentGroup = groupPosition;
				currentGroupId = questions.get(previousGroup).getPostID();
			}
		});

		expList.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				currentGroupId = -1;//no post expanded
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
	
	//called when user votes a reply up
	public void addLikeToReply(View view) {
		String values = (String)view.getTag();
		String [] positionString = values.split(",");
		int groupPosition = Integer.parseInt(positionString[0]);
		int childPosition = Integer.parseInt(positionString[1]);
		questions.get(groupPosition).getReply(childPosition).like(this, expAdapter, view);
	}

	//user wants to submit a post to the forum
	public void SubmitPost(View view) {
		EditText post = (EditText)findViewById(R.id.NewPost);
		String newPost = post.getText().toString();
		post.setText("");
		
		InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE); 
		inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                   InputMethodManager.HIDE_NOT_ALWAYS);
		
		
		if(newPost.equals("")) {
			return;//no blank submissions
		}
		// Just change the -1 to the parent post ID for replies
		Network.sendObject(new NetCreatePost(newPost, currentGroupId));

		if(currentGroupId == -1) {	
			Network.setCallback(NetTopLevelPosts.class, new TopLevelPostsCallback() {
				@Override
				public void onResults(ArrayList<Submission> result) {
					questions.clear();
					questions.addAll(result);//refresh forum
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
		else{
			final int nextGroup = currentGroupId;
			Network.setCallback(NetTopLevelPosts.class, new TopLevelPostsCallback() {
				@Override
				public void onResults(ArrayList<Submission> result) {
					questions.clear();
					questions.addAll(result);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							expAdapter.notifyDataSetChanged();
							expList.collapseGroup(currentGroup);//
							
							for(int i = 0; i < questions.size(); i++) {
								if(questions.get(i).getPostID() == nextGroup) {
									expList.expandGroup(i);//expand the post that matches currentGroupId
									return;
								}
							}
						}
					});
				}
			});
			Network.sendObject(new NetTopLevelPosts());
		}
//		System.out.println(currentGroupId);
	}
}

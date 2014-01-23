package com.ptype.forums;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
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

import com.ptype.forums.adapters.ExpandableListAdapter;
import com.ptype.forums.classes.Submission;

public class ForumActivity extends Activity {
	private String[] drawerTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

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

	
		questions = setStandardGroup();
		expList = (ExpandableListView)findViewById(R.id.ExpList);

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

			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(getApplicationContext(),
		                questions.get(groupPosition) + " Expanded",
		                Toast.LENGTH_SHORT).show();
			}
			
		});
		expList.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(getApplicationContext(), 
						questions.get(groupPosition).getText() + " Collapsed",
						Toast.LENGTH_SHORT).show();
			}
		});
		expList.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
				Toast.makeText(getApplicationContext(),
						questions.get(groupPosition).getText() + " : " +
								questions.get(groupPosition).getReplies().get(childPosition), Toast.LENGTH_SHORT).show();
				return false;
			}
			
		});
	}
	
	//listener for the navigation drawer on left
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView parent, View view, int position, long id) {
	        selectItem(position);
	    }
	}
	
	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
	    // Create a new fragment and specify the planet to show based on position
//	    Fragment fragment = new PlanetFragment();
//	    Bundle args = new Bundle();
//	    args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
//	    fragment.setArguments(args);
//
//	    // Insert the fragment by replacing any existing fragment
//	    FragmentManager fragmentManager = getFragmentManager();
//	    fragmentManager.beginTransaction()
//	                   .replace(R.id.content_frame, fragment)
//	                   .commit();
//
//	    // Highlight the selected item, update the title, and close the drawer
//	    mDrawerList.setItemChecked(position, true);
//	    setTitle(mPlanetTitles[position]);
//	    mDrawerLayout.closeDrawer(mDrawerList);
	}

//	@Override
//	public void setTitle(CharSequence title) {
//	    mTitle = title;
//	    getActionBar().setTitle(mTitle);
//	}

	
	// Creates the options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forum, menu);
		return true;
	}
	
	// Sample data to test the activity
	public ArrayList<Submission> setStandardGroup() {
		ArrayList<Submission> ques = new ArrayList<Submission>();
		ArrayList<Submission> comms = new ArrayList<Submission>();
		
		Submission ques1 = new Submission("What are considered fruits?");
		Submission comm1 = new Submission();
		comm1.setText("Apple is considered a fruit. It is one of the most "
				+ "red of all the fruits and is actually very healthy. Although it "
				+ "may not seem as though it is healthy, it is capable of providing many "
				+ "nutrients a human body needs to survive.");
		comms.add(comm1);
		ques1.addReply(comms);
		comms = new ArrayList<Submission>();
		
		Submission ques2 = new Submission("What are considered vegetables?");
		ques2.addReply(new Submission("Lettuce?"));
		
		ques.add(ques1);
		ques.add(ques2);
		ques.add(new Submission("Hello"));
		ques.add(new Submission("Hello"));
		ques.add(new Submission("Hello"));
		ques.add(new Submission("Hello"));
		ques.add(new Submission("Hello"));
		ques.add(new Submission("Hello"));
		ques.add(new Submission("Hello"));
		ques.add(new Submission("Hello"));
		ques.add(new Submission("Hello"));
		ques.add(new Submission("Hello"));
		ques.add(new Submission("Hello"));
		
		return ques;
	}
	
	// Actionbar allows sorting for the menus
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
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
		return true;
	}
	
	// Sorts the Arraylist by time, likes, and views
	public void sort(int sortBy) {
		for(Submission e :questions) {
			//e.setSortBy(sortBy);
		}
		//Collections.sort(questions);
	}
	
	// Increments the number of likes for a certain question
	// when the up arrow is pressed
	public void addLikes(View view) {
		int groupPosition = (Integer)view.getTag();
		//questions.get(groupPosition).incrementLikes();
		expAdapter.notifyDataSetChanged();
	}

	
}

package com.ptype.forums;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.ptype.forums.adapters.ExpandableListAdapter;
import com.ptype.forums.classes.Question;
import com.ptype.forums.classes.Reply;

public class ForumActivity extends Activity {

	// Custom Adapter that allows the list to expand when a group is clicked on
	private ExpandableListAdapter expAdapter;
	// ArrayList of questions that are displayed through the custom adapter
	private ArrayList<Question> questions;
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
		
		expList = (ExpandableListView)findViewById(R.id.ExpList);
		questions = setStandardGroup();
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
	
	// Creates the options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forum, menu);
		return true;
	}
	
	// Sample data to test the activity
	public ArrayList<Question> setStandardGroup() {
		ArrayList<Question> ques = new ArrayList<Question>();
		ArrayList<Reply> comms = new ArrayList<Reply>();
		
		Question ques1 = new Question("What are considered fruits?");
		Reply comm1 = new Reply();
		comm1.setText("Apple is considered a fruit. It is one of the most "
				+ "red of all the fruits and is actually very healthy. Although it "
				+ "may not seem as though it is healthy, it is capable of providing many "
				+ "nutrients a human body needs to survive.");
		comms.add(comm1);
		ques1.addReply(comms);
		comms = new ArrayList<Reply>();
		
		Question ques2 = new Question("What are considered vegetables?");
		ques2.addReply(new Reply("Lettuce?"));
		
		ques.add(ques1);
		ques.add(ques2);
		ques.add(new Question("Hello"));
		ques.add(new Question("Hello"));
		ques.add(new Question("Hello"));
		ques.add(new Question("Hello"));
		ques.add(new Question("Hello"));
		ques.add(new Question("Hello"));
		ques.add(new Question("Hello"));
		ques.add(new Question("Hello"));
		ques.add(new Question("Hello"));
		ques.add(new Question("Hello"));
		ques.add(new Question("Hello"));
		
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
		for(Question e :questions) {
			e.setSortBy(sortBy);
		}
		Collections.sort(questions);
	}
	
	// Increments the number of likes for a certain question
	// when the up arrow is pressed
	public void addLikes(View view) {
		int groupPosition = (Integer)view.getTag();
		questions.get(groupPosition).incrementLikes();;
		expAdapter.notifyDataSetChanged();
	}

	
}

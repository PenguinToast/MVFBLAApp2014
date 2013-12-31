package com.ptype.forums;

import java.util.ArrayList;
import java.util.*;

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
import com.ptype.forums.classes.Comment;
import com.ptype.forums.classes.Question;

public class ForumActivity extends Activity {

	private ExpandableListAdapter expAdapter;
	private ArrayList<Question> questions;
	private ExpandableListView expList;
	static final int SORT_TIME = 1;
	static final int SORT_VIEWS = 2;
	static final int SORT_LIKES = 3;
	static final int SORT_DEFAULT = 0;
	
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
						questions.get(groupPosition).getQuestion() + " Collapsed",
						Toast.LENGTH_SHORT).show();
			}
		});
		expList.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
				Toast.makeText(getApplicationContext(),
						questions.get(groupPosition).getQuestion() + " : " +
								questions.get(groupPosition).getComments().get(childPosition), Toast.LENGTH_SHORT).show();
				return false;
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forum, menu);
		return true;
	}
	
	public ArrayList<Question> setStandardGroup() {
		ArrayList<Question> ques = new ArrayList<Question>();
		ArrayList<Comment> comms = new ArrayList<Comment>();
		
		Question ques1 = new Question("What are considered fruits?");
		Comment comm1 = new Comment();
		comm1.setComment("Apple is considered a fruit. It is one of the most "
				+ "red of all the fruits and is actually very healthy. Although it "
				+ "may not seem as though it is healthy, it is capable of providing many "
				+ "nutrients a human body needs to survive.");
		comms.add(comm1);
		ques1.addComment(comms);
		comms = new ArrayList<Comment>();
		
		Question ques2 = new Question("What are considered vegetables?");
		ques2.addComment(new Comment("Lettuce?"));
		
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
		
		ques.get(3).incrementLikes();
		ques.get(4).incrementLikes();
		ques.get(4).incrementLikes();
		ques.get(4).incrementLikes();
		ques.get(0).incrementLikes();
		ques.get(0).incrementLikes();
		ques.get(1).incrementLikes();
		ques.get(1).incrementLikes();
		ques.get(1).incrementLikes();
		ques.get(1).incrementLikes();
		return ques;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.SortByTime: 
			sort(this.SORT_TIME);
			break;
		default:
			Toast.makeText(this, "Action bar", Toast.LENGTH_SHORT).show();
		}
		
		expAdapter.notifyDataSetChanged();
		return true;
	}
	
	public void sort(int sortBy) {
		for(Question e :questions) {
			e.setSortBy(sortBy);
		}
		Collections.sort(questions);
	}

	
}

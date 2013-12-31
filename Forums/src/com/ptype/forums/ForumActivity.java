package com.ptype.forums;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
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
				this.notifyAll();
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
		comm1.setComment("Apple!");
		comms.add(comm1);
		comms.add(new Comment("Orange!"));
		comms.add(new Comment("Pear!"));
		ques1.addComment(comms);
		comms = new ArrayList<Comment>();
		
		Question ques2 = new Question("What are considered vegetables?");
		ques2.addComment(new Comment("Lettuce?"));
		Comment comm21 = new Comment();
		comm21.setComment("Pomegranate?");
		ques2.addComment(comm21);
		ques2.addComment("Pomegranate's not a fruit you noob...");
		ques2.addComment("*Vegetable...");
		
		ques.add(ques1);
		ques.add(ques2);
		
		return ques;
	}
}

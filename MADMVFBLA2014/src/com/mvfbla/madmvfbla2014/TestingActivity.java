/* This class represents the activity in which
 * the user can take practice tests for his or her competition.
 */

package com.mvfbla.madmvfbla2014;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestingActivity extends DrawerActivity {
	private String answer, question;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		//retrieve from server
		Button ans1 = (Button)findViewById(R.id.Answer1);
		Button ans2 = (Button)findViewById(R.id.Answer2);
		Button ans3 = (Button)findViewById(R.id.Answer3);
		Button ans4 = (Button)findViewById(R.id.Answer4);
		//Need to get the answers
		// To set the answers, just do ans1.setText("answer here") for each of them
		
		TextView q = (TextView)findViewById(R.id.QuestionForTest);
		//Set the question by q.setText("question here")
		
		super.initNavDrawer();
	}
	
	public void setAnswer(String a) {
		this.answer = a;
	}
	
	public void checkAnswer1(View view) {
		Button ans = (Button)view;
		if(ans.getText().equals(answer)) {
			// Put in what happens if it's right
		}
		else {
			//What happens if it's wrong
		}
	}
	public void checkAnswer2(View view) {
		Button ans = (Button)view;
		if(ans.getText().equals(answer)) {
			// Put in what happens if it's right
		}
		else {
			//What happens if it's wrong
		}
	}
	public void checkAnswer3(View view) {
		Button ans = (Button)view;
		if(ans.getText().equals(answer)) {
			// Put in what happens if it's right
		}
		else {
			//What happens if it's wrong
		}
	}
	public void checkAnswer4(View view) {
		Button ans = (Button)view;
		if(ans.getText().equals(answer)) {
			// Put in what happens if it's right
		}
		else {
			//What happens if it's wrong
		}
	}
}

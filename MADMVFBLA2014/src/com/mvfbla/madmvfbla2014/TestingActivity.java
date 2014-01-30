/*
 * This class represents the activity in which the user may take practice tests for his or her competition.
 */

package com.mvfbla.madmvfbla2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mvfbla.madmvfbla2014.net.Network;
import com.mvfbla.madmvfbla2014.net.data.NetAddPoints;

public class TestingActivity extends DrawerActivity {
	private String answer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		reset();

		super.initNavDrawer();// initialize navigation drawer from DrawerActivity
	}

	private ArrayList<String> getQuestion() {
		try {// read the questions, which are retrieve from the server
			String[] files = getAssets().list("questions");
			int chosenFile = (int) (Math.random() * files.length);
			InputStream is = getAssets().open("questions/" + files[chosenFile]);
			BufferedReader bis = new BufferedReader(new InputStreamReader(is));
			String line = null;
			ArrayList<ArrayList<String>> questions = new ArrayList<ArrayList<String>>();
			ArrayList<String> current = null;
			int ansCount = 5;
			while ((line = bis.readLine()) != null) {
				if (line.trim().equals("")) {
					continue;
				}
				ansCount++;
				if (ansCount <= 4) {
					current.add(line);
				} else {
					ansCount = 0;
					if (current != null) {
						questions.add(current);
					}
					current = new ArrayList<String>(5);
					current.add(line);
				}
			}
			questions.add(current);
			return questions.get((int) (Math.random() * questions.size()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// refreshes the questions
	public void reset() {
		ArrayList<String> question = getQuestion();

		answer = new String(question.get(1));

		Button[] ans = new Button[4];
		ans[0] = (Button) findViewById(R.id.Answer1);
		ans[1] = (Button) findViewById(R.id.Answer2);
		ans[2] = (Button) findViewById(R.id.Answer3);
		ans[3] = (Button) findViewById(R.id.Answer4);
		// To set the answers, just do ans1.setText("answer here") for each of them
		for (int i = 0; i < 4; i++) {
			int chosen = (int) (Math.random() * (question.size() - 1));
			ans[i].setText(question.remove(chosen + 1));
		}

		TextView q = (TextView) findViewById(R.id.QuestionForTest);
		// Set the question by q.setText("question here")
		q.setText(question.get(0));
	}

	public void setAnswer(String a) {
		this.answer = a;
	}

	// called if user answers a question correctly
	public void correct() {
		Network.sendObject(new NetAddPoints(1));
		Toast.makeText(this, "Yay! You got it right! You earned 1 point!", Toast.LENGTH_SHORT).show();
		;

		reset();
	}

	// called if user answers a question incorrectly
	public void incorrect() {
		// Show dialog for fail
		Toast.makeText(this, "Sorry! You got it wrong! The answer was " + answer, Toast.LENGTH_SHORT).show();
		;
		reset();
	}

	/* These methods check if answers are correctly chosen. */

	public void checkAnswer1(View view) {
		Button ans = (Button) view;
		if (ans.getText().equals(answer)) {
			correct();
		}
		else {
			incorrect();
		}
	}

	public void checkAnswer2(View view) {
		Button ans = (Button) view;
		if (ans.getText().equals(answer)) {
			correct();
		}
		else {
			incorrect();
		}
	}

	public void checkAnswer3(View view) {
		Button ans = (Button) view;
		if (ans.getText().equals(answer)) {
			correct();
		}
		else {
			incorrect();
		}
	}

	public void checkAnswer4(View view) {
		Button ans = (Button) view;
		if (ans.getText().equals(answer)) {
			correct();
		}
		else {
			incorrect();
		}
	}
}

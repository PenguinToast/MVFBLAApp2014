/* This adapter handles the user data
 * displayed in the leaderboard.
 */

package com.mvfbla.madmvfbla2014.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mvfbla.madmvfbla2014.R;
import com.mvfbla.madmvfbla2014.classes.UserData;

public class LeaderboardAdapter extends ArrayAdapter<UserData> {

	private final ArrayList<UserData> leaderboard;
	private final Context context;

	public LeaderboardAdapter(Context context, ArrayList<UserData> leaderboard) {
		// retrieve the layout of the leaderboard from xml
		super(context, R.layout.leaderboard_rowlayout, leaderboard);
		this.leaderboard = leaderboard;
		this.context = context;
	}

	// return view for user in leaderboards. includes number of points the user
	// has
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.leaderboard_rowlayout, parent,
				false);

		TextView points = (TextView) rowView.findViewById(R.id.Points);
		TextView name = (TextView) rowView.findViewById(R.id.LBname);
		points.setText("" + leaderboard.get(position).getPoints());
		name.setText(leaderboard.get(position).getName());
		return rowView;
	}

}

/* Adapter that handles the
 * expandable list present in ForumActivity.
 */

package com.mvfbla.madmvfbla2014.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvfbla.madmvfbla2014.R;
import com.mvfbla.madmvfbla2014.classes.Submission;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	private Context context;//Interface to global information about an application environment
	private ArrayList<Submission> questions;//list of the questions
	
	public ExpandableListAdapter(Context context, ArrayList<Submission> questions) {
		this.context = context;
		this.questions = questions;
	}

	//called when want to add submission to forum
	public void addItem(Submission reply, Submission question) {
		if(!questions.contains(question)) {
			questions.add(question);
		}
		int index = questions.indexOf(question);
		questions.get(index).addReply(reply);
	}
	
	//returns child of particular post
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return questions.get(groupPosition).getReplies().get(childPosition);
	}

	//returns id of child of particular post
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	//returns the View of child. includes icon of vote and child's text
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		Submission comment = (Submission)getChild(groupPosition, childPosition);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.expandlist_comment, null);	
		
		TextView likes = (TextView)convertView.findViewById(R.id.numLikeComment);
		likes.setText(comment.getNumLikes() + "");
		ImageView iv = (ImageView)convertView.findViewById(R.id.addLikeComment);
		setVoteImage(iv, groupPosition, childPosition);
		iv.setTag(Integer.valueOf(groupPosition) + "," + Integer.valueOf(childPosition));
		TextView tv = (TextView)convertView.findViewById(R.id.tvComment); 
		tv.setText(comment.getText().toString());
	
		return convertView;
	}

	//number of children
	@Override
	public int getChildrenCount(int groupPosition) {
		return questions.get(groupPosition).getReplies().size();
	}

	//return post at groupPosition
	@Override
	public Object getGroup(int groupPosition) {
		return questions.get(groupPosition);
	}

	//size of forums
	@Override
	public int getGroupCount() {
		return questions.size();
	}

	//returns id of particular post
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	//returns the View of a post. includes icon for voting and post's text
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		Submission question = (Submission)getGroup(groupPosition);
		if(convertView == null) {
			LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inf.inflate(R.layout.expandlist_question, null);
		}
		TextView likes = (TextView)convertView.findViewById(R.id.numLikes);
		likes.setText(question.getNumLikes() + "");
		ImageView iv = (ImageView)convertView.findViewById(R.id.addLike);
		setVoteImage(iv, groupPosition);
		iv.setTag(Integer.valueOf(groupPosition));
		TextView tv = (TextView)convertView.findViewById(R.id.tvQuestion);
		tv.setText(question.getText());
		
//		Resources res = getResources();
		if(isExpanded){
			convertView.setBackgroundColor(Color.rgb(255,128,0));//react to press
//			convertView.setBackgroundColor(Color.parseColor(R.color.Orange + ""));
//			convertView.setBackgroundColor(getResources().getColor(R.color.Orange));
		}
		else{
			convertView.setBackgroundColor(Color.WHITE);
		}
		return convertView;
	}
	
	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	//toggles the appearance of vote icon
	public void setVoteImage(ImageView iv, int groupPosition) {
		if(questions.get(groupPosition).getIsLiked())
			iv.setImageResource(R.drawable.down_arrow);
		else
			iv.setImageResource(R.drawable.up_arrow);
	}
	
	public void setVoteImage(ImageView iv, int groupPosition, int childPosition) {
		if(questions.get(groupPosition).getReply(childPosition).getIsLiked())
			iv.setImageResource(R.drawable.down_arrow);
		else
			iv.setImageResource(R.drawable.up_arrow);
	}

}

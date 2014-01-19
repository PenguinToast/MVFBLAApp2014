package com.ptype.forums.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptype.forums.R;
import com.ptype.forums.classes.Submission;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	private Context context;
	private ArrayList<Submission> questions;
	
	public ExpandableListAdapter(Context context, ArrayList<Submission> questions) {
		this.context = context;
		this.questions = questions;
	}

	
	public void addItem(Submission reply, Submission question) {
		if(!questions.contains(question)) {
			questions.add(question);
		}
		int index = questions.indexOf(question);
		questions.get(index).addReply(reply);
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return questions.get(groupPosition).getReplies().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		Submission comment = (Submission)getChild(groupPosition, childPosition);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.expandlist_comment, null);	
		
		TextView tv = (TextView)convertView.findViewById(R.id.tvComment); 
		tv.setText(comment.getText().toString());
	
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return questions.get(groupPosition).getReplies().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return questions.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return questions.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

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
		iv.setTag(Integer.valueOf(groupPosition));
		TextView tv = (TextView)convertView.findViewById(R.id.tvQuestion);
		tv.setText(question.getText());
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

}

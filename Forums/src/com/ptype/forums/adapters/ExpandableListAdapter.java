package com.ptype.forums.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptype.forums.R;
import com.ptype.forums.classes.Comment;
import com.ptype.forums.classes.Question;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	private Context context;
	private ArrayList<Question> questions;
	
	public ExpandableListAdapter(Context context, ArrayList<Question> questions) {
		this.context = context;
		this.questions = questions;
	}

	
	public void addItem(Comment comment, Question question) {
		if(!questions.contains(question)) {
			questions.add(question);
		}
		int index = questions.indexOf(question);
		questions.get(index).addComment(comment);
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return questions.get(groupPosition).getComments().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final int GROUP_POSITION = groupPosition;
		
		if(childPosition < getChildrenCount(groupPosition)-1) {
			Comment comment = (Comment)getChild(groupPosition, childPosition);
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.expandlist_comment, null);	
			TextView tv = (TextView)convertView.findViewById(R.id.tvComment); 
			tv.setText(comment.getComment().toString());
		}
		else {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.expandlist_add_comment, null);
			ImageView iv = (ImageView)convertView.findViewById(R.id.send_button);
			final View CONVERT_VIEW = convertView;

			iv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					EditText et = (EditText)CONVERT_VIEW.findViewById(R.id.NewComment);
					String message = et.getText().toString();
					((Question)getGroup(GROUP_POSITION)).addComment(message);
					System.out.println("Worked");
					et.setText("");
				}
			});
		}
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return questions.get(groupPosition).getComments().size() + 1;
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
		Question question = (Question)getGroup(groupPosition);
		if(convertView == null) {
			LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inf.inflate(R.layout.expandlist_question, null);
		}
		
		TextView tv = (TextView)convertView.findViewById(R.id.tvQuestion);
		tv.setText(question.getQuestion());
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

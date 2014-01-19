/* This class is a type of submission. 
 * It represents the questions posted to the forum.
 */


package com.ptype.forums.classes;

import com.ptype.forums.ForumActivity;
import android.text.format.Time;

public class Question extends Submission implements Comparable<Question>{
	private int sortBy;//int to determine how to sort. by time submitted, rating, or number of views
	
	public Question(){
		this("");//calls overloaded constructor. the text of the question is an empty string
	}
	
	public Question(String text){
		super(text);//creates a Submission 
	}
	
	//set what method to sort submissions by. constants are in ForumActivity
	public void setSortBy(int x){
		sortBy = x;
	}
	
	//allows questions to be sorted by time posted
	@Override
	public int compareTo(Question q){
//		return Time.compare(timePosted, q.timePosted);
		switch(sortBy){
		case ForumActivity.SORT_TIME:
			return Time.compare(q.getTimePosted(), this.getTimePosted());
//			break;
		case ForumActivity.SORT_LIKES:
			return q.getNumLikes() - this.getNumLikes();
//			break;
		default://temporary just sort by number of likes
			return q.getNumLikes() - this.getNumLikes();
		}
	}
	
}

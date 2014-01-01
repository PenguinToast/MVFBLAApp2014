/* This class is a type of submission. It represents 
 * the replies to questions and comments.
 */

package com.ptype.forums.classes;

public class Reply extends Submission{
	
	public Reply(){
		this("");//calls overloaded constructor. empty string
	}
	
	public Reply(String comment){
		super();
		this.text = comment;
	}
	
}

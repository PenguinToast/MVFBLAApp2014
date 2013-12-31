package com.ptype.forums.classes;

public class Reply extends Submission{
	
	public Reply(){
		this("");
	}
	
	public Reply(String comment){
		super();
		this.text = comment;
	}
	
}

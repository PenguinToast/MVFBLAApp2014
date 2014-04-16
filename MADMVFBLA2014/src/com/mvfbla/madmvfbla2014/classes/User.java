/* This class represents a single user
 * of the app.
 */
package com.mvfbla.madmvfbla2014.classes;

public class User {

	private int POINTS = 0;
	private String ID = "";
	private String USERNAME = "";
	private int POSTS = 0;
	private int COMMENTS = 0;
	private int NUMQUESTIONS = 0;
	private int NUMCORRECT = 0;
	
	
	
	public void setUsername(String firstName, String lastName) {
		USERNAME = firstName + " " + lastName;
	}

	/* Getters and Setters */
	
	public String getUsername() {
		return USERNAME;
	}

	public void setPoints(int numPoints) {
		POINTS = numPoints;
	}

	public int getPoints() {
		return POINTS;
	}

	public void incrementPoints() {
		POINTS = getPoints() + 1;
	}

	public void setId(String id) {
		ID = id;
	}

	public String getId() {
		return ID;
	}
}

package com.mvfbla.madmvfbla2014.classes;

public class OtherUser {
	private int POINTS = 0;
	private String ID = "";
	private String USERNAME = "";

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

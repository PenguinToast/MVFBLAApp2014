/* This class represents a single user
 * of the app.
 */
package com.mvfbla.madmvfbla2014.classes;

public class User {

	private static int POINTS = 0;
	private static String ID = "";
	private static String USERNAME = "";

	public static void setUsername(String firstName, String lastName) {
		USERNAME = firstName + " " + lastName;
	}

	/* Getters and Setters */
	
	public static String getUsername() {
		return USERNAME;
	}

	public static void setPoints(int numPoints) {
		POINTS = numPoints;
	}

	public static int getPoints() {
		return POINTS;
	}

	public static void incrementPoints() {
		POINTS = getPoints() + 1;
	}

	public static void setId(String id) {
		ID = id;
	}

	public static String getId() {
		return ID;
	}
}

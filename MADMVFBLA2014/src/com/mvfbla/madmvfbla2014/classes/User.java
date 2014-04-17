/* This class represents a single user
 * of the app.
 */
package com.mvfbla.madmvfbla2014.classes;

public class User {

	private static int POINTS = 0;
	private static String ID = "";
	private static String USERNAME = "";
	private static int POSTS = 0;
	private static int COMMENTS = 0;
	private static int NUMQUESTIONS = 0;
	private static int NUMCORRECT = 0;
	
	
	
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

	public static int getNumQuestions() {
		return NUMQUESTIONS;
	}

	public static void setNumQuestions(int NumQuestions) {
		NUMQUESTIONS = NumQuestions;
	}

	public static int getNumCorrect() {
		return NUMCORRECT;
	}

	public static void setNumCorrect(int NumCorrect) {
		NUMCORRECT = NumCorrect;
	}

	public static int getComments() {
		return COMMENTS;
	}

	public static void setComments(int Comments) {
		COMMENTS = Comments;
	}

	public static int getPosts() {
		return POSTS;
	}

	public static void setPosts(int Posts) {
		POSTS = Posts;
	}
}

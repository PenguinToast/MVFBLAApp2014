/* Data for each user.
 * Includes name of user and number of points.
 */

package com.mvfbla.madmvfbla2014.classes;

public class UserData {
	private String name;
	private int points;
	private String ID;
	private int userId;
	private int questionsCorrect, questionsIncorrect;
	private int voteCount, postCount;

	public UserData() {
	}

	public UserData(String name, int points) {
		this.name = name;
		this.points = points;
	}
	public UserData(String name, int points, String id) {
		this.name = name;
		this.points = points;
		this.ID = id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPoints() {
		return points;
	}
	
	public String getId() {
		return ID;
	}
	
	public int getQuestionsCorrect() {
		return questionsCorrect;
	}

	public void setQuestionsCorrect(int questionsCorrect) {
		this.questionsCorrect = questionsCorrect;
	}

	public int getQuestionsIncorrect() {
		return questionsIncorrect;
	}

	public void setQuestionsIncorrect(int questionsIncorrect) {
		this.questionsIncorrect = questionsIncorrect;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
}

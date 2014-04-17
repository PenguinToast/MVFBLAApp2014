/* Data for each user.
 * Includes name of user and number of points.
 */

package com.mvfbla.madmvfbla2014.classes;

public class UserData {
	private String name;
	private int points;
	private String ID;

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
}

package com.mvfbla.madmvfbla2014.classes;

public class UserData {
	private String name;
	private int points;

	public UserData() {
	}

	public UserData(String name, int points) {
		this.name = name;
		this.points = points;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPoints() {
		return points;
	}
}

package com.penguintoast.mvfbla.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Tester {

	public static void main(String[] args) {
		new Tester().go();
	}

	public Tester() {
	}

	public void go() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/forums?"
					+ "user=admin&password=starcraft1");
			
			PreparedStatement prepared = connect.prepareStatement("SELECT COUNT(*) FROM forums.upvotes WHERE user_id=? AND post_id=?");
			prepared.setInt(1, 3);
			prepared.setInt(2, 1);
			ResultSet results = prepared.executeQuery();
			
			results.first();
			System.out.println(results.getInt(1));
					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

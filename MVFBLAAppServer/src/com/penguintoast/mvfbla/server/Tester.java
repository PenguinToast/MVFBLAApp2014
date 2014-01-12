package com.penguintoast.mvfbla.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
			
			PreparedStatement prepared = connect.prepareStatement("insert into FORUMS.USERS values (default, ?, ?)");
			prepared.setInt(1, 0);
			prepared.setDate(2, Date.valueOf("2014-1-2"));
			prepared.executeUpdate();
			
			Statement statement = connect.createStatement();
			ResultSet results = statement.executeQuery("select * from FORUMS.USERS");
			
			System.out.println(results.getMetaData());
			
					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

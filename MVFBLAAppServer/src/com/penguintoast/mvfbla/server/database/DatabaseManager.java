package com.penguintoast.mvfbla.server.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Types;

public class DatabaseManager {
	private static DatabaseManager INSTANCE;

	private PreparedStatement createUser;
	private PreparedStatement createPost;
	private PreparedStatement editPost;
	private PreparedStatement deletePost;
	private PreparedStatement timePost;

	static {
		INSTANCE = new DatabaseManager();
	}

	private DatabaseManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/forums?"
					+ "user=admin&password=starcraft1");

			createUser = connect.prepareStatement("insert into FORUMS.USERS values (default, ?, ?);");
			createPost = connect.prepareStatement("insert into FORUMS.POSTS values (default, ?, ?, ?, ?, ?);");
			editPost = connect.prepareStatement("update FORUMS.POSTS set post_content=? where post_id=?;");
			deletePost = connect.prepareStatement("delete from FORUMS.POSTS where post_id=?");
			timePost = connect.prepareStatement("update FORUMS.POSTS set post_date_replied=? where post_id=?");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public DatabaseManager getInstance() {
		return INSTANCE;
	}

	/**
	 * Creates a new user in the forums
	 * 
	 * @param fb_id
	 *            The Facebook ID of the user
	 */
	public boolean createUser(int fb_id) {
		try {
			createUser.setInt(1, fb_id);
			createUser.setDate(2, new Date(System.currentTimeMillis()));
			return createUser.executeUpdate() > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Creates a new post in the forums
	 * 
	 * @param content
	 *            The content of the post
	 * @param post_by
	 *            The userID of the user who created the post
	 * @param post_parent
	 *            The postID of the parent of the post (-1 for questions)
	 */
	public boolean createPost(String content, int post_by, int post_parent) {
		try {
			createPost.setString(1, content);
			createPost.setDate(2, new Date(System.currentTimeMillis()));
			createPost.setInt(3, post_by);
			if (post_parent < 0) {
				createPost.setNull(4, Types.INTEGER);
			} else {
				createPost.setInt(4, post_parent);
				
			}
			return createPost.executeUpdate() > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Edits the content of a post
	 * 
	 * @param postID
	 *            ID of the post to edit
	 * @param update
	 *            New content to replace old content with
	 */
	public boolean editPost(int postID, String update) {
		try {
			editPost.setString(2, update);
			editPost.setInt(1, postID);
			int result = editPost.executeUpdate();
			if(result > 0) {
				return updatePostTime(postID);
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Update the last replied time of the post to the current server time
	 * 
	 * @param postID
	 *            ID of the post to update
	 */
	public boolean updatePostTime(int postID) {
		try {
			timePost.setDate(1, new Date(System.currentTimeMillis()));
			timePost.setInt(2, postID);
			return timePost.executeUpdate() > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Deletes a post
	 * 
	 * @param postID
	 *            ID of the post to delete
	 */
	public void deletePost(int postID) {
		try {
			deletePost.setInt(1, postID);
			deletePost.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

package com.penguintoast.mvfbla.server.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;

import com.ptype.forums.classes.Submission;

public class DatabaseManager {
	private static DatabaseManager INSTANCE;

	private PreparedStatement createUser;
	private PreparedStatement createPost;

	private PreparedStatement editPost;
	private PreparedStatement deletePost;
	private PreparedStatement timePost;

	private PreparedStatement getTopLevelPosts;
	private PreparedStatement getPostReplies;

	private PreparedStatement voteQuery;
	private PreparedStatement voteAdd;
	private PreparedStatement voteRemove;
	private PreparedStatement voteNum;

	private PreparedStatement pointsSet;
	private PreparedStatement pointsQuery;

	static {
		INSTANCE = new DatabaseManager();
	}

	private DatabaseManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/forums?"
					+ "user=admin&password=starcraft1");

			// Insert user - facebookID, date
			createUser = connect.prepareStatement("INSERT INTO forums.users VALUES (default, ?, ?, 0);");
			// Insert post - content, date, by, parent, date_replied
			createPost = connect.prepareStatement("INSERT INTO forums.posts VALUES (default, ?, ?, ?, ?, ?);");
			// Edit post - content, postID
			editPost = connect.prepareStatement("UPDATE forums.posts SET post_content=? WHERE post_id=?;");
			// Delete post - postID
			deletePost = connect.prepareStatement("DELETE FROM forums.posts WHERE post_id=?");
			// Update post replied time - date_replied, postID
			timePost = connect.prepareStatement("UPDATE forums.posts SET post_date_replied=? WHERE post_id=?");
			// Query user vote on post - userID, postID
			voteQuery = connect.prepareStatement("SELECT COUNT(*) FROM forums.upvotes WHERE user_id=? AND post_id=?");
			// Query vote count on post - postID
			voteNum = connect.prepareStatement("SELECT COUNT(*) FROM forums.upvotes WHERE post_id=?");
			// Add vote - userID, postID
			voteAdd = connect.prepareStatement("INSERT INTO forums.upvotes values (?, ?)");
			// Remove vote - userID, postID
			voteRemove = connect.prepareStatement("DELETE FROM forums.upvotes WHERE user_id=? AND post_id=?");
			// Set points of a user - points, userID
			pointsSet = connect.prepareStatement("UPDATE forums.users SET user_points=? WHERE user_id=?");
			// Query points count of user - userID
			pointsQuery = connect.prepareStatement("SELECT forums.users.user_points FROM forums.users WHERE user_id=?");
			// Query top level posts
			getTopLevelPosts = connect
					.prepareStatement("SELECT * FROM forums.posts WHERE post_parent IS NULL ORDER BY post_date_replied DESC;");
			// Query post replies - postParent
			getPostReplies = connect
					.prepareStatement("SELECT * FROM forums.posts WHERE post_parent = ? ORDER BY post_date_replied DESC;");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static DatabaseManager getInstance() {
		return INSTANCE;
	}
	
	public Time dateToTime(Date date) {
		return null;
	}

	public ArrayList<Submission> getTopLevelPosts() {
		try {
			ArrayList<Submission> out = new ArrayList<Submission>();
			ResultSet results = getTopLevelPosts.executeQuery();
			while(results.next()) {
				Submission sub = new Submission();
				sub.setPostID(results.getInt("post_id"));
				sub.setText(results.getString("post_content"));
				sub.setTimePosted(dateToTime(results.getDate("post_date")));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Submission> getReplies(int postID) {
		try {
			ArrayList<Submission> out = new ArrayList<Submission>();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
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
				updatePostTime(post_parent);
			}
			createPost.setDate(5, new Date(System.currentTimeMillis()));
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
			if (result > 0) {
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
	public boolean deletePost(int postID) {
		try {
			deletePost.setInt(1, postID);
			return deletePost.executeUpdate() > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Changes a user's vote choice on a post
	 * 
	 * @param userID
	 *            ID of the user that is voting
	 * @param postID
	 *            ID of the post to vote on
	 */
	public boolean votePost(int userID, int postID) {
		try {
			if (voteState(userID, postID)) {
				voteRemove.setInt(1, userID);
				voteRemove.setInt(2, postID);
				return voteRemove.executeUpdate() > 0;
			} else {
				voteAdd.setInt(1, userID);
				voteAdd.setInt(2, postID);
				return voteAdd.executeUpdate() > 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Gets whether or not a user has voted on a post
	 * 
	 * @param userID
	 *            ID of the user to check
	 * @param postID
	 *            ID of the post to check
	 * @return Whether or not the post has been voted on by the user
	 */
	public boolean voteState(int userID, int postID) {
		try {
			voteQuery.setInt(1, userID);
			voteQuery.setInt(2, postID);
			ResultSet results = voteQuery.executeQuery();
			results.first();
			return results.getInt(1) > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Gets the vote count on a post
	 * 
	 * @param postID
	 *            ID of the post to check
	 * @return Number of votes on the post
	 */
	public int voteCount(int postID) {
		try {
			voteNum.setInt(1, postID);
			ResultSet results = voteNum.executeQuery();
			results.first();
			return results.getInt(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	/**
	 * Sets the number of points for a user
	 * 
	 * @param userID
	 *            ID of user to modify
	 * @param points
	 *            Point value to set to
	 * @return Whether or not set was successful
	 */
	public boolean setPoints(int userID, int points) {
		try {
			pointsSet.setInt(1, points);
			pointsSet.setInt(2, userID);
			return pointsSet.executeUpdate() > 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Gets the number of points a user has
	 * 
	 * @param userID
	 *            ID of user to query
	 * @return Number of points the user has
	 */
	public int getPoints(int userID) {
		try {
			pointsQuery.setInt(1, userID);
			ResultSet results = pointsQuery.executeQuery();
			results.first();
			return results.getInt(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	/**
	 * Adds points to a user
	 * 
	 * @param userID
	 *            ID of user to add points to
	 * @param points
	 *            Number of points to add
	 * @return Whether or not the add was successful
	 */
	public boolean addPoints(int userID, int points) {
		try {
			int numPoints = getPoints(userID);
			return setPoints(userID, numPoints + points);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}

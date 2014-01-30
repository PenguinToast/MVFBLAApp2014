package com.penguintoast.mvfbla.server.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.mvfbla.madmvfbla2014.net.Network;
import com.mvfbla.madmvfbla2014.net.data.NetAddPoints;
import com.mvfbla.madmvfbla2014.net.data.NetCreatePost;
import com.mvfbla.madmvfbla2014.net.data.NetEditPost;
import com.mvfbla.madmvfbla2014.net.data.NetLogin;
import com.mvfbla.madmvfbla2014.net.data.NetTopLevelPosts;
import com.mvfbla.madmvfbla2014.net.data.NetUserPoints;
import com.mvfbla.madmvfbla2014.net.data.NetUserPostCount;
import com.mvfbla.madmvfbla2014.net.data.NetUserPosts;
import com.mvfbla.madmvfbla2014.net.data.NetUsers;
import com.mvfbla.madmvfbla2014.net.data.NetVote;
import com.mvfbla.madmvfbla2014.net.data.NetVoteCount;
import com.penguintoast.mvfbla.server.database.DatabaseManager;

public class ForumServer {
	private Server server;
	private DatabaseManager database;

	public ForumServer() {
	}

	public void start() {
		try {
			database = DatabaseManager.getInstance();
			server = new Server(163840, 20480) {
				@Override
				protected Connection newConnection() {
					return new ForumConnection();
				}
			};
			Network.register(server.getKryo());
			server.start();
			server.bind(Network.PORT);
			server.addListener(new Listener() {
				@Override
				public void received(Connection connection, Object object) {
					ForumServer.this.received((ForumConnection) connection, object);
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void received(ForumConnection connection, Object object) {
		database.ensureValid();
		if (object instanceof NetLogin) {
			NetLogin dat = (NetLogin) object;
			connection.setFBID(dat.id);
			connection.setUserID(database.getUserID(dat.id, dat.name));
			database.setUserID(connection.getUserID());
		}
		if (object instanceof NetTopLevelPosts) {
			connection.sendTCP(new NetTopLevelPosts(database.getTopLevelPosts()));
		}
		if (object instanceof NetVote) {
			NetVote dat = (NetVote) object;
			database.votePost(connection.getUserID(), dat.postID);
		}
		if (object instanceof NetUserPosts) {
			NetUserPosts dat = (NetUserPosts) object;
			int userID = connection.getUserID();
			if (dat.userID != -1) {
				userID = dat.userID;
			}
			connection.sendTCP(new NetUserPosts(userID, database.getUserPosts(userID)));
		}
		if (object instanceof NetUserPoints) {
			NetUserPoints dat = (NetUserPoints) object;
			int userID = connection.getUserID();
			if (dat.userID != -1) {
				userID = dat.userID;
			}
			connection.sendTCP(new NetUserPoints(userID, database.getPoints(userID)));
		}
		if (object instanceof NetUserPostCount) {
			NetUserPostCount dat = (NetUserPostCount) object;
			int userID = connection.getUserID();
			if (dat.userID != -1) {
				userID = dat.userID;
			}
			connection.sendTCP(new NetUserPostCount(userID, database.getUserPostCount(userID)));
		}
		if (object instanceof NetCreatePost) {
			NetCreatePost dat = (NetCreatePost) object;
			int userID = connection.getUserID();
			if (database.getUserPostCount(userID) <= 0) {
				database.addPoints(userID, 10);
			}
			database.createPost(dat.content, userID, dat.parent);
		}
		if (object instanceof NetEditPost) {
			NetEditPost dat = (NetEditPost) object;
			database.editPost(dat.postID, dat.content);
		}
		if (object instanceof NetVoteCount) {
			NetVoteCount dat = (NetVoteCount) object;
			connection.sendTCP(new NetVoteCount(dat.postID, database.voteCount(dat.postID), database.voteState(connection.getUserID(), dat.postID)));
		}
		if (object instanceof NetUsers) {
			connection.sendTCP(new NetUsers(database.getUsers()));
		}
		if (object instanceof NetAddPoints) {
			database.addPoints(connection.getUserID(), ((NetAddPoints)object).points);
		}
	}
}

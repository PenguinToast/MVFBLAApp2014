package com.penguintoast.mvfbla.server.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.mvfbla.mvfbla2014.net.Network;
import com.mvfbla.mvfbla2014.net.data.NetLogin;
import com.mvfbla.mvfbla2014.net.data.NetTopLevelPosts;
import com.mvfbla.mvfbla2014.net.data.NetVote;
import com.penguintoast.mvfbla.server.database.DatabaseManager;

public class ForumServer {
	private Server server;
	private DatabaseManager database;

	public ForumServer() {
	}

	public void start() {
		try {
			database = DatabaseManager.getInstance();
			server = new Server() {
				@Override
				protected Connection newConnection() {
					return new ForumConnection();
				}
			};
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
		if (object instanceof NetLogin) {
			NetLogin dat = (NetLogin) object;
			connection.setFBID(dat.id);
			connection.setUserID(database.getUserID(dat.id));
		}
		if (object instanceof NetTopLevelPosts) {
			connection.sendTCP(new NetTopLevelPosts(database.getTopLevelPosts()));
		}
		if (object instanceof NetVote) {
			NetVote dat = (NetVote) object;
			database.votePost(connection.getUserID(), dat.postID);
		}
	}
}

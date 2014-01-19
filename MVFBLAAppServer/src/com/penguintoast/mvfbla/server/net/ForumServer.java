package com.penguintoast.mvfbla.server.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.penguintoast.mvfbla.server.database.DatabaseManager;
import com.ptype.forums.net.Network;
import com.ptype.forums.net.data.NetLogin;

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
		}
	}
}

package com.penguintoast.mvfbla.server.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.ptype.forums.net.Network;

public class ForumServer {
	private Server server;

	public ForumServer() {
	}

	public void start() {
		try {
			server = new Server();
			server.start();
			server.bind(Network.PORT);
			server.addListener(new Listener() {
				@Override
				public void received(Connection connection, Object object) {
					ForumServer.this.received(connection, object);
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void received(Connection connection, Object object) {
		
	}
}

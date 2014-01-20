package com.mvfbla.madmvfbla2014.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.util.ObjectMap;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.mvfbla.madmvfbla2014.net.callback.Callback;
import com.mvfbla.madmvfbla2014.net.data.NetCreatePost;
import com.mvfbla.madmvfbla2014.net.data.NetEditPost;
import com.mvfbla.madmvfbla2014.net.data.NetLogin;
import com.mvfbla.madmvfbla2014.net.data.NetTopLevelPosts;
import com.mvfbla.madmvfbla2014.net.data.NetUserPoints;
import com.mvfbla.madmvfbla2014.net.data.NetUserPosts;
import com.mvfbla.madmvfbla2014.net.data.NetVote;

public class Network {
	public static final int PORT = 56635;

	private static boolean connected;
	private static Client client;
	private static ObjectMap<Class, Callback> callbacks;

	public static void init() {
		client = new Client();
		client.addListener(new Listener() {
			@Override
			public void received(Connection connection, Object object) {
				Network.received(connection, object);
			}

			@Override
			public void connected(Connection connection) {
				connected = true;
			}

			@Override
			public void disconnected(Connection connection) {
				connected = false;
			}
		});
		client.start();
		attemptConnect();
	}
	
	private static void received(Connection connection, Object object) {
		if(callbacks.containsKey(object.getClass())) {
			callbacks.get(object.getClass()).onCallback(object);
		}
	}

	public static boolean attemptConnect() {
		try {
			client.connect(3000, "192.168.0.100", PORT);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static boolean isConnected() {
		return connected;
	}
	
	public static void setCallback(Class clazz, Callback callback) {
		callbacks.put(clazz, callback);
	}

	public static void register(Kryo kryo) {
		@SuppressWarnings("rawtypes")
		Class[] toRegister = {
				NetCreatePost.class,
				NetEditPost.class,
				NetLogin.class,
				NetTopLevelPosts.class,
				NetUserPoints.class,
				NetUserPosts.class,
				NetVote.class
		};
		for (int i = 0; i < toRegister.length; i++) {
			kryo.register(toRegister[i]);
		}
	}
}

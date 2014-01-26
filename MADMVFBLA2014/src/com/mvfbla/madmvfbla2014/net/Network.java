package com.mvfbla.madmvfbla2014.net;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;

import android.os.StrictMode;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.util.ObjectMap;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.facebook.Session;
import com.mvfbla.madmvfbla2014.classes.Submission;
import com.mvfbla.madmvfbla2014.classes.User;
import com.mvfbla.madmvfbla2014.net.callback.Callback;
import com.mvfbla.madmvfbla2014.net.data.NetCreatePost;
import com.mvfbla.madmvfbla2014.net.data.NetEditPost;
import com.mvfbla.madmvfbla2014.net.data.NetLogin;
import com.mvfbla.madmvfbla2014.net.data.NetTopLevelPosts;
import com.mvfbla.madmvfbla2014.net.data.NetUserPoints;
import com.mvfbla.madmvfbla2014.net.data.NetUserPostCount;
import com.mvfbla.madmvfbla2014.net.data.NetUserPosts;
import com.mvfbla.madmvfbla2014.net.data.NetVote;
import com.mvfbla.madmvfbla2014.net.data.NetVoteCount;

public class Network {
	public static final int PORT = 44443;

	private static Client client;
	private static ObjectMap<Class, Callback> callbacks;
	private static ArrayDeque<Object> sendQueue;

	public static void init() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);
		com.esotericsoftware.minlog.Log.TRACE();
		client = new Client();
		register(client.getKryo());
		callbacks = new ObjectMap<Class, Callback>();
		sendQueue = new ArrayDeque<Object>();
		client.addListener(new Listener() {
			@Override
			public void received(Connection connection, Object object) {
				Network.received(connection, object);
			}

			@Override
			public void connected(Connection connection) {
				if (!sendQueue.isEmpty()) {
					Object next = null;
					while ((next = sendQueue.poll()) != null) {
						client.sendTCP(next);
					}
				}
			}

			@Override
			public void disconnected(Connection connection) {
			}
		});

		client.start();
	}

	private static void received(Connection connection, Object object) {
		if (callbacks.containsKey(object.getClass())) {
			callbacks.get(object.getClass()).onCallback(object);
		}
	}

	public static boolean attemptConnect() {
		if (!isConnected() && Session.getActiveSession().isOpened()) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						client.connect(3000, "penguintoast.no-ip.biz", Network.PORT);
						client.sendTCP(new NetLogin(User.getId()));
					} catch (Exception ex) {
						return;
					}
				}
			});
			t.start();
		}
		return true;
	}

	public static boolean isConnected() {
		return client.isConnected();
	}

	public static void sendObject(Object o) {
		if (!isConnected()) {
			attemptConnect();
			sendQueue.offer(o);
		} else {
			client.sendTCP(o);
		}
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
				NetUserPostCount.class,
				NetUserPosts.class,
				NetVote.class,
				NetVoteCount.class,

				Submission.class,
				ArrayList.class,
				Date.class,
				java.sql.Date.class
		};
		for (int i = 0; i < toRegister.length; i++) {
			kryo.register(toRegister[i]);
		}
	}
}

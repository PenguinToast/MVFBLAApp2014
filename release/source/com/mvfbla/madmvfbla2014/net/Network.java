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
import com.mvfbla.madmvfbla2014.classes.UserData;
import com.mvfbla.madmvfbla2014.net.callback.Callback;
import com.mvfbla.madmvfbla2014.net.data.NetAddPoints;
import com.mvfbla.madmvfbla2014.net.data.NetCreatePost;
import com.mvfbla.madmvfbla2014.net.data.NetEditPost;
import com.mvfbla.madmvfbla2014.net.data.NetLogin;
import com.mvfbla.madmvfbla2014.net.data.NetQuestionAnswered;
import com.mvfbla.madmvfbla2014.net.data.NetTopLevelPosts;
import com.mvfbla.madmvfbla2014.net.data.NetUserData;
import com.mvfbla.madmvfbla2014.net.data.NetUserPoints;
import com.mvfbla.madmvfbla2014.net.data.NetUserPostCount;
import com.mvfbla.madmvfbla2014.net.data.NetUserPosts;
import com.mvfbla.madmvfbla2014.net.data.NetUsers;
import com.mvfbla.madmvfbla2014.net.data.NetVote;
import com.mvfbla.madmvfbla2014.net.data.NetVoteCount;

/**
 * @author William
 *
 */
public class Network {
	public static final int PORT = 44443;

	private static Client client;
	private static ObjectMap<Class, Callback> callbacks;
	private static ArrayDeque<Object> sendQueue;

	/**
	 * Initialize networking
	 */
	public static void init() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);
		//com.esotericsoftware.minlog.Log.TRACE();
		client = new Client(81920, 20480);
		register(client.getKryo());
		// Initialize network callbacks hashmap
		callbacks = new ObjectMap<Class, Callback>();
		sendQueue = new ArrayDeque<Object>();
		client.addListener(new Listener() {
			@Override
			public void received(Connection connection, Object object) {
				Network.received(connection, object);
			}

			@Override
			public void connected(Connection connection) {
				client.sendTCP(new NetLogin(User.getId(), User.getUsername()));
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

	// Call the callback for the received object, if it exists
	private static void received(Connection connection, Object object) {
		if (callbacks.containsKey(object.getClass())) {
			callbacks.get(object.getClass()).onCallback(object);
		}
	}

	/**
	 * Sends an object over the network to server
	 * @param o - Object to send
	 */
	public static void sendObject(Object o) {
		if (!isConnected()) {
			attemptConnect();
			sendQueue.offer(o);
		} else {
			client.sendTCP(o);
		}
	}

	/**
	 * Set a callback for the specified class
	 * @param clazz - Class to set callback for
	 * @param callback - Callback to call upon object reception
	 */
	public static void setCallback(Class clazz, Callback callback) {
		callbacks.put(clazz, callback);
	}

	public static boolean attemptConnect() {
		if (!isConnected() && Session.getActiveSession().isOpened()) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						client.connect(3000, "ec2-54-200-186-68.us-west-2.compute.amazonaws.com", Network.PORT);
					} catch (Exception ex) {
						System.out.println("Unable to connect to server.");
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

	public static void register(Kryo kryo) {
		@SuppressWarnings("rawtypes")
		Class[] toRegister = {
				NetCreatePost.class,
				NetEditPost.class,
				NetLogin.class,
				NetTopLevelPosts.class,
				NetAddPoints.class,
				NetUserPoints.class,
				NetUserPostCount.class,
				NetUserPosts.class,
				NetUsers.class,
				NetVote.class,
				NetVoteCount.class,
				NetQuestionAnswered.class,
				NetUserData.class,

				Submission.class,
				UserData.class,
				ArrayList.class,
				Date.class,
				java.sql.Date.class
		};
		for (int i = 0; i < toRegister.length; i++) {
			kryo.register(toRegister[i]);
		}
	}
}

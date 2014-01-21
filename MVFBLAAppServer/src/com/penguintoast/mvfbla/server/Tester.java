package com.penguintoast.mvfbla.server;

import com.esotericsoftware.kryonet.Client;
import com.mvfbla.madmvfbla2014.net.Network;

public class Tester {

	public static void main(String[] args) {
		new Tester().go();
	}

	public Tester() {
	}

	public void go() {
		try {
			Client c = new Client();
			c.start();
			c.connect(2000, "penguintoast.no-ip.biz", Network.PORT);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

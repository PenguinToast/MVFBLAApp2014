package com.mvfbla.madmvfbla2014.net.callback;

public interface Callback<T, V> {
	void onCallback(T object);
	void onResults(V result);
}

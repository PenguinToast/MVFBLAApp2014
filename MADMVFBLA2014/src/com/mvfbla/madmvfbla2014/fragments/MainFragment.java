package com.mvfbla.madmvfbla2014.fragments;

import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.mvfbla.madmvfbla2014.R;
import com.mvfbla.madmvfbla2014.classes.User;

public class MainFragment extends Fragment {

	private static final String TAG = "MainFragment";
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	        System.out.println("4");
	    }
	};
	private UiLifecycleHelper uiHelper;

	
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.activity_main, container, false);
	    LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    authButton.setLoginBehavior(SessionLoginBehavior.SUPPRESS_SSO);
	    authButton.setFragment(this);
	    authButton.setReadPermissions(Arrays.asList("user_location"));

	    return view;
	}
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    if (state.isOpened()) {
	        Log.i(TAG, "Logged in...");
	        final Session NewSession = Session.getActiveSession();
		    if (NewSession != null && NewSession.isOpened()) {
		        // If the session is open, make an API call to get user data
		        // and define a new callback to handle the response
		        Request request = Request.newMeRequest(NewSession, new Request.GraphUserCallback() {
		            @Override
		            public void onCompleted(GraphUser user, Response response) {
		                // If the response is successful
		                if (NewSession == Session.getActiveSession()) {
		                    if (user != null) {
		                        User.setId(user.getId());//user id
		                        User.setUsername(user.getFirstName(), user.getLastName());//user's profile name
		                    }   
		                }   
		            }
		            
		        }); 
		        Request.executeBatchAsync(request);
		    }  
	    } else if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	        System.out.println("2");
	    }
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    Session session = Session.getActiveSession();
	    if (session != null &&
	           (session.isOpened() || session.isClosed()) ) {
	        onSessionStateChange(session, session.getState(), null);
	        System.out.println("3");
	    }
	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
}

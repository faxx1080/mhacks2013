package com.example.mhacks;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import android.os.Bundle;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.view.*;
import android.widget.TextView;


public class MainActivity extends Activity {
 private Thread timer = new Thread();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			setTimelines();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			TextView myTextView = (TextView)findViewById(R.id.helloW);
		    myTextView.setText("ERROR.");
			e.printStackTrace();
		}

	}
	public void setTimelines() throws InterruptedException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("I7Z0g2sNMuMvcna0Oc54gA")
		  .setOAuthConsumerSecret("ZvbvNO7fisYh2iv8uE6Wh6sONgKgWJeZ1E3gfvaOlY")
		  .setOAuthAccessToken("358724021-e78kCSEz1MTCWvTk4aO3lwUolOfxxWpd6Zask6Wm")
		  .setOAuthAccessTokenSecret("zoNcUYpHpYP49ioJlLeQs9GBmEAYNDeypGI50D3qGc");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		//twitter = TwitterFactory.getSingleton();
	    List<Status> statuses = null;
	    List<Status> userStatus = null;	
	    try {
			statuses = twitter.getHomeTimeline();
			userStatus = twitter.getUserTimeline();
		
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    TextView myTextView = (TextView)findViewById(R.id.helloW);
	    myTextView.setMovementMethod(new ScrollingMovementMethod());
	    myTextView.setText("Showing home timeline.");
	    //timer.wait(2000);
	   // System.out.println("Showing home timeline.");
	    for (Status status : statuses) {
	    myTextView.append(status.getUser().getName() + ":" + status.getText());
	    	//System.out.println(status.getUser().getName() + ":" +
	         //                  status.getText());
	    }
	    myTextView.append("\n\nShowing User timeline.\n");
	    for (Status status2 : userStatus) {
	    	myTextView.append(status2.getUser().getName() + ":" + status2.getText());
	    	//System.out.println(status2.getUser().getName() + ":" +
	          //                 status2.getText());
	    }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

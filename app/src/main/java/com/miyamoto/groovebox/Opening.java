
/* Miyamoto Yoshi
 * CS86 Spring
 * Assignment 11
 * 
 * 5/21/13
 */


package com.miyamoto.groovebox;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class Opening extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "XOLGV5J3xvZ3KWMyloHTvENyV";
    private static final String TWITTER_SECRET = "m5t3T6KNI0vsymJpFkrhJEdxKX5HTeZ38OwlSZmRM8v2Ys0KBs";

	private static MediaPlayer mp;

	AnimationDrawable openingAnimation;
	ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
		Fabric.with(this, new Twitter(authConfig));

		setContentView(R.layout.opening);

		ImageView animeView = (ImageView) findViewById(R.id.imageOpening);
		animeView.setBackgroundResource(R.drawable.animation);
		openingAnimation = (AnimationDrawable) animeView.getBackground();

		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		mp = MediaPlayer.create(this, R.raw.opening2); // remix1 player
		mp.start();

		Thread timer2 = new Thread() {
			public void run() {
				int progress = 0;
				for (int i = 0; i < 20; i++) // progress bar increment by 5
					progressBar.setProgress(progress);
					progress += 5;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		};
		timer2.start();
		
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					openingAnimation.stop();
					startActivity(new Intent(Opening.this, MainActivity.class));
				}
			}
		};
		timer.start();
	}
	

	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		
		openingAnimation.start();

		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mp.stop();
		finish();
	}


}

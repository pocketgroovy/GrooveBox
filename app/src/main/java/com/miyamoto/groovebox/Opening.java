
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

public class Opening extends Activity {
	private static MediaPlayer mp;

	AnimationDrawable openingAnimation;
	ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

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

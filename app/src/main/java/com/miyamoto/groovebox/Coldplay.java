/* Miyamoto Yoshi
 * CS86 Spring
 * Assignment 11
 * 
 * 5/21/13
 */

package com.miyamoto.groovebox;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;


public class Coldplay extends Activity implements Runnable {
	private static Button btnPlay, btnPlay2;
	private static MediaPlayer mp, mp2, clickSound;
	private static TextView totalTimeTV, totalTimeTV2;
	private static TextView musicCurrentTimeTV;
	private static TextView musicCurrentTimeTV2;
	private Date musicTotalTime, musicTotalTime2;
	private VisualizerWindow visualizerWindow;
	private static SimpleDateFormat formatter;
	private Thread timer;

	private static Integer[] coldPic = { R.drawable.co, R.drawable.co2,
			R.drawable.co3, R.drawable.co4 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coldplay);

		Gallery coldPhotos = (Gallery) findViewById(R.id.coldGallery);

		coldPhotos.setAdapter(new ImageAdapter(this));
		visualizerWindow = new VisualizerWindow();

	}

	public static class ImageAdapter extends BaseAdapter {
		private Context context;

		public ImageAdapter(Context c) {
			context = c;

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return coldPic.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView pic = new ImageView(context);
			pic.setImageResource(coldPic[position]);
			pic.setScaleType(ImageView.ScaleType.FIT_XY);
			pic.setLayoutParams(new Gallery.LayoutParams(400, 350));
			return pic;
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// remix1
		btnPlay = (Button) findViewById(R.id.btnRemix1);
		mp = MediaPlayer.create(this, R.raw.coldplay_remix1); // remix1 player
		mp.setLooping(true);
		totalTimeTV = (TextView) findViewById(R.id.tvTime); // textview for
															// displaying total
															// time for remix1
		musicCurrentTimeTV = (TextView) findViewById(R.id.tvTimeNow); // textview
																		// for
																		// displaynig
																		// current
																		// time
																		// for
																		// remix1
		musicTotalTime = new Date(mp.getDuration()); // total duration of remix1

		// remix2
		btnPlay2 = (Button) findViewById(R.id.btnRemix2);
		mp2 = MediaPlayer.create(this, R.raw.coldplay_remix2); // remix1 player
		mp2.setLooping(true);
		totalTimeTV2 = (TextView) findViewById(R.id.tvTime2); // textview for
																// displaying
																// total time
																// for remix1
		musicCurrentTimeTV2 = (TextView) findViewById(R.id.tvTimeNow2); // textview
																		// for
																		// displaynig
																		// current
																		// time
																		// for
																		// remix1
		musicTotalTime2 = new Date(mp2.getDuration()); // total duration of
														// remix1

		formatter = new SimpleDateFormat("mm:ss"); // format the long number to
													// time format

		totalTimeTV.setText(formatter.format(musicTotalTime)); // set the remix1
																// totaltime to
																// the textview
		totalTimeTV2.setText(formatter.format(musicTotalTime2));// set the
																// remix2
																// totaltime to
																// the textview

		timer = new Thread(this); // start the timer for the class

		clickSound = MediaPlayer.create(this, R.raw.click);

	}

	@Override
	public void run() {
		while (mp != null || mp2 != null) {

			if (mp.isPlaying()) {
				try {
					Message msg = new Message();
					msg.what = mp.getCurrentPosition(); // get current time for
														// the song playing
					threadHandler.sendMessage(msg); // send the message back to
													// the main thread to set
													// the text to
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} catch (IllegalStateException ex) {
					ex.printStackTrace();
				} finally {
				}
			} else if (mp2.isPlaying()) {
				try {
					Message msg = new Message();
					msg.what = mp2.getCurrentPosition(); // get current time for
															// the song playing
					threadHandler.sendMessage(msg); // send the message back to
													// the main thread to set
													// the text to
					Thread.sleep(1000);

				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} catch (IllegalStateException ex) {
					ex.printStackTrace();
				} finally {
				}
			}
		}
	}

	// remix 1 button clicked
	public void onClickPlayOne(View v) {

		clickSound.start();
		if (!mp.isPlaying()) {
			mp.start();
			btnPlay.setText("Pause");
			if (!timer.isAlive()) {
				timer.start();
			}
			// btnPlay2.setVisibility(View.INVISIBLE);
			btnPlay2.setEnabled(false);

		} else {
			mp.pause();
			btnPlay.setText("Resume");
			// btnPlay2.setVisibility(View.VISIBLE);
			btnPlay2.setEnabled(true);

		}
	}

	public void onClickPlayTwo(View v) {
		clickSound.start();

		if (!mp2.isPlaying()) {
			mp2.start();
			btnPlay2.setText("Pause");
			if (!timer.isAlive())
				timer.start();
			// btnPlay.setVisibility(View.INVISIBLE);
			btnPlay.setEnabled(false);

		} else {
			mp2.pause();
			btnPlay2.setText("Resume");
			// btnPlay.setVisibility(View.VISIBLE);
			btnPlay.setEnabled(true);

		}

	}

	public void onClickTwit(View v) {
		clickSound.start();

		Intent intent = new Intent(Coldplay.this, TwitterTimelineActivity.class);
		intent.putExtra("screen_name", "coldplay");
		startActivity(intent);
	}

	public void onClickV(View v) {
		clickSound.start();

		Intent i = new Intent("android.intent.action.VisualizerWindow");
		if (mp.isPlaying()) {
			int audioID = mp.getAudioSessionId();
			i.putExtra("audio", audioID);
			i.putExtra("myDrawable", R.drawable.coldplay_remix1);
			visualizerWindow.mp = mp;

		} else {
			int audioID2 = mp2.getAudioSessionId();
			i.putExtra("audio", audioID2);
			i.putExtra("myDrawable", R.drawable.coldplay_remix2);
			visualizerWindow.mp = mp2;
		}
		startActivity(i);
	}

	private static Handler threadHandler = new Handler() {
		private Date musicCurrentTime;

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			musicCurrentTime = new Date(msg.what);
			if (mp.isPlaying()) {
				musicCurrentTimeTV.setText(formatter.format(musicCurrentTime));
			} else if (mp2.isPlaying()) {
				musicCurrentTimeTV2.setText(formatter.format(musicCurrentTime));

			}
		}
	};

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (mp.isPlaying()) {
			mp.release();
		}
		if (mp2.isPlaying()) {

			mp2.release();
		}
		// mp = null;
		// mp2 = null;

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}
}

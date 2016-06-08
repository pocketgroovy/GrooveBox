package com.miyamoto.groovebox;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

public class VisualizerWindow extends Activity {

	private static ImageView imageView;
	public MediaPlayer mp;
	private static SoundVisualizer visualizer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visualizerwindow);

		imageView = (ImageView) findViewById(R.id.visualizerImage);

		int drawableID = getIntent().getIntExtra("myDrawable", -1);

		imageView.setImageResource(drawableID);

		int audio = getIntent().getIntExtra("audio", -1);

		try {
			visualizer = new SoundVisualizer(audio, this, R.anim.visualizer,
					imageView);
			visualizer.startVisualizer(mp);

		} catch (RuntimeException e) {
			e.getMessage();
		}
	}

	private void stopAll() {

		visualizer.release();

		finish();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		stopAll();

	}

}

package com.miyamoto.groovebox;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.media.audiofx.Visualizer.OnDataCaptureListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SoundVisualizer extends Visualizer implements OnDataCaptureListener {
	public int numberOfEQBands = 32;
	private int[] dbArray;
	private Context c = null;
	private int id = 0;
	private ImageView image = null;

	public SoundVisualizer(int audioSession) throws UnsupportedOperationException,
			RuntimeException {
		super(audioSession);
		// TODO Auto-generated constructor stub
	}

	
	//designated constructor with context, animation xml id and an image to animate
	public SoundVisualizer(int audioSession, Context c, int id, ImageView image) 
			throws UnsupportedOperationException, RuntimeException {
		super(audioSession);
		this.c = c;
		this.id = id;
		this.image = image;
	}
	

	public void startVisualizer(MediaPlayer mp) {

		this.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);

		this.setDataCaptureListener(this, Visualizer.getMaxCaptureRate() / 2,
				false, true);

		this.setEnabled(true);

		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				SoundVisualizer.this.setEnabled(false);
				
			}
		});

	}
	

	@Override
	public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform,
			int samplingRate) {
		// TODO Auto-generated method stub
	}

	@Override //get frequency data from audio, separated into the number of bands and calculate the each bands db, trigger the animation by the volume( and the frequency data for more accuracy)
	public void onFftDataCapture(Visualizer visualizer, byte[] fft,
			int samplingRate) {

		if (dbArray == null || dbArray.length < numberOfEQBands) {
			dbArray = new int[numberOfEQBands];
		}

		for (int i = 0; i < numberOfEQBands; i++) {
			byte rfk = fft[numberOfEQBands * i];
			byte ifk = fft[numberOfEQBands * i + 1];
			float dynamics = (rfk * rfk + ifk + ifk);
			int db = (int) (10 * Math.log10(dynamics));
			dbArray[i] = db;
//			System.out.println(dbArray[i] + " " + i);
		}

		//animation trigger
		if (dbArray[1] > 20 || fft[4] > 60) {
			startAnime(c, id, image);
		}

	}

	private void startAnime(Context c, int id, ImageView image) {
		Animation animation = AnimationUtils.loadAnimation(c, id);
		image.startAnimation(animation);

	}

}

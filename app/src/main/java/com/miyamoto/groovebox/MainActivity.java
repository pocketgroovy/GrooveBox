/* Miyamoto Yoshi
 * CS86 Spring
 * Assignment 11
 * 
 * 5/21/13
 */

package com.miyamoto.groovebox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Log;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

	ProgressDialog dialog;
	Thread timer, timer2;
	private static MediaPlayer clickSound;

	private static Integer[] artists = { R.drawable.coldplay, R.drawable.radiohead,
			R.drawable.skrillex, R.drawable.rjd2 };
	String[] radioStation = { "Coldplay Remix and Photo",
			"Radiohead Remix and Photo", "Skrillex Remix and Photo",
			"RJD2 Remix and Photo" }; // play list

	ImageView imageView;
	Gallery photos;


	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);

		Log.d("DEBUG", " restored on rotate");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView) findViewById(R.id.imgArtists);
		clickSound = MediaPlayer.create(this, R.raw.click);

		photos = (Gallery) findViewById(R.id.gallery1);

		photos.setAdapter(new ImageAdapter(this)); // set imageAdopter to show photos in the Gallery

		photos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub

				Toast.makeText(
						getBaseContext(),
						"Selected " + radioStation[position]
								+ " page!", Toast.LENGTH_LONG).show();
				imageView.setVisibility(View.VISIBLE);
				imageView.setImageResource(artists[position]);

				dialog = new ProgressDialog(MainActivity.this); // progressbar
				dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				dialog.setMax(100);
				dialog.show();

				timer2 = new Thread() {
					public void run() {

						for (int i = 0; i < 20; i++) // progress bar increment by 5
						{
							dialog.incrementProgressBy(5);
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				};
				timer2.start();

				switch (position) // going to each artists' page after 2.5sec
				{

				case 0:
					clickSound.start();
					timer = new Thread() {
						public void run() {
							try {
								sleep(2500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							} finally {
								dialog.dismiss();
								startActivity(new Intent(MainActivity.this,
										Coldplay.class));

							}
						}
					};
					timer.start();

					// display the artist picture
					break;

				case 1:
					clickSound.start();

					timer = new Thread() {
						public void run() {
							try {
								sleep(2500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							} finally {
								dialog.dismiss();
								startActivity(new Intent(MainActivity.this,
										Radiohead.class)); // display the artist
															// picture
							}
						}
					};
					timer.start();
					break;

				case 2:
					clickSound.start();

					timer = new Thread() {
						public void run() {
							try {
								sleep(2500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							} finally {
								dialog.dismiss();
								startActivity(new Intent(MainActivity.this,
										Skrillex.class));
							}
						}
					};
					timer.start();
					// display the artist picture
					break;

				case 3:
					clickSound.start();

					timer = new Thread() {
						public void run() {
							try {
								sleep(2500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							} finally {
								dialog.dismiss();
								startActivity(new Intent(MainActivity.this,
										Rjd2.class));
							}
						}
					};
					timer.start();
					// display the artist picture
					break;
				}
			}
		});

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		imageView.setVisibility(View.INVISIBLE);

	}

	public static class ImageAdapter extends BaseAdapter {
		Context context;

		public ImageAdapter(Context c) {
			// TODO Auto-generated constructor stub
			context = c;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return artists.length;
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
			pic.setImageResource(artists[position]);
			pic.setScaleType(ImageView.ScaleType.FIT_XY);
			pic.setLayoutParams(new Gallery.LayoutParams(400, 350));
			return pic;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	
}
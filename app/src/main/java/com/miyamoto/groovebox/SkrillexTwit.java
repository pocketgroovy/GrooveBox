/* Miyamoto Yoshi
 * CS86 Spring
 * Assignment 11
 * 
 * 5/21/13
 */


package com.miyamoto.groovebox;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SkrillexTwit extends Activity {

	TextView twitTx,twitTx2,twitTx3,twitTx4,twitTx5;
	TextView twitTimeTx,twitTimeTx2,twitTimeTx3,twitTimeTx4,twitTimeTx5;
	HttpClient client;
	JSONArray json;
	JSONObject latest,latest2,latest3,latest4,latest5;

	final static String URL = "https://api.twitter.com/1/statuses/user_timeline.json?screen_name=";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sk_twit);
		twitTx = (TextView)findViewById(R.id.txtTwit);
		twitTx2 = (TextView)findViewById(R.id.txtTwit2);
		twitTx3 = (TextView)findViewById(R.id.txtTwit3);
		twitTx4 = (TextView)findViewById(R.id.txtTwit4);
		twitTx5 = (TextView)findViewById(R.id.txtTwit5);
		
		
		twitTimeTx = (TextView)findViewById(R.id.txtTwitTime);
		twitTimeTx2 = (TextView)findViewById(R.id.txtTwitTime2);
		twitTimeTx3 = (TextView)findViewById(R.id.txtTwitTime3);
		twitTimeTx4 = (TextView)findViewById(R.id.txtTwitTime4);
		twitTimeTx5 = (TextView)findViewById(R.id.txtTwitTime5);

		client = new DefaultHttpClient();
		new Read().execute("twitter");
	}
	
	
	public JSONArray latestTweet(String username) throws ClientProtocolException, IOException, JSONException
	{
		StringBuilder url = new StringBuilder(URL);
		url.append(username);
		HttpGet get = new HttpGet(url.toString());
		HttpResponse response = client.execute(get);
		int status = response.getStatusLine().getStatusCode();
		if(status == 200){
			HttpEntity entity = response.getEntity();
			String data = EntityUtils.toString(entity);
			JSONArray timeline = new JSONArray(data);
			return timeline;
		}else{
			Toast.makeText(SkrillexTwit.this, "Error", Toast.LENGTH_SHORT).show();
		
		return null;
		}
	}
	
		
		public class Read extends AsyncTask<String, Integer, JSONArray>{
			private ProgressBar progressBar;

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				
				progressBar = (ProgressBar)findViewById(R.id.progressbar);
				progressBar.setVisibility(View.VISIBLE);
			}

			@Override
			protected JSONArray doInBackground(String... params) {
				// TODO Auto-generated method stub
				int count = params.length;
				for(int i = 0; i < count ; i++)
				{
					publishProgress((int)((i/(float)count)* 100));
					if(isCancelled()) break;
				}
				try {
					return json = latestTweet("skrillex");
					
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
			protected void onProgressUpdate(Integer...progress) {
				super.onProgressUpdate(progress[0]);
				progressBar.setProgress(progress[0]);
				
			}
		
		@Override
		protected void onPostExecute(JSONArray result) {
			// TODO Auto-generated method stub
			progressBar.setVisibility(View.INVISIBLE);
			progressBar.setProgress(0);
			
			try {
				latest = result.getJSONObject(0);
				latest2 = result.getJSONObject(1);
				latest3 = result.getJSONObject(2);
				latest4 = result.getJSONObject(3);
				latest5 = result.getJSONObject(4);

			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				twitTx.setText(latest.getString("text"));
				twitTx2.setText(latest2.getString("text"));
				twitTx3.setText(latest3.getString("text"));
				twitTx4.setText(latest4.getString("text"));
				twitTx5.setText(latest5.getString("text"));
				
				twitTimeTx.setText(latest.getString("created_at"));
				twitTimeTx2.setText(latest2.getString("created_at"));
				twitTimeTx3.setText(latest3.getString("created_at"));
				twitTimeTx4.setText(latest4.getString("created_at"));
				twitTimeTx5.setText(latest5.getString("created_at"));


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();

	}
}

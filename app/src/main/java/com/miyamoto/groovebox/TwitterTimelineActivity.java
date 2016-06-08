package com.miyamoto.groovebox;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import com.twitter.sdk.android.tweetui.UserTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
/**
 * Created by yoshiloop on 6/8/16.
 */
public class TwitterTimelineActivity extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String screenName = intent.getExtras().getString("screen_name");
        if(screenName == null){
            return;
        }
        // TODO Auto-generated method stub
        setContentView(R.layout.timeline);
        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName(screenName)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);
    }
}

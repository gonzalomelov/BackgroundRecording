package uy.edu.fing.proygrad.backgroundrecording;


import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.LiveCard.PublishMode;


public class SimpleLiveCardService extends Service {

	 public static final String TAG = "SimpleLiveCardService";
	 private boolean recording;

    private LiveCard mLiveCard;
    private RemoteViews mLiveCardView;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mLiveCard == null) {
            mLiveCard = new LiveCard(this, TAG);

            // Inflate a layout into a remote view
            mLiveCardView = new RemoteViews(getPackageName(), R.layout.simple_layout);

            mLiveCardView.setTextViewText(R.id.card_sample_textview, "background recording");
            // Always call setViews() to update the live card's RemoteViews.
            mLiveCard.setViews(mLiveCardView);

            Intent menuIntent = new Intent(this, MenuActivity.class);
            menuIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mLiveCard.setAction(PendingIntent.getActivity(this, 0, menuIntent, 0));
           
            mLiveCard.attach(this);
            mLiveCard.publish(PublishMode.REVEAL);
            
            Log.e(TAG, "onStartCommand");
        	
        	
        	// potentially add data to the intent
        	
        	startService(new Intent(this, BackgroundVideoRecorder.class)); 
        	recording = true;
        } else {
            mLiveCard.navigate();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
    	Log.e(TAG, "onDestroy");
    	if(recording){
    		stopService(new Intent(this, BackgroundVideoRecorder.class));
    	}
        if (mLiveCard != null && mLiveCard.isPublished()) {
            mLiveCard.unpublish();
            mLiveCard = null;
        }
        super.onDestroy();
    }
}

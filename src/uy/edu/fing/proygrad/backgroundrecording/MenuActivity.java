package uy.edu.fing.proygrad.backgroundrecording;




import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


/**
 * Created by Gonzalo on 04/05/2014.
 */
public class MenuActivity extends Activity {

    public static final String TAG = "MenuActivity";

    private final Handler mHandler = new Handler();

	private boolean recording;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        openOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.simple, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        switch (item.getItemId()) {
            case R.id.stop:
            	Log.e(TAG, "onOptionsItemSelected - stop");
//            	if(recording){
//            		stopService(new Intent(MenuActivity.this, BackgroundVideoRecorder.class));
//            	}
//            	
//                // Stop the service at the end of the message queue for proper options menu
//                // animation. This is only needed when starting a new Activity or stopping a Service
//                // that published a LiveCard.
//                post(new Runnable() {
//
//                    @Override
//                    public void run() {
//                    	
//                    	
//                        stopService(new Intent(MenuActivity.this, SimpleLiveCardService.class));
//                    }
//                });
                return true;
            
            case R.id.record:
                // Start the new Activity at the end of the message queue for proper options menu
                // animation. This is only needed when starting a new Activity or stopping a Service
                // that published a LiveCard.
            	Log.e(TAG, "onOptionsItemSelected - record");
            	
            	
            	// potentially add data to the intent
            	
//            	startService(new Intent(this, BackgroundVideoRecorder.class)); 
//            	recording = true;
            	startService(new Intent(this, SendService.class)); 
            	

                return true;
            case R.id.video:
//            	Intent myIntent = new Intent(MenuActivity.this,
//						VideoViewActivity.class);
//				startActivity(myIntent);
            	Intent i = new Intent();
            	i.setAction("com.google.glass.action.VIDEOPLAYER");
            	i.putExtra("video_url", "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov"); 
            	startActivity(i);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        // Nothing else to do, closing the Activity.
        finish();
    }

    /**
     * Posts a {@link Runnable} at the end of the message loop, overridable for testing.
     */
    protected void post(Runnable runnable) {
        mHandler.post(runnable);
    }
}

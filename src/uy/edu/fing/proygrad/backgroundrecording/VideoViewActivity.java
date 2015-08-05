package uy.edu.fing.proygrad.backgroundrecording;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;
 
public class VideoViewActivity extends Activity {
 
	// Declare variables
	ProgressDialog pDialog;
	VideoView videoview;
 
	// Insert your Video URL
	//String VideoURL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
	//String VideoURL = "rtsp://192.168.1.43:1935/vod/2015-07-0720-4112.mp4";
	String VideoURL = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the layout from video_main.xml
		setContentView(R.layout.videoview_main);
		// Find your VideoView in your video_main.xml layout
		videoview = (VideoView) findViewById(R.id.VideoView);
		// Execute StreamVideo AsyncTask
 
		// Create a progressbar
		pDialog = new ProgressDialog(VideoViewActivity.this);
		// Set progressbar title
		pDialog.setTitle("Android Video Streaming Tutorial");
		// Set progressbar message
		pDialog.setMessage("Buffering...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		// Show progressbar
		pDialog.show();
 
		try {
			// Start the MediaController
			MediaController mediacontroller = new MediaController(
					VideoViewActivity.this);
			mediacontroller.setAnchorView(videoview);
			// Get the URL from String VideoURL
			Uri video = Uri.parse(VideoURL);
			videoview.setMediaController(mediacontroller);
			videoview.setVideoURI(video);
 
		} catch (Exception e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}
 
		videoview.requestFocus();
		videoview.setOnPreparedListener(new OnPreparedListener() {
			// Close the progress bar and play the video
			public void onPrepared(MediaPlayer mp) {
				pDialog.dismiss();
				videoview.start();
			}
		});
 
	}
 
}
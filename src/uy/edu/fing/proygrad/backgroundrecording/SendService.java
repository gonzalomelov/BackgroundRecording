package uy.edu.fing.proygrad.backgroundrecording;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.RemoteViews;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.LiveCard.PublishMode;


public class SendService extends Service {

	 public static final String TAG = "SendService";


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        
    	//Log.e(TAG, "servicio on start");
    	/*
    	
    	 File dir = new File(Environment.getExternalStoragePublicDirectory(
                 Environment.DIRECTORY_PICTURES), "BackgroundRecording");
         // This location works best if you want the created images to be shared
         // between applications and persist after your app has been uninstalled.

         // Create the storage directory if it does not exist
         if (!dir.exists()){
             if (!dir.mkdirs()) {
                 Log.d("BackgroundRecording", "failed to create directory");
                 return -100;
             }
         }
         
         String url = "http://169.254.123.41:8080/server/rest/services/upload";
         for(File file : dir.listFiles()){
        	 try {
        		    HttpClient httpclient = new DefaultHttpClient();

        		    HttpPost httppost = new HttpPost(url);

        		    InputStreamEntity reqEntity = new InputStreamEntity(
        		            new FileInputStream(file), -1);
        		    reqEntity.setContentType("binary/octet-stream");
        		    reqEntity.setChunked(true); // Send in multiple parts if needed
        		    httppost.setEntity(reqEntity);
        		    HttpResponse response = httpclient.execute(httppost);
        		    //Do something with response...

        		} catch (Exception e) {
        		    // show error
        		}
        	 
        	 
         }
        
*/
        return START_STICKY;
    }

    
    @Override
    public void onCreate() {

    	Log.e(TAG, "servicio send onCreate");
    	
    	new SendPostTask().execute();
       	 
       	 
       
       

    }
    
    @Override
    public void onDestroy() {
    	Log.e(TAG, "onDestroy");
    	
        super.onDestroy();
    }
    
    private class SendPostTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
                // Make your request POST here. Example:
        	File dir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "BackgroundRecording");
            // This location works best if you want the created images to be shared
            // between applications and persist after your app has been uninstalled.

            // Create the storage directory if it does not exist
            if (!dir.exists()){
                if (!dir.mkdirs()) {
                    Log.d("BackgroundRecording", "failed to create directory");
                   
                }
            }
            
            String url = "http://192.168.1.42:8080/server/rest/services/upload";
            String url2 = "http://192.168.1.42:8080/server/rest/services/get";
            
            Log.e(TAG, "Antes for");
            for(File file : dir.listFiles()){
           	 try {
           		 Log.e(TAG, "Dentro for");
           		 Log.e(TAG, file.getName());
           		 
           		    HttpClient httpclient = new DefaultHttpClient();

           		    HttpPost httppost = new HttpPost(url);
           		    HttpGet get = new HttpGet(url2);
           		    
           		    InputStreamEntity reqEntity = new InputStreamEntity(
           		            new FileInputStream(file), -1);
           		    reqEntity.setContentType("binary/octet-stream");
           		    reqEntity.setChunked(true); // Send in multiple parts if needed
           		    httppost.setEntity(reqEntity);
           		    HttpResponse response = httpclient.execute(httppost);
           		 HttpResponse response2 = httpclient.execute(get);
           		    //Do something with response...
           		 Log.e(TAG, String.valueOf(response.getStatusLine().getStatusCode()));
           		Log.e(TAG, response.getStatusLine().getReasonPhrase());
           		} catch (Exception e) {
           		    // show error
           		}
            }
                return null;
                
        }

        protected void onPostExecute(Void result) {
          // Do something when finished.
        }
    }
}

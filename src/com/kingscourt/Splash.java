package com.kingscourt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_splash);
		Thread timer = new Thread(){
			public void run(){
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					Intent startMain = new Intent("com.kingscourt.Menu");
					startActivity(startMain);
				}
			}
		};
		timer.start();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		finish();
	}
	
	@Override
	protected void onResume() {
	    // TODO Auto-generated method stub
	    super.onResume();

	    new AsyncCaller().execute();

	}

	private class AsyncCaller extends AsyncTask<Void, Void, Void>{

	    private int downloadedSize;
		private int totalSize;

		@Override
	    protected void onPreExecute() {
	        super.onPreExecute();

	        //this method will be running on UI thread
//	        showMsg("Loading files...");
//	        showMsg("Please Wait...");
	    }
	    @Override
	    protected Void doInBackground(Void... params) {

	        //this method will be running on background thread so don't update UI frome here
	        //do your long running http tasks here,you dont want to pass argument and 
	    	//u can access the parent class' variable url over here

	    	String [] files = {"events.xml", "contacts.xml", "pdesk.xml"};
	    	String [] file_paths = {"https://dl.dropboxusercontent.com/s/stafz8hfyfsijkj/events.xml?dl=1&token_hash=AAEOufG1Dfmb6JrFmOKa_Dq5h-Z9NPzS-CyviWfX4gnOkw", 
	    							"https://dl.dropboxusercontent.com/s/40314f9cbxb08h5/contacts.xml?dl=1&token_hash=AAH6FtZxmxqXutCH_9hJcts_f01V4CGbeQbATbnbZkL0QA", 
	    							"https://dl.dropboxusercontent.com/s/r55i2dabgcwxa3j/pdesk.xml?dl=1&token_hash=AAGIwCOL79lVTVt-BANfFZN7Sf7T7EE46J4J5LwF4oIt2Q"};
	    	
	    	for(int i =0; i < files.length; i++){
	    		try {
	    			downloadedSize = 0;
	    			totalSize = 0;
	    			String dwnload_file_path = file_paths[i];
	    			
	    			URL url = new URL(dwnload_file_path);
	    			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	    			
	    			urlConnection.setRequestMethod("GET");
	    			urlConnection.setReadTimeout(10000); // millis
	    			urlConnection.setConnectTimeout(15000); // millis
	    			urlConnection.setDoOutput(true);
	            //	urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
	            
	            //connect
	    			urlConnection.connect();
	    			
	            //	isExternalStorageWritable();
	    			
	            //	set the path where we want to save the file
	    			Context context = getApplicationContext();
	    			File SDCardRoot =  Environment.getExternalStorageDirectory();
	            //	create a new file, to save the downloaded file
	    			File file = new File(SDCardRoot,"Android/data/"+files[i]);	            
	    			FileOutputStream fileOutput = new FileOutputStream(file);
	    			
	            //	Stream used for reading the data from the internet
	    			InputStream inputStream = urlConnection.getInputStream();
	    			
	            //	this is the total size of the file which we are downloading
	    			totalSize = urlConnection.getContentLength();
	    			
	    			runOnUiThread(new Runnable() {
	    				public void run() {
	                    //	pb.setMax(totalSize);
	    				}               
	    			});
	    			
	            //	create a buffer...
	    			byte[] buffer = new byte[1024];
	    			int bufferLength = 0;
	 
	    			while ((bufferLength = inputStream.read(buffer)) > 0 ) {
	    				fileOutput.write(buffer, 0, bufferLength);
	    				downloadedSize += bufferLength;
	                // 	update the progressbar //
	    				runOnUiThread(new Runnable() {
	    					public void run() {
	                        //	pb.setProgress(downloadedSize);
	                        //	float per = ((float)downloadedSize/totalSize) * 100;
	                        //	cur_val.setText("Downloaded " + downloadedSize + "KB / " + totalSize + "KB (" + (int)per + "%)" );
	    					}
	    				});
	    			}
	            //	close the output stream when complete //
	    			fileOutput.close();
	    			runOnUiThread(new Runnable() {
	    				public void run() {
	                    // 	pb.dismiss(); // if you want close it..
	    				}
	    			});         
	    			
	    		} catch (final MalformedURLException e) {
	         //   	showError("Error : MalformedURLException " + e);        
	    			e.printStackTrace();
	    		} catch (final IOException e) {
	           // 	showError("Error : IOException " + e);          
	    			e.printStackTrace();
	    		}
	    		catch (final Exception e) {
	            //	showError("Error : Please check your internet connection " + e);
	    		}
	    	}
	        return null;
	    }

	    @Override
	    protected void onPostExecute(Void result) {
	        super.onPostExecute(result);

	        //this method will be running on UI thread
	        //pdLoading.dismiss();
	        
	        if(isNetworkConnected() == true){
	        	showMsg("Ready");
	        }else{
	        	showMsg("No Internet Connection");
	        }
	    }

	}
	
	private boolean isNetworkConnected() {
		  ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		  NetworkInfo ni = cm.getActiveNetworkInfo();
		  if (ni == null) {
		   // There are no active networks.
		   return false;
		  } else
		   return true;
		 }
	
	void showMsg(final String msg){
        runOnUiThread(new Runnable() {
            public void run() {
                Toast toast= Toast.makeText(getApplicationContext(), 
                		msg, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });
    }
}

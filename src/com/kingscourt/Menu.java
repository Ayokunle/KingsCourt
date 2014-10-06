package com.kingscourt;

import java.util.Calendar;

//import org.joda.time.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;


public class Menu extends Activity {
	
	private static final int SETTINGS_RESULT = 1;
	
	Button about, contact, s_times;
	Button events, pastor, settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_menu);
		
		about = (Button) findViewById(R.id.about_button);
		events = (Button) findViewById(R.id.events_button);
		contact = (Button) findViewById(R.id.contact_button);
		s_times = (Button) findViewById(R.id.service_button);
		pastor =  (Button) findViewById(R.id.pastor_button);
		settings = (Button) findViewById(R.id.settings_button);
		
		Typeface tf = Typeface.createFromAsset(getAssets(),"Aller_Rg.ttf");
		
		about.setTypeface(tf);
		events.setTypeface(tf);
		contact.setTypeface(tf);
		s_times.setTypeface(tf);
		pastor.setTypeface(tf);
		settings.setTypeface(tf);
		
		
		events.setBackgroundResource(R.drawable.menu_button);
		about.setBackgroundResource(R.drawable.menu_button);
		contact.setBackgroundResource(R.drawable.menu_button);
		s_times.setBackgroundResource(R.drawable.menu_button);
		pastor.setBackgroundResource(R.drawable.menu_button);
		settings.setBackgroundResource(R.drawable.menu_button);		
		
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		int widthPixels = metrics.widthPixels;
		int heightPixels = metrics.heightPixels;
		
		float scaleFactor = metrics.density;
		float widthDp = widthPixels / scaleFactor;
		float heightDp = heightPixels / scaleFactor;
		float smallestWidth = Math.min(widthDp, heightDp);
		
		DisplayMetrics dm = new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(dm);
	    double x = Math.pow(dm.widthPixels/dm.xdpi,2);
	    double y = Math.pow(dm.heightPixels/dm.ydpi,2);
	    double screenInches = Math.sqrt(x+y);
		
		ImageView rccg_logo = new ImageView(this);
		ImageView kc_logo = new ImageView(this);
		
		rccg_logo.setImageResource(R.drawable.rccg_logo);
		kc_logo.setImageResource(R.drawable.kc_logo);
		
		LayoutParams lp = new LayoutParams(70, 70);
		
		if (smallestWidth >= 720) {
		    //Device is a 10" tablet
			LinearLayout ll = (LinearLayout)findViewById(R.id.header);
			lp = new LayoutParams(130, 130);
			ll.addView(rccg_logo, lp);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, 130);
			ll.addView(kc_logo, lp);
			
		}else if (smallestWidth >= 600) {
		    //Device is a 7" tablet
			LinearLayout ll = (LinearLayout)findViewById(R.id.header);
			lp = new LayoutParams(120, 120);
			ll.addView(rccg_logo, lp);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, 120);
			ll.addView(kc_logo, lp);
			
		}else if (smallestWidth >= 480) {
		    //Device is a 7" tablet/ 4" phone
			LinearLayout ll = (LinearLayout)findViewById(R.id.header);
			lp = new LayoutParams(100, 100);
			ll.addView(rccg_logo, lp);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, 100);
			ll.addView(kc_logo, lp);
			
		}else if (smallestWidth >= 240) {
		    //Device is a 4" phone
			LinearLayout ll = (LinearLayout)findViewById(R.id.header);
			lp = new LayoutParams(100, 90);
			ll.addView(rccg_logo, lp);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, 90);
			ll.addView(kc_logo, lp);
		}
	}
	
	public void onEventsClick(final View v){
		// on UI thread
		events.setBackgroundResource(R.drawable.meun_button_pressed);
	    // launch concurrent thread
	    (new Thread(new Runnable() {
	            @Override
	            // the code of this method will run concurrently
	            public void run() {
	                try {
	                	Thread.sleep(1000/2);
	                } catch (InterruptedException e) {}
	                // at this point, we need to get back to the UI thread
	                // for simplicity, we use the view's convenience method for that
	                v.post(new Runnable() {
	                    @Override
	                    // the code of this method will run on the UI thread
	                    public void run() {
	                    	events.setBackgroundResource(R.drawable.menu_button);
	                    }
	                });
	            }
	        })
	    ).start();
		Intent startMain = new Intent(this, Events.class);
		startActivity(startMain);
    }
	
	public void onPastorClick(final View v){
		// on UI thread
		pastor.setBackgroundResource(R.drawable.meun_button_pressed);
	    // launch concurrent thread
	    (new Thread(new Runnable() {
	            @Override
	            // the code of this method will run concurrently
	            public void run() {
	                try {
	                	Thread.sleep(1000/2);
	                } catch (InterruptedException e) {}
	                // at this point, we need to get back to the UI thread
	                // for simplicity, we use the view's convenience method for that
	                v.post(new Runnable() {
	                    @Override
	                    // the code of this method will run on the UI thread
	                    public void run() {
	                    	pastor.setBackgroundResource(R.drawable.menu_button);
	                    }
	                });
	            }
	        })
	    ).start();
		Intent startMain = new Intent(this, Pastor.class);
		startActivity(startMain);
    }
	
	public void onAboutClick(final View v){
		// on UI thread
		about.setBackgroundResource(R.drawable.meun_button_pressed);
	    // launch concurrent thread
	    (new Thread(new Runnable() {
	            @Override
	            // the code of this method will run concurrently
	            public void run() {
	                try {
	                	Thread.sleep(1000/2);
	                } catch (InterruptedException e) {}
	                // at this point, we need to get back to the UI thread
	                // for simplicity, we use the view's convenience method for that
	                v.post(new Runnable() {
	                    @Override
	                    // the code of this method will run on the UI thread
	                    public void run() {
	                    	about.setBackgroundResource(R.drawable.menu_button);
	                    }
	                });
	            }
	        })
	    ).start();
		Intent startMain = new Intent(this, About.class);
		startActivity(startMain);
    }
	
	public void onContactClick(final View v){
		// on UI thread
		contact.setBackgroundResource(R.drawable.meun_button_pressed);
	    // launch concurrent thread
	    (new Thread(new Runnable() {
	            @Override
	            // the code of this method will run concurrently
	            public void run() {
	                try {
	                	Thread.sleep(1000/2);
	                } catch (InterruptedException e) {}
	                // at this point, we need to get back to the UI thread
	                // for simplicity, we use the view's convenience method for that
	                v.post(new Runnable() {
	                    @Override
	                    // the code of this method will run on the UI thread
	                    public void run() {
	                    	contact.setBackgroundResource(R.drawable.menu_button);
	                    }
	                });
	            }
	        })
	    ).start();
		Intent startMain = new Intent(this, Contact.class);
		startActivity(startMain);
    }
	
	public void onServiceClick(final View v){
		// on UI thread
		s_times.setBackgroundResource(R.drawable.meun_button_pressed);
	    // launch concurrent thread
	    (new Thread(new Runnable() {
	            @Override
	            // the code of this method will run concurrently
	            public void run() {
	                try {
	                    Thread.sleep(1000/2);
	                } catch (InterruptedException e) {}
	                // at this point, we need to get back to the UI thread
	                // for simplicity, we use the view's convenience method for that
	                v.post(new Runnable() {
	                    @Override
	                    // the code of this method will run on the UI thread
	                    public void run() {
	                    	s_times.setBackgroundResource(R.drawable.menu_button);
	                    }
	                });
	            }
	        })
	    ).start();
		Intent startMain = new Intent(this, Times.class);
		startActivity(startMain);
    }
	
	public void onSettingsClick(final View v){
		// on UI thread
		settings.setBackgroundResource(R.drawable.meun_button_pressed);
	    // launch concurrent thread
	    (new Thread(new Runnable() {
	            @Override
	            // the code of this method will run concurrently
	            public void run() {
	                try {
	                    Thread.sleep(1000/2);
	                } catch (InterruptedException e) {}
	                // at this point, we need to get back to the UI thread
	                // for simplicity, we use the view's convenience method for that
	                v.post(new Runnable() {
	                    @Override
	                    // the code of this method will run on the UI thread
	                    public void run() {
	                    	settings.setBackgroundResource(R.drawable.menu_button);
	                    }
	                });
	            }
	        })
	    ).start();
	    //showMsg("This button is not ready yet.");
		Intent startMain = new Intent(this, Preferences.class);
		startActivity(startMain);
//		finish();
    }
	
	@Override
	public boolean onKeyDown(int keycode, KeyEvent e) {
	    switch(keycode) {
	        case KeyEvent.KEYCODE_MENU:
	            //doSomething();
	        	//Splash s = new Splash();
	        	//showMsg("Menu");
	        	Intent startMain = new Intent(this, Preferences.class);
	    		startActivity(startMain);
	            return true;
	    }
	    return super.onKeyDown(keycode, e);
	}
	
	void showMsg(final String msg){
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(Menu.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
	
public static final String PREFS_NAME = "welcome_note";
    
	/** Called when the activity is brought to front. */
    @Override
    protected void onResume() {
    	AlertDialog.Builder adb = new AlertDialog.Builder(this);
    	if(true){
    		adb.setTitle("Welcome!");
    		adb.setMessage("Thank you for downloading this application.\n \n" +
    				"Update: \n" +
    				"• No more error message when phone is turned on.\n" +
    				"Removed Feature: \n" +
    				"• The reminder feature has been removed due to the error messages.\n" +
    				"This application:\n" +
    				"• Allows you to read the monthly messages from the Pastor.\n" +
    				"• Updates you on local, national and world-wide church events.\n" +
    				"• Provides information such as service times and contact numbers.\n" +
    				"\n" +
    				"Some parts of this application needs internet access to work." +
    				"\n\n"+
    				"We hope that you enjoy using this application.\nGod bless.\n");
    		adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    String checkBoxResult = "NOT checked";
                    if (true){
                        checkBoxResult = "checked";
                    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    	SharedPreferences.Editor editor = settings.edit();
                    	editor.putString("welcome_1", checkBoxResult);
                    	//Commit the edits!
                    	editor.commit();
                    	return;
                    }
                }
            });
    	}
    	
    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String skipMessage = settings.getString("welcome_1", "NOT checked");
        if (!skipMessage.equals("checked")){
        	adb.show();
        }
        
        super.onResume();
    }
}
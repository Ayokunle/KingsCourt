package com.kingscourt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.Preference;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class Preferences extends PreferenceActivity {

	static boolean settings = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setTitle("Send Feedback");
		super.onCreate(savedInstanceState);
		

		addPreferencesFromResource(R.xml.prefs);
		
		final Context context = this;
		
		Preference button = (Preference)findPreference("button");
		button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
		                @Override
		                public boolean onPreferenceClick(Preference arg0) { 
		                	ImageView image = new ImageView(context);
		                    image.setImageResource(R.drawable.rccg_logo);
		                    LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams(150,150);
		                    layoutParams.setMargins(Gravity.CENTER, 10, Gravity.CENTER, 10);
		                    image.setLayoutParams(layoutParams);
		                    
		                	AlertDialog.Builder builder = new AlertDialog.Builder(context);

		                	// set title
		                	builder.setTitle("King's Court Dublin");

		                	// set dialog message
		                	
		                	TextView msg = new TextView(context);
		                	msg.setText("Version 1.1 \n Developer: Ayokunle Adeosun \n adeosua@tcd.ie ");
		                	msg.setGravity(Gravity.CENTER_HORIZONTAL);
		                	
		                	LinearLayout layout = new LinearLayout(getApplicationContext());
		                    layout.setOrientation(LinearLayout.VERTICAL);
		                    layout.setGravity(Gravity.CENTER);
		                    
		                    layout.addView(image);
		                    
		                    LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		                    lp.setMargins(Gravity.CENTER, 10, Gravity.CENTER, 10);
		                    
		                    layout.addView(msg, lp);
		                    
		                	builder.setView(layout);
		                	
		                	//create alert dialog
		                	AlertDialog alertDialog = builder.create();

		                	//show it
		                	alertDialog.show();
		                    return true;
		                }
		            });
		
		Preference feedback = (Preference)findPreference("feedback");
		feedback.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
		                @Override
		                public boolean onPreferenceClick(Preference arg0) {
		                	Intent startMain = new Intent(context, Feedback.class);
		    	    		startActivity(startMain);
		                	return false;
		                	
		                }
		});
		
		final String PREFS_NAME = "welcome_note";
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
	
//	@Override
//	public boolean onKeyDown(int keycode, KeyEvent e) {
//	    switch(keycode) {
//	        case KeyEvent.KEYCODE_BACK:
//	            //doSomething();
//	        	//Splash s = new Splash();
//	        	//showMsg("Menu");
//	        	settings = true;
////	        	Intent startMain = new Intent(this, Menu.class);
////	    		startActivity(startMain);
////	    		finish();
//	            return true;
//	    }
//	    return super.onKeyDown(keycode, e);
//	}
}

package com.kingscourt;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Times extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		setContentView(R.layout.activity_times);
		
		TextView tv = (TextView) findViewById(R.id.textView1);
		Typeface type = Typeface.createFromAsset(getAssets(),"PTC75F.ttf"); 
		tv.setTypeface(type);
		
		type = Typeface.createFromAsset(getAssets(),"Aller_Rg.ttf"); 
		
		View v = new View(this);	
		v.setBackgroundResource(R.color.white);
		
		View v2 = new View(this);
		v2.setBackgroundResource(R.color.white);
		
		View v3 = new View(this);
		v3.setBackgroundResource(R.color.white);
		
		View v4 = new View(this);
		v4.setBackgroundResource(R.color.white);
		
		View v5 = new View(this);
		v5.setBackgroundResource(R.color.white);
		
		View v6 = new View(this);
		v6.setBackgroundResource(R.color.white);
		
		LayoutParams view_lp = new LayoutParams(LayoutParams.FILL_PARENT, 5);
		view_lp.height = 1;
		view_lp.setMargins(Gravity.CENTER, 5, Gravity.CENTER, 5);
		
		TextView tv2 = new TextView(this);
		TextView tv3 = new TextView(this);
		TextView tv4 = new TextView(this);
		TextView tv5 = new TextView(this);
		TextView tv6 = new TextView(this);
		TextView tv7 = new TextView(this);
		TextView tv8 = new TextView(this);
		
		tv2.setTypeface(type);
		tv3.setTypeface(type);
		tv4.setTypeface(type);
		tv5.setTypeface(type);
		tv6.setTypeface(type);
		tv7.setTypeface(type);
		tv8.setTypeface(type);
		
		tv2.setText("Sunday\nFresh Anointing – 9:00 AM – 9:30 AM\nSunday School – 9:30 AM – 10:30 AM\nWorship Service – 10:30 AM – 1:30 PM");
		tv3.setText("Tuesday\nDigging Deep (Bible Study)\n6:30 PM – 8:00 PM");
		tv4.setText("First Wednesday of Every Month\nMorning Glow\n5:30AM - 6:30AM");
		tv5.setText("Thursday\nFaith Clinic\n6:30 PM - 7:30 PM");
		tv6.setText("First Sunday Of Every Month\nThanksgiving Service\n10:30 AM – 1:30 PM");
		tv7.setText("Last day of Every Month\nCrossover Evening\n6:00 PM – 7:00 PM");
		tv8.setText("Third Weekend of January, April, July & October\nOvercomers’ Meeting\nFriday : 6:00 PM – 8:00 PM\nSaturday : 6:00 PM – 8:00 PM\nSunday : 11:30 AM – 1:30 PM");
		
		Context context = this;
		tv2.setTextColor(context.getResources().getColor(R.color.white));
		tv3.setTextColor(context.getResources().getColor(R.color.white));
		tv4.setTextColor(context.getResources().getColor(R.color.white));
		tv5.setTextColor(context.getResources().getColor(R.color.white));
		tv6.setTextColor(context.getResources().getColor(R.color.white));
		tv7.setTextColor(context.getResources().getColor(R.color.white));
		tv8.setTextColor(context.getResources().getColor(R.color.white));
		
		tv2.setGravity(Gravity.LEFT);
		tv3.setGravity(Gravity.LEFT);
		tv4.setGravity(Gravity.LEFT);
		tv5.setGravity(Gravity.LEFT);
		tv6.setGravity(Gravity.LEFT);
		tv7.setGravity(Gravity.LEFT);
		tv8.setGravity(Gravity.LEFT);
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		int widthPixels = metrics.widthPixels;
		int heightPixels = metrics.heightPixels;
		
		float scaleFactor = metrics.density;
		float widthDp = widthPixels / scaleFactor;
		float heightDp = heightPixels / scaleFactor;
		
		float smallestWidth = Math.min(widthDp, heightDp);

		if (smallestWidth > 720) {
		    //Device is a 10" tablet
			tv2.setTextSize(25);
			tv3.setTextSize(25);
			tv4.setTextSize(25);
			tv5.setTextSize(25);
			tv6.setTextSize(25);
			tv7.setTextSize(25);
			tv8.setTextSize(25);
			
		}else if (smallestWidth > 600) {
		    //Device is a 7" tablet
			tv2.setTextSize(20);
			tv3.setTextSize(20);
			tv4.setTextSize(20);
			tv5.setTextSize(20);
			tv6.setTextSize(20);
			tv7.setTextSize(20);
			tv8.setTextSize(20);
			
		}else if (smallestWidth > 480) {
		    //Device is a 7" tablet/ 4" phone
			tv2.setTextSize(20);
			tv3.setTextSize(20);
			tv4.setTextSize(20);
			tv5.setTextSize(20);
			tv6.setTextSize(20);
			tv7.setTextSize(20);
			tv8.setTextSize(20);
			
		}else if (smallestWidth >= 240) {
		    //Device is a 7" tablet/ 4" phone
			tv2.setTextSize(15);
			tv3.setTextSize(15);
			tv4.setTextSize(15);
			tv5.setTextSize(15);
			tv6.setTextSize(15);
			tv7.setTextSize(15);
			tv8.setTextSize(15);
			
		}
		
		LinearLayout ll = (LinearLayout)findViewById(R.id.events_layout);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lp.setMargins(Gravity.CENTER, 0, Gravity.CENTER, 10);
		
		ll.addView(tv2, lp);
		ll.addView(v, view_lp);
		ll.addView(tv3, lp);
		ll.addView(v2, view_lp);
		ll.addView(tv4, lp);
		ll.addView(v3, view_lp);
		ll.addView(tv5, lp);
		ll.addView(v4, view_lp);
		ll.addView(tv6, lp);
		ll.addView(v5, view_lp);
		ll.addView(tv7, lp);
		ll.addView(v6, view_lp);
		ll.addView(tv8, lp);
		
		ImageView rccg_logo = new ImageView(this);
		ImageView kc_logo = new ImageView(this);
		
		rccg_logo.setImageResource(R.drawable.rccg_logo);
		kc_logo.setImageResource(R.drawable.kc_logo);
		
		if (smallestWidth >= 720) {
		    //Device is a 10" tablet
			ll = (LinearLayout)findViewById(R.id.header);
			lp = new LayoutParams(130, 130);
			ll.addView(rccg_logo, lp);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, 130);
			ll.addView(kc_logo, lp);
			
		}else if (smallestWidth >= 600) {
		    //Device is a 7" tablet
			ll = (LinearLayout)findViewById(R.id.header);
			lp = new LayoutParams(120, 120);
			ll.addView(rccg_logo, lp);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, 120);
			ll.addView(kc_logo, lp);
			
		}else if (smallestWidth >= 480) {
		    //Device is a 7" tablet/ 4" phone
			ll = (LinearLayout)findViewById(R.id.header);
			lp = new LayoutParams(100, 100);
			ll.addView(rccg_logo, lp);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, 100);
			ll.addView(kc_logo, lp);
			
		}else if (smallestWidth >= 240) {
		    //Device is a 4" phone
			ll = (LinearLayout)findViewById(R.id.header);
			lp = new LayoutParams(100, 90);
			ll.addView(rccg_logo, lp);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, 90);
			ll.addView(kc_logo, lp);
		}
		
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
}
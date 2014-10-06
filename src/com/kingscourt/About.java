package com.kingscourt;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class About extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		setContentView(R.layout.activity_about);
		
		TextView tv_1 = (TextView) findViewById(R.id.textView1);
		Typeface type = Typeface.createFromAsset(getAssets(),"PTC75F.ttf"); 
		tv_1.setTypeface(type);
		
		TextView tv = (TextView) findViewById(R.id.textView2);
		type = Typeface.createFromAsset(getAssets(),"Aller_Rg.ttf"); 
		tv.setTypeface(type);
		
		String x = "King’s Court Dublin, " +
				"is a mission of The Redeemed Christian Church of God (RCCG). " +
				"A parish established by an unusual God for a privileged people " +
				"in a very strange environment where they encounter Him in an unusual way. \n \n";
		
		String y = "As a Bible believing and God fearing Church and in line with the " +
				"mission and mandate of RCCG, we teach and encourage a life of " +
				"holiness. The Word from His altar in King’s Court is to establish, " +
				"teach and impart people to excel in all areas of their lives while " +
				"building them up for God’s kingdom.\n \n" +
				"King's Court was founded as a " +
				"church plant of Life Sanctuary, a parish of the Redeemed Christian " +
				"Church of God (RCCG), in Dublin, Ireland in 23rd February 2003, and the " +
				"progress we have made since those humble beginnings serves as a constant " +
				"reminder that Jehovah is indeed an unusual God.\n \n" +
				"Our passion for the growth," +
				" development and the well-being of the memebers is reflected in the vision " +
				"of the Church which  can be found in Philipians 3:10 ....to know Him " +
				"(Christ) and also make Him known.  We are therefore irrevoceably committed " +
				"to making a positive impact in not only our local community but also " +
				"society at large.\n \n" +
				"Whatever your background, culture or race, at King's " +
				"Court you can find a home for yourself and your family. So join us as we " +
				"‘pursue God, discover our purpose, maximise our potential while impacting " +
				"other lives’.";
		tv.setText(x+y);
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		int widthPixels = metrics.widthPixels;
		int heightPixels = metrics.heightPixels;
		
		float scaleFactor = metrics.density;
		float widthDp = widthPixels / scaleFactor;
		float heightDp = heightPixels / scaleFactor;
		float smallestWidth = Math.min(widthDp, heightDp);
		
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
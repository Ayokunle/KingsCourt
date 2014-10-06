package com.kingscourt;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Pastor extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_pastor);
				
		TextView tv_1 = (TextView) findViewById(R.id.textView1);
		Typeface type = Typeface.createFromAsset(getAssets(),"PTC75F.ttf"); 
		tv_1.setTypeface(type);
		
		TextView tv_ = (TextView) findViewById(R.id.textView2);
		type = Typeface.createFromAsset(getAssets(),"Aller_Rg.ttf"); 
		tv_.setTypeface(type);
		
		TextView tv_2 = (TextView) findViewById(R.id.txtStatus);
		tv_2.setTypeface(type);
		
		Context context = this;
		File dir =  Environment.getExternalStorageDirectory();
    	File file = new File(dir, "Android/data/pdesk.xml");
    	Date d = new Date(file.lastModified());
    	String ld = "Last Update: " + d.toString();
    	
    	TextView textv = (TextView) findViewById(R.id.update);
    	textv.setText(ld);
		
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
		ImageView pastor =  new ImageView (this);
		
		XMLReader xml = new XMLReader();
		
		try {
			tv_.setText(xml.getPastorTitle(this));
			String text = xml.getPastorMessage(this);
			tv_2.setText(text);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		rccg_logo.setImageResource(R.drawable.rccg_logo);
		kc_logo.setImageResource(R.drawable.kc_logo);
		pastor.setImageResource(R.drawable.pastor);
		
		LayoutParams lp = new LayoutParams(70, 70);
		
		if (smallestWidth >= 720) {
		    //Device is a 10" tablet
			LinearLayout ll = (LinearLayout)findViewById(R.id.header);
			lp = new LayoutParams(130, 130);
			ll.addView(rccg_logo, lp);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, 130);
			ll.addView(kc_logo, lp);
			RelativeLayout rl = (RelativeLayout)findViewById(R.id.pastor_header);
			lp = new LayoutParams(220, 220);
			rl.addView(pastor, lp);
			
		}else if (smallestWidth >= 600) {
		    //Device is a 7" tablet
			LinearLayout ll = (LinearLayout)findViewById(R.id.header);
			lp = new LayoutParams(120, 120);
			ll.addView(rccg_logo, lp);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, 120);
			ll.addView(kc_logo, lp);
			RelativeLayout rl = (RelativeLayout)findViewById(R.id.pastor_header);
			lp = new LayoutParams(200, 200);
			rl.addView(pastor, lp);
			
		}else if (smallestWidth >= 480) {
		    //Device is a 7" tablet/ 4" phone
			LinearLayout ll = (LinearLayout)findViewById(R.id.header);
			lp = new LayoutParams(100, 100);
			ll.addView(rccg_logo, lp);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, 100);
			ll.addView(kc_logo, lp);
			RelativeLayout rl = (RelativeLayout)findViewById(R.id.pastor_header);
			lp = new LayoutParams(200, 200);
			rl.addView(pastor, lp);

		}else if (smallestWidth >= 240) {
		    //Device is a 4" phone
			LinearLayout ll = (LinearLayout)findViewById(R.id.header);
			lp = new LayoutParams(100, 90);
			ll.addView(rccg_logo, lp);
			lp = new LayoutParams(LayoutParams.MATCH_PARENT, 90);
			ll.addView(kc_logo, lp);
			RelativeLayout rl = (RelativeLayout)findViewById(R.id.pastor_header);
			lp = new LayoutParams(170, 170);
			rl.addView(pastor, lp);
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
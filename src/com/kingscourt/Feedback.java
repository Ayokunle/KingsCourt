package com.kingscourt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class Feedback extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		setContentView(R.layout.feedback_layout);
		
		final Context context = this;
		
		Button send = (Button)findViewById(R.id.send_feedback);
		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "works ", Toast.LENGTH_LONG).show();
				EditText name = (EditText) findViewById(R.id.user_name);
				String x = name.getText().toString();
				
				EditText feedback = (EditText) findViewById(R.id.user_feedback);
				String y = feedback.getText().toString();
				
				if(x.equals("")){
					x = "Anonymous";
				}
				if(y.equals("")){
					y = "No Message";
				}
				
				/* Create the Intent */
				final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

				/* Fill it with Data */
				emailIntent.setType("plain/text");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"adeosua@tcd.ie"});
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "King's Court App Feedback");
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "From: "+x+"\n\n"+y);

				/* Send it off to the Activity-Chooser */
				context.startActivity(Intent.createChooser(emailIntent, "Send feedback..."));
				finish();
			}
		});
		
		TextView tv_1 = (TextView) findViewById(R.id.textView1);
		Typeface type = Typeface.createFromAsset(getAssets(),"PTC75F.ttf"); 
		tv_1.setTypeface(type);
		
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
	}
}

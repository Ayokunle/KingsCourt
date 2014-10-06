package com.kingscourt;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class Contact extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		setContentView(R.layout.activity_contact_us);
		
		TextView tv = (TextView) findViewById(R.id.textView1);
		Typeface type = Typeface.createFromAsset(getAssets(),"PTC75F.ttf"); 
		tv.setTypeface(type); //map_button
		
		
		Typeface tf = Typeface.createFromAsset(getAssets(),"Aller_Rg.ttf");
		
		TextView tv2 = (TextView) findViewById(R.id.textView2);
		tv2.setGravity(Gravity.CENTER);
		tv2.setEms(20);
		tv2.setTypeface(tf);
		
		Context context = this;
		
		XMLReader xml = new XMLReader();
		int x = 1;
		
		Button map = new Button(this);
		map.setText("View In Google Maps");
		map.setGravity(Gravity.CENTER);
		map.setTypeface(tf);
		map.setTextColor(context.getResources().getColor(R.color.white));
		map.setBackgroundResource(R.drawable.map_shape);
		map.setOnClickListener(new Button.OnClickListener() {  
	        public void onClick(View v)
            {
                //perform action
	        	onMapClick(v);
	        	
            }
         });

		Button pastor = new Button(this);
		pastor.setText("Parish Pastor" + "\n"+ "Pastor Segun Gbadebo" 
					+ "\n" + "087 924 1730" + "\n" + "pastor@rccgkingscourt.org");
		pastor.setGravity(Gravity.LEFT);
		pastor.setTypeface(tf);
		pastor.setTextColor(context.getResources().getColor(R.color.white));
		pastor.setBackgroundResource(R.drawable.button_shape);
		pastor.setOnClickListener(new Button.OnClickListener() {  
	        public void onClick(View v){
	        	 new AlertDialog.Builder(Contact.this)
	             .setTitle("Contact Pastor")
	             .setPositiveButton("Email",
	                     new DialogInterface.OnClickListener() {
	                         public void onClick(DialogInterface dialog, int id) {
	                        	 Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
	    	                	 emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"pastor@rccgkingscourt.org"});
	    	                	 emailIntent.setType("text/plain");
	    	                	 startActivity(Intent.createChooser(emailIntent, "Send a mail ..."));
	    	                     dialog.cancel(); 
	                         }
	                     })
	             .setNegativeButton("Call", new DialogInterface.OnClickListener() {
	                 public void onClick(DialogInterface dialog, int id) {
	                	//perform action
      		        	Intent dial = new Intent();
     	            	dial.setAction("android.intent.action.DIAL");
     	            	dial.setData(Uri.parse("tel:"+ "087 924 1730"));
     	            	startActivity(dial);
                        dialog.cancel();
	                 }
	             }).show();
            }
         });
		
		Button youth = new Button(this);
		youth.setText("Youth Fellowship\nDaniel Generation" + "\n"+ "Asst. Pastor Solomon" 
					+ "\n" + "086 083 0191");
		youth.setGravity(Gravity.LEFT);
		youth.setTypeface(tf);
		youth.setTextColor(context.getResources().getColor(R.color.white));
		youth.setBackgroundResource(R.drawable.button_shape);
		youth.setOnClickListener(new Button.OnClickListener() {  
	        public void onClick(View v){
	        	 new AlertDialog.Builder(Contact.this)
	             .setTitle("Youth Fellowship")
	             .setPositiveButton("Visit Facebook Page",
	                     new DialogInterface.OnClickListener() {
	                         public void onClick(DialogInterface dialog, int id) {
	                        	 String url = "http://goo.gl/wAQBJn";
	                        	 Intent i = new Intent(Intent.ACTION_VIEW);
	                        	 i.setData(Uri.parse(url));
	                        	 startActivity(i);
	    	                     dialog.cancel(); 
	                         }
	                     })
	             .setNegativeButton("Call", new DialogInterface.OnClickListener() {
	                 public void onClick(DialogInterface dialog, int id) {
	                	//perform action
      		        	Intent dial = new Intent();
     	            	dial.setAction("android.intent.action.DIAL");
     	            	dial.setData(Uri.parse("tel:"+ "086 083 0191"));
     	            	startActivity(dial);
                        dialog.cancel();
	                 }
	             }).show();
            }
         });
		
		LinearLayout ll = (LinearLayout)findViewById(R.id.events_layout);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		
		lp.setMargins(Gravity.CENTER, 10, Gravity.CENTER, 10);
		ll.addView(map, lp);
		
		lp.setMargins(Gravity.CENTER, 10, Gravity.CENTER, 10);
		ll.addView(pastor, lp);
		
		try {
			x = xml.getNumContacts(this);
			
			Button [] contact_buttons = new Button[x];
			for(int i =0; i < x; i++){
				contact_buttons[i] = new Button(this);
				String info = xml.getContactInfo(this, i);
				String name = xml.getContactName(this, i);
				final String number = xml.getContactNumber(this, i);
				contact_buttons[i].setText(info + "\n"+ name + "\n" + number);
				contact_buttons[i].setGravity(Gravity.LEFT);
				contact_buttons[i].setTypeface(tf);
				contact_buttons[i].setTextColor(context.getResources().getColor(R.color.white));
				contact_buttons[i].setBackgroundResource(R.drawable.button_shape);
				contact_buttons[i].setOnClickListener(new Button.OnClickListener() {  
			        public void onClick(View v)
		            {
		                //perform action
			        	Intent dial = new Intent();
		            	dial.setAction("android.intent.action.DIAL");
		            	dial.setData(Uri.parse("tel:"+ number));
		            	startActivity(dial);
		            }
		         });
				ll = (LinearLayout)findViewById(R.id.events_layout);
				lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				lp.setMargins(Gravity.CENTER, 10, Gravity.CENTER, 10);
				ll.addView(contact_buttons[i], lp);
				
			}
			
		} catch (XmlPullParserException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lp.setMargins(Gravity.CENTER, 10, Gravity.CENTER, 10);
		ll.addView(youth, lp);
		
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
		
		lp = new LayoutParams(70, 70);
		
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
	
	public void onMapClick(final View v){ //53.413014,-6.405998
		
		double latitude = 53.413014;
		double longitude = -6.405998;
		String label = "King's Court Dublin";
		String uriBegin = "geo:" + latitude + "," + longitude;
		String query = latitude + "," + longitude + "(" + label + ")";
		String encodedQuery = Uri.encode(query);
		String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
		Uri uri = Uri.parse(uriString);
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
		startActivity(intent);
    }
	
	public static final String PREFS_NAME = "MyPrefsFile1";
    public CheckBox dontShowAgain;
    
	/** Called when the activity is brought to front. */
    @Override
    protected void onResume() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        LayoutInflater adbInflater = LayoutInflater.from(this);
        View eulaLayout = adbInflater.inflate(R.layout.checkbox, null);
        dontShowAgain = (CheckBox) eulaLayout.findViewById(R.id.skip);
        adb.setView(eulaLayout);
        adb.setTitle("King's Court Dublin");
        adb.setMessage("Press a button and see what happens. \n \n" +
        		"The Map always works after the first try.");
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String checkBoxResult = "NOT checked";
                if (dontShowAgain.isChecked())
                    checkBoxResult = "checked";
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("skipMessage", checkBoxResult);
                // Commit the edits!
                editor.commit();
                return;
            }
        });
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String skipMessage = settings.getString("skipMessage", "NOT checked");
        if (!skipMessage.equals("checked"))
            adb.show();

        super.onResume();
    }
}

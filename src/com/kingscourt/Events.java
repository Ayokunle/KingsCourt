package com.kingscourt;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
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
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
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
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Events extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		
		loadActivity();
	}
	
	public void loadActivity(){
		setContentView(R.layout.activity_events);
		
		LinearLayout ll = (LinearLayout)findViewById(R.id.events_layout);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		
		TextView tv = (TextView) findViewById(R.id.textView1);
		Typeface type = Typeface.createFromAsset(getAssets(),"PTC75F.ttf"); 
		tv.setTypeface(type);
			
		Typeface tf = Typeface.createFromAsset(getAssets(),"Aller_Rg.ttf");
		
		XMLReader xml = new XMLReader();
		
		Context context = this;
		
		int x = 1;
		try {
			File dir =  Environment.getExternalStorageDirectory();
	    	File file = new File(dir, "Android/data/events.xml");
	    	Date d = new Date(file.lastModified());
	    	String ld = "Last Update: " + d.toString();
	    	
	    	TextView textv = new TextView(this);
	    	textv.setText(ld);
	    	textv.setGravity(Gravity.LEFT);
	    	textv.setTypeface(tf);
	    	textv.setTextColor(context.getResources().getColor(R.color.white));
	    
			lp.setMargins(Gravity.CENTER, 0, Gravity.CENTER, 10);
			ll.addView(textv, lp);
			
			x = xml.getNumEvents(this);
		
			Button [] event_buttons = new Button[x];
			for(int i =0; i < x; i++){
				event_buttons[i] = new Button(this);
				final String name = xml.getEventName(this, i);
				String date = xml.getEventDate(this, i);
				final String venue = xml.getEventVenue(this, i);
				final int year = xml.getEventYear(this, i);
				final int month = xml.getEventMonth(this, i);
				final int day = xml.getEventDay(this, i);
				event_buttons[i].setText(name + date + "\n" + venue);
				event_buttons[i].setGravity(Gravity.LEFT);
				event_buttons[i].setTypeface(tf);
				event_buttons[i].setTextColor(context.getResources().getColor(R.color.white));
				event_buttons[i].setBackgroundResource(R.drawable.button_shape);
				event_buttons[i].setOnClickListener(new Button.OnClickListener() {  
			        public void onClick(View v)
		            {
		                //perform action
			        	Calendar beginCal = Calendar.getInstance();
		                beginCal.set(year, month-1, day);
		                long startTime = beginCal.getTimeInMillis();

		                Calendar endCal = Calendar.getInstance();
		                endCal.set(year, month-1, day);
		                long endTime = endCal.getTimeInMillis();     

		                Intent intent = new Intent(Intent.ACTION_INSERT);
		                intent.setType("vnd.android.cursor.item/event");
		                intent.putExtra("title", name);
		                intent.putExtra("eventLocation", venue.replace("Venue:", ""));
		                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginCal.getTimeInMillis());
		                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endCal.getTimeInMillis());
		                intent.putExtra("allDay", true);
		                startActivity(intent);
		            }
		         });
				ll = (LinearLayout)findViewById(R.id.events_layout);
				lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				lp.setMargins(Gravity.CENTER, 0, Gravity.CENTER, 10);
				ll.addView(event_buttons[i], lp);
			}
		} catch (XmlPullParserException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	public static final String PREFS_NAME = "MyPrefsFile2";
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
        adb.setMessage("Press a button to add an event to your Calendar.");
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
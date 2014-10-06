package com.kingscourt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Environment;

/**
 *
 * @author ayokunle
 */
public class XMLReader extends Activity {
    Splash sp = new Splash();

    public int getNumEvents(Activity activity)throws XmlPullParserException, IOException, ParserConfigurationException, SAXException{
    	
    	File dir =  Environment.getExternalStorageDirectory();
    	File events = new File(dir, "Android/data/events.xml");
    	
    	InputStream is = new FileInputStream(events.getPath());
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new InputSource(is));
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("event");
    	return nodeList.getLength();
    }
    
    public String getEventName(Activity activity, int x)throws XmlPullParserException, IOException{
    	//DownloadFromUrl("", "");
    	String name = "";
    	int next = 0;
    	
    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    	factory.setNamespaceAware(true);
    	XmlPullParser xpp = factory.newPullParser();
    	File dir = Environment.getExternalStorageDirectory();
    	File file = new File(dir, "Android/data/events.xml");
    	FileInputStream fis = new FileInputStream(file);
    	xpp.setInput(new InputStreamReader(fis));
    	
    	int eventType = xpp.getEventType();
    	while (eventType != XmlPullParser.END_DOCUMENT){
    		if(eventType == XmlPullParser.START_TAG){
    			if(xpp.getName().equalsIgnoreCase("name")){
    				if(next == x){
    					String temp = xpp.nextText();
    					temp = temp.substring(1, temp.length()-1) + "";
    					temp = temp.replace("\t", "");
    					name = name + temp+ "";
    				}
    				next++;
    			}
    		}
    		eventType = xpp.next();
    	}
    	return name;
    }
    
    public String getEventDate(Activity activity, int x)throws XmlPullParserException, IOException{
    	
    	String date = "";
    	int next = 0;
    	
    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    	factory.setNamespaceAware(true);
    	XmlPullParser xpp = factory.newPullParser();
    	File dir = Environment.getExternalStorageDirectory();
    	File file = new File(dir, "Android/data/events.xml");
    	FileInputStream fis = new FileInputStream(file);
    	xpp.setInput(new InputStreamReader(fis));
    	
    	int eventType = xpp.getEventType();
    	while (eventType != XmlPullParser.END_DOCUMENT){
    		if(eventType == XmlPullParser.START_TAG){
    			if(xpp.getName().equalsIgnoreCase("day")){
    				if(next == x){
    					String temp = xpp.nextText();
    					temp = temp.replace("\t", "");
    					temp = temp.replace(temp.substring(0, 1), "");
    					date =date + temp;
    				}
    			}
    			if(xpp.getName().equalsIgnoreCase("month")){
    				if(next == x){
    					String temp = xpp.nextText();
    					temp = temp.replace("\t", "");
    					temp = temp.replace("\n", " ");
    					date =date + temp;
    				}
    			}
    			if(xpp.getName().equalsIgnoreCase("year")){
    				if(next == x){
    					String temp = xpp.nextText();
    					temp = temp.replace("\t", "");
    					temp = temp.replace("\n", "");
    					date =date + temp;
    				}
    				next++;
    			}
    		}
    		eventType = xpp.next();
    	}
    	return date;
    }
    
    public int getEventYear(Activity activity, int x)throws XmlPullParserException, IOException{
    	
	String date = "";
	int next = 0;
	
	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	factory.setNamespaceAware(true);
	XmlPullParser xpp = factory.newPullParser();
	File dir = Environment.getExternalStorageDirectory();
	File file = new File(dir, "Android/data/events.xml");
	FileInputStream fis = new FileInputStream(file);
	xpp.setInput(new InputStreamReader(fis));
	
	int eventType = xpp.getEventType();
	while (eventType != XmlPullParser.END_DOCUMENT){
		if(eventType == XmlPullParser.START_TAG){
			if(xpp.getName().equalsIgnoreCase("day")){
				if(next == x){
					String temp = xpp.nextText();
					temp = temp.replace("\t", "");
					temp = temp.replace(temp.substring(0, 1), "");
					//date =date + temp;
				}
			}
			if(xpp.getName().equalsIgnoreCase("month")){
				if(next == x){
					String temp = xpp.nextText();
					temp = temp.replace("\t", "");
					temp = temp.replace("\n", " ");
					//date =date + temp;
				}
			}
			if(xpp.getName().equalsIgnoreCase("year")){
				if(next == x){
					String temp = xpp.nextText();
					temp = temp.replace("\t", "");
					temp = temp.replace("\n", "");
					date =date + temp;
				}
				next++;
			}
		}
		eventType = xpp.next();
	}
	Scanner in = new Scanner(date).useDelimiter("[^0-9]+");
	int integer = in.nextInt();
	return integer;
    }

	public int getEventMonth(Activity activity, int x)throws XmlPullParserException, IOException{
	
	String date = "";
	int next = 0;
	
	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	factory.setNamespaceAware(true);
	XmlPullParser xpp = factory.newPullParser();
	File dir = Environment.getExternalStorageDirectory();
	File file = new File(dir, "Android/data/events.xml");
	FileInputStream fis = new FileInputStream(file);
	xpp.setInput(new InputStreamReader(fis));
	
	int eventType = xpp.getEventType();
	while (eventType != XmlPullParser.END_DOCUMENT){
		if(eventType == XmlPullParser.START_TAG){
			if(xpp.getName().equalsIgnoreCase("day")){
				if(next == x){
					String temp = xpp.nextText();
					temp = temp.replace("\t", "");
					temp = temp.replace(temp.substring(0, 1), "");
					//date =date + temp;
				}
			}
			if(xpp.getName().equalsIgnoreCase("month")){
				if(next == x){
					String temp = xpp.nextText();
					temp = temp.replace("\t", "");
					temp = temp.replace("\n", " ");
					date =date + temp;
				}
			}
			if(xpp.getName().equalsIgnoreCase("year")){
				if(next == x){
					String temp = xpp.nextText();
					temp = temp.replace("\t", "");
					temp = temp.replace("\n", "");
					//date =date + temp;
				}
				next++;
			}
		}
		eventType = xpp.next();
	}
	date = date.replaceAll("[^A-Za-z0-9]", "");
	
	if(date.equals("January")){
		return 1;
	}
	if(date.equals("February")){
		return 2;
	}
	if(date.equals("March")){
		return 3;
	}
	if(date.equals("April")){
		return 4;
	}
	if(date.equals("May")){
		return 5;
	}
	if(date.equals("June")){
		return 6;
	}
	if(date.equals("July")){
		return 7;
	}
	if(date.equals("August")){
		return 8;
	}
	if(date.equals("September")){
		return 9;
	}
	if(date.equals("October")){
		return 10;
	}
	if(date.equals("November")){
		return 11;
	}
	if(date.equals("December")){
		return 12;
	}
	return 1;
}

	public int getEventDay(Activity activity, int x)throws XmlPullParserException, IOException{
	
	String date = "";
	int next = 0;
	
	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	factory.setNamespaceAware(true);
	XmlPullParser xpp = factory.newPullParser();
	File dir = Environment.getExternalStorageDirectory();
	File file = new File(dir, "Android/data/events.xml");
	FileInputStream fis = new FileInputStream(file);
	xpp.setInput(new InputStreamReader(fis));
	
	int eventType = xpp.getEventType();
	while (eventType != XmlPullParser.END_DOCUMENT){
		if(eventType == XmlPullParser.START_TAG){
			if(xpp.getName().equalsIgnoreCase("day_number")){
				if(next == x){
					String temp = xpp.nextText();
					temp = temp.replace("\t", "");
					temp = temp.replace(temp.substring(0, 1), "");
					date =date + temp;
				}
			}
			if(xpp.getName().equalsIgnoreCase("month")){
				if(next == x){
					String temp = xpp.nextText();
					temp = temp.replace("\t", "");
					temp = temp.replace("\n", " ");
					//date =date + temp;
				}
			}
			if(xpp.getName().equalsIgnoreCase("year")){
				if(next == x){
					String temp = xpp.nextText();
					temp = temp.replace("\t", "");
					temp = temp.replace("\n", "");
					//date =date + temp;
				}
				next++;
			}
		}
		eventType = xpp.next();
	}
	
	Scanner in = new Scanner(date).useDelimiter("[^0-9]+");
	int integer = in.nextInt();
	return integer;
}
    
	public String getEventVenue(Activity activity, int x)throws XmlPullParserException, IOException{

    	String venue = "";
    	int next = 0;
    	
    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    	factory.setNamespaceAware(true);
    	XmlPullParser xpp = factory.newPullParser();
    	File dir = Environment.getExternalStorageDirectory();
    	File file = new File(dir, "Android/data/events.xml");
    	FileInputStream fis = new FileInputStream(file);
    	xpp.setInput(new InputStreamReader(fis));
    	
    	int eventType = xpp.getEventType();
    	while (eventType != XmlPullParser.END_DOCUMENT){
    		if(eventType == XmlPullParser.START_TAG){
    			if(xpp.getName().equalsIgnoreCase("venue")){
    				if(next == x){
    					String temp = xpp.nextText();
    					temp = temp.substring(2, temp.length()-1) + "";
    					temp = temp.replace("\t", "");
    					temp = temp.replace(temp.substring(0, 2), "");
    					temp = temp.replace("\n", "");
    					temp = temp.replace("_", " ");
    					venue =venue + temp;
    				}
    				next++;
    			}
    		}
    		eventType = xpp.next();
    	}
    	return venue;
    }
    
	public int getNumContacts(Activity activity)throws XmlPullParserException, IOException{
    	int x = 0;
    	
    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    	factory.setNamespaceAware(true);
    	XmlPullParser xpp = factory.newPullParser();
    	File dir = Environment.getExternalStorageDirectory();
    	File file = new File(dir, "Android/data/contacts.xml");
    	FileInputStream fis = new FileInputStream(file);
    	xpp.setInput(new InputStreamReader(fis));
    	
    	int eventType = xpp.getEventType();
    	while (eventType != XmlPullParser.END_DOCUMENT){
    		if(eventType == XmlPullParser.START_TAG){
    			if(xpp.getName().equalsIgnoreCase("contact")){
    				x++;
    			}
    		}
    		eventType = xpp.next();
    	}
    	return x;
    }
    
    public String getContactName(Activity activity, int x)throws XmlPullParserException, IOException{

    	String name = "";
    	int next = 0;

    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    	factory.setNamespaceAware(true);
    	XmlPullParser xpp = factory.newPullParser();
    	File dir = Environment.getExternalStorageDirectory();
    	File file = new File(dir, "Android/data/contacts.xml");
    	FileInputStream fis = new FileInputStream(file);
    	xpp.setInput(new InputStreamReader(fis));
    	
    	int eventType = xpp.getEventType();
    	while (eventType != XmlPullParser.END_DOCUMENT){
    		if(eventType == XmlPullParser.START_TAG){
    			if(xpp.getName().equalsIgnoreCase("name")){
    				if(next == x){
    					String temp = xpp.nextText();
    					temp = temp.substring(1, temp.length()-1) + " ";
    					temp = temp.replace("\t", "");
    					temp = temp.replace("\n", "");
    					name = name + temp;
    				}
    				next++;
    			}
    		}
    		eventType = xpp.next();
    	}
    	return name;
    }
    
    public String getContactInfo(Activity activity, int x)throws XmlPullParserException, IOException{
    	
    	String info = "";
    	int next = 0;

    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    	factory.setNamespaceAware(true);
    	XmlPullParser xpp = factory.newPullParser();
    	File dir = Environment.getExternalStorageDirectory();
    	File file = new File(dir, "Android/data/contacts.xml");
    	FileInputStream fis = new FileInputStream(file);
    	xpp.setInput(new InputStreamReader(fis));
    	
    	xpp.next();
    	int eventType = xpp.getEventType();
    	while (eventType != XmlPullParser.END_DOCUMENT){
    		if(eventType == XmlPullParser.START_TAG){
    			if(xpp.getName().equalsIgnoreCase("info")){
    				if(next == x){
    					String temp = xpp.nextText();
    					temp = temp.substring(1, temp.length()-1) + " ";
    					temp = temp.replace("\t", "");
    					temp = temp.replace("\n", "");
    					temp = temp.replace("_", "\n \n");
    					info = info + temp;
    				}
    				next++;
    			}
    		}
    		eventType = xpp.next();
    	}
    	return info;
    }    

    public String getContactNumber(Activity activity, int x)throws XmlPullParserException, IOException{

    	String number = "";
    	int next = 0;
    	
    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    	factory.setNamespaceAware(true);
    	XmlPullParser xpp = factory.newPullParser();
    	File dir = Environment.getExternalStorageDirectory();
    	File file = new File(dir, "Android/data/contacts.xml");
    	FileInputStream fis = new FileInputStream(file);
    	xpp.setInput(new InputStreamReader(fis));
    	
    	xpp.next();
    	int eventType = xpp.getEventType();
    	while (eventType != XmlPullParser.END_DOCUMENT){
    		if(eventType == XmlPullParser.START_TAG){
    			if(xpp.getName().equalsIgnoreCase("number")){
    				if(next == x){
    					String temp = xpp.nextText();
    					temp = temp.substring(1, temp.length()-1) + " ";
    					temp = temp.replace("_", "\n");
    					temp = temp.replace("\t", "");
    					temp = temp.replace("\n", "");
    					number = number + temp;
    				}
    				next++;
    			}
    		}
    		eventType = xpp.next();
    	}
    	return number;
    }
    
    public String getPastorTitle(Activity activity)throws XmlPullParserException, IOException{
    	
    	String title = "";

    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    	factory.setNamespaceAware(true);
    	XmlPullParser xpp = factory.newPullParser();
    	File dir = Environment.getExternalStorageDirectory();
    	File file = new File(dir, "Android/data/pdesk.xml");
    	FileInputStream fis = new FileInputStream(file);
    	xpp.setInput(new InputStreamReader(fis));
    	
    	xpp.next();
    	int eventType = xpp.getEventType();
    	while (eventType != XmlPullParser.END_DOCUMENT){
    		if(eventType == XmlPullParser.START_TAG){
    			if(xpp.getName().equalsIgnoreCase("title")){
    					String temp = xpp.nextText();
    					temp = temp.substring(1, temp.length()-1) + " ";
    					temp = temp.replace("\t", "");
    					temp = temp.replace("\n", "\n");
    					title = title + temp;
    			}
    		}
    		eventType = xpp.next();
    	}
    	return title;
    }
    
    public String getPastorMessage(Activity activity)throws XmlPullParserException, IOException{

    	String title = "";

    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    	factory.setNamespaceAware(true);
    	XmlPullParser xpp = factory.newPullParser();
    	File dir = Environment.getExternalStorageDirectory();
    	File file = new File(dir, "Android/data/pdesk.xml");
    	FileInputStream fis = new FileInputStream(file);
    	xpp.setInput(new InputStreamReader(fis));
    	
    	xpp.next();
    	int eventType = xpp.getEventType();
    	while (eventType != XmlPullParser.END_DOCUMENT){
    		if(eventType == XmlPullParser.START_TAG){
    			if(xpp.getName().equalsIgnoreCase("message")){
    					String temp = xpp.nextText();
    					temp = temp.substring(1, temp.length()-1) + " ";
    					temp = temp.replace("\n", "\n");
    					temp = temp.replace("\t", "");
    					title = title + temp;
    			}
    		}
    		eventType = xpp.next();
    	}
    	return title;
    }
}
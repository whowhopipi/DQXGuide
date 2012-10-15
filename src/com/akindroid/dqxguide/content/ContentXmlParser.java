package com.akindroid.dqxguide.content;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class ContentXmlParser {
	private XmlPullParser mXmlPullParser = null;
	private OnParseListener mListener = null;
	
	public interface OnParseListener {
		void onParse(Object object);
		void onParseFinished();
	}
	
	public ContentXmlParser(InputStream inputStream) {
		mXmlPullParser = Xml.newPullParser();
		
		try {
			mXmlPullParser.setInput(inputStream, "UTF-8");
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			
			mXmlPullParser = null;
		}
	}
	
	public void setOnParseListener(OnParseListener listener) {
		mListener = listener;
	}
	
	public void beginParse(ParseStateMachine mMachine) {
		try {
			int eventType = mXmlPullParser.getEventType();
			
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					mMachine.setStartTag(mXmlPullParser.getName());
				} else if (eventType == XmlPullParser.END_TAG) {
					Object item = mMachine.setEndTag(mXmlPullParser.getName());
					if (item != null && mListener != null) {
						mListener.onParse(item);
					}
				} else if (eventType == XmlPullParser.TEXT) {
					mMachine.setText(mXmlPullParser.getText());
				}
				
				eventType = mXmlPullParser.next();
			}
			
			if (mListener != null) {
				mListener.onParseFinished();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

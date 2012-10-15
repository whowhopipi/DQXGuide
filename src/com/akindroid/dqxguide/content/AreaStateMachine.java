package com.akindroid.dqxguide.content;

public class AreaStateMachine implements ParseStateMachine {
	private static final int STATE_NONE = 0;
	private static final int STATE_ID = 1;
	private static final int STATE_NAME = 2;
	private static final int STATE_CONTINENT = 3;
	private static final int STATE_TYPE = 4;
	private static final int STATE_MAP = 5;
	
	private static final String TAG_AREA = "area";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_CONTINENT = "continent";
	private static final String TAG_TYPE = "type";
	private static final String TAG_MAP = "map";
	
	private Area mArea;
	private int mCurrentState;
	
	public AreaStateMachine() {
		initialize();
	}
	
	public void setStartTag(String sTag) {
		if (sTag.equals(TAG_AREA)) {
			initialize();
		} else if (sTag.equals(TAG_ID)) {
			mCurrentState = STATE_ID;
		} else if (sTag.equals(TAG_NAME)) {
			mCurrentState = STATE_NAME;
		} else if (sTag.equals(TAG_CONTINENT)) {
			mCurrentState = STATE_CONTINENT;
		} else if (sTag.equals(TAG_TYPE)) {
			mCurrentState = STATE_TYPE;
		} else if (sTag.equals(TAG_MAP)) {
			mCurrentState = STATE_MAP;
		}
	}

	public Object setEndTag(String eTag) {
		if (eTag.equals(TAG_AREA)) {
			return mArea;
		} else {
			mCurrentState = STATE_NONE;
		}
		
		return null;
	}

	public void setText(String text) {
		if (mCurrentState == STATE_NONE) {
			;
		} else if (mCurrentState == STATE_ID) {
			mArea.Id = Integer.parseInt(text);
		} else if (mCurrentState == STATE_NAME) {
			mArea.Name = text;
		} else if (mCurrentState == STATE_CONTINENT) {
			mArea.Continent = text;
		} else if (mCurrentState == STATE_TYPE) {
			mArea.Type = text;
		} else if (mCurrentState == STATE_MAP) {
			mArea.Map = text;
		}
	}
	
	private void initialize() {
		mArea = new Area();
		mCurrentState = STATE_NONE;
	}
	
	public class Area {
		public int Id;
		public String Name;
		public String Continent;
		public String Type;
		public String Map;
	}

}

package com.akindroid.dqxguide.content;

public class QuestStateMachine implements ParseStateMachine {
	private static final int STATE_NONE = 0;
	private static final int STATE_ID = 1;
	private static final int STATE_NAME = 2;
	private static final int STATE_AREA = 3;
	private static final int STATE_PROV = 4;
	private static final int STATE_FAME = 5;
	
	private static final String TAG_QUEST = "quest";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_AREA = "area";
	private static final String TAG_PROV = "prov";
	private static final String TAG_FAME = "fame";
	
	private Quest mQuest;
	private int mCurrentState;
	
	public QuestStateMachine() {
		initialize();
	}

	public void setStartTag(String sTag) {
		if (sTag.equals(TAG_QUEST)) {
			initialize();
		} else if (sTag.equals(TAG_ID)) {
			mCurrentState = STATE_ID;
		} else if (sTag.equals(TAG_NAME)) {
			mCurrentState = STATE_NAME;
		} else if (sTag.equals(TAG_AREA)) {
			mCurrentState = STATE_AREA;
		} else if (sTag.equals(TAG_PROV)) {
			mCurrentState = STATE_PROV;
		} else if (sTag.equals(TAG_FAME)) {
			mCurrentState = STATE_FAME;
		}		
	}

	public Object setEndTag(String eTag) {
		if (eTag.equals(TAG_QUEST)) {
			return mQuest;
		} else {
			mCurrentState = STATE_NONE;
		}
		
		return null;
	}

	public void setText(String text) {
		if (mCurrentState == STATE_NONE) {
			;
		} else if (mCurrentState == STATE_ID) {
			mQuest.Id = Integer.parseInt(text);
		} else if (mCurrentState == STATE_NAME) {
			mQuest.Name = text;
		} else if (mCurrentState == STATE_AREA) {
			mQuest.Area = text;
		} else if (mCurrentState == STATE_PROV) {
			mQuest.Prov = text;
		} else if (mCurrentState == STATE_FAME) {
			mQuest.Fame = Integer.parseInt(text);
		}
	}

	private void initialize() {
		mQuest = new Quest();
		mCurrentState = STATE_NONE;
	}
	
	public class Quest {
		public int Id;
		public String Name;
		public String Area;
		public String Prov;
		public int Fame;
	}
	
}

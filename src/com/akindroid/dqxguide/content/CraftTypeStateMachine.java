package com.akindroid.dqxguide.content;

public class CraftTypeStateMachine implements ParseStateMachine {
	private static final int STATE_NONE = 0;
	private static final int STATE_ID = 1;
	private static final int STATE_NAME = 2;
	
	private static final String TAG_CRAFT_TYPE = "craft_type";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	
	private CraftType mCraftType;
	private int mCurrentState;
	
	public CraftTypeStateMachine() {
		initialize();
	}

	public void setStartTag(String sTag) {
		if (sTag.equals(TAG_CRAFT_TYPE)) {
			initialize();
		} else if (sTag.equals(TAG_ID)) {
			mCurrentState = STATE_ID;
		} else if (sTag.equals(TAG_NAME)) {
			mCurrentState = STATE_NAME;
		}
	}

	public Object setEndTag(String eTag) {
		if (eTag.equals(TAG_CRAFT_TYPE)) {
			return mCraftType;
		} else {
			mCurrentState = STATE_NONE;
		}
		
		return null;
	}

	public void setText(String text) {
		if (mCurrentState == STATE_NONE) {
			;
		} else if (mCurrentState == STATE_ID) {
			mCraftType.Id = Integer.parseInt(text);
		} else if (mCurrentState == STATE_NAME) {
			mCraftType.Name = text;
		}
	}
	
	private void initialize() {
		mCraftType = new CraftType();
		mCurrentState = STATE_NONE;
	}
	
	public class CraftType {
		public int Id;
		public String Name;
	}
	
}

package com.akindroid.dqxguide.content;

public class ArmyTypeStateMachine implements ParseStateMachine {
	private static final int STATE_NONE = 0;
	private static final int STATE_ID = 1;
	private static final int STATE_NAME = 2;
	private static final int STATE_ICON = 3;
	
	private static final String TAG_ARMY_TYPE = "army_type";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_ICON = "icon";
	
	private ArmyType mArmyType;
	private int mCurrentState;
	
	public ArmyTypeStateMachine() {
		initialize();
	}

	public void setStartTag(String sTag) {
		if (sTag.equals(TAG_ARMY_TYPE)) {
			initialize();
		} else if (sTag.equals(TAG_ID)) {
			mCurrentState = STATE_ID;
		} else if (sTag.equals(TAG_NAME)) {
			mCurrentState = STATE_NAME;
		} else if (sTag.equals(TAG_ICON)) {
			mCurrentState = STATE_ICON;
		}
	}

	public Object setEndTag(String eTag) {
		if (eTag.equals(TAG_ARMY_TYPE)) {
			return mArmyType;
		} else {
			mCurrentState = STATE_NONE;
		}
		
		return null;
	}

	public void setText(String text) {
		if (mCurrentState == STATE_NONE) {
			;
		} else if (mCurrentState == STATE_ID) {
			mArmyType.Id = Integer.parseInt(text);
		} else if (mCurrentState == STATE_NAME) {
			mArmyType.Name = text;
		} else if (mCurrentState == STATE_ICON) {
			mArmyType.Icon = text;
		}
	}
	
	private void initialize() {
		mArmyType = new ArmyType();
		mCurrentState = STATE_NONE;
	}
	
	public class ArmyType {
		public int Id;
		public String Name;
		public String Icon;
	}
	
}

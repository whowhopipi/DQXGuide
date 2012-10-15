package com.akindroid.dqxguide.content;

public class UpdateStateMachine implements ParseStateMachine {
	private static final int STATE_NONE = 0;
	private static final int STATE_ID = 1;
	private static final int STATE_VERSION = 2;
	private static final int STATE_DESCRIPTION = 3;
	
	private static final String TAG_UPDATE = "update";
	private static final String TAG_ID = "id";
	private static final String TAG_VERSION = "v";
	private static final String TAG_DESCRIPTION = "description";	
	
	private Update mUpdate;
	private int mCurrentState;
	
	public UpdateStateMachine() {
		initialize();
	}

	public void setStartTag(String sTag) {
		if (sTag.equals(TAG_UPDATE)) {
			initialize();
		} else if (sTag.equals(TAG_ID)) {
			mCurrentState = STATE_ID;
		} else if (sTag.equals(TAG_VERSION)) {
			mCurrentState = STATE_VERSION;
		} else if (sTag.equals(TAG_DESCRIPTION)) {
			mCurrentState = STATE_DESCRIPTION;
		}
	}

	public Object setEndTag(String eTag) {
		if (eTag.equals(TAG_UPDATE)) {
			return mUpdate;
		} else {
			mCurrentState = STATE_NONE;
		}
	
		return null;
	}

	public void setText(String text) {
		if (mCurrentState == STATE_NONE) {
			;
		} else if (mCurrentState == STATE_ID) {
			mUpdate.Id = Integer.parseInt(text);
		} else if (mCurrentState == STATE_VERSION) {
			mUpdate.Version = text;
		} else if (mCurrentState == STATE_DESCRIPTION) {
			mUpdate.Description = text;
		}
	}
	
	private void initialize() {
		mUpdate = new Update();
		mCurrentState = STATE_NONE;
	}
		
	public class Update {
		public int Id;
		public String Version;
		public String Description;
	}
	
}

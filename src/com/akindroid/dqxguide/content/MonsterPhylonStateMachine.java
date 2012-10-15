package com.akindroid.dqxguide.content;

public class MonsterPhylonStateMachine implements ParseStateMachine {
	private static final int STATE_NONE = 0;
	private static final int STATE_ID = 1;
	private static final int STATE_NAME = 2;
	
	private static final String TAG_MONSTER_PHYLON = "monster_phylon";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	
	private MonsterPhylon mMonsterPhylon;
	private int mCurrentState;
	
	public MonsterPhylonStateMachine() {
		initialize();
	}

	public void setStartTag(String sTag) {
		if (sTag.equals(TAG_MONSTER_PHYLON)) {
			initialize();
		} else if (sTag.equals(TAG_ID)) {
			mCurrentState = STATE_ID;
		} else if (sTag.equals(TAG_NAME)) {
			mCurrentState = STATE_NAME;
		}
	}

	public Object setEndTag(String eTag) {
		if (eTag.equals(TAG_MONSTER_PHYLON)) {
			return mMonsterPhylon;
		} else {
			mCurrentState = STATE_NONE;
		}
		
		return null;
	}

	public void setText(String text) {
		if (mCurrentState == STATE_NONE) {
			;
		} else if (mCurrentState == STATE_ID) {
			mMonsterPhylon.Id = Integer.parseInt(text);
		} else if (mCurrentState == STATE_NAME) {
			mMonsterPhylon.Name = text;
		}
	}
	
	private void initialize() {
		mMonsterPhylon = new MonsterPhylon();
		mCurrentState = STATE_NONE;
	}
	
	public class MonsterPhylon {
		public int Id;
		public String Name;
	}
	
}

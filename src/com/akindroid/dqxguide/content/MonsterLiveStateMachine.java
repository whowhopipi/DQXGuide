package com.akindroid.dqxguide.content;

public class MonsterLiveStateMachine implements ParseStateMachine {
	private static final int STATE_NONE = 0;
	private static final int STATE_ID = 1;
	private static final int STATE_MID = 2;
	private static final int STATE_AID = 3;
	
	private static final String TAG_MONSTER_LIVE = "monster_live";
	private static final String TAG_ID = "id";
	private static final String TAG_MID = "mid";
	private static final String TAG_AID = "aid";
	
	private MonsterLive mMonsterLive;
	private int mCurrentState;
	
	public MonsterLiveStateMachine() {
		initialize();
	}
	
	public void setStartTag(String sTag) {
		if (sTag.equals(TAG_MONSTER_LIVE)) {
			initialize();
		} else if (sTag.equals(TAG_ID)) {
			mCurrentState = STATE_ID;
		} else if (sTag.equals(TAG_MID)) {
			mCurrentState = STATE_MID;
		} else if (sTag.equals(TAG_AID)) {
			mCurrentState = STATE_AID;
		}
	}

	public Object setEndTag(String eTag) {
		if (eTag.equals(TAG_MONSTER_LIVE)) {
			return mMonsterLive;
		} else {
			mCurrentState = STATE_NONE;
		}
		
		return null;
	}

	public void setText(String text) {
		if (mCurrentState == STATE_NONE) {
			;
		} else if (mCurrentState == STATE_ID) {
			mMonsterLive.Id = Integer.parseInt(text);
		} else if (mCurrentState == STATE_MID) {
			mMonsterLive.Mid = Integer.parseInt(text);
		} else if (mCurrentState == STATE_AID) {
			mMonsterLive.Aid = Integer.parseInt(text);
		}
	}
	
	private void initialize() {
		mMonsterLive = new MonsterLive();
		mCurrentState = STATE_NONE;
	}
	
	public class MonsterLive {
		public int Id;
		public int Mid;
		public int Aid;
	}

}

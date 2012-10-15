package com.akindroid.dqxguide.content;

import com.akindroid.dqxguide.content.MonsterLiveStateMachine.MonsterLive;

public class QuestHireStateMachine implements ParseStateMachine {
	private static final int STATE_NONE = 0;
	private static final int STATE_ID = 1;
	private static final int STATE_QID = 2;
	private static final int STATE_HIRE = 3;
	private static final int STATE_HIRE_ITEM_ID = 4;
	private static final int STATE_HIRE_ITEM_COUNT = 5;
	private static final int STATE_HIRE_TYPE = 6;
	
	private static final String TAG_QUEST_HIRE = "quest_hire";
	private static final String TAG_ID = "id";
	private static final String TAG_QID = "qid";
	private static final String TAG_HIRE = "hire";
	private static final String TAG_HIRE_ITEM_ID = "hireitemid";
	private static final String TAG_HIRE_ITEM_COUNT = "hireitemcount";
	private static final String TAG_HIRE_TYPE = "hiretype";
	
	private QuestHire mQuestHire;
	private int mCurrentState;
	
	public QuestHireStateMachine() {
		initialize();
	}
	
	public void setStartTag(String sTag) {
		if (sTag.equals(TAG_QUEST_HIRE)) {
			initialize();
		} else if (sTag.equals(TAG_ID)) {
			mCurrentState = STATE_ID;
		} else if (sTag.equals(TAG_QID)) {
			mCurrentState = STATE_QID;
		} else if (sTag.equals(TAG_HIRE)) {
			mCurrentState = STATE_HIRE;
		} else if (sTag.equals(TAG_HIRE_ITEM_ID)) {
			mCurrentState = STATE_HIRE_ITEM_ID;
		} else if (sTag.equals(TAG_HIRE_ITEM_COUNT)) {
			mCurrentState = STATE_HIRE_ITEM_COUNT;
		} else if (sTag.equals(TAG_HIRE_TYPE)) {
			mCurrentState = STATE_HIRE_TYPE;
		}
	}

	public Object setEndTag(String eTag) {
		if (eTag.equals(TAG_QUEST_HIRE)) {
			return mQuestHire;
		} else {
			mCurrentState = STATE_NONE;
		}
		
		return null;
	}

	public void setText(String text) {
		if (mCurrentState == STATE_NONE) {
			;
		} else if (mCurrentState == STATE_ID) {
			mQuestHire.Id = Integer.parseInt(text);
		} else if (mCurrentState == STATE_QID) {
			mQuestHire.Qid = Integer.parseInt(text);
		} else if (mCurrentState == STATE_HIRE) {
			mQuestHire.Hire = text;
		} else if (mCurrentState == STATE_HIRE_ITEM_ID) {
			mQuestHire.HireItemId = Integer.parseInt(text);
		} else if (mCurrentState == STATE_HIRE_ITEM_COUNT) {
			mQuestHire.HireItemCount = Integer.parseInt(text);
		} else if (mCurrentState == STATE_HIRE_TYPE) {
			mQuestHire.HireType = Integer.parseInt(text);
		}
	}
	
	private void initialize() {
		mQuestHire = new QuestHire();
		mCurrentState = STATE_NONE;
	}
	
	public class QuestHire {
		public int Id;
		public int Qid;
		public String Hire;
		public int HireItemId;
		public int HireItemCount;
		public int HireType;
	}
}

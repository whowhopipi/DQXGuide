package com.akindroid.dqxguide.content;

public class QuestFlowStateMachine implements ParseStateMachine {
	private static final int STATE_NONE = 0;
	private static final int STATE_ID = 1;
	private static final int STATE_QID = 2;
	private static final int STATE_FLOW = 3;
	
	private static final String TAG_QUEST_FLOW = "quest_flow";
	private static final String TAG_ID = "id";
	private static final String TAG_QID = "qid";
	private static final String TAG_FLOW = "flow";
	
	private QuestFlow mQuestFlow;
	private int mCurrentState;
	
	public QuestFlowStateMachine() {
		initialize();
	}

	public void setStartTag(String sTag) {
		if (sTag.equals(TAG_QUEST_FLOW)) {
			initialize();
		} else if (sTag.equals(TAG_ID)) {
			mCurrentState = STATE_ID;
		} else if (sTag.equals(TAG_QID)) {
			mCurrentState = STATE_QID;
		} else if (sTag.equals(TAG_FLOW)) {
			mCurrentState = STATE_FLOW;
		}
	}

	public Object setEndTag(String eTag) {
		if (eTag.equals(TAG_QUEST_FLOW)) {
			return mQuestFlow;
		} else {
			mCurrentState = STATE_NONE;
		}
		
		return null;
	}

	public void setText(String text) {
		if (mCurrentState == STATE_NONE) {
			;
		} else if (mCurrentState == STATE_ID) {
			mQuestFlow.Id = Integer.parseInt(text);
		} else if (mCurrentState == STATE_QID) {
			mQuestFlow.Qid = Integer.parseInt(text);
		} else if (mCurrentState == STATE_FLOW) {
			mQuestFlow.Flow = text;
		}
	}
	
	private void initialize() {
		mQuestFlow = new QuestFlow();
		mCurrentState = STATE_NONE;
	}
	
	public class QuestFlow {
		public int Id;
		public int Qid;
		public String Flow;
	}
	
}

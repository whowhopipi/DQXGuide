package com.akindroid.dqxguide.content;

public class RecipeStateMachine implements ParseStateMachine {
	private static final int STATE_NONE = 0;
	private static final int STATE_ID = 1;
	private static final int STATE_IID = 2;
	private static final int STATE_COUNT = 3;
	
	private static final String TAG_RECIPE = "recipe";
	private static final String TAG_ID = "id";
	private static final String TAG_IID = "iid";
	private static final String TAG_COUNT = "count";
	
	private Recipe mRecipe;
	private int mCurrentState;
	
	public RecipeStateMachine() {
		initialize();
	}

	public void setStartTag(String sTag) {
		if (sTag.equals(TAG_RECIPE)) {
			initialize();
		} else if (sTag.equals(TAG_ID)) {
			mCurrentState = STATE_ID;
		} else if (sTag.equals(TAG_IID)) {
			mCurrentState = STATE_IID;
		} else if (sTag.equals(TAG_COUNT)) {
			mCurrentState = STATE_COUNT;
		}
	}

	public Object setEndTag(String eTag) {
		if (eTag.equals(TAG_RECIPE)) {
			return mRecipe;
		} else {
			mCurrentState = STATE_NONE;
		}
		
		return null;
	}

	public void setText(String text) {
		if (mCurrentState == STATE_NONE) {
			;
		} else if (mCurrentState == STATE_ID) {
			mRecipe.Id = Integer.parseInt(text);
		} else if (mCurrentState == STATE_IID) {
			mRecipe.Iid = Integer.parseInt(text);
		} else if (mCurrentState == STATE_COUNT) {
			mRecipe.Count = Integer.parseInt(text);
		}
	}
	
	private void initialize() {
		mRecipe = new Recipe();
		mCurrentState = STATE_NONE;
	}
	
	public class Recipe {
		public int Id;
		public int Iid;
		public int Count;
	}
	
}

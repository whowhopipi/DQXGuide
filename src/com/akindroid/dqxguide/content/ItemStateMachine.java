package com.akindroid.dqxguide.content;

public class ItemStateMachine implements ParseStateMachine {
	private static final int STATE_NONE = 0;
	private static final int STATE_ID = 1;
	private static final int STATE_NAME = 2;
	private static final int STATE_BUY = 3;
	private static final int STATE_SELL = 4;
	private static final int STATE_DESCRIPTION = 5;
	private static final int STATE_ICON = 6;
	private static final int STATE_RECIPE = 7;
	private static final int STATE_DROP = 8;
	private static final int STATE_AREA = 9;
	private static final int STATE_TYPE = 10;

	private static final String TAG_ITEM = "item";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_BUY = "buy";
	private static final String TAG_SELL = "sell";
	private static final String TAG_DESCRIPTION = "desc";
	private static final String TAG_ICON = "icon";
	private static final String TAG_RECIPE = "recipe";
	private static final String TAG_DROP = "drop";
	private static final String TAG_AREA = "area";
	private static final String TAG_TYPE = "type";
	
	private Item mItem;
	private int mCurrentState;
	
	public ItemStateMachine() {
		initialize();
	}
	
	public void setStartTag(String sTag) {
		if (sTag.equals(TAG_ITEM)) {
			initialize();
		} else if (sTag.equals(TAG_ID)) {
			mCurrentState = STATE_ID;
		} else if (sTag.equals(TAG_NAME)) {
			mCurrentState = STATE_NAME;
		} else if (sTag.equals(TAG_BUY)) {
			mCurrentState = STATE_BUY;
		} else if (sTag.equals(TAG_SELL)) {
			mCurrentState = STATE_SELL;
		} else if (sTag.equals(TAG_DESCRIPTION)) {
			mCurrentState = STATE_DESCRIPTION;
		} else if (sTag.equals(TAG_ICON)) {
			mCurrentState = STATE_ICON;
		} else if (sTag.equals(TAG_RECIPE)) {
			mCurrentState = STATE_RECIPE;
		} else if (sTag.equals(TAG_DROP)) {
			mCurrentState = STATE_DROP;
		} else if (sTag.equals(TAG_AREA)) {
			mCurrentState = STATE_AREA;
		} else if (sTag.equals(TAG_TYPE)) {
			mCurrentState = STATE_TYPE;
		}
	}

	public Object setEndTag(String eTag) {
		if (eTag.equals(TAG_ITEM)) {
			return mItem;
		} else {
			mCurrentState = STATE_NONE;
		}
		
		return null;
	}

	public void setText(String text) {
		if (mCurrentState == STATE_NONE) {
			;
		} else if (mCurrentState == STATE_ID) {
			mItem.Id = Integer.parseInt(text);
		} else if (mCurrentState == STATE_NAME) {
			mItem.Name = text;
		} else if (mCurrentState == STATE_BUY) {
			mItem.Buy = Integer.parseInt(text);
		} else if (mCurrentState == STATE_SELL) {
			mItem.Sell = Integer.parseInt(text);
		} else if (mCurrentState == STATE_DESCRIPTION) {
			mItem.Description = text;
		} else if (mCurrentState == STATE_ICON) {
			mItem.Icon = text;
		} else if (mCurrentState == STATE_RECIPE) {
			mItem.Recipe = Integer.parseInt(text);
		} else if (mCurrentState == STATE_DROP) {
			mItem.Drop = Integer.parseInt(text);
		} else if (mCurrentState == STATE_AREA) {
			mItem.Area = Integer.parseInt(text);
		} else if (mCurrentState == STATE_TYPE) {
			mItem.Type = Integer.parseInt(text);
		}
 	}
	
	private void initialize() {
		mItem = new Item();
		mCurrentState = STATE_NONE;
	}
		
	public class Item {
		public int Id;
		public String Name;
		public int Buy;
		public int Sell;
		public String Description;
		public String Icon;
		public int Recipe;
		public int Drop;
		public int Area;
		public int Type;
	}
}

package com.akindroid.dqxguide.content;

public class MonsterStateMachine implements ParseStateMachine {
	private static final int STATE_NONE = 0;
	private static final int STATE_ID = 1;
	private static final int STATE_NAME = 2;
	private static final int STATE_HP = 3;
	private static final int STATE_ATTACK = 4;
	private static final int STATE_DEFENCE = 5;
	private static final int STATE_EXP = 6;
	private static final int STATE_GOLD = 7;
	private static final int STATE_PHYLON = 8;
	private static final int STATE_CMN = 9;
	private static final int STATE_RARE = 10;
	private static final int STATE_ICON = 11;
	private static final int STATE_DESC = 12;
	
	private static final String TAG_MONSTER = "monster";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_HP = "hp";
	private static final String TAG_ATTACK = "attack";
	private static final String TAG_DEFENCE = "defence";
	private static final String TAG_EXP = "exp";
	private static final String TAG_GOLD = "gold";
	private static final String TAG_PHYLON = "phylon";
	private static final String TAG_CMN = "cmn";
	private static final String TAG_RARE = "rare";
	private static final String TAG_ICON = "icon";
	private static final String TAG_DESC = "description";
 	
	private Monster mMonster;
	private int mCurrentState;
	
	public MonsterStateMachine() {
		initialize();
	}
	
	public void setStartTag(String sTag) {
		if (sTag.equals(TAG_MONSTER)) {
			initialize();
		} else if (sTag.equals(TAG_ID)) {
			mCurrentState = STATE_ID;
		} else if (sTag.equals(TAG_NAME)) {
			mCurrentState = STATE_NAME;
		} else if (sTag.equals(TAG_HP)) {
			mCurrentState = STATE_HP;
		} else if (sTag.equals(TAG_ATTACK)) {
			mCurrentState = STATE_ATTACK;
		} else if (sTag.equals(TAG_DEFENCE)) {
			mCurrentState = STATE_DEFENCE;
		} else if (sTag.equals(TAG_EXP)) {
			mCurrentState = STATE_EXP;
		} else if (sTag.equals(TAG_GOLD)) {
			mCurrentState = STATE_GOLD;
		} else if (sTag.equals(TAG_PHYLON)) {
			mCurrentState = STATE_PHYLON;
		} else if (sTag.equals(TAG_CMN)) {
			mCurrentState = STATE_CMN;
		} else if (sTag.equals(TAG_RARE)) {
			mCurrentState = STATE_RARE;
		} else if (sTag.equals(TAG_ICON)) {
			mCurrentState = STATE_ICON;
		} else if (sTag.equals(TAG_DESC)) {
			mCurrentState = STATE_DESC;
		}   
	}

	public Object setEndTag(String eTag) {
		if (eTag.equals(TAG_MONSTER)) {
			return mMonster;
		} else {
			mCurrentState = STATE_NONE;
		}
		
		return null;
	}

	public void setText(String text) {
		if (mCurrentState == STATE_NONE) {
			;
		} else if (mCurrentState == STATE_ID) {
			mMonster.Id = Integer.parseInt(text);
		} else if (mCurrentState == STATE_NAME) {
			mMonster.Name = text;
		} else if (mCurrentState == STATE_HP) {
			mMonster.Hp = text;
		} else if (mCurrentState == STATE_ATTACK) {
			mMonster.Attack = text;
		} else if (mCurrentState == STATE_DEFENCE) {
			mMonster.Defence = text;
		} else if (mCurrentState == STATE_EXP) {
			mMonster.Exp = text;
		} else if (mCurrentState == STATE_GOLD) {
			mMonster.Gold = text;
		} else if (mCurrentState == STATE_PHYLON) {
			mMonster.Phylon = text;
		} else if (mCurrentState == STATE_CMN) {
			mMonster.Cmn = text;
		} else if (mCurrentState == STATE_RARE) {
			mMonster.Rare = text;
		} else if (mCurrentState == STATE_ICON) {
			mMonster.Icon = text;
		} else if (mCurrentState == STATE_DESC) {
			mMonster.Description = text;
		}
	}
	
	private void initialize() {
		mMonster = new Monster();
		mCurrentState = STATE_NONE;
	}

	public class Monster {
		public int Id;
		public String Name;
		public String Hp;
		public String Attack;
		public String Defence;
		public String Exp;
		public String Gold;
		public String Phylon;
		public String Cmn;
		public String Rare;
		public String Icon;
		public String Description;
	}
}

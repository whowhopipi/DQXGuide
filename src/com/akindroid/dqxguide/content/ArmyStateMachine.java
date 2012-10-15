package com.akindroid.dqxguide.content;

import android.util.Log;

public class ArmyStateMachine implements ParseStateMachine {
	private static final int STATE_NONE = 0;
	private static final int STATE_ID = 1;
	private static final int STATE_IID = 2;
	private static final int STATE_TYPE = 3;
	private static final int STATE_CTYPE = 18;
	private static final int STATE_LV = 4;
	private static final int STATE_KLV = 5;
	private static final int STATE_ATK = 6;
	private static final int STATE_DEF = 7;
	private static final int STATE_HP = 8;
	private static final int STATE_MP = 9;
	private static final int STATE_SNZ = 10;
	private static final int STATE_SPD = 11;
	private static final int STATE_FAC = 12;
	private static final int STATE_ATKSP = 13;
	private static final int STATE_DEFSP = 14;
	private static final int STATE_WT = 15;
	private static final int STATE_CON = 16;
	private static final int STATE_ADV = 17;
	
	private static final String TAG_ARMY = "army";
	private static final String TAG_ID = "id";
	private static final String TAG_IID = "iid";
	private static final String TAG_TYPE = "type";
	private static final String TAG_CTYPE = "ctype";
	private static final String TAG_LV = "lv";
	private static final String TAG_KLV = "klv";
	private static final String TAG_ATK = "atk";
	private static final String TAG_DEF = "df";
	private static final String TAG_HP = "hp";
	private static final String TAG_MP = "mp";
	private static final String TAG_SNZ = "snz";
	private static final String TAG_SPD = "spd";
	private static final String TAG_FAC = "fac";
	private static final String TAG_ATKSP = "atksp";
	private static final String TAG_DEFSP = "defsp";
	private static final String TAG_WT = "wt";
	private static final String TAG_CON = "con";
	private static final String TAG_ADV = "adv";

	private Army mArmy;
	private int mCurrentState;
	
	public ArmyStateMachine() {
		initialize();
	}

	public void setStartTag(String sTag) {
		if (sTag.equals(TAG_ARMY)) {
			initialize();
		} else if (sTag.equals(TAG_ID)) {
			mCurrentState = STATE_ID;
		} else if (sTag.equals(TAG_IID)) {
			mCurrentState = STATE_IID;
		} else if (sTag.equals(TAG_TYPE)) {
			mCurrentState = STATE_TYPE;
		} else if (sTag.equals(TAG_CTYPE)) {
			mCurrentState = STATE_CTYPE;
		} else if (sTag.equals(TAG_LV)) {
			mCurrentState = STATE_LV;
		} else if (sTag.equals(TAG_KLV)) {
			mCurrentState = STATE_KLV;
		} else if (sTag.equals(TAG_ATK)) {
			mCurrentState = STATE_ATK;
		} else if (sTag.equals(TAG_DEF)) {
			mCurrentState = STATE_DEF;
		} else if (sTag.equals(TAG_HP)) {
			mCurrentState = STATE_HP;
		} else if (sTag.equals(TAG_MP)) {
			mCurrentState = STATE_MP;
		} else if (sTag.equals(TAG_SNZ)) {
			mCurrentState = STATE_SNZ;
		} else if (sTag.equals(TAG_SPD)) {
			mCurrentState = STATE_SPD;
		} else if (sTag.equals(TAG_FAC)) {
			mCurrentState = STATE_FAC;
		} else if (sTag.equals(TAG_ATKSP)) {
			mCurrentState = STATE_ATKSP;
		} else if (sTag.equals(TAG_DEFSP)) {
			mCurrentState = STATE_DEFSP;
		} else if (sTag.equals(TAG_WT)) {
			mCurrentState = STATE_WT;
		} else if (sTag.equals(TAG_CON)) {
			mCurrentState = STATE_CON;
		} else if (sTag.equals(TAG_ADV)) {
			mCurrentState = STATE_ADV;
		}
	}

	public Object setEndTag(String eTag) {
		if (eTag.equals(TAG_ARMY)) {
			return mArmy;
		} else {
			mCurrentState = STATE_NONE;
		}
		
		return null;
	}

	public void setText(String text) {
		if (mCurrentState == STATE_NONE) {
			;
		} else if (mCurrentState == STATE_ID) {
			mArmy.Id = Integer.parseInt(text);
		} else if (mCurrentState == STATE_IID) {
			mArmy.Iid = Integer.parseInt(text);
		} else if (mCurrentState == STATE_TYPE) {
			mArmy.Type = Integer.parseInt(text);
		} else if (mCurrentState == STATE_CTYPE) {
			mArmy.CType = Integer.parseInt(text);
		} else if (mCurrentState == STATE_LV) {
			mArmy.Lv = Integer.parseInt(text);
		} else if (mCurrentState == STATE_KLV) {
			mArmy.Klv = Integer.parseInt(text);
		} else if (mCurrentState == STATE_ATK) {
			mArmy.Atk = Integer.parseInt(text);
		} else if (mCurrentState == STATE_DEF) {
			mArmy.Def = Integer.parseInt(text);
		} else if (mCurrentState == STATE_HP) {
			mArmy.Hp = Integer.parseInt(text);
		} else if (mCurrentState == STATE_MP) {
			mArmy.Mp = Integer.parseInt(text);
		} else if (mCurrentState == STATE_SNZ) {
			mArmy.Snz = Integer.parseInt(text);
		} else if (mCurrentState == STATE_SPD) {
			mArmy.Spd = Integer.parseInt(text);
		} else if (mCurrentState == STATE_FAC) {
			mArmy.Fac = Integer.parseInt(text);
		} else if (mCurrentState == STATE_ATKSP) {
			mArmy.Atksp = Integer.parseInt(text);
		} else if (mCurrentState == STATE_DEFSP) {
			mArmy.Defsp = Integer.parseInt(text);
		} else if (mCurrentState == STATE_WT) {
			mArmy.Wt = Integer.parseInt(text);
		} else if (mCurrentState == STATE_CON) {
			mArmy.Con = Integer.parseInt(text);
		} else if (mCurrentState == STATE_ADV) {
			mArmy.Adv = text;
		}
	}
	
	private void initialize() {
		mArmy = new Army();
		mCurrentState = STATE_NONE;
	}
	
	public class Army {
		public int Id;
		public int Iid;
		public int Type;
		public int CType;
		public int Lv;
		public int Klv;
		public int Atk;
		public int Def;
		public int Hp;
		public int Mp;
		public int Snz;
		public int Spd;
		public int Fac;
		public int Atksp;
		public int Defsp;
		public int Wt;
		public int Con;
		public String Adv;
	}
	
}

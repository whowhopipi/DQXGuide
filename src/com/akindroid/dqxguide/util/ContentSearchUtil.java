package com.akindroid.dqxguide.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.akindroid.dqxguide.content.ArmyStateMachine;
import com.akindroid.dqxguide.content.ArmyTypeStateMachine;
import com.akindroid.dqxguide.content.ArmyTypeStateMachine.ArmyType;
import com.akindroid.dqxguide.content.AreaStateMachine;
import com.akindroid.dqxguide.content.ContentDatabaseHelper;
import com.akindroid.dqxguide.content.ItemStateMachine;
import com.akindroid.dqxguide.content.MonsterStateMachine;
import com.akindroid.dqxguide.content.QuestFlowStateMachine;
import com.akindroid.dqxguide.content.QuestHireStateMachine;
import com.akindroid.dqxguide.content.QuestStateMachine;

import android.content.Context;
import android.database.Cursor;

public class ContentSearchUtil {
	private Context mContext;
	
	private static ContentSearchUtil mContentSearchUtil = null;
	private static ContentDatabaseHelper mDatabaseHelper;
	
	private static final String ITEM_TABLE = "item";
	private static final String MONSTER_TABLE = "monster";
	private static final String ARMY_TABLE = "army";
	private static final String ARMY_TYPE_TABLE = "armytype";
	private static final String QUEST_TABLE = "quest";
	private static final String QUEST_FLOW_TABLE = "questflow";
	private static final String QUEST_HIRE_TABLE = "questhire";
	private static final String AREA_TABLE = "area";
	
	public static final String SELECT_ITEM_ALL = "SELECT * FROM " + ITEM_TABLE 
			+ " WHERE NOT itype=2 AND NOT itype=4 ORDER BY name asc";
	public static final String SELECT_ITEM_MATERIAL = "SELECT * FROM " + ITEM_TABLE 
			+ " WHERE itype=0 ORDER BY name asc";
	public static final String SELECT_ITEM_USES = "SELECT * FROM " + ITEM_TABLE 
			+ " WHERE itype=1 ORDER BY name asc";
	public static final String SELECT_ITEM_ONE = "SELECT * FROM " + ITEM_TABLE + " WHERE id=?";
	
	public static final String SELECT_MONSTER_ALL = "SELECT * FROM " 
			+ MONSTER_TABLE + " ORDER BY name asc";
	public static final String SELECT_MONSTER_ONE = "SELECT * FROM " 
			+ MONSTER_TABLE + " WHERE id=?";
	public static final String SELECT_MONSTER_CMN_DROP = "SELECT * FROM " 
			+ MONSTER_TABLE + " WHERE cmn=?";
	public static final String SELECT_MONSTER_RARE_DROP = "SELECT * FROM " 
			+ MONSTER_TABLE + " WHERE rare=?";
	public static final String SELECT_MONSTER_SEARCH = "SELECT * FROM " 
			+ MONSTER_TABLE + " WHERE name like ? ORDER BY name asc";
	
	public static final String SELECT_ARMY_ALL = "SELECT * FROM " + ITEM_TABLE
			+ " WHERE itype=2 ORDER BY name ASC";
	public static final String SELECT_ARMY_CTYPE = "SELECT * FROM " + ARMY_TABLE
			+ " WHERE ctype=? ORDER BY id ASC";
	
	public static final String SELECT_ARMY_TYPE_ONE = "SELECT * FROM " 
			+ ARMY_TYPE_TABLE + " WHERE id=?";
	
	public static final String SELECT_QUEST_SPACE = "SELECT * FROM "
			+ QUEST_TABLE + " WHERE id>=? AND id<=?";
	
	public static final String SELECT_QUEST_FLOW_ONE = "SELECT * FROM "
			+ QUEST_FLOW_TABLE + " WHERE qid=? ORDER BY id asc";
	
	public static final String SELECT_QUEST_HIRE = "SELECT * FROM "
			+ QUEST_HIRE_TABLE + " WHERE qid=? and hiretype=0";
	public static final String SELECT_QUEST_REHIRE = "SELECT * FROM "
			+ QUEST_HIRE_TABLE + " WHERE qid=? and hiretype=1";
	
	public static final String SELECT_AREA_ONE = "SELECT * FROM " + AREA_TABLE
			+ " WHERE id=?";
	
	private ContentSearchUtil(Context context) {
		mContext = context;
	}
	
	public static ContentSearchUtil getInstance(Context context) {
		if (mContentSearchUtil == null) {
			mContentSearchUtil = new ContentSearchUtil(context);
			mDatabaseHelper = new ContentDatabaseHelper(context);
		}
		
		return mContentSearchUtil;
	}
	
	public List<ItemStateMachine.Item> getItem(String query, String[] whereArgs) {
		List<ItemStateMachine.Item> result = new ArrayList<ItemStateMachine.Item>();
		
		Cursor cursor = mDatabaseHelper.rawQuery(query, whereArgs);
		while (cursor.moveToNext()) {
			ItemStateMachine.Item item = (new ItemStateMachine()).new Item();
			item.Id = cursor.getInt(cursor.getColumnIndex("id"));
			item.Name = cursor.getString(cursor.getColumnIndex("name"));
			item.Buy = cursor.getInt(cursor.getColumnIndex("buy"));
			item.Sell = cursor.getInt(cursor.getColumnIndex("sell"));
			item.Description = cursor.getString(cursor.getColumnIndex("description"));
			item.Icon = cursor.getString(cursor.getColumnIndex("icon"));
			item.Recipe = cursor.getInt(cursor.getColumnIndex("recipe"));
			item.Drop = cursor.getInt(cursor.getColumnIndex("idrop"));
			item.Area = cursor.getInt(cursor.getColumnIndex("area"));
			item.Type = cursor.getInt(cursor.getColumnIndex("itype"));
			
			result.add(item);
		}
		
		cursor.close();
		
		return result;
	}
	
	public List<MonsterStateMachine.Monster> getMonster(String query, String[] whereArgs) {
		List<MonsterStateMachine.Monster> result = new ArrayList<MonsterStateMachine.Monster>();
		
		Cursor cursor = mDatabaseHelper.rawQuery(query, whereArgs);
		while (cursor.moveToNext()) {
			MonsterStateMachine.Monster monster = (new MonsterStateMachine()).new Monster();
			monster.Id = cursor.getInt(cursor.getColumnIndex("id"));
			monster.Name = cursor.getString(cursor.getColumnIndex("name"));
			monster.Hp = cursor.getString(cursor.getColumnIndex("hp"));
			monster.Attack = cursor.getString(cursor.getColumnIndex("attack"));
			monster.Defence = cursor.getString(cursor.getColumnIndex("defence"));
			monster.Exp = cursor.getString(cursor.getColumnIndex("exp"));
			monster.Gold = cursor.getString(cursor.getColumnIndex("gold"));
			monster.Phylon = cursor.getString(cursor.getColumnIndex("phylon"));
			monster.Cmn = cursor.getString(cursor.getColumnIndex("cmn"));
			monster.Rare = cursor.getString(cursor.getColumnIndex("rare"));
			
			result.add(monster);
		}
		
		cursor.close();
		
		return result;
	}
		
	public List<ArmyStateMachine.Army> getArmy(String query, String[] whereArgs) {
		List<ArmyStateMachine.Army> result = new ArrayList<ArmyStateMachine.Army>();
		
		Cursor cursor = mDatabaseHelper.rawQuery(query, whereArgs);
		while (cursor.moveToNext()) {
			ArmyStateMachine.Army army = (new ArmyStateMachine()).new Army();
			army.Id = cursor.getInt(cursor.getColumnIndex("id"));
			army.Iid = cursor.getInt(cursor.getColumnIndex("iid"));
			army.Type = cursor.getInt(cursor.getColumnIndex("type"));
			army.CType = cursor.getInt(cursor.getColumnIndex("ctype"));
			army.Lv = cursor.getInt(cursor.getColumnIndex("lv"));
			army.Klv = cursor.getInt(cursor.getColumnIndex("klv"));
			army.Atk = cursor.getInt(cursor.getColumnIndex("atk"));
			army.Def = cursor.getInt(cursor.getColumnIndex("df"));
			army.Hp = cursor.getInt(cursor.getColumnIndex("hp"));
			army.Mp = cursor.getInt(cursor.getColumnIndex("mp"));
			army.Snz = cursor.getInt(cursor.getColumnIndex("snz"));
			army.Spd = cursor.getInt(cursor.getColumnIndex("spd"));
			army.Fac = cursor.getInt(cursor.getColumnIndex("fac"));
			army.Atksp = cursor.getInt(cursor.getColumnIndex("atksp"));
			army.Defsp = cursor.getInt(cursor.getColumnIndex("defsp"));
			army.Wt = cursor.getInt(cursor.getColumnIndex("wt"));
			army.Con = cursor.getInt(cursor.getColumnIndex("con"));
			army.Adv = cursor.getString(cursor.getColumnIndex("adv"));
		
			result.add(army);
		}
		
		cursor.close();
		
		return result;
	}
	
	public List<ArmyTypeStateMachine.ArmyType> getArmyType(String query, String[] whereArgs) {
		List<ArmyTypeStateMachine.ArmyType> result = new ArrayList<ArmyTypeStateMachine.ArmyType>();
		
		Cursor cursor = mDatabaseHelper.rawQuery(query, whereArgs);
		while (cursor.moveToNext()) {
			ArmyTypeStateMachine.ArmyType armyType = (new ArmyTypeStateMachine()).new ArmyType();
			armyType.Id = cursor.getInt(cursor.getColumnIndex("id"));
			armyType.Name = cursor.getString(cursor.getColumnIndex("name"));
			armyType.Icon = cursor.getString(cursor.getColumnIndex("icon"));
			
			result.add(armyType);
		}
		
		cursor.close();
		
		return result;
	}
	
	public List<QuestStateMachine.Quest> getQuest(String query, String[] whereArgs) {
		List<QuestStateMachine.Quest> result = new ArrayList<QuestStateMachine.Quest>();
		
		Cursor cursor = mDatabaseHelper.rawQuery(query, whereArgs);
		while (cursor.moveToNext()) {
			QuestStateMachine.Quest quest = (new QuestStateMachine()).new Quest();
			quest.Id = cursor.getInt(cursor.getColumnIndex("id"));
			quest.Name = cursor.getString(cursor.getColumnIndex("name"));
			quest.Area = cursor.getString(cursor.getColumnIndex("area"));
			quest.Prov = cursor.getString(cursor.getColumnIndex("prov"));
			quest.Fame = cursor.getInt(cursor.getColumnIndex("fame"));
			
			result.add(quest);
		}
		
		return result;
	}
	
	public List<QuestFlowStateMachine.QuestFlow> getQuestFlow(String query, String[] whereArgs) {
		List<QuestFlowStateMachine.QuestFlow> result = 
				new ArrayList<QuestFlowStateMachine.QuestFlow>();
		
		Cursor cursor = mDatabaseHelper.rawQuery(query, whereArgs);
		while (cursor.moveToNext()) {
			QuestFlowStateMachine.QuestFlow questFlow =
					(new QuestFlowStateMachine()).new QuestFlow();
			questFlow.Id = cursor.getInt(cursor.getColumnIndex("id"));
			questFlow.Qid = cursor.getInt(cursor.getColumnIndex("qid"));
			questFlow.Flow = cursor.getString(cursor.getColumnIndex("flow"));
			
			result.add(questFlow);
		}
		
		return result;
	}
	
	public List<AreaStateMachine.Area> getArea(String query, String[] whereArgs) {
		List<AreaStateMachine.Area> result = new ArrayList<AreaStateMachine.Area>();
		
		Cursor cursor = mDatabaseHelper.rawQuery(query, whereArgs);
		while (cursor.moveToNext()) {
			AreaStateMachine.Area area = (new AreaStateMachine()).new Area();
			area.Id = cursor.getInt(cursor.getColumnIndex("id"));
			area.Name = cursor.getString(cursor.getColumnIndex("name"));
			area.Continent = cursor.getString(cursor.getColumnIndex("continent"));
			area.Type = cursor.getString(cursor.getColumnIndex("type"));
			area.Map = cursor.getString(cursor.getColumnIndex("map"));
			
			result.add(area);
		}
		
		return result;
	}
	
	public List<QuestHireStateMachine.QuestHire> getQuestHire(String query, String[] whereArgs) {
		List<QuestHireStateMachine.QuestHire> result = 
				new ArrayList<QuestHireStateMachine.QuestHire>();
		
		Cursor cursor = mDatabaseHelper.rawQuery(query, whereArgs);
		while (cursor.moveToNext()) {
			QuestHireStateMachine.QuestHire questHire = 
					(new QuestHireStateMachine()).new QuestHire();
			questHire.Id = cursor.getInt(cursor.getColumnIndex("id"));
			questHire.Qid = cursor.getInt(cursor.getColumnIndex("qid"));
			questHire.Hire = cursor.getString(cursor.getColumnIndex("hire"));
			questHire.HireItemId = cursor.getInt(cursor.getColumnIndex("hireitemid"));
			questHire.HireItemCount = cursor.getInt(cursor.getColumnIndex("hireitemcount"));
			questHire.HireType = cursor.getInt(cursor.getColumnIndex("hiretype"));
			
			result.add(questHire);
		}
				
		return result;
	}
}

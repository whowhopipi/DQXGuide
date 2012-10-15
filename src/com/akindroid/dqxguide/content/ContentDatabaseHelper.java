package com.akindroid.dqxguide.content;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContentDatabaseHelper extends SQLiteOpenHelper {
	private static final String TAG = "ContentDatabaseHelper";
	
	private static final String DATABASE_NAME = "content.db";
	private static final int DATABLSE_VERSION = 1;
	
	private static final String ITEM_TABLE = "item";
	private static final String MONSTER_TABLE = "monster";
	private static final String MONSTER_PHYLON_TABLE = "monsterphylon";
	private static final String MONSTER_LIVE_TABLE = "monsterlive";
	private static final String AREA_TABLE = "area";
	private static final String ARMY_TABLE = "army";
	private static final String ARMY_TYPE_TABLE = "armytype";
	private static final String CRAFT_TYPE_TABLE = "crafttype";
	private static final String RECIPE_TABLE = "recipe";
	private static final String QUEST_TABLE = "quest";
	private static final String QUEST_FLOW_TABLE = "questflow";
	private static final String QUEST_HIRE_TABLE = "questhire";
	private static final String UPDATE_TABLE = "upd";
	
	// item
	private static final String CREATE_ITEM_TABLE = "CREATE TABLE IF NOT EXISTS " + ITEM_TABLE +
			" (id INTEGER, name TEXT, buy INTEGER, sell INTEGER, description TEXT," +
			" icon TEXT, recipe INTEGER, idrop INTEGER, area INTEGER, itype INTEGER)";
	// monster
	private static final String CREATE_MONSTER_TABLE = "CREATE TABLE IF NOT EXISTS " + MONSTER_TABLE +
			" (id INTEGER, name TEXT, hp INTEGER, attack INTEGER, defence INTEGER," +
			" exp INTEGER, gold INTEGER, phylon INTEGER, cmn INTEGER, rare INTEGER," +
			" icon TEXT, description TEXT)";
	// monster_phylon
	private static final String CREATE_MONSTER_PHYLON_TABLE = "CREATE TABLE IF NOT EXISTS " 
			+ MONSTER_PHYLON_TABLE + " (id INTEGER, name TEXT)";
	// monster_live
	private static final String CREATE_MONSTER_LIVE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ MONSTER_LIVE_TABLE + " (id INTEGER, mid INTEGER, aid INTEGER)";
	// area
	private static final String CREATE_AREA_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ AREA_TABLE + " (id INTEGER, name TEXT, continent TEXT, type TEXT, map TEXT)";
	// army
	private static final String CREATE_ARMY_TABLE = "CREATE TABLE IF NOT EXISTS " + ARMY_TABLE +
			" (id INTEGER, iid INTEGER, type INTEGER, ctype INTEGER, lv INTEGER, klv INTEGER," +
			" atk INTEGER, df INTEGER, hp INTGER, mp INTEGER, snz INTEGER, spd INTEGER," +
			" fac INTEGER, atksp INTEGER, defsp INTEGER, wt INTEGR, con INTEGER, adv TEXT)";
	// army_type
	private static final String CREATE_ARMY_TYPE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ ARMY_TYPE_TABLE + " (id INTEGER, name TEXT, icon TEXT)";
	// craft_type
	private static final String CREATE_CRAFT_TYPE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ CRAFT_TYPE_TABLE + " (id INTEGER, name TEXT)";
	// recipe
	private static final String CREATE_RECIPE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ RECIPE_TABLE + " (id INTEGER, iid INTEGER, count INTEGER)";
	// quest
	private static final String CREATE_QUEST_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ QUEST_TABLE + " (id INTEGER, name TEXT, area TEXT, prov TEXT, fame INTEGER)";
	// quest_flow
	private static final String CREATE_QUEST_FLOW_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ QUEST_FLOW_TABLE + " (id INTEGER, qid INTEGER, flow TEXT)";
	// quest_hire
	private static final String CREATE_QUEST_HIRE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ QUEST_HIRE_TABLE + " (id INTEGER, qid INTEGER, hire TEXT, hireitemid INTEGER, "
			+ "hireitemcount INTEGER, hiretype INTEGER)";
	
	// update
	public static final String CREATE_UPDATE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ UPDATE_TABLE + " (id INTEGER, v TEXT, description TEXT)";
	
	private Context mContext;
	private SQLiteDatabase mdb;
	private AssetManager mAssetmanager;
	
	public ContentDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABLSE_VERSION);
		
		mContext = context;
		mdb = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_ITEM_TABLE);
		db.execSQL(CREATE_MONSTER_TABLE);
		db.execSQL(CREATE_MONSTER_PHYLON_TABLE);
		db.execSQL(CREATE_MONSTER_LIVE_TABLE);
		db.execSQL(CREATE_AREA_TABLE);
		db.execSQL(CREATE_ARMY_TABLE);
		db.execSQL(CREATE_ARMY_TYPE_TABLE);
		db.execSQL(CREATE_CRAFT_TYPE_TABLE);
		db.execSQL(CREATE_RECIPE_TABLE);
		db.execSQL(CREATE_QUEST_TABLE);
		db.execSQL(CREATE_QUEST_FLOW_TABLE);
		db.execSQL(CREATE_QUEST_HIRE_TABLE);
		db.execSQL(CREATE_UPDATE_TABLE);
		
		mdb = db;
		mAssetmanager = mContext.getResources().getAssets();
		
		initializeEntryData();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		mdb = db;
	}
		
	private void initializeEntryData() {
		zip2db("item.zip", new MyParseItemListener(), new ItemStateMachine());	
		zip2db("monster.zip", new MyParseMonsterListener(), new MonsterStateMachine());
		zip2db("monster_phylon.zip", 
				new MyParseMonsterPhylonListener(), new MonsterPhylonStateMachine());
		zip2db("monster_live.zip", new MyParseMonsterLiveListener(), new MonsterLiveStateMachine());
		zip2db("area.zip", new MyParseAreaListener(), new AreaStateMachine());
		zip2db("army.zip", new MyParseArmyListener(), new ArmyStateMachine());
		zip2db("army_type.zip", new MyParseArmyTypeListener(), new ArmyTypeStateMachine());
		zip2db("craft_type.zip", new MyParseCraftTypeListener(), new CraftTypeStateMachine());
		zip2db("recipe.zip", new MyParseRecipeListener(), new RecipeStateMachine());
		zip2db("quest.zip", new MyParseQuestListener(), new QuestStateMachine());
		zip2db("quest_flow.zip", new MyParseQuestFlowListener(), new QuestFlowStateMachine());
		zip2db("quest_hire.zip", new MyParseQuestHireListener(), new QuestHireStateMachine());
		zip2db("update.zip", new MyUpdateListener(), new UpdateStateMachine());
	}
	
	private void zip2db(String zipFileName, 
			ContentXmlParser.OnParseListener listener, ParseStateMachine stateMachine) {
		try {
			InputStream inputStream = mAssetmanager.open(
					zipFileName, AssetManager.ACCESS_STREAMING);
			
			ZipInputStream zipInputStream = new ZipInputStream(inputStream);
			ZipEntry zipEntry = zipInputStream.getNextEntry();
			
			if (zipEntry != null) {
				Log.w(TAG, "Start initialize table = " + zipEntry.getName());
				
				ContentXmlParser parser = new ContentXmlParser(zipInputStream);
				parser.setOnParseListener(listener);
				parser.beginParse(stateMachine);
			}
			
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private class MyParseItemListener implements ContentXmlParser.OnParseListener {
		public void onParse(Object object) {
			ItemStateMachine.Item item = (ItemStateMachine.Item) object;

			ContentValues values = new ContentValues();
			values.put("id", item.Id);
			values.put("name", item.Name);
			values.put("buy", item.Buy);
			values.put("sell", item.Sell);
			values.put("description", item.Description);
			values.put("icon", item.Icon);
			values.put("recipe", item.Recipe);
			values.put("idrop", item.Drop);
			values.put("area", item.Area);
			values.put("itype", item.Type);
			
			insert(ITEM_TABLE, null, values);
		}

		public void onParseFinished() {
			Log.w(TAG, "Finish initialize item table.");
		}
	}
	
	private class MyParseMonsterListener implements ContentXmlParser.OnParseListener {
		public void onParse(Object object) {
			MonsterStateMachine.Monster monster = (MonsterStateMachine.Monster) object;
			
			ContentValues values = new ContentValues();
			values.put("id", monster.Id);
			values.put("name", monster.Name);
			values.put("hp", monster.Hp);
			values.put("attack", monster.Attack);
			values.put("defence", monster.Defence);
			values.put("exp", monster.Exp);
			values.put("gold", monster.Gold);
			values.put("phylon", monster.Phylon);
			values.put("cmn", monster.Cmn);
			values.put("rare", monster.Rare);
			values.put("icon", monster.Icon);
			values.put("description", monster.Description);
			
			insert(MONSTER_TABLE, null, values);
		}

		public void onParseFinished() {
			Log.w(TAG, "Finish initialize monster table.");
		}
	}
	
	private class MyParseMonsterPhylonListener implements ContentXmlParser.OnParseListener {
		public void onParse(Object object) {
			MonsterPhylonStateMachine.MonsterPhylon monsterPhylon = 
					(MonsterPhylonStateMachine.MonsterPhylon) object;
			
			ContentValues values = new ContentValues();
			values.put("id", monsterPhylon.Id);
			values.put("name", monsterPhylon.Name);
			
			insert(MONSTER_PHYLON_TABLE, null, values);
		}
		
		public void onParseFinished() {
			Log.w(TAG, "Finish initialize monster phylon table.");
		}
	}
	
	private class MyParseMonsterLiveListener implements ContentXmlParser.OnParseListener {
		public void onParse(Object object) {
			MonsterLiveStateMachine.MonsterLive monsterLive = 
					(MonsterLiveStateMachine.MonsterLive) object;
			
			ContentValues values = new ContentValues();
			values.put("id", monsterLive.Id);
			values.put("mid", monsterLive.Mid);
			values.put("aid", monsterLive.Aid);
			
			insert(MONSTER_LIVE_TABLE, null, values);
		}

		public void onParseFinished() {
			Log.w(TAG, "Finish initialize monsterlive table.");
		}
	}
	
	private class MyParseAreaListener implements ContentXmlParser.OnParseListener {
		public void onParse(Object object) {
			AreaStateMachine.Area areaLive = (AreaStateMachine.Area) object;
			
			ContentValues values = new ContentValues();
			values.put("id", areaLive.Id);
			values.put("name", areaLive.Name);
			values.put("continent", areaLive.Continent);
			values.put("type", areaLive.Type);
			values.put("map", areaLive.Map);
			
			insert(AREA_TABLE, null, values);
		}

		public void onParseFinished() {
			Log.w(TAG, "Finish initialize area table.");
		}
	}
	
	private class MyParseArmyListener implements ContentXmlParser.OnParseListener {
		public void onParse(Object object) {
			ArmyStateMachine.Army army = (ArmyStateMachine.Army) object;
			
			ContentValues values = new ContentValues();
			values.put("id", army.Id);
			values.put("iid", army.Iid);
			values.put("type", army.Type);
			values.put("ctype", army.CType);
			values.put("lv", army.Lv);
			values.put("klv", army.Klv);
			values.put("atk", army.Atk);
			values.put("df", army.Def);
			values.put("hp", army.Hp);
			values.put("mp", army.Mp);
			values.put("snz", army.Snz);
			values.put("spd", army.Spd);
			values.put("fac", army.Fac);
			values.put("atksp", army.Atksp);
			values.put("defsp", army.Defsp);
			values.put("wt", army.Wt);
			values.put("con", army.Con);
			values.put("adv", army.Adv);

			insert(ARMY_TABLE, null, values);
		}

		public void onParseFinished() {
			Log.w(TAG, "Finish initialize army table.");
		}
	}
	
	private class MyParseArmyTypeListener implements ContentXmlParser.OnParseListener {
		public void onParse(Object object) {
			ArmyTypeStateMachine.ArmyType armyType = (ArmyTypeStateMachine.ArmyType) object;
			
			ContentValues values = new ContentValues();
			values.put("id", armyType.Id);
			values.put("name", armyType.Name);
			values.put("icon", armyType.Icon);
			
			insert(ARMY_TYPE_TABLE, null, values);
		}

		public void onParseFinished() {
			Log.w(TAG, "Finish initialize army type table.");
		}
	}
	
	private class MyParseCraftTypeListener implements ContentXmlParser.OnParseListener {
		public void onParse(Object object) {
			CraftTypeStateMachine.CraftType craftType = (CraftTypeStateMachine.CraftType) object;
			
			ContentValues values = new ContentValues();
			values.put("id", craftType.Id);
			values.put("name", craftType.Name);
			
			insert(CRAFT_TYPE_TABLE, null, values);
		}

		public void onParseFinished() {
			Log.w(TAG, "Finish initialize craft type table.");
		}
	}
	
	private class MyParseRecipeListener implements ContentXmlParser.OnParseListener {
		public void onParse(Object object) {
			RecipeStateMachine.Recipe recipe = (RecipeStateMachine.Recipe) object;
			
			ContentValues values = new ContentValues();
			values.put("id", recipe.Id);
			values.put("iid", recipe.Iid);
			values.put("count", recipe.Count);
			
			insert(RECIPE_TABLE, null, values);
		}

		public void onParseFinished() {
			Log.w(TAG, "Finish initialize recipe table.");
		}
	}
	
	private class MyParseQuestListener implements ContentXmlParser.OnParseListener {
		public void onParse(Object object) {
			QuestStateMachine.Quest quest = (QuestStateMachine.Quest) object;
			
			ContentValues values = new ContentValues();
			values.put("id", quest.Id);
			values.put("name", quest.Name);
			values.put("area", quest.Area);
			values.put("prov", quest.Prov);
			values.put("fame", quest.Fame);
			
			insert(QUEST_TABLE, null, values);
		}

		public void onParseFinished() {
			Log.w(TAG, "Finish initialize quest table.");
		}
	}
	
	private class MyParseQuestFlowListener implements ContentXmlParser.OnParseListener {
		public void onParse(Object object) {
			QuestFlowStateMachine.QuestFlow flow = (QuestFlowStateMachine.QuestFlow) object;
			
			ContentValues values = new ContentValues();
			values.put("id", flow.Id);
			values.put("qid", flow.Qid);
			values.put("flow", flow.Flow);
	
			insert(QUEST_FLOW_TABLE, null, values);
		}

		public void onParseFinished() {
			Log.w(TAG, "Finish initialize recipe table.");
		}
	}
	
	private class MyParseQuestHireListener implements ContentXmlParser.OnParseListener {
		public void onParse(Object object) {
			QuestHireStateMachine.QuestHire mQuestHire = 
					(QuestHireStateMachine.QuestHire) object;

			ContentValues values = new ContentValues();
			values.put("id", mQuestHire.Id);
			values.put("qid", mQuestHire.Qid);
			values.put("hire", mQuestHire.Hire);
			values.put("hireitemid", mQuestHire.HireItemId);
			values.put("hireitemcount", mQuestHire.HireItemCount);
			values.put("hiretype", mQuestHire.HireType);
						
			insert(QUEST_HIRE_TABLE, null, values);
		}

		public void onParseFinished() {
			Log.w(TAG, "Finish initialize area table.");
		}
	}
	
	private class MyUpdateListener implements ContentXmlParser.OnParseListener {
		public void onParse(Object object) {
			UpdateStateMachine.Update mUpdate = (UpdateStateMachine.Update) object;
			
			ContentValues values = new ContentValues();
			values.put("id", mUpdate.Id);
			values.put("v", mUpdate.Version);
			values.put("description", mUpdate.Description);
			
			insert(UPDATE_TABLE, null, values);
		}

		public void onParseFinished() {
			Log.w(TAG, "Finish initialize update table.");
		}
	}
			
	public Cursor rawQuery(String sql, String[] selectionArgs) {
		return mdb.rawQuery(sql, selectionArgs);
	}
	
	public void insert(String table, String nullColumnHack, ContentValues values) {
		mdb.insert(table, nullColumnHack, values);
	}
	
	public void delete(String table, String whereClause, String[] whereArgs) {
		mdb.delete(table, whereClause, whereArgs);
	}

}

package com.akindroid.dqxguide;

import java.util.List;

import com.akindroid.dqxguide.content.ItemStateMachine;
import com.akindroid.dqxguide.content.MonsterStateMachine;
import com.akindroid.dqxguide.util.ContentSearchUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MonsterDialogActivity extends Activity {
	private ContentSearchUtil mSearchUtil;
	
	private TextView monsterNameTextView;
	private TextView monsterHpTextView;
	private TextView monsterAtkTextView;
	private TextView monsterDefTextView;
	private TextView monsterExpTextView;
	private TextView monsterGoldTextView;
	private TextView monsterCommonTextView;
	private TextView monsterRareTextView;
	private LinearLayout monsterAreaLinearLayout;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.dialog_monster);
	    
	    monsterNameTextView = (TextView) findViewById(R.id.dialog_monster_name);
	    monsterHpTextView = (TextView) findViewById(R.id.dialog_monster_hp);
	    monsterAtkTextView = (TextView) findViewById(R.id.dialog_monster_atk);
	    monsterDefTextView = (TextView) findViewById(R.id.dialog_monster_def);
	    monsterExpTextView = (TextView) findViewById(R.id.dialog_monster_exp);
	    monsterGoldTextView = (TextView) findViewById(R.id.dialog_monster_gold);
	    monsterCommonTextView = (TextView) findViewById(R.id.dialog_monster_common);
	    monsterRareTextView = (TextView) findViewById(R.id.dialog_monster_rare);
	    
	    mSearchUtil = ContentSearchUtil.getInstance(getApplicationContext());
	    
	    int id = getIntent().getIntExtra("id", -1);
	    if (id == -1) return;
	    
	    List<MonsterStateMachine.Monster> monsterList = mSearchUtil.getMonster(
	    		ContentSearchUtil.SELECT_MONSTER_ONE, new String[] { String.valueOf(id) });
	    if (monsterList.size() < 1) return;
	    
	    MonsterStateMachine.Monster monster = monsterList.get(0);
	    
	    monsterNameTextView.setText(monster.Name);
	    monsterHpTextView.setText(monster.Hp);
	    monsterAtkTextView.setText(monster.Attack);
	    monsterDefTextView.setText(monster.Defence);
	    monsterExpTextView.setText(monster.Exp);
	    monsterGoldTextView.setText(monster.Gold);
	    
	    List<ItemStateMachine.Item> commonItemList = mSearchUtil.getItem(
	    		ContentSearchUtil.SELECT_ITEM_ONE, new String[] { monster.Cmn });
	    if (commonItemList.size() > 0) {
	    	monsterCommonTextView.setText(commonItemList.get(0).Name);
	    	monsterCommonTextView.getPaint().setUnderlineText(true);
	    	monsterCommonTextView.setTextColor(Color.CYAN);
	    	monsterCommonTextView.setOnClickListener(
	    			new MyItemClickListener(Integer.valueOf(monster.Cmn)));
	    }
	    
	    List<ItemStateMachine.Item> rareItemList = mSearchUtil.getItem(
	    		ContentSearchUtil.SELECT_ITEM_ONE, new String[] { monster.Rare });
	    if (rareItemList.size() > 0) {
	    	monsterRareTextView.setText(rareItemList.get(0).Name);
	    	monsterRareTextView.getPaint().setUnderlineText(true);
	    	monsterRareTextView.setTextColor(Color.CYAN);
	    	monsterRareTextView.setOnClickListener(
	    			new MyItemClickListener(Integer.valueOf(monster.Rare)));
	    }
	}
	
	private class MyItemClickListener implements View.OnClickListener {
		int mItemId;
		
		public MyItemClickListener(int itemid) {
			mItemId = itemid;
		}
		
		public void onClick(View v) {
			Intent intent = new Intent(getApplicationContext(), ItemDialogActivity.class);
			intent.putExtra("id", mItemId);
			
			startActivity(intent);
		}
	}

}

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
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemDialogActivity extends Activity {
	private ContentSearchUtil mSearchUtil;
	
	private TextView itemNameTextView;
	private TextView itemBuyTextView;
	private TextView itemSellTextView;
	private TextView itemDescTextView;
	private LinearLayout itemRecipeLayout;
	private LinearLayout itemDropLayout;
	private LinearLayout itemSaleLayout;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.dialog_item);
	    
	    itemNameTextView = (TextView) findViewById(R.id.dialog_item_name);
	    itemBuyTextView = (TextView) findViewById(R.id.dialog_item_buy);
	    itemSellTextView = (TextView) findViewById(R.id.dialog_item_sell);
	    itemDescTextView = (TextView) findViewById(R.id.dialog_item_description);
	    itemRecipeLayout = (LinearLayout) findViewById(R.id.dialog_item_recipe);
	    itemDropLayout = (LinearLayout) findViewById(R.id.dialog_item_drop);
	    itemSaleLayout = (LinearLayout) findViewById(R.id.dialog_item_sale);

	    mSearchUtil = ContentSearchUtil.getInstance(getApplicationContext());
	    
	    int id = getIntent().getIntExtra("id", -1);
	    if (id == -1) return;
	    
    	List<ItemStateMachine.Item> item = mSearchUtil.getItem(
    			ContentSearchUtil.SELECT_ITEM_ONE, new String[] { String.valueOf(id) });
	    if (item.size() < 1) return;
	    
    	itemNameTextView.setText(String.valueOf(item.get(0).Name));
    	itemBuyTextView.setText(
    			(item.get(0).Buy == -1)? "バザーのみ" : String.valueOf(item.get(0).Buy));
    	itemSellTextView.setText(String.valueOf(item.get(0).Sell));
	    itemDescTextView.setText(String.valueOf(item.get(0).Description));
	    
	    List<MonsterStateMachine.Monster> cmnDropMonsterList = mSearchUtil.getMonster(
	    		ContentSearchUtil.SELECT_MONSTER_CMN_DROP, new String[] { String.valueOf(item.get(0).Id) } );
	    for (MonsterStateMachine.Monster monster : cmnDropMonsterList) {
	    	TextView textView = new TextView(this);
	    	textView.setText("(通常) " + monster.Name);
	    	textView.setTextColor(Color.CYAN);
	    	textView.getPaint().setUnderlineText(true);
	    	textView.setPadding(0, 10, 0, 0);
	    	textView.setOnClickListener(new MyDropMonsterClickListener(monster.Id));
	    	
	    	itemDropLayout.addView(textView, new LinearLayout.LayoutParams(
	    			ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
	    }
	    
	    List<MonsterStateMachine.Monster> rareDropMonsterList = mSearchUtil.getMonster(
	    		ContentSearchUtil.SELECT_MONSTER_RARE_DROP, new String[] { String.valueOf(item.get(0).Id) });
	    for (MonsterStateMachine.Monster monster : rareDropMonsterList) {
	    	TextView textView = new TextView(this);
	    	textView.setText("(レア) " + monster.Name);
	    	textView.setTextColor(Color.YELLOW);
	    	textView.getPaint().setUnderlineText(true);
	    	textView.setPadding(0, 10, 0, 0);
	    	textView.setOnClickListener(new MyDropMonsterClickListener(monster.Id));
	    	
	    	itemDropLayout.addView(textView, new LinearLayout.LayoutParams(
	    			ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
	    }
	}
	
	private class MyDropMonsterClickListener implements View.OnClickListener {
		private int mMonsterId;
		
		public MyDropMonsterClickListener(int monsterid) {
			mMonsterId = monsterid;
		}
		
		public void onClick(View v) {
			Intent intent = new Intent(getApplicationContext(), MonsterDialogActivity.class);
			intent.putExtra("id", mMonsterId);
			
			startActivity(intent);
		}
	}

}

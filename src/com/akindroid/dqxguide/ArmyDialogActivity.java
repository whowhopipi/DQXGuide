package com.akindroid.dqxguide;

import java.util.List;

import com.akindroid.dqxguide.content.ItemStateMachine;
import com.akindroid.dqxguide.util.ContentSearchUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ArmyDialogActivity extends Activity {
	private ContentSearchUtil mSearchUtil;
	
	private TextView armyNameTextView;
	private TextView armyBuyTextView;
	private TextView armySellTextView;
	private TextView armyDescTextView;
	private LinearLayout armyRecipeLayout;
	private LinearLayout armyDropLayout;
	private LinearLayout armySaleLayout;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.dialog_army);
	    
	    armyNameTextView = (TextView) findViewById(R.id.dialog_army_name);
	    armyBuyTextView = (TextView) findViewById(R.id.dialog_army_buy);
	    armySellTextView = (TextView) findViewById(R.id.dialog_army_sell);
	    armyDescTextView = (TextView) findViewById(R.id.dialog_army_description);
	    armyRecipeLayout = (LinearLayout) findViewById(R.id.dialog_army_recipe);
	    armyDropLayout = (LinearLayout) findViewById(R.id.dialog_army_drop);
	    armySaleLayout = (LinearLayout) findViewById(R.id.dialog_army_sale);
	    
	    mSearchUtil = ContentSearchUtil.getInstance(getApplicationContext());
	    
	    int id = getIntent().getIntExtra("id", -1);
	    if (id == -1) return;
	    
	    List<ItemStateMachine.Item> item = mSearchUtil.getItem(
	    		ContentSearchUtil.SELECT_ITEM_ONE, new String[] { String.valueOf(id) });
	    if (item.size() < 1) return;
	    
	    armyNameTextView.setText(item.get(0).Name);
	    armyBuyTextView.setText(
    			(item.get(0).Buy == -1)? "ƒoƒU[‚Ì‚Ý" : String.valueOf(item.get(0).Buy));
	    armySellTextView.setText(String.valueOf(item.get(0).Sell));
	    armyDescTextView.setText(item.get(0).Description);
	    
	}
	
	private class MyContentClickListener implements View.OnClickListener {
		public final static int TYPE_MONSTER = 0;
		public final static int TYPE_ITEM = 1;

		private int mContentId;
		private int mType = TYPE_MONSTER;
		
		public MyContentClickListener(int contentid, int type) {
			mContentId = contentid;
			mType = type;
		}
		
		public void onClick(View v) {
			Intent intent = null;
			
			if (mType == TYPE_MONSTER) {
				intent = new Intent(getApplicationContext(), MonsterDialogActivity.class);
			} else if (mType == TYPE_ITEM) {
				intent = new Intent(getApplicationContext(), ItemDialogActivity.class);
			}
			
			if (intent != null) {
				intent.putExtra("id", mContentId);
				startActivity(intent);
			}
		}
	}

}

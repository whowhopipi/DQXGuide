package com.akindroid.dqxguide;

import java.util.List;

import com.akindroid.dqxguide.adapter.ItemListAdapter;
import com.akindroid.dqxguide.adapter.ItemListAdapterItem;
import com.akindroid.dqxguide.content.ItemStateMachine;
import com.akindroid.dqxguide.util.ContentSearchUtil;
import com.akindroid.dqxguide.util.ImageFileUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;

public class ArmyListActivity extends Activity {
	private ContentSearchUtil mSearchUtil;
	private ItemListAdapter mAdapter;
	private ImageFileUtil mImageFileUtil;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setSoftInputMode(
				LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	    setContentView(R.layout.activity_army_menu);
	    
	    mSearchUtil = ContentSearchUtil.getInstance(getApplicationContext());
	    mImageFileUtil = new ImageFileUtil(getApplicationContext());
	    
	    mAdapter = new ItemListAdapter(this, R.layout.list_item_content);
	    initializeListAdapter(mAdapter);
	    
	    ListView lv = (ListView) findViewById(R.id.army_menu_list);
	    lv.setAdapter(mAdapter);
	    lv.setOnItemClickListener(new ListItemClickListener());
	}
	
	private void initializeListAdapter(ItemListAdapter adapter) {
		List<ItemStateMachine.Item> itemList = 
				mSearchUtil.getItem(ContentSearchUtil.SELECT_ARMY_ALL, null);
		
		for (ItemStateMachine.Item item : itemList) {
			adapter.add(getNewAdapterItem(item));
		}
	}
	
	private ItemListAdapterItem getNewAdapterItem(ItemStateMachine.Item item) {
		ItemListAdapterItem newItem = new ItemListAdapterItem();
		newItem.Id = item.Id;
		newItem.Name = item.Name;
		newItem.BuyPrice = (item.Buy == -1) ? "ÉoÉUÅ[ÇÃÇ›" : String.valueOf(item.Buy);
		newItem.SellPrice = String.valueOf(item.Sell);
		newItem.ItemImage = mImageFileUtil.getBitmapImage("leaf.png");

		return newItem;
	}
	
	private class ListItemClickListener implements AdapterView.OnItemClickListener {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			ListView listView = (ListView) parent;
			ItemListAdapterItem selectedItem = 
					(ItemListAdapterItem) listView.getItemAtPosition(position);
			
			Intent intent = new Intent(getApplicationContext(), ArmyDialogActivity.class);
			intent.putExtra("id", selectedItem.Id);
			
			startActivity(intent);
		}
	}

}

package com.akindroid.dqxguide;

import java.util.List;

import com.akindroid.dqxguide.adapter.MonsterListAdapter;
import com.akindroid.dqxguide.adapter.MonsterListAdapterItem;
import com.akindroid.dqxguide.content.MonsterStateMachine;
import com.akindroid.dqxguide.util.ContentSearchUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MonsterListActivity extends Activity {
	private ContentSearchUtil mSearchUtil;
	private MonsterListAdapter mAdapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setSoftInputMode(
				LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	    setContentView(R.layout.activity_monster_menu);
	    
	    mSearchUtil = ContentSearchUtil.getInstance(getApplicationContext());
	    
	    mAdapter = new MonsterListAdapter(this, R.layout.list_monster_content);
	    initializeListAdapter(mAdapter);
	    
	    final EditText searchEditText = (EditText) findViewById(R.id.monster_menu_search);
	    searchEditText.addTextChangedListener(new MySearchTextWatcher());
	    
	    final Button searchClearButton = (Button) findViewById(R.id.monster_menu_clear);
	    searchClearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				searchEditText.setText("");
			}
		});
	    
	    ListView lv = (ListView) findViewById(R.id.monster_menu_list);
	    lv.setAdapter(mAdapter);
	    lv.setOnItemClickListener(new ListItemClickListener());
	}
	
	private void initializeListAdapter(MonsterListAdapter adapter) {
		List<MonsterStateMachine.Monster> monsterList = 
				mSearchUtil.getMonster(ContentSearchUtil.SELECT_MONSTER_ALL, null);
		
		for (MonsterStateMachine.Monster monster : monsterList) {
			adapter.add(getNewAdapterItem(monster));
		}
	}
	
	private void updateListAdapter(MonsterListAdapter adapter, String searchString) {
		List<MonsterStateMachine.Monster> monsterList = 
				mSearchUtil.getMonster(ContentSearchUtil.SELECT_MONSTER_SEARCH, new String[] { searchString });
		
		for (MonsterStateMachine.Monster monster : monsterList) {
			adapter.add(getNewAdapterItem(monster));
		}
	}
		
	private MonsterListAdapterItem getNewAdapterItem(MonsterStateMachine.Monster monster) {
		MonsterListAdapterItem newItem = new MonsterListAdapterItem();
		newItem.Id = monster.Id;
		newItem.Name = monster.Name;
		newItem.Hp = String.valueOf(monster.Hp);
		newItem.Exp = String.valueOf(monster.Exp);
		newItem.Gold = String.valueOf(monster.Gold);
		newItem.Cmn = String.valueOf(monster.Cmn);
		newItem.Rare = String.valueOf(monster.Rare);
		
		return newItem;
	}

	private class ListItemClickListener implements AdapterView.OnItemClickListener {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			ListView listView = (ListView) parent;
			MonsterListAdapterItem selectedItem = 
					(MonsterListAdapterItem) listView.getItemAtPosition(position);
			
			Intent intent = new Intent(getApplicationContext(), MonsterDialogActivity.class);
			intent.putExtra("id", selectedItem.Id);
			
			startActivity(intent);
		}
	}
	
	private class MySearchTextWatcher implements TextWatcher {
		public void afterTextChanged(Editable s) {
			mAdapter.clear();
			
			if (s.toString().equals("")) {
				initializeListAdapter(mAdapter);
			} else {
				updateListAdapter(mAdapter, "%" + s.toString() + "%");
			}
		}

		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			;
		}

		public void onTextChanged(CharSequence s, int start, int before, int count) {
			;
		}
	}
	
}

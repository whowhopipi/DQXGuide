package com.akindroid.dqxguide.adapter;

import java.util.List;

import com.akindroid.dqxguide.R;
import com.akindroid.dqxguide.content.ItemStateMachine;
import com.akindroid.dqxguide.util.ContentSearchUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MonsterListAdapter extends ArrayAdapter<MonsterListAdapterItem> {
	private Context mContext;
	private int mTextViewResourceId;
	private ContentSearchUtil mSearchUtil;
	
	public MonsterListAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		
		mContext = context;
		mTextViewResourceId = textViewResourceId;
		mSearchUtil = ContentSearchUtil.getInstance(context);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.
					getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(mTextViewResourceId, null);
		}
		
		MonsterListAdapterItem item = getItem(position);
		
		TextView monsterName = (TextView) view.findViewById(R.id.list_monster_name);
		monsterName.setText(item.Name);
		
		TextView monsterHp = (TextView) view.findViewById(R.id.list_monster_hp);
		monsterHp.setText(item.Hp);
		
		TextView monsterExp = (TextView) view.findViewById(R.id.list_monster_exp);
		monsterExp.setText(item.Exp);
		
		TextView monsterGold = (TextView) view.findViewById(R.id.list_monster_gold);
		monsterGold.setText(item.Gold);
		
		List<ItemStateMachine.Item> cmnItemList = mSearchUtil.getItem(
				ContentSearchUtil.SELECT_ITEM_ONE, new String[] { item.Cmn });
		if (cmnItemList.size() > 0) {
			TextView monsterCmn = (TextView) view.findViewById(R.id.list_monster_common);
			monsterCmn.setText(cmnItemList.get(0).Name);
		}
		
		List<ItemStateMachine.Item> rareItemList = mSearchUtil.getItem(
				ContentSearchUtil.SELECT_ITEM_ONE, new String[] { item.Rare });
		if (rareItemList.size() > 0) {
			TextView monsterRare = (TextView) view.findViewById(R.id.list_monster_rare);
			monsterRare.setText(rareItemList.get(0).Name);
		}

		return view;
	}
}

package com.akindroid.dqxguide.fragment;

import java.util.ArrayList;
import java.util.List;

import com.akindroid.dqxguide.R;
import com.akindroid.dqxguide.adapter.CraftAlchemyListAdapter;
import com.akindroid.dqxguide.adapter.CraftListAdapter;
import com.akindroid.dqxguide.adapter.CraftListAdapterItem;
import com.akindroid.dqxguide.content.ArmyStateMachine;
import com.akindroid.dqxguide.content.ArmyTypeStateMachine;
import com.akindroid.dqxguide.content.ItemStateMachine;
import com.akindroid.dqxguide.util.ContentSearchUtil;
import com.akindroid.dqxguide.util.ImageFileUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CraftListFragment extends Fragment {
	private int mCurrentType = CRAFT_BUKI;
	
	private ImageFileUtil mImageFileUtil;
	private ContentSearchUtil mSearchUtil;

	private List<Integer> mArmyTypeList = new ArrayList<Integer>();
	
	public static final int CRAFT_BUKI = 0;
	public static final int CRAFT_MOKKOU = 1;
	public static final int CRAFT_BOUGU = 2;
	public static final int CRAFT_SAIHOU = 3;
	public static final int CRAFT_TSUBO = 4;
	public static final int CRAFT_RANPU = 5;
	public static final int CRAFT_DOUGU = 6;
		
	public CraftListFragment(int lunchType) {
		super();
		
		mCurrentType = lunchType;
		mSearchUtil = ContentSearchUtil.getInstance(getActivity());
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_item, null);

		mImageFileUtil = new ImageFileUtil(getActivity());
		
		ArrayAdapter<CraftListAdapterItem> adapter = null;
		if (mCurrentType == CRAFT_TSUBO || mCurrentType == CRAFT_RANPU) {
			adapter = new CraftAlchemyListAdapter(getActivity(), R.layout.list_craft_alchemy_content);
		} else {
			adapter = new CraftListAdapter(getActivity(), R.layout.list_craft_content);
		}
		initializeListAdapter(adapter);
		
		ListView lv = (ListView) view.findViewById(R.id.item_menu_list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new ListCraftClickListener());
		
		return view;
	}
	
	private void initializeListAdapter(ArrayAdapter<CraftListAdapterItem> adapter) {
		adapter.addAll(getAdapterList(mCurrentType));
	}
	
	private class ListCraftClickListener implements AdapterView.OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Click Event
			Log.w("CraftListFragment", "item clicked = " + position);
		}
		
	}
	
	private List<CraftListAdapterItem> getAdapterList(int craftType) {
		List<CraftListAdapterItem> craftList = new ArrayList<CraftListAdapterItem>();
		
		List<ArmyStateMachine.Army> armyList = mSearchUtil.getArmy(
				ContentSearchUtil.SELECT_ARMY_CTYPE, new String[] { String.valueOf(craftType) });
		
		for (ArmyStateMachine.Army army : armyList) {
			CraftListAdapterItem newItem = new CraftListAdapterItem();

			List<ItemStateMachine.Item> itemList = mSearchUtil.getItem(
					ContentSearchUtil.SELECT_ITEM_ONE, new String[] { String.valueOf(army.Iid) });
			if (itemList.size() == 0) continue;
			if (army.Klv == -1) continue;
			
			if (!mArmyTypeList.contains(army.Type)) {
				List<ArmyTypeStateMachine.ArmyType> armyTypeList = mSearchUtil.getArmyType(
						ContentSearchUtil.SELECT_ARMY_TYPE_ONE, new String[] { String.valueOf(army.Type) });
				
				if (armyTypeList.size() != 0) {
					newItem.TitleName = armyTypeList.get(0).Name;
				}
				mArmyTypeList.add(army.Type);
			}

			newItem.Id = army.Iid;
//			newItem.CraftImage = mImageFileUtil.getBitmapImage(itemList.get(0).Icon);
			newItem.CraftImage = mImageFileUtil.getBitmapImage("leaf.png");
			newItem.Name = itemList.get(0).Name;
			newItem.Lv = army.Lv;
			newItem.CraftLv = army.Klv;
			newItem.BuyPrice = itemList.get(0).Buy;
			newItem.SellPrice = itemList.get(0).Sell;
			
			craftList.add(newItem);
		}
		
		return craftList;
	}

}

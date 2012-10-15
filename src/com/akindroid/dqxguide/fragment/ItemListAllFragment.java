package com.akindroid.dqxguide.fragment;

import java.util.List;

import com.akindroid.dqxguide.ItemDialogActivity;
import com.akindroid.dqxguide.R;
import com.akindroid.dqxguide.adapter.ItemListAdapter;
import com.akindroid.dqxguide.adapter.ItemListAdapterItem;
import com.akindroid.dqxguide.content.ItemStateMachine;
import com.akindroid.dqxguide.util.ContentSearchUtil;
import com.akindroid.dqxguide.util.ImageFileUtil;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class ItemListAllFragment extends Fragment {
	private ImageFileUtil imageFileUtil;
	private ContentSearchUtil mSearchUtil;
	
	public static final int TYPE_ALL = 0;
	public static final int TYPE_MATERIAL = 1;
	public static final int TYPE_USES = 2;
	
	private int mCurrentType = TYPE_ALL;
	
	public ItemListAllFragment(int lunchType) {
		super();
		
		mCurrentType = lunchType;
		mSearchUtil = ContentSearchUtil.getInstance(getActivity());
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_item, null);

		imageFileUtil = new ImageFileUtil(getActivity());
		
		ItemListAdapter adapter = new ItemListAdapter(getActivity(), R.layout.list_item_content);
		initializeListAdapter(adapter);

		ListView lv = (ListView) view.findViewById(R.id.item_menu_list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new ListItemClickListener());
		
		return view;
	}

	private void initializeListAdapter(ItemListAdapter adapter) {
		List<ItemStateMachine.Item> itemList = null;

		if (mCurrentType == TYPE_ALL) {
			itemList = mSearchUtil.getItem(ContentSearchUtil.SELECT_ITEM_ALL, null);
		} else if (mCurrentType == TYPE_MATERIAL) {
			itemList = mSearchUtil.getItem(ContentSearchUtil.SELECT_ITEM_MATERIAL, null);
		} else if (mCurrentType == TYPE_USES) {
			itemList = mSearchUtil.getItem(ContentSearchUtil.SELECT_ITEM_USES, null);
		}
		
		for (ItemStateMachine.Item item : itemList) {
			String buyStr = (item.Buy == -1) ? "ÉoÉUÅ[ÇÃÇ›" : item.Buy + "G";
			
			adapter.add(getNewAdapterItem(item.Id, 
					item.Name, "leaf.png", buyStr, item.Sell + "G"));
		}
	 }
	
	 private ItemListAdapterItem getNewAdapterItem(int id, 
			 String name, String imageName, String buyPrice, String sellPrice) {
		 ItemListAdapterItem newItem = new ItemListAdapterItem();
		 newItem.Id = id;
		 newItem.Name = name;
		 newItem.ItemImage = imageFileUtil.getBitmapImage(imageName);
		 newItem.BuyPrice = buyPrice;
		 newItem.SellPrice = sellPrice;
		
		 return newItem;
	 }
	
	 private class ListItemClickListener implements AdapterView.OnItemClickListener {
	
		 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			 ListView listView = (ListView) parent;
			 ItemListAdapterItem selectedItem = 
					 (ItemListAdapterItem) listView.getItemAtPosition(position);

			 Intent intent = new Intent(getActivity(), ItemDialogActivity.class);
			 intent.putExtra("id", selectedItem.Id);
			 
			 startActivity(intent);
		 }
	
	 }
}

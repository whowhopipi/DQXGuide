package com.akindroid.dqxguide.adapter;

import com.akindroid.dqxguide.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemListAdapter extends ArrayAdapter<ItemListAdapterItem> {
	private Context mContext;
	private int mTextViewResourceId;
	
	public ItemListAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		
		mContext = context;
		mTextViewResourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.
					getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(mTextViewResourceId, null);
		}
		
		ItemListAdapterItem item = getItem(position);
		
		ImageView itemIconImage = (ImageView) view.findViewById(R.id.list_item_image);
		itemIconImage.setImageBitmap(item.ItemImage);
		
		TextView itemName = (TextView) view.findViewById(R.id.list_item_title);
		itemName.setText(item.Name);
		
		TextView itemBuyPrice = (TextView) view.findViewById(R.id.list_item_buy_price);
		itemBuyPrice.setText(item.BuyPrice);
		
		TextView itemSellPrice = (TextView) view.findViewById(R.id.list_item_sell_price);
		itemSellPrice.setText(item.SellPrice);
		
		return view;
	}
}

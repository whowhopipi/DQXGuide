package com.akindroid.dqxguide.adapter;

import com.akindroid.dqxguide.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CraftListAdapter extends ArrayAdapter<CraftListAdapterItem> {
	private Context mContext;
	private int mTextViewResourceId;
	
	public CraftListAdapter(Context context, int textViewResourceId) {
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
		
		CraftListAdapterItem item = getItem(position);

		TextView separaterTitle = (TextView) view.findViewById(R.id.list_craft_separator_text);
		LinearLayout separater = (LinearLayout) view.findViewById(R.id.list_craft_separator);
		if (item.TitleName != null) {
			separaterTitle.setText(item.TitleName);
			separater.setVisibility(View.VISIBLE);
		} else {
			separater.setVisibility(View.GONE);
		}
		
		ImageView craftImage = (ImageView) view.findViewById(R.id.list_craft_image);
		craftImage.setImageBitmap(item.CraftImage);
		
		TextView craftName = (TextView) view.findViewById(R.id.list_craft_title);
		craftName.setText(item.Name);
		
		TextView craftKLvTextView = (TextView) view.findViewById(R.id.list_craft_craft_lv);
		craftKLvTextView.setText(String.valueOf(item.CraftLv));
		
		TextView craftLvTextView = (TextView) view.findViewById(R.id.list_craft_lv);
		craftLvTextView.setText(String.valueOf(item.Lv));
		
		TextView craftBuyTextView = (TextView) view.findViewById(R.id.list_craft_buy_price);
		craftBuyTextView.setText(String.valueOf(item.BuyPrice));
		
		TextView craftSellTextView = (TextView) view.findViewById(R.id.list_craft_sell_price);
		craftSellTextView.setText(String.valueOf(item.SellPrice));
				
		return view;
	}

}

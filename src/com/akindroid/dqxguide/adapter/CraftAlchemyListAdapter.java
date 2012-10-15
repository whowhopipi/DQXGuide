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

public class CraftAlchemyListAdapter extends ArrayAdapter<CraftListAdapterItem> {
	private Context mContext;
	private int mTextViewResourceId;
	
	public CraftAlchemyListAdapter(Context context, int textViewResourceId) {
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

		ImageView craftImage = (ImageView) view.findViewById(R.id.list_craft_alchemy_image);
		craftImage.setImageBitmap(item.CraftImage);
		
		TextView craftName = (TextView) view.findViewById(R.id.list_craft_alchemy_title);
		craftName.setText(item.Name);
		
		TextView craftKLvTextView = (TextView) view.findViewById(R.id.list_craft_alchemy_craft_lv);
		craftKLvTextView.setText(String.valueOf(item.CraftLv));

		return view;
	}

}

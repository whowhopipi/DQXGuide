package com.akindroid.dqxguide.adapter;


import com.akindroid.dqxguide.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenuListAdapter extends ArrayAdapter<MainMenuAdapterItem> {
	private Context mContext;
	private int mTextViewResourceId;
	
	public MainMenuListAdapter(Context context, int textViewResourceId) {
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
		
		MainMenuAdapterItem item = getItem(position);
		TextView txtView = (TextView) view.findViewById(R.id.list_main_menu_title);
		txtView.setText(item.Title);
		if (!item.Enabled) txtView.setTextColor(Color.GRAY);
		
		ImageView menuIconImage = (ImageView) view.findViewById(R.id.list_main_menu_image);
		menuIconImage.setImageBitmap(item.MenuImage);
		
		ImageView arrowImage = (ImageView) view.findViewById(R.id.list_main_menu_arrow);
		if (!item.Enabled) arrowImage.setVisibility(View.GONE);
		
		return view;
	}
	
}

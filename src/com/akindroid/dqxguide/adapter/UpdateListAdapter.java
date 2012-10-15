package com.akindroid.dqxguide.adapter;

import com.akindroid.dqxguide.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UpdateListAdapter extends ArrayAdapter<UpdateListAdapterItem> {
	private Context mContext;
	private int mTextViewResourceId;
	
	public UpdateListAdapter(Context context, int textViewResourceId) {
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
		
		UpdateListAdapterItem item = getItem(position);
		
		TextView versionText = (TextView) view.findViewById(R.id.list_update_version);
		versionText.setText(item.Version);
		
		LinearLayout descriptionLayout = 
				(LinearLayout) view.findViewById(R.id.list_update_description);
		descriptionLayout.removeAllViews();
		
		for (String description : item.Descriptions) {
			TextView newTextView = new TextView(mContext);
			newTextView.setTextColor(mContext.getResources().getColor(android.R.color.darker_gray));
			newTextView.setText("ÅE " + description);
			newTextView.setTextAppearance(mContext, android.R.attr.textAppearanceSmall);
			newTextView.setTextSize(12.0f);
			
			descriptionLayout.addView(newTextView);
		}
		
		return view;
	}
}

package com.akindroid.dqxguide.adapter;

import java.util.List;

import com.akindroid.dqxguide.ItemDialogActivity;
import com.akindroid.dqxguide.R;
import com.akindroid.dqxguide.content.AreaStateMachine;
import com.akindroid.dqxguide.content.ItemStateMachine;
import com.akindroid.dqxguide.content.QuestHireStateMachine;
import com.akindroid.dqxguide.util.ContentSearchUtil;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuestListAdapter extends ArrayAdapter<QuestListAdapterItem> {
	private Context mContext;
	private int mTextViewResourceId;
	private ContentSearchUtil mSearchUtil;
	
	public QuestListAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		
		mContext = context;
		mTextViewResourceId = textViewResourceId;
		mSearchUtil = ContentSearchUtil.getInstance(mContext);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.
					getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(mTextViewResourceId, null);
		}
		
		QuestListAdapterItem item = getItem(position);
		
		TextView questId = (TextView) view.findViewById(R.id.list_quest_id);
		questId.setText(String.format("%03d", item.Id));
			
		TextView questName = (TextView) view.findViewById(R.id.list_quest_name);
		questName.setText(item.Name);
		
		TextView questArea = (TextView) view.findViewById(R.id.list_quest_area);
		if (item.Area != null && !item.Area.isEmpty()) {
			questArea.setText(getAreaName(item.Area));
		} else {
			questArea.setText("");
		}
		
		LinearLayout questProv = (LinearLayout) view.findViewById(R.id.list_quest_prov);
		TextView questProvText = (TextView) view.findViewById(R.id.list_quest_prov_text);
		if (item.Prov == null || item.Prov.isEmpty()) {
			questProv.setVisibility(View.GONE);
		} else {
			questProvText.setText(item.Prov);
		}
		
		TextView questFame = (TextView) view.findViewById(R.id.list_quest_fame);
		questFame.setText(String.valueOf(item.Fame));
		
		// Quest Hire
		LinearLayout questHire = (LinearLayout) view.findViewById(R.id.list_quest_hire);
		questHire.removeAllViews();
		
		TextView hireTextView = createNewTextView("ïÒèV:");
		questHire.addView(hireTextView);
		
		List<QuestHireStateMachine.QuestHire> questHireList = mSearchUtil.getQuestHire(
				ContentSearchUtil.SELECT_QUEST_HIRE, new String[] { String.valueOf(item.Id) });
		for (QuestHireStateMachine.QuestHire qh : questHireList) {
			TextView newHireItemName = createNewTextView("");
			newHireItemName.setPadding(10, 0, 0, 0);
			TextView newHireItemCount = createNewTextView("");
			newHireItemCount.setPadding(10, 0, 0, 0);
			
			if (qh.Hire != null && !qh.Hire.isEmpty()) {
				newHireItemName.setText(qh.Hire);
				newHireItemCount.setText("");
			} else {
				if (qh.HireItemId > 0) {
					newHireItemName.setText(getItemName(qh.HireItemId));
					setLinkMode(newHireItemName, qh.HireItemId);
					newHireItemCount.setText("x" + String.valueOf(qh.HireItemCount));
				} else {
					newHireItemName.setText("");
					newHireItemCount.setText("");
				}
			}
			
			questHire.addView(newHireItemName);
			questHire.addView(newHireItemCount);
		}
		
		// Quest Rehire
		LinearLayout questRehire = (LinearLayout) view.findViewById(R.id.list_quest_rehire);
		questRehire.removeAllViews();
		
		TextView rehireTextView = createNewTextView("ÉäÉvÉåÉC:");
		questRehire.addView(rehireTextView);
		List<QuestHireStateMachine.QuestHire> questRehireList = mSearchUtil.getQuestHire(
				ContentSearchUtil.SELECT_QUEST_REHIRE, new String[] { String.valueOf(item.Id) });
		for (QuestHireStateMachine.QuestHire qh : questRehireList) {
			TextView newHireItemName = createNewTextView("");
			newHireItemName.setPadding(10, 0, 0, 0);
			TextView newHireItemCount = createNewTextView("");
			newHireItemCount.setPadding(10, 0, 0, 0);
			
			if (qh.Hire != null && !qh.Hire.isEmpty()) {
				newHireItemName.setText(qh.Hire);
				newHireItemCount.setText("");
			} else {
				if (qh.HireItemId > 0) {
					newHireItemName.setText(getItemName(qh.HireItemId));
					setLinkMode(newHireItemName, qh.HireItemId);
					newHireItemCount.setText("x" + String.valueOf(qh.HireItemCount));
				} else {
					newHireItemName.setText("");
					newHireItemCount.setText("");
				}
			}
			
			questRehire.addView(newHireItemName);
			questRehire.addView(newHireItemCount);
		}

		LinearLayout questFlow = (LinearLayout) view.findViewById(R.id.list_quest_flow);
		questFlow.removeAllViews();
		
		int counter = 1;
		for (String flowDescription : item.FlowList) {
			TextView newTextView = createNewTextView(
					String.valueOf(counter++) + ". " + flowDescription);
			
			questFlow.addView(newTextView);
		}

		return view;
	}
	
	private TextView createNewTextView(String initString) {
		TextView newTextView = new TextView(mContext);
		newTextView.setTextColor(mContext.getResources().getColor(android.R.color.darker_gray));
		newTextView.setText(initString);
		newTextView.setTextAppearance(mContext, android.R.attr.textAppearanceSmall);
		newTextView.setTextSize(12.0f);
		
		return newTextView;
	}

	private String getItemName(int itemId) {
		List<ItemStateMachine.Item> itemList =
				mSearchUtil.getItem(ContentSearchUtil.SELECT_ITEM_ONE, 
						new String[] { String.valueOf(itemId) });
		
		if (itemList.size() > 0) {
			return itemList.get(0).Name;
		} else {
			return null;
		}
	}
	
	private String getAreaName(String areaid) {
		List<AreaStateMachine.Area> areaList =
				mSearchUtil.getArea(ContentSearchUtil.SELECT_AREA_ONE,
						new String[] { areaid });
		
		if (areaList.size() > 0) {
			return areaList.get(0).Name;
		} else {
			return null;
		}
	}
	
	private void setLinkMode(TextView txtView, int itemId) {
		if (itemId > 0) {
			txtView.setTextColor(Color.BLUE);
			txtView.getPaint().setUnderlineText(true);
			txtView.setOnClickListener(new MyItemClickListener(itemId));
		} else {
			txtView.setTextColor(mContext.getResources().getColor(android.R.color.darker_gray));
			txtView.getPaint().setUnderlineText(false);
			txtView.setOnClickListener(null);
		}
	}
	
	private class MyItemClickListener implements View.OnClickListener {
		private int mItemId;
		
		public MyItemClickListener(int itemId) {
			mItemId = itemId;
		}
		
		public void onClick(View v) {
			Intent intent = new Intent(mContext, ItemDialogActivity.class);
			intent.putExtra("id", mItemId);
			
			mContext.startActivity(intent);
		}
	}
	
}

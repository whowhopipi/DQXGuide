package com.akindroid.dqxguide.fragment;

import java.util.ArrayList;
import java.util.List;

import com.akindroid.dqxguide.R;
import com.akindroid.dqxguide.adapter.CraftAlchemyListAdapter;
import com.akindroid.dqxguide.adapter.CraftListAdapter;
import com.akindroid.dqxguide.adapter.CraftListAdapterItem;
import com.akindroid.dqxguide.adapter.QuestListAdapter;
import com.akindroid.dqxguide.adapter.QuestListAdapterItem;
import com.akindroid.dqxguide.content.ArmyStateMachine;
import com.akindroid.dqxguide.content.ArmyTypeStateMachine;
import com.akindroid.dqxguide.content.ItemStateMachine;
import com.akindroid.dqxguide.content.QuestFlowStateMachine;
import com.akindroid.dqxguide.content.QuestHireStateMachine;
import com.akindroid.dqxguide.content.QuestStateMachine;
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

public class QuestListFragment extends Fragment {
	private int mCurrentType = QUEST_1;
	
	public static final int QUEST_1 = 0;
	public static final int QUEST_2 = 1;
	public static final int QUEST_3 = 2;
	
	private ImageFileUtil mImageFileUtil;
	private ContentSearchUtil mSearchUtil;
	
	public QuestListFragment(int lunchType) {
		super();
		
		mCurrentType = lunchType;
		mSearchUtil = ContentSearchUtil.getInstance(getActivity());
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_item, null);

		mImageFileUtil = new ImageFileUtil(getActivity());
		
		ArrayAdapter<QuestListAdapterItem> adapter =
				new QuestListAdapter(getActivity(), R.layout.list_quest_content);
		initializeListAdapter(adapter);
		
		ListView lv = (ListView) view.findViewById(R.id.item_menu_list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new ListQuestClickListener());
		
		return view;
	}
	
	private class ListQuestClickListener implements AdapterView.OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Click Event
			Log.w("QuestListFragment", "item clicked = " + position);
		}
		
	}

	private void initializeListAdapter(ArrayAdapter<QuestListAdapterItem> adapter) {
		List<QuestListAdapterItem> questList = new ArrayList<QuestListAdapterItem>();
		
		String[] searchSpace;
		if (mCurrentType == QUEST_1) {
			searchSpace = new String[] { "0", "50" };
		} else if (mCurrentType == QUEST_2) {
			searchSpace = new String[] { "51", "100" };
		} else if (mCurrentType == QUEST_3) {
			searchSpace = new String[] { "101", "150" };
		} else {
			searchSpace = new String[] { "0", "50" };
		}
		
		List<QuestStateMachine.Quest> rawQuestList = mSearchUtil.getQuest(
				ContentSearchUtil.SELECT_QUEST_SPACE, searchSpace);
		for (QuestStateMachine.Quest quest : rawQuestList) {
			QuestListAdapterItem newItem = new QuestListAdapterItem();

			newItem.Id = quest.Id;
			newItem.Name = quest.Name;
			newItem.Area = quest.Area;
			newItem.Prov = quest.Prov;
			newItem.Fame = quest.Fame;
			newItem.FlowList = getFlowList(quest.Id);
			
			questList.add(newItem);
		}
		
		adapter.addAll(questList);
	}
	
	private List<String> getFlowList(int questId) {
		List<String> result = new ArrayList<String>();
		List<QuestFlowStateMachine.QuestFlow> questFlowList =
				mSearchUtil.getQuestFlow(ContentSearchUtil.SELECT_QUEST_FLOW_ONE, 
						new String[] { String.valueOf(questId) });
		
		for (QuestFlowStateMachine.QuestFlow questFlow : questFlowList) {
			result.add(questFlow.Flow);
		}

		return result;
	}
	
	
}

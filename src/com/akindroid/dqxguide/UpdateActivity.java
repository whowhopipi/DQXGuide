package com.akindroid.dqxguide;

import java.util.ArrayList;
import java.util.List;

import com.akindroid.dqxguide.adapter.UpdateListAdapter;
import com.akindroid.dqxguide.adapter.UpdateListAdapterItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

public class UpdateActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_update_menu);
        
        TextView backButton = (TextView) findViewById(R.id.update_menu_back);
        backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
        
        UpdateListAdapter adapter = new UpdateListAdapter(this, R.layout.list_update_content);
        initializeListAdapter(adapter);
        
        ListView updateList = (ListView) findViewById(R.id.update_menu_list);
        updateList.setAdapter(adapter);
	}
	
	private void initializeListAdapter(UpdateListAdapter adapter) {
		UpdateListAdapterItem newItem = new UpdateListAdapterItem();
		newItem.Version = "2012/10/15 : ver 0.1";
		
		List<String> descList = new ArrayList<String>();
		descList.add("新規作成");
		descList.add("色々と変更");
		newItem.Descriptions = descList;
		
		adapter.add(newItem);
	}

}

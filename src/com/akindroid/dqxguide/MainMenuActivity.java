package com.akindroid.dqxguide;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.akindroid.dqxguide.adapter.MainMenuAdapterItem;
import com.akindroid.dqxguide.adapter.MainMenuListAdapter;
import com.akindroid.dqxguide.content.ContentDatabaseHelper;
import com.akindroid.dqxguide.util.ContentSearchUtil;
import com.akindroid.dqxguide.util.ImageFileUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

public class MainMenuActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main_menu);
        
        ContentSearchUtil.getInstance(getApplicationContext());
        
        ImageFileUtil util = new ImageFileUtil(this);
        try {
			util.initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        MainMenuListAdapter adapter = new MainMenuListAdapter(this, R.layout.list_main_menu);
        initializeListAdapter(adapter);
                
        ListView lv = (ListView) findViewById(R.id.main_menu_list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new ListItemClickListener());
    }
    
    private void initializeListAdapter(MainMenuListAdapter adapter) {
        adapter.add(getNewAdapterItem("ストーリー攻略", "leaf.png", ItemListActivity.class, false));
        adapter.add(getNewAdapterItem("クエスト", "leaf.png", QuestActivity.class, true));
        adapter.add(getNewAdapterItem("職業", "leaf.png", WorkActivity.class, false));
        adapter.add(getNewAdapterItem("職人", "leaf.png", CraftActivity.class, true));
        adapter.add(getNewAdapterItem("アイテム", "leaf.png", ItemListActivity.class, true));
        adapter.add(getNewAdapterItem("装備", "leaf.png", ArmyListActivity.class, true));
        adapter.add(getNewAdapterItem("ワールドマップ", "leaf.png", ItemListActivity.class, false));
        adapter.add(getNewAdapterItem("モンスター", "leaf.png", MonsterListActivity.class, true));
        adapter.add(getNewAdapterItem("お知らせ", "leaf.png", UpdateActivity.class, true));
        adapter.add(getNewAdapterItem("報告", "leaf.png", ReportActivity.class, true));
    }
    
    private MainMenuAdapterItem getNewAdapterItem(String title, String imgName, 
    		Class<?> lunchActivity, boolean enabled) {
    	MainMenuAdapterItem newItem = new MainMenuAdapterItem();
    	newItem.Title = title;
    	newItem.MenuImage = getBitmapImage(imgName);
    	newItem.LunchActivity = new Intent(getApplicationContext(), lunchActivity);
    	newItem.Enabled = enabled;

    	return newItem;
    }
    
    private Bitmap getBitmapImage(String imageName) {
    	Bitmap image = null;
	
		InputStream inputStream = null;
		try {
			inputStream = openFileInput(imageName);
			image = BitmapFactory.decodeStream(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return image;
    }
    
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			MainMenuAdapterItem item = (MainMenuAdapterItem) parent.getItemAtPosition(position);
			
			if (item.Enabled) {
				startActivity(item.LunchActivity);
			}
		}
    }
}

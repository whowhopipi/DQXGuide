package com.akindroid.dqxguide;

import com.akindroid.dqxguide.fragment.QuestListFragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

public class QuestActivity extends FragmentActivity {
	private static final int PAGE_SIZE = 3;
	
	private static final int QUEST_1 = 0;
	private static final int QUEST_2 = 1;
	private static final int QUEST_3 = 2;
	
	private TextView questBack;
	private TextView questTab1;
	private TextView questTab2;
	private TextView questTab3;
	
	private MyPagerAdapter mAdapter;
	private ViewPager mPager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.activity_quest_menu);
		
		questBack = (TextView) findViewById(R.id.quest_menu_back);
		questBack.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		questTab1 = (TextView) findViewById(R.id.quest_tab_1);
		questTab1.setOnClickListener(new MySelectPageListener(QUEST_1));
		
		questTab2 = (TextView) findViewById(R.id.quest_tab_2);
		questTab2.setOnClickListener(new MySelectPageListener(QUEST_2));
		
		questTab3 = (TextView) findViewById(R.id.quest_tab_3);
		questTab3.setOnClickListener(new MySelectPageListener(QUEST_3));
		
		mAdapter = new MyPagerAdapter(getSupportFragmentManager());

		mPager = (ViewPager) findViewById(R.id.quest_pager);
		mPager.setPageMargin(20);
		mPager.setPageMarginDrawable(R.drawable.page_separator);
		mPager.setOnPageChangeListener(new MyPageChangeListener());
		mPager.setAdapter(mAdapter);
		
		refleshTabMenu(0);
	}
	
	private class MyPagerAdapter extends FragmentStatePagerAdapter {
		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			
			switch (position) {
			case QUEST_1:
				fragment = new QuestListFragment(QuestListFragment.QUEST_1);
				break;
			case QUEST_2:
				fragment = new QuestListFragment(QuestListFragment.QUEST_2);
				break;
			case QUEST_3:
				fragment = new QuestListFragment(QuestListFragment.QUEST_3);
				break;
			}
			
			return fragment;
		}

		@Override
		public int getCount() {
			return PAGE_SIZE;
		}
	}
	
	private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

		public void onPageScrollStateChanged(int state) {
			
		}

		public void onPageScrolled(int position, 
				float positionOffset, int positionOffsetPixels) {
			
		}

		public void onPageSelected(int position) {
			refleshTabMenu(position);
		}
		
	}
	
	private void refleshTabMenu(int position) {
		questTab1.setTextColor(Color.BLUE);
		questTab1.getPaint().setUnderlineText(false);
		questTab2.setTextColor(Color.BLUE);
		questTab2.getPaint().setUnderlineText(false);
		questTab3.setTextColor(Color.BLUE);
		questTab3.getPaint().setUnderlineText(false);
		
		switch (position) {
		case QUEST_1:
			questTab1.setTextColor(Color.WHITE);
			questTab1.getPaint().setUnderlineText(true);
			break;
		case QUEST_2:
			questTab2.setTextColor(Color.WHITE);
			questTab2.getPaint().setUnderlineText(true);
			break;
		case QUEST_3:
			questTab3.setTextColor(Color.WHITE);
			questTab3.getPaint().setUnderlineText(true);
			break;
		}
	}
	
	private class MySelectPageListener implements View.OnClickListener {
		private int mTargetPage;
		
		public MySelectPageListener(int targetPage) {
			mTargetPage = targetPage;
		}
		
		public void onClick(View v) {
			mPager.setCurrentItem(mTargetPage);
			refleshTabMenu(mTargetPage);
		}
	}

}

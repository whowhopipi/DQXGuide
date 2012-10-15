package com.akindroid.dqxguide;

import com.akindroid.dqxguide.fragment.ItemListAllFragment;

import android.R.color;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

public class ItemListActivity extends FragmentActivity {
	private static final int PAGE_SIZE = 3;
	private static final int ITEMS_ALL = 0;
	private static final int ITEMS_MATERIAL = 1;
	private static final int ITEMS_USES = 2;
	
	private TextView itemTabAll;
	private TextView itemTabMaterial;
	private TextView itemTabUses;
	
	private MyPagerAdapter mAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.activity_item_menu);

		itemTabAll = (TextView) findViewById(R.id.item_tab_all);
		itemTabMaterial = (TextView) findViewById(R.id.item_tab_material);
		itemTabUses = (TextView) findViewById(R.id.item_tab_uses);
		
		mAdapter = new MyPagerAdapter(getSupportFragmentManager());
		
		ViewPager mPager = (ViewPager) findViewById(R.id.pager);
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
			case ITEMS_ALL:
				fragment = new ItemListAllFragment(ItemListAllFragment.TYPE_ALL);
				break;
			case ITEMS_MATERIAL:
				fragment = new ItemListAllFragment(ItemListAllFragment.TYPE_MATERIAL);
				break;
			case ITEMS_USES:
				fragment = new ItemListAllFragment(ItemListAllFragment.TYPE_USES);
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
		itemTabAll.setTextColor(Color.BLUE);
		itemTabAll.getPaint().setUnderlineText(false);
		itemTabMaterial.setTextColor(Color.BLUE);
		itemTabMaterial.getPaint().setUnderlineText(false);
		itemTabUses.setTextColor(Color.BLUE);
		itemTabUses.getPaint().setUnderlineText(false);
		
		switch (position) {
		case ITEMS_ALL:
			itemTabAll.setTextColor(Color.WHITE);
			itemTabAll.getPaint().setUnderlineText(true);
			break;
		case ITEMS_MATERIAL:
			itemTabMaterial.setTextColor(Color.WHITE);
			itemTabMaterial.getPaint().setUnderlineText(true);
			break;
		case ITEMS_USES:
			itemTabUses.setTextColor(Color.WHITE);
			itemTabUses.getPaint().setUnderlineText(true);
			break;
		}
	}

}

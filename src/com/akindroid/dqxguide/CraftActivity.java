package com.akindroid.dqxguide;

import com.akindroid.dqxguide.fragment.CraftListFragment;
import com.akindroid.dqxguide.fragment.ItemListAllFragment;

import android.app.Activity;
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

public class CraftActivity extends FragmentActivity {
	private static final int PAGE_SIZE = 7;
	private static final int CRAFT_BUKI = 0;
	private static final int CRAFT_MOKKOU = 1;
	private static final int CRAFT_BOUGU = 2;
	private static final int CRAFT_SAIHOU = 3;
	private static final int CRAFT_TSUBO = 4;
	private static final int CRAFT_RANPU = 5;
	private static final int CRAFT_DOUGU = 6;
	
	private TextView craftTabBuki;
	private TextView craftTabMokkou;
	private TextView craftTabBougu;
	private TextView craftTabSaihou;
	private TextView craftTabTsubo;
	private TextView craftTabRanpu;
	private TextView craftTabDougu;
	
	private MyPagerAdapter mAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setSoftInputMode(
				LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	    setContentView(R.layout.activity_craft_menu);
	    
	    craftTabBuki = (TextView) findViewById(R.id.craft_tab_buki);
	    craftTabMokkou = (TextView) findViewById(R.id.craft_tab_mokkou);
	    craftTabBougu = (TextView) findViewById(R.id.craft_tab_bougu);
	    craftTabSaihou = (TextView) findViewById(R.id.craft_tab_saihou);
	    craftTabTsubo = (TextView) findViewById(R.id.craft_tab_tsubo);
	    craftTabRanpu = (TextView) findViewById(R.id.craft_tab_ranpu);
	    craftTabDougu = (TextView) findViewById(R.id.craft_tab_dougu);
	    
	    mAdapter = new MyPagerAdapter(getSupportFragmentManager());
	    
	    ViewPager mPager = (ViewPager) findViewById(R.id.craft_pager);
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
			case CRAFT_BUKI:
				fragment = new CraftListFragment(CraftListFragment.CRAFT_BUKI);
				break;
			case CRAFT_MOKKOU:
				fragment = new CraftListFragment(CraftListFragment.CRAFT_MOKKOU);
				break;
			case CRAFT_BOUGU:
				fragment = new CraftListFragment(CraftListFragment.CRAFT_BOUGU);
				break;
			case CRAFT_SAIHOU:
				fragment = new CraftListFragment(CraftListFragment.CRAFT_SAIHOU);
				break;
			case CRAFT_TSUBO:
				fragment = new CraftListFragment(CraftListFragment.CRAFT_TSUBO);
				break;
			case CRAFT_RANPU:
				fragment = new CraftListFragment(CraftListFragment.CRAFT_RANPU);
				break;
			case CRAFT_DOUGU:
				fragment = new CraftListFragment(CraftListFragment.CRAFT_DOUGU);
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
		craftTabBuki.setTextColor(Color.BLUE);
		craftTabBuki.getPaint().setUnderlineText(false);
		craftTabMokkou.setTextColor(Color.BLUE);
		craftTabMokkou.getPaint().setUnderlineText(false);
		craftTabBougu.setTextColor(Color.BLUE);
		craftTabBougu.getPaint().setUnderlineText(false);
		craftTabSaihou.setTextColor(Color.BLUE);
		craftTabSaihou.getPaint().setUnderlineText(false);
		craftTabTsubo.setTextColor(Color.BLUE);
		craftTabTsubo.getPaint().setUnderlineText(false);
		craftTabRanpu.setTextColor(Color.BLUE);
		craftTabRanpu.getPaint().setUnderlineText(false);
		craftTabDougu.setTextColor(Color.BLUE);
		craftTabDougu.getPaint().setUnderlineText(false);
		
		switch (position) {
		case CRAFT_BUKI:
			craftTabBuki.setTextColor(Color.WHITE);
			craftTabBuki.getPaint().setUnderlineText(true);
			break;
		case CRAFT_MOKKOU:
			craftTabMokkou.setTextColor(Color.WHITE);
			craftTabMokkou.getPaint().setUnderlineText(true);
			break;
		case CRAFT_BOUGU:
			craftTabBougu.setTextColor(Color.WHITE);
			craftTabBougu.getPaint().setUnderlineText(true);
			break;
		case CRAFT_SAIHOU:
			craftTabSaihou.setTextColor(Color.WHITE);
			craftTabSaihou.getPaint().setUnderlineText(true);
			break;
		case CRAFT_TSUBO:
			craftTabTsubo.setTextColor(Color.WHITE);
			craftTabTsubo.getPaint().setUnderlineText(true);
			break;
		case CRAFT_RANPU:
			craftTabRanpu.setTextColor(Color.WHITE);
			craftTabRanpu.getPaint().setUnderlineText(true);
			break;
		case CRAFT_DOUGU:
			craftTabDougu.setTextColor(Color.WHITE);
			craftTabDougu.getPaint().setUnderlineText(true);
			break;
		}
	}

}

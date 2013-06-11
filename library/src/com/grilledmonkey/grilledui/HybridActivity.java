package com.grilledmonkey.grilledui;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.grilledmonkey.grilledui.abstracts.GrilledActivity;
import com.grilledmonkey.grilledui.adapters.SectionAdapter;
import com.grilledmonkey.grilledui.fragments.SectionListFragment;
import com.grilledmonkey.grilledui.listener.TabChangeListener;

/**
 * HybridActivity combines TabActivity for phones and SectionActivity for
 * tablets.
 * 
 * @author Aux
 *
 */
public class HybridActivity extends GrilledActivity implements ActionBar.TabListener {
	private boolean hasTwoPanes = false;
	private FragmentManager fm;
	private SectionAdapter sectionAdapter;
	private ActionBar actionBar;
	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(needsAutoInit()) {
			initSections();
		}
	}

	private void initSections() {
		initSections(getLayout());
	}

	private void initSections(int layout) {
		fm = getSupportFragmentManager();

		setContentView(layout);
		if(findViewById(getSectionDetailContainer()) != null) {
			hasTwoPanes = true;
			sectionAdapter = createSectionAdapter(fm);
			((SectionListFragment)fm.findFragmentById(getSectionList())).setActivateOnItemClick(true);
		}
		else {
			actionBar = getActionBar();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			pager = findViewPager();
			sectionAdapter = createSectionAdapter(fm);
			pager.setAdapter(sectionAdapter);
			pager.setOnPageChangeListener(new TabChangeListener(actionBar));
		}
	}

	public ViewPager findViewPager() {
		View view = findViewById(R.id.gui__tab_pager);
		return(view instanceof ViewPager ? (ViewPager)view : null);
	}

	@Override
	public SectionAdapter getSectionAdapter() {
		return(sectionAdapter);
	}

	@Override
	public SectionAdapter createSectionAdapter(FragmentManager fm) {
		return(new SectionAdapter(fm, actionBar, this));
	}

	public int getSectionDetailContainer() {
		return(R.id.section_detail_container);
	}

	public int getSectionList() {
		return(R.id.section_list);
	}

	public boolean hasTwoPanes() {
		return(hasTwoPanes);
	}

	public ViewPager getViewPager() {
		return(pager);
	}

	@Override
	public int getLayout() {
		return(R.layout.gui__activity_hybrid);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		pager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

}

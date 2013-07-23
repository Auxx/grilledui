package com.grilledmonkey.grilleduisherlock;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.grilledmonkey.grilleduisherlock.adapters.SectionAdapter;
import com.grilledmonkey.grilleduisherlock.fragments.SectionListFragment;
import com.grilledmonkey.grilleduisherlock.listener.TabChangeListener;

/**
 * HybridActivity combines TabActivity for phones and SectionActivity for
 * tablets.
 *
 * @author Aux
 *
 */
public class HybridActivity extends SectionActivity implements ActionBar.TabListener {
	private ActionBar actionBar;
	private ViewPager pager;

	@Override
	public void initSections(int layout) {
		fm = getSupportFragmentManager();
		sectionAdapter = createSectionAdapter(fm);

		setContentView(layout);
		if(findViewById(getSectionDetailContainer()) != null) {
			hasTwoPanes = true;
			((SectionListFragment)fm.findFragmentById(getSectionList())).setActivateOnItemClick(true);
		}
		else {
			actionBar = getSherlock().getActionBar();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			pager = findViewPager();
			pager.setAdapter(sectionAdapter);
			pager.setOnPageChangeListener(new TabChangeListener(actionBar));
		}
	}

	public ViewPager findViewPager() {
		View view = findViewById(R.id.gui__tab_pager);
		return(view instanceof ViewPager ? (ViewPager)view : null);
	}

	@Override
	public SectionAdapter createSectionAdapter(FragmentManager fm) {
		return(new SectionAdapter(fm, actionBar, this));
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
		if(pager != null) {
			pager.setCurrentItem(tab.getPosition());
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

}

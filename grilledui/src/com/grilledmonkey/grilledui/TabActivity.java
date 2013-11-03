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
import com.grilledmonkey.grilledui.listener.TabChangeListener;
import com.grilledmonkey.grilledui.xml.XmlSectionReader;

/**
 * TabActivity implements navigation with tabs in a simple way. Descendants
 * automatically get tabbed navigation with zero tabs. Tabs consist of
 * content fragment and tab title.
 * <p/>
 * If auto-initialization is not desired, override needsAutoInit() method to
 * return false and call initTabs() when needed. This will allow you to
 * complete pre-checks in onCreate() and act accordingly.
 * <p/>
 * Tabs are populated with createSectionAdapter() method. It is called in
 * initTabs(), so if you have fixed tabs ready at the start, then override
 * createSectionAdapter() and return populated {@link SectionAdapter}. You may
 * use {@link XmlSectionReader} to create adapter and
 * populate it with data in XML file.
 * <p/>
 * You can also get current SectionAdapter from getSectionAdapter() and use it
 * to dynamically add, modify and remove tabs.
 * 
 * @author Aux
 *
 */
public class TabActivity extends GrilledActivity implements ActionBar.TabListener {
	private ActionBar actionBar;
	private ViewPager pager;
	private SectionAdapter sectionAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(needsAutoInit()) {
			initTabs();
		}
	}

	/**
	 * Initializes tab navigation, populates tabs and ViewPager with data
	 * provided by SectionAdapter, sets handlers.
	 * <p/>
	 * This variant of initTabs() loads internal GrilledUI layout. You must
	 * reference GrilledUI in your project settings to use this form.
	 */
	public void initTabs() {
		initTabs(getLayout());
	}

	/**
	 * Initializes tab navigation, populates tabs and ViewPager with data
	 * provided by SectionAdapter, sets handlers.
	 * <p/>
	 * This variant should be used if you want to use alternative layout or
	 * if you use GrilledUI library in JAR form (you then are forced to
	 * use your own layouts).
	 * <p/>
	 * findViewPager() should be overridden to find correct ViewPager in
	 * alternative layout.
	 * 
	 * @param layout to be used with current activity
	 */
	public void initTabs(int layout) {
		setContentView(layout);
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		pager = findViewPager();
		sectionAdapter = createSectionAdapter(getSupportFragmentManager());
		pager.setAdapter(sectionAdapter);
		pager.setOnPageChangeListener(new TabChangeListener(actionBar));
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

	/**
	 * This method must be overridden when using alternative layout! It is used
	 * to get correct ViewPager from layout.
	 */
	public ViewPager findViewPager() {
		View view = findViewById(R.id.gui__tab_pager);
		return(view instanceof ViewPager ? (ViewPager)view : null);
	}

	public ViewPager getViewPager() {
		return(pager);
	}

	@Override
	public SectionAdapter getSectionAdapter() {
		return(sectionAdapter);
	}

	@Override
	public SectionAdapter createSectionAdapter(FragmentManager fm) {
		return(new SectionAdapter(fm, actionBar, this));
	}

	@Override
	public int getLayout() {
		return(R.layout.gui__activity_tab);
	}

}

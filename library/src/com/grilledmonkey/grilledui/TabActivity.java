package com.grilledmonkey.grilledui;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.grilledmonkey.grilledui.adapters.SectionAdapter;
import com.grilledmonkey.grilledui.listener.TabChangeListener;

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
 * use SectionAdapter static method fromXml() to create adapter and
 * populate it with data in XML file.
 * <p/>
 * You can also get current SectionAdapter from getSectionAdapter() and use it
 * to dynamically add, modify and remove tabs.
 * 
 * @author Aux
 *
 */
public class TabActivity extends FragmentActivity implements ActionBar.TabListener {
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
		initGrill(R.layout.gui__activity_tab);
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
	public void initGrill(int layout) {
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
		pager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	/**
	 * This method is used to determine if tabbed navigation should be
	 * automatically enabled in onCreate() method. If you plan to override
	 * onCreate() and add some logic to determine what to load and show to user
	 * or you want to use alternative layout, then override this method,
	 * return FALSE and call initGrill() when needed.
	 */
	public boolean needsAutoInit() {
		return(true);
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

	public SectionAdapter getSectionAdapter() {
		return(sectionAdapter);
	}

	/**
	 * Returns new {@link SectionAdapter}, blank by default. If you want to
	 * populate tabs statically, then override this method.
	 * <p/>
	 * You may want to populate tabs from XML, just return the result of
	 * static method SectionAdapter.fromXml() when overriding.
	 * 
	 * @param fm
	 * @return SectionAdapter with tabs
	 */
	public SectionAdapter createSectionAdapter(FragmentManager fm) {
		return(new SectionAdapter(fm, actionBar, this));
	}

}

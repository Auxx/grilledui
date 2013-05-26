package com.grilledmonkey.grilledui.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.grilledmonkey.grilledui.TabActivity;
import com.grilledmonkey.grilledui.xml.XmlSectionReader;

/**
 * This is adapter for {@link TabActivity} tabs. It stores and manages tabs,
 * handles their integration with ViewPager and so on. Methods are self
 * explaining.
 * <p/>
 * You can use {@link XmlSectionReader} to generate new adapter populated with tabs
 * from XML resource.
 * 
 * @author Aux
 *
 */
public class SectionAdapter extends FragmentPagerAdapter {
	private final List<Fragment> sections = new ArrayList<Fragment>();
	private final ActionBar actionBar;
	private final ActionBar.TabListener tabListener;

	public SectionAdapter(FragmentManager fm, ActionBar actionBar, ActionBar.TabListener tabListener) {
		super(fm);
		this.actionBar = actionBar;
		this.tabListener = tabListener;
	}

	public SectionAdapter add(Fragment fragment, String title) {
		sections.add(fragment);
		actionBar.addTab(createTab(title));
		notifyDataSetChanged();
		return(this);
	}

	public SectionAdapter add(int position, Fragment fragment, String title) {
		sections.add(position, fragment);
		actionBar.addTab(createTab(title), position);
		notifyDataSetChanged();
		return(this);
	}

	private Tab createTab(String title) {
		Tab tab = actionBar.newTab();
		tab.setText(title.toUpperCase(Locale.getDefault()));
		tab.setTabListener(tabListener);
		return(tab);
	}

	public Fragment remove(int position) {
		actionBar.removeTabAt(position);
		Fragment removed = sections.remove(position);
		notifyDataSetChanged();
		return(removed);
	}

	public Tab getTab(int position) {
		return(actionBar.getTabAt(position));
	}

	@Override
	public Fragment getItem(int position) {
		return(sections.get(position));
	}

	@Override
	public int getCount() {
		return(sections.size());
	}

}

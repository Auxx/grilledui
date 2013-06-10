package com.grilledmonkey.grilledui.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ArrayAdapter;

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
	private final List<String> titles = new ArrayList<String>();
	private final ActionBar actionBar;
	private final ActionBar.TabListener tabListener;
	private ArrayAdapter<String> arrayAdapter = null;

	/**
	 * This constructor should be used when not using tabs.
	 * 
	 * @param fm FragmentManager as required by FragmentPagerAdapter
	 */
	public SectionAdapter(FragmentManager fm) {
		this(fm, null, null);
	}

	/**
	 * This constructor should be used when using tabs.
	 * 
	 * @param fm FragmentManager as required by FragmentPagerAdapter
	 * @param actionBar ActionBar of current activity
	 * @param tabListener object which will listen for tab events
	 */
	public SectionAdapter(FragmentManager fm, ActionBar actionBar, ActionBar.TabListener tabListener) {
		super(fm);
		this.actionBar = actionBar;
		this.tabListener = tabListener;
	}

	public SectionAdapter add(Fragment fragment, String title) {
		titles.add(title);
		sections.add(fragment);

		if(actionBar != null) {
			actionBar.addTab(createTab(title));
		}

		if(arrayAdapter != null) {
			arrayAdapter.notifyDataSetChanged();
		}

		notifyDataSetChanged();
		return(this);
	}

	public SectionAdapter add(int position, Fragment fragment, String title) {
		titles.add(position, title);
		sections.add(position, fragment);

		if(actionBar != null) {
			actionBar.addTab(createTab(title), position);
		}

		if(arrayAdapter != null) {
			arrayAdapter.notifyDataSetChanged();
		}

		notifyDataSetChanged();
		return(this);
	}

	public void setTitle(int position, String title) {
		titles.set(position, title);

		if(actionBar != null) {
			actionBar.getTabAt(position).setText(title);
		}

		if(arrayAdapter != null) {
			arrayAdapter.notifyDataSetChanged();
		}
	}

	public String getTitle(int position) {
		return(titles.get(position));
	}

	private Tab createTab(String title) {
		Tab tab = actionBar.newTab();
		tab.setText(title.toUpperCase(Locale.getDefault()));
		tab.setTabListener(tabListener);
		return(tab);
	}

	public Fragment remove(int position) {
		titles.remove(position);

		if(actionBar != null) {
			actionBar.removeTabAt(position);
		}

		if(arrayAdapter != null) {
			arrayAdapter.notifyDataSetChanged();
		}

		Fragment removed = sections.remove(position);
		notifyDataSetChanged();
		return(removed);
	}

	public Tab getTab(int position) {
		return(actionBar != null ? actionBar.getTabAt(position) : null);
	}

	/**
	 * This method is used for generating an adapter compatible
	 * with lists and other UI elements in Android. Generated ArrayAdapter
	 * is lazy-loaded and notified of all changes properly.
	 * 
	 * @param context
	 * @param layoutId
	 * @param viewId
	 * @return
	 */
	public ArrayAdapter<String> getArrayAdapter(Context context, int layoutId, int viewId) {
		if(arrayAdapter == null) {
			arrayAdapter = new ArrayAdapter<String>(context, layoutId, viewId, titles);
		}
		return(arrayAdapter);
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

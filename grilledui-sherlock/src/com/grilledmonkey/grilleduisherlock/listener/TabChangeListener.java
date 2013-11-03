package com.grilledmonkey.grilleduisherlock.listener;

import com.actionbarsherlock.app.ActionBar;
import android.support.v4.view.ViewPager;

/**
 * Simple class to listen to ViewPager events and send them to ActionBar.
 *
 * @author Aux
 *
 */
public class TabChangeListener extends ViewPager.SimpleOnPageChangeListener {
	private final ActionBar actionBar;

	public TabChangeListener(ActionBar actionBar) {
		this.actionBar = actionBar;
	}

	@Override
	public void onPageSelected(int position) {
		actionBar.setSelectedNavigationItem(position);
	}
}

package com.grilledmonkey.examples.tabactivityexample;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.support.v4.app.FragmentManager;

import com.grilledmonkey.grilledui.TabActivity;
import com.grilledmonkey.grilledui.adapters.SectionAdapter;

/**
 * This example shows the basic usage of TabActivity with GrilledUI library
 * referencing and using provided layouts. GrilledUI layouts can only be used
 * when referencing GrilledUI in source form. Built-in layouts can not be used
 * with pre-compiled library in JAR form.
 * <p/>
 * Each tab layout and logic is put inside fragments
 * ({@link DummySectionFragment} in this example). This simple example can be
 * used as a base to many simple apps with minimal navigation requirements.
 * You can put your logic inside fragments and completely forget about
 * activity.
 *
 * @author Aux
 *
 */
public class MainActivity extends TabActivity {

	/**
	 * Default SectionAdapter is empty, meaning without any tabs. Override
	 * {@code createSectionAdapter()} method to populate tabs. Remember to pass
	 * {@link ActionBar} and {@link TabListener} to {@link SectionAdapter}
	 * constructor.
	 * <p/>
	 * P.S. You may use method chaining, because {@code add()} returns current
	 * SectionAdapter instance.
	 */
	@Override
	public SectionAdapter createSectionAdapter(FragmentManager fm) {
		// Create new SectionAdapter, bind it to action bar and make current
		// activity a handler to all tab events.
		SectionAdapter adapter = new SectionAdapter(fm, getActionBar(), this);

		// Add two tabs.
		adapter.add(new DummySectionFragment(), "First tab");
		adapter.add(new DummySectionFragment(), "Second tab");

		// Chaining example
		// adapter.add(new DummySectionFragment(), "First tab").add(new DummySectionFragment(), "Second tab");

		// Return populated adapter.
		return(adapter);
	}

}

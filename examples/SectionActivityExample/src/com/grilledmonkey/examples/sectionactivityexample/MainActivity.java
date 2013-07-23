package com.grilledmonkey.examples.sectionactivityexample;

import android.support.v4.app.FragmentManager;

import com.grilledmonkey.grilledui.SectionActivity;
import com.grilledmonkey.grilledui.adapters.SectionAdapter;

/**
 * This example shows the basic usage of {@link SectionActivity} with GrilledUI library
 * referencing and using provided layouts. GrilledUI layouts can only be used
 * when referencing GrilledUI in source form. Built-in layouts can not be used
 * with pre-compiled library in JAR form.
 * <p/>
 * Each section layout and logic is put inside fragments
 * ({@link DummySectionFragment} in this example). This simple example can be
 * used as a base to many simple apps with minimal navigation requirements.
 * You can put your logic inside fragments and completely forget about
 * activity.
 *
 * @author Aux
 *
 */
public class MainActivity extends SectionActivity {

	@Override
	public SectionAdapter createSectionAdapter(FragmentManager fm) {
		// Create new SectionAdapter and make current
		// activity a handler to all events.
		SectionAdapter adapter = new SectionAdapter(fm);

		// Add two sections.
		adapter.add(new DummySectionFragment(), "First section");
		adapter.add(new DummySectionFragment(), "Second section");

		// Return populated adapter.
		return(adapter);
	}

}

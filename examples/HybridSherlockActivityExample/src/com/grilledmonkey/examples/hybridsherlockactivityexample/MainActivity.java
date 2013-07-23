package com.grilledmonkey.examples.hybridsherlockactivityexample;

import android.support.v4.app.FragmentManager;

import com.grilledmonkey.grilleduisherlock.HybridActivity;
import com.grilledmonkey.grilleduisherlock.adapters.SectionAdapter;

public class MainActivity extends HybridActivity {

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
		// Create new SectionAdapter and make current
		// activity a handler to all events.
		SectionAdapter adapter = new SectionAdapter(fm, getSherlock().getActionBar(), this);

		// Add two sections.
		adapter.add(new DummySectionFragment(), "First section");
		adapter.add(new DummySectionFragment(), "Second section");

		// Return populated adapter.
		return(adapter);
	}

}

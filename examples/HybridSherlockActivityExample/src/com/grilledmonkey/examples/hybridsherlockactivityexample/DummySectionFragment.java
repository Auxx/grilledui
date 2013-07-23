package com.grilledmonkey.examples.hybridsherlockactivityexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * This is a dummy fragment to put inside tabs. Make your own fragments
 * for anything more useful than "Hello world" example.
 *
 * @author Aux
 *
 */
public class DummySectionFragment extends SherlockFragment {
	/**
	 * Default constructor must be provided. It is not recommended to use
	 * parameterized fragment constructors since Android and GrilledUI
	 * will not pass any parameters.
	 */
	public DummySectionFragment() {
	}

	/**
	 * Override {@code onCreateView()} to inflate desired layout and prepare
	 * your fragment for work.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return(inflater.inflate(R.layout.fragment_example_tab_dummy, container, false));
	}
}

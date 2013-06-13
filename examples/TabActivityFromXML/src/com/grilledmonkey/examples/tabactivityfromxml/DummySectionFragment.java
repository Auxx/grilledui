package com.grilledmonkey.examples.tabactivityfromxml;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This is a dummy fragment to put inside tabs. Make your own fragments
 * for anything more useful than "Hello world" example.
 * 
 * @author Aux
 *
 */
public class DummySectionFragment extends Fragment {
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

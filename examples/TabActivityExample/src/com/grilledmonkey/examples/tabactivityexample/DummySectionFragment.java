package com.grilledmonkey.examples.tabactivityexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DummySectionFragment extends Fragment {
	public DummySectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return(inflater.inflate(R.layout.fragment_example_tab_dummy, container, false));
	}
}

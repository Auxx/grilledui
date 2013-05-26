package com.grilledmonkey.grilleduiexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DummySectionFragment extends Fragment {
	public static final String ARG_SECTION_NUMBER = "section_number";

	public DummySectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_example_tab_dummy, container, false);
		TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);

		Bundle arguments = getArguments();
		if(arguments != null) {
			int number = arguments.getInt(ARG_SECTION_NUMBER);
			dummyTextView.setText(Integer.toString(number));
		}
		else {
			dummyTextView.setText("hi");
		}
		return rootView;
	}
}

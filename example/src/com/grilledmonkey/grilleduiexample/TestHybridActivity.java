package com.grilledmonkey.grilleduiexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.grilledmonkey.grilledui.HybridActivity;
import com.grilledmonkey.grilledui.adapters.SectionAdapter;

public class TestHybridActivity extends HybridActivity {
	@Override
	public SectionAdapter createSectionAdapter(FragmentManager fm) {
		SectionAdapter adapter = new SectionAdapter(fm, getActionBar(), this);

		adapter.add(new TabModFragment(), "Tab actions");

		Fragment fragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, 888);
		fragment.setArguments(args);
		adapter.add(fragment, "888");

		return(adapter);
	}
}

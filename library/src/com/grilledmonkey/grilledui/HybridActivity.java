package com.grilledmonkey.grilledui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.grilledmonkey.grilledui.abstracts.GrilledActivity;
import com.grilledmonkey.grilledui.adapters.SectionAdapter;

/**
 * HybridActivity combines TabActivity for phones and SectionActivity for
 * tablets.
 * 
 * @author Aux
 *
 */
public class HybridActivity extends GrilledActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(needsAutoInit()) {
			initSections();
		}
	}

	private void initSections() {
		initSections(getLayout());
	}

	private void initSections(int layout) {
	}

	@Override
	public SectionAdapter getSectionAdapter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SectionAdapter createSectionAdapter(FragmentManager fm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLayout() {
		// TODO Auto-generated method stub
		return 0;
	}

}

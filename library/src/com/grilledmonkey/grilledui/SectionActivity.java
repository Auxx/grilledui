package com.grilledmonkey.grilledui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.grilledmonkey.grilledui.abstracts.GrilledActivity;
import com.grilledmonkey.grilledui.adapters.SectionAdapter;

/**
 * 
 * @author Aux
 *
 */
public class SectionActivity extends GrilledActivity {
	private boolean hasTwoPanes = false;
	private FragmentManager fm;
	private SectionAdapter sectionAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(needsAutoInit()) {
			initSections();
		}
	}

	private void initSections() {
		initSections(R.layout.gui__activity_section_list);
	}

	private void initSections(int layout) {
		setContentView(layout);
		fm = getSupportFragmentManager();
		sectionAdapter = createSectionAdapter(fm);

		if(findViewById(R.id.section_detail_container) != null) {
			hasTwoPanes = true;
			//			((ItemListFragment)fm.findFragmentById(R.id.section_list)).setActivateOnItemClick(true);
		}
	}

	public boolean hasTwoPanes() {
		return(hasTwoPanes);
	}

	@Override
	public SectionAdapter getSectionAdapter() {
		return(sectionAdapter);
	}

	@Override
	public SectionAdapter createSectionAdapter(FragmentManager fm) {
		return(new SectionAdapter(fm));
	}
}

package com.grilledmonkey.grilledui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.grilledmonkey.grilledui.abstracts.GrilledActivity;
import com.grilledmonkey.grilledui.adapters.SectionAdapter;
import com.grilledmonkey.grilledui.fragments.SectionListFragment;

/**
 * 
 * @author Aux
 *
 */
public class SectionActivity extends GrilledActivity {
	private static final String ARG_SECTION_ID = "sectionId";
	private static final String ARG_SECTION_FRAGMENT = "fragment";

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
		fm = getSupportFragmentManager();
		sectionAdapter = createSectionAdapter(fm);
		setContentView(layout);

		if(findViewById(R.id.section_detail_container) != null) {
			hasTwoPanes = true;
			// TODO Anything to do here?
			((SectionListFragment)fm.findFragmentById(R.id.section_list)).setActivateOnItemClick(true);
		}
	}

	public void onSectionSelected(int position) {
		Fragment fragment = sectionAdapter.getItem(position);
		if(hasTwoPanes) {
			// TODO R.id.section_detail_container should user changeable
			fm.beginTransaction().replace(R.id.section_detail_container, fragment).commit();
		}
		else {
			Intent intent = new Intent(this, SectionActivity.class);
			intent.putExtra(ARG_SECTION_ID, position);
			intent.putExtra(ARG_SECTION_FRAGMENT, fragment.getClass().getName());
			startActivity(intent);



			//			detailIntent.putE

			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			//			Intent detailIntent = new Intent(this, ItemDetailActivity.class);
			//			detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
			//			startActivity(detailIntent);
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

package com.grilledmonkey.grilledui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.grilledmonkey.grilledui.SectionActivity;

public class SectionListFragment extends ListFragment {
	private static final String STATE_ACTIVE_SECTION = "activate_section";

	private static final String CALLBACKS_EXCEPTION_MESSAGE = "Activity must implement fragment's callbacks.";

	private SectionActivity activity;
	private int activeSection = ListView.INVALID_POSITION;

	public SectionListFragment() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Remove if unneeded
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if(savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVE_SECTION)) {
			setActiveSection(savedInstanceState.getInt(STATE_ACTIVE_SECTION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if(activity instanceof SectionActivity) {
			this.activity = (SectionActivity)activity;
			// TODO Use own layouts and views
			setListAdapter(this.activity.getSectionAdapter().getArrayAdapter(this.activity, android.R.layout.simple_list_item_activated_1, android.R.id.text1));
		}
		else {
			throw new IllegalStateException(CALLBACKS_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		this.activity = null;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
		super.onListItemClick(listView, view, position, id);
		activity.onSectionSelected(position);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if(activeSection != ListView.INVALID_POSITION) {
			outState.putInt(STATE_ACTIVE_SECTION, activeSection);
		}
	}

	public void setActivateOnItemClick(boolean activateOnItemClick) {
		getListView().setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
	}

	private void setActiveSection(int position) {
		if(position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(activeSection, false);
		}
		else {
			getListView().setItemChecked(position, true);
		}

		activeSection = position;
	}

}

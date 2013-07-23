package com.grilledmonkey.examples.customlayoutexample;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.grilledmonkey.grilledui.HybridActivity;
import com.grilledmonkey.grilledui.adapters.SectionAdapter;

/**
 * This example shows how to properly use custom layouts and what is possible
 * with {@link HybridActivity}. It uses refs files to provide different layouts for
 * different screens and orientations. All layouts are stored in basic layouts
 * folder.
 * <p/>
 * Custom layouts in this example are based on GrilledUI built-in layouts, but
 * thanks to refs system they behave differently. This example will render
 * tabbed navigation for phones and small (Nexus 7 like) tablets in portrait
 * mode and master detail flow navigation in landscape mode. Bigger tablets will
 * always work in MDF mode.
 *
 * @author Aux
 *
 */
public class MainActivity extends HybridActivity {

	/**
	 * Overall initialization of activity is the same as in simple cases:
	 * just override {@code createSectionAdapter()} and populate
	 * {@link SectionAdapter}.
	 */
	@Override
	public SectionAdapter createSectionAdapter(FragmentManager fm) {
		SectionAdapter adapter = new SectionAdapter(fm, getActionBar(), this);

		adapter.add(new DummySectionFragment(), "First section");
		adapter.add(new DummySectionFragment(), "Second section");

		return(adapter);
	}

	/**
	 * Override {@code getLayout()} to return ID of desired custom layout.
	 * This example uses refs system to specify different layouts for different
	 * screens and orientations with one name. That makes code easier to read
	 * and understand yet enables you to name layout files differently and store
	 * them in one place.
	 */
	@Override
	public int getLayout() {
		return(R.layout.activity_main);
	}

	/**
	 * {@code findViewPager()} should be overridden every time you use custom
	 * layouts. This method returns ID for {@link ViewPager} which will host
	 * fragments inside tabs.
	 */
	@Override
	public ViewPager findViewPager() {
		View view = findViewById(R.id.tab_pager);
		return(view instanceof ViewPager ? (ViewPager)view : null);
	}

	/**
	 * Section list will contain your sections (tabs) in master detail flow type
	 * navigation. Override {@code getSectionList()} to specify layout element
	 * which will contain sections in your custom layout.
	 */
	@Override
	public int getSectionList() {
		return(R.id.section_list);
	}

	/**
	 * Section detail container will contain functional fragments. Override
	 * {@code getSectionDetailContainer()} to specify layout element in your
	 * custom layout.
	 */
	@Override
	public int getSectionDetailContainer() {
		return(R.id.section_detail_container);
	}

}

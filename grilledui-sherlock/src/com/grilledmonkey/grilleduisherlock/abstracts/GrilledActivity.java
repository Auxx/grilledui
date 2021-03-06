package com.grilledmonkey.grilleduisherlock.abstracts;

import android.support.v4.app.FragmentManager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.grilledmonkey.grilleduisherlock.adapters.SectionAdapter;

/**
 * Base abstract class for GrilledUI activities. Should not be used directly
 * since it does no real work. Subclass it for custom UI activities.
 *
 * @author Aux
 *
 */
public abstract class GrilledActivity extends SherlockFragmentActivity {

	/**
	 * This method is used to determine if tabbed navigation should be
	 * automatically enabled in onCreate() method. If you plan to override
	 * onCreate() and add some logic to determine what to load and show to user
	 * or you want to use alternative layout, then override this method,
	 * return FALSE and call initGrill() when needed.
	 */
	public boolean needsAutoInit() {
		return(true);
	}

	public abstract SectionAdapter getSectionAdapter();

	/**
	 * Returns new {@link SectionAdapter}, blank by default. If you want to
	 * populate tabs statically, then override this method.
	 * <p/>
	 * You may want to populate tabs from XML, just return the result of
	 * static method XmlSectionReader.load() when overriding.
	 *
	 * @param fm
	 * @return SectionAdapter
	 */
	public abstract SectionAdapter createSectionAdapter(FragmentManager fm);

	/**
	 * Override this method to use your own layout in auto-init mode.
	 *
	 * @return layout id to use
	 */
	public abstract int getLayout();
}

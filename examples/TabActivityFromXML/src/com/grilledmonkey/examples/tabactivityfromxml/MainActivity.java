package com.grilledmonkey.examples.tabactivityfromxml;

import android.support.v4.app.FragmentManager;

import com.grilledmonkey.grilledui.TabActivity;
import com.grilledmonkey.grilledui.adapters.SectionAdapter;
import com.grilledmonkey.grilledui.xml.XmlSectionReader;

/**
 * This example shows the easiest way to populate {@link SectionAdapter} from
 * XML file. XML resources are stored inside res/xml directory of your project.
 * XML syntax is pretty straight-forward: define each section as a separate
 * {@code <section>} tag, use {@code title} attribute to specify tab title and
 * {@code fragment} attribute to specify class name of fragment which should be
 * loaded into tab (section).
 * <p/>
 * Please check {@code TabActivityExample} for more information.
 * @author Aux
 *
 */
public class MainActivity extends TabActivity {
	/**
	 * {@link XmlSectionReader} is used to read XML file, create
	 * {@link SectionAdapter} and populate it with data read from XML. Please
	 * check {@code XmlSectionReader} documentation for more information and
	 * methods available.
	 */
	@Override
	public SectionAdapter createSectionAdapter(FragmentManager fm) {
		return(XmlSectionReader.loadSilent(fm, getActionBar(), this, this, R.xml.sections));
	}
}
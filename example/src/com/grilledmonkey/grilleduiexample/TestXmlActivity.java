package com.grilledmonkey.grilleduiexample;

import android.support.v4.app.FragmentManager;

import com.grilledmonkey.grilledui.TabActivity;
import com.grilledmonkey.grilledui.adapters.SectionAdapter;
import com.grilledmonkey.grilledui.xml.XmlSectionReader;

public class TestXmlActivity extends TabActivity {

	@Override
	public SectionAdapter createSectionAdapter(FragmentManager fm) {
		return(XmlSectionReader.loadSilent(fm, getActionBar(), this, this, R.xml.sections));
	}

}

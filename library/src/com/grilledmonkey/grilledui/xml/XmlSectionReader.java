package com.grilledmonkey.grilledui.xml;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.grilledmonkey.grilledui.adapters.SectionAdapter;

public class XmlSectionReader {
	private static final String RESOURCE_PREFIX = "@string/";
	private static final String RESOURCE_TYPE = "string";

	private static final String ROOT_TAG = "sections";
	private static final String SECTION_TAG = "section";

	private static final String NAME_ATTR = "title";
	private static final String FRAGMENT_ATTR = "fragment";

	public static SectionAdapter loadSilent(FragmentManager fm, ActionBar actionBar, ActionBar.TabListener tabListener, Context context, int resId) {
		return(loadSilent(fm, actionBar, tabListener, context, context.getResources().getXml(resId)));
	}

	public static SectionAdapter loadSilent(FragmentManager fm, ActionBar actionBar, ActionBar.TabListener tabListener, Context context, XmlPullParser xml) {
		try {
			return(load(fm, actionBar, tabListener, context, xml));
		}
		catch(Exception e) {
			e.printStackTrace();
			return(null);
		}
	}

	public static SectionAdapter load(FragmentManager fm, ActionBar actionBar, ActionBar.TabListener tabListener, Context context, int resId) throws XmlPullParserException, IOException, NotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		return(load(fm, actionBar, tabListener, context, context.getResources().getXml(resId)));
	}

	public static SectionAdapter load(FragmentManager fm, ActionBar actionBar, ActionBar.TabListener tabListener, Context context, XmlPullParser xml) throws XmlPullParserException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		SectionAdapter adapter = new SectionAdapter(fm, actionBar, tabListener);
		while(canContinue(xml, adapter, context)) {
			xml.next();
		}
		return(adapter);
	}

	private static boolean canContinue(XmlPullParser xml, SectionAdapter adapter, Context context) throws XmlPullParserException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		int eventType = xml.getEventType();
		switch(eventType) {
			case XmlPullParser.END_DOCUMENT:
				return(false);
			case XmlPullParser.START_TAG:
				return(onStartTag(xml, adapter, context));
		}
		return(true);
	}

	private static boolean onStartTag(XmlPullParser xml, SectionAdapter adapter, Context context) throws XmlPullParserException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		int depth = xml.getDepth();
		String name = xml.getName();
		switch(depth) {
			case 1:
				if(!ROOT_TAG.equals(name)) {
					return(false);
				}
				break;
			case 2:
				if(!SECTION_TAG.equals(name)) {
					return(false);
				}
				else {
					addSection(xml, adapter, context);
				}
				break;
		}

		return(true);
	}

	private static void addSection(XmlPullParser xml, SectionAdapter adapter, Context context) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Object instance = Class.forName(xml.getAttributeValue(null, FRAGMENT_ATTR)).newInstance();
		if(instance instanceof Fragment) {
			adapter.add((Fragment)instance, parseStringAttribute(xml.getAttributeValue(null, NAME_ATTR), context));
		}
	}

	private static String parseStringAttribute(String attr, Context context) {
		if(attr != null && attr.startsWith(RESOURCE_PREFIX)) {
			int id = context.getResources().getIdentifier(attr.substring(RESOURCE_PREFIX.length()), RESOURCE_TYPE, context.getPackageName());
			if(id > 0) {
				return(context.getResources().getString(id));
			}
			else {
				return(attr);
			}
		}
		else {
			return(attr);
		}
	}
}

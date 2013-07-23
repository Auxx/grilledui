# GrilledUI

Library to implement common UIs easily. Available UI types are: TabActivity, SectionActivity and HybridActivity. Pre-ICS devices are also supported with the help of `ActionBarSherlock`. Use `library` folder for ICS+ devices only and `library-sherlock` together with your installation of `ActionBarSherlock` for 2.1+ projects.

GrilledUI is licensed under 2 clause BSD license. In simple words - use the way you want! But remember to notify your users that you use this library.

## TabActivity

This is a base activity to create your tabbed UIs. It wraps all the code needed to create and manage scrollable tabs inside activity. Empty activity is created by default, override `createSectionAdapter()` method to add tabs on start-up or use `getSectionAdapter()` to retrieve current section adapter to manage tabs dynamically.

## SectionActivity

This is a base activity to create master detail flow UI for your app. Unlike Google's example, everything is done in one activity, UI sections can be populated from XML file and dynamic modification is available.

## HybridActivity

This activity is a hybrid between MDF and tabs. Tabs will be used on phones and MDF on tablets by default. But behaviour can easily be overriden with proper layouts. It is quite possible to use MDF in landscape and tabs in portrait orientation, for example. Check out `CustomLayoutExample` for this advanced behaviour.

## XmlSectionReader

XmlSectionReader is a convenience class to load tab sections from XML file. Override `createSectionAdapter()` and return `SectionAdapter` from `XmlSectionReader.load()` method. You can use XML resources or any XML in form of `XmlPullParser` instance. You can use `@string` resources inside XML file to reuse your internationalized string resources as tab titles.

## XML example

XML file must have `<sections>` root tag with multiple `<section>` tags inside. Each `<section>` should have `title` and `fragment` attributes. `title` will be shown as a tab title and `fragment` is a fully qualified class name containing fragment for section tab.

```xml
<?xml version="1.0" encoding="utf-8"?>
<sections>
  <section title="@string/title_section1" fragment="com.grilledmonkey.grilleduiexample.DummySectionFragment" />
  <section title="@string/title_section2" fragment="com.grilledmonkey.grilleduiexample.TabModFragment" />
</sections>
```

## Examples

Check [project wiki](https://github.com/Auxx/grilledui/wiki/Examples) for code example descriptions and where to find them (all inside repo, actually).

## Screenshots

Screenshots are available in [Picasa album](https://picasaweb.google.com/111938394443043677436/GrilledUI).

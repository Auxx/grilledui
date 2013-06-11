package com.grilledmonkey.grilleduiexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class ExampleTabActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_example_tab);
	}

	public void nextActivity(View view) {
		Intent intent = new Intent(this, TestActivity.class);
		startActivity(intent);
	}

	public void nextXmlActivity(View view) {
		Intent intent = new Intent(this, TestXmlActivity.class);
		startActivity(intent);
	}

	public void nextSectionActivity(View view) {
		Intent intent = new Intent(this, TestSectionActivity.class);
		startActivity(intent);
	}

	public void nextHybridActivity(View view) {
		Intent intent = new Intent(this, TestHybridActivity.class);
		startActivity(intent);
	}

}

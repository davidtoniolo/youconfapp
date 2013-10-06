package com.unittestcloud.youconfapp_app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.unittestcloud.R;

import de.akquinet.android.androlog.Log;

/**
 * 
 * @author davidtoniolo
 * 
 */
public class MapActivity extends FragmentActivity {

	@SuppressWarnings("unused")
	private static final String TAG = "youconfapp_app.MapActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.init(this);

		setContentView(R.layout.map);
	}

}

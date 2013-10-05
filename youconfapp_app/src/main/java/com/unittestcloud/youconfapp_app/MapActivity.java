package com.unittestcloud.youconfapp_app;

import com.unittestcloud.R;

import de.akquinet.android.androlog.Log;
import android.app.Activity;
import android.os.Bundle;

/**
 * 
 * @author davidtoniolo
 *
 */
public class MapActivity extends Activity {
	
	@SuppressWarnings("unused")
	private static final String TAG = "youconfapp_app.MapActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.init(this);
		
		setContentView(R.layout.map);
	}
}

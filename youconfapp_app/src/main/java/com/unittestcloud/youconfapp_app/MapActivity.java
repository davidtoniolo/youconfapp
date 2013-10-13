package com.unittestcloud.youconfapp_app;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.unittestcloud.R;
import com.unittestcloud.youconfapp_app.callback.MapCallback;
import com.unittestcloud.youconfapp_app.listener.MapListener;

import de.akquinet.android.androlog.Log;

/**
 * 
 * @author davidtoniolo
 * 
 */
public class MapActivity extends Activity {

	@SuppressWarnings("unused")
	private static final String TAG = "youconfapp_app.MapActivity";

	private GoogleMap map;
	private LocationClient locationClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.init(this);

		setContentView(R.layout.gmaps);

		map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.gmaps_map)).getMap();

		map.setMyLocationEnabled(true);

		MapCallback mapCallback = new MapCallback(getApplicationContext(), map);
		locationClient = new LocationClient(this, mapCallback, new MapListener(
				getApplicationContext()));
	}

	@Override
	protected void onStart() {
		super.onStart();
		locationClient.connect();
	}

	@Override
	protected void onStop() {
		locationClient.disconnect();
		super.onStop();
	}

}

package com.unittestcloud.youconfapp_app;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
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

	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	private GoogleMap map;
	private LocationClient locationClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.init(this);

		setContentView(R.layout.gmaps);

		map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.gmaps_map)).getMap();
		// Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
		// .title("Hamburg"));
		// Marker kiel = map.addMarker(new MarkerOptions().position(KIEL)
		// .title("Kiel").snippet("Kiel is cool")
		// .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon)));
		//
		// // Move the camera instantly to hamburg with a zoom of 15.
		// map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
		//
		// // Zoom in, animating the camera.
		// map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

		// display my location button
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

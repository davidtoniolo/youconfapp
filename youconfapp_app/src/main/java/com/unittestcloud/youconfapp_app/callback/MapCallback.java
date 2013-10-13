package com.unittestcloud.youconfapp_app.callback;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unittestcloud.youconfapp_localization.R;

/**
 * 
 * @author davidtoniolo
 * 
 */
public class MapCallback implements
		GooglePlayServicesClient.ConnectionCallbacks {

	private GoogleMap map;
	private Context context;
	private Location currentLocation;
	private LocationManager locationManager;

	/**
	 * @param context
	 * @param map
	 */
	public MapCallback(Context context, GoogleMap map) {
		this.context = context;
		this.map = map;
	}

	@Override
	public void onConnected(Bundle arg0) {
		locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {
					}

					@Override
					public void onProviderEnabled(String provider) {
					}

					@Override
					public void onProviderDisabled(String provider) {
					}

					@Override
					public void onLocationChanged(final Location location) {
						currentLocation = location;

						map.addMarker(new MarkerOptions()
								.position(
										new LatLng(currentLocation
												.getLatitude(), currentLocation
												.getLongitude()))
								.title(context
										.getString(R.string.yourPositionMarkerTitle))
								.icon(BitmapDescriptorFactory
										.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

						/*
						 * Center position in google maps.
						 */
						map.animateCamera(CameraUpdateFactory.newLatLngZoom(
								new LatLng(currentLocation.getLatitude(),
										currentLocation.getLongitude()), 15.0f));
					}
				});
	}

	@Override
	public void onDisconnected() {
	}

}

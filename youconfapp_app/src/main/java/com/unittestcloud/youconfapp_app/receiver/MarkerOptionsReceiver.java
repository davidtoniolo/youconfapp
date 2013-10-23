package com.unittestcloud.youconfapp_app.receiver;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.model.MarkerOptions;
import com.unittestcloud.R;
import com.unittestcloud.youconfapp_app.service.AddDefaultMarkersService;
import com.unittestcloud.youconfapp_utils.map.CustomizableMap;

/**
 * Receives marker options and adds them to the map.
 * 
 * @author davidtoniolo
 * 
 */
public class MarkerOptionsReceiver extends BroadcastReceiver {

	/**
	 * Status to load the data only once.
	 */
	private static boolean mapDataAlreadyLoaded = false;

	/**
	 * Data received and saved statically.
	 */
	private static List<MarkerOptions> list = new ArrayList<MarkerOptions>();

	private CustomizableMap customMap;

	public MarkerOptionsReceiver(CustomizableMap customMap) {
		this.customMap = customMap;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			String string = bundle
					.getString(AddDefaultMarkersService.RESPONSE_STATUS);
			if (string.equals(AddDefaultMarkersService.RESPONSE_STATUS_OK)) {
				list = bundle
						.getParcelableArrayList(AddDefaultMarkersService.MARKEROPTIONS);
				if (!list.isEmpty()) {
					mapDataAlreadyLoaded = true;
					customMap.addMarkers(list);
					Toast.makeText(context,
							R.string.mapHasBeenUpdatedWithMarkers,
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(context,
							R.string.noLocationsAvailableForUpdates,
							Toast.LENGTH_LONG).show();
				}
			} else {

				// TODO show dialog and redirect to home screen?

			}
		} else {

			// TODO show dialog and redirect to home screen?

		}
	}

	/**
	 * Check whether data are already loaded or not.
	 * 
	 * @return
	 */
	public boolean getLoadStatus() {
		return mapDataAlreadyLoaded;
	}

	/**
	 * @return
	 */
	public List<MarkerOptions> getMarkerOptions() {
		return list;
	}

}

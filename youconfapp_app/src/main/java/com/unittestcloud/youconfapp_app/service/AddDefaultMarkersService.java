package com.unittestcloud.youconfapp_app.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unittestcloud.R;
import com.unittestcloud.youconfapp_utils.service.MarkersLoaderAbstract;

/**
 * Create MarkerOptions which should be displayed by default in a map.
 * 
 * This is an android.app.IntentService component and runs asynchronously.
 * 
 * @author davidtoniolo
 * 
 */
public class AddDefaultMarkersService extends MarkersLoaderAbstract {

	private static final String TAG = "youconfapp_app.AddMarkersService";

	public AddDefaultMarkersService() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		sendResponse(RESPONSE_STATUS_OK, createMarkerOptions());
	}

	@Override
	protected void sendResponse(String status, List<MarkerOptions> markerOptions) {
		ArrayList<MarkerOptions> mOptions = new ArrayList<MarkerOptions>();
		if (null == markerOptions) {
			mOptions = new ArrayList<MarkerOptions>();
		} else {
			mOptions = (ArrayList<MarkerOptions>) markerOptions;
		}

		Intent intent = new Intent(NOTIFICATION);
		intent.putExtra(RESPONSE_STATUS, status);
		intent.putExtra(RESULT, result);
		intent.putExtra(MARKEROPTIONS, mOptions);
		sendBroadcast(intent);
	}

	@Override
	protected ArrayList<MarkerOptions> createMarkerOptions() {

		// TODO load real data

		LatLng BOTNANG = new LatLng(48.778300, 9.129000);

		ArrayList<MarkerOptions> list = new ArrayList<MarkerOptions>();

		list.add(new MarkerOptions()
				.position(BOTNANG)
				.title("Botnang")
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_action_star)));
		return list;
	}

}

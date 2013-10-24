package com.unittestcloud.youconfapp_app.model;

import java.util.ArrayList;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unittestcloud.R;

/**
 * Pojo 
 * 
 * @author davidtoniolo
 *
 */
public class MarkerOptionsDao {

	private ArrayList<MarkerOptions> markerOptions;
	
	public MarkerOptionsDao() {
		markerOptions = new ArrayList<MarkerOptions>();
	}
	
	public ArrayList<MarkerOptions> getMarkerOptions() {

		// TODO load real data

		LatLng BOTNANG = new LatLng(48.778300, 9.129000);

		markerOptions.add(new MarkerOptions()
				.position(BOTNANG)
				.title("Botnang (Stuttgart)")
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_action_star)));
		return markerOptions;
	}
	
}

package com.unittestcloud.youconfapp_utils.map;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * 
 * @author davidtoniolo
 * 
 */
public class MarkerOptionsFactory {

	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(53.551, 9.993);

	/**
	 * Build an array list of MarkerOptions objects.
	 * 
	 * @return
	 */
	public static ArrayList<MarkerOptions> createDefaultDestinationMarkerOptions() {
		ArrayList<MarkerOptions> list = new ArrayList<MarkerOptions>();
		
		list.add(new MarkerOptions().position(HAMBURG).title("Hamburg"));
		list.add(new MarkerOptions().position(KIEL).title("Kiel"));
		
		return list;
	}
}

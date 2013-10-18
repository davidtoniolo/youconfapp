package com.unittestcloud.youconfapp_utils.map;

import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * 
 * @author davidtoniolo
 * 
 */
public class Map {

	/**
	 * Default initialization of GoogleMap.
	 * 
	 * @param map
	 */
	public static void initDefaultMap(GoogleMap map) {
		map.setMyLocationEnabled(true);

		Map.addMarkers(
				MarkerOptionsFactory.createDefaultDestinationMarkerOptions(),
				map);
	}

	/**
	 * Add markers to the map from database.
	 * 
	 * @param map
	 */
	public static void addMarkers(List<MarkerOptions> options, GoogleMap map) {
		for (MarkerOptions option : options) {
			map.addMarker(option);
		}
	}
}

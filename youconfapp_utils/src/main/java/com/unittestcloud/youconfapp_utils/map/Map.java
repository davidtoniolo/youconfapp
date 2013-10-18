package com.unittestcloud.youconfapp_utils.map;

import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Helper class for GoogleMaps.
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
		if (null != map) {
			map.setMyLocationEnabled(true);

			Map.addMarkers(MarkerOptionsFactory
					.createDefaultDestinationMarkerOptions(), map);
		}
	}

	/**
	 * Add markers to the map from database.
	 * 
	 * @param map
	 */
	public static void addMarkers(List<MarkerOptions> options, GoogleMap map) {
		if (null != options) {
			for (MarkerOptions option : options) {
				map.addMarker(option);
			}
		}
	}
}

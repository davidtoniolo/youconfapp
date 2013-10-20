package com.unittestcloud.youconfapp_utils.map;

import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Customization of a GoogleMap object.
 * 
 * @author davidtoniolo
 * 
 */
public final class DefaultMap implements CustomizableMap {

	private GoogleMap map;

	/**
	 * @param map
	 */
	public DefaultMap(GoogleMap map) {
		this.map = map;
	}
	
	/**
	 * Default custom google map.
	 * 
	 * @param map
	 */
	public void configure() {
		if (null != map) {
			if (!map.isMyLocationEnabled()) {
				map.setMyLocationEnabled(true);
			}

			DefaultMap.addMarkers(MarkerOptionsFactory
					.createDefaultDestinationMarkerOptions(), map);
		}
	}
	
	public GoogleMap getCustomMap() {
		return map;
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

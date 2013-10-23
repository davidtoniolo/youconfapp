package com.unittestcloud.youconfapp_utils.map;

import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Default configuration with standard features of a youconfapp map.
 * 
 * @author davidtoniolo
 * 
 */
public final class DefaultMap implements CustomizableMap {

	private final GoogleMap map;

	/**
	 * @param map
	 * @param listener
	 */
	public DefaultMap(GoogleMap map, OnMarkerClickListener listener) {
		this.map = map;
		this.map.setOnMarkerClickListener(listener);
	}

	/**
	 * Default custom google map.
	 * 
	 * @param map
	 */
	@Override
	public void configure() {
		if (null != map) {
			if (!map.isMyLocationEnabled()) {
				map.setMyLocationEnabled(true);
			}
		}
	}

	@Override
	public GoogleMap getCustomMap() {
		return map;
	}

	/**
	 * Add markers to the map from database.
	 * 
	 * @param map
	 */
	@Override
	public void addMarkers(List<MarkerOptions> options) {
		if (null != options) {
			for (MarkerOptions option : options) {
				map.addMarker(option);
			}
		}
	}

}

package com.unittestcloud.youconfapp_utils.map;

import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A component which gets a GoogleMap instance and configures it.
 * 
 * @author davidtoniolo
 *
 */
public interface CustomizableMap  {

	/**
	 * Get the GoogleMap instance.
	 * 
	 * @return
	 */
	public GoogleMap getCustomMap();
	
	/**
	 * Configure the GoogleMap object, e.g. hide or show UI elements.
	 */
	public void configure();
	
	/**
	 * Add the MarkerOptions list elements to the GoogleMap instance.
	 * 
	 * @param options
	 */
	public void addMarkers(List<MarkerOptions> options);
	
}

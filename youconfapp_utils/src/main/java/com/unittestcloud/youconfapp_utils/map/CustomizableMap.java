package com.unittestcloud.youconfapp_utils.map;

import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * 
 * @author davidtoniolo
 *
 */
public interface CustomizableMap  {

	public GoogleMap getCustomMap();
	
	public void configure();
	
	public void addMarkers(List<MarkerOptions> options);
	
}

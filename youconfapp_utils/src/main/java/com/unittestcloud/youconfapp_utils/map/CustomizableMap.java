package com.unittestcloud.youconfapp_utils.map;

import com.google.android.gms.maps.GoogleMap;

/**
 * 
 * @author davidtoniolo
 *
 */
public interface CustomizableMap {

	public GoogleMap getCustomMap();
	
	public void configure();
	
}

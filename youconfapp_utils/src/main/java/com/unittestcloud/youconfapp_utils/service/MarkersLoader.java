package com.unittestcloud.youconfapp_utils.service;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.IntentService;

/**
 * Base class for IntentService classes which load data and create MarkerOptions
 * for a GoogleMap.
 * 
 * @author davidtoniolo
 * 
 */
public abstract class MarkersLoader extends IntentService {

	public static final String NOTIFICATION 		= "com.unittestcloud.youconfapp_app";
	public static final String RESULT 				= "result";
	public static final String RESPONSE_STATUS 		= "response_status";
	public static final String RESPONSE_STATUS_OK 	= "ok";
	public static final String MARKEROPTIONS 		= "markeroptions";
	public static final String TYPE 				= "type";

	protected int result = Activity.RESULT_CANCELED;

	protected MarkersLoader(String name) {
		super(name);
	}

	/**
	 * Create list of MarkerOptions instances.
	 * 
	 * @return Avoid NULL
	 */
	abstract protected ArrayList<MarkerOptions> createMarkerOptions();

	/**
	 * Send marker options back to activity.
	 * 
	 * @param status
	 * @param markerOptions
	 */
	abstract protected void sendResponse(String status,
			List<MarkerOptions> markerOptions);

}

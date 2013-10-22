package com.unittestcloud.youconfapp_utils.service;

/**
 * Indicates components which load MarkerOptions asynchronously.
 * 
 * Implementing classes are responsible to use this API in an asynchronous context.
 * 
 * @author davidtoniolo
 *
 */
public interface AsyncLoadableMarkerOptions {

	/**
	 * Load data in asynchronous process, e.g. IntentService, etc.
	 */
	public void loadMarkerOptions();
	
}

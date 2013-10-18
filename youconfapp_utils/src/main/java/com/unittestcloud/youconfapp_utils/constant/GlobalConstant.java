package com.unittestcloud.youconfapp_utils.constant;

/**
 * App constants.
 * 
 * @author davidtoniolo
 *
 */
public enum GlobalConstant {
	
	MINIMUM_DISTANCE_TO_UPDATE_LOCAL_POSITION_IN_METER ( 50 );
	
	private int intValue;
	
	private GlobalConstant(int val) {
		intValue = val;
	}
	
	public int getInt() {
		return intValue;
	}
}

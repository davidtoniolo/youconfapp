package com.unittestcloud.youconfapp_app.model;

import java.util.ArrayList;

import com.google.android.gms.maps.model.MarkerOptions;
import com.unittestcloud.R;

/**
 * Additional data or data mappings which are not represented by json data model.
 * 
 * Example:
 * The json data model can have a string element "iconType" which is mapped to an Android
 * resource id here, {@see MarkerOptionsDataModel.loadResourceIdByIconType()}
 * 
 * @author davidtoniolo
 * 
 */
public class MarkerOptionsDataModel {

	public static final String ICONTYPE_CONFERENCE = "conference";
	
	private ArrayList<MarkerOptions> markerOptions;

	public MarkerOptionsDataModel() {
		markerOptions = new ArrayList<MarkerOptions>();
	}

	/**
	 * Add a single option.
	 * 
	 * @param option
	 */
	public void addOption(MarkerOptions option) {
		markerOptions.add(option);
	}

	public ArrayList<MarkerOptions> getMarkerOptions() {
		return markerOptions;
	}

	/**
	 * Maps a type to an existing resource id.
	 * 
	 * @param type
	 * @return 0 on not found.
	 */
	public static int loadResourceIdByIconType(final String type) {
		if (null == type) {
			return 0;
		}
		
		if (type.equals(ICONTYPE_CONFERENCE)) {
			return R.drawable.ic_action_star;
		}
		
		return 0;
	}

}

package com.unittestcloud.youconfapp_app.listener;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.maps.model.MarkerOptions;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.unittestcloud.R;
import com.unittestcloud.youconfapp_app.model.MarkerOptionsDataModel;
import com.unittestcloud.youconfapp_utils.map.CustomizableMap;

import de.akquinet.android.androlog.Log;

/**
 * 
 * TODO think about an interface for "alreadyLoaded" functionality
 * 
 * @author davidtoniolo
 * 
 */
public class MarkerOptionsRequestListener implements
		RequestListener<MarkerOptionsDataModel> {

	private static final String TAG = "youconfapp_app.listener.MarkerOptionsRequestListener";

	/**
	 * Status to load the data only once.
	 */
	private static boolean mapDataAlreadyLoaded = false;

	/**
	 * Data received and saved statically.
	 */
	private static List<MarkerOptions> list = new ArrayList<MarkerOptions>();

	private CustomizableMap customMap;

	private Context context;

	public MarkerOptionsRequestListener(Context context,
			CustomizableMap customMap) {
		if (null == customMap) {
			/*
			 * TODO maybe context.getString() can be replaced by RoboGuice?
			 */
			throw new NullPointerException(
					context.getString(R.string.missingInstanceOfCustomizableMap));
		}

		this.context = context;
		this.customMap = customMap;
	}

	@Override
	public void onRequestFailure(SpiceException e) {
		Log.e(TAG, e.getMessage());
	}

	@Override
	public void onRequestSuccess(MarkerOptionsDataModel result) {
		list = result.getMarkerOptions();

		if (null != list) {
			customMap.addMarkers(list);
			mapDataAlreadyLoaded = true;
		}

		Toast.makeText(context, R.string.mapHasBeenUpdatedWithMarkers,
				Toast.LENGTH_LONG).show();
	}

	/**
	 * Check whether data are already loaded or not.
	 * 
	 * @return
	 */
	public boolean isDataAlreadyLoaded() {
		return mapDataAlreadyLoaded;
	}

	/**
	 * @return
	 */
	public List<MarkerOptions> getMarkerOptions() {
		return list;
	}
	
}

package com.unittestcloud.youconfapp_app.listener;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;

import de.akquinet.android.androlog.Log;

/**
 * 
 * @author davidtoniolo
 * 
 */
public class MapListener implements
		GooglePlayServicesClient.OnConnectionFailedListener {

	private static final String TAG = "youconfapp_app.listener.MapListener";

	private Context context;

	/**
	 * @param context
	 */
	public MapListener(Context context) {
		Log.init(context);
		this.context = context;
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		Log.e(TAG, arg0.toString());
	}

}

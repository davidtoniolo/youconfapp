package com.unittestcloud.youconfapp_utils.network;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * @author davidtoniolo
 *
 */
public class NetUtils {
	
	/**
	 * @param cm
	 * @return
	 */
	public static boolean isNetworkAvailable(ConnectivityManager cm) {
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		// if no network is available networkInfo will be null
		// otherwise check if we are connected
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}
}

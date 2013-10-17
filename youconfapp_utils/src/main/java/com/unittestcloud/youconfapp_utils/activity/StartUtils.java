package com.unittestcloud.youconfapp_utils.activity;

import android.app.Activity;
import android.content.Intent;

/**
 * 
 * @author davidtoniolo
 * 
 */
public class StartUtils {

	/**
	 * Start a new explicit activity.
	 * 
	 * @param intent
	 */
	public static void startActivity(Activity packageContext,
			Class<?> newActivityClass) {
		Intent intent = new Intent(packageContext, newActivityClass);
		((Activity) packageContext).startActivity(intent);
	}
	
}

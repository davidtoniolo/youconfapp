package com.unittestcloud.youconfapp_app.listener;

import com.unittestcloud.youconfapp_app.MainActivity;
import com.unittestcloud.youconfapp_utils.activity.StartUtils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/**
 * 
 * @author davidtoniolo
 *
 */
public class NegativeMapActivityOnClickListener implements OnClickListener {
	
	Activity activity;
	
	public NegativeMapActivityOnClickListener(Activity instance) {
		activity = instance;
	}
	
	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		StartUtils.startActivity(activity, MainActivity.class);
	}

}

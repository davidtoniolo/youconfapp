package com.unittestcloud.youconfapp_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.unittestcloud.R;
import com.unittestcloud.youconfapp_app.callback.MapDefaultCallback;
import com.unittestcloud.youconfapp_app.listener.MapListener;
import com.unittestcloud.youconfapp_app.listener.NegativeMapActivityOnClickListener;
import com.unittestcloud.youconfapp_utils.map.Map;
import com.unittestcloud.youconfapp_utils.network.NetUtils;

import de.akquinet.android.androlog.Log;

/**
 * 
 * @author davidtoniolo
 * 
 */
public class MapActivity extends Activity {

	@SuppressWarnings("unused")
	private static final String TAG = "youconfapp_app.MapActivity";

	private static final int DIALOG_ALERT_NETWORK_MISSING = 10;

	private GoogleMap map;
	private LocationClient locationClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.init(this);

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		if (false == NetUtils.isNetworkAvailable(cm)) {
			showDialog(DIALOG_ALERT_NETWORK_MISSING);
		} else {
			setContentView(R.layout.gmaps);

			MapFragment fragment = (MapFragment) getFragmentManager()
					.findFragmentById(R.id.gmaps_map);

			map = fragment.getMap();
			
			Map.initDefaultMap(map);
			
			MapDefaultCallback mapCallback = new MapDefaultCallback(getApplicationContext(),
					map);
			
			locationClient = new LocationClient(this, mapCallback,
					new MapListener(getApplicationContext()));
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		
		if (false == NetUtils.isNetworkAvailable(cm)) {
			showDialog(DIALOG_ALERT_NETWORK_MISSING);
		}
		
		if (null != locationClient && !locationClient.isConnected()) {
			locationClient.connect();
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		
		if (false == NetUtils.isNetworkAvailable(cm)) {
			showDialog(DIALOG_ALERT_NETWORK_MISSING);
		}
		
		if (null != locationClient && !locationClient.isConnected()) {
			locationClient.connect();
		}
	}
	
	@Override
	protected void onStop() {
		if (null != locationClient && locationClient.isConnected()) {
			locationClient.disconnect();
		}
		super.onStop();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_ALERT_NETWORK_MISSING:
			AlertDialog dialog = getAlertDialogNetworkMissing();
			dialog.show();
		}
		return super.onCreateDialog(id);
	}

	/**
	 * Build simple alert dialog to inform user about missing network.
	 * 
	 * @return
	 */
	private AlertDialog getAlertDialogNetworkMissing() {
		Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(getString(com.unittestcloud.youconfapp_localization.R.string.titleOnClickMapActivityDialogAlert));
		builder.setNegativeButton(
				getString(com.unittestcloud.youconfapp_localization.R.string.negativeOnClickMapActivityDialogAlert),
				new NegativeMapActivityOnClickListener(this));
		return builder.create();
	}

}

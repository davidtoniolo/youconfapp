package com.unittestcloud.youconfapp_app;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.MapFragment;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.unittestcloud.R;
import com.unittestcloud.youconfapp_app.callback.MapDefaultCallback;
import com.unittestcloud.youconfapp_app.listener.MapListener;
import com.unittestcloud.youconfapp_app.listener.NegativeMapActivityOnClickListener;
import com.unittestcloud.youconfapp_app.ormlite.helper.DatabaseHelper;
import com.unittestcloud.youconfapp_app.receiver.MarkerOptionsReceiver;
import com.unittestcloud.youconfapp_app.service.AddDefaultMarkersService;
import com.unittestcloud.youconfapp_utils.map.CustomizableMap;
import com.unittestcloud.youconfapp_utils.map.DefaultMap;
import com.unittestcloud.youconfapp_utils.network.NetUtils;
import com.unittestcloud.youconfapp_utils.service.AsyncLoadableMarkerOptions;

import de.akquinet.android.androlog.Log;

/**
 * 
 * @todo avoid updating map after orientation/configuration changes. Toast is
 *       displayed each time.
 * 
 * @todo replace deprecated usages
 * 
 * @author davidtoniolo
 * 
 */
public class MapActivity extends OrmLiteBaseActivity<DatabaseHelper> implements
		AsyncLoadableMarkerOptions {

	@SuppressWarnings("unused")
	private static final String TAG = "youconfapp_app.MapActivity";

	private static final int DIALOG_ALERT_NETWORK_MISSING = 10;

	private LocationClient locationClient;

	private CustomizableMap customMap;

	private MarkerOptionsReceiver receiver;

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

			customMap = new DefaultMap(fragment.getMap());
			customMap.configure();

			MapDefaultCallback mapCallback = new MapDefaultCallback(
					getApplicationContext(), customMap.getCustomMap());

			locationClient = new LocationClient(this, mapCallback,
					new MapListener(getApplicationContext()));

			receiver = new MarkerOptionsReceiver(customMap);

			if (receiver.getLoadStatus()) {
				customMap.addMarkers(receiver.getMarkerOptions());
			} else {
				loadMarkerOptions();
			}
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

		if (!receiver.getLoadStatus()) {
			registerReceiver(receiver, new IntentFilter(
					AddDefaultMarkersService.NOTIFICATION));
		}

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		if (false == NetUtils.isNetworkAvailable(cm)) {
			showDialog(DIALOG_ALERT_NETWORK_MISSING);
		}

		if (null != locationClient && !locationClient.isConnected()) {
			locationClient.connect();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (!receiver.getLoadStatus()) {
			unregisterReceiver(receiver);
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
	 * Load data in asynchronous process.
	 */
	@Override
	public void loadMarkerOptions() {
		Intent intent = new Intent(this, AddDefaultMarkersService.class);
		startService(intent);
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

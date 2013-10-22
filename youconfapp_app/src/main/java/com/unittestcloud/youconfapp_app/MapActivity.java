package com.unittestcloud.youconfapp_app;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.unittestcloud.R;
import com.unittestcloud.youconfapp_app.callback.MapDefaultCallback;
import com.unittestcloud.youconfapp_app.listener.MapListener;
import com.unittestcloud.youconfapp_app.listener.NegativeMapActivityOnClickListener;
import com.unittestcloud.youconfapp_app.ormlite.helper.DatabaseHelper;
import com.unittestcloud.youconfapp_app.service.AddDefaultMarkersService;
import com.unittestcloud.youconfapp_utils.map.DefaultMap;
import com.unittestcloud.youconfapp_utils.network.NetUtils;
import com.unittestcloud.youconfapp_utils.service.AsyncLoadableMarkerOptions;

import de.akquinet.android.androlog.Log;

/**
 * 
 * @todo avoid updating map after orientation/configuration changes. Toast is displayed each time.
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

	private DefaultMap customMap;

	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				String string = bundle
						.getString(AddDefaultMarkersService.RESPONSE_STATUS);
				if (string.equals(AddDefaultMarkersService.RESPONSE_STATUS_OK)) {
					ArrayList<MarkerOptions> list = bundle
							.getParcelableArrayList(AddDefaultMarkersService.MARKEROPTIONS);
					if (!list.isEmpty()) {
						customMap.addMarkers(list);
						Toast.makeText(MapActivity.this,
								R.string.mapHasBeenUpdatedWithMarkers,
								Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(MapActivity.this,
								R.string.noLocationsAvailableForUpdates,
								Toast.LENGTH_LONG).show();
					}
				} else {

					// TODO show dialog and redirect to home screen?

				}
			} else {

				// TODO show dialog and redirect to home screen?

			}
		}
	};

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

			loadMarkerOptions();
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

		registerReceiver(receiver, new IntentFilter(
				AddDefaultMarkersService.NOTIFICATION));

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
		unregisterReceiver(receiver);
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

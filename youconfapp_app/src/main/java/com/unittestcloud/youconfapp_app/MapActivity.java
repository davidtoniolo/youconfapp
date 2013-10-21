package com.unittestcloud.youconfapp_app;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.unittestcloud.R;
import com.unittestcloud.youconfapp_app.callback.MapDefaultCallback;
import com.unittestcloud.youconfapp_app.listener.MapListener;
import com.unittestcloud.youconfapp_app.listener.NegativeMapActivityOnClickListener;
import com.unittestcloud.youconfapp_app.ormlite.helper.DatabaseHelper;
import com.unittestcloud.youconfapp_utils.map.DefaultMap;
import com.unittestcloud.youconfapp_utils.network.NetUtils;

import de.akquinet.android.androlog.Log;

/**
 * 
 * @author davidtoniolo
 * 
 */
public class MapActivity extends OrmLiteBaseActivity<DatabaseHelper> {

	@SuppressWarnings("unused")
	private static final String TAG = "youconfapp_app.MapActivity";

	private static final int DIALOG_ALERT_NETWORK_MISSING = 10;

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

			DefaultMap customMap = new DefaultMap(fragment.getMap());
			customMap.addMarkers(addDefaultDestinationMarkerOptions());
			customMap.configure();

			MapDefaultCallback mapCallback = new MapDefaultCallback(
					getApplicationContext(), customMap.getCustomMap());

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

	/**
	 * @return
	 */
	public ArrayList<MarkerOptions> addDefaultDestinationMarkerOptions() {
		
		// TODO load data async
		
		LatLng HAMBURG = new LatLng(53.558, 9.927);
		LatLng KIEL = new LatLng(53.551, 9.993);

		ArrayList<MarkerOptions> list = new ArrayList<MarkerOptions>();

		list.add(new MarkerOptions().position(HAMBURG).title("Hamburg"));
		list.add(new MarkerOptions()
				.position(KIEL)
				.title("Kiel")
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.social_group)));
		
		// TODO refresh map

		return list;
	}

}

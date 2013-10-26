package com.unittestcloud.youconfapp_app;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.MapFragment;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.unittestcloud.R;
import com.unittestcloud.youconfapp_app.callback.MapDefaultCallback;
import com.unittestcloud.youconfapp_app.listener.DefaultMarkerOnClickListener;
import com.unittestcloud.youconfapp_app.listener.MapListener;
import com.unittestcloud.youconfapp_app.listener.MarkerOptionsRequestListener;
import com.unittestcloud.youconfapp_app.listener.NegativeMapActivityOnClickListener;
import com.unittestcloud.youconfapp_app.ormlite.helper.DatabaseHelper;
import com.unittestcloud.youconfapp_app.receiver.MarkerOptionsReceiver;
import com.unittestcloud.youconfapp_app.request.MarkerOptionsJsonRequest;
import com.unittestcloud.youconfapp_app.service.LoadDefaultMarkersSpiceService;
import com.unittestcloud.youconfapp_menu.ActionBarMapActivityMenu;
import com.unittestcloud.youconfapp_utils.map.CustomizableMap;
import com.unittestcloud.youconfapp_utils.map.DefaultMap;
import com.unittestcloud.youconfapp_utils.network.NetUtils;
import com.unittestcloud.youconfapp_utils.service.AsyncLoadableMarkerOptions;

import de.akquinet.android.androlog.Log;

/**
 * 
 * @todo delete deprecated class files when their usages are refactored completely.
 * 
 * @author davidtoniolo
 * 
 */
public class MapActivity extends SherlockActivity implements
		ActionBarMapActivityMenu, AsyncLoadableMarkerOptions {

	@SuppressWarnings("unused")
	private static final String TAG = "youconfapp_app.MapActivity";

	private static final int DIALOG_ALERT_NETWORK_MISSING = 10;

	private LocationClient locationClient;

	private CustomizableMap customMap;

	private MarkerOptionsReceiver receiver;

	private MarkerOptionsRequestListener markerOptionsListener;

	private static final String JSON_CACHE_KEY = "markeroptions_json";

	/**
	 * TODO set real ws url
	 * TODO integrate a test and production mode
	 */
	private static final String WS_MARKEROPTIONS = "http://davidtoniolo.bplaced.net/live access/json/markerinfo";

	protected SpiceManager spiceManager = new SpiceManager(
			LoadDefaultMarkersSpiceService.class);

	private DatabaseHelper ormHelper = null;
	
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

			customMap = new DefaultMap(fragment.getMap(),
					new DefaultMarkerOnClickListener(this));
			customMap.configure();

			markerOptionsListener = new MarkerOptionsRequestListener(this,
					customMap);

			MapDefaultCallback mapCallback = new MapDefaultCallback(
					getApplicationContext(), customMap.getCustomMap());

			locationClient = new LocationClient(this, mapCallback,
					new MapListener(getApplicationContext()));
		}
	}

	@Override
	protected void onStart() {
		super.onStart();

		spiceManager.start(this);
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (markerOptionsListener.isDataAlreadyLoaded()) {
			customMap.getCustomMap().clear();
			customMap.addMarkers(markerOptionsListener.getMarkerOptions());
		} else {
			loadMarkerOptions();
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
	protected void onStop() {
		spiceManager.shouldStop();

		if (null != locationClient && locationClient.isConnected()) {
			locationClient.disconnect();
		}
		
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (ormHelper != null) {
			OpenHelperManager.releaseHelper();
			ormHelper = null;
		}
	}
	
	public DatabaseHelper getHelper() {
		if (ormHelper == null) {
			ormHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
		}
		return ormHelper;
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
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.actionbar_map_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.openPreferencesOnClick:
			openPreferencesOnClick();
			return true;
		case R.id.refresh:
			refreshOnClick();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Load data in asynchronous process.
	 */
	@Override
	public void loadMarkerOptions() {
		spiceManager.execute(new MarkerOptionsJsonRequest(WS_MARKEROPTIONS), JSON_CACHE_KEY,
				DurationInMillis.ALWAYS_EXPIRED, markerOptionsListener);
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

	@Override
	public void refreshOnClick() {
		loadMarkerOptions();
	}

	@Override
	public void openPreferencesOnClick() {
		// TODO Auto-generated method stub

	}

}

package com.unittestcloud.youconfapp_app;

import java.sql.SQLException;
import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.octo.android.robospice.SpiceManager;
import com.unittestcloud.R;
import com.unittestcloud.youconfapp_app.ormlite.entity.AppVersion;
import com.unittestcloud.youconfapp_app.ormlite.helper.DatabaseHelper;
import com.unittestcloud.youconfapp_app.service.LoadDefaultMarkersSpiceService;
import com.unittestcloud.youconfapp_utils.activity.StartUtils;

import de.akquinet.android.androlog.Log;

/**
 * Homescreen
 * 
 * @author davidtoniolo
 * 
 */
public class MainActivity extends SherlockActivity {

	@SuppressWarnings("unused")
	private static final String TAG = "youconfapp_app.MainActivity";

	private DatabaseHelper ormHelper = null;

	private static final String JSON_CACHE_KEY = "marker_json";

	protected SpiceManager spiceManager = new SpiceManager(
			LoadDefaultMarkersSpiceService.class);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.init(this);

		setContentView(R.layout.main);
	}

	@Override
	protected void onStart() {
		super.onStart();

		spiceManager.start(this);
	}

	@Override
	protected void onStop() {
		spiceManager.shouldStop();

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.actionbar_map_menu, menu);
		return true;
	}

	public DatabaseHelper getHelper() {
		if (ormHelper == null) {
			ormHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
		}
		return ormHelper;
	}

	/**
	 * Get the current version number from database.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String appVersion() throws SQLException {
		QueryBuilder<AppVersion, Integer> queryBuilder = getHelper()
				.getAppVersionRuntimeDao().queryBuilder();
		queryBuilder.orderBy(AppVersion.COLUMN_ID, false).limit(1l);
		List<AppVersion> result = getHelper().getAppVersionRuntimeDao().query(
				queryBuilder.prepare());

		if (result.isEmpty()) {
			return "";
		}
		return result.get(0).getVersionNumber();
	}

	/**
	 * Temp stub
	 * 
	 * @param view
	 */
	public void runMapActivity(final View view) {
		StartUtils.startActivity(this, MapActivity.class);
	}

}

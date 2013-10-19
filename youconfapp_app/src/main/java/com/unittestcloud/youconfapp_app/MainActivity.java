package com.unittestcloud.youconfapp_app;

import java.sql.SQLException;
import java.util.List;

import android.os.Bundle;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.unittestcloud.R;
import com.unittestcloud.youconfapp_app.ormlite.entity.AppVersion;
import com.unittestcloud.youconfapp_app.ormlite.helper.DatabaseHelper;

import de.akquinet.android.androlog.Log;

/**
 * Homescreen
 * 
 * @author davidtoniolo
 * 
 */
public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> {

	@SuppressWarnings("unused")
	private static final String TAG = "youconfapp_app.MainActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.init(this);

		setContentView(R.layout.main);
	}

	/**
	 * Get the current version number from database.
	 * 
	 * @return
	 */
	public String appVersion() {
		QueryBuilder<AppVersion, Integer> queryBuilder = getHelper()
				.getAppVersionRuntimeDao().queryBuilder();
		queryBuilder.orderBy(AppVersion.COLUMN_ID, false).limit(1l);
		PreparedQuery<AppVersion> preparedQuery;
		try {
			preparedQuery = queryBuilder.prepare();
			List<AppVersion> result = getHelper().getAppVersionRuntimeDao()
					.query(preparedQuery);
			if (result.isEmpty()) {
				return "";
			} else {
				return result.get(0).getVersionNumber();
			}
		} catch (SQLException e) {
		}
		return "";
	}
}

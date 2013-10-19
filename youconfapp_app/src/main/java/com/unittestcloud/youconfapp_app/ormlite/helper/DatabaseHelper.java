package com.unittestcloud.youconfapp_app.ormlite.helper;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.unittestcloud.R;
import com.unittestcloud.youconfapp_app.ormlite.entity.AppVersion;

/**
 * ORMLite helper class
 * 
 * @see http://ormlite.com/android/examples/DatabaseHelper.java
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "youconfapp.db";

	// increase the database version after each change in the db file.
	private static final int DATABASE_VERSION = 1;

	private Dao<AppVersion, Integer> appVersionDao = null;
	private RuntimeExceptionDao<AppVersion, Integer> appVersionRuntimeDao = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION,
				R.raw.ormlite_config);
	}

	/**
	 * This is called when the database is first created. Usually you should
	 * call createTable statements here to create the tables that will store
	 * your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, AppVersion.class);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		insertData();
	}

	/**
	 * Default data.
	 */
	private void insertData() {
		RuntimeExceptionDao<AppVersion, Integer> dao = getAppVersionRuntimeDao();
		AppVersion entry = new AppVersion("0.1");
		dao.create(entry);
	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, AppVersion.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the Database Access Object (DAO) for our AppVersion class. It
	 * will create it or just give the cached value.
	 */
	public Dao<AppVersion, Integer> getAppVersionDao() throws SQLException {
		if (appVersionDao == null) {
			appVersionDao = getDao(AppVersion.class);
		}
		return appVersionDao;
	}

	/**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao
	 * for our AppVersion class. It will create it or just give the cached
	 * value. RuntimeExceptionDao only through RuntimeExceptions.
	 */
	public RuntimeExceptionDao<AppVersion, Integer> getAppVersionRuntimeDao() {
		if (appVersionRuntimeDao == null) {
			appVersionRuntimeDao = getRuntimeExceptionDao(AppVersion.class);
		}
		return appVersionRuntimeDao;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		appVersionRuntimeDao = null;
	}

}

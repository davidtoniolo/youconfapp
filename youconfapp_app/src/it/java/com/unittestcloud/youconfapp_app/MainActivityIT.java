package com.unittestcloud.youconfapp_app;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import com.google.mockwebserver.MockWebServer;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.table.TableUtils;
import com.unittestcloud.R;
import com.unittestcloud.youconfapp_app.ormlite.entity.AppVersion;
import com.unittestcloud.youconfapp_app.ormlite.helper.DatabaseConfigUtil;

/**
 * 
 * @author davidtoniolo
 * 
 */
@LargeTest
@RunWith(RobolectricTestRunner.class)
public class MainActivityIT extends AndroidTestCase {

	private MainActivity activity;

	private MockWebServer mockWebServer;

	/**
	 * Assert OrmLite updates the db schema with latest entity classes.
	 * 
	 * @throws IOException
	 * @throws SQLException
	 */
	@BeforeClass
	public static void setUpTest() throws SQLException, IOException {
		DatabaseConfigUtil.main(null);
	}

	@Before
	public void setUp() throws SQLException {
		MockitoAnnotations.initMocks(this);

		activity = Robolectric.buildActivity(MainActivity.class).create().get();

		/*
		 * Seems more like a dirty workaround to make the db setup working for
		 * all tests.
		 */
		try {
			activity.getHelper().onCreate(null,
					activity.getHelper().getConnectionSource());
		} catch (RuntimeException e) {
		}

		mockWebServer = new MockWebServer();
	}

	@After
	public void tearDown() throws IOException {
		try {
			TableUtils.clearTable(activity.getHelper().getConnectionSource(),
					AppVersion.class);
		} catch (SQLException e) {
		}
		activity.getHelper().getConnectionSource().closeQuietly();
		mockWebServer.shutdown();
	}

	@Test
	public void assertStringResourceAppNameExists() throws Exception {
		String appName = activity.getResources().getString(R.string.app_name);
		assertThat(appName, equalTo("youconf!app"));
	}

	@Test
	public void loadLatestVersionNumber_appVersion() throws SQLException {
		final String newVersion = "999.0";

		RuntimeExceptionDao<AppVersion, Integer> appVersionDao = activity
				.getHelper().getAppVersionRuntimeDao();

		AppVersion entry = new AppVersion(newVersion);
		appVersionDao.create(entry);

		assertEquals(newVersion, activity.appVersion());
	}

}

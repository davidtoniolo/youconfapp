package com.unittestcloud.youconfapp_app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.mockwebserver.MockResponse;
import com.google.mockwebserver.MockWebServer;
import com.unittestcloud.youconfapp_app.model.MarkerOptionsDataModel;
import com.unittestcloud.youconfapp_app.ormlite.helper.DatabaseConfigUtil;
import com.unittestcloud.youconfapp_app.request.MarkerOptionsJsonRequest;

/**
 * 
 * @author davidtoniolo
 * 
 */
@LargeTest
@RunWith(RobolectricTestRunner.class)
public class MapActivityIT extends AndroidTestCase {

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

		mockWebServer = new MockWebServer();
	}

	@After
	public void tearDown() throws IOException {
		mockWebServer.shutdown();
	}

	@Test
	public void verifyJsonDataProcessing_loadDataFromNetwork() throws Exception {

		// TODO load json from text file

		// given
		String expected = "{\"title\":\"Feuerbach (Stuttgart)\",\"description\":\"some text\", \"longitude\":9.158800, \"latitude\":48.811300}";
		mockWebServer.enqueue(new MockResponse().setBody(expected));
		mockWebServer.play();

		// when
		MarkerOptionsJsonRequest sut = new MarkerOptionsJsonRequest(
				mockWebServer.getUrl("/json/marker-info/default").toString());
		MarkerOptionsDataModel actual = sut.loadDataFromNetwork();

		// then
		ArrayList<MarkerOptions> list = actual.getMarkerOptions();
		assertEquals(1, list.size());

		MarkerOptions option = list.get(0);
		assertEquals("Feuerbach (Stuttgart)", option.getTitle());
		assertEquals("some text", option.getSnippet());
	}

}

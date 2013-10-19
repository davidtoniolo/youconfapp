package com.unittestcloud.youconfapp_utils.network;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * @author davidtoniolo
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class NetUtilsTest {

	@Mock
	private ConnectivityManager connManager;

	@Mock
	private NetworkInfo netInfo;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void isNetworkAvailable_withNetworkEnabled() {
		Mockito.when(netInfo.isConnected()).thenReturn(true);
		Mockito.when(connManager.getActiveNetworkInfo()).thenReturn(netInfo);

		assertTrue(NetUtils.isNetworkAvailable(connManager));
	}

	@Test
	public void isNetworkAvailable_withNoActiveNetworkInfoAvailable() {
		Mockito.when(connManager.getActiveNetworkInfo()).thenReturn(null);

		assertFalse(NetUtils.isNetworkAvailable(connManager));
	}

}

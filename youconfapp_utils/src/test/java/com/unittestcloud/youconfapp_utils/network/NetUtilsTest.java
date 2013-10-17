package com.unittestcloud.youconfapp_utils.network;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * @author davidtoniolo
 * 
 */
public class NetUtilsTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void isNetworkAvailable_withNetworkEnabled() {
		ConnectivityManager connManager = Mockito
				.mock(ConnectivityManager.class);
		NetworkInfo netInfo = Mockito.mock(NetworkInfo.class);

		Mockito.when(netInfo.isConnected()).thenReturn(true);
		Mockito.when(connManager.getActiveNetworkInfo()).thenReturn(netInfo);

		assertTrue(NetUtils.isNetworkAvailable(connManager));
	}

	@Test
	public void isNetworkAvailable_withNoActiveNetworkInfoAvailable() {
		ConnectivityManager connManager = Mockito
				.mock(ConnectivityManager.class);
		Mockito.when(connManager.getActiveNetworkInfo()).thenReturn(null);

		assertFalse(NetUtils.isNetworkAvailable(connManager));
	}

}

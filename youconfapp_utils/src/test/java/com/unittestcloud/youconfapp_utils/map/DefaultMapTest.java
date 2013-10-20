package com.unittestcloud.youconfapp_utils.map;

import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.android.gms.maps.GoogleMap;

/**
 * 
 * @author davidtoniolo
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(GoogleMap.class)
public class DefaultMapTest {

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void verifyConfigurationOfDefaultMap() throws InstantiationException,
			IllegalAccessException {
		PowerMockito.mockStatic(GoogleMap.class);
		GoogleMap mapMock = PowerMockito.mock(GoogleMap.class);

		PowerMockito.when(mapMock.isMyLocationEnabled()).thenReturn(false);

		DefaultMap customMap = new DefaultMap(mapMock);
		customMap.configure();

		Mockito.verify(mapMock, times(1)).setMyLocationEnabled(true);
	}
	
	@Test
	public void verifyConfigWithMyLocationAlreadyEnabled() throws InstantiationException,
			IllegalAccessException {
		PowerMockito.mockStatic(GoogleMap.class);
		GoogleMap mapMock = PowerMockito.mock(GoogleMap.class);

		PowerMockito.when(mapMock.isMyLocationEnabled()).thenReturn(true);

		DefaultMap customMap = new DefaultMap(mapMock);
		customMap.configure();

		Mockito.verify(mapMock, times(0)).setMyLocationEnabled(true);
	}
}

package com.unittestcloud.youconfapp_utils.map;

import static org.mockito.Mockito.times;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
	public void verifyConfigurationOfDefaultMap()
			throws InstantiationException, IllegalAccessException {
		PowerMockito.mockStatic(GoogleMap.class);
		GoogleMap mapMock = PowerMockito.mock(GoogleMap.class);

		PowerMockito.when(mapMock.isMyLocationEnabled()).thenReturn(false);

		DefaultMap customMap = new DefaultMap(mapMock);
		customMap.addMarkers(addDefaultDestinationMarkerOptions());
		customMap.configure();

		Mockito.verify(mapMock, times(1)).setMyLocationEnabled(true);
	}

	@Test
	public void verifyConfigWithMyLocationAlreadyEnabled()
			throws InstantiationException, IllegalAccessException {
		PowerMockito.mockStatic(GoogleMap.class);
		GoogleMap mapMock = PowerMockito.mock(GoogleMap.class);

		PowerMockito.when(mapMock.isMyLocationEnabled()).thenReturn(true);

		DefaultMap customMap = new DefaultMap(mapMock);
		customMap.addMarkers(addDefaultDestinationMarkerOptions());
		customMap.configure();

		Mockito.verify(mapMock, times(0)).setMyLocationEnabled(true);
	}

	/**
	 * Data provider
	 * 
	 * @return
	 */
	public ArrayList<MarkerOptions> addDefaultDestinationMarkerOptions() {
		LatLng HAMBURG = new LatLng(53.558, 9.927);
		LatLng KIEL = new LatLng(53.551, 9.993);

		ArrayList<MarkerOptions> list = new ArrayList<MarkerOptions>();

		list.add(new MarkerOptions().position(HAMBURG).title("Hamburg"));
		list.add(new MarkerOptions().position(KIEL).title("Kiel"));

		return list;
	}

}

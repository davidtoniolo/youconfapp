package com.unittestcloud.youconfapp_app;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.unittestcloud.R;

/**
 * 
 * @author davidtoniolo
 *
 */
@RunWith(RobolectricTestRunner.class)
public class MainActivityIT {

	private MainActivity activity;
	
    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
    }
	
    @Test
    public void shouldHaveAppName() throws Exception {
        String appName = activity.getResources().getString(R.string.app_name);
        assertThat(appName, equalTo("youconf!app"));
    }
    
}


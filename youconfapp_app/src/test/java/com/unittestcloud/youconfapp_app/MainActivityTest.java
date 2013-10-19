package com.unittestcloud.youconfapp_app;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * 
 * @author davidtoniolo
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {
	
    @Before
    public void setUp() throws Exception {
    }
	
    @Test
    public void dummy() throws Exception {
        assertThat("youconf!app", equalTo("youconf!app"));
    }
    
}


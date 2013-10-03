package com.unittestcloud.youconfapp_app;

import com.unittestcloud.R;

import android.app.Activity;
import android.os.Bundle;
import de.akquinet.android.androlog.Log;

/**
 * Homescreen
 * 
 * @author davidtoniolo
 *
 */
public class MainActivity extends Activity {

    @SuppressWarnings("unused")
	private static final String TAG = "youconfapp_app.MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.init(this);
		
        setContentView(R.layout.main);
    }
	
}


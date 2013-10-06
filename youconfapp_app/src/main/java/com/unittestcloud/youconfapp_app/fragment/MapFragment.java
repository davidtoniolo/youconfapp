package com.unittestcloud.youconfapp_app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.unittestcloud.R;

/**
 * 
 * @author davidtoniolo
 * 
 */
public class MapFragment extends Fragment {

	/**
	 * Empty public constructor to meet MapFragment requirements.
	 */
	public MapFragment() {
		// must be empty and public
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.gmaps, container, false);
		return view;
	}

}

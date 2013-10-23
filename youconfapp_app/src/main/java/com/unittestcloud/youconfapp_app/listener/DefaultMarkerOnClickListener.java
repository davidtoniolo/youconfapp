package com.unittestcloud.youconfapp_app.listener;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.Marker;
import com.unittestcloud.R;

/**
 * Opens a dialog with some information.
 * 
 * @author davidtoniolo
 * 
 */
public class DefaultMarkerOnClickListener implements OnMarkerClickListener {

	private Context context;

	public DefaultMarkerOnClickListener(Context context) {
		this.context = context;
	}

	/**
	 * TODO avoid open dialog for the MyLocation marker.
	 */
	@Override
	public boolean onMarkerClick(Marker marker) {
		marker.showInfoWindow();

		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.dialog_preferences);
		dialog.setTitle(marker.getTitle());

		TextView description = (TextView) dialog.findViewById(R.id.description);
		description.setText("description here");

		dialog.show();
		return true;
	}

}

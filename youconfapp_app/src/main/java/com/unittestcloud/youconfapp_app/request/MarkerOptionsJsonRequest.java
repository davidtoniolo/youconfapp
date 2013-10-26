package com.unittestcloud.youconfapp_app.request;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharEncoding;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import com.unittestcloud.youconfapp_app.model.MarkerOptionsDataModel;
import com.unittestcloud.youconfapp_app.ormlite.entity.MarkerOptionsInfo;

/**
 * Worker for RoboSpice
 * 
 * @see https
 *      ://github.com/octo-online/robospice/wiki/Starter-Guide#wiki-create-a
 *      -spicerequest-and-a-listener
 * 
 * @author davidtoniolo
 * 
 */
public class MarkerOptionsJsonRequest extends
		SpringAndroidSpiceRequest<MarkerOptionsDataModel> {

	private String url = "http://";

	public MarkerOptionsJsonRequest(final String url) {
		super(MarkerOptionsDataModel.class);
		this.url = url;
	}

	@Override
	public MarkerOptionsDataModel loadDataFromNetwork() throws Exception {
		// TODO load data from webservice (this.url) using getRestTemplate()...

		String jsonBody = IOUtils.toString(new InputStreamReader(new URL(url)
				.openStream(), CharEncoding.UTF_8));

		MarkerOptionsDataModel model = new MarkerOptionsDataModel();

		if (jsonBody.isEmpty()) {
			return model;
		}

		// build object from json
		ObjectMapper mapper = new ObjectMapper();
		List<MarkerOptionsInfo> entities = mapper.readValue(
				jsonBody,
				mapper.getTypeFactory().constructCollectionType(List.class,
						MarkerOptionsInfo.class));

		for (MarkerOptionsInfo entity : entities) {
			MarkerOptions option = new MarkerOptions()
					.position(
							new LatLng(entity.getLatitude(), entity
									.getLongitude())).title(entity.getTitle())
					.snippet(entity.getDescription());

			int iconResourceId = MarkerOptionsDataModel
					.loadResourceIdByIconType(entity.getIconType());

			if (0 != iconResourceId) {
				option.icon(BitmapDescriptorFactory
						.fromResource(iconResourceId));
			}

			model.addOption(option);
		}
		return model;
	}

}

package com.unittestcloud.youconfapp_app.request;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import com.unittestcloud.youconfapp_app.model.MarkerOptionsDao;

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
		SpringAndroidSpiceRequest<MarkerOptionsDao> {

	public MarkerOptionsJsonRequest() {
		super(MarkerOptionsDao.class);
	}

	@Override
	public MarkerOptionsDao loadDataFromNetwork() throws Exception {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO load data from webservice using getRestTemplate()
		// TODO build dao, pojo, etc.
		
		MarkerOptionsDao data = new MarkerOptionsDao();
		return data;
	}

}

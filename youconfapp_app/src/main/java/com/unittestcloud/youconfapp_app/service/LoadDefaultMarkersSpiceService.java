package com.unittestcloud.youconfapp_app.service;

import java.util.List;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Application;

import com.octo.android.robospice.SpringAndroidSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.springandroid.json.jackson.JacksonObjectPersisterFactory;

/**
 * Default REST template for JSON data.
 * 
 * There are many others REST templates, e.g. for OrmLite data mapping, etc.
 * 
 * @see https
 *      ://github.com/octo-online/RoboSpice-samples/blob/release/robospice-sample
 *      -ormlite/src/com/octo/android/robospice/sample/ormlite/network/
 *      SampleSpiceService.java
 * 
 * @author davidtoniolo
 * 
 */
public class LoadDefaultMarkersSpiceService extends SpringAndroidSpiceService {

	@Override
	public RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		// web services support json responses
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
		FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
		final List<HttpMessageConverter<?>> listHttpMessageConverters = restTemplate
				.getMessageConverters();

		listHttpMessageConverters.add(jsonConverter);
		listHttpMessageConverters.add(formHttpMessageConverter);
		listHttpMessageConverters.add(stringHttpMessageConverter);
		restTemplate.setMessageConverters(listHttpMessageConverters);
		return restTemplate;
	}

	@Override
	public CacheManager createCacheManager(Application application)
			throws CacheCreationException {
		CacheManager cacheManager = new CacheManager();
		JacksonObjectPersisterFactory jacksonObjectPersisterFactory = new JacksonObjectPersisterFactory(
				application);
		cacheManager.addPersister(jacksonObjectPersisterFactory);
		return cacheManager;
	}

}

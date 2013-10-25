package com.unittestcloud.youconfapp_app.ormlite.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * @author davidtoniolo
 * 
 */
@DatabaseTable(tableName = "marker_options_info")
public class MarkerOptionsInfo {

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_ICONTYPE = "iconType";
	public static final String COLUMN_LNG = "longitude";
	public static final String COLUMN_LAT = "latitude";

	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField(columnName = COLUMN_TITLE, canBeNull = false)
	private String title;
	
	@DatabaseField(columnName = COLUMN_DESCRIPTION)
	private String description;
	
	@DatabaseField(columnName = COLUMN_ICONTYPE)
	private String iconType;
	
	@DatabaseField(columnName = COLUMN_LNG, canBeNull = false)
	private float longitude;
	
	@DatabaseField(columnName = COLUMN_LAT, canBeNull = false)
	private float latitude;
	
	@Override
	public String toString() {
		return id + ";" + title;
	}

	public MarkerOptionsInfo() {
		// ORMLite needs a no-arg constructor
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIconType() {
		return iconType;
	}

	public void setIconType(String iconType) {
		this.iconType = iconType;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

}

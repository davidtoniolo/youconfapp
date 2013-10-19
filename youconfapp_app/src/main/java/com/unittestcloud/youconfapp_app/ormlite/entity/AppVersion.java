package com.unittestcloud.youconfapp_app.ormlite.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * @author davidtoniolo
 * 
 */
@DatabaseTable(tableName = "appversion")
public class AppVersion {

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_VERSION = "version_number";
	
	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(columnName = COLUMN_VERSION, canBeNull = false, unique = true)
	private String versionNumber;

	@Override
	public String toString() {
		return id + ";" + versionNumber;
	}

	public AppVersion() {
		// ORMLite needs a no-arg constructor
	}
	
	public AppVersion(String version) {
		versionNumber = version;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

}

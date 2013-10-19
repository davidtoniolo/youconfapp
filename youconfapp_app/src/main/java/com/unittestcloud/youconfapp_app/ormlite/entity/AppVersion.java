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

	public static final String COLUMN_VERSION = "version_number";
	
	@DatabaseField(id = true, generatedId = true, canBeNull = false)
	private String id;

	@DatabaseField(columnName = COLUMN_VERSION, canBeNull = false)
	private String versionNumber;

	@Override
	public String toString() {
		return versionNumber;
	}

	public AppVersion() {
		// ORMLite needs a no-arg constructor
	}

	public AppVersion(String version) {
		versionNumber = version;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

}

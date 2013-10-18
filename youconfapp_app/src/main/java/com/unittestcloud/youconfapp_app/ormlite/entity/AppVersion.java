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

	@DatabaseField(id = true)
	private String id;

	@DatabaseField(columnName = "version_number", canBeNull = false)
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

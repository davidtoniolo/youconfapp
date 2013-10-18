package com.unittestcloud.youconfapp_app.ormlite.helper;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

/**
 * ORMLite helper program.
 * Running it will create a TXT file in the res/raw folder of this app.
 * 
 * This is for administration only. It is not part of the Android app itself.
 * 
 * @see http://ormlite.com/javadoc/ormlite-android/com/j256/ormlite/android/apptools/OrmLiteConfigUtil.html
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {
	
	public static void main(String[] args) throws SQLException, IOException {
		writeConfigFile("ormlite_config.txt");
	}
	
}

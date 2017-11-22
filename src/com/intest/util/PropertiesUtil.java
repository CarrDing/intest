package com.intest.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Properties;

import com.intest.constant.Constant;




public class PropertiesUtil {
	public static Properties prop = new Properties();   
    public static InputStream in = Object.class.getResourceAsStream(Constant.SOURCE_PATH);
    public static String getProperties(String key){
    	try {
			prop.load(in);
		} catch (IOException e) {
			throw new RuntimeException();
		}
    	return prop.getProperty(key);
    }
    
	public String getProperties(String key, String type, Clob info) {
		Properties properties = new Properties();
		InputStream is;
		try {
			is = info.getAsciiStream();
			properties.load(is);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.get(key).toString();
	}
}

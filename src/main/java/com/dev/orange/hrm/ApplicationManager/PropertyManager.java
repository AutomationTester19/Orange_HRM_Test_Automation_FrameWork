package com.dev.orange.hrm.ApplicationManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	
	static Properties prop = null;
	static FileInputStream inputStream = null;
	
	public  Properties readPropertyFile() {
		
	    prop = new Properties();
		try {
			inputStream = new FileInputStream(DriverManager.propertyFilePath);
			prop.load(inputStream);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;

	}
	

}

package com.dev.orange.hrm.ApplicationManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FactoryManager {

	public static  Properties prop = null ;
	public static FileInputStream inputStream = null;
	private ChromeOptions chromeOptions;
	private FirefoxOptions firefoxOptions;
	private EdgeOptions edgeOptions;
	
	   public static String defaultPropertyFilePath = System.getProperty("user.dir");
	   public static String propertyFilePath = defaultPropertyFilePath+ "//src//main//resources//application.properties";

	public static Properties readPropertyFile() {

		prop = new Properties();
		try {
			inputStream = new FileInputStream(propertyFilePath);
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	public  String getInputProperty(String key) {

		prop = readPropertyFile();
		return prop.getProperty(key);
	}
	
	public ChromeOptions getChromeOptions() {

		chromeOptions = new ChromeOptions();
		if (Boolean.parseBoolean(getInputProperty("headless").trim()))
			chromeOptions.addArguments("--headless");
		if (Boolean.parseBoolean(getInputProperty("incognito").trim()))
			chromeOptions.addArguments("--incognito");

		if (Boolean.parseBoolean(getInputProperty("remote"))) {
			chromeOptions.setCapability("browserName", "chrome");
			chromeOptions.setBrowserVersion(getInputProperty("browserversion").trim());

			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", getInputProperty("testname"));
			chromeOptions.setCapability("selenoid:options", selenoidOptions);

		}
		
		chromeOptions.addArguments("--remote-allow-origins=*");
		chromeOptions.setCapability("browserVersion", "121.0.6167.85");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("--headless");

		return chromeOptions;
	}

	public FirefoxOptions getFirefoxOptions() {

		firefoxOptions = new FirefoxOptions();
		if (Boolean.parseBoolean(getInputProperty("headless").trim()))
			firefoxOptions.addArguments("--headless");
		if (Boolean.parseBoolean(getInputProperty("incognito").trim()))
			firefoxOptions.addArguments("--incognito");

		if (Boolean.parseBoolean(getInputProperty("remote"))) {
			firefoxOptions.setCapability("browserName", "firefox");
			firefoxOptions.setBrowserVersion(getInputProperty("browserversion").trim());

			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", getInputProperty("testname"));
			firefoxOptions.setCapability("selenoid:options", selenoidOptions);

		}
		return firefoxOptions;
	}

	public EdgeOptions getEdgeOptions() {
		edgeOptions = new EdgeOptions();
		edgeOptions.setCapability("platform", Platform.LINUX);
		if (Boolean.parseBoolean(getInputProperty("headless").trim()))
			edgeOptions.addArguments("--headless");
		if (Boolean.parseBoolean(getInputProperty("incognito").trim()))
			edgeOptions.addArguments("--inPrivate");
		return edgeOptions;
	}

}

package com.dev.orange.hrm.ApplicationManager;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.dev.orange.hrm.Utilities.Log;
import com.dev.orange.hrm.Utilities.Utils;
import com.dev.orange.hrm.pageObjects.BasePage;
import com.dev.orange.hrm.pageObjects.DashBoardPage;

public class DriverManager {

	protected static String defaultPropertyFilePath = System.getProperty("user.dir");
	protected static String propertyFilePath = defaultPropertyFilePath + "//src//main//resources//application.properties";

	public static Properties prop = null;
	public static FileInputStream inputStream = null;

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	protected static PropertyManager property = null;
	protected static BasePage basePage = null;
	protected static DashBoardPage dashBoardPage = null;
	protected static Utils utils = null;

	public static void pageObjectInitialization() {

		property = new PropertyManager();
		basePage = new BasePage();
		dashBoardPage = new DashBoardPage(driver());
		utils = new Utils();
	}

	public static void destroyPageObjects(){

		property = null;
		basePage = null;
		dashBoardPage = null;
		utils = null;
	}

	public static WebDriver driver() {
		return driver.get();
	}

	public static void deleteAllCookies() {
		driver().manage().deleteAllCookies();
	}

	public static void maximizeWindow() {
		driver().manage().window().maximize();
	}

	public static String getInputProperty(String key) {

		prop = property.readPropertyFile();
		return prop.getProperty(key);
	}

	public static Object setInputProperty(String key, String value) {
		
		return prop.setProperty(key, value);
	}
	public static void openBrowser(String browser) {

		if (getInputProperty("remote")!=null) {
			//driver.set(new RemoteWebDriver(null));
		} else
			createInstance(browser);
	}

	public static void createInstance(String browser) {

		switch (browser) {

		case "chrome":
			driver.set(new ChromeDriver());
			break;
		case "edge":
			driver.set(new EdgeDriver());
			break;
		default:
			break;

		}

		Log.info(browser + " Browser Launched From the Instance --> " + driver());

	}

	public static void launchApp(){

		driver().get(getInputProperty("url"));
	}
}

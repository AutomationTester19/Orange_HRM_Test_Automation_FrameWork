package com.dev.orange.hrm.ApplicationManager;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import com.dev.orange.hrm.pageObjects.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.dev.orange.hrm.Utilities.Utils;
import com.dev.orange.hrm.pageObjects.BasePage;
import com.dev.orange.hrm.pageObjects.DashBoardPage;

public class DriverManager {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	protected static FactoryManager factoryManager = null;
	protected static BasePage basePage = null;
	protected static DashBoardPage dashBoardPage = null;
	protected static LoginPage loginPage = null;
	protected static Utils utils = null;

	public static WebDriver driver() {

		return driver.get();
	}

	public static void createInstance() {

		String browserName = factoryManager.getInputProperty("browser");
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver(factoryManager.getChromeOptions()));
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver(factoryManager.getEdgeOptions()));
			break;
		default:
			break;
		}
	}

	public static void openBrowser(String browserName) {

		if (factoryManager.getInputProperty("remote") == "GRID_URL") {

			switch (browserName) {
			case "chrome":
				try {
					driver.set(new RemoteWebDriver(new URL(factoryManager.getInputProperty("browser")),
							factoryManager.getChromeOptions()));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			case "edge":
				try {
					driver.set(new RemoteWebDriver(new URL(factoryManager.getInputProperty("browser")),
							factoryManager.getEdgeOptions()));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			case "firefox":
				try {
					driver.set(new RemoteWebDriver(new URL(factoryManager.getInputProperty("browser")),
							factoryManager.getFirefoxOptions()));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}

		} else {
			createInstance();
		}
	}

	public static void deleteAllCookies() {

		driver().manage().deleteAllCookies();
	}

	public static void maximizeWindow() {

		driver().manage().window().maximize();
	}

	public static void pageLoadTimeOut(long seconds) {

		driver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
	}

	public static void launchApp() {

		driver().get(factoryManager.getInputProperty("url"));
		utils.sleep(2000);
	}

	public static void pageObjectInitialization() {

		factoryManager = new FactoryManager();
		basePage = new BasePage();
		dashBoardPage = new DashBoardPage(driver());
		loginPage = new LoginPage(driver());
		utils = new Utils();
	}

	public static void destroyPageObjects() {

		basePage = null;
		dashBoardPage = null;
		utils = null;
		loginPage = null;
	}

	public static String getScreenshot(String methodName) {

		File srcFile = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";
		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}

package com.qa.orange.hrm.baseTestSuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.dev.orange.hrm.ApplicationManager.DriverManager;


public class BaseTestSuite extends DriverManager {

	@BeforeMethod
    public void setUp(){

         pageObjectInitialization();
        /* if(browserName!=null){
             prop.setProperty("browser",browserName);
         }*/
         openBrowser(getInputProperty("browser"));
         deleteAllCookies();
         maximizeWindow();
         launchApp();
     }

	@AfterMethod
    public void tearDown(){

         destroyPageObjects();
         if(driver()!=null){
             driver().quit();
         }
     }
}

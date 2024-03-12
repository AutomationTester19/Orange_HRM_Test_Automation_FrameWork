package com.qa.orange.hrm.baseTestSuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.dev.orange.hrm.ApplicationManager.DriverManager;
import org.testng.annotations.Parameters;



public class BaseTestSuite extends DriverManager {

    @BeforeMethod
    public void setUp(String browserName){

        String browserVal = " ";
         pageObjectInitialization();
         if(browserName!=null){
             browserVal = factoryManager.setProperty("browser",browserName);
         }
        openBrowser(browserVal);
        deleteAllCookies();
        maximizeWindow();
        launchApp();
        loginPage.loginOrangeHRM();


     }

    @AfterMethod
    public void tearDown(){

         destroyPageObjects();
         if(driver()!=null){
             driver().quit();
         }
     }
}

package com.dev.orange.hrm.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.dev.orange.hrm.ApplicationManager.DriverManager;

public class LoginPage extends DriverManager {

    public static By username_locator = By.xpath("//input[@name='username' and @placeholder='Username']");
    public static By password_locator  = By.xpath("//input[@name='password' and @placeholder='Password']");
    public static By loginBtn_locator    = By.xpath("//button[contains(@class,'login-button')]");

    public LoginPage(WebDriver driver){

        PageFactory.initElements(driver, this);
    }

    public void loginOrangeHRM(){

        String username = factoryManager.getInputProperty("username");
        String password = factoryManager.getInputProperty("password");

        if(!username.isEmpty() && !password.isEmpty()){

                basePage.clickAndEnterText(username_locator,username);
                utils.sleep(200);
                basePage.clickAndEnterText(password_locator,password);
                utils.sleep(200);
                basePage.getWebElement(loginBtn_locator).click();
        }
        utils.sleep(1000);

    }

}

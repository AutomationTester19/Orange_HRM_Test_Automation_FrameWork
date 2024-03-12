package com.dev.orange.hrm.pageObjects;

import com.dev.orange.hrm.ApplicationManager.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage extends DriverManager{

    public WebElement getWebElement(By locator){
         return driver().findElement(locator);
    }

    @Step("Enter Value in {1} in element: {0} ")
    public void clickAndEnterText(By locator, String input){

        WebElement element = getWebElement(locator);
        if(element.isDisplayed()){
            element.sendKeys(input);
        }
    }
}

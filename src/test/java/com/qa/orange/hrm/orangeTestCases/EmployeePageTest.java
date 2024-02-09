package com.qa.orange.hrm.orangeTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.orange.hrm.baseTestSuite.BaseTestSuite;



public class EmployeePageTest extends BaseTestSuite{

    @Test
    public void tc_hrm_employee_001(){

       String title = driver().getTitle();
       Assert.assertEquals(title,"OrangeHRM");
   }

   @Test
    public void tc_hrm_employee_002(){

       WebElement loginBtn = driver().findElement(By.xpath("//button[contains(@class,'orangehrm-login-button')]"));
       Assert.assertTrue(loginBtn.isDisplayed());
   }
}

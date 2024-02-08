package com.dev.orange.hrm.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.dev.orange.hrm.ApplicationManager.DriverManager;

public class DashBoardPage extends DriverManager{

	
	public DashBoardPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
}

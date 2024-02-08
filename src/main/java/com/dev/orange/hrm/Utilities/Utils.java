package com.dev.orange.hrm.Utilities;

import com.dev.orange.hrm.ApplicationManager.DriverManager;

public class Utils extends DriverManager {
	
	public void sleep(long seconds) {
		
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}

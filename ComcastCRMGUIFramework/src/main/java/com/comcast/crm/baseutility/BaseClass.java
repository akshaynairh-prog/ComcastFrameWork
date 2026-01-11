package com.comcast.crm.baseutility;

import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.objectrepository.HomePage;
import com.comcast.crm.generic.objectrepository.LoginPage;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;


public class BaseClass {
	public FileUtility flib=new FileUtility();
	public JavaUtility jlib=new JavaUtility();
	public WebdriverUtility wlib=new WebdriverUtility();
	public ExcelUtility elib=new ExcelUtility();
	public WebDriver driver=null;
	
	
	
	@BeforeSuite
	public void configBS() {
		System.out.println("connected to database");
	}
	
	@BeforeClass
	public void configBC() throws IOException {
		System.out.println("Launch the browser");
		String browser=flib.getDatafromPropertiesFile("browser");
		driver=wlib.launchBrowser(browser);
		UtilityClassObject.setDriver(driver);
		System.out.println(driver);
		
	}
	
	@BeforeMethod
	public void configBM() throws IOException {
		String url=flib.getDatafromPropertiesFile("url");
		String username=flib.getDatafromPropertiesFile("username");
		String password=flib.getDatafromPropertiesFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(url,username, password);
	
	}
	
	@AfterMethod
	public void configAM()throws IOException{
		HomePage hp=new HomePage(driver);
		hp.logout();
	}
	
	@AfterClass
	public void configureAC() {
		driver.quit();
	}
	
	@AfterSuite
	public void configureAS() {
		System.out.println("DB close, report backup");
	}
	
	
	
	
	
	
	

	
	
	
	
}

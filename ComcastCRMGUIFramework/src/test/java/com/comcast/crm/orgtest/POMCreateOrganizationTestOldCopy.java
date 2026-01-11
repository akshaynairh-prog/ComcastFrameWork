package com.comcast.crm.orgtest;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.objectrepository.CreatingNewOrganizationPage;
import com.comcast.crm.generic.objectrepository.HomePage;
import com.comcast.crm.generic.objectrepository.LoginPage;
import com.comcast.crm.generic.objectrepository.OrganizationInfo;
import com.comcast.crm.generic.objectrepository.OrganizationsPage;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;

public class POMCreateOrganizationTestOldCopy {

	public static void main(String[] args) throws IOException {
	
		FileUtility flib=new FileUtility();
		JavaUtility jlib=new JavaUtility();
		WebdriverUtility wlib=new WebdriverUtility();
		ExcelUtility elib=new ExcelUtility();
		String browser=flib.getDatafromPropertiesFile("browser");
		String url=flib.getDatafromPropertiesFile("url");
		String username=flib.getDatafromPropertiesFile("username");
		String password=flib.getDatafromPropertiesFile("password");

		
		
		WebDriver driver=wlib.launchBrowser(browser);
		wlib.waitForPageLoad(driver);
		driver.get(url);
		
		String orgname=elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumber();
		String shipad=elib.getDataFromExcel("org", 1, 5)+jlib.getRandomNumber();
		//step 1 : login
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(url,username, password);
		
		//navigate to organization
		HomePage hp=new HomePage(driver);
		hp.getOrglink().click();		
		
		//click on creat org
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		//Enter details for org
		CreatingNewOrganizationPage cp=new CreatingNewOrganizationPage(driver);
		cp.getOrgNameEdt().sendKeys(orgname);
		cp.getShipAddEdt().sendKeys(shipad);
		//save
		cp.getSaveBtn().click();
		
		
		//verify with createdorgname with 
		OrganizationInfo oi=new OrganizationInfo(driver);
		String orgnameprint=oi.getOrgnameprint().getText();
		if(orgnameprint.contains(orgname)) {
			System.out.println(orgname+"is created == PASS");
		}
		else {
			System.out.println(orgname+"is not created == FAIL");
		}

		
		//logout
		hp.logout();
		
		
		driver.quit();
		
		
		
	}}
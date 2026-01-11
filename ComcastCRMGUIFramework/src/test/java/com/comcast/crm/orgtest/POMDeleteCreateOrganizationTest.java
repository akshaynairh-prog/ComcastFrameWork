package com.comcast.crm.orgtest;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.objectrepository.CreatingNewOrganizationPage;
import com.comcast.crm.generic.objectrepository.HomePage;
import com.comcast.crm.generic.objectrepository.LoginPage;
import com.comcast.crm.generic.objectrepository.OrganizationInfo;
import com.comcast.crm.generic.objectrepository.OrganizationsPage;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;

public class POMDeleteCreateOrganizationTest {

	public static void main(String[] args) throws IOException, InterruptedException {
	
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
		
		
		
		
		//verify with orgnameprinted with orgname
		OrganizationInfo oi=new OrganizationInfo(driver);
		String orgnameprint=oi.getOrgnameprint().getText();
		if(orgnameprint.contains(orgname)) {
			System.out.println(orgname+"is created == PASS");
		}
		else {
			System.out.println(orgname+"is not created == FAIL");
		}
		
		//navigate back to organizations page
		hp.getOrglink().click();
		//search orgname
		op.getSearchBtn().sendKeys(orgname);
		wlib.select(op.getSearchDD(), "Organization Name");
		op.getSubmitBtn().click();
		

		driver.findElement(By.xpath("//a[text()='"+orgname+"']/ancestor::tr/td[8]/a[text()='del']")).click();
		wlib.switchtoAlertandAccept(driver);
		
		//verify
		hp.getOrglink().click();
		op.getSearchBtn().sendKeys(orgname);
		wlib.select(op.getSearchDD(), "Organization Name");
		op.getSubmitBtn().click();
		
//		boolean status=driver.findElement(By.xpath("//a[text()='"+orgname+"']")).isDisplayed();
//		Assert.assertTrue(status);
		
		
		String orgnamecheck=driver.findElement(By.xpath("//span[contains(text(),'No Organization Found !')]")).getText();
		SoftAssert verify=new SoftAssert();
		verify.assertTrue(orgnamecheck.contains("No Organization"),"Org is deleted");
//		System.out.println(orgname+"is deleted");
		
		//logout
		hp.logout();
		
		
		driver.quit();
		
		
		
	}}
package com.comcast.crm.orgtest;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;

public class CreateOrganizationTest {

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
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		System.out.println("Logged in");
		
		//navigate to organization
		driver.findElement(By.linkText("Organizations")).click();
		
		//click on creat org
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Enter details for org
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.name("ship_street")).sendKeys(shipad);
		
		//save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify with headerinfo with excel
		String headerinfo=driver.findElement(By.className("dvHeaderText")).getText();
		
		if(headerinfo.contains(orgname)) {
			System.out.println(orgname+"is created == PASS");
		}
		else {
			System.out.println(orgname+"is not created == FAIL");
		}
		
		//verify with createdorgname with excelname
		String createdorgname=driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(orgname.contains(createdorgname)) {
			System.out.println(orgname+"is created == PASS");
		}
		else {
			System.out.println(orgname+"is not created == FAIL");
		}
		
		
		driver.quit();
		
		
		
	}}
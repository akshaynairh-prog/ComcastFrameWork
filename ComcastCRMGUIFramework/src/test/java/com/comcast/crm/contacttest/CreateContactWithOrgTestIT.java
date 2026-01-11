package com.comcast.crm.contacttest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;

public class CreateContactWithOrgTestIT {
public static void main(String[] args) throws IOException {

	FileUtility flib=new FileUtility();
	JavaUtility jlib=new JavaUtility();
	WebdriverUtility wlib=new WebdriverUtility();
	ExcelUtility elib=new ExcelUtility();
	
	String browser=flib.getDatafromPropertiesFile("browser");
	String url=flib.getDatafromPropertiesFile("url");
	String username=flib.getDatafromPropertiesFile("username");
	String password=flib.getDatafromPropertiesFile("password");
	
	//testscript
	String orgname=elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumber();
	String shipad=elib.getDataFromExcel("org", 1, 5)+jlib.getRandomNumber();
	//String phonenumber=elib.getDataFromExcel("org", 7, 3);
	
	WebDriver driver=wlib.launchBrowser(browser);
	wlib.waitForPageLoad(driver);
	driver.get(url);
	
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
//	driver.findElement(By.id("phone")).sendKeys(phonenumber);
	
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
	String lastname=elib.getDataFromExcel("contact", 7, 3)+jlib.getRandomNumber();
	//navigate to contact
	driver.findElement(By.linkText("Contacts")).click();
	//create contact
	driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	//enter details
	driver.findElement(By.name("lastname")).sendKeys(lastname);
	
	driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img[@title='Select']")).click();
	
	wlib.switchToTabOnURL(driver, "module=Accounts");
	driver.findElement(By.name("search_text")).sendKeys(orgname);
	driver.findElement(By.name("search")).click();
	driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
	wlib.switchToTabOnURL(driver, "module=Contacts");
	
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	System.out.println("saved");
	String contactprint=driver.findElement(By.id("dtlview_Last Name")).getText();
	if(contactprint.contains(lastname)) {
			System.out.println(lastname+"is created == PASS");
	}
	else {
			System.out.println(lastname+"is not created == FAIL");
	}
	
	String orgnameprint=driver.findElement(By.id("mouseArea_Organization Name")).getText();
	if(orgnameprint.contains(orgname)) {
			System.out.println(orgname+"is created == PASS");
	}
	else {
			System.out.println(orgname+"is not created == FAIL");
	}
			
	driver.quit();
			
	
}
}

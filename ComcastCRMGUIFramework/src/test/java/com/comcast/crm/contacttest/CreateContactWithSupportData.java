package com.comcast.crm.contacttest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;

public class CreateContactWithSupportData {
public static void main(String[] args) throws IOException, Throwable {
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
	
	String lastname=elib.getDataFromExcel("contact", 7, 3)+jlib.getRandomNumber();
	
	//login
	driver.findElement(By.name("user_name")).sendKeys(username);
	driver.findElement(By.name("user_password")).sendKeys(password);
	driver.findElement(By.id("submitButton")).click();
	//navigate to org
	driver.findElement(By.linkText("Contacts")).click();
	//create org
	driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	//enter details
	driver.findElement(By.name("lastname")).sendKeys(lastname);
	
	String startdate=jlib.getSystemdateYYYYMMDD();
	String enddate=jlib.getRequiredDateYYYYMMDD(30);
	
	driver.findElement(By.name("support_start_date")).clear();
	driver.findElement(By.name("support_start_date")).sendKeys(startdate);
	driver.findElement(By.name("support_end_date")).clear();
	driver.findElement(By.name("support_end_date")).sendKeys(enddate);
	System.out.println(enddate);
	//save
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	System.out.println("saved");
	//verify headerinfo with industry and type
	
	Thread.sleep(3000);
	String startdateprint=driver.findElement(By.id("mouseArea_Support Start Date")).getText();
	if(startdateprint.contains(startdate)) {
			System.out.println(startdate+"is created == PASS");
	}
	else {
			System.out.println(startdate+"is not created == FAIL");
	}
	
	String reqdateprint=driver.findElement(By.id("dtlview_Support End Date")).getText();
	if(reqdateprint.contains(enddate)) {
			System.out.println(enddate+"is created == PASS");
	}
	else {
			System.out.println(enddate+"is not created == FAIL");
	}
			
	driver.quit();
	
	
}
}

package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;

public class CreateOrganizationWithPhoneNumber {

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
		String phonenumber=elib.getDataFromExcel("org", 7, 3);
		
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
		driver.findElement(By.id("phone")).sendKeys(phonenumber);
		
		//save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify with headerinfo with excel
		String headerinfo=driver.findElement(By.id("dtlview_Phone")).getText();
		
		if(headerinfo.contains(phonenumber)) {
			System.out.println(phonenumber+"is created == PASS");
		}
		else {
			System.out.println(phonenumber+"is not created == FAIL");
		}
		
		
		driver.quit();
		
		
		
	}}
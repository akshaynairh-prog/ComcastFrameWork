package com.comcast.crm.leadstest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;

public class CreateLeadsWithUserAssignedTo {
	public static void main(String[] args) throws Throwable {
		
		FileUtility flib=new FileUtility();
		JavaUtility jlib=new JavaUtility();
		WebdriverUtility wlib=new WebdriverUtility();
		ExcelUtility elib=new ExcelUtility();
		
		String browser=flib.getDatafromPropertiesFile("browser");
		String url=flib.getDatafromPropertiesFile("url");
		String username=flib.getDatafromPropertiesFile("username");
		String password=flib.getDatafromPropertiesFile("password");
		
		//testscript
		String lastname=elib.getDataFromExcel("leads", 1, 2)+jlib.getRandomNumber();
		String company=elib.getDataFromExcel("leads", 1, 3)+jlib.getRandomNumber();
		String group=elib.getDataFromExcel("leads", 1, 4);
		
		WebDriver driver=wlib.launchBrowser(browser);
		wlib.waitForPageLoad(driver);
		driver.get(url);
		
		//step 1 : login
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		System.out.println("Logged in");
		
		//navigate to leads
		driver.findElement(By.linkText("Leads")).click();
		
		//click on creat lead
		driver.findElement(By.xpath("//img[@alt='Create Lead...']")).click();
		
		//enter last name
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		//enter company
		driver.findElement(By.name("company")).sendKeys(company);
		//click on group
		driver.findElement(By.xpath("//input[@type='radio' and @value='T']")).click();
		WebElement groupselectElement=driver.findElement(By.name("assigned_group_id"));
		wlib.select(groupselectElement, group);
		

		//save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify headerinfo last name and company
				String lastnameprint=driver.findElement(By.id("dtlview_Last Name")).getText();
				if(lastnameprint.contains(lastname)) {
					System.out.println(lastname+"is created == PASS");
				}
				else {
					System.out.println(lastname+"is not created == FAIL");
				}
				
				String compnayprint=driver.findElement(By.id("dtlview_Company")).getText();
				if(compnayprint.contains(company)) {
					System.out.println(company+"is created == PASS");
				}
				else {
					System.out.println(company+"is not created == FAIL");
				}
				
				String groupprint=driver.findElement(By.id("dtlview_Assigned To")).getText();
				if(groupprint.contains(group)) {
					System.out.println(group+"is created == PASS");
				}
				else {
					System.out.println(group+"is not created == FAIL");
				}
			
				
				System.out.println(lastnameprint);
				System.out.println(compnayprint);
				System.out.println(groupprint);
				
				driver.quit();
	}
	}
		
	



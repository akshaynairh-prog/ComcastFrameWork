package com.comcast.crm.producttest;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;

public class DeleteCreateProductTest2 {

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
		
		String prodname=elib.getDataFromExcel("productscrm", 1, 0)+jlib.getRandomNumber();
		String shipad=elib.getDataFromExcel("org", 1, 5)+jlib.getRandomNumber();
		//step 1 : login
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		System.out.println("Logged in");
		
		//navigate to product
		driver.findElement(By.linkText("Products")).click();
		
		//click on creat prod
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//Enter details for prod
		driver.findElement(By.name("productname")).sendKeys(prodname);
	
		//save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify with headerinfo with excel
		String headerinfo=driver.findElement(By.className("lvtHeaderText")).getText();
		
		if(headerinfo.contains(prodname)) {
			System.out.println(prodname+"is created == PASS");
		}
		else {
			System.out.println(prodname+"is not created == FAIL");
		}
		
		//navigate to products
		driver.findElement(By.linkText("Products")).click();
		
		//search
		driver.findElement(By.name("search_text")).sendKeys(prodname);
		//in
		WebElement in=driver.findElement(By.name("search_field"));
		wlib.select(in,"Product Name");
		driver.findElement(By.xpath("//input[@value=' Search Now '  and @onclick=\"callSearch('Basic');\"]")).click();
		
		
		//delete
		WebElement del=driver.findElement(By.xpath("(//a[text()='"+prodname+"'])[position()=2]/ancestor::tr/td[9]/a[text()='del']"));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(del));
		del.click();
		
		
		
		driver.quit();
		
		
		
	}}
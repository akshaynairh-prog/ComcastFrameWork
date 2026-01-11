package com.comcast.crm.orgtest;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;

public class CreateOrgWithIndustry {

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
		String industry=elib.getDataFromExcel("org", 4, 3);
		String type=elib.getDataFromExcel("org", 4, 4);
		
		WebDriver driver=wlib.launchBrowser(browser);
		wlib.waitForPageLoad(driver);
		driver.get(url);
		
		//login
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		//navigate to org
		driver.findElement(By.linkText("Organizations")).click();
		//create org
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//enter details
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.name("ship_street")).sendKeys(shipad);
		
		WebElement industryele=driver.findElement(By.name("industry"));
		WebElement typeele=driver.findElement(By.name("accounttype"));
		//select industry and type
		wlib.select(industryele, industry);
		wlib.select(typeele, type);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify headerinfo with industry and type
		String industryprint=driver.findElement(By.id("dtlview_Industry")).getText();
		if(industryprint.contains(industry)) {
			System.out.println(industry+"is created==PASS");
		}
		else {
			System.out.println(industry+"is not created==FAIL");
		}
		
		String typeprint=driver.findElement(By.id("dtlview_Type")).getText();
		if(typeprint.contains(type)) {
			System.out.println(type+"is created==PASS");
		}
		else {
			System.out.println(type+"is not created==FAIL");
		}
		driver.quit();		
		}

}

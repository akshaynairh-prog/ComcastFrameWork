package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class WebdriverUtility {
	
	WebDriver driver;
	public WebDriver launchBrowser(String browser) {
		
		if(browser.equals("chrome")) {
			return new ChromeDriver();
		}
		else if(browser.equals("edge")) {
			return new EdgeDriver();
		}
		else if(browser.equals("firefox")) {
			return new FirefoxDriver();
		}
		else {
			return new ChromeDriver();
		}
		
	}
	
	
	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
	public void waitForElementPresent(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void select(WebElement element,String text) {
	Select select=new Select(element);
	select.selectByVisibleText(text);
	}
	public void select(WebElement element,int index) {
		Select select=new Select(element);
		select.selectByIndex(index);
	}
	
	public void switchToTabOnURL(WebDriver driver,String partialurl) {
		Set<String>wind=driver.getWindowHandles();
		Iterator<String>iterator=wind.iterator();
		while (iterator.hasNext()) {
		
			String windowid=iterator.next();
			driver.switchTo().window(windowid);
			String acturl=driver.getCurrentUrl();
			if(acturl.contains(partialurl)) {
				break;
			}
		
		}
	}
	
	public void switchToTabOnTitle(WebDriver driver,String partialTitle) {
		Set<String>wind=driver.getWindowHandles();
		Iterator<String>iterator=wind.iterator();
		while (iterator.hasNext()) {
		
			String windowid=iterator.next();
			driver.switchTo().window(windowid);
			String acturl=driver.getTitle();
			if(acturl.contains(partialTitle)) {
				break;
			}
		
		}
	
	}
	public void switchtoAlertandAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void switchtoAlertandDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void mouseMoveOnElement(WebDriver driver,WebElement element) {
		Actions actions=new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	public void doubleclick(WebDriver driver,WebElement element) {
		Actions actions=new Actions(driver);
		actions.doubleClick(element).perform();
	}
	
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver,String name) {
		driver.switchTo().frame(name);
	}
	
	public void switchToFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}

}
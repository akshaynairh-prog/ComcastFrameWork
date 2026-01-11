package com.comcast.crm.generic.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {
WebDriver driver;
	public LeadsPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//img[@alt=\"Create Lead...\"]")
	private WebElement createLeadsBtn;
	/**
	 * @return the createLeadsBtn
	 */
	public WebElement getCreateLeadsBtn() {
		return createLeadsBtn;
	}
	
	
	

}

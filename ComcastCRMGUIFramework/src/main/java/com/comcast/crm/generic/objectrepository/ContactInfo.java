package com.comcast.crm.generic.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfo {
	WebDriver driver;
	
	public ContactInfo(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(id="dtlview_Last Name")
	private WebElement lastnameprinted;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement orgnameprrintedContacts;
	
	@FindBy(id="mouseArea_Support Start Date")
	private WebElement startDateprinted;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement endDateprinted;

	public WebElement getLastnameprinted() {
		return lastnameprinted;
	}

	public WebElement getOrgnameprrintedContacts() {
		return orgnameprrintedContacts;
	}

	public WebElement getStartDateprinted() {
		return startDateprinted;
	}

	public WebElement getEndDateprinted() {
		return endDateprinted;
	}
	
	
	
	
}

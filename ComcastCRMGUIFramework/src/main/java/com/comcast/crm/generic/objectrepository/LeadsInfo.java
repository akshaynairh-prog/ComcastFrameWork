package com.comcast.crm.generic.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsInfo {
	WebDriver driver;
	public LeadsInfo(	WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="mouseArea_Last Name")
	private WebElement lastNamePrintedLeads;
	
	@FindBy(id="dtlview_Company")
	private WebElement companyPrintedLeads;
	
	@FindBy(id="dtlview_Assigned To")
	private WebElement userPrintedLeads;
	
	@FindBy(id="mouseArea_Assigned To")
	private WebElement groupPrintedLeads;
	/**
	 * @return the lastNamePrintedLeads
	 */
	public WebElement getLastNamePrintedLeads() {
		return lastNamePrintedLeads;
	}

	/**
	 * @return the companyPrintedLeads
	 */
	public WebElement getCompanyPrintedLeads() {
		return companyPrintedLeads;
	}

	/**
	 * @return the userPrintedLeads
	 */
	public WebElement getUserPrintedLeads() {
		return userPrintedLeads;
	}

	/**
	 * @return the groupPrintedLeads
	 */
	public WebElement getGroupPrintedLeads() {
		return groupPrintedLeads;
	}
	
	
	

}

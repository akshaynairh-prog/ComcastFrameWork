package com.comcast.crm.generic.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText = "Organizations")
	private WebElement orglink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactlink;
	
	@FindBy(linkText = "Leads")
	private WebElement leadslink;
	
	/**
	 * @return the leadslink
	 */
	public WebElement getLeadslink() {
		return leadslink;
	}

	@FindBy(linkText = "More")
	private WebElement morelink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignlink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signoutlink;
	
	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getContactlink() {
		return contactlink;
	}

	public WebElement getMorelink() {
		return morelink;
	}

	public WebElement getCampaignlink() {
		return campaignlink;
	}

	public WebElement getAdminImg() {
		return adminImg;
	}
	
	public void movetoCampaign() {
		Actions actions=new Actions(driver);
		actions.moveToElement(morelink).perform();
		campaignlink.click();
	}
	
	public void logout() {
		Actions actions=new Actions(driver);
		actions.moveToElement(adminImg).perform();
		signoutlink.click();
	}
}

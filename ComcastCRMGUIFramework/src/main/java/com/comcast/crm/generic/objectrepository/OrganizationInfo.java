package com.comcast.crm.generic.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfo {
WebDriver driver;

public OrganizationInfo(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
}

@FindBy(id="dtlview_Organization Name")
WebElement orgnameprint;

@FindBy(id="dtlview_Shipping Address")
WebElement shipAdprint;

public WebElement getOrgnameprint() {
	return orgnameprint;
}

public WebElement getShipAdprint() {
	return shipAdprint;
}

//public void verifyOrgName() {
//	String act
//}

}

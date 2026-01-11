package com.comcast.crm.generic.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewLeads {
WebDriver driver;

public CreatingNewLeads(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
}

@FindBy(name="lastname")
private WebElement lastnameLeads;

@FindBy(name="company")
private WebElement companyLeads;

/**
 * @return the userDD
 */
public WebElement getUserDD() {
	return userDD;
}

/**
 * @return the groupDD
 */
public WebElement getGroupDD() {
	return groupDD;
}

@FindBy(xpath="//input[@type='radio' and @value='U']")
private WebElement userCheckB;

@FindBy(name="assigned_user_id")
private WebElement userDD;

@FindBy(name="assigned_group_id")
private WebElement groupDD;

@FindBy(xpath="//input[@type='radio' and @value='T']")
private WebElement groupCheckB;

@FindBy(xpath="//input[@title=\"Save [Alt+S]\"]")
private WebElement saveBtnLeadsElement;
/**
 * @return the saveBtnLeadsElement
 */
public WebElement getSaveBtnLeadsElement() {
	return saveBtnLeadsElement;
}

/**
 * @return the lastnameLeads
 */
public WebElement getLastnameLeads() {
	return lastnameLeads;
}

/**
 * @return the companyLeads
 */
public WebElement getCompanyLeads() {
	return companyLeads;
}

/**
 * @return the userCheckB
 */
public WebElement getUserCheckB() {
	return userCheckB;
}

/**
 * @return the groupCheckB
 */
public WebElement getGroupCheckB() {
	return groupCheckB;
}



}

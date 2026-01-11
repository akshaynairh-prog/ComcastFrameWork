package com.comcast.crm.generic.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactsPage {

	WebDriver driver;

	public CreatingNewContactsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

		@FindBy(name="lastname")
		private WebElement lastNameEdt;
		
		@FindBy(name="support_start_date")
		private WebElement startDate;
		
		@FindBy(name="support_end_date")
		private WebElement endDate;
		
		@FindBy(name="account_name")
		private WebElement orgNameContacts;
		
		@FindBy(xpath = "//input[@title=\"Save [Alt+S]\" and contains(@class,'crmButton')]")
		private WebElement saveBtnContacts;
		
		@FindBy(xpath="//img[@alt='Select' and contains(@onclick,'module=Accounts')]")
		private WebElement selectOrgnameForContacts;

		public WebElement getLastNameEdt() {
			return lastNameEdt;
		}

		public WebElement getStartDate() {
			return startDate;
		}

		public WebElement getEndDate() {
			return endDate;
		}

		public WebElement getOrgNameContacts() {
			return orgNameContacts;
		}

		public WebElement getSaveBtnContacts() {
			return saveBtnContacts;
		}

		public WebElement getSelectOrgnameForContacts() {
			return selectOrgnameForContacts;
		}
		
		
		
}

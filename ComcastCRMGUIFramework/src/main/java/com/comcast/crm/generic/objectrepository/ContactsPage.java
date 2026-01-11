package com.comcast.crm.generic.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage{
	WebDriver driver;

	public ContactsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}	
		@FindBy(xpath = "//img[@title=\"Create Contact...\"]")
		private WebElement createNewContactbtn;
		
		@FindBy(name="search_text")
		private WebElement searchBtnContact;

		@FindBy(name="search_field")
		private WebElement searchDDContact;

		@FindBy(name="submit")
		private WebElement submitBtnContact;

		public WebElement getCreateNewContactbtn() {
			return createNewContactbtn;
		}

		public WebElement getSearchBtnContact() {
			return searchBtnContact;
		}

		public WebElement getSearchDDContact() {
			return searchDDContact;
		}

		public WebElement getSubmitBtnContact() {
			return submitBtnContact;
		}
		
		
}
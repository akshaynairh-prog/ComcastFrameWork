package com.comcast.crm.generic.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchForOrg {
WebDriver driver;
	public SearchForOrg(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="search_txt")
	private WebElement searchEdtSearchOrg;
	
	@FindBy(name="search_field")
	private WebElement orgnameInDDSearchOrg;
	
	@FindBy(name="search")
	private WebElement searchBtnSearchOrg;
	public WebElement getSearchEdtSearchOrg() {
		return searchEdtSearchOrg;
	}

	public WebElement getOrgnameInDDSearchOrg() {
		return orgnameInDDSearchOrg;
	}

	public WebElement getSearchBtnSearchOrg() {
		return searchBtnSearchOrg;
	}
	
	

}

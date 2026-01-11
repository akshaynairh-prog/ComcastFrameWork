package com.comcast.crm.contacttest;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.baseutility.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.objectrepository.ContactInfo;
import com.comcast.crm.generic.objectrepository.ContactsPage;
import com.comcast.crm.generic.objectrepository.CreatingNewContactsPage;
import com.comcast.crm.generic.objectrepository.CreatingNewOrganizationPage;
import com.comcast.crm.generic.objectrepository.HomePage;
import com.comcast.crm.generic.objectrepository.OrganizationInfo;
import com.comcast.crm.generic.objectrepository.OrganizationsPage;
import com.comcast.crm.generic.objectrepository.SearchForOrg;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;

public class CreateContactTest extends BaseClass {
	@Test
	public void createContactTest() throws IOException {
		// TestScriptData
		UtilityClassObject.getTest().log(Status.INFO,"Read data from Excel");
		String lastname = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();
		// navigate to contact
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to Contact Page");
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();
		// create contact
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to create Contact Page");
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactbtn().click();
		//enter details
		UtilityClassObject.getTest().log(Status.INFO,"Create a new COntact");
		CreatingNewContactsPage cncp = new CreatingNewContactsPage(driver);
		cncp.getLastNameEdt().sendKeys(lastname);
		// save
		cncp.getSaveBtnContacts().click();
		UtilityClassObject.getTest().log(Status.INFO,"Contact Saved");
		//verify headerinfo with industry and type
		ContactInfo ci = new ContactInfo(driver);
		String lastnameprint = ci.getLastnameprinted().getText();
		Assert.assertEquals(lastnameprint, lastname);
	}
	
	@Test
	public void CreateContactWithSupportData() throws EncryptedDocumentException, IOException, InterruptedException {
		//TestScript
		String lastname=elib.getDataFromExcel("contact", 7, 3)+jlib.getRandomNumber();
		// navigate to contact
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();
		// create contact
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactbtn().click();
		CreatingNewContactsPage cncp = new CreatingNewContactsPage(driver);
		cncp.getLastNameEdt().sendKeys(lastname);
		String startdate=jlib.getSystemdateYYYYMMDD();
		String enddate=jlib.getRequiredDateYYYYMMDD(30);
		cncp.getStartDate().clear();
		cncp.getStartDate().sendKeys(startdate);
		cncp.getEndDate().clear();
		cncp.getEndDate().sendKeys(enddate);
		// save
		cncp.getSaveBtnContacts().click();
		//verify headerinfo with industry and type
		ContactInfo ci=new ContactInfo(driver);
		String lastnameprint = ci.getLastnameprinted().getText();
		String startdateprint=ci.getStartDateprinted().getText().trim();
		String endateprint=ci.getEndDateprinted().getText().trim();
		Assert.assertEquals(lastnameprint, lastname);
		Assert.assertEquals(endateprint, enddate);
		Assert.assertEquals(startdateprint,startdate);	
	}
	
	@Test
	public void CreateContactWithOrgTestIT() throws EncryptedDocumentException, IOException {
		// testscript
		String orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		String shipad = elib.getDataFromExcel("org", 1, 5) + jlib.getRandomNumber();
		// navigate to organization
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		// click on creat org
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		// Enter details for org
		CreatingNewOrganizationPage cp = new CreatingNewOrganizationPage(driver);
		cp.getOrgNameEdt().sendKeys(orgname);
		cp.getShipAddEdt().sendKeys(shipad);
		// save
		cp.getSaveBtn().click();
		// verify with createdorgname with
		OrganizationInfo oi = new OrganizationInfo(driver);
		String orgnameprint = oi.getOrgnameprint().getText();
		Assert.assertEquals(orgnameprint, orgname);

		String lastname=elib.getDataFromExcel("contact", 7, 3)+jlib.getRandomNumber();
		// navigate to contact
		hp.getContactlink().click();
		// create contact
		ContactsPage cpc = new ContactsPage(driver);
		cpc.getCreateNewContactbtn().click();
		CreatingNewContactsPage cncp = new CreatingNewContactsPage(driver);
		cncp.getLastNameEdt().sendKeys(lastname);
		cncp.getSelectOrgnameForContacts().click();
		wlib.switchToTabOnURL(driver, "module=Accounts");
		SearchForOrg sf=new SearchForOrg(driver);
		sf.getSearchEdtSearchOrg().sendKeys(orgname);
		sf.getSearchBtnSearchOrg().click();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		wlib.switchToTabOnURL(driver, "module=Contacts");
		// save
		cncp.getSaveBtnContacts().click();
		System.out.println("saved");
		ContactInfo ci=new ContactInfo(driver);
		String lastnameprint=ci.getLastnameprinted().getText();
		Assert.assertEquals(lastname, lastnameprint);
		String orgnameprint21=ci.getOrgnameprrintedContacts().getText().trim();
		Assert.assertEquals(orgname, orgnameprint21);				
	}
	
}
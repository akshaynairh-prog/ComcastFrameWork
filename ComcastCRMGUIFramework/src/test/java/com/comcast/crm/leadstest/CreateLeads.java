package com.comcast.crm.leadstest;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.comcast.crm.baseutility.BaseClass;
import com.comcast.crm.generic.objectrepository.CreatingNewLeads;
import com.comcast.crm.generic.objectrepository.HomePage;
import com.comcast.crm.generic.objectrepository.LeadsInfo;
import com.comcast.crm.generic.objectrepository.LeadsPage;

public class CreateLeads extends BaseClass{
@Test
public void CreateLeads() throws EncryptedDocumentException, IOException{
	//testscript
	String lastname=elib.getDataFromExcel("leads", 1, 2)+jlib.getRandomNumber();
	String company=elib.getDataFromExcel("leads", 1, 3)+jlib.getRandomNumber();
	//navigate to leads
	HomePage hp=new HomePage(driver);
	hp.getLeadslink().click();
	//click on creat lead
	LeadsPage lp=new LeadsPage(driver);
	lp.getCreateLeadsBtn().click();
	//enter details
	CreatingNewLeads cnl=new CreatingNewLeads(driver);
	cnl.getLastnameLeads().sendKeys(lastname);
	cnl.getCompanyLeads().sendKeys(company);
	//save
	cnl.getSaveBtnLeadsElement().click();
	//verify headerinfo last name and company
	LeadsInfo li=new LeadsInfo(driver);
	String lastnameprint=li.getLastNamePrintedLeads().getText().trim();
	Assert.assertEquals(lastname, lastnameprint);
	String compnayprint=li.getCompanyPrintedLeads().getText().trim();
	Assert.assertEquals(company, compnayprint);
}

@Test
public void CreateLeadsWithUserAssignedTo() throws EncryptedDocumentException, IOException {
	//testscript
		String lastname=elib.getDataFromExcel("leads", 1, 2)+jlib.getRandomNumber();
		String company=elib.getDataFromExcel("leads", 1, 3)+jlib.getRandomNumber();
		String group=elib.getDataFromExcel("leads", 1, 4);
		String user=elib.getDataFromExcel("leads", 1, 5);
		//navigate to leads
		HomePage hp=new HomePage(driver);
		hp.getLeadslink().click();
		//click on creat lead
		LeadsPage lp=new LeadsPage(driver);
		lp.getCreateLeadsBtn().click();
		//enter details
		CreatingNewLeads cnl=new CreatingNewLeads(driver);
		cnl.getLastnameLeads().sendKeys(lastname);
		cnl.getCompanyLeads().sendKeys(company);
		cnl.getUserCheckB().click();
		WebElement userselectElement=cnl.getUserDD();
		wlib.select(userselectElement, user);
		//SAVE
		cnl.getSaveBtnLeadsElement().click();
		//verify
		LeadsInfo li=new LeadsInfo(driver);
		String lastnameprint=li.getLastNamePrintedLeads().getText().trim();
		Assert.assertEquals(lastname, lastnameprint);
		String compnayprint=li.getCompanyPrintedLeads().getText().trim();
		Assert.assertEquals(compnayprint, company);
		String userprint=li.getUserPrintedLeads().getText().trim();
		Assert.assertEquals(userprint, user);
}

@Test
public void CreateLeadsWithGroup() throws EncryptedDocumentException, IOException {
	//testscript
	String lastname=elib.getDataFromExcel("leads", 1, 2)+jlib.getRandomNumber();
	String company=elib.getDataFromExcel("leads", 1, 3)+jlib.getRandomNumber();
	String group=elib.getDataFromExcel("leads", 1, 4);
	String user=elib.getDataFromExcel("leads", 1, 5);
	//navigate to leads
	HomePage hp=new HomePage(driver);
	hp.getLeadslink().click();
	//click on creat lead
	LeadsPage lp=new LeadsPage(driver);
	lp.getCreateLeadsBtn().click();
	//enter details
	CreatingNewLeads cnl=new CreatingNewLeads(driver);
	cnl.getLastnameLeads().sendKeys(lastname);
	cnl.getCompanyLeads().sendKeys(company);
	cnl.getGroupCheckB().click();
	WebElement groupselectElement=cnl.getGroupDD();
	wlib.select(groupselectElement, group);
	//SAVE
	cnl.getSaveBtnLeadsElement().click();
	//verify
	LeadsInfo li=new LeadsInfo(driver);
	String lastnameprint=li.getLastNamePrintedLeads().getText().trim();
	Assert.assertEquals(lastname, lastnameprint);
	String compnayprint=li.getCompanyPrintedLeads().getText().trim();
	Assert.assertEquals(compnayprint, company);
	String groupprint=li.getUserPrintedLeads().getText().trim();
	Assert.assertEquals(groupprint, group);
}}

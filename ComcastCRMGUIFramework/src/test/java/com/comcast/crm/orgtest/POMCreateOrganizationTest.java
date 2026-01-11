package com.comcast.crm.orgtest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.baseutility.BaseClass;
import com.comcast.crm.generic.objectrepository.CreatingNewOrganizationPage;
import com.comcast.crm.generic.objectrepository.HomePage;
import com.comcast.crm.generic.objectrepository.OrganizationInfo;
import com.comcast.crm.generic.objectrepository.OrganizationsPage;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;


public class POMCreateOrganizationTest extends BaseClass {
	@Test
	public void createOrg() throws EncryptedDocumentException, IOException {
		// TestSCriptdata
		UtilityClassObject.getTest().log(Status.INFO,"Read data from excel");
		String orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		String shipad = elib.getDataFromExcel("org", 1, 5) + jlib.getRandomNumber();
		// navigate to organization
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to Org Page");
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		// click on creat org
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to Create Org Page");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		// Enter details for org
		UtilityClassObject.getTest().log(Status.INFO,"Create a New Org");
		CreatingNewOrganizationPage cp = new CreatingNewOrganizationPage(driver);
		cp.getOrgNameEdt().sendKeys(orgname);
		cp.getShipAddEdt().sendKeys(shipad);
		// save
		cp.getSaveBtn().click();
		UtilityClassObject.getTest().log(Status.INFO,orgname+"is created");
		// verify with createdorgname with
		OrganizationInfo oi = new OrganizationInfo(driver);
		SoftAssert verify=new SoftAssert();
		String orgnameprint = oi.getOrgnameprint().getText();
		Assert.assertEquals(orgnameprint, orgname);
		verify.assertAll();
	}

	
	@Test
	public void CreateOrgWithIndustry() throws IOException {
		// testscript
//		String orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		String orgname = elib.getDataFromExcel("org", 1, 4) + jlib.getRandomNumber();
		String shipad = elib.getDataFromExcel("org", 1, 5) + jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("org", 4, 3);
		String type = elib.getDataFromExcel("org", 4, 4);
		// navigate to org
		driver.findElement(By.linkText("Organizations")).click();
		// create org
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		// enter details
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.name("ship_street")).sendKeys(shipad);

		WebElement industryele = driver.findElement(By.name("industry"));
		WebElement typeele = driver.findElement(By.name("accounttype"));
		// select industry and type
		wlib.select(industryele, industry);
		wlib.select(typeele, type);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		// verify headerinfo with industry and type
		String industryprint = driver.findElement(By.id("dtlview_Industry")).getText();
		Assert.assertEquals(industry, industryprint);
		String typeprint = driver.findElement(By.id("dtlview_Type")).getText();
		Assert.assertEquals(type, typeprint);
		
	}

	@Test
	public void CreateOrganizationWithPhoneNumber() throws IOException {
		// testscript
		String orgname = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		String shipad = elib.getDataFromExcel("org", 1, 5) + jlib.getRandomNumber();
		String phonenumber = elib.getDataFromExcel("org", 7, 3);
		
		// navigate to organization
		driver.findElement(By.linkText("Organizations")).click();

		// click on creat org
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		// Enter details for org
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.name("ship_street")).sendKeys(shipad);
		driver.findElement(By.id("phone")).sendKeys(phonenumber);

		// save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify with headerinfo with excel
		String headerinfo = driver.findElement(By.id("dtlview_Phone")).getText();
		Assert.assertEquals(phonenumber, headerinfo);
	}

	@Test
	public void deleteCreateOrganizationTest() throws EncryptedDocumentException, IOException {
		String orgname=elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumber();
		String shipad=elib.getDataFromExcel("org", 1, 5)+jlib.getRandomNumber();
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
		// verify with orgnameprinted with orgname
		OrganizationInfo oi = new OrganizationInfo(driver);
		String orgnameprinted=oi.getOrgnameprint().getText();
		Assert.assertEquals(orgname, orgnameprinted);
		// navigate back to organizations page
		hp.getOrglink().click();
		// search orgname
		op.getSearchBtn().sendKeys(orgname);
		wlib.select(op.getSearchDD(), "Organization Name");
		op.getSubmitBtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']/ancestor::tr/td[8]/a[text()='del']")).click();
		wlib.switchtoAlertandAccept(driver);
		// verify
		hp.getOrglink().click();
		op.getSearchBtn().sendKeys(orgname);
		wlib.select(op.getSearchDD(), "Organization Name");
		op.getSubmitBtn().click();
		String orgnamecheck = driver.findElement(By.xpath("//span[contains(text(),'No Organization Found !')]"))
				.getText();
		SoftAssert verify = new SoftAssert();
		verify.assertTrue(orgnamecheck.contains("No Organization"),"Org is deleted");
	}

}

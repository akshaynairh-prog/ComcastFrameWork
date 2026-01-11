package Practice;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;

public class AmazonProductInfoTest {

ExcelUtility elib=new ExcelUtility();
WebdriverUtility wlib=new WebdriverUtility();

@Test(dataProvider = "getData" )
public void productinfo(String brandname, String productname) throws EncryptedDocumentException, IOException {
	WebDriver driver=new ChromeDriver();
	wlib.waitForPageLoad(driver);
	driver.get("https://www.amazon.in/");
	
	//Search for phones
	String valueString=elib.getDataFromExcel("product", 1, 0);
	driver.findElement(By.id("twotabsearchtextbox")).sendKeys(valueString,Keys.ENTER);
	//display product
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	String x1="//span[contains(text(),'"+productname+"')]/ancestor::div[@class=\"puisg-col-inner\"]/descendant::span[@class=\"a-price\"]";
	WebElement element=driver.findElement(By.xpath(x1));
	wait.until(ExpectedConditions.visibilityOf(element));
	
	String x2=driver.findElement(By.xpath(x1)).getText();
	System.out.println(x2);
	
	driver.quit();
	
}

@DataProvider
public Object[][] getData() throws EncryptedDocumentException, IOException{
	int rowcount=elib.getRowCount("product");
	System.out.println(rowcount);
	Object [][] objArr=new Object[rowcount][2];
	
	for (int i = 0; i < rowcount; i++) {
		objArr[i][0] = elib.getDataFromExcel("product", i+1, 0);
		objArr[i][1] = elib.getDataFromExcel("product", i+1, 1);
		
	}
	return objArr;
}
}


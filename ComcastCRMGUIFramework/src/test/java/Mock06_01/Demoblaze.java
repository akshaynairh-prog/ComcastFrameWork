package Mock06_01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.webdriverutility.WebdriverUtility;

public class Demoblaze {
	WebdriverUtility wlib=new WebdriverUtility();
	ExcelUtility elib=new ExcelUtility();
@Test
public void monitors() throws IOException, InterruptedException {
	WebDriver driver=new ChromeDriver();
	wlib.waitForPageLoad(driver);
	driver.manage().window().maximize();
	driver.get("https://www.demoblaze.com/");
	//click on monitors
	driver.findElement(By.linkText("Monitors")).click();
	WebDriverWait wt=new WebDriverWait(driver, Duration.ofSeconds(15));
	//click on next
//	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
//	WebElement clickElement=driver.findElement(By.linkText("Monitors"));
//	wait.until(ExpectedConditions.elementToBeClickable(clickElement));
	JavascriptExecutor js=(JavascriptExecutor)driver;
	
	// capture page-1 element
//	WebElement oldItem =driver.findElement(By.xpath("(//a[@class='hrefch'])[1]"));
	
	WebElement clickElement=driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
	js.executeScript("arguments[0].click()",clickElement);
	Thread.sleep(2000);
//	wt.until(ExpectedConditions.stalenessOf(oldItem));
	wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@class='hrefch']")));
	
	List<WebElement>monitorList=driver.findElements(By.xpath("//a[@class='hrefch']"));
	List<WebElement>pricelist=driver.findElements(By.xpath("//a[@class='hrefch']/ancestor::div[@class='card-block']/h5"));
	FileInputStream fileInputStream=new FileInputStream("C:\\Users\\Akshay\\eclipse-workspace\\ComcastCRMGUIFramework\\MockTestData\\Mock Test Data.xlsx");
	Workbook wb=WorkbookFactory.create(fileInputStream);
	Sheet sh=wb.getSheet("Monitors");

	
	for (int i = 0; i < monitorList.size(); i++) {
		Row row2=sh.getRow(i+1);
		 if (row2 == null) {
		        row2 = sh.createRow(i + 1);
		    }
		row2.createCell(0).setCellValue(monitorList.get(i).getText());
//		row2.createCell(1).setCellValue(prices.get(i).getText());
	}
	

	for (int i = 0; i < pricelist.size(); i++) {
		Row row2=sh.getRow(i+1);
		 if (row2 == null) {
		        row2 = sh.createRow(i + 1);
		    }
		row2.createCell(1).setCellValue(pricelist.get(i).getText());

	}
	
	FileOutputStream fileOutputStream=new FileOutputStream("C:\\Users\\Akshay\\eclipse-workspace\\ComcastCRMGUIFramework\\MockTestData\\Mock Test Data.xlsx");
	wb.write(fileOutputStream);
	wb.close();
	
	//navigate to home
	driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
	//navigate to contact
	driver.findElement(By.xpath("//a[contains(text(),'Contact')]")).click();
	
	FileInputStream fis2=new FileInputStream("C:\\Users\\Akshay\\eclipse-workspace\\ComcastCRMGUIFramework\\MockTestData\\PropertiesDemoblaze.properties");
	Properties pobj=new Properties();
	pobj.load(fis2);
	String email=pobj.getProperty("email");
	String name=pobj.getProperty("name");
	
	driver.findElement(By.id("recipient-email")).sendKeys(email);
	driver.findElement(By.id("recipient-name")).sendKeys(name);
	driver.findElement(By.id("message-text")).sendKeys("Hi");
	driver.findElement(By.xpath("//button[contains(text(),'Send message')]")).click();
	
	wlib.switchtoAlertandAccept(driver);
	
	driver.quit();
	
	
}






@Test
public void phones() throws IOException, InterruptedException {
	WebDriver driver = new ChromeDriver();
	wlib.waitForPageLoad(driver);
	driver.manage().window().maximize();
	driver.get("https://www.demoblaze.com/");
	// click on phones
	driver.findElement(By.linkText("Phones")).click();
	WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(15));

	// click on 2nd phone
	driver.findElement(By.xpath("(//a[@class='hrefch'])[position()=2]")).click();
	
	//add to cart
	driver.findElement(By.linkText("Add to cart")).click();
	Thread.sleep(2000);
	wlib.switchtoAlertandAccept(driver);
	
	//navigate to cart
	driver.findElement(By.linkText("Cart")).click();
	
	//take scareenshot
	TakesScreenshot ts=(TakesScreenshot) driver;
	File src=ts.getScreenshotAs(OutputType.FILE);
	File dest=new File("./MockScreenshots/Cart.png");
	FileHandler.copy(src, dest);
	
	//place order
	driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
	
	//enter details
	String name=elib.getDataFromExcel("Phones", 1, 0);
	String country=elib.getDataFromExcel("Phones", 1, 1);
	String city=elib.getDataFromExcel("Phones", 1, 2);
	String creditcard=elib.getDataFromExcel("Phones", 1, 3);
	String month=elib.getDataFromExcel("Phones", 1, 4);
	String year=elib.getDataFromExcel("Phones", 1, 5);
	
	driver.findElement(By.id("name")).sendKeys(name);
	driver.findElement(By.id("country")).sendKeys(country);
	driver.findElement(By.id("city")).sendKeys(city);
	driver.findElement(By.id("card")).sendKeys(creditcard);
	driver.findElement(By.id("month")).sendKeys(month);
	driver.findElement(By.id("year")).sendKeys(year);
	
	//click on purchase
	driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
	
	//verify
	String verificationinfo=driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]")).getText();
	Assert.assertEquals(verificationinfo, "Thank you for your purchase!");
	
	TakesScreenshot ts1=(TakesScreenshot) driver;
	File src1=ts.getScreenshotAs(OutputType.FILE);
	File dest1=new File("./MockScreenshots/Order.png");
	FileHandler.copy(src1, dest1);
	driver.quit();
	
	
	
}



@Test(dataProvider = "getData")
public void laptop(String name, String country,String city, String card, String month, String year) throws InterruptedException {
	WebDriver driver = new ChromeDriver();
	wlib.waitForPageLoad(driver);
	driver.manage().window().maximize();
	driver.get("https://www.demoblaze.com/");
	// click on laptop
	driver.findElement(By.linkText("Laptops")).click();

	// click on 3rd laptop
	driver.findElement(By.xpath("(//a[@class='hrefch'])[position()=3]")).click();

	// add to cart
	driver.findElement(By.linkText("Add to cart")).click();
	Thread.sleep(2000);
	wlib.switchtoAlertandAccept(driver);
	
	//navigate to cart
	driver.findElement(By.linkText("Cart")).click();
	
	//place order
	driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
	
	//enter details
	driver.findElement(By.id("name")).sendKeys(name);
	driver.findElement(By.id("country")).sendKeys(country);
	driver.findElement(By.id("city")).sendKeys(city);
	driver.findElement(By.id("card")).sendKeys(card);
	driver.findElement(By.id("month")).sendKeys(month);
	driver.findElement(By.id("year")).sendKeys(year);
	
	// click on purchase
	driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();

	// verify
	String verificationinfo = driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]")).getText();
	Assert.assertEquals(verificationinfo, "Thank you for your purchase!");
		
	driver.quit();
	
}
@DataProvider
public Object[][] getData() throws EncryptedDocumentException, IOException {
	ExcelUtility elib=new ExcelUtility();
	int rowcount=elib.getRowCount("Phones");
	System.out.println(rowcount);
	Object [][] objArr=new Object[rowcount][6];
	try {
		for (int i = 0; i < rowcount; i++) {
			objArr[i][0] = elib.getDataFromExcel("Phones", i+1, 0);
			objArr[i][1] = elib.getDataFromExcel("Phones", i+1, 1);
			objArr[i][2] = elib.getDataFromExcel("Phones", i+1, 2);
			objArr[i][3] = elib.getDataFromExcel("Phones", i+1, 3);
			objArr[i][4] = elib.getDataFromExcel("Phones", i+1, 4);
			objArr[i][5] = elib.getDataFromExcel("Phones", i+1, 5);
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	return objArr;
}
}
package com.comcast.crm.listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListImpClass implements ITestListener,ISuiteListener{
	public ExtentReports report;
//	public static ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		//config
		System.out.println("Report Config");
		String time=new Date().toString().replace(" ","_").replace(":","_");
		ExtentSparkReporter spark=new ExtentSparkReporter("./Report/report"+"_"+time+".html");
		spark.config().setDocumentTitle("CRMTest Suite Reports");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//env info and create test
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS","Windows-10");
		report.setSystemInfo("Browser", "Chrome-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report backup");
		report.flush();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("=====>"+result.getMethod().getMethodName()+"===START===");
		ExtentTest test=report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		UtilityClassObject.getTest().log(Status.INFO,result.getMethod().getMethodName()+"===STARTED===");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("======="+result.getMethod().getMethodName()+"===END===");
		UtilityClassObject.getTest().log(Status.PASS,result.getMethod().getMethodName()+"===COMPLETED===");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		TakesScreenshot ts=(TakesScreenshot)UtilityClassObject.getDriver();
		String filepath=ts.getScreenshotAs(OutputType.BASE64);
		String time=new Date().toString().replace(" ","_").replace(":","_");
		UtilityClassObject.getTest().addScreenCaptureFromBase64String(filepath, result.getMethod().getMethodName()+time);
		UtilityClassObject.getTest().log(Status.FAIL,result.getMethod().getMethodName()+time+"===FAILED===" );		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}

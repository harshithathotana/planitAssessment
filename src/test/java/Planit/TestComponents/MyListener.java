package Planit.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Planit.Resources.ExtentManager;
import Planit.Resources.ExtentReport;


public class MyListener extends BaseTest implements ITestListener, ISuiteListener {


	@Override
	public void onStart(ISuite suite) {
		ExtentReport.initReports();
	}

	
	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushReports();

	}

	
	@Override
	public void onTestStart(ITestResult result) {

		ExtentReport.createTest(result.getMethod().getMethodName());
	}


	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentManager.getExtentTest().pass(result.getMethod().getMethodName() + " is passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentManager.getExtentTest().fail(result.getMethod().getMethodName() + " is failed");
//			result.getMethod().getMethodName();
//			result.getThrowable().toString();
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentManager.getExtentTest().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	}


	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentManager.getExtentTest().skip(result.getMethod().getMethodName() + " is skipped");
		// ExtentLogger.skip(result.getMethod().getMethodName() +" is skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
	
	}

	@Override
	public void onFinish(ITestContext context) {

	}

}

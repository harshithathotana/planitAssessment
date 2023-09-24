package Planit.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
//import com.tmb.annotations.FrameworkAnnotation;
//import com.tmb.reports.ExtentReport;
//import com.tmb.utils.ELKUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Planit.Resources.ExtentManager;
import Planit.Resources.ExtentReport;

/**
 * Implements {@link org.testng.ITestListener} and
 * {@link org.testng.ISuiteListener} to leverage the abstract methods Mostly
 * used to help in extent report generation
 * 
 * <pre>
 * Please make sure to add the listener details in the testng.xml file
 * </pre>
 * 
 */
public class MyListener extends BaseTest implements ITestListener, ISuiteListener {

	/**
	 * Initialise the reports with the file name
	 * 
	 * @see com.tmb.reports.ExtentReport
	 */
	@Override
	public void onStart(ISuite suite) {
		ExtentReport.initReports();
	}

	/**
	 * Terminate the reports
	 * 
	 * @see com.tmb.reports.ExtentReport
	 */
	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushReports();

	}

	/**
	 * Starts a test node for each testng test
	 * 
	 * @see com.tmb.reports.ExtentReport
	 * @see com.tmb.annotations.FrameworkAnnotation
	 */
	@Override
	public void onTestStart(ITestResult result) {

		ExtentReport.createTest(result.getMethod().getMethodName());
//		ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class)
//			.author());
//		ExtentReport.addCategories(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class)
//			.category());
	}

	/**
	 * Marks the test as pass and logs it in the report
	 * 
	 * @see com.tmb.reports.FrameworkLogger
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentManager.getExtentTest().pass(result.getMethod().getMethodName() + " is passed");
//		ExtentLogger.pass(result.getMethod().getMethodName() +" is passed");
//		log(PASS,result.getMethod().getMethodName() +" is passed");
//		ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "pass");
	}

	/**
	 * Marks the test as fail,append base64 screenshot and logs it in the report
	 * 
	 * @see com.tmb.reports.FrameworkLogger
	 * @see com.tmb.utils.ScreenshotUtils
	 */
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
//			log(FAIL,Arrays.toString(result.getThrowable().getStackTrace()));
//			ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "fail");
	}

	/**
	 * Marks the test as skip and logs it in the report
	 * 
	 * @see com.tmb.reports.FrameworkLogger
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentManager.getExtentTest().skip(result.getMethod().getMethodName() + " is skipped");
		// ExtentLogger.skip(result.getMethod().getMethodName() +" is skipped");
//		log(SKIP,result.getMethod().getMethodName() +" is skipped");
//		ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "skip");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		/*
		 * We are having just one test in our suite. So we dont have any special
		 * implementation as of now
		 */
	}

	@Override
	public void onFinish(ITestContext context) {
		/*
		 * We are having just one test in our suite. So we dont have any special
		 * implementation as of now
		 */

	}

}

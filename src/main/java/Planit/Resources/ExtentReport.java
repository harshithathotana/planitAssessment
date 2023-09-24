package Planit.Resources;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class ExtentReport {

	// Private constructor to avoid external instantiation
	private ExtentReport() {
	}

	private static ExtentReports extent;

	// To set the initial configuration for the Extent Reports and report generation path
	public static void initReports() {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(
					System.getProperty("user.dir") + "//reports//TestReport.html");
			extent.attachReporter(spark);
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setReportName(" Jupitor Toys Automation Results");
			spark.config().setDocumentTitle("Planit Test Assessment Results");
			extent.setSystemInfo("Tester", "Harshitha");
		}
	}

//	 Flushing the reports ensures extent logs are reflected properly. Opens the report in the default desktop browser. Sets the ThreadLocal variable to default value
	public static void flushReports() {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}
		ExtentManager.unload();
		try {
			Desktop.getDesktop()
					.browse(new File(System.getProperty("user.dir") + "//reports//TestReport.html").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// To Create a test node in the extent report and test case name is added as the test name in the report
	public static void createTest(String testcasename) {
		ExtentManager.setExtentTest(extent.createTest(testcasename));
	}

}

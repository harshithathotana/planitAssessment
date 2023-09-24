package Planit.Resources;

import java.util.Objects;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	// Private constructor to avoid external instantiation
	
	private ExtentManager() {
	}

	private static ThreadLocal<ExtentTest> extTest = new ThreadLocal<>();

	public static ExtentTest getExtentTest() {
		return extTest.get();
	}

	public static void setExtentTest(ExtentTest test) {
		if (Objects.nonNull(test)) {
			extTest.set(test);
		}
	}

	static void unload() {
		extTest.remove();
	}
}

package com.ebay.utils.listeners;

import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ebay.commonfunctions.BaseTest;
import com.ebay.utils.ExtentManager;
import com.ebay.utils.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestListener extends BaseTest implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// Before starting all tests, below method runs.
	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("onStart method " + iTestContext.getName());
		iTestContext.setAttribute("AndroidDriver", this.driver);
	}

	// After ending all tests, below method runs.
	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("onFinish method " + iTestContext.getName());
		// Do tier down operations for extentreports reporting!
		ExtentTestManager.endTest();
		ExtentManager.getReporter().flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("onTestStart method " + getTestMethodName(iTestResult) + " start");
		// Start operation for extentreports.
		ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), "");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
		// Get driver from BaseTest and assign to local webdriver variable.
		Object testClass = iTestResult.getInstance();
		AndroidDriver<AndroidElement> androidDriver = ((com.ebay.Testcases.ProductInfo_TCs) testClass).driver;

		// Take base64Screenshot screenshot.
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) androidDriver).getScreenshotAs(org.openqa.selenium.OutputType.BASE64);
//		ExtentTestManager.getTest().
		// Extentreports log operation for passed tests.
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed",
				ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("onTestFailure method " + getTestMethodName(iTestResult) + " failed");

		// Get driver from BaseTest and assign to local webdriver variable.
		Object testClass = iTestResult.getInstance();
		AndroidDriver<AndroidElement> androidDriver = ((com.ebay.Testcases.ProductInfo_TCs) testClass).driver;

		// Take base64Screenshot screenshot.
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) androidDriver).getScreenshotAs(org.openqa.selenium.OutputType.BASE64);

		// Extentreports log and screenshot operations for failed tests.
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
				ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
		// Extentreports log operation for skipped tests.
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

}

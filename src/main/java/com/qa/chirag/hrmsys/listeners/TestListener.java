package com.qa.chirag.hrmsys.listeners;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.chirag.hrmsys.base.BasePage;
import com.qa.chirag.hrmsys.utils.ExtentReport;

public class TestListener implements ITestListener {
	
	ExtentReport extentReport;
	public ExtentReports extent;
	public ExtentTest logger;
	private static Logger log = LogManager.getLogger(TestListener.class);

	public void onTestStart(ITestResult result) {
		log.info(result.getName() + " Test started");
	}

	public void onTestSuccess(ITestResult result) {
		logger = extent.createTest(result.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		log.info(result.getName() + " Test passed");
	}

	public void onTestFailure(ITestResult result) {
		String file = BasePage.takeScreenshot();
		log.info(result.getName() + " Test failed");
		logger = extent.createTest(result.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		try {
			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder
					.createScreenCaptureFromPath(System.getProperty("user.dir") + "/Screenshots/" + file).build());
		} catch (IOException e) {
			log.error("Error in extent reporting + " + e.getMessage());
		}
	}

	public void onTestSkipped(ITestResult result) {
		logger = extent.createTest(result.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
		log.warn(result.getName() + " Test skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		String file = BasePage.takeScreenshot();
		log.info(result.getName() + " Test failed with TimeOut. Screenshot file name: " + file);
		logger = extent.createTest(result.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		try {
			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder
					.createScreenCaptureFromPath(System.getProperty("user.dir") + "/Screenshots/" + file).build());
		} catch (IOException e) {
			log.error("Error in extent reporting + " + e.getMessage());
		}
	}

	public void onStart(ITestContext context) {
		extentReport = new ExtentReport();
		extent = extentReport.initReport();
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}

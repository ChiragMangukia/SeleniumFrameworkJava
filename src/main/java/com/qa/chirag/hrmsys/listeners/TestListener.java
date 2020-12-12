package com.qa.chirag.hrmsys.listeners;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	
	private static Logger log = LogManager.getLogger(TestListener.class);

	public void onTestStart(ITestResult result) {
		log.info(result.getName() + " Test started");
	}

	public void onTestSuccess(ITestResult result) {
		log.info(result.getName() + " Test passed");
	}

	public void onTestFailure(ITestResult result) {
		log.info(result.getName() + " Test failed");
	}

	public void onTestSkipped(ITestResult result) {
		log.info(result.getName() + " Test skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		log.info(result.getName() + " Test failed with Timeout");
	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

	}

}

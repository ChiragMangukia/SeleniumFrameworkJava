package com.qa.chirag.hrmsys.base;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.chirag.hrmsys.utils.Utilities;

public class BaseTest {
	
	public BasePage basePage;
	
	private Properties prop;
	protected WebDriver driver;
	
	private static Logger log = LogManager.getLogger(BaseTest.class);
	
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = Utilities.getProp();
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		driver = basePage.initDriver(browser);
		driver.get(url);
		log.info("URL opened: " + url);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}

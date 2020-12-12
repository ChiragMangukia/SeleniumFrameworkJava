package com.qa.chirag.hrmsys.base;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.google.common.io.Files;
import com.qa.chirag.hrmsys.utils.Browser;
import com.qa.chirag.hrmsys.utils.OptionsManager;
import com.qa.chirag.hrmsys.utils.TestUtils;
import com.qa.chirag.hrmsys.utils.Utilities;

public class BasePage {
	
	private static WebDriver driver = null;
	private Properties prop;
	private OptionsManager optionsManager;
	
	private static Logger log = LogManager.getLogger(BasePage.class);
	
	protected WebDriver initDriver(String browser) {
		if(driver == null) {
			prop = Utilities.getProp();
			optionsManager = new OptionsManager(prop);
			if(browser.equalsIgnoreCase(Browser.CHROME.name())) {
				System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
				driver = new ChromeDriver(optionsManager.getChromeOptions());
				log.info("Chrome driver initialized");
			} else if(browser.equalsIgnoreCase(Browser.FIREFOX.name())) {
				System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
				driver = new ChromeDriver(optionsManager.getChromeOptions());
				log.info("Firefox driver initialized");
			} else if (browser.equalsIgnoreCase(Browser.EDGE.name())) {
				System.setProperty("webdriver.edge.driver", "./Drivers/msedgedriver.exe");
				driver = new EdgeDriver();
				log.info("Edge driver initialized");
			} else {
				log.error("No valid driver name found");
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		}
		return driver;
	}
	
	protected void closeCon() {
		driver.quit();
		driver = null;
		log.info("Driver quit");
	}
	
	public static String takeScreenshot() {
		String fileName = null;
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			fileName = System.currentTimeMillis() + ".png";
			String path = "./Screenshots/" + fileName;
			File destination = new File(path);
			Files.copy(src, destination);
		} catch (Exception e) {
			log.error(e.getStackTrace());
		}
		return fileName;
	}

}

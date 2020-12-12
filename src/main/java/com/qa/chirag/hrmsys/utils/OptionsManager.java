package com.qa.chirag.hrmsys.utils;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	Properties prop;
	ChromeOptions chrome;
	FirefoxOptions firefox;
	
	private static Logger log = LogManager.getLogger(OptionsManager.class);
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		chrome = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			chrome.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			chrome.addArguments("--incognito");
		log.info("ChromeOptions capabilities set");
		return chrome;
	}

	public FirefoxOptions getFirefoxOptions() {
		firefox = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			firefox.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			firefox.addArguments("--incognito");
		log.info("FirefoxOptions capabilities set");
		return firefox;
	}

}

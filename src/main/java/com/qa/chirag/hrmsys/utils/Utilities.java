package com.qa.chirag.hrmsys.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Utilities {
	
	private static Logger log = LogManager.getLogger(Utilities.class);
	
	public static Properties getProp() {
		Properties prop = new Properties();
		String path = "./src/main/resources/config.properties";

		try {
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			log.error(e.getStackTrace());
		} catch (IOException e) {
			log.error(e.getStackTrace());
		}
		return prop;
	}

}

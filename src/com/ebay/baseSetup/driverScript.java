package com.ebay.baseSetup;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class driverScript {
	private static AndroidDriver<AndroidElement> androidDriver = null;
	private static Properties prop = null;
	private final String propFilePath = "Config.properties";

	private void loadPropertyFile() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propFilePath));
			prop = new Properties();
			try {
				prop.load(reader);
				reader.close();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Properties file is not found at " + propFilePath);
		}
	}

	@Test
	public Properties getPropertiesObj() {
		return prop;
	}

	@Test
	public AndroidDriver<AndroidElement> getDriver() {
		if ((androidDriver == null)) {
			loadPropertyFile();
			initDriver();
		}

		return androidDriver;
	}

	private void initDriver() {
		System.out.println("Inside initDriver method");

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", prop.getProperty("deviceName"));
		cap.setCapability("udid", prop.getProperty("udid"));
		cap.setCapability("platformName", prop.getProperty("platformName"));
		cap.setCapability("platformVersion", prop.getProperty("platformVersion"));
		cap.setCapability("app", System.getProperty("user.dir") + prop.getProperty("app"));
		cap.setCapability("noReset", prop.getProperty("noReset"));
		try {
			androidDriver = new AndroidDriver<AndroidElement>(new URL(prop.getProperty("AppiumURL")), cap);
			androidDriver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("implicitlyWait")),
					TimeUnit.SECONDS);

		} catch (NullPointerException | MalformedURLException ex) {
			throw new RuntimeException("appium driver could not be initialised for device ");

		}

	}

}

package com.ebay.commonfunctions;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ebay.baseSetup.driverScript;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
//import io.appium.java_client.android.WebElement;
import io.appium.java_client.touch.offset.PointOption;

public class BaseTest {
	public AndroidDriver<AndroidElement> driver;
	public Properties properties;
	public String filepath = null;
	public FileInputStream file = null;
	public XSSFWorkbook wb;
	WebDriverWait wait = null;

	public BaseTest() {
		driverScript driverScriptObj = new driverScript();
		this.driver = driverScriptObj.getDriver();
		this.properties = driverScriptObj.getPropertiesObj();
		wait = new WebDriverWait(driver, 30);
	}

	public void click(WebElement e) {
		wait.until(ExpectedConditions.elementToBeClickable(e));
		e.click();
		System.out.println( "clicked");
	}

	public void clearAndType(WebElement e, String value) {
		wait.until(ExpectedConditions.visibilityOf(e));
		e.clear();
		e.sendKeys(value);
		System.out.println(" Type");
	}

	public void Type(WebElement e, String value) {
		wait.until(ExpectedConditions.visibilityOf(e));
		e.sendKeys(value);
	}

	public String getText(WebElement e) {
		wait.until(ExpectedConditions.visibilityOf(e));
		e.getText();

		return e.getText();

	}

	public String getData(String sheetname, int row, int col) {

		filepath = System.getProperty("user.dir") + properties.getProperty("TestDataFile").trim();
		if (file == null) {
			try {
				file = new FileInputStream(filepath);
				wb = new XSSFWorkbook(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		XSSFSheet sh = wb.getSheet(sheetname);
		String value = sh.getRow(row).getCell(col).getStringCellValue();
		return value;

	}

	public void swipeDown() {
		Dimension dim = driver.manage().window().getSize();
		System.out.println(dim.getWidth());
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width / 2;
		int top_y = (int) (height * 0.85);
		int bottom_y = (int) (height * 0.40);
		new io.appium.java_client.TouchAction(driver).longPress(PointOption.point(x, top_y))
				.moveTo(PointOption.point(x, bottom_y)).release().perform();

	}
	
	public void swipeUp() {
		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width / 2;
		int top_y = (int) (height * 0.45);
		int bottom_y = (int) (height * 0.80);
		new io.appium.java_client.TouchAction(driver).longPress(PointOption.point(x, top_y))
				.moveTo(PointOption.point(x, bottom_y)).release().perform();

	}

	public void sleepThread() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	public void verifyPage(WebElement e) {
		System.out.println("inside verify page");
		wait.until(ExpectedConditions.visibilityOf(e));
		assertTrue(e.isDisplayed());
		System.out.println("after verify page");
	}
}

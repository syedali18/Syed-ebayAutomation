package com.ebay.Testcases;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ebay.baseSetup.driverScript;
import com.ebay.pages.HomePage;
import com.ebay.pages.ProductInfoPage;
import com.ebay.pages.SearchPage;
import com.ebay.pages.SearchResultPage;
import com.ebay.pages.SignInPage;
import com.ebay.pages.WelcomePage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ProductInfo_TCs extends driverScript {
	public AndroidDriver<AndroidElement> driver;
	public Properties properties;
	XSSFWorkbook wb = null;
	XSSFSheet sh1 = null;
	int numrow;
	String username;
	String password;

	@BeforeTest
	public void Driver() {
		this.driver = super.getDriver();
		this.properties = super.getPropertiesObj();
	}

	@Test(description = "To verify Product Prize", dataProvider = "testdata")
	public void TS_verifyProductPrize(HashMap<String, String> hashMapValue) throws Exception {
		System.out.println("inside verifyPrize");
//		HomePage homePage = new HomePage();
//		homePage.verifyHomePage();
//		homePage.clickOnMenuButton();
//		homePage.clickOnSignInTab();
//		// SignIn Page
//		SignInPage signinpage = new SignInPage();
//		signinpage.clickUseEmailbutton();
//		username = hashMapValue.get("0");
//		password = hashMapValue.get("1");
//		System.out.println(username + password);
//		signinpage.enterSignInDetails(username, password);
//
//		WelcomePage welcomePage = new WelcomePage();
//		welcomePage.verifyWelcomePage();
//		welcomePage.clickOnMaybeLaterButton();
 
		SearchPage searchPage = new SearchPage();
		searchPage.verifySearchPage();
		String searchText = hashMapValue.get("2");
		searchPage.searchItem(searchText);

		SearchResultPage searchresultpage = new SearchResultPage();
		searchresultpage.verifySearchResultPage();
		String productname = hashMapValue.get("3");
		String productPrize = searchresultpage.selectProduct(productname);

		ProductInfoPage ProductInfoPage = new ProductInfoPage();
		ProductInfoPage.verifyProductInfoPage();
		ProductInfoPage.verifyProductInfo(productPrize,productname);

		// SearchPage
	}

	@DataProvider(name = "testdata")
	public Object[][] TestDataFeed() { 

		try {
			String filepath = System.getProperty("user.dir") + properties.getProperty("TestDataFile").trim();
			System.out.println(filepath);
			// load file
			FileInputStream fis = new FileInputStream(filepath);
			// Load workbook
			wb = new XSSFWorkbook(fis);
			// Load sheet- Here we are loading first sheetonly
			sh1 = wb.getSheet("TestDataSheet");
			// get number of rows so that we can run loop based on this
			numrow = sh1.getLastRowNum();
			System.out.println(numrow + " " + sh1);
		} catch (Exception e)

		{
			e.printStackTrace();
		}

		Row r = sh1.getRow(1);
		int col = r.getPhysicalNumberOfCells();
		System.out.println(col);
		List<HashMap<String, String>> arrayMapList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMapItems = new HashMap<String, String>();

		for (int i = 0; i < numrow; i++) {
			for (int j = 0; j < col; j++) {
				hashMapItems.put(j + "", sh1.getRow(i + 1).getCell(j).getStringCellValue());
			}
			arrayMapList.add(hashMapItems);
			hashMapItems = new HashMap<String, String>();
		}

		Object[][] hashMapObj = new Object[arrayMapList.size()][1];

		for (int i = 0; i < arrayMapList.size(); i++) {
			hashMapObj[i][0] = arrayMapList.get(i);
		}

		return hashMapObj;
	}

	@AfterTest
	public void quitDriver() {
		this.driver.quit();
	}
}

package com.ebay.Testcases;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
	Workbook wb;
	Sheet sh1;
	int numrow;
	String username;
	String password;
	@BeforeTest
	public void Driver() {
		this.driver = super.getDriver();
	}

	@Test(description = "To verify Product Prize")
	public void TS_verifyProductPrize() throws Exception {
		System.out.println("inside verifyPrize");
		HomePage homePage = new HomePage();
		homePage.verifyHomePage();
		homePage.clickOnMenuButton();
		homePage.clickOnSignInTab();
		// SignIn Page
		SignInPage signinpage = new SignInPage();
		signinpage.clickUseEmailbutton();
		signinpage.enterSignInDetails();

		WelcomePage welcomePage = new WelcomePage();
		welcomePage.verifyWelcomePage();
		welcomePage.clickOnMaybeLaterButton();

		SearchPage searchPage = new SearchPage();
		searchPage.verifySearchPage();
		searchPage.searchItem();

		SearchResultPage searchresultpage = new SearchResultPage();
		searchresultpage.verifySearchResultPage();
		String productPrize = searchresultpage.selectProduct();

		ProductInfoPage ProductInfoPage = new ProductInfoPage();
		ProductInfoPage.verifyProductInfoPage();
		ProductInfoPage.verifyProductInfo(productPrize);

		// SearchPage
	}

	@AfterTest
	public void quitDriver() {
		this.driver.quit();
	}
}

package com.ebay.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ebay.baseSetup.driverScript;
import com.ebay.commonfunctions.BaseTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class HomePage extends BaseTest {

	PageObjects homePage;
	public AndroidDriver<AndroidElement> driver;

	public HomePage() {
		super();
		this.driver = new driverScript().getDriver();
		homePage = new PageObjects();
		System.out.println("in constructor");
		PageFactory.initElements(driver, homePage);
		System.out.println("after constructor");

	}

	public void verifyHomePage() {
		System.out.println("inside verify home page");
		verifyPage(homePage.ebayLogo);

	}

	public void clickOnMenuButton() {
		System.out.println("inside click Menu btn");
		click(homePage.homeIcon);
		verifyPage(homePage.SignIn_tab);

	}

	public void clickOnSignInTab() {
//		swipeUp();
		click(homePage.SignIn_tab);
		verifyPage(homePage.SignIn_page);

	}

	class PageObjects {

		@FindBy(id = "com.ebay.mobile:id/home")
		public WebElement homeIcon;

		@FindBy(id = "com.ebay.mobile:id/logo")
		public WebElement ebayLogo;

		@FindBy(id = "com.ebay.mobile:id/textview_sign_out_status")
		public WebElement SignIn_tab;

		@FindBy(xpath = "//*[contains(@text,'username')]")
		public WebElement SignIn_page;

		@FindBy(id = "com.ebay.mobile:id/menuitem_settings")
		public WebElement SettingsTab;

	}

}

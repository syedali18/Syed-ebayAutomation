package com.ebay.pages;

import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ebay.baseSetup.driverScript;
import com.ebay.commonfunctions.BaseTest;

import io.appium.java_client.android.AndroidDriver;

public class SignInPage extends BaseTest {

	PageObjects signinpage;
	

	public SignInPage() {
		super();
		this.driver = new driverScript().getDriver();
		signinpage = new PageObjects();
		PageFactory.initElements(driver, signinpage);
	}

	public void clickUseEmailbutton() {
		click(signinpage.UseEmail_BTN);
	}

	public void enterSignInDetails() throws Exception {
		clearAndType(signinpage.username_TBX, getData("TestDataSheet", 1, 0));
		clearAndType(signinpage.password_TBX, getData("TestDataSheet", 1, 1));
		click(signinpage.signin_BTN);
	}

	class PageObjects {

		@CacheLookup
		@FindBy(xpath = "//*[contains(@text,'username')]")
		public WebElement username_TBX;

		@CacheLookup
		@FindBy(xpath = "//*[contains(@text,'Password')]")
		public WebElement password_TBX;

		@CacheLookup
		@FindBy(xpath = "//*[contains(@text,'SIGN IN')]")
		public WebElement signin_BTN;

		@CacheLookup
		@FindBy(id = "com.ebay.mobile:id/button_classic")
		public WebElement UseEmail_BTN;

	}

}

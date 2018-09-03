package com.ebay.pages;

import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ebay.baseSetup.driverScript;
import com.ebay.commonfunctions.BaseTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class WelcomePage extends BaseTest {

	PageObjects welcomepage;
	public AndroidDriver<AndroidElement> driver;
	public WelcomePage() {
		super();
		this.driver = new driverScript().getDriver();
		welcomepage = new PageObjects();
		PageFactory.initElements(driver, welcomepage);
	}

	public void verifyWelcomePage() {
		verifyPage(welcomepage.fingerprint_icon);
		
		
	}

	public void clickOnMaybeLaterButton() {
		click(welcomepage.maybelater_TBX);

	}

	class PageObjects {

		@CacheLookup
		@FindBy(id = "com.ebay.mobile:id/fingerprint_icon")
		public WebElement fingerprint_icon;

		@CacheLookup
		@FindBy(id = "com.ebay.mobile:id/bt_maybe_later")
		public WebElement maybelater_TBX;

	}

}

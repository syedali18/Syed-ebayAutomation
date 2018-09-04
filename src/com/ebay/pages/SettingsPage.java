package com.ebay.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ebay.baseSetup.driverScript;
import com.ebay.commonfunctions.BaseTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SettingsPage extends BaseTest {

	PageObjects settingsPage;
	public AndroidDriver<AndroidElement> driver;

	public SettingsPage() {
		super();
		this.driver = new driverScript().getDriver();
		settingsPage = new PageObjects();
		System.out.println("in constructor");
		PageFactory.initElements(driver, settingsPage);
		System.out.println("after constructor");

	}

	public void selectUSregion() {
		click(settingsPage.homeIcon);
		swipeDown();
		click(settingsPage.SettingsMenuItem);
		String selectedRegion = settingsPage.selectedRegion_TXT.getText();
		if (!(selectedRegion.equalsIgnoreCase("United States"))) {
			click(settingsPage.countryRegion_BTN);
			click(settingsPage.autoDetect_BTN);
			click(settingsPage.countryRegion_BTN);
			clearAndType(settingsPage.searchRegion_TBX, "United States");
			click(settingsPage.US_TXT);

		}
		selectedRegion = settingsPage.selectedRegion_TXT.getText();
		if ((selectedRegion.equalsIgnoreCase("United States"))) {
			click(settingsPage.back_BT);
			click(settingsPage.back_BT);
			
		}
		

	}

	class PageObjects {

		@FindBy(id = "com.ebay.mobile:id/menuitem_settings")
		public WebElement SettingsMenuItem;

		@FindBy(id = "Country / region button")
		public WebElement countryRegion_BTN;

		@FindBy(xpath = "//android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[2]")
		public WebElement selectedRegion_TXT;

		@FindBy(id = "android:id/switch_widget")
		public WebElement autoDetect_BTN;

		@FindBy(id = "com.ebay.mobile:id/filter")
		public WebElement searchRegion_TBX;

		@FindBy(xpath = "//android.widget.CheckedTextView[@content-desc=\"EBAY-US\"]")
		public WebElement US_TXT;

		@FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
		public WebElement back_BT;

		@FindBy(id = "com.ebay.mobile:id/textview_sign_in_status")
		public WebElement sign_in_status_TXT;

		@FindBy(id = "com.ebay.mobile:id/menuitem_sign_out")
		public WebElement signout_menuItem;

		@FindBy(id = "android:id/button1")
		public WebElement alertOK_BTN;
		
		@FindBy(id = "com.ebay.mobile:id/home")
		public WebElement homeIcon;
		// android:id/button1

	}

}

package com.ebay.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ebay.baseSetup.driverScript;
import com.ebay.commonfunctions.BaseTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SearchPage extends BaseTest {

	PageObjects searchpage;
	public AndroidDriver<AndroidElement> driver;

	public SearchPage() {
		super();
		this.driver = new driverScript().getDriver();
		searchpage = new PageObjects();
		PageFactory.initElements(driver, searchpage);
	}
 
	public void searchItem(String searchVal) {
		sleepThread();
		String searchText = searchVal;
		click(searchpage.search_TBX);
		clearAndType(searchpage.search_TXT, searchText);
		click(searchpage.search_item);
//		click(searchpage.search_msg);

	} 
	
	public void verifySearchPage() {
		verifyPage(searchpage.search_TBX);

	}

	class PageObjects {

		@CacheLookup
		@FindBy(id = "com.ebay.mobile:id/search_box")
		public WebElement search_TBX;

		@CacheLookup
		@FindBy(id = "com.ebay.mobile:id/search_src_text")
		public WebElement search_TXT;

		@CacheLookup
		@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.TextView")
		public WebElement search_item;

		@CacheLookup
		@FindBy(id = "com.ebay.mobile:id/textview_header_title")
		public WebElement headerTitle;

		@CacheLookup
		@FindBy(id = "com.ebay.mobile:id/text_slot_1")
		public WebElement search_msg;
		
		
		

	}

}

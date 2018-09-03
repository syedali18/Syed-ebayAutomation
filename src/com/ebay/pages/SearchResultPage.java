package com.ebay.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ebay.baseSetup.driverScript;
import com.ebay.commonfunctions.BaseTest;

public class SearchResultPage extends BaseTest {

	PageObjects searchresultpage;

	public SearchResultPage() {
		super();
		this.driver = new driverScript().getDriver();
		searchresultpage = new PageObjects();
		PageFactory.initElements(driver, searchresultpage);
	}

	public void verifySearchResultPage() {
		verifyPage(searchresultpage.SearchResult_headerTitle);

	}

	public String selectProduct() {
		String ExpProductName = getData("TestDataSheet", 1, 3).trim();
		List<WebElement> productsNames = searchresultpage.productsName;
		List<WebElement> productsPrices = searchresultpage.productsPrice;
		String productsPriceInList = "";
		swipeDown();

		System.out.println(ExpProductName + "ProductName");
		
		boolean isClicked = true;
		while (isClicked) {
			int i = 0;
			for (WebElement e : productsNames) {
				System.out.println(e.getText().trim() + "productsNames");
				System.out.println(ExpProductName + "ExpProductName");
				if (e.getText().trim().contains(ExpProductName)) {
					System.out.println("before click");
					productsPriceInList = productsPrices.get(i).getText().trim();
					System.out.println("before click");
					click(e);
					isClicked = false;
					break;
				}
				i++;
				
			}
			swipeDown();
			productsNames = searchresultpage.productsName;
			productsPrices = searchresultpage.productsPrice;
		}

		return productsPriceInList;
	}

	class PageObjects {

		@FindBy(xpath = "//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView[@resource-id='com.ebay.mobile:id/textview_item_title']")
		public List<WebElement> productsName;

		@CacheLookup
		@FindBy(xpath = "//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.TextView[@resource-id='com.ebay.mobile:id/textview_item_price']")
		public List<WebElement> productsPrice;

		@CacheLookup
		@FindBy(id = "com.ebay.mobile:id/textview_header_title")
		public WebElement SearchResult_headerTitle;

	}

}

package com.ebay.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ebay.baseSetup.driverScript;
import com.ebay.commonfunctions.BaseTest;

public class ProductInfoPage extends BaseTest {
	PageObjects productinfopage;

	public ProductInfoPage() {
		super();
		this.driver = new driverScript().getDriver();
		productinfopage = new PageObjects();
		PageFactory.initElements(driver, productinfopage);
	}
	
	public void verifyProductInfoPage() {
		verifyPage(productinfopage.productsName); 

	}
	
	public void verifyProductInfo(String productPrizeInList,String ExpectedProductName) {
		String ExpProductName = ExpectedProductName;
		String productName = getText(productinfopage.productsName);
		String productPrize = getText(productinfopage.productPrize);
		System.out.println(productName);
		System.out.println(productPrize);

		Boolean verifyInfo = (productName.contains(ExpProductName) && (productPrize.equals(productPrizeInList)));
		assertTrue(verifyInfo, "Verify the Product Name and product prize");

	}

	class PageObjects {
		@CacheLookup
		@FindBy(id = "com.ebay.mobile:id/textview_item_price")
		public WebElement productPrize;

		@CacheLookup
		@FindBy(id = "com.ebay.mobile:id/textview_item_name")
		public WebElement productsName;

	}

}

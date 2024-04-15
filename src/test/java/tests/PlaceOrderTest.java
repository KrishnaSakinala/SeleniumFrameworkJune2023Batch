package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;
import pages.CheckoutPage;
import pages.HomePage;

public class PlaceOrderTest extends BaseTest {

	@Test
	public void placeOrderUsingDBT() throws InterruptedException {
		extentTest = extentReports.createTest("placeOrderUsingDBT", "Place order using Direct Bank Transfer");
		HomePage homePage = new HomePage(driver);
		homePage.searchBook()
				.selectSearchedBook()
				.navigateBasketPage()
				.proceedToCheckoutPage()
				.placeOrderUsingDirectBankTransfer();	
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		String successMessage = checkoutPage.getSuccessMessage();
		Assert.assertEquals(successMessage, "Thank you. Your order has been received.");
	}
	
	@Test
	public void placeOrderUsingCOD() throws InterruptedException {
		extentTest = extentReports.createTest("placeOrderUsingCOD", "Place order using Cash On Delivery");
		HomePage homePage = new HomePage(driver);
		homePage.searchBook()
				.selectSearchedBook()
				.navigateBasketPage()
				.proceedToCheckoutPage()
				.placeOrderUsingCashOnDelivery();	
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		String successMessage = checkoutPage.getSuccessMessage();
		Assert.assertEquals(successMessage, "Thank you. Your order has been received.");
	}
}

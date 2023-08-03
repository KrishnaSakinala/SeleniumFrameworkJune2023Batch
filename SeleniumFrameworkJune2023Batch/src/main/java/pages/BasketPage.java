package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class BasketPage extends BasePage {

	//WebDriver driver;

	// Locators
	private By proceedToCheckoutButton = By.cssSelector(".wc-proceed-to-checkout >a");

	public BasketPage(WebDriver driver) {
		super(driver);
	}

	// functions
	public CheckoutPage proceedToCheckoutPage() throws InterruptedException {
		wait(5);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		//driver.findElement(proceedToCheckoutButton).click();
		click(proceedToCheckoutButton);
		return new CheckoutPage(driver);
	}

}

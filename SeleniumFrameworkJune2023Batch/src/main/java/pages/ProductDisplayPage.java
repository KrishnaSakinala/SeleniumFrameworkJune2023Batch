package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDisplayPage extends BasePage {
	
	//WebDriver driver;
	
	// Locators
	private By addToBasketButton = By.xpath("//button[@type='submit']");
	private By viewBasketButton = By.cssSelector(".woocommerce-message >a");

	public ProductDisplayPage(WebDriver driver) {
		super(driver);
	}

	// functions
	public BasketPage navigateBasketPage() throws InterruptedException {
		//driver.findElement(addToBasketButton).click();
		click(addToBasketButton);
		wait(3);
		//driver.findElement(viewBasketButton).click();
		click(viewBasketButton);
		return new BasketPage(driver);
	}

}

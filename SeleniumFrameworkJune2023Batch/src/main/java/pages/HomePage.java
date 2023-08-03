package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
	
	//public WebDriver driver;
	
	// Locators
	public By searchTextbox = By.id("s");

	public HomePage(WebDriver driver) {
		super(driver); // calling base class constructor
	}

	// Functions
	public SearchResultsPage searchBook() {
		type(searchTextbox,"Selenium");
		driver.findElement(searchTextbox).sendKeys(Keys.ENTER);
		return new SearchResultsPage(driver);
	}
}

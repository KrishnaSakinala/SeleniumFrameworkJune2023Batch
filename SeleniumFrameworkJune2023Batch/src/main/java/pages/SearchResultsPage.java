package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends BasePage {
	
	//WebDriver driver;
	
	// Locators
	private By searchedBook = By.linkText("Selenium Ruby");

	public SearchResultsPage(WebDriver driver) {
		super(driver);
	}

	// functions
	public ProductDisplayPage selectSearchedBook() throws InterruptedException {
		click(searchedBook);
		wait(5);	
		return new ProductDisplayPage(driver);
	}
}
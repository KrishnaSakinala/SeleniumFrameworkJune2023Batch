package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {

	//private WebDriver  driver;
	
	// Locators
	By firstNameTextbox = By.cssSelector("#billing_first_name");
	By lastNameTextbox = By.cssSelector("#billing_last_name");
	By emailTextbox = By.cssSelector("#billing_email");
	By phoneTextbox = By.cssSelector("#billing_phone");
	By countryDropdown = By.cssSelector("#select2-chosen-1");
	By countrySearchBox = By.cssSelector("#s2id_autogen1_search");
	By countryList = By.cssSelector(".select2-results >li");
	By addressTextBox = By.cssSelector("#billing_address_1");
	By cityTextBox = By.cssSelector("#billing_city");
	By stateDropdown = By.cssSelector("#s2id_billing_state >a");
	By stateSearchBox = By.cssSelector("#s2id_autogen2_search");
	By statesList = By.cssSelector("#select2-results-2 >li");
	By postCodeTextBox = By.cssSelector("#billing_postcode");
	By cashOnDeliveryRadioButton = By.cssSelector("#payment_method_cod");
	By placeOrderButton = By.cssSelector("#place_order");
	By successMessageLabel = By.cssSelector(".woocommerce-thankyou-order-received");
	By directBankTransferRadioButton = By.cssSelector("#payment_method_bacs");
	
	
	public CheckoutPage(WebDriver driver) {
		//this.driver = driver;
		super(driver);
	}

	// functions
	
	public void provideCustomerDetails() {
		driver.findElement(firstNameTextbox).sendKeys("Krishna");
		driver.findElement(lastNameTextbox).sendKeys("Sakinala");
		driver.findElement(emailTextbox).sendKeys("email@gmail.com");
		driver.findElement(phoneTextbox).sendKeys("9876543210");
		driver.findElement(countryDropdown).click();
		driver.findElement(countrySearchBox).sendKeys("India");
		List<WebElement> countries = driver.findElements(countryList);
		for(WebElement country : countries) {
			if(country.getText().equals("India")) {
				country.click();
			}
		}			
		driver.findElement(addressTextBox).sendKeys("Address1");
		driver.findElement(cityTextBox).sendKeys("Hyderabad");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,250)");
		driver.findElement(stateDropdown).click();
		driver.findElement(stateSearchBox).sendKeys("Telangana");
		List<WebElement> states = driver.findElements(statesList);
		for(WebElement state : states) {
			if(state.getText().equals("Telangana")) {
				state.click();
			}
		}
		driver.findElement(postCodeTextBox).sendKeys("500082");
		js.executeScript("window.scrollBy(0,250)");
	}
	
	
	public void placeOrderUsingCashOnDelivery() throws InterruptedException {
		provideCustomerDetails();
		driver.findElement(cashOnDeliveryRadioButton).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,250)");
		driver.findElement(placeOrderButton).click();
		Thread.sleep(5000);	 
		
	}
	
	public void placeOrderUsingDirectBankTransfer() throws InterruptedException {
		provideCustomerDetails();
		driver.findElement(directBankTransferRadioButton).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,250)");
		driver.findElement(placeOrderButton).click();
		Thread.sleep(5000);			
	}
	
	public String getSuccessMessage() {
		return driver.findElement(successMessageLabel).getText();
	}

}

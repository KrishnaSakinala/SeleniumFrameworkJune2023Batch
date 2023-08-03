package tests;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;

public class DummyTest extends BaseTest {	
	
	@Test
	public void placeOrder() throws InterruptedException {		
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://practice.automationtesting.in");
		
		// Home Page
		driver.findElement(By.id("s")).sendKeys("Selenium");
		driver.findElement(By.id("s")).sendKeys(Keys.ENTER);
		// Search Results Page
		driver.findElement(By.linkText("Selenium Ruby")).click();
		Thread.sleep(5000);
		// Product Display Page
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".woocommerce-message >a")).click();
		// Basket Page
		//driver.findElement(By.name("coupon_code")).sendKeys("krishnasakinala");
		//driver.findElement(By.name("apply_coupon")).click();
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,200)");		
		driver.findElement(By.cssSelector(".wc-proceed-to-checkout >a")).click();
		// checkout page
		driver.findElement(By.cssSelector("#billing_first_name")).sendKeys("Krishna");
		driver.findElement(By.cssSelector("#billing_last_name")).sendKeys("Sakinala");
		driver.findElement(By.cssSelector("#billing_email")).sendKeys("email@gmail.com");
		driver.findElement(By.cssSelector("#billing_phone")).sendKeys("9876543210");
		driver.findElement(By.cssSelector("#select2-chosen-1")).click();
		driver.findElement(By.cssSelector("#s2id_autogen1_search")).sendKeys("India");
		List<WebElement> countries = driver.findElements(By.cssSelector(".select2-results >li"));
		for(WebElement country : countries) {
			if(country.getText().equals("India")) {
				country.click();
			}
		}			
		driver.findElement(By.cssSelector("#billing_address_1")).sendKeys("Address1");
		driver.findElement(By.cssSelector("#billing_city")).sendKeys("Hyderabad");
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,250)");
		driver.findElement(By.cssSelector("#s2id_billing_state >a")).click();
		driver.findElement(By.cssSelector("#s2id_autogen2_search")).sendKeys("Telangana");
		List<WebElement> states = driver.findElements(By.cssSelector("#select2-results-2 >li"));
		for(WebElement state : states) {
			if(state.getText().equals("Telangana")) {
				state.click();
			}
		}
		driver.findElement(By.cssSelector("#billing_postcode")).sendKeys("500082");
		js.executeScript("window.scrollBy(0,250)");
		driver.findElement(By.cssSelector("#payment_method_cod")).click();
		js.executeScript("window.scrollBy(0,250)");
		driver.findElement(By.cssSelector("#place_order")).click();
		Thread.sleep(5000);
		String successMessage = driver.findElement(By.cssSelector(".woocommerce-thankyou-order-received")).getText();
		Assert.assertEquals(successMessage, "Thank you. Your order has been received.");
		driver.quit();
	}
}

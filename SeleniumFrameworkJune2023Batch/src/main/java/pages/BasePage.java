package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
	
	public WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void click(By locator) {
		driver.findElement(locator).click();
	}
	
	public void type(By locator, String value) {
		driver.findElement(locator).sendKeys(value);
	}
	
	public void wait(int seconds) {
		try {
			Thread.sleep(1000*seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

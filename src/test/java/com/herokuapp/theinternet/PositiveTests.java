package com.herokuapp.theinternet;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void loginTest() {
		System.out.println("Starting Log In Test");
		// Create Driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		sleep(3000);

		driver.manage().window().maximize();
		
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Open the page
		String url = "https://www.optimus-nt-new.slc.co.uk/login";
		driver.get(url);
		sleep(5000);
		System.out.println("Page Is Opened.");

		// Enter Username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("bcfmanager");

		// Enter Password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("password61");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		// Push Login button
		WebElement logInButton = driver.findElement(By.id("btn-confirm"));
		wait.until(ExpectedConditions.elementToBeClickable(logInButton));
		logInButton.click();

		// Verifications
		// New URL
		sleep(3000);
		String expectedUrl = "https://www.optimus-nt-new.slc.co.uk/customers";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);

		// Logout button is visible
		sleep(3000);
		WebElement logOutButton = driver.findElement(By.id("logout"));
		Assert.assertTrue(logOutButton.isDisplayed(), "logOutButton is not displayed.");

		// Successful log in message
		WebElement sucessMessage = driver.findElement(By.className("overview-title"));
		String expectedSucessMessage = "Search or create a customer";
		String actualSucessMessage = sucessMessage.getText();
		Assert.assertTrue(actualSucessMessage.contains(expectedSucessMessage),
				"actualSucessMessage does not contain expectedSucessMessage: " + expectedSucessMessage
						+ "\nactualSucessMessage: " + actualSucessMessage);

		sleep(3000);

		// Close Browser
		driver.quit();

	}

	/**
	 * Static Sleep
	 */

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

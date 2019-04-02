package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTest {

	// Create Driver outside of Methods and within the CLass so all Methods can use
	// wd
	WebDriver wd;

	@Parameters({ "broswer" })
	@BeforeMethod
	protected void setUp(@Optional("chrome") String browser) {
		System.out.println("Creating Driver: " + browser);
		// Create Driver

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			wd = new ChromeDriver();

		case "firefox":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			wd = new ChromeDriver();

			break;

		default:
			System.out.println("Do not know how to start: " + browser + ", Starting Chrome");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			wd = new ChromeDriver();
			break;
		}

	}

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 1, groups = { "smokeTests", "negativeTests" })
	public void negativeTest(String username, String password, String expectedMessage) {

		System.out.println("Starting Negative Testing");
		sleep(3000);
		// Open Webpage
		String url = "https://www.optimus-nt-new.slc.co.uk/login";
		wd.get(url);

		// Enter incorrect Username
		sleep(3000);
		WebElement usernameElement = wd.findElement(By.id("username"));
		usernameElement.sendKeys(username);

		// Enter Valid Password
		WebElement passwordElement = wd.findElement(By.id("password"));
		passwordElement.sendKeys(password);

		// Click button
		WebElement logInButton = wd.findElement(By.id("btn-confirm"));
		logInButton.click();

		sleep(3000);
		// Verifications
		// Confirm correct message displayed
		WebElement failLoginMessage = wd.findElement(By.id("loginErrorMessage"));

		String actualLoginMessage = failLoginMessage.getText();
		Assert.assertTrue(actualLoginMessage.contains(expectedMessage),
				"Expected Error Message: " + expectedMessage + "\nActual Error Message: " + actualLoginMessage + ".");

	}

	@AfterMethod
	protected void tearDown() {
		System.out.println("Close Driver");
		// Close Browser
		wd.quit();

	}

	/*
	 * @Test(priority = 1, groups = {"smokeTests", "negativeTests"}) public void
	 * incorrectUsernameTest() {
	 * 
	 * System.out.println("Incorrect Username Test"); // Create Driver
	 * System.setProperty("webdriver.chrome.driver",
	 * "src/main/resources/chromedriver.exe"); WebDriver wd = new ChromeDriver();
	 * 
	 * // Open Webpage String url = "https://www.optimus-nt-new.slc.co.uk/login";
	 * wd.get(url); wd.manage().window().maximize();
	 * 
	 * // Enter incorrect Username sleep(3000); WebElement username =
	 * wd.findElement(By.id("username")); username.sendKeys("incorrectusername");
	 * 
	 * // Enter Valid Password WebElement password =
	 * wd.findElement(By.id("password")); password.sendKeys("password61");
	 * 
	 * // Click button WebElement logInButton =
	 * wd.findElement(By.id("btn-confirm")); logInButton.click();
	 * 
	 * sleep(3000); // Verifications // Confirm correct message displayed WebElement
	 * failLoginMessage = wd.findElement(By.id("loginErrorMessage")); String
	 * expectedLoginMessage = "Username and password mismatch"; String
	 * actualLoginMessage = failLoginMessage.getText();
	 * Assert.assertTrue(actualLoginMessage.contains(expectedLoginMessage),
	 * "Expected Error Message: " + expectedLoginMessage +
	 * "\nActual Error Message: " + actualLoginMessage + ".");
	 * 
	 * // Close Browser wd.quit();
	 * 
	 * }
	 * 
	 * @Test(priority = 2, groups = {"negativeTests"}) public void
	 * incorrectPasswordTest() {
	 * 
	 * System.out.println("Incorrect Password Test"); // Create Driver
	 * System.setProperty("webdriver.chrome.driver",
	 * "src/main/resources/chromedriver.exe"); WebDriver wd = new ChromeDriver();
	 * 
	 * // Open Webpage String url = "https://www.optimus-nt-new.slc.co.uk/login";
	 * wd.get(url); wd.manage().window().maximize();
	 * 
	 * // Enter incorrect Username sleep(3000); WebElement username =
	 * wd.findElement(By.id("username")); username.sendKeys("bcfmanager");
	 * 
	 * // Enter Valid Password WebElement password =
	 * wd.findElement(By.id("password")); password.sendKeys("passwordWrong");
	 * 
	 * // Click button WebElement logInButton =
	 * wd.findElement(By.id("btn-confirm")); logInButton.click();
	 * 
	 * sleep(3000); // Verifications // Confirm correct message displayed WebElement
	 * failLoginMessage2 = wd.findElement(By.id("loginErrorMessage")); String
	 * expectedLoginMessage2 = "Username and password mismatch"; String
	 * actualLoginMessage2 = failLoginMessage2.getText();
	 * Assert.assertTrue(actualLoginMessage2.contains(expectedLoginMessage2),
	 * "Expected Error Message: " + expectedLoginMessage2 +
	 * "\nActual Error Message: " + actualLoginMessage2 + ".");
	 * 
	 * // Close Browser wd.quit();
	 * 
	 * }
	 */

	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

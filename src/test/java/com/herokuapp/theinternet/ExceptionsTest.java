package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExceptionsTest {

	// Create Driver outside of Methods and within the CLass so all Methods can use
	// wd
	WebDriver wd;

	@BeforeMethod
	protected void setUp() {
		System.out.println("Creating Driver");
		// Create Driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		wd = new ChromeDriver();

		sleep(3000);

		wd.manage().window().maximize();

	}

	@Test
	public void studentNotFoundTest() {

		System.out.println("Starting Student Not Found Test");
		// Open Log in Page
		String url = "https://www.optimus-nt-new.slc.co.uk/";
		wd.get(url);
		sleep(3000);
		System.out.println("Page is Open");

		// Enter Username
		WebElement username = wd.findElement(By.id("username"));
		username.sendKeys("bcfmanager");

		// Enter Password
		WebElement password = wd.findElement(By.id("password"));
		password.sendKeys("password61");

		// Click login button
		WebElement logInButton = wd.findElement(By.id("btn-confirm"));
		logInButton.click();
		sleep(2000);

		// Enter Incorrect Student Forename
		WebElement studentForeName = wd.findElement(By.id("studentFirstName"));
		studentForeName.sendKeys("ABC1");
		// Enter Incorrect Student Surname
		WebElement studentSurName = wd.findElement(By.id("studentLastName"));
		studentSurName.sendKeys("XYZ");

		// Click Search button
		WebElement searchButton = wd.findElement(By.id("advancedSearchSubmit"));
		searchButton.click();
		sleep(2000);

		// Check Correct Test Appears
		WebElement custNotFoundText = wd.findElement(By.id("studentAdvancedValidation"));

		// Wait till the text is visable on the screen
		WebDriverWait wait = new WebDriverWait(wd, 10);
		wait.until(ExpectedConditions.visibilityOf(custNotFoundText));

		Assert.assertTrue(custNotFoundText.getText().equals("Customer not found"),
				",Customer not found Text is not present on the page'");

	}

	@Test
	public void timeoutTest() {
		// Open Log in Page
		String url = "https://www.optimus-nt-new.slc.co.uk/";
		wd.get(url);
		sleep(3000);
		System.out.println("Page is Open");

		// Enter Username
		WebElement username = wd.findElement(By.id("username"));
		username.sendKeys("bcfmanager");

		// Enter Password
		WebElement password = wd.findElement(By.id("password"));
		password.sendKeys("password61");

		// Click login button
		WebElement logInButton = wd.findElement(By.id("btn-confirm"));
		logInButton.click();
		sleep(2000);

		// Enter Incorrect Student Forename
		WebElement studentForeName = wd.findElement(By.id("studentFirstName"));
		studentForeName.sendKeys("ABC1");
		// Enter Incorrect Student Surname
		WebElement studentSurName = wd.findElement(By.id("studentLastName"));
		studentSurName.sendKeys("XYZ");

		// Click Search button
		WebElement searchButton = wd.findElement(By.id("advancedSearchSubmit"));
		searchButton.click();
		sleep(2000);

		// Check Correct Test Appears
		WebElement custNotFoundText = wd.findElement(By.id("studentAdvancedValidation"));

		// Wait till the text is visable on the screen
		WebDriverWait wait = new WebDriverWait(wd, 2);
		try {
			wait.until(ExpectedConditions.visibilityOf(custNotFoundText));
		} catch (TimeoutException e) {
			// Steps after exception
			System.out.println("Exception catched " + e.getMessage());
			sleep(3000);
		}

		Assert.assertTrue(custNotFoundText.getText().equals("Customer not found"),
				",Customer not found Text is not present on the page'");

	}

	@AfterMethod
	protected void tearDown() {
		sleep(3000);
		System.out.println("Close Driver");
		// Close Browser
		wd.quit();
	}

	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

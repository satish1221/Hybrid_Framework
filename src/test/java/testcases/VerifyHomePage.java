package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import factory.BrowserFactory;
import factory.DataProviderFactory;
import pages.HomePage;

public class VerifyHomePage {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = BrowserFactory.getBrowser("chrome");
		driver.get(DataProviderFactory.getConfig().getApplicationUrl());
	}

	@Test
	public void testHomePage() {

		HomePage home = PageFactory.initElements(driver, HomePage.class);
		String title = home.getTitle();
		System.out.println("My Application Title is :" + title);
		Assert.assertTrue(title.contains("Avactis Demo Store"));
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(10000);
		BrowserFactory.closeBrowser(driver);
	}
}

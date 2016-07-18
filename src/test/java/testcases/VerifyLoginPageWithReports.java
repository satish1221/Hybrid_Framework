package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import factory.BrowserFactory;
import factory.DataProviderFactory;
import pages.HomePage;
import pages.LoginPage;
import utility.Helper;

public class VerifyLoginPageWithReports {
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;

	@BeforeMethod
	public void setup() {

		report = new ExtentReports(".\\Reports\\LoginPageReport.html", true);
		logger = report.startTest("Verify Login Page");
		driver = BrowserFactory.getBrowser("firefox");
		driver.get(DataProviderFactory.getConfig().getApplicationUrl());
		logger.log(LogStatus.INFO, "Application is up and Running");
	}

	@Test
	public void testHomePage() {

		HomePage home = PageFactory.initElements(driver, HomePage.class);
		String title = home.getTitle();
		System.out.println("My Application Title is :" + title);
		Assert.assertTrue(title.contains("Avactis Demo Store"));
		logger.log(LogStatus.PASS, "HomePage Loaded and Verified");
		home.clickOnSignInLink();
		logger.log(LogStatus.INFO, "Click on SignIn Link");
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		login.loginApplication(DataProviderFactory.getExcel().getData(0, 0, 0),
				DataProviderFactory.getExcel().getData(0, 0, 1));
		logger.log(LogStatus.INFO, "Login to application");
		login.verifySignoutLink();
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captureScreenshot(driver, "Validation2 ")));
		logger.log(LogStatus.PASS, "SignOut Link is Present");
		
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		// Thread.sleep(10000);

		if (result.getStatus() == ITestResult.FAILURE) {
			String path = Helper.captureScreenshot(driver, result.getName());
			logger.log(LogStatus.FAIL, logger.addScreenCapture(path));
		}
		BrowserFactory.closeBrowser(driver);
		report.endTest(logger);
		report.flush();
	}
}

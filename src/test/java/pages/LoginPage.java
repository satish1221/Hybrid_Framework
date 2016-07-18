package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	@FindBy(id = "account_sign_in_form_email_id")
	WebElement userName;

	@FindBy(id = "account_sign_in_form_passwd_id")
	WebElement password;

	@FindBy(xpath = "//input[@value='Sign In']")
	WebElement signIn;

	By signOut = By.xpath("//div[@class='pre-header']//a[text()='Sign Out']");

	public void loginApplication(String user, String pass) {
		userName.sendKeys(user);
		password.sendKeys(pass);
		signIn.click();
	}

	public void verifySignoutLink() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement ele = wait.until(ExpectedConditions
				.visibilityOfElementLocated(signOut));
		String text = ele.getText();
		Assert.assertEquals(text, "Sign Out");
	}

}

package factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BrowserFactory {
	static WebDriver driver;
	public static WebDriver getBrowser(String browserName){
		
		if(browserName.equalsIgnoreCase("firefox")){
			driver= new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", DataProviderFactory.getConfig().getChromePath());
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("internet")){
			System.setProperty("webdriver.ie.driver", DataProviderFactory.getConfig().getIEpath());
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}
	
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}

}

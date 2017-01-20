package core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.PropertyDictionary;

public class Browser {
	private WebDriver driver;
	
	public Browser(String browser, String environment) throws MalformedURLException
	{
		DesiredCapabilities caps = new DesiredCapabilities();
		browser = browser.trim().toLowerCase();
		
		if(environment.equalsIgnoreCase("remote"))
		{
			//Dilip
			caps = DesiredCapabilities.chrome();
			caps.setBrowserName("chrome");
			caps.setPlatform(Platform.LINUX);
			//System.setProperty("webdriver.chrome.driver","/home/dilip/Devlopment_Dilip_NobleAutomation/lib/chromedriver");
			//System.out.println("In Browser.java -- before launching Web Driver");
			driver = new RemoteWebDriver(new URL(PropertyDictionary.map.get("remoteWebdriver")),caps);
			driver.manage().window().maximize();
			//driver = new RemoteWebDriver(new URL("http://10.100.4.10:4444/wd/hub"),caps);
		}
		
		else
		{
		switch (browser) {
		case "chrome":
			caps = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("ignore-certificate-errors");
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			System.out.println(System.getProperty("user.dir"));
			System.setProperty("webdriver.chrome.driver",".//lib//chromedriver.exe");
			driver = new ChromeDriver(caps);
			
			break;

		case "firefox":
			caps = DesiredCapabilities.firefox();
			driver = new FirefoxDriver();
			break;
			
		case "ie":
			caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
			caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			driver = new InternetExplorerDriver(caps);
			break;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		}
	}
	
	public WebDriver getWebDriver() {
		
		return driver;
	}

}

package com.barsha.selenium;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBaseSetup {
	private WebDriver driver;
	protected String defaultDriver = "chrome";
	static String driverPath = "lib";
	Map<String, WebDriver> driverMap = new HashMap<>();

	public WebDriver getDriver(String driverName) {
		driver = driverMap.get(driverName);
		return driver;
	}

	private void setDriver(String appURL) {
		driverMap.put("chrome", initChromeDriver(appURL));
		//driverMap.put("firefox", initFirefoxDriver(appURL));
	}

	private static WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", "/home/pratik/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser.."+appURL);
		//System.setProperty("webdriver.firefox.marionette","/home/pratik/git/selenium/lib/selenium-firefox-driver-2.53.1.jar");
		//FirefoxDriverManager.getInstance().setup();
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	//@Parameters({ "appURL" })
	//@BeforeClass
	public void initializeTestBaseSetup(String appURL) {
		try {
			setDriver(appURL);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void setUp() {
		initializeTestBaseSetup("http://stage.ehs.ap.gov.in/EHSAP");
	}

	@AfterMethod
	public void tearDown() {
		for (WebDriver driver : driverMap.values()) {
			driver.quit();
		}
	}

	public void close() {

	}
}

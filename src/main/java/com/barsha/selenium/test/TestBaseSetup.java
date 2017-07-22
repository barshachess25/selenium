package com.barsha.selenium.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.barsha.selenium.db.DatabaseHelper;

public class TestBaseSetup extends DatabaseHelper {
	private static WebDriver driver;
	protected String defaultDriver = "chrome";
	static String driverPath = "lib";
	Map<String, WebDriver> driverMap = new HashMap<>();

	public static <T> T initPage(Class<T> clazz) {
		return PageFactory.initElements(driver, clazz);
	}

	public WebDriver getDriver(String driverName) {
		driver = driverMap.get(driverName);
		return driver;
	}

	public WebDriver getDriver() {
		driver = driverMap.get("chrome");
		return driver;
	}

	protected String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	private void setDriver() {
		driverMap.put("chrome", initChromeDriver());
		//driverMap.put("firefox", initFirefoxDriver());
	}

	private WebDriver initChromeDriver() {
		System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", config.getProperty("chromeDriverLocation"));
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.navigate().to(config.getProperty("appUrl"));
		return driver;
	}

	private WebDriver initFirefoxDriver() {
		System.out.println("Launching Firefox browser.."+config.getProperty("appUrl"));
		System.setProperty("webdriver.gecko.driver", config.getProperty("firefoxDriverLocation"));
		FirefoxBinary binary = new FirefoxBinary(new File(config.getProperty("firefoxBinaryLocation")));
		//FirefoxDriverManager.getInstance().setup();
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setBinary(binary);
		firefoxOptions.setProfile(new FirefoxProfile());
		WebDriver driver = new FirefoxDriver(firefoxOptions);
		driver.manage().window().maximize();
		driver.navigate().to(config.getProperty("appUrl"));
		return driver;
	}

	//@Parameters({ "appURL" })
	//@BeforeClass
	public void initializeTestBaseSetup() {
		try {
			setDriver();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void setUp() {
		getDriver();
		driver.navigate().to(config.getProperty("appUrl"));
	}

	@AfterMethod
	public void afterMethod() {
		/*for (WebDriver driver : driverMap.values()) {
			driver.close();
		}*/
	}

	@BeforeTest
	public void setTest() {
		initializeTestBaseSetup();
	}

	@AfterTest
	public void tearDown() {
		for (WebDriver driver : driverMap.values()) {
			driver.quit();
		}
	}

	public void close() {

	}
}

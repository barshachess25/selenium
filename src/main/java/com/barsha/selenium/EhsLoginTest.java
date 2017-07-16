package com.barsha.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EhsLoginTest extends TestBaseSetup {

	private String DRIVERS[] = {"chrome", "firefox"};

	LoginPage loginPage;
	HomePage homePage;

	@DataProvider(name = "webdriverDataProvider")
	public Object[][] webdriverDataProvider() {
		return new Object[][] {
			{"chrome"},
			{"firefox"}
		};
	}

	@DataProvider(name = "loginDataProvider")
	public Object[][] loginDataProvider() {
		Object obj[][] = new Object[][] {
			{"P22117737", "trust", "Pensioner", "G.DEMUDAMMA"},
			{"P060548278", "trust", "Pensioner", "V.JHANSI"},
			{"P030553649", "trust", "Pensioner", "MR/MSP.ALIVENI."},
			{"1222395", "trust", "Employee", "RAJA GOPAL"},
			{"1444988", "trust", "Employee", "LEELA KUMARI"}
		};

		Object[][] aobj = new Object[obj.length][];
		for (int i=0;i<obj.length;i++) {
			aobj[i] = new Object[obj[i].length+1];
			aobj[i][0] = defaultDriver;
			for (int j=0;j<obj[i].length;j++) {
				aobj[i][j+1] = obj[i][j];
			}
		}
		return aobj;
	}

	@Test(dataProvider = "loginDataProvider")
	public void testDoLogin(String driverName, String uname, String pwd, String userType, String name) {
		WebDriver driver = getDriver(driverName);
		loginPage = new LoginPage(driver);
		loginPage.doLogin(uname, pwd, userType);
		String url = driver.getCurrentUrl();
		Assert.assertEquals("http://stage.ehs.ap.gov.in/EHSAP/loginAction.do?actionFlag=checkLogin", url);

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.name("loginForm")));
		homePage = new HomePage(driver);

		Assert.assertEquals(name, homePage.getHeaderUserName());
		Assert.assertEquals(name, homePage.getNoticeUserName());
		if ("Pensioner".equals(userType)) {
			Assert.assertEquals("Family Pensioner", homePage.getHeaderDesignation());
		} else if ("Employee".equals(userType)) {
			Assert.assertEquals("Employee", homePage.getHeaderDesignation());
		}

		Assert.assertEquals("javascript:fn_logout()", homePage.getLogoutUrl());

		homePage.logout();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
	}
}

package com.barsha.selenium.test;

import static org.testng.Assert.assertEquals;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.barsha.selenium.HomePage;
import com.barsha.selenium.Pages;

public class EhsLoginTest extends TestBaseSetup {

	@DataProvider(name = "loginDataProvider")
	public Object[][] loginDataProvider() throws SQLException {
		return query("select login_name,pass_word,user_role,first_name from asrim_users where user_role='Pensioner'");
	}

	@Test(dataProvider = "loginDataProvider")
	public void testDoLogin(String uname, String pwd, String userType, String name) {
		HomePage homePage = Pages.loginPage.doLoginSuccess(uname, pwd, userType);
		assertEquals("http://stage.ehs.ap.gov.in/EHSAP/loginAction.do?actionFlag=checkLogin", getCurrentUrl());
		assertEquals(name, homePage.getHeaderUserName());
		assertEquals(name, homePage.getNoticeUserName());
		if ("Pensioner".equals(userType)) {
			assertEquals("Family Pensioner", homePage.getHeaderDesignation());
		} else if ("Employee".equals(userType)) {
			assertEquals("Employee", homePage.getHeaderDesignation());
		}
		assertEquals("javascript:fn_logout()", homePage.getLogoutUrl());

		homePage.logout();
		new WebDriverWait(getDriver(), 30).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
	}
}

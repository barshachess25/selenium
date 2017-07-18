package com.barsha.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void doLogin(String username, String password, String userType) {
		getElementById("username").sendKeys(username);
		getElementById("password").sendKeys(password);
		new Select(getElementByCss("select[name=loginType]")).selectByVisibleText(userType);
		getElementById("login").click();
	}

	public void doForgotPassword() {

	}
}

package com.barsha.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class LoginPage extends BasePage {

	By username = By.id("username");
	By password = By.id("password");
	By userType = By.name("loginType");
	By submit = By.id("login");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void doLogin(String username, String password, String userType) {
		driver.findElement(this.username).sendKeys(username);
		driver.findElement(this.password).sendKeys(password);
		new Select(driver.findElement(this.userType)).selectByVisibleText(userType);
		driver.findElement(this.submit).click();
	}

	public void doForgotPassword() {

	}
}

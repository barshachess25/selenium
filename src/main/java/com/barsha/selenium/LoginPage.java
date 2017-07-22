package com.barsha.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="username") WebElement username;
	@FindBy(id="password") WebElement password;
	@FindBy(css="select[name=loginType]") WebElement loginType;
	@FindBy(id="login") WebElement login;

	public HomePage doLoginSuccess(String usernameVal, String passwordVal, String userTypeVal) {
		username.sendKeys(usernameVal);
		password.sendKeys(passwordVal);
		new Select(loginType).selectByVisibleText(userTypeVal);
		login.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		return initPage(HomePage.class);
	}

	public LoginPage doLoginFailure(String usernameVal, String passwordVal, String userTypeVal) {
		username.sendKeys(usernameVal);
		password.sendKeys(passwordVal);
		new Select(loginType).selectByVisibleText(userTypeVal);
		login.click();
		return new LoginPage(driver);
	}

	public void doForgotPassword() {

	}
}

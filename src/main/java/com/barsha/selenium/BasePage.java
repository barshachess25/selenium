package com.barsha.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickImagesLink() {
		// It should have a logic to click on images link
		// And it should navigate to google images page
	}

	public String getPageTitle() {
		String title = driver.getTitle();
		return title;
	}

	public boolean verifyBasePageTitle() {
		String expectedPageTitle = "Google";
		return getPageTitle().contains(expectedPageTitle);
	}

	protected WebElement getElementById(String id) {
		return driver.findElement(By.id(id));
	}

	protected String getTextById(String id) {
		return driver.findElement(By.id(id)).getText();
	}

	protected WebElement getElementByCss(String cssPath) {
		return driver.findElement(By.cssSelector(cssPath));
	}

	protected String getTextByCss(String cssPath) {
		return driver.findElement(By.cssSelector(cssPath)).getText();
	}
}

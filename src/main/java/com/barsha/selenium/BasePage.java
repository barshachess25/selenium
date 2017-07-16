package com.barsha.selenium;

import org.openqa.selenium.WebDriver;

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
}

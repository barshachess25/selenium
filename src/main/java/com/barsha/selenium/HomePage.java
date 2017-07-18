package com.barsha.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public String getHeaderUserName() {
		return getTextByCss("#headerDiv > table > tbody > tr > td:first-child > p:nth-child(1)").split(":")[1].trim();
	}

	public String getNoticeUserName() {
		String text = driver.switchTo().frame("middleFrame").findElement(By.id("noticeboard")).findElement(By.tagName("h2")).getText().trim();
		driver.switchTo().defaultContent();
		return text;
	}

	public String getHeaderDesignation() {
		return getTextByCss("#headerDiv > table > tbody > tr > td:first-child > p:nth-child(2)").split(":")[1].trim();
	}

	public String getLogoutUrl() {
		return getElementByCss("a[title='Logout']").getAttribute("href");
	}

	public void logout() {
		Actions action = new Actions(driver);
		WebElement we = getElementByCss("a[title='Logout']");
		action.moveToElement(we).click();
	}
}

package com.barsha.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	By homePageUserName = By.id("noticeboard");

	public String getHeaderUserName() {
		return driver.findElement(By.id("headerDiv"))
				.findElement(By.cssSelector("table > tbody > tr > td:first-child > p:nth-child(1)"))
				.getText().split(":")[1].trim();
	}

	public String getNoticeUserName() {
		String text = driver.switchTo().frame("middleFrame").findElement(By.id("noticeboard")).findElement(By.tagName("h2")).getText().trim();
		driver.switchTo().defaultContent();
		return text;
	}

	public String getHeaderDesignation() {
		return driver.findElement(By.id("headerDiv")).findElement(By.cssSelector("table > tbody > tr > td:first-child > p:nth-child(2)")).getText().split(":")[1].trim();
	}

	public String getLogoutUrl() {
		return driver.findElement(By.cssSelector("a[title='Logout']")).getAttribute("href");
	}

	public void logout() {
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.cssSelector("a[title='Logout']"));
		action.moveToElement(we).click();
	}
}

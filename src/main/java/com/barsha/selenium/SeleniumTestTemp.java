package com.barsha.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "/home/pratik/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://inmumzm06.tcs.com");
		driver.findElement(By.id("username")).sendKeys("985956");
		driver.findElement(By.id("password")).sendKeys("Hyderabad@7");
		driver.findElement(By.cssSelector(".zLoginButton")).click();
		driver.findElement(By.cssSelector("#folder_6 > a")).click();
		driver.findElement(By.cssSelector("#mess_list_tbody:nth-child(1)")).click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.close();
	}
}

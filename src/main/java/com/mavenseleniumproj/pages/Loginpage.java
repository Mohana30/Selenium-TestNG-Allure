package com.mavenseleniumproj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Loginpage {
	
	public By loginUsername = By.id("emailAddress");
	private By loginPassword = By.id("password");
	private By loginSignIn = By.className("MuiButton-label");
	
	
	public WebElement getUsernameInput(WebDriver driver) {
		return driver.findElement(loginUsername);
	}
	
	public WebElement getPasswordInput(WebDriver driver) {
		return driver.findElement(loginPassword);
	}
	
	public WebElement getSigninButton(WebDriver driver) {
		return driver.findElement(loginSignIn);
	}

	
}

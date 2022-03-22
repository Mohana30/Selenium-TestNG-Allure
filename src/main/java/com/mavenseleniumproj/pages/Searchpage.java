package com.mavenseleniumproj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Searchpage {

	public WebDriver driver;

	public By searchHeading = By.cssSelector("h1.title");
	private By dashboardList = By.cssSelector("#sidebar li[ui-route='/dashboards']");	
	private By dashboardListLink = By.cssSelector(".panel-collapse.pullout.collapse.in a:nth-child(2)");
	
	public Searchpage(WebDriver driver) {		
		this.driver = driver;		
	}

	
	public WebElement getsearchHeading() {
		return driver.findElement(searchHeading);
	}
	
	public WebElement getdashboardList() {
		return driver.findElement(dashboardList);
	}
	
	public WebElement getdashboardListLink() {
		return driver.findElement(dashboardListLink);
	}
}

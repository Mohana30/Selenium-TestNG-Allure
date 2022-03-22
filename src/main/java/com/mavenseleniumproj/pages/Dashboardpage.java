package com.mavenseleniumproj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Dashboardpage {	
	
	public By headerDateTimeButton = By.cssSelector("button[title='DATE/TIME']");
	public By historicIcon = By.cssSelector(".interval-selector-container a:nth-child(2)");
	public By fromDateTimeText = By.cssSelector("div[selection='from'] .control-toggle span");
	public By toDateTimeText = By.cssSelector("div[selection='to'] .control-toggle span");	
	
	public By submitBtn = By.cssSelector("#date-range-selector button[type='submit']");
	public By fromDate = By.cssSelector("div.clearfix span:first-child");
	public By toDate = By.cssSelector("div.clearfix span:last-child");	
	public By duplicateDashboard = By.cssSelector(".pull-right.btn-group.widget-tools li[title='Duplicate Dashboard']");
	public By duplicateDashboardTitle = By.className(".modal-header");	
	public By duplicateDashboardName = By.cssSelector("input[id=name]");	
	public By deleteDashboard = By.cssSelector(".pull-right.btn-group.widget-tools li[title='Delete']");
	public By okDelDashboardBtn = By.cssSelector(".modal-footer .btn-group .btn.btn-primary");	
	public By newDashboardTitle = By.cssSelector("div h1");
		
	public By signOutUserBtn = By.cssSelector(".fa.fa-user");
	public By signOutUserIcon = By.cssSelector(".btn.btn-primary.pull-right");	
	
	
			
	public WebElement getheaderDateTimeButton(WebDriver driver) {
		return driver.findElement(headerDateTimeButton);
	}
	
	public WebElement gethistoricIcon(WebDriver driver) {
		return driver.findElement(historicIcon);
	}
	
	public WebElement getfromDateTimeText(WebDriver driver) {
		return driver.findElement(fromDateTimeText);
	}
	
	public WebElement gettoDateTimeText(WebDriver driver) {
		return driver.findElement(toDateTimeText);
	}
	
	public WebElement getfromToMonthYear(String dateNav,WebDriver driver) {		
		By fromMonthYear = By.cssSelector("div[selection='"+dateNav+"'] .month-container span");
		return driver.findElement(fromMonthYear);
	}
	public WebElement getfromToMonthYearPrevious(String dateNav,WebDriver driver) {
		By fromMonthYearPrevious = By.cssSelector("div[selection='"+dateNav+"'] .previous.btn.btn-text");
		return driver.findElement(fromMonthYearPrevious);
	}
	public WebElement getfromToMonthYearNext(String dateNav,WebDriver driver) {
		By fromMonthYearNext = By.cssSelector("div[selection='"+dateNav+"'] .next.btn.btn-text");
		return driver.findElement(fromMonthYearNext);
	}
	public WebElement getfromToDay(String dateNav,WebDriver driver, String day) {
		By fromDay = By.xpath("//div[@selection='"+dateNav+"']//div[normalize-space()='"+day+"'][@class='day']");
		return driver.findElement(fromDay);
	}
	public WebElement getfromToHours(String dateNav,WebDriver driver) {
		By fromHours = By.cssSelector("div[selection='"+dateNav+"'] select[name='hours']");
		return driver.findElement(fromHours);
	}
	public WebElement getfromToMinutes(String dateNav,WebDriver driver) {
		By fromMinutes = By.cssSelector("div[selection='"+dateNav+"'] select[name='minutes']");
		return driver.findElement(fromMinutes);
	}
	
	
	public WebElement getsubmitBtn(WebDriver driver) {
		return driver.findElement(submitBtn);
	}	
	
	public WebElement getfromDate(WebDriver driver) {
		return driver.findElement(fromDate);
	}	
	
	public WebElement gettoDate(WebDriver driver) {
		return driver.findElement(toDate);
	}	
	
	public WebElement getduplicateDashboard(WebDriver driver) {
		return driver.findElement(duplicateDashboard);
	}
	
	public WebElement getduplicateDashboardTitle(WebDriver driver) {
		return driver.findElement(duplicateDashboardTitle);
	}
	
	public WebElement getduplicateDashboardName(WebDriver driver) {
		return driver.findElement(duplicateDashboardName);
	}
	
	public WebElement getdeleteDashboard(WebDriver driver) {
		return driver.findElement(deleteDashboard);
	}
	
	public WebElement getokDelDashboardBtn(WebDriver driver) {
		return driver.findElement(okDelDashboardBtn);
	}
	
	public WebElement getnewDashboardTitle(WebDriver driver) {
		return driver.findElement(newDashboardTitle);
	}
	
	public WebElement getsignOutUserBtn(WebDriver driver) {
		return driver.findElement(signOutUserBtn);
	}
	
	public WebElement getsignOutUserIcon(WebDriver driver) {
		return driver.findElement(signOutUserIcon);
	}
	
}

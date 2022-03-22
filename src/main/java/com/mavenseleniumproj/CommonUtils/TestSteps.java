package com.mavenseleniumproj.CommonUtils;

import java.time.Duration;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mavenseleniumproj.pages.Dashboardpage;
import com.mavenseleniumproj.pages.Loginpage;
import com.mavenseleniumproj.pages.Searchpage;

public class TestSteps extends Libraries {


	public void loginApp(String url) throws Exception {
		Loginpage loginPage = new Loginpage();
		navigateToUrl(url);
		Assert.assertTrue(waitForElementPresence(driver,loginPage.loginUsername));
		loginPage.getUsernameInput(driver).sendKeys(getProp("intellisense_username"));
		loginPage.getPasswordInput(driver).sendKeys(getProp("intellisense_password"));
		loginPage.getSigninButton(driver).click();
	}

	public void verifySearchPageDisplayed() throws Exception, Exception {
		Searchpage searchPage = new Searchpage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(getProp("pageLoadTimeout"))));
		wait.until(ExpectedConditions.presenceOfElementLocated(searchPage.searchHeading));
	}

	public void navigateToDashboard(String url) throws Exception {
		navigateToUrl(url);
		implicitlyWait(driver);
	}

	public void verifyDataDisplayed(String dashboardHeading) throws Exception {
		Searchpage searchPage = new Searchpage(driver);
		Assert.assertTrue(waitForElementPresence(driver,searchPage.searchHeading));
		implicitlyWait(driver);
		Assert.assertTrue(searchPage.getsearchHeading().getText().contains(dashboardHeading));
	}

	public void changeDate(String fromDate, String toDate) throws Exception {
		Dashboardpage dashboardPage = new Dashboardpage();
		Assert.assertTrue(waitForElementToBeClickable(driver,dashboardPage.headerDateTimeButton));	
		dashboardPage.getheaderDateTimeButton(driver).click();
		Assert.assertTrue(waitForElementToBeClickable(driver,dashboardPage.historicIcon));
		dashboardPage.gethistoricIcon(driver).click();
		implicitlyWait(driver);
		dashboardPage.getfromDateTimeText(driver).click();
		selectFromDateTime(fromDate,"from");
		dashboardPage.getfromDateTimeText(driver).click();
		dashboardPage.gettoDateTimeText(driver).click();
		selectFromDateTime(toDate,"to");
		dashboardPage.gettoDateTimeText(driver).click();
		dashboardPage.getsubmitBtn(driver).click();

	}
	
	public void verifyDate(String fromDate, String toDate) {
		Dashboardpage dashboardPage = new Dashboardpage();
		Assert.assertEquals(dashboardPage.getfromDate(driver).getText(), fromDate);
		Assert.assertEquals(dashboardPage.gettoDate(driver).getText(), toDate);
	}
	
	public void cloneCurrentDashboard(String newDashboardName) throws Exception {
		Dashboardpage dashboardPage = new Dashboardpage();
		Assert.assertTrue(waitForElementToBeClickable(driver,dashboardPage.duplicateDashboard));
		dashboardPage.getduplicateDashboard(driver).click();
		implicitlyWait(driver);
		dashboardPage.getduplicateDashboardName(driver).sendKeys(newDashboardName);
		dashboardPage.getokDelDashboardBtn(driver).click();
		implicitlyWait(driver);
	}
	
	public void deleteDashboard(String newDashboardTitle) throws Exception {
		Dashboardpage dashboardPage = new Dashboardpage();
		dashboardPage.getdeleteDashboard(driver).click();
		dashboardPage.getokDelDashboardBtn(driver).click();
		Assert.assertTrue(waitForElementToDisappear(driver,dashboardPage.deleteDashboard));
		Assert.assertEquals(dashboardPage.getnewDashboardTitle(driver).getText(), newDashboardTitle);
	}

	public void selectFromDateTime(String fromDateTime, String navType) {
		
		System.out.println(fromDateTime);
		Dashboardpage dashboardPage = new Dashboardpage(); 
		String monthYear, hour, minutes, date;
		String[] dateArray = fromDateTime.split(" |:");
		
		date = dateArray[1].replace(",", "");
		int year = Integer.parseInt(dateArray[2]);
		monthYear = dateArray[0]+" "+dateArray[2];
		hour = dateArray[3];
		minutes = dateArray[4];
		
		String calMonthYear = dashboardPage.getfromToMonthYear(navType, driver).getText().trim();
		String[] calMonthYearArray = calMonthYear.split(" ");
		int calYear = Integer.parseInt(calMonthYearArray[1]);
		
		DateTimeFormatter MONTH_FORMAT = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
		int month = MONTH_FORMAT.parse(dateArray[0]).get(ChronoField.MONTH_OF_YEAR); 
		
		while(!calMonthYear.equals(monthYear)) {

			
			if(calYear>year) {
				dashboardPage.getfromToMonthYearPrevious(navType, driver).click();
			}else if(calYear<year) {
				dashboardPage.getfromToMonthYearNext(navType, driver).click();
			}else {
				String[] calMonthArray = calMonthYear.split(" ");
				MONTH_FORMAT = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
				int calMonthNum = MONTH_FORMAT.parse(calMonthArray[0]).get(ChronoField.MONTH_OF_YEAR);
				
				if(calMonthNum>month) {
					dashboardPage.getfromToMonthYearPrevious(navType, driver).click();
				}else if(calMonthNum<month) {
					dashboardPage.getfromToMonthYearNext(navType, driver).click();
				}
			}
			calMonthYear = dashboardPage.getfromToMonthYear(navType, driver).getText().trim();
		}
		
		dashboardPage.getfromToDay(navType, driver, date).click();
		
		//Select Time and Minute
		Select seHour = new Select(dashboardPage.getfromToHours(navType, driver));
		seHour.selectByValue(hour);
		
		Select seMin = new Select(dashboardPage.getfromToMinutes(navType, driver));
		seMin.selectByValue(minutes);
	}

	public void signOut() throws Exception {
		Dashboardpage dashboardPage = new Dashboardpage();
		Loginpage loginPage = new Loginpage();
		implicitlyWait(driver);	
		dashboardPage.getsignOutUserBtn(driver).click();
		Assert.assertTrue(waitForElementToBeClickable(driver,dashboardPage.signOutUserIcon));
		dashboardPage.getsignOutUserIcon(driver).click();
		Assert.assertTrue(waitForElementPresence(driver,dashboardPage.okDelDashboardBtn));
		dashboardPage.getokDelDashboardBtn(driver).click();
		implicitlyWait(driver);	
		Assert.assertTrue(waitForElementPresence(driver,loginPage.loginUsername));
	}
}

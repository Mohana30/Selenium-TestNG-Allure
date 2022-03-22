package com.mavenseleniumproj.tests;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.mavenseleniumproj.CommonUtils.Libraries;
import com.mavenseleniumproj.CommonUtils.TestSteps;
import com.mavenseleniumproj.pages.Dashboardpage;
import com.mavenseleniumproj.pages.Searchpage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class DashboardTest extends TestSteps{
	

	WebDriver driver;
	public Searchpage sp;
	public Dashboardpage dp;
	
	@BeforeTest
	public void initialize() throws Exception	{	
		 driver = initializeDriver();
	}
	
	
	@Test(priority=1, description="Verify that the dashboard page displayed with defailt data")
	@Description("Verify that the dashboard page displayed with defailt data")
	@Severity(SeverityLevel.BLOCKER)
	public void login() throws Exception {
		loginApp(getProp("baseUrl"));
		verifySearchPageDisplayed();
		navigateToDashboard(getProp("dashboardUrl"));
		verifyDataDisplayed("Test Project");
		signOut();
	}
	
	
	@Test(priority=2, description="Verify if user can change the date and validate the data in dashboard")
	@Description("Verify if user can change the date and validate the data in dashboard")
	@Severity(SeverityLevel.CRITICAL)
	public void openSpecificDashboard() throws Exception {
		loginApp(getProp("baseUrl"));
		verifySearchPageDisplayed();
		navigateToDashboard(getProp("dashboardUrl"));
		verifyDataDisplayed("Test Project");
		changeDate(getProp("fromDateTime"), getProp("toDateTime"));
		verifyDate(getProp("fromDateTime"), getProp("toDateTime"));
		verifyDataDisplayed("Test Project");
		signOut();
		
	}
	
	
	@Test(priority=3, description="Verify if user can clone the current dashboard and validate the data")
	@Description("Verify if user can clone the current dashboard and validate the data")
	@Severity(SeverityLevel.NORMAL)
	public void dateOperation() throws Exception {
		loginApp(getProp("baseUrl"));
		verifySearchPageDisplayed();
		navigateToDashboard(getProp("dashboardUrl"));
		cloneCurrentDashboard("MohanaTest1");
		verifyDataDisplayed("MohanaTest1");
		signOut();
	}
	
	
	@Test(priority=4, description="Verify if user can delete the current dashboard")
	@Description("Verify if user can delete the current dashboard")
	@Severity(SeverityLevel.NORMAL)
	public void copyCurrentDashboard() throws Exception {
		loginApp(getProp("baseUrl"));
		verifySearchPageDisplayed();
		navigateToDashboard(getProp("dashboardUrl"));
		cloneCurrentDashboard("MohanaTest1");
		verifyDataDisplayed("MohanaTest1");
		deleteDashboard("New Dashboard");
		signOut();
	}
	
	

	@AfterTest
	public void teardown()	{		
		driver.close();		
		
	}
	
	
	
	
	
	

	
}

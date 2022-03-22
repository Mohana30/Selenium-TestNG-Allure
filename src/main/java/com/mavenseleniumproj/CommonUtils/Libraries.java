package com.mavenseleniumproj.CommonUtils;

import java.io.File;
import java.time.Duration;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;

public class Libraries {

	public WebDriver driver;
	public Properties prop;
	private String currentWorkingDirectory;

	public WebDriver initializeDriver() throws Exception {

		String browserName = getProp("browserName");

		if (browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", currentWorkingDirectory
					+ "/src/main/java/com/mavenseleniumproj/resources/drivers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
		}

		else if (browserName.contains("edge")) {
			System.setProperty("webdriver.edge.driver", currentWorkingDirectory
					+ "/src/main/java/com/mavenseleniumproj/resources/drivers/msedgedriver.exe");
			EdgeOptions options = new EdgeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new EdgeDriver(options);
		}

		else {
			throw new Exception("Invalid Browser Type" + browserName);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}

	public String getProp(String property) throws Exception {
		currentWorkingDirectory = System.getProperty("user.dir");
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				currentWorkingDirectory + "\\src\\main\\java\\com\\mavenseleniumproj\\resources\\envConfig.properties");
		prop.load(fis);
		return prop.getProperty(property);
	}

	public void navigateToUrl(String url) throws Exception {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(getProp("pageLoadTimeout"))));
		driver.get(url);
	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}

	public Boolean waitForElementPresence(WebDriver driver, By selector) throws Exception {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(Integer.parseInt(getProp("pageLoadTimeout"))))
					.ignoring(NoSuchElementException.class).ignoring(WebDriverException.class);
			wait.until(ExpectedConditions.presenceOfElementLocated(selector));
		} catch (TimeoutException e) {
			return false;
		}
		return true;
	}
	
	public void implicitlyWait(WebDriver driver) throws Exception {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(getProp("pageLoadTimeout"))));
    }

	public boolean waitForElementToBeClickable(WebDriver driver, By selector) throws Exception {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(Integer.parseInt(getProp("pageLoadTimeout"))))
					.ignoring(NoSuchElementException.class).ignoring(WebDriverException.class);
			wait.until(ExpectedConditions.elementToBeClickable(selector));
		} catch (TimeoutException e) {
			return false;
		}
		return true;
	}
	
	public Boolean waitForElementToDisappear(WebDriver driver, By locator) throws Exception {
		try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(Integer.parseInt(getProp("pageLoadTimeout")))).
            		ignoring(NoSuchElementException.class).ignoring(WebDriverException.class);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
	    }

	public void teardown() {
		driver.quit();
	}
}

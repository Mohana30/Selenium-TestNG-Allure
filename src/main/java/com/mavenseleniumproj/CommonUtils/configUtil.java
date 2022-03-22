package com.mavenseleniumproj.CommonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class configUtil {
	
	public static Properties readProperties(String filename) throws Exception {
		
		filename = filename.trim();
		InputStream fileReader = new FileInputStream(filename);
		Properties property = new Properties();
		property.load(fileReader);
		return property;
		
	}
		
	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;

	}

}
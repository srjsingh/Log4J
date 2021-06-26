package com.tests;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
	
	// What is log? : Capturing info/activities at the time of program execution
	// Types of logs:
	//1. info
	//2. warn
	//3. error
	//4. fatal
	
	//how to generate the logs? : use Apache API (log4j jar)
	// How it works? it reads log 4j configuration from log4j.properties file
	//where to create: create inside resources folder
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Suraj\\Desktop\\Selenium Jar\\Chrome Driver\\chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();

        prefs.put("profile.default_content_setting_values.notifications", 2);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
		
		driver = new ChromeDriver(options);
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		
		driver.get("http://yatra.com");
	}
	
	@Test(priority = 1)
	public void yatraTitleTest() {
		
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Flight, Cheap Air Tickets , Hotels, Holiday, Trains Package Booking - Yatra.com");
	}
	
	@Test(priority = 2)
	public void yatraLogoTest() {
		
		boolean b = driver.findElement(By.xpath("//a[@class='logo']/i")).isDisplayed();
		Assert.assertTrue(b);
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}

}

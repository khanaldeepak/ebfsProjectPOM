package com.ebfs.test;

import org.testng.annotations.Test;

import com.ebfs.pages.HomePage;
import com.ebfs.pages.SignInPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import java.io.File;

public class SmokeTest {
	ExtentReports report;
	ExtentTest logger;
	WebDriver driver;
	
 
	@Test (priority=1, enabled=true)
  public void createAccount() {
		logger = report.startTest("Verify Invalid Email");
		
		HomePage homePage = new HomePage(driver);
		
		
		homePage.clickSignIn();
	
		SignInPage signInPage = new SignInPage(driver);
		logger = report.startTest("SIGN IN");
		
		Assert.assertEquals(signInPage.createAccountTitle(), "CREATE AN ACCOUNT");
		signInPage.createNewAccount("rab");
		logger.log(LogStatus.PASS, "Invalid Email Verified");
  }
 
	/*
	@Test
	  public void logIn() {
			HomePage homePage = new HomePage(driver);
			homePage.clickSignIn();
			SignInPage signInPage = new SignInPage(driver);
			signInPage.loginIn("rab@rab.com", "password");
	  }
  */
  @BeforeTest
  public void openBrowser() {
		report = new ExtentReports("C:\\Report\\LearnAutomation.html",true);
		
	  driver = new FirefoxDriver();
	  driver.get("http://ebfs.bruteforcesolution.net/ebfs/index.php");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @AfterMethod
  public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			//String screenshot_path = Utility.captureScreenshot(driver, result.getName());
			File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenshot_path = "./Snapshot/" + scrfile.getName();
			String image = logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.FAIL, "Title verification", image);
		}

		report.endTest(logger);
		report.flush();

		driver.get("C:\\Report\\LearnAutomation.html");
		
  }
  
  @AfterTest
  public void aftermeth(){
	  
	  driver.get("C:\\Report\\LearnAutomation.html");
  }

}
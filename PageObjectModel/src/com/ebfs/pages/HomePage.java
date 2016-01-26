package com.ebfs.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	public WebDriver driver;
	
	By signInLink = By.xpath("//*[@id='header']/div[2]/div/div/nav/div[1]/a");
	
	public HomePage(WebDriver argDriver){
		driver = argDriver;
	}
	
	public void clickSignIn(){
		driver.findElement(signInLink).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	

}
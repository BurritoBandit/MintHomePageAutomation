package com.mintAutomation.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

import org.junit.Assert;


public class MintFrontPageAutomation {

	public static void main(String[] args) throws InterruptedException {
WebDriver driver;
		
		//System.setProperty("webdriver.gecko.driver", "Resources/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
		//System.setProperty("webdriver.ie.driver", "Resources/IEDriverServer.exe");
		
		
		//driver = new FirefoxDriver();
		driver = new ChromeDriver(); 
		//driver = new InternetExplorerDriver();
				
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		//Open Mint home page
		driver.get("https://www.mint.com");
		
		//Assert the title appears as expected
		Assert.assertEquals("Mint: Money Manager, Bill Pay, Credit Score, Budgeting & Investing",driver.getTitle());
		
		driver.findElement(By.className("btn-primary")).click();
		//The "sign up free" button actually had a compound class name, which isn't supported by Selenium. I had to choose one of the class names
		//try it to see if it would click. "btn-primary" worked
		
		//Assert we are taken to the Start Here (account creation page) Note: I had to set a wait.until because the page title changes from mint.com to Mint > Start Here
		wait.until(ExpectedConditions.titleContains("Mint > Start Here"));
		Assert.assertEquals("Mint > Start Here",driver.getTitle());
		
		//fill out sign-up form
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ius-email"))); //wait needed because selenium looks for the elements before the page is finished rendering
		//Your Email
		driver.findElement(By.id("ius-email")).clear();
		driver.findElement(By.id("ius-email")).sendKeys("mint_automation@gmail.com");
		
		//Confirm Email
		driver.findElement(By.id("ius-confirm-email-address")).clear();
		driver.findElement(By.id("ius-confirm-email-address")).sendKeys("mint_automation@gmail.com");
		
		//Phone
		driver.findElement(By.id("ius-mobile-phone")).clear();
		driver.findElement(By.id("ius-mobile-phone")).sendKeys("555-555-5555");
		driver.findElement(By.id("ius-sign-up-label-sms")).isSelected();
		
		//Password
		driver.findElement(By.id("ius-password")).clear();
		driver.findElement(By.id("ius-password")).sendKeys("MintAutomation1234!");
		//assert password minimum complexity requirements
		Assert.assertEquals("Your password is STRONG.", driver.findElement(By.className("ius-password-validator-message-success")).getText());
		
		//Confirm Password
		driver.findElement(By.id("ius-confirm-password")).clear();
		driver.findElement(By.id("ius-confirm-password")).sendKeys("MintAutomation1234!");
		driver.findElement(By.className("ius-check")).isSelected();
		
		//Click Remember me
		driver.findElement(By.id("ius-sign-up-label-remember")).click();
		
		//I will back out as to not create a fake account
		driver.navigate().back();
		//Assertion to verify I'm back at the home page
		Assert.assertEquals("Mint: Money Manager, Bill Pay, Credit Score, Budgeting & Investing",driver.getTitle());

		//Navigate to How It Works
		driver.findElement(By.linkText("HOW IT WORKS")).click();
		wait.until(ExpectedConditions.titleContains("Mint: Money Manager, Personal Finance, and Budgeting"));
		Assert.assertEquals("Mint: Money Manager, Personal Finance, and Budgeting",driver.getTitle());
		driver.navigate().back();
		
    	//Navigate to Find Savings
		driver.findElement(By.linkText("FIND SAVINGS")).click();
		wait.until(ExpectedConditions.titleContains("Mint: Best Credit Cards, Credit Card Offers"));
		Assert.assertEquals("Mint: Best Credit Cards, Credit Card Offers",driver.getTitle());
		driver.navigate().back();
		
		//Navigate to Support
		driver.findElement(By.linkText("SUPPORT")).click();
		wait.until(ExpectedConditions.titleContains("Mint Support, Mint Bills Support"));
		Assert.assertEquals("Mint Support, Mint Bills Support",driver.getTitle());
		driver.navigate().back();
		
		//Navigate to Blog
		driver.findElement(By.linkText("BLOG")).click();
		wait.until(ExpectedConditions.titleContains("MintLife Blog | Personal Finance Advice & News"));
		Assert.assertEquals("MintLife Blog | Personal Finance Advice & News",driver.getTitle());
		driver.navigate().back();

		//Navigate to News & Press Releases
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("News & Press Releases")));
		driver.findElement(By.linkText("News & Press Releases")).click();
		wait.until(ExpectedConditions.titleContains("Mint News: Latest Updates and Press Releases"));
		Assert.assertEquals("Mint News: Latest Updates and Press Releases",driver.getTitle());
		driver.navigate().back();
		
		//Navigate to Mint app on Apple store
		driver.findElement(By.className("icn-app-store")).click();
		wait.until(ExpectedConditions.titleContains("Mint: Personal Finance, Budget, Bills & Money on the App Store"));
		Assert.assertEquals("Mint: Personal Finance, Budget, Bills & Money on the App Store",driver.getTitle());
		driver.navigate().back();
		
		//Navigate to Mint app on the Google Play store
		driver.findElement(By.className("icn-google-play")).click();
		wait.until(ExpectedConditions.titleContains("Mint: Budget, Bills, Finance - Android Apps on Google Play"));
		Assert.assertEquals("Mint: Budget, Bills, Finance - Android Apps on Google Play",driver.getTitle());
		driver.navigate().back();
				
		//Navigate to Login page 
		driver.findElement(By.linkText("Log In")).click();
		Assert.assertEquals("https://mint.intuit.com/login.event?referrer=direct&soc=&utm=&test=&cta=nav_login_dropdown", driver.getCurrentUrl());
        
		//Navigate to home page using the Mint Icon on the upper left corner of the page
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logo-link")));
		driver.findElement(By.id("logo-link")).click();
		wait.until(ExpectedConditions.titleContains("Mint: Money Manager, Bill Pay, Credit Score, Budgeting & Investing"));
		Assert.assertEquals("Mint: Money Manager, Bill Pay, Credit Score, Budgeting & Investing",driver.getTitle());
		
		//Navigate to "How It Works -> Track & pay bills" using the "Learn More" link under "Effortlessly stay on top of bills"
		driver.findElement(By.linkText("Learn more")).click();
		  //Switch to new tab
		System.out.println(driver.getWindowHandles());
	      //switch selenium's focus to newly opened tab
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));
				
          //assert user is on the right page
	    	wait.until(ExpectedConditions.titleContains("Mint: Budget, Budgeting Goals"));
	    	Assert.assertEquals("Mint: Budget, Budgeting Goals",driver.getTitle());
	    	Assert.assertEquals("https://www.mint.com/how-mint-works/bills#toc", driver.getCurrentUrl());
	    	driver.close();
	      //switch selenium's focus to initial tab
	    	driver.switchTo().window(tabs2.get(0));
		
		//Navigate to "How It Works -> Security" using the "Learn more about Security" link under "Get started simply & securely"
		driver.findElement(By.linkText("Learn more about Security")).click();
		wait.until(ExpectedConditions.titleContains("Mint: Safe & Secure"));
		Assert.assertEquals("Mint: Safe & Secure",driver.getTitle());
		Assert.assertEquals("https://www.mint.com/how-mint-works/security", driver.getCurrentUrl());
		driver.close();
		
	}

}

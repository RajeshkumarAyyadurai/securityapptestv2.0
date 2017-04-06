package com.tests.project.security;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class DDoS_SecurityTests {
	private static WebDriver driver = null;
	//private static HtmlUnitDriver driver = null;
	//private String URL = "http://ec2-54-254-182-212.ap-southeast-1.compute.amazonaws.com:8080/security";
	private String URL = "http://ec2-54-254-182-212.ap-southeast-1.compute.amazonaws.com:8080/bamboo";
	private String userName = "admin";
	private String password = "admin";
	private String alias = "ad";
	public WebDriverWait wait;
	
	public String generateAccName(){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		return "Test"+dateFormat.format(new Date());
	}
	
	@Test
	public void testSubmitDDoSConfiguration() throws InterruptedException{
		// variables
		String accountName = generateAccName();
		String email = accountName+"@gmail.com";
		
		String projectBaseDir = System.getProperty("user.dir");
		System.setProperty("phantomjs.binary.path", projectBaseDir+"/lib/drivers/phantomjs");
		driver = new PhantomJSDriver();
		//driver = new HtmlUnitDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		
		// login to application
		driver.findElement(By.xpath("//h2[text()='Please sign in']")).isDisplayed();
		driver.findElement(By.xpath("//input[@id='inputEmail']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='signIn']")).click();
	}
	
	@AfterSuite
	public void killBrowser(){
		driver.quit();
	}
}

package com.selenium.test;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.util.concurrent.Service;
import com.selenium.dataProvider.LoginDataProvider;
import com.utils.DriverUtils;

public class Open {
	InternetExplorerDriverService service = new InternetExplorerDriverService.Builder().usingAnyFreePort()
			.usingDriverExecutable(new File("C:/driver/IEDriverServer.exe")).build();

	//Service service2= DriverUtils.initService();
	WebDriver driver=DriverUtils.getDriver();
	
	@BeforeMethod
	public void OpenBrower() {
		try {
			service.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setProperty("browser.type", "ie");
	}
	
	@Test(dataProviderClass=LoginDataProvider.class, dataProvider="loginData")
	public void Login(String username,String password) {
		
		driver.get("http://localhost/ECShop/");
		driver.findElement(By.partialLinkText("登录")).click();
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		
		
		
		}
	@AfterMethod
	public void quit() {
		driver.quit();
		service.stop();
	}

	

}

package com.selenium.screenShot;

import org.testng.annotations.Test;

import com.selenium.dataProvider.LoginDataProvider;
import com.selenium.pageObject.IndexPage;
import com.selenium.pageObject.LoginPage;
import com.utils.TestBase;

public class TestLoginScreenShot extends TestBase{
	@Test
	public void test() throws InterruptedException {
			driver.get("http://localhost/ECShop/");
			IndexPage indexPage=new IndexPage(driver);
			indexPage.loginLink_click();
			LoginPage loginPage=new LoginPage(driver);
			loginPage.input_username("nyf");
			loginPage.input_pwd("a123456");
			loginPage.submit_click();
//	        System.out.println(driver.getPageSource());
			loginPage.check_login_result("登录成功");
			Thread.sleep(3000);
	}

}

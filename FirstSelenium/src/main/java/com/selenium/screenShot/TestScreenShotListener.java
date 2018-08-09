package com.selenium.screenShot;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.bcel.generic.NEW;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestScreenShotListener extends TestListenerAdapter{
	@Override
	public void onTestFailure(ITestResult tr) {
		// TODO Auto-generated method stub
		try {
			Field field=tr.getTestClass().getRealClass().getField("driver");//通过反射机制来获取driver成员变量
			WebDriver driver=(WebDriver)field.get(tr.getInstance());//获取当前测试的driver对象
			System.out.println(tr.getInstance());//打印所测试类的对象
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);//获取截图的file
			//创建目录
			File screenShotDir=new File("screenShots");
			if(!screenShotDir.exists()&&!screenShotDir.isDirectory()) {
				screenShotDir.mkdirs();
			}
			
			String classname=tr.getClass().getName();//获取类名
			String methodName=tr.getName();//获取方法名
			SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss_SSS");//SSS精确到毫秒
			String time=dateformat.format(new Date());//具体执行时间
			file.renameTo(new File(screenShotDir,classname+"_"+methodName+"_"+time+".png"));//将file设置文件名称
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.onTestFailure(tr);
	}

}

package test.apptest.chuangye;


import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;






import service.AppCommonService;
import service.InitialService;
import service.WebCommonService;
import common.frame.helper.Utils;
import common.frame.test.BaseTest;
import common.utils.UrlsOfPre;



public class AppChuangyeReleaseLoan extends BaseTest {

	@Autowired
	private InitialService Initial;
	@Autowired
	private AppCommonService appCommonService;	
//	@Autowired
//	private WebCommonService webCommonService;

	private AppiumDriver driver;
//	private WebDriver webDriver;


	@BeforeClass
	public void beforeClass() {
	}


	@Test(enabled = true, dataProvider = "testData",description="发布贷款",timeOut=600)
	public void chuangyeEnterIncubator(Map<String, String> datadriven)throws Exception {
		
		String changyeApkName = datadriven.get("changyeApkName");//创业者apk
		
		logger.info("APP "+datadriven.get("version")+"---发布贷款测试开始---");
		
		logger.info("启动创业者app");
		driver = Initial.appiumAndroidChuangyeSetUp(driver, changyeApkName);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		appCommonService.logoutForApp(driver);
//
//		logger.info("登录创业者app");
//		driver = appCommonService.loginForApp(driver,datadriven.get("changyeUserName"),datadriven.get("chuangyePassword"));
		
		//点击进入发布贷款页
		logger.info("一融-进入发布贷款页");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("一融"))).click();
	    new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我要贷款"))).click();
	    new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("发布贷款项目"))).click();
		
		logger.info("填写并提交申请");
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.name("请输入金额"))).sendKeys("500");
		driver.findElement(By.name("请输入1-120")).sendKeys("12");	
		driver.findElement(By.name("请选择")).click();
		driver.findElement(By.name("原材料采购")).click();
		driver.findElement(By.name("请选择")).click();
		driver.findElement(By.name("担保")).click();
		driver.findElement(By.name("个人担保")).click();
		driver.findElement(By.name("本公司股东担保")).click();
		driver.findElement(By.name("请输入")).sendKeys("XLH");
		driver.findElement(By.name("请选择")).click();
		driver.findElement(By.name("国有")).click();
		appCommonService.swipeToDown(driver);
		driver.findElement(By.name("请输入")).sendKeys("XLH");
		driver.findElement(By.name("请选择")).click();
		driver.findElement(By.name("Dec")).click();
		driver.findElement(By.name("01")).click();
		driver.findElement(By.name("2015")).click();
		driver.findElement(By.name("确定")).click();
		driver.findElement(By.name("请选择")).click();
		swipeleft(driver);
		Thread.sleep(1500);
		swipemiddum(driver);
		Thread.sleep(1500);
		swiperight(driver);
		Thread.sleep(1500);
		driver.findElement(By.name("确定")).click();
		driver.findElement(By.name("请选择")).click();
		driver.findElement(By.name("房地产")).click();
		driver.findElement(By.name("请输入")).sendKeys("50");
		driver.findElement(By.name("请输入")).sendKeys("50");
		driver.findElement(By.name("请输入")).sendKeys("50");
		appCommonService.swipeToDown(driver);
		driver.findElement(By.name("请输入")).sendKeys("50");
		driver.findElement(By.name("请输入姓名")).sendKeys("ceshi");
		driver.findElement(By.name("请输入手机号")).sendKeys("13900000000");
		driver.findElement(By.name("立即提交")).click();
		
		logger.info("项目发布成功，返回首页");
		driver.findElement(By.name("返回首页")).click();
		logger.info("去我的-贷款项目,校验");
		driver.findElement(By.name("我的")).click();
		driver.findElement(By.name("贷款项目")).click();
		
		try {
			
			new WebDriverWait(driver,40).until(ExpectedConditions.visibilityOfElementLocated(By.id("apply_company_name")));
			logger.info("校验成功，返回并退出");
			new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("title_back_img"))).click();
			appCommonService.logoutForApp(driver);
			driver.quit();
			logger.info("APP "+datadriven.get("version")+"---发布贷款项目测试结束---");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			logger.info("校验失败");
			driver.quit();
			e.printStackTrace();
			
		}
		
	}
		
		private void swipeleft (AppiumDriver driver) {
			// TODO Auto-generated method stub
			
	        int width = driver.manage().window().getSize().width;
	        int height = driver.manage().window().getSize().height;
	        driver.swipe(width/6,height*5/6, width/6,height*2/3, 1000);//向下滑动，间隔1s        	
		}
		
		private void swipemiddum (AppiumDriver driver) {
			// TODO Auto-generated method stub
			
	        int width = driver.manage().window().getSize().width;
	        int height = driver.manage().window().getSize().height;
	        driver.swipe(width/2,height*5/6, width/2,height*8/15, 1000);//向下滑动，间隔1s        	
		}
		
		private void swiperight (AppiumDriver driver) {
			// TODO Auto-generated method stub
			
	        int width = driver.manage().window().getSize().width;
	        int height = driver.manage().window().getSize().height;
	        driver.swipe(width*5/6,height*5/6, width*5/6,height*2/3, 1000);//向下滑动，间隔1s        	
		}
		

	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

	
	@AfterClass
	public void afterClass() {
	}

}

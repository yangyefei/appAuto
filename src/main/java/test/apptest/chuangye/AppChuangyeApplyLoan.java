package test.apptest.chuangye;


import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.text.SimpleDateFormat;

import net.sf.saxon.functions.Substring;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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



public class AppChuangyeApplyLoan extends BaseTest {

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


	@Test(enabled = true, dataProvider = "testData",description="申请贷款",timeOut=480000)
	public void chuangyeApplyLoan(Map<String, String> datadriven)throws Exception {
		
		String apkPathOfChuangye = datadriven.get("apkPathOfChuangye");
		
		logger.info("APP "+datadriven.get("version")+"---申请贷款测试开始---");
		
		logger.info("启动创业者app");
		driver = Initial.appiumAndroidChuangyeSetUp(driver, apkPathOfChuangye);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		appCommonService.logoutForApp(driver);

		logger.info("登录创业者app");
		driver = appCommonService.loginForApp(driver,datadriven.get("changyeUserName"),datadriven.get("chuangyePassword"));
		
		//点击进入发布贷款页
		logger.info("一融-进入贷款产品列表页");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("一融"))).click();
	    new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我要贷款"))).click();
	    new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("申请贷款产品"))).click();
	    logger.info("选择贷款产品");
	    new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name(datadriven.get("loanProducts")))).click();
		
		logger.info("填写并提交申请");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("申请贷款"))).click();
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.name("请输入金额"))).sendKeys("500");
		driver.findElement(By.name("请输入1-120")).sendKeys("22");	
		driver.findElement(By.name("请选择")).click();
		driver.findElement(By.name("扩大经营")).click();
		driver.findElement(By.name("请选择")).click();
		driver.findElement(By.name("信用")).click();
		driver.findElement(By.name("企业流水")).click();
		List<WebElement> list=driver.findElements(By.id("title"));
		WebElement target = list.get(2);
		target.click();
		driver.findElement(By.name("请输入")).sendKeys("10");
		driver.findElement(By.name("请输入")).sendKeys("XLH");
		appCommonService.swipeToDown(driver);
		driver.findElement(By.name("请选择")).click();
		dateswipeleft(driver);
		Thread.sleep(1500);
		appCommonService.swipeToDown(driver);
		Thread.sleep(1500);
		dateswiperight(driver);
		Thread.sleep(1500);
		driver.findElement(By.name("确定")).click();
		driver.findElement(By.name("请选择")).click();
		swipeleft(driver);
		Thread.sleep(1500);
		appCommonService.swipeToDown(driver);
		Thread.sleep(1500);		
		swiperight(driver);
		Thread.sleep(1500);
		driver.findElement(By.name("确定")).click();	
		driver.findElement(By.name("请选择")).click();
		driver.findElement(By.name("商业贸易")).click();
		driver.findElement(By.name("请输入")).sendKeys("50");
		driver.findElement(By.name("请输入")).sendKeys("50");
		driver.findElement(By.name("请输入")).sendKeys("50");
		appCommonService.swipeToDown(driver);
		driver.findElement(By.name("请输入")).sendKeys("50");
		driver.findElement(By.name("请输入姓名")).sendKeys("ceshi");
		driver.findElement(By.name("请输入手机号")).sendKeys("13900000000");
		driver.findElement(By.name("立即提交")).click();
		//获取申请时间
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = date.format(new Date());
		System.out.println(time);
		
		logger.info("贷款申请成功，返回首页");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("返回首页"))).click();
		logger.info("去我的-申请进度,校验");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("申请进度"))).click();
		
		try {
			
			new WebDriverWait(driver,40).until(ExpectedConditions.visibilityOfElementLocated(By.name(datadriven.get("loanProducts"))));
			new WebDriverWait(driver,40).until(ExpectedConditions.visibilityOfElementLocated(By.name(time)));
			logger.info("校验成功，返回并退出");	
			new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("title_back_img"))).click();
			appCommonService.logoutForApp(driver);
			driver.quit();
			logger.info("APP "+datadriven.get("version")+"---申请贷款测试结束---");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			logger.info("校验失败");
			driver.quit();
			e.printStackTrace();
			
		}
		
	}
		

		private void swipeleft (AppiumDriver driver) {
			
	        int width = driver.manage().window().getSize().width;
	        int height = driver.manage().window().getSize().height;
	        driver.swipe(width/6,height*5/6, width/6,height*2/3, 1000);//向下滑动，间隔1s  
	        
		}
			
		private void swiperight (AppiumDriver driver) {
			
	        int width = driver.manage().window().getSize().width;
	        int height = driver.manage().window().getSize().height;
	        driver.swipe(width*5/6,height*5/6, width*5/6,height*2/3, 1000);//向下滑动，间隔1s    
	        
		}
		
		private void dateswipeleft (AppiumDriver driver) {
			
	        int width = driver.manage().window().getSize().width;
	        int height = driver.manage().window().getSize().height;
	        driver.swipe(width*3/10,height*5/6, width*3/10,height*99/100, 1000);//向上滑动，间隔1s     
	        
		}
		
		private void dateswiperight (AppiumDriver driver) {
			
	        int width = driver.manage().window().getSize().width;
	        int height = driver.manage().window().getSize().height;
	        driver.swipe(width*7/10,height*5/6, width*7/10,height*99/100, 1000);//向上滑动，间隔1s      
	        
		}

	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

	
	@AfterClass
	public void afterClass() {
	}

}

package test.apptest.chuangye;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

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



public class AppChuangyeBuyService extends BaseTest {

	@Autowired
	private InitialService Initial;
	@Autowired
	private AppCommonService appCommonService;	
	@Autowired
	private WebCommonService webCommonService;

	private AppiumDriver driver;
	private WebDriver webDriver;


	@BeforeClass
	public void beforeClass() {
	}


	@Test(enabled = true, dataProvider = "testData",description="购买服务",timeOut=480000)
	public void chuangyeBuyService(Map<String, String> datadriven)throws Exception {
		
		String apkPathOfChuangye = datadriven.get("apkPathOfChuangye");
		
		logger.info("APP "+datadriven.get("version")+"---购买服务测试开始---");
		
		logger.info("启动创业者app");
		driver = Initial.appiumAndroidChuangyeSetUp(driver, apkPathOfChuangye);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		appCommonService.logoutForApp(driver);

		logger.info("登录创业者app");
		driver = appCommonService.loginForApp(driver,datadriven.get("changyeUserName"),datadriven.get("chuangyePassword"));
		
		//点击进入服务列表页
		logger.info("一融-进入服务列表页并选择购买产品");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("一融"))).click();
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("企业服务"))).click();
	    new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name(datadriven.get("service")))).click();
		
		logger.info("填写并立即购买");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("请选择"))).click();	
		driver.findElement(By.name("浙江")).click();
		driver.findElement(By.name("台州")).click();
		driver.findElement(By.name("确定")).click();
		driver.findElement(By.name("请选择")).click();
		driver.findElement(By.name("打发第三方第三方")).click();
		appCommonService.swipeToDown(driver);
		driver.findElement(By.name("立即购买")).click();
		Thread.sleep(3000);
		appCommonService.swipeToDown(driver);
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("同意服务协议并下单"))).click();
		//获取下单时间
		SimpleDateFormat date = new SimpleDateFormat("MM-dd HH:mm");
		String time = date.format(new Date());
		System.out.println(time);
		Thread.sleep(3000);
		
		logger.info("去支付");
		clickzhifu();
		Thread.sleep(5000);
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("付款"))).click();
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("返回首页 Link"))).click();
		driver.quit();
		
		logger.info("启动并登录后台系统");
	    webDriver = Initial.browserOfFirefoxSetUp(webDriver);
//	    webDriver = Initial.browserOfChromeSetUp(webDriver);
	    webDriver.manage().window().maximize();
	    webDriver.get(UrlsOfPre.BackGroundSystem.getUrl());
	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.id("form_user"))).sendKeys(datadriven.get("backUserName"));
	    webDriver.findElement(By.id("form_password")).sendKeys(datadriven.get("backPassword"));
	    webDriver.findElement(By.className("btn-submit")).click();
	
	    logger.info("进入一融邦-订单管理管理-订单列表菜单");
	    new WebDriverWait(webDriver,120).until(ExpectedConditions.elementToBeClickable(By.linkText("一融邦"))).click();
	    new WebDriverWait(webDriver,120).until(ExpectedConditions.elementToBeClickable(By.id("li_10"))).click();
	    new WebDriverWait(webDriver,120).until(ExpectedConditions.elementToBeClickable(By.linkText("订单列表"))).click();
	    Thread.sleep(3000);
	    
	    logger.info("分配供应商并确认服务完成");
	    new WebDriverWait(webDriver,120).until(ExpectedConditions.elementToBeClickable(By.linkText("分配供应商"))).click();
	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.s-sopop-btn"))).click();
	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.s-sopop-btn"))).click();
	    webDriver.findElement(By.linkText("确认服务完成")).click();
	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.s-sopop-btn"))).click();
	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.s-sopop-btn"))).click();
	    
	    logger.info("退出后台系统");
	    webDriver = webCommonService.logoutOfBackgroundSystem(webDriver);
	    webDriver.quit();
		
		logger.info("再次打开APP，查看订单");
		driver = Initial.appiumAndroidChuangyeSetUp(driver, apkPathOfChuangye);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		appCommonService.swipeToDown(driver);
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("服务订单"))).click();	
		try {
			
			new WebDriverWait(driver,40).until(ExpectedConditions.visibilityOfElementLocated(By.name(datadriven.get("service"))));
			new WebDriverWait(driver,40).until(ExpectedConditions.visibilityOfElementLocated(By.name(time)));
			new WebDriverWait(driver,40).until(ExpectedConditions.visibilityOfElementLocated(By.name("服务完成")));
			logger.info("校验成功，返回并退出");	
			new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("title_back_img"))).click();
			appCommonService.logoutForApp(driver);
			driver.quit();
			logger.info("APP "+datadriven.get("version")+"---购买服务测试结束---");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			logger.info("校验失败");
			driver.quit();
			e.printStackTrace();
			
		}
		
	}
	
	
	private void clickzhifu () {
		
		int x =driver.manage().window().getSize().width;
		int y =driver.manage().window().getSize().height;

		TouchAction  touchAction =new TouchAction(driver);
		touchAction.press(x/2, y*19/20).release().perform();
        
	}
		

	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

	
	@AfterClass
	public void afterClass() {
	}

}

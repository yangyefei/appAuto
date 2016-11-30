package test.apptest.chuangye;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy.ByAccessibilityId;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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



public class AppChuangyeSignUp extends BaseTest {

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


	@Test(enabled = true, dataProvider = "testData",description="活动报名")
	public void chuangyeSignUp(Map<String, String> datadriven)throws Exception {
		
		String changyeApkName = datadriven.get("changyeApkName");//创业者apk
		
		logger.info("APP "+datadriven.get("version")+"---活动报名测试开始---");
		
		logger.info("启动创业者app");
		driver = Initial.appiumAndroidChuangyeSetUp(driver, changyeApkName);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		appCommonService.appLogout(driver);

		logger.info("登录创业者app");
		driver = appCommonService.appLogin(driver,datadriven.get("changyeUserName"),datadriven.get("chuangyePassword"));
		
		//点击进入活动列表页
		logger.info("发现-进入活动列表页");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("发现"))).click();
	    new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("一融活动"))).click();
		
		//选择活动并报名
	    logger.info("选择活动并报名");
		new WebDriverWait(driver,120).until(ExpectedConditions.elementToBeClickable(By.name(datadriven.get("activity")))).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			
			logger.info("点击进入报名页面");
//			new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(ByAccessibilityId("报名 Link")));
			driver.findElementByAccessibilityId("报名 Link").click();
			logger.info("填写并提交报名申请");
			driver.findElementByClassName("android.widget.EditText").sendKeys("ceshi");
			driver.findElementByAccessibilityId("获得 Link").click();
			driver.findElementByAccessibilityId("请输入验证码").sendKeys("000000");
			driver.findElementByAccessibilityId("其他").click();
			
//			driver.findElementByAccessibilityId("公司").click();
//			driver.findElementByAccessibilityId("请输入名称").sendKeys("11111");
			
			driver.findElementByAccessibilityId("提交 Link").click();
			
//			driver.findElementByClassName("android.widget.EditText").sendKeys("ceshi");
//			driver.findElementByAccessibilityId("获得 Link").click();
//			driver.findElement(By.name("请输入验证码")).sendKeys("000000");
//			driver.findElementByAccessibilityId("公司").click();
//			driver.findElement(By.name("请输入名称")).sendKeys("xinlonghang");
//			driver.findElementByAccessibilityId("提交 Link").click();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			logger.info("报名成功");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			logger.info("已报名、报名截止或活动结束");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			logger.info("报名失败");
			e.printStackTrace();
		
		}
		
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("detail_back_view"))).click();
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("title_back_img"))).click();
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		driver = appCommonService.swipeToDown(driver);
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的活动"))).click();
		
		try {

			logger.info("去我的活动页面查看是否有活动");
			new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.name("自动化活动")));
			logger.info("校验成功");
			
		} catch (Exception e) {//没有活动退出APP
			// TODO Auto-generated catch block

			logger.info("活动报名失败");
			e.printStackTrace();
		}
		
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("title_back_img"))).click();
		logger.info("退出当前账号");
		appCommonService.appLogout(driver);
		logger.info("APP "+datadriven.get("version")+"---报名流程测试结束---");
		driver.quit();
		
//		logger.info("启动并登录后台系统");
//	    webDriver = Initial.browserOfFirefoxSetUp(webDriver);
//	    webDriver = Initial.browserOfChromeSetUp(webDriver);
//	    webDriver.manage().window().maximize();
//	    webDriver.get(UrlsOfPre.BackGroundSystem.getUrl());
//	
//	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.id("form_user"))).sendKeys(datadriven.get("backUserName"));
//	    webDriver.findElement(By.id("form_password")).sendKeys(datadriven.get("backPassword"));
//	    webDriver.findElement(By.className("btn-submit")).click();
//	
//	    logger.info("进入营销-活动报名-报名管理菜单");
//	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.linkText("营销"))).click();
//	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.id("li_40"))).click();
//	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.id("li_53"))).click();
//	    Thread.sleep(5000);
//	
//	    logger.info("通过报名");
//	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.linkText("通过"))).click();
//	    
//	    logger.info("退出后台系统");
//	    webDriver = webCommonService.logoutOfBackgroundSystem(webDriver);
//	    webDriver.quit();
//		
//	    logger.info("再次启动创业者app");
//		driver = Initial.appiumAndroidChuangyeSetUp(driver, changyeApkName);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		logger.info("进入助理，查看已报名活动");
//		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("助理"))).click();
//		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("待参加"))).click();
//		
//		try {
//
//			logger.info("去待参加页面查看是否有活动");
//			new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.name("自动化活动")));
//			logger.info("校验成功");
//			
//		} catch (Exception e) {//没有活动退出APP
//			// TODO Auto-generated catch block
//
//			logger.info("待参加中没有活动");
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//			logger.info("活动报名失败");		
//			e.printStackTrace();
		
//		}
//		
//		logger.info("退出登录");
//		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
//		appCommonService.appLogout(driver);
//		logger.info("APP "+datadriven.get("version")+"---报名流程测试结束---");
//		
//		driver.quit();
		
	}
 
	
	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

	
	@AfterClass
	public void afterClass() {
	}

}

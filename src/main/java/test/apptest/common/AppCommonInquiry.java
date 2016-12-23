package test.apptest.common;


import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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



public class AppCommonInquiry extends BaseTest {

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
	

	@Test(enabled = true, dataProvider = "testData", timeOut=600000, description="普通问诊流程")
	public void commonInquiryProcess(Map<String, String> datadriven)throws Exception {
		
		String apkPathOfChuangye = datadriven.get("apkPathOfChuangye");
		String apkPathOfFund = datadriven.get("apkPathOfFund");
		String commonContent = datadriven.get("comments");
		
		logger.info("APP "+datadriven.get("version")+"---普通问诊流程测试开始---");

		logger.info("启动并登录创业者app");
		driver = Initial.appiumAndroidChuangyeSetUp(driver,apkPathOfChuangye);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver = appCommonService.logoutForApp(driver);//先退出登录下		
		driver = appCommonService.loginForApp(driver,datadriven.get("changyeUserName"),datadriven.get("chuangyePassword"));
						
		//点击进入问诊导师列表
		logger.info("首页-企业问诊进入导师列表");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("一融"))).click();
	    new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("企业问诊"))).click();
		
		//选择问诊导师并申请问诊
	    logger.info("选择问诊导师并申请问诊");
		new WebDriverWait(driver,120).until(ExpectedConditions.elementToBeClickable(By.name(datadriven.get("inquiryTutor")))).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info("点击进入申请问诊页面");
		new WebDriverWait(driver,120).until(ExpectedConditions.elementToBeClickable(By.name("申请问诊"))).click();
		logger.info("填写并提交问诊申请");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("融资规划"))).click();
		driver.findElement(By.id("qi_ye_xin_xi_edit")).sendKeys(commonContent);		
		driver = appCommonService.swipeToDown(driver);//向下滑动
		driver.findElement(By.id("wen_ti_xin_xi_edit")).sendKeys(commonContent);
		driver.findElement(By.id("submit_view")).click();
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("进入我的问诊"))).click();
		
		driver.quit();

		//启动投资者app并且登录
		logger.info("启动并登录投资者app");
		driver = Initial.appiumAndroidFundSetUp(driver,apkPathOfFund);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver = appCommonService.logoutForApp(driver);//先退出登录下
		driver = appCommonService.loginForApp(driver,datadriven.get("fundUserName"),datadriven.get("fundPassword"));
		
		logger.info("进入我的问诊");
		driver.findElement(By.name("我的问诊")).click();
		logger.info("接受问诊");
		driver.findElement(By.name("接受问诊")).click();
		driver.quit();
		
/*		//启动创业者app
		driver = Initial.appiumAndroidChuangyeSetUp(driver,apkPathOfChuangye);
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的问诊"))).click();
		//支付费用
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("支付费用"))).click();
		appCommonService.alipay(driver);
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的问诊"))).click();
		driver.quit();*/
		
		logger.info("启动并登录后台系统");
//	    webDriver = Initial.browserOfFirefoxSetUp(webDriver);
	    webDriver = Initial.browserOfChromeSetUp(webDriver);
	    webDriver.manage().window().maximize();
	    webDriver.get(UrlsOfPre.BackGroundSystem.getUrl());
	
	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.id("form_user"))).sendKeys(datadriven.get("backUserName"));
	    webDriver.findElement(By.id("form_password")).sendKeys(datadriven.get("backPassword"));
	    webDriver.findElement(By.className("btn-submit")).click();
	
	    logger.info("进入一融赋-问诊管理-问诊订单菜单");
	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.linkText("一融赋"))).click();
	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.id("li_73"))).click();
	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.id("li_74"))).click();
	    Thread.sleep(5000);
	
	    logger.info("确认到账");
	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.linkText("确认到账"))).click();
	    webDriver.findElement(By.id("add_freeReason")).sendKeys(commonContent);
	    webDriver.findElement(By.linkText("提交")).click();
	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.s-sopop-btn"))).click();
	    
	    logger.info("上传报告");
	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.linkText("上传报告"))).click();	    
	    webDriver = webCommonService.uploadFilesOfBackgroundSystem(webDriver, datadriven.get("uploadFilePath"));
	    
	    logger.info("退出后台系统");
	    webDriver = webCommonService.logoutOfBackgroundSystem(webDriver);
	    webDriver.quit();
	    
		//再次启动投资者app
		logger.info("再次启动之前已经登录的投资者app");
		driver = Initial.appiumAndroidFundSetUp(driver,apkPathOfFund);
		
		logger.info("进入投资者我的-我的问诊页面");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的问诊"))).click();		
	    	
		try {

			logger.info("去待问诊页面校验等候问诊状态和订单");
			new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("待问诊"))).click();
			new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.name("等候问诊")));
			new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.name(Utils.getCurrentDate())));//和当前日期比较，检查是否可以找到当天下的订单
			logger.info("校验成功");
			
		} catch (Exception e) {//检查失败后创业者和投资者app都退出登录
			// TODO Auto-generated catch block

			logger.info("校验失败，进行退出登录");
			logoutOfcurrentApp();
			
			driver = Initial.appiumAndroidChuangyeSetUp(driver, apkPathOfChuangye);
			logoutOfAnotherApp();
			
			logger.info("APP "+datadriven.get("version")+"---校验失败，问诊流程测试结束---");
			
			e.printStackTrace();
		}

		driver.quit();

		logger.info("再次启动创业者app");
		driver = Initial.appiumAndroidChuangyeSetUp(driver,apkPathOfChuangye);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		logger.info("进入我的问诊");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的问诊"))).click();
//		driver = appCommonService.alipay(driver);
		logger.info("去待问诊页面确认服务");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("待问诊"))).click();
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("确认服务"))).click();
		
		logger.info("对服务进行评价");
		driver = appCommonService.commentSubmit(driver, commonContent);
		
		try {

			logger.info("去成功页面校验状态和订单");
			new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("已成功"))).click();
			new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.name("已评价")));
			new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.name(Utils.getCurrentDate())));//和当前日期比较，检查是否可以找到当天下的订单
			logger.info("校验成功");
			
		} catch (Exception e) {//检查失败后创业者和投资者app都退出登录
			// TODO Auto-generated catch block

			logger.info("校验失败，进行退出登录");
			logoutOfcurrentApp();
			
			driver = Initial.appiumAndroidFundSetUp(driver,apkPathOfFund);
			logoutOfAnotherApp();
			
			logger.info("APP "+datadriven.get("version")+"---校验失败，问诊流程测试结束---");
			
			e.printStackTrace();
		}
		
		
		logoutOfcurrentApp();
		
		logger.info("最后启动投资者app");
		driver = Initial.appiumAndroidFundSetUp(driver,apkPathOfFund);
		logger.info("进入我的问诊");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的问诊"))).click();
		
		try {

			logger.info("去成功页面校验");
			new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("已成功"))).click();
			new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.name("服务完成")));
			new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.name(Utils.getCurrentDate())));//和当前日期比较，检查是否可以找到当天下的订单
			logger.info("校验成功");
		} catch (Exception e) {//检查失败后创业者和投资者app都退出登录
			// TODO Auto-generated catch block

			logger.info("校验失败，进行退出登录");
			logoutOfcurrentApp();
			
			logger.info("APP "+datadriven.get("version")+"---校验失败，问诊流程测试结束---");
			
			e.printStackTrace();
		}
		
		logoutOfcurrentApp();
		logger.info("APP "+datadriven.get("version")+"---普通问诊流程测试结束---");

	}
	
	
	/**
	 * 退出当前app
	 */
	private void logoutOfcurrentApp() {
		
		logger.info("退出当前app");
		driver.findElement(By.id("title_back_img")).click();
		appCommonService.logoutForApp(driver);
		driver.quit();
		
	}
	
	/**
	 * 退出另一个app
	 */
	private void logoutOfAnotherApp(){
		
		logger.info("退出另一个app");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		appCommonService.logoutForApp(driver);
		driver.quit();
	}
	


	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		webDriver.quit();
	}

}

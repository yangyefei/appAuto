package test.apptest.common;


import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;


import service.AppCommonService;
import service.InitialService;

import common.frame.helper.Utils;
import common.frame.test.BaseTest;



public class AppCommonInquiry extends BaseTest {

	@Autowired
	private InitialService Initial;
	@Autowired
	private AppCommonService appCommonService;

	private AppiumDriver driver;


	@BeforeClass
	public void beforeClass() {		
	}


	@Test(enabled = true, dataProvider = "testData",description="普通问诊流程")
	public void commonInquiryProcess(Map<String, String> datadriven)throws Exception {
		
		logger.info("APP "+datadriven.get("version")+"---普通问诊流程测试开始---");
		//启动投资者app并且登录
		logger.info("启动并登陆创业者app");
		driver = Initial.appiumAndroidChuangyeSetUp(driver, datadriven.get("changyeApkName"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver = appCommonService.appLogin(driver,datadriven.get("changyeUserName"),datadriven.get("chuangyePassword"));
		
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
		driver.findElement(By.id("qi_ye_xin_xi_edit")).sendKeys(datadriven.get("comments"));
		
//		Actions actions = new Actions(driver);
//		Action action = null;
//		actions.clickAndHold(driver.findElement(By.id("qi_ye_xin_xi_edit"))).moveToElement(driver.findElement(By.name("告诉导师您需要咨询的问题")),0,500).release();
//		action = actions.build();
//		action.perform();
		

        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width/2,height*3/4, width/2,height/4, 1000);

		
		driver.findElement(By.id("wen_ti_xin_xi_edit")).sendKeys(datadriven.get("comments"));
		driver.findElement(By.id("submit_view")).click();
		
		driver.quit();
		

		
//		//启动创业者包并且登录
//		logger.info("启动并登陆创业者app");
//		driver = Initial.appiumAndroidChuangyeSetUp(driver, datadriven.get("changyeApkName"));
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver = appCommonService.appLogin(driver,datadriven.get("changyeUserName"),datadriven.get("chuangyePassword"));
//		
//		//接受邀约
//		logger.info("去收到的邀约页面接受邀约");
//		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("收到的邀约"))).click();
//		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("接受邀约"))).click();
//		
//		//确认见过
//		logger.info("去待约见点击确认见过");
//		driver.findElement(By.name("待约见")).click();
//		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("确认见过"))).click();
//		
//		//评价后提交
//		logger.info("评价后提交");
//		appCommonService.commentSubmit(driver, datadriven.get("interviewComments"));
//		
//		try {
//			//去创业者app约谈成功页面进行检查
//			logger.info("去已成功页面校验是否有当天订单");
//			new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("已成功"))).click();
//			new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.name(Utils.getCurrentDate())));//和当前日期比较，检查是否可以找到当天下的订单
//			
//		} catch (Exception e) {//检查失败后创业者和投资者app都退出登录
//			// TODO Auto-generated catch block
//			//返回并退出创业者app
//			logger.info("创业者中校验订单失败，未找到当天订单，进行创业者退出操作");
//			driver.findElement(By.id("title_back_img")).click();
//			appCommonService.appLoginout(driver);
//			driver.quit();
//			
//			//退出投资者app
//			logger.info("创业者中校验订单失败，未找到当天订单，将之前登录的投资者也进行退出操作");
//			driver = Initial.appiumAndroidFundSetUp(driver, datadriven.get("fundApkName"));
//			new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
//			appCommonService.appLoginout(driver);
//			driver.quit();
//			logger.info("APP "+datadriven.get("version")+"---约谈流程测试结束---");
//			
//			e.printStackTrace();
//		}
//		
//		//检查成功，返回并退出app
//		logger.info("创业者中校验当天订单成功，进行创业者退出操作");
//		driver.findElement(By.id("title_back_img")).click();
//		appCommonService.appLoginout(driver);
//		driver.quit();
//		
//		
//		//再次启动投资者app
//		logger.info("再次启动之前已经登录的投资者app");
//		driver = Initial.appiumAndroidFundSetUp(driver, datadriven.get("fundApkName"));
//		logger.info("进入投资者我的-我的约谈页面");
//		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
//		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的约谈"))).click();
//				
//		try {
//			//去投资者约谈成功页面检查
//			logger.info("进入投资者我的-我的约谈-已成功页面校验当天约谈订单是否存在");
//			new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("已成功"))).click();
//			new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.name(Utils.getCurrentDate())));//和当前日期比较，检查是否可以找到当天下的订单
//			
//		} catch (Exception e) {//检查失败退出投资者app
//			// TODO Auto-generated catch block
//			logger.info("投资者中校验订单失败，未找到当天订单，进行退出操作");
//			driver.findElement(By.id("title_back_img")).click();
//			appCommonService.appLoginout(driver);
//			driver.quit();
//			logger.info("APP "+datadriven.get("version")+"---约谈流程测试结束---");
//			
//			e.printStackTrace();
//		}
//		
//		//评论并且提交
//		logger.info("投资者中校验当天订单成功，对当天订单进行评价操作");
//		driver.findElement(By.name("评价")).click();
//		appCommonService.commentSubmit(driver, datadriven.get("interviewComments"));
//		
//		//返回并退出app
//		logger.info("投资者当天订单评价成功后，最后返回并进行退出登录操作");
//		driver.findElement(By.id("title_back_img")).click();
//		appCommonService.appLoginout(driver);
//		driver.quit();
//		
//		logger.info("APP "+datadriven.get("version")+"---约谈流程测试结束---");
		
//		List<WebElement> textFieldsList = driver.findElementsById("yue_tan_start_view");		
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	


	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

	@AfterClass
	public void afterClass() {

	}

}

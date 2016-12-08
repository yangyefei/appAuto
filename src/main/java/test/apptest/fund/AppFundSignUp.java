package test.apptest.fund;


import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;



import orm.jdbc.MysqlDataDeal;
//import antlr.collections.List;
import service.AppCommonService;
import service.InitialService;
import common.frame.test.BaseTest;

public class AppFundSignUp extends BaseTest {

	@Autowired
	private InitialService Initial;
	@Autowired
	private AppCommonService appCommonService;
	@Autowired
	private MysqlDataDeal mysqlDataDeal;
	
	
	private AppiumDriver driver;
//	private WebDriver webDriver;


	@BeforeClass
	public void beforeClass() {
	}
	

	@Test(enabled = true, dataProvider = "testData",description="活动报名",timeOut=480000)
	public void fundSignUp(Map<String, String> datadriven)throws Exception {
		
		String apkPathOfFund = datadriven.get("apkPathOfFund");
		
		logger.info("APP "+datadriven.get("version")+"---活动报名测试开始---");
		
		logger.info("启动投资者app");

		driver = Initial.appiumAndroidFundSetUp(driver, apkPathOfFund);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		appCommonService.logoutForApp(driver);

		logger.info("登录投资者app");
		driver = appCommonService.loginForApp(driver,datadriven.get("fundUserName"),datadriven.get("fundPassword"));
		
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
			driver.findElementByAccessibilityId("报名 Link").click();
			logger.info("填写并提交报名申请");		
			driver.findElementByClassName("android.widget.EditText").sendKeys("ceshi");
			driver.findElementByAccessibilityId("获得 Link").click();
			//获取验证码
			String code = mysqlDataDeal.getSignUpComfirmCode(datadriven.get("fundUserName"));
			driver.findElementByAccessibilityId("请输入验证码").sendKeys(code);
			
			driver.findElementByAccessibilityId("公司").click();
			List<WebElement> list=driver.findElementsByClassName("android.widget.EditText");
			WebElement target = list.get(3);
			target.sendKeys("xinlonghang");
			driver.findElementByAccessibilityId("提交 Link").click();	
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
			logger.info("返回，去我的活动页面校验");
			new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("detail_back_view"))).click();
			new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("title_back_img"))).click();	
			new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
			driver = appCommonService.swipeToDown(driver);
			new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的活动"))).click();
			
			try {
				new WebDriverWait(driver,40).until(ExpectedConditions.visibilityOfElementLocated(By.name("自动化测试活动-长期")));
				logger.info("校验成功");
				logger.info("退出当前账号");
				new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("title_back_img"))).click();
				appCommonService.logoutForApp(driver);
				driver.quit();
				logger.info("APP "+datadriven.get("version")+"---报名流程测试结束---");			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("活动报名失败");
				driver.quit();
				e.printStackTrace();	
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			logger.info("报名失败,之前已经报名、报名截止或活动已结束");
			driver.quit();
			e.printStackTrace();		
			
		}	
			
			mysqlDataDeal.deleteActivitySignUp(datadriven.get("activity"), datadriven.get("fundUserName"));
			
	}


	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

	
	@AfterClass
	public void afterClass(){	
	}

	
}

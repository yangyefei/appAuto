package test.apptest.chuangye;


import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import orm.jdbc.MysqlDataDeal;
import service.AppCommonService;
import service.InitialService;
import common.frame.test.BaseTest;

public class AppChuangyeEnterIncubator extends BaseTest {

	@Autowired
	private InitialService Initial;
	@Autowired
	private AppCommonService appCommonService;	
//	@Autowired
//	private WebCommonService webCommonService;
	
	@Autowired
	private MysqlDataDeal mysqlDataDeal;

	private AppiumDriver driver;
//	private WebDriver webDriver;


	@BeforeClass
	public void beforeClass() {
		
		logger.info("APP --- 企业入驻孵化器测试开始---");
		logger.info("先准备测试数据");
		mysqlDataDeal.updateEnterpriseStatus("SHXLHEnterprise", 4);
		    
	}


	@Test(enabled = true, dataProvider = "testData",description="企业入驻孵化器",timeOut=480000)
	public void chuangyeEnterIncubator(Map<String, String> datadriven)throws Exception {
		
		String apkPathOfChuangye = datadriven.get("apkPathOfChuangye");
		
		logger.info("APP "+datadriven.get("version")+"---企业入驻孵化器业务流程测试开始---");
		
		logger.info("启动创业者app");
		driver = Initial.appiumAndroidChuangyeSetUp(driver,apkPathOfChuangye);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver=appCommonService.logoutForApp(driver);

		logger.info("登录创业者app");
		driver = appCommonService.loginForApp(driver,datadriven.get("changyeUserName"),datadriven.get("chuangyePassword"));
		
		//点击进入孵化器列表页
		logger.info("发现-进入孵化器列表页");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("发现"))).click();
	    new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("一融孵化"))).click();
		
		//选择孵化器并申请入驻
	    logger.info("选择孵化器并申请入驻");
		new WebDriverWait(driver,120).until(ExpectedConditions.elementToBeClickable(By.name(datadriven.get("incubator")))).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info("点击进入申请入驻页面");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("申请入驻"))).click();
		
		try {
			
			logger.info("填写并提交入驻申请");
			new WebDriverWait(driver,40).until(ExpectedConditions.visibilityOfElementLocated(By.id("apply_company_name")));
			driver.findElement(By.id("apply_company_name")).sendKeys("SHXLHEnterprise");
			driver.findElement(By.id("apply_company_number")).sendKeys("1234567891234");	
			driver.findElement(By.id("apply_company_contact")).sendKeys("ceshi");
			driver.findElement(By.id("apply_company_mobile")).sendKeys("15000000002");
			driver.findElement(By.id("apply_company_email")).sendKeys("123@163.com");
			driver.findElement(By.name("提交")).click();
			Thread.sleep(5000);
			
		} catch (Exception e) {
			// TODO: handle exception
			
			logger.info("已申请入驻孵化器，不能再次入驻");
			new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("确定"))).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			logger.info("入驻失败");	
			driver.quit();
			Assert.assertTrue(false);
			e.printStackTrace();
			
		}
		
		logger.info("返回并退出登录");
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("title_back_img"))).click();
		Thread.sleep(2000);
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("title_back_img"))).click();
		driver=appCommonService.logoutForApp(driver);
		driver.quit();
		
		logger.info("校验是否入驻成功");
		Assert.assertTrue(checkResult(), "入驻失败！");				
		
		logger.info("APP "+datadriven.get("version")+"---入驻流程测试结束---");
		
	}
	
	private Boolean checkResult(){		
		
		boolean result=false;
		String num = mysqlDataDeal.checkEnterIncubator("SHXLHEnterprise");
		int number=Integer.parseInt(num);
		logger.info("入驻企业数量为:"+ number);

		if (1==number) {
			
			result = true;
			logger.info("入驻成功");
		} 
		
		return result;
		
	}
	
	
	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}

}

package test.apptest.fund;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.InitialService;
import common.frame.test.BaseTest;




public class AppFundRequirement extends BaseTest {
	@Autowired
	private InitialService Initial;
	@Autowired
	//private AppCommonService appCommonService;

	private AppiumDriver driver;

	@BeforeClass
	public void beforeClass() {
		
	}

	@Test(enabled = true, dataProvider = "testData",description="需求发布与响应")
	public void FundRequirement (Map<String, String> datadriven)throws Exception {
		
		logger.info("APP "+datadriven.get("version")+"---需求发布与响应---");
		//启动投资者app并且登录
		logger.info("启动并登陆投资者app");
		driver = Initial.appiumAndroidFundSetUp(driver, datadriven.get("fundApkName"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


	//driver=appCommonService.appLogin(driver,datadriven.get("fundUserName"),datadriven.get("fundPassword"));
		
		//进入我有项目菜单,填写信息
		logger.info("进入我有项目，填写信息");
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.name("一融"))).click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/wo_you_xiang_mu_tv"))).click();
//		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/ji_gou"))).sendKeys("信隆行QA自动化测试公司");
//		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/lian_xi_ren"))).sendKeys("信隆行QA");
//		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/phone_number"))).sendKeys("13000000001");
//		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/huo_qu_yan_zheng_ma"))).click();
//		Thread.sleep(2000);
//		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/phone_yan_zheng"))).sendKeys("000000");
//		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/xian_mu_name"))).sendKeys("信隆行核聚变");
//		
//		//特殊输入框，需要双击，可以封装
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/diqu_tv")));
//		element.click();
//		Thread.sleep(2000);
//		element.click();
//		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/confirm_action"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/note"))).sendKeys("QA自动化测试");
		//从下往上 滑动屏幕，可以封装
		int width=driver.manage().window().getSize().width;
		int height=driver.manage().window().getSize().height; 
		Thread.sleep(3000);
		driver.swipe(width/2,height*3/4, width/2,height/4, 1000);

		Thread.sleep(3000);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/submit"))).click();
		Thread.sleep(5000);
	
	}
	
	


	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}


}

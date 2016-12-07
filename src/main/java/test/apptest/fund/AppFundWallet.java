package test.apptest.fund;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import common.frame.test.BaseTest;
import io.appium.java_client.AppiumDriver;
import service.AppCommonService;
import service.InitialService;

@Test
public class AppFundWallet extends BaseTest{
	@Autowired
	private InitialService Initial;
	@Autowired
	private AppCommonService appCommonService;

	private AppiumDriver driver;


	@BeforeClass
	public void beforeClass() throws Exception {	
		//Runtime.getRuntime().exec("cmd.exe /c appium -a 172.16.22.233 -p 4723 -bp 4724");
//		Thread.sleep(5000);
	}

	@AfterClass
	public void afterClass() throws Exception {	
	//	Runtime.getRuntime().exec("cmd /c taskkill -f -im node.exe");
//		Runtime.getRuntime().exec("cmd.exe /C start wmic process where name='cmd.exe' call terminate");
//		Thread.sleep(5000);

	}
	@Test(enabled = true, dataProvider ="testData",description="我的钱包提现",timeOut=300000)
	public void wallet(Map<String, String> datadriven)throws Exception {

		String apkPathOfFund = datadriven.get("apkPathOfFund");
	
		logger.info("APP "+datadriven.get("version")+"---提现流程开始--");
		//启动企业app并且登录
		logger.info("启动并登陆投资者app");
		
		driver = Initial.appiumAndroidFundSetUp(driver,apkPathOfFund);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		appCommonService.logoutForApp(driver);
		appCommonService.loginForApp(driver, datadriven.get("fundUserName"), datadriven.get("fundPassword"));
		//测试自动搜索定位

		logger.info("进入我的钱包");
		driver.findElement(By.name("我的钱包")).click();
		driver.findElement(By.name("提现")).click();
		appCommonService.swipeToDown(driver);
		driver.findElement(By.name("下一步")).click();
		
		logger.info("账号输入界面");
		WebElement availableMonkey =driver.findElementById("com.easyrongfund:id/money_edit_tv");
		String avamon=availableMonkey.getText();
		driver.findElement(By.name("请输入支付宝账号")).sendKeys("111111111111");
		driver.findElement(By.name("请再次输入支付宝账号")).sendKeys("111111111111");
		driver.findElement(By.name("获取")).click();
		driver.findElement(By.name("输入验证码")).sendKeys("000000");
		driver.findElement(By.name("申请提现")).click();
		
		logger.info("验证判断提现是否成功");
        try {
    		WebElement getMonkey =driver.findElementById("com.easyrongfund:id/money_number_tv");
    		String getmon=getMonkey.getText();
    		Assert.assertEquals(avamon, getmon,"提现金额和可提现金额不对应");
		} catch (Exception e) {
		logger.info("验证判断提现失败");
		    Assert.assertTrue(false);
		}	
		driver.quit();
 
	}	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}
	
	}

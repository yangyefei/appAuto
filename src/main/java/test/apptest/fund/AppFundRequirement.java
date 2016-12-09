package test.apptest.fund;

import static org.testng.AssertJUnit.assertTrue;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.AppiumDriver;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.AppCommonService;
import service.InitialService;
import common.frame.test.BaseTest;


public class AppFundRequirement extends BaseTest {

	@Autowired
	private InitialService Initial;
	@Autowired
	private AppCommonService appCommonService;

	private AppiumDriver driver;

	private WebDriver webDriver ;
	
    

	@BeforeClass
	public void beforeClass() throws Exception {	

	}
	@AfterClass
	public void afterClass() throws Exception {	
	
	}


	@Test(enabled = true, dataProvider = "testData",description="需求发布",timeOut=300000)
	public void fundRequirement (Map<String, String> datadriven)throws Exception {

		String apkPathOfFund = datadriven.get("apkPathOfFund");
		
		logger.info("APP "+datadriven.get("version")+"---发起需求发布流程---");
		//启动投资者app并且登录
		logger.info("启动并登陆投资者app");
		driver = Initial.appiumAndroidFundSetUp(driver,apkPathOfFund);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//退出登录
		appCommonService.logoutForApp(driver);
		appCommonService.loginForApp(driver, datadriven.get("fundUserName"), datadriven.get("fundPassword"));

		
		// 进入我有我的需求菜单,填写信息
		logger.info("进入我的需求，填写信息");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("一融"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/xu_qiu_jie_tv"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/demand_my_publish_btn"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/back_title_right_img"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("需求类型"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("投资需求"))).click();
		WebElement XuQu=new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("请输入")));
		XuQu.sendKeys(datadriven.get("Result"));
		driver.findElementByName("投资金额").click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("500万以下"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("投资行业"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("新能源"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("确定"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("投资阶段"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("种子期"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("投资地区"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("湖南"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("确定"))).click();
		appCommonService.swipeToDown(driver);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("发布"))).click();
		WebElement SubmitSuccess =new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.name("提交成功")));
		
		//提交需求测试结果判断
		if(SubmitSuccess.isDisplayed()){	
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.name("关闭"))).click();
			WebElement res = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.name(datadriven.get("Result"))));
			assertTrue("我的需求 中提交的需求不存在", res.isDisplayed());
			logger.info("我的需求提交成功");
		} else {
			assertTrue("我的需求提交失败", false);
		}
		// 进入web 管理后台,确认需求申请
		logger.info("进入web 管理后台,确认需求申请");
		
		oderConfrim();

		// APP投资者 确认状态是否改变
		logger.info("APP投资者 确认状态是否改变");
		refresh();
		WebElement icon = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.name("已上线")));
		if (icon.isDisplayed()) {
			icon.click();
		} else {
			assertTrue(false);
		}
		driver.quit();
	}

	
	@Test(enabled =true, dataProvider = "testData",description="我的需求响应",timeOut=300000)
	public void fundRespond (Map<String, String> datadriven)throws Exception {
		
		String apkPathOfFund = datadriven.get("apkPathOfFund");
		
		logger.info("APP "+datadriven.get("version")+"---我的需求响应---");
		//启动投资者app并且登录

		logger.info("启动并登陆投资者app");
		driver = Initial.appiumAndroidFundSetUp(driver,apkPathOfFund);

		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//退出登录
		new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.id("mine_setting_img"))).click();
		
		driver.findElement(By.name("退出登录")).click();		
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("button1"))).click();
		driver=appCommonService.loginForApp(driver,"13774364001","xlh123456");	
		//进入需求街，响应需求

		logger.info("进入响应需求流程");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("一融"))).click();

		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/xu_qiu_jie_tv"))).click();
		WebElement ResponeName= new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name(datadriven.get("Result"))));
	
		if(ResponeName.isDisplayed()){
			ResponeName.click();}
		else{
			Assert.fail("响应失败");}
	

		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("我来响应"))).click();

		WebElement Responetext= new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongfund:id/success_name")));
		
		//响应需求测试结果判断

		Assert.assertEquals("响应提交页面失败", "您已响应需求", Responetext.getText());
		driver.quit();
		
		logger.info("APP "+datadriven.get("version")+"---我的需求响应测试结束---");
	}

	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}

	public void refresh() {
		// TODO Auto-generated method stub
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width/2,height/4, width/2,height*3/4, 1000);
	}


//	    System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
//	    System.setProperty("webdriver.gecko.marionette","C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");
//	   //运行时关闭之前启动的浏览器
////	    WindowsUtils.tryToKillByName("firefox.exe");
//	    WebDriver  driverweb=new FirefoxDriver();  
//	    driverweb.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
//	    driverweb.get("http://fwgl.yirongbang.net/");
		
		




	public void oderConfrim() throws Exception {
		
  webDriver = Initial.browserOfChromeSetUp(webDriver);
		
//		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
//		WebDriver webDriver = new ChromeDriver();
		webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		webDriver.get("http://fwgl.yirongbang.net/");

		// 运行时关闭之前启动的浏览器
		//WindowsUtils.tryToKillByName("firefox.exe");
		webDriver.manage().window().maximize();

		webDriver.findElement(By.name("form_user")).sendKeys("zdhcs");
		webDriver.findElement(By.name("form_password")).sendKeys("xlh123456");
		new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.className("btn-submit")))
				.click();
		System.out.println(webDriver.getTitle());
		new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.linkText("一融赋"))).click();
		new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.id("li_31"))).click();
		new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.linkText("需求审核"))).click();
		new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.linkText("通过"))).click();
		new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.name("operation_info")))
				.sendKeys("123456");
		new WebDriverWait(webDriver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div[2]/p/a[1]/span"))).click();
		Thread.sleep(5000);
		webDriver.quit();

	}

}

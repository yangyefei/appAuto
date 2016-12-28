package test.apptest.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
import io.appium.java_client.TouchAction;
import service.AppCommonService;
import service.InitialService;
import service.WebCommonService;

public class AppCommonSpeedInquiry extends BaseTest{
	@Autowired
	private InitialService Initial;
	@Autowired
	private AppCommonService appCommonService;
	@Autowired
	private WebCommonService webCommonService;
	
	private AppiumDriver driver;
	private WebDriver webDriver;
	@BeforeClass
	public void beforeClass()  {		

		
	}
	
	@Test(enabled = true, dataProvider = "testData",description="快速问诊",timeOut=300000)
	public void appCommonSpeedInquiry(Map<String, String> datadriven)throws Exception {
		
		String apkPathOfChuangye = datadriven.get("apkPathOfChuangye");
		
		logger.info("APP "+datadriven.get("version")+"---导师快速问诊流程开始--");
		//启动企业app并且登录
		logger.info("启动并登陆企业者app");
		driver = Initial.appiumAndroidChuangyeSetUp(driver,apkPathOfChuangye);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		driver =appCommonService.logoutForApp(driver);
		driver = appCommonService.loginForApp(driver,datadriven.get("changyeUserName"),datadriven.get("chuangyePassword"));
		
		//进入企业快速问诊流程
		logger.info("进入企业快速问诊流程");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("一融"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongchuangye:id/wen_zhen_layout"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongchuangye:id/speend_wen_zhen"))).click();
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongchuangye:id/detai_op_view"))).click();
	
		driver.findElementById("com.easyrongchuangye:id/qi_ye_xin_xi_edit").sendKeys("adlkjjjjk123456两点上课是老款的解放路");
		driver=appCommonService.swipeToDown(driver);
		driver.findElementById("com.easyrongchuangye:id/wen_ti_xin_xi_edit").sendKeys("adlkjjjjk123456两点上课是老款的解放路");
		driver.findElementById("com.easyrongchuangye:id/submit_view").click();
		Thread.sleep(3000);
		//调用付款
		logger.info(" 点击付款");
		//appCommonService.alipay(driver);
		PayButton();
	
		driver.findElementByName("我的问诊 Link").click();
		
		logger.info(" 付款成功");
		logger.info(" 进入PC后台确认快速问诊订单");
		oderConfrim(datadriven);
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("待问诊"))).click();
		
		//获取当前时间
		Calendar calendar = Calendar.getInstance(); 
		SimpleDateFormat timeformat=new SimpleDateFormat("yyyy-MM-dd"); 
		Date date=calendar.getTime();
		String time=timeformat.format(date);
		logger.info("获取当前时间："+time);
		
		WebElement yaoyue=new  WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongchuangye:id/yao_yue_shi_jian")));
		
		//快速问诊结果判断
		logger.info("判断问诊是否成功？");
		List<WebElement>  set = driver.findElementsById("com.easyrongchuangye:id/contact_name");
		for (WebElement webElement : set) {
                if (webElement.getText().contains("快速问诊")&& time.equals(yaoyue.getText())) {
                	logger.info("快速问诊测试成功");
                	Assert.assertTrue(true);
          break;
				} else {
					logger.info("快速问诊测试失败");
//					driver.quit();
					Assert.assertTrue(false);
				}
			
                			
			}
      	
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("确认服务"))).click();
		driver=appCommonService.commentSubmit(driver, "123");
				
		logger.info("APP "+datadriven.get("version")+"---导师快速问诊流程测试结束--");

	}

/*	private Log info(String string) {
		// TODO Auto-generated method stub
		return null;
	}*/

	public void PayButton(){
		try {
			WebElement element =new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.name("一融支付中心")));
			if (element.isDisplayed()) {
				int x =driver.manage().window().getSize().width;
				int y =driver.manage().window().getSize().height;
		     
				TouchAction  touchAction =new TouchAction(driver);
				touchAction.press(x/2, y*19/20).release().perform();
			} 
		} catch (Exception e) {
			logger.info("支付宝控件调出失败");
		}
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.className("android.widget.Button"))).click();
	}


	public void oderConfrim(Map<String, String> datadriven) throws Exception {
	 webDriver = Initial.browserOfChromeSetUp(webDriver);
////
//		System.setProperty("webdriver.chrome.driver",
//				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
//		WebDriver webDriver = new ChromeDriver();
		// 运行时关闭之前启动的浏览器

		webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		webDriver.get("http://fwgl.yirongbang.net/");
		// webDriver.manage().window().maximize();

		webDriver.findElement(By.name("form_user")).sendKeys("zdhcs");
		webDriver.findElement(By.name("form_password")).sendKeys("xlh123456");
		new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.className("btn-submit")))
				.click();
		new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.linkText("一融赋"))).click();
		new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.id("li_73"))).click();
		new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.linkText("问诊订单"))).click();
		new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.linkText("确认到账"))).click();
		new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.id("add_freeReason")))
				.sendKeys("123456");
		new WebDriverWait(webDriver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/p/a[1]/span"))).click();
		new WebDriverWait(webDriver, 30)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/p/a/span"))).click();
		Thread.sleep(3000);
	    logger.info("上传报告");
	    new WebDriverWait(webDriver,60).until(ExpectedConditions.elementToBeClickable(By.linkText("上传报告"))).click();	
	    webDriver = webCommonService.uploadFilesOfBackgroundSystem(webDriver, datadriven.get("uploadFilePath")); 
//	    logger.info("退出后台系统");
//	    webDriver = webCommonService.logoutOfBackgroundSystem(webDriver);
		webDriver.quit();
		}

	
	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}
	
	@AfterClass
	public void afterClass()  {	
			driver.quit();
			webDriver.quit();
	}
	
}		

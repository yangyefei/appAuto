package test.apptest.fund;

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
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import common.frame.test.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import service.AppCommonService;
import service.InitialService;

public class AppFundSpeedInquiry extends BaseTest{
	@Autowired
	private InitialService Initial;
	@Autowired
	private AppCommonService appCommonService;

	private AppiumDriver driver;


	@BeforeClass
	public void beforeClass() throws IOException, InterruptedException {		
	
	}


	@Test(enabled = true, dataProvider = "testData",description="快速问诊")
	public void Wallet(Map<String, String> datadriven)throws Exception {
		
		logger.info("APP "+datadriven.get("version")+"---导师问诊流程开始--");
		//启动企业app并且登录
		logger.info("启动并登陆企业者app");
		driver = Initial.appiumAndroidChuangyeSetUp(driver, datadriven.get("changyeApkName"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		driver =appCommonService.appLogout(driver);
		driver = appCommonService.appLogin(driver,datadriven.get("changyeUserName"),datadriven.get("chuangyePassword"));
		
		//进入企业快速问诊流程
		logger.info("进入企业专人问诊流程");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("一融"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongchuangye:id/wen_zhen_layout"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongchuangye:id/speend_wen_zhen"))).click();
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("com.easyrongchuangye:id/detai_op_view"))).click();
		
	
		driver.findElementById("com.easyrongchuangye:id/qi_ye_xin_xi_edit").sendKeys("adlkjjjjk123456两点上课是老款的解放路");
		appCommonService.swipeToDown(driver);
		driver.findElementById("com.easyrongchuangye:id/wen_ti_xin_xi_edit").sendKeys("adlkjjjjk123456两点上课是老款的解放路");
		driver.findElementById("com.easyrongchuangye:id/submit_view").click();
		Thread.sleep(3000);
		//调用付款
		logger.info(" 点击付款");
		PayButton();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.className("android.widget.Button"))).click();
		driver.findElementByName("我的问诊 Link").click();
		WebForSpeedInquiry.oderConfrim();
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("待问诊"))).click();
	
		//快速问诊结果判断
		List<WebElement>  set = driver.findElementsById("com.easyrongchuangye:id/contact_name");
		for (WebElement webElement : set) {
                if (webElement.getText().contains("快速问诊")) {
                	Assert.assertTrue(true);
                	logger.info("快速问诊测试成功");
				} else {
					Assert.assertTrue(false);
					logger.info("快速问诊测试失败");
				}
			
                			
			}
		driver.quit();

	}
//		WebElement touzhiren=new  WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("Johnnice看")));
//		Boolean flag=touzhiren.isDisplayed();
	public void PayButton(){
		int x =driver.manage().window().getSize().width;
		int y =driver.manage().window().getSize().height;
//		driver.tap(2,x/2, y-50, 500);
		TouchAction  touchAction =new TouchAction(driver);
		touchAction.press(x/2, y*19/20).release().perform();
	}

        
        
		
	
	
	
	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}
	}

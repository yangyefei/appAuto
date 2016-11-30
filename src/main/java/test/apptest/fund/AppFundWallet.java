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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.ctc.wstx.evt.WEndDocument;
import common.frame.test.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import service.AppCommonService;
import service.InitialService;
public class AppFundWallet extends BaseTest{
	@Autowired
	private InitialService Initial;
	@Autowired
	private AppCommonService appCommonService;

	private AppiumDriver driver;


	@BeforeClass
	public void beforeClass() throws Exception {	
//		Runtime.getRuntime().exec("cmd.exe /c start appium");
//		Thread.sleep(5000);
	}

	@AfterClass
	public void afterClass() throws Exception {	
//		Runtime.getRuntime().exec("cmd.exe /C start wmic process where name='cmd.exe' call terminate");
//		Thread.sleep(5000);
	}
	@Test(enabled = true, dataProvider = "testData",description="我的钱包提现")
	public void Wallet(Map<String, String> datadriven)throws Exception {
		
		logger.info("APP "+datadriven.get("version")+"---提现流程开始--");
		//启动企业app并且登录
		logger.info("启动并登陆投资者app");
		driver = Initial.appiumAndroidFundSetUp(driver, datadriven.get("fundApkName"));
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		//测试自动搜索定位
	   new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("一融"))).click();
	   new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("订阅项目"))).click();
logger.info("开始滑动");
	   AutoSwipeAndSearchById("com.easyrongfund:id/project_name","企业名称项目名称");
Thread.sleep(10000);
	   driver.quit();
 
	}

	public void PayButton(){
		int x =driver.manage().window().getSize().width;
		int y =driver.manage().window().getSize().height;
		TouchAction  touchAction =new TouchAction(driver);
		touchAction.press(x/2, y-20).release().perform();
	}
//，参数请输入搜索id和你想要搜索的名字
	public void AutoSwipeAndSearchById(String id,String name) {
		try {
			List<WebElement> elements=driver.findElementsById(id);
			
			for (WebElement webElement : elements) {
			
			if(webElement.getText().equals(name)){
				webElement.click();
		     	}
			else {
				
			
				do {
					
					int width=driver.manage().window().getSize().width;
					int  height=driver.manage().window().getSize().height;
					driver.swipe(width/2,height*3/4, width/2,height*1/2, 1000);
					
					List<WebElement> elements1=driver.findElementsById(id);
					elements=elements1;

					for(WebElement webElement2 : elements) {
						if(webElement2.getText().equals(name)){
							webElement2.click();
							}
						}
					}
			
			  while (!elements.contains(name));
				}
			
			}
		} catch (Exception e) {
			logger.info("ok");
		} 
			
		


	}
        
		
	
	
	
	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");

	}
	}

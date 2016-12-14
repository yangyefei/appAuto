package service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import service.AppCommonService;

public class AppCommonServiceImpl implements AppCommonService{

	@Override
	public AppiumDriver loginForApp(AppiumDriver driver, String userName, String userPassWord) {
		// TODO Auto-generated method stub
		
		  //wait for 60s
//	      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	      //点击进入登录页面
	      new WebDriverWait(driver,120).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
	      new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("请点击登录/注册"))).click();
	      
	      //输入用户名和密码
	      new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("login_username"))).sendKeys(userName);
	      driver.findElementById("login_password").sendKeys(userPassWord);
	      
	      driver.findElementByName("立即登录").click();
	      
	      new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.id("mine_user_name")));
				
		  return driver;
	}

	@Override
	public AppiumDriver logoutForApp(AppiumDriver driver) {
		// TODO Auto-generated method stub
		//点击我的
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("我的"))).click();
		//点击设置按钮
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("mine_setting_img"))).click();
		//退出登录
		driver.findElement(By.name("退出登录")).click();		
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("button1"))).click();
		
		return driver;
	}

	@Override
	public AppiumDriver commentSubmit(AppiumDriver driver, String comment) {
		// TODO Auto-generated method stub
		
		//星级评定
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("star_1"))).click();
		driver.findElement(By.id("star_2")).click();
		driver.findElement(By.id("star_3")).click();
		//评论内容
		driver.findElement(By.id("zong_ti_ping_jia")).sendKeys(comment);
		driver.findElement(By.id("submit")).click();	

		return driver;
	}

	@Override
	public AppiumDriver swipeToDown(AppiumDriver driver) {
		// TODO Auto-generated method stub
		
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width/2,height*3/4, width/2,height/4, 1000);//向下滑动，间隔1s        
	
		return driver;
	}

	@Override
	public AppiumDriver alipay(AppiumDriver driver) throws Exception {
		// TODO Auto-generated method stub
		Thread.sleep(5000);	
		int x =driver.manage().window().getSize().width;
		int y =driver.manage().window().getSize().height;
		TouchAction  touchAction =new TouchAction(driver);
		touchAction.press(x/2, y*19/20).release().perform();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.name("付款"))).click();
		
		return driver;
	}

	@Override
	public AppiumDriver scrollAndFindName(AppiumDriver driver, String searchName, String nameId, String totalNum) {		
		// TODO Auto-generated method stub
		
//		//获取项目总数
//		String totalNum = new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id(totalNumId))).getText();
//		//去掉数字两边的括号并且将其转换为int
//		int realTotalNum = Integer.valueOf(totalNum.substring(1,totalNum.length()-1))+3;
		//总数
		int realTotalNum = Integer.valueOf(totalNum)+6;
		
		Boolean isfound = false;
		int allNum = 0;
		
		do {
						
			int currentNum;
			List<WebElement> elements=driver.findElementsById(nameId);
			currentNum = elements.size();
			System.out.println("currentNum="+currentNum);
			//查找当前页是否有匹配的内容
			for (WebElement webElement : elements) {
				
				if(searchName.equals(webElement.getText())){
					
					System.out.println("内容已经被找到！");
					
					isfound = true;
					
					return driver;	
			     	}				
			}
			
			allNum = allNum + currentNum;

			//滑动屏幕
			int width=driver.manage().window().getSize().width;
			int height=driver.manage().window().getSize().height;
			driver.swipe(width/2,height*7/8, width/2,height*1/8, 1000);
									
			
		} while (!isfound && allNum < realTotalNum);//如果没有找到内容并且查找的项目数已经超过项目总数，跳出循环
		
		System.out.println("内容没有被找到！");
	
		return driver;
	}

}

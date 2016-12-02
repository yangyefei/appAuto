package test.selftest;

import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import common.frame.test.BaseTest;

import service.InitialService;

public class AppTestA extends BaseTest{

	  @Autowired
	  private InitialService Initial;
	  
	  public WebDriver driver2;
	  	  
	  public AppiumDriver driver;
	  public String changyeApkNameString="EasyrongChuangye2_1.apk";
	  public String fundApkNameString="EasyrongFund2_1.apk";
	  

  @BeforeClass
  public void beforeClass(){
	  
  }
  
  @Test(enabled=false)
  public void testa(){
	  
	   System.setProperty("webdriver.firefox.bin",
			   "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
	   
	   driver2 = new FirefoxDriver();
	   
	   driver2.get("http://www.yirongbang.net/");
	   driver2.manage().window().maximize();
	   
	   new WebDriverWait(driver2,30).until(ExpectedConditions.elementToBeClickable(By.linkText("一融孵化"))).click();

//	   driver2.findElement(By.linkText("请登录")).click();
	   
	   driver2.quit();

	  
  }
  
  @Test(enabled=false)
  public void demo() throws MalformedURLException {
	  
	  this.driver = Initial.appiumAndroidFundSetUp(driver,"");
	  
//	  this.driver = Initial.appiumAndroidChuangyeSetUp(driver,changyeApkNameString);
	  
	   //wait for 5s
      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      
//      WebElement ui = driver.findElementByName("");
//      ui.click();
//      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      
//      WebElement ui1 = driver.findElementByName("");
//      ui1.click();
      
      new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.name(""))).click();
      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      
      driver.quit();
 
	  
  }
  

  @AfterClass
  public void afterClass() {
	  
  }


}

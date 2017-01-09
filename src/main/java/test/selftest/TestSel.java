package test.selftest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import common.frame.test.BaseTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TestSel extends BaseTest{

  @BeforeClass
  public void beforeClass() {
	  logger.info("just test");
  }
  
  @Test
  public void function() throws Exception {
	  
//		String filePath =  String.valueOf(new File("./../../").getCanonicalPath()) +"auto.pdf";
//		
//		System.out.println(filePath);
		
		
	    WebDriver  driverweb=new FirefoxDriver();  
	    driverweb.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	    driverweb.get("http://fwgl.yirongbang.net/");
	  

  }

  @AfterClass
  public void afterClass() {
  }

}

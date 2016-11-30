package test.selftest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TestSel {

  @BeforeClass
  public void beforeClass() {
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

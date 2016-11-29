package service.impl;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import service.WebCommonService;

public class WebCommonServiceImpl implements WebCommonService{

	@Override
	public WebDriver logoutOfBackgroundSystem(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(3000);
	    new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='user']/img"))).click();
	    
	    new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.linkText("退出"))).click();
	
		return driver;
	}

	@Override
	public WebDriver uploadFilesOfBackgroundSystem(WebDriver driver,
			String uploadFileName) throws Exception {
		// TODO Auto-generated method stub
		
//		 File classpathRoot = new File(System.getProperty("user.dir"));
//		 File testdataDir = new File(classpathRoot, "testdata");
//
//		 String filePath = String.valueOf(testdataDir)+"/uploadFiles/"+uploadFileName;
		 
		 
		
//		String filePath =  String.valueOf(new File("./../..//").getCanonicalPath()) + uploadFileName;
		
		String filePath = "E:\\"+uploadFileName;
		
		System.out.println("文件上传路径为：" + filePath);
		
		driver.switchTo().frame(driver.findElement(By.id("sopop-iframe")));
	    
	    new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.id("counsel_file"))).sendKeys(filePath);	    
	    driver.findElement(By.xpath("//input[@type='submit'][@value='确 定']")).click();
	    
	    driver.switchTo().defaultContent();
	    
	    new WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.s-sopop-close"))).click();
						
		return driver;
	}



}

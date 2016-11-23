package test.apptest.fund;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class WebForReuirement {
	Alert alert = null;

//@Parameters({"user","pwd"})
@Test
public static void oderConfrim() throws InterruptedException { 

    System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
    System.setProperty("webdriver.gecko.marionette","C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");
   //运行时关闭之前启动的浏览器
    WindowsUtils.tryToKillByName("firefox.exe");
    WebDriver  driverweb=new FirefoxDriver();  
    driverweb.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    driverweb.get("http://fwgl.yirongbang.net/");
    //driverweb.manage().window().maximize();
  
	    driverweb.findElement(By.name("form_user")).sendKeys("zdhcs");
	    driverweb.findElement(By.name("form_password")).sendKeys("xlh123456");
	    new WebDriverWait(driverweb,30).until(ExpectedConditions.elementToBeClickable(By.className("btn-submit"))).click();
//	    new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent();
//      alert = driver.switchTo().alert();
//      alert.accept();
	    System.out.println(driverweb.getTitle());
//  	SwitchToWindow(s,driver);
	    new WebDriverWait(driverweb,30).until(ExpectedConditions.elementToBeClickable(By.linkText("一融赋"))).click();
	    new WebDriverWait(driverweb,30).until(ExpectedConditions.elementToBeClickable(By.id("li_31"))).click();
	    new WebDriverWait(driverweb,30).until(ExpectedConditions.elementToBeClickable(By.linkText("需求审核"))).click();
//	    new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.linkText("分配供应商"))).click();
//	    new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.linkText("确定"))).click();
//	    new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.linkText("确定"))).click();
	    new WebDriverWait(driverweb,30).until(ExpectedConditions.elementToBeClickable(By.linkText("通过"))).click();
        new WebDriverWait(driverweb,30).until(ExpectedConditions.elementToBeClickable(By.name("operation_info"))).sendKeys("123456");
        new WebDriverWait(driverweb,30).until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div[2]/p/a[1]/span"))).click();
        Thread.sleep(5000);
        driverweb.quit();
        //Select fruits = new Select(option1);
//	    fruits.selectByValue("4");
}
}	
	

//private  boolean SwitchToWindow(String windowTitle,WebDriver dr) {
//	// TODO Auto-generated method stub
//	 boolean flag = false;  
//	    try { 
////	    	将页面上所有的windowshandle放在入set集合当中
//	        String currentHandle = dr.getWindowHandle();  
//	        List<String> handles = (List<String>) dr.getWindowHandles();  
//	        for (String s : handles) {  
//	            if (s.equals(currentHandle))  
//	                continue;  
//	            else {  
//	            	dr.switchTo().window(s);
////	                和当前的窗口进行比较如果相同就切换到windowhandle
////	                判断title是否和handles当前的窗口相同
//	                if (dr.getTitle().contains(windowTitle)) {  
//	                    flag = true;  
//	                    System.out.println("Switch to window: "  
//	                            + windowTitle + " successfully!");  
//	                    break;  
//	                } else  
//	                    continue;  
//	            }  
//	        }  
//	    } catch (Exception e) {  
//	        System.out.printf("Window: " + windowTitle  
//	                + " cound not found!", e.fillInStackTrace());  
//	        flag = false;  
//	    }  
//	    return flag;  
//	} 
//
//
//  }





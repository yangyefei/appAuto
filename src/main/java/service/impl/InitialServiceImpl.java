package service.impl;

import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import service.InitialService;

public class InitialServiceImpl implements InitialService{
	
	  private String chuangyeAppPackage;
	  private String chuangyeAppActivity;
	  private String apkPathOfChuangye;
	  
	  private String fundAppPackage;
	  private String fundAppActivity;
	  private String apkPathOfFund;
	  
	  private String platformVersion;
	  private String appRunMachineIp;
	  private String webRunMachineIp;
	  private String appiumPort;

	@Override
	public AppiumDriver appiumAndroidChuangyeSetUp(AppiumDriver driver) throws MalformedURLException {
		// TODO Auto-generated method stub
		
//		 File classpathRoot = new File(System.getProperty("user.dir"));
//		 File appDir = new File(classpathRoot, "apps");
//		 File app = new File(appDir, apkName);
		 System.out.println("创业者app安装包路径为："+apkPathOfChuangye);
		 
		 DesiredCapabilities capabilities = new DesiredCapabilities();		 
		 capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		 capabilities.setCapability("platformName", "Android");	
		 capabilities.setCapability("deviceName", "Android Emulator");
//		 capabilities.setCapability("deviceName", "emulator-5554");
		 
//		 capabilities.setCapability("deviceName","device");
//		 capabilities.setCapability("automationName","Appium");
		 
	     capabilities.setCapability("unicodeKeyboard" ,"True");

	     capabilities.setCapability("resetKeyboard", "True");

         capabilities.setCapability("noReset", true);   
		 
		 capabilities.setCapability("platformVersion", platformVersion);
//		 capabilities.setCapability("udid", "emulator-5554");//如果要远程调用模拟器，这个参数必须要有
		 
//		 capabilities.setCapability("app", app.getAbsolutePath());
		 capabilities.setCapability("app", apkPathOfChuangye);
		 capabilities.setCapability("appPackage", chuangyeAppPackage);
		 capabilities.setCapability("appActivity", chuangyeAppActivity);

	     capabilities.setCapability("noSign", "True");
		 
	     driver = new AppiumDriver(new URL("http://"+appRunMachineIp+":"+appiumPort+"/wd/hub"), capabilities);
		
		return driver;
	}
	
	
	@Override
	public AppiumDriver appiumAndroidFundSetUp(AppiumDriver driver) throws MalformedURLException {
		// TODO Auto-generated method stub
		
//		 File classpathRoot = new File(System.getProperty("user.dir"));
//		 File appDir = new File(classpathRoot, "apps");
//		 File app = new File(appDir, apkName);
		System.out.println("投资者app安装包路径为："+apkPathOfFund);
		 
		 DesiredCapabilities capabilities = new DesiredCapabilities();		 
		 capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		 capabilities.setCapability("platformName", "Android");	
		 capabilities.setCapability("deviceName", "Android Emulator");
//		 capabilities.setCapability("deviceName", "emulator-5554");
		 
//		 capabilities.setCapability("deviceName","device");
//		 capabilities.setCapability("automationName","Appium");
		 
	     capabilities.setCapability("unicodeKeyboard" ,"True");
	     capabilities.setCapability("resetKeyboard", "True");
		 
	     capabilities.setCapability("noReset", true);
	     
		 capabilities.setCapability("platformVersion", platformVersion);
//		 capabilities.setCapability("udid", "emulator-5554");//如果要远程调用模拟器，这个参数必须要有
		 
		 capabilities.setCapability("app", apkPathOfFund);
		 
		 capabilities.setCapability("appPackage", fundAppPackage);
		 capabilities.setCapability("appActivity", fundAppActivity);

	     capabilities.setCapability("noSign", "True");
		 
		 driver = new AppiumDriver(new URL("http://"+appRunMachineIp+":"+appiumPort+"/wd/hub"), capabilities);
			
		return driver;
	}
	
	
	@Override
	public WebDriver browserOfInternetSetUp(WebDriver driver) throws Exception {
		// TODO Auto-generated method stub
		
		driver = new RemoteWebDriver(new URL("http://" + webRunMachineIp + ":4444/wd/hub"), DesiredCapabilities.internetExplorer());
		
		return driver;
	}
	
	@Override
	public WebDriver browserOfChromeSetUp(WebDriver driver) throws Exception {
		// TODO Auto-generated method stub
		driver = new RemoteWebDriver(new URL("http://" + webRunMachineIp + ":4444/wd/hub"), DesiredCapabilities.chrome());
		
		return driver;
	}


	@Override
	public WebDriver browserOfFirefoxSetUp(WebDriver driver) throws Exception {
		// TODO Auto-generated method stub
		driver = new RemoteWebDriver(new URL("http://" + webRunMachineIp + ":4444/wd/hub"), DesiredCapabilities.firefox());
		return driver;
	}



	public String getPlatformVersion() {
		return platformVersion;
	}

	public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
	}

	public String getChuangyeAppPackage() {
		return chuangyeAppPackage;
	}

	public void setChuangyeAppPackage(String chuangyeAppPackage) {
		this.chuangyeAppPackage = chuangyeAppPackage;
	}

	public String getChuangyeAppActivity() {
		return chuangyeAppActivity;
	}

	public void setChuangyeAppActivity(String chuangyeAppActivity) {
		this.chuangyeAppActivity = chuangyeAppActivity;
	}

	public String getFundAppPackage() {
		return fundAppPackage;
	}

	public void setFundAppPackage(String fundAppPackage) {
		this.fundAppPackage = fundAppPackage;
	}

	public String getFundAppActivity() {
		return fundAppActivity;
	}

	public void setFundAppActivity(String fundAppActivity) {
		this.fundAppActivity = fundAppActivity;
	}

	public String getAppiumPort() {
		return appiumPort;
	}

	public void setAppiumPort(String appiumPort) {
		this.appiumPort = appiumPort;
	}


	public String getAppRunMachineIp() {
		return appRunMachineIp;
	}


	public void setAppRunMachineIp(String appRunMachineIp) {
		this.appRunMachineIp = appRunMachineIp;
	}


	public String getWebRunMachineIp() {
		return webRunMachineIp;
	}


	public void setWebRunMachineIp(String webRunMachineIp) {
		this.webRunMachineIp = webRunMachineIp;
	}


	public String getApkPathOfChuangye() {
		return apkPathOfChuangye;
	}


	public void setApkPathOfChuangye(String apkPathOfChuangye) {
		this.apkPathOfChuangye = apkPathOfChuangye;
	}


	public String getApkPathOfFund() {
		return apkPathOfFund;
	}


	public void setApkPathOfFund(String apkPathOfFund) {
		this.apkPathOfFund = apkPathOfFund;
	}


}

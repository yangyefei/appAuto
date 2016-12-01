package service;

import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

public interface InitialService {
	
	
	/**
	 * Android 创业者app初始化
	 * @param driver
	 * @param apkName
	 * @return
	 * @throws MalformedURLException
	 */
	public AppiumDriver appiumAndroidChuangyeSetUp(AppiumDriver driver) throws MalformedURLException;
	
	/**
	 * Android 机构app初始化
	 * @param driver
	 * @param apkName
	 * @return
	 * @throws MalformedURLException
	 */
	public AppiumDriver appiumAndroidFundSetUp(AppiumDriver driver) throws MalformedURLException;
	
	
	/**
	 * 启动远程ie浏览器
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public WebDriver browserOfInternetSetUp(WebDriver driver) throws Exception;
	
	/**
	 * 启动chrome浏览器
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public WebDriver browserOfChromeSetUp(WebDriver driver) throws Exception;
	
	/**
	 * 启动Firefox浏览器
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public WebDriver browserOfFirefoxSetUp(WebDriver driver) throws Exception;


}

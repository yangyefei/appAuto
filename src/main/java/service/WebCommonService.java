package service;


import org.openqa.selenium.WebDriver;



public interface WebCommonService {
	

	/**
	 * 后台系统logout
	 * @param driver
	 * @return
	 */
	public WebDriver logoutOfBackgroundSystem(WebDriver driver);
	
	
	/**
	 * 后台系统上传报告
	 * @param driver
	 * @param filePath
	 * @return
	 */
	public WebDriver uploadFilesOfBackgroundSystem(WebDriver driver, String filePath);
		
	
}

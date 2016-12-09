package service;


import io.appium.java_client.AppiumDriver;



public interface AppCommonService {
	
	/**
	 * app登录
	 * @param driver
	 * @param userName
	 * @param userPassWord
	 * @return
	 */
	public AppiumDriver loginForApp(AppiumDriver driver, String userName, String userPassWord); 
	
	/**
	 * 退出app
	 * @param driver
	 * @return
	 */
	public AppiumDriver logoutForApp(AppiumDriver driver);
	
	
	/**
	 * 评论并且提交
	 * @param driver
	 * @param comment
	 * @return
	 */
	public AppiumDriver commentSubmit(AppiumDriver driver,String comment);
	
	/**
	 * 向下滑动
	 * @param driver
	 * @return
	 */
	public AppiumDriver swipeToDown(AppiumDriver driver);
	
	/**
	 * 支付宝付款
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public AppiumDriver alipay(AppiumDriver driver) throws Exception;
	
	/**
	 * 滚动并查找要找的内容
	 * @param driver
	 * @param searchName
	 * @param nameId
	 * @param totalNumId
	 * @return
	 */
	public AppiumDriver scrollAndFindName(AppiumDriver driver, String searchName, String nameId, String totalNum);
	
	
}

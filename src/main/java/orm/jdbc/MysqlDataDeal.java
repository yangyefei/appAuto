package orm.jdbc;

public interface MysqlDataDeal {

	public String getSignUpComfirmCode(String userId);
	
	public void updateEnterpriseStatus(String enterpriseName, int statusValue);
	
	public void deleteActivitySignUp(String activityName, String mobile);
	

}

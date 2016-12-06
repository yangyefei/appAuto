package orm.jdbc.impl;

import org.testng.Reporter;

import orm.jdbc.DataBaseDao;
import orm.jdbc.MysqlDataDeal;

import shelper.db.MySql;

import orm.DBType;

public class MysqlDataDealImpl implements MysqlDataDeal {

	private DataBaseDao dataBaseDao;
	private MySql msql;

	@Override
	public String getSignUpComfirmCode(String userId) {

		Reporter.log("从数据库中获取活动报名验证码...", true);

		msql = dataBaseDao.getInstanceOfMySql(DBType.MYSQL_PRE, "xlh_sms");

		String sql = "select s.content from sms_record s where phone="+userId+" ORDER BY s.id DESC LIMIT 1";
		String queryInfo = msql.query(sql);

		msql.closeDBcon();
		// System.out.println(queryInfo);
		return queryInfo;
	}
		
	
	@Override
	public void updateEnterpriseStatus(String enterpriseName, int statusValue) {
		// TODO Auto-generated method stub
		Reporter.log("更新企业状态...", true);

		msql = dataBaseDao.getInstanceOfMySql(DBType.MYSQL_PRE, "xlh_fhq");

		String sql = "UPDATE incubator_enterprises SET enterprise_status="+statusValue+" WHERE enterprise_name="+"'"+enterpriseName+"'";
		msql.Update(sql);

		msql.closeDBcon();

	}
	
	
	

	public DataBaseDao getDataBaseDao() {
		return dataBaseDao;
	}

	public void setDataBaseDao(DataBaseDao dataBaseDao) {
		this.dataBaseDao = dataBaseDao;
	}



}

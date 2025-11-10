package com.maan.adminnew.motor;

import java.util.List;

import com.maan.adminnew.motor.MotorAdminBean;
import com.maan.adminnew.motor.MotorAdminDAO;
import com.maan.adminnew.rating.RatingEngineBean;

public class MotorAdminService {
	
	MotorAdminDAO dao=new MotorAdminDAO();

	public List<MotorAdminBean>getAreaList(String branchcode,String searchField,String searchString,String searchOper)
	{
		return dao.getAreaList(branchcode,searchField, searchString, searchOper);
	}
	public List<MotorAdminBean>getMotorformList(String branchcode,String productid,String searchField,String searchString,String searchOper)
	{
		return dao.getMotorformList(branchcode,productid,searchField, searchString, searchOper);
	}
	public void updateArea(Object val[],String ss)
	{
		dao.updateArea(val,ss);
	}
	public void getareacoverage(MotorAdminBean bean)
	{
		dao.getareacoverage(bean);
	}
	public void insertArea(Object[] val)
	{
		dao.insertArea(val);
		
	}
}

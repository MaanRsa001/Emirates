package com.maan.adminnew.motor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.maan.adminnew.motor.MotorAdminBean;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.opensymphony.xwork2.ActionContext;

public class MotorAdminDAO extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(MotorAdminDAO.class);
	
	private String query="";
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	
	@SuppressWarnings("unchecked")
	public List<MotorAdminBean>getAreaList(String branchcode,String searchField,String searchString,String searchOper)
    {
	    Object obj[]=null;
	    List areaList = null;
        String query = "",str="like '%'||upper(?)||'%'";
        try
        {
            query = getQuery("GET_AREA_LIST");
            if(searchField!=null && searchString!=null && searchOper!=null){
				if("nc".equalsIgnoreCase(searchOper))
					str="not like '%'||upper(?)||'%'";
				else if("cn".equalsIgnoreCase(searchOper))
					str="like '%'||upper(?)||'%'";
				else if("eq".equalsIgnoreCase(searchOper))
					str=" = upper(?)";
				
				if("areaCode".equalsIgnoreCase(searchField))
					query+=" and upper(AREACOVER_CODE) "+str;
				if("areaDesc".equalsIgnoreCase(searchField))
					query+=" and upper(AREACOVER_DESC_ENGLISH) "+str;
										
				obj=new Object[] {branchcode,branchcode,searchString};
			}else
			{
				obj=new Object[] {branchcode,branchcode};
			}
            int j =  obj.length;
            for(int i = 0; i < j; i++)
            {
                Object k = obj[i];
                logger.info((new StringBuilder("Args===>")).append(k).toString());
            }
				logger.info((new StringBuilder("Query==>")).append(query).toString());
				areaList = this.mytemplate.query(query,obj,new RowMapper() {
    			public Object mapRow(final ResultSet rset,final int idVal) throws SQLException {
    				MotorAdminBean bean1=new MotorAdminBean();
    				bean1.setAreaID(rset.getString("AREACOVER_ID")==null?"":rset.getString("AREACOVER_ID"));
    				bean1.setAreaCode(rset.getString("AREACOVER_CODE")==null?"":rset.getString("AREACOVER_CODE"));
    				bean1.setAreaDesc(rset.getString("AREACOVER_DESC_ENGLISH")==null?"":rset.getString("AREACOVER_DESC_ENGLISH"));
    				bean1.setArabic(rset.getString("AREACOVER_DESC_ARABIC")==null?"":rset.getString("AREACOVER_DESC_ARABIC"));
    				bean1.setStatus(rset.getString("STATUS")==null?"":rset.getString("STATUS"));
    			 	bean1.setEdit(rset.getString("AREACOVER_ID")==null?"":rset.getString("AREACOVER_ID"));
    				return bean1;
    			}});
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return areaList;
    }
	@SuppressWarnings("unchecked")
	public void updateArea(Object val[],String ss)
	{
		try
		{
			logger.info("Method to updateArea()");
			logger.info("BranchCode===>" + branchCode);
			if(ss.equals("insert"))
			{
				query=getQuery("INSERT_AREACOVERAGE_UPDATE");
			}
			else if(ss.equals("update"))
			{
				query=getQuery("UPDATE_AREACOVERAGE");
			}
			this.mytemplate.update(query,val);
			logger.info("Query===>" + query);
			logger.info("updateArea() - Exit");
		}
		catch(Exception e)
		{
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}
	
	public void insertArea(Object[] val) {
		try{
		logger.info("Method to NewInsert()");
		logger.info("BranchCode===>" + branchCode);
		query=getQuery("INSERT_AREACOVERAGE");
		
		this.mytemplate.update(query,val);
		logger.info("Query===>" + query);
		logger.info("NewInsert() - Exit");
		}
		catch(Exception e)
		{
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		
	}

	
	@SuppressWarnings("unchecked")
	public List<MotorAdminBean>getMotorformList(String branchcode,String productid,String searchField,String searchString,String searchOper)
    {
	    Object obj[]=null;
	    List motorList = null;
        String query = "",str="like '%'||upper(?)||'%'";
        try
        {
            query = getQuery("GET_MOTORFORM_LIST");
            if(searchField!=null && searchString!=null && searchOper!=null){
				if("nc".equalsIgnoreCase(searchOper))
					str="not like '%'||upper(?)||'%'";
				else if("cn".equalsIgnoreCase(searchOper))
					str="like '%'||upper(?)||'%'";
				else if("eq".equalsIgnoreCase(searchOper))
					str=" = upper(?)";
				
				if("typeID".equalsIgnoreCase(searchField))
					query+=" and upper(TYPE_OF_BODY_ID) "+str;
				if("bodyName".equalsIgnoreCase(searchField))
					query+=" and upper(TYPE_OF_BODY_NAME) "+str;
										
				obj=new Object[] {branchcode,productid,searchString};
			}else
			{
				obj=new Object[] {branchcode,productid};
			}
            int j =  obj.length;
            for(int i = 0; i < j; i++)
            {
                Object k = obj[i];
                logger.info((new StringBuilder("Args===>")).append(k).toString());
            }
				logger.info((new StringBuilder("Query==>")).append(query).toString());
				motorList = this.mytemplate.query(query,obj,new RowMapper() {
    			public Object mapRow(final ResultSet rset,final int idVal) throws SQLException {
    				MotorAdminBean bean1=new MotorAdminBean();
    			    bean1.setTypeid(rset.getString("TYPE_OF_BODY_ID")==null?"":rset.getString("TYPE_OF_BODY_ID"));
    				bean1.setBodyname(rset.getString("TYPE_OF_BODY_NAME")==null?"":rset.getString("TYPE_OF_BODY_NAME"));
    				bean1.setBodyname(rset.getString("NO_OF_YEARS_ALLOWED")==null?"":rset.getString("NO_OF_YEARS_ALLOWED"));
    				bean1.setThirdparty(rset.getString("THIRD_PARTY_LIABILITY")==null?"":rset.getString("THIRD_PARTY_LIABILITY"));
    				bean1.setEdit(rset.getString("TYPE_OF_BODY_ID")==null?"":rset.getString("TYPE_OF_BODY_ID"));
    				return bean1;
    			}});
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return motorList;
    }
	
	@SuppressWarnings("unchecked")
	public void getareacoverage(final MotorAdminBean bean)
	{
		//logger.info("Method to getSelectedTransport()");
		@SuppressWarnings("unused")
		List editAreac=null;
		try
		{
			logger.info("Enter into getArealist()");
			query = getQuery("GET_AREA_LIST_EDIT");
			Object[] obj=new Object[3];
			obj[0]=branchCode;
			obj[1]=branchCode;
			obj[2]=bean.getAreaID();
			
			editAreac=this.mytemplate.query(query,obj,new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					bean.setAreaCode(rs.getString("AREACOVER_CODE")==null?"":rs.getString("AREACOVER_CODE"));
					bean.setAreaDesc(rs.getString("AREACOVER_DESC_ENGLISH")==null?"":rs.getString("AREACOVER_DESC_ENGLISH"));
					bean.setArabic(rs.getString("AREACOVER_DESC_ARABIC")==null?"":rs.getString("AREACOVER_DESC_ARABIC"));
					bean.setEff_date(rs.getString("EFFECTIVE_DATE")==null?"":rs.getString("EFFECTIVE_DATE"));
					bean.setPrevdate(rs.getString("EFFECTIVE_DATE")==null?"":rs.getString("EFFECTIVE_DATE"));
					bean.setStatus(rs.getString("STATUS")==null?"":rs.getString("STATUS"));
					bean.setRemarks(rs.getString("REMARKS")==null?"":rs.getString("REMARKS"));
					return null;
				}
		   });
			logger.info("Exit from getArealist()");
		}
		
		catch(Exception e)
		{
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		
	}
	

}

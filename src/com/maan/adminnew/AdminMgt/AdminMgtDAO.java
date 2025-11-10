package com.maan.adminnew.AdminMgt;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.password.passwordEnc;

public class AdminMgtDAO extends MyJdbcTemplate
{
	private static final Logger logger = LogUtil.getLogger(AdminMgtDAO.class);
	String query="";
	/*public List <Object> getMenuList(final AdminMgtBean bean, String branchCode){
		List <Object> menuList=null;
		try{

			Object[] obj=new Object[]{branchCode};
			if("".equals(bean.getSearchBy()) || bean.getSearchBy()==null || StringUtils.isNotBlank(bean.getUname()))
				query=getQuery("GET_MENU_LIST")+" order by A.DETAIL_NAME";
			else if((!"".equals(bean.getSearchBy())) && (!"".equals(bean.getSearchValue())) )
				query=getQuery("GET_MENU_LIST")+" and lower("+bean.getSearchBy()+") like '%'||lower('"+bean.getSearchValue()+"')||'%' order by A.DETAIL_NAME";
			logger.info("Query===>" + query);
			logger.info("Args===> " + StringUtils.join(obj,","));
			menuList=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return menuList;
	}*/
	
	public List<Map<String, Object>> getMenuList(final AdminMgtBean bean, String branchCode){
		List<Map<String, Object>> menuList=null;
		try{

			Object[] obj=new Object[]{branchCode};
			if("newadmin".equalsIgnoreCase(bean.getReqFrom())){
				query=getQuery("GET_MENU_LIST")+" and status='Y'";
			}
			else if("".equals(bean.getSearchBy()) || bean.getSearchBy()==null || StringUtils.isNotBlank(bean.getUname()))
				query=getQuery("GET_MENU_LIST")+" order by A.DETAIL_NAME";
			else if((!"".equals(bean.getSearchBy())) && (!"".equals(bean.getSearchValue())) )
				query=getQuery("GET_MENU_LIST")+" and lower("+bean.getSearchBy()+") like '%'||lower('"+bean.getSearchValue()+"')||'%' order by A.DETAIL_NAME";
			logger.info("Query===>" + query);
			logger.info("Args===> " + StringUtils.join(obj,","));
			menuList=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return menuList;
	}
	
	public List<Map<String, Object>>UtypeList(String branchCode,String appId){
		List <Map<String, Object>> utypeList=null;;
		try{
			query=getQuery("GET_UTYPE_LIST");
			logger.info("Query===>" + query);
			Object[] obj=new Object[]{branchCode, "2".equalsIgnoreCase(appId)?"Marine":appId};
			for(Object k:obj)
				logger.info("Args===>" + k);
			utypeList=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return utypeList;
	}
	
	public List<Map<String, Object>>adminList(final AdminMgtBean bean, String branchCode,String appId){
		List<Map<String, Object>> adminList=null;;
		try{
			if("".equals(bean.getSearchBy()) || bean.getSearchBy()==null)
				query=getQuery("GET_ADMIN_LIST");
			else if((!"".equals(bean.getSearchBy())) && (!"".equals(bean.getSearchValue())))
				query=getQuery("GET_ADMIN_LIST")+" and lower("+bean.getSearchBy()+") like '%'||lower('"+bean.getSearchValue()+"')||'%'";
			logger.info("Query===>" + query);
			Object[] obj=new Object[]{branchCode};
			for(Object k:obj)
				logger.info("Args===>" + k);
			adminList=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return adminList;
	}
	
	public void getAdminInfo(final AdminMgtBean bean,String branchCode, String appId){
		List<Map<String, Object>> adminList=null;
		try{
			query=getQuery("GET_ADMIN_LIST");
			query+=" and a.login_id=?";
			logger.info("Query===>" + query);
			//Object[] obj=new Object[]{appId=="2"?"Marine":appId, branchCode, bean.getLoginID()};
			Object[] obj=new Object[]{ branchCode, bean.getLoginID()};
			for(Object k:obj)
				logger.info("Args===>" + k);
			adminList=this.mytemplate.queryForList(query,obj);
			if(adminList!=null && adminList.size()>0){
				Map map=(Map) adminList.get(0);
				bean.setUname(map.get("USERNAME")==null?"":map.get("USERNAME").toString());
				bean.setLoginID(map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString());
				bean.setUtype(map.get("USERID")==null?"":map.get("USERID").toString());
				bean.setBranch(map.get("BRANCH_NAME")==null?"":map.get("BRANCH_NAME").toString());
				bean.setStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setBroker(map.get("BROKER_CODES")==null?"":map.get("BROKER_CODES").toString());
				bean.setMid(map.get("MENU_ID")==null?"":map.get("MENU_ID").toString());
				bean.setEmail(map.get("USER_MAIL")==null?"":map.get("USER_MAIL").toString());
				bean.setProductID(map.get("PRODUCT_ID").toString().trim().split(","));
				//bean.setProduct("Marine");
			}
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}
	
	public void getMenuInfo(final AdminMgtBean bean,String branchCode){
		List<Map<String, Object>> adminList=null;;
		try{
			query=getQuery("GET_MENU_LIST");
			query+=" and A.CATEGORY_DETAIL_ID=?";
			logger.info("Query===>" + query);
			Object[] obj=new Object[]{branchCode, bean.getMid()};
			for(Object k:obj)
				logger.info("Args===>" + k);
			adminList=this.mytemplate.queryForList(query,obj);
			if(adminList!=null && adminList.size()>0){
				Map map=(Map) adminList.get(0);
				bean.setMid(map.get("CATEGORY_DETAIL_ID")==null?"":map.get("CATEGORY_DETAIL_ID").toString());
				bean.setMname(map.get("DETAIL_NAME")==null?"":map.get("DETAIL_NAME").toString());
				bean.setUrlPath(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
				bean.setStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setParent(map.get("PARENT_MENU")==null?"":map.get("PARENT_MENU").toString());
			}
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}
	
	public int insNewMenu(final AdminMgtBean bean,String branchCode){
		int affRows=0;
		try{
			query=getQuery("INS_NEW_MENU");
			logger.info("Query===>" + query);
			Object[] obj=new Object[]{branchCode, bean.getMname(), bean.getUrlPath(), bean.getStatus(),branchCode, branchCode, bean.getParent()};
			for(Object k:obj)
				logger.info("Args===>" + k);
			affRows=this.mytemplate.update(query,obj);
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return affRows;
	}
	
	public int updateMenu(final AdminMgtBean bean,String branchCode){
		int affRows=0;
		try{
			query=getQuery("UPD_EXIST_MENU");
			logger.info("Query===>" + query);
			Object[] obj=new Object[]{bean.getMname(), bean.getUrlPath(), bean.getStatus(), bean.getParent(), bean.getMid(), branchCode};
			for(Object k:obj)
				logger.info("Args===>" + k);
			affRows=this.mytemplate.update(query,obj);
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return affRows;
	}
	
	public int insNewAdmin(final AdminMgtBean bean,String branchCode, String appId, String login_id){
		int affRows=0;
		try{
			passwordEnc pass = new passwordEnc();
			query=getQuery("INS_NEW_ADMIN_USER");
			logger.info("Query===>" + query);
			Object[] obj=new Object[]{bean.getLoginID(), pass.crypt(bean.getPwd().substring(0, 3), bean.getPwd()), bean.getUtype(), bean.getUname(), bean.getUtype(),
					 bean.getLoginID(),"1", "admin", "Y", "Y", "Y", "BOTH", branchCode, branchCode, bean.getMid(), bean.getProduct(), "", bean.getEmail(), login_id};
			for(Object k:obj)
				logger.info("Args===>" + k);
			affRows=this.mytemplate.update(query,obj);
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return affRows;
	}
	
	public int updateAdmin(final AdminMgtBean bean, String branchCode, String appId, String login_id){
		int affRows=0;
		Object[] obj=null;
		try{
			logger.info("Query===>" + query);
			if("".equals(bean.getPwd()) || bean.getPwd()==null){
				query=getQuery("UPD_EXIST_ADMIN_USER");
				obj=new Object[]{bean.getUname(), branchCode, appId=="2"?"Marine":appId, appId, appId, bean.getStatus(), bean.getMid(), bean.getProduct(), bean.getEmail(), "", login_id, bean.getLoginID()};
			}
			else{
				passwordEnc pass = new passwordEnc();
				query=getQuery("UPD_EXIST_ADMIN_USER_PWD");
				obj=new Object[]{bean.getUname(),pass.crypt(bean.getPwd().substring(0, 3), bean.getPwd()), branchCode, appId=="2"?"Marine":appId, appId, appId, bean.getStatus(), bean.getMid(), bean.getProduct(), bean.getEmail(), bean.getBroker(), login_id, bean.getLoginID()};
				
			}
			for(Object k:obj)
				logger.info("Args===>" + k);
			logger.info("Query===>" + query);
			affRows=this.mytemplate.update(query,obj);
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return affRows;
	}
	public String getBranch(String branchCode){
		String branch="";
		try{
			query=getQuery("GET_BRANCH_NAME");
			logger.info("Query===>" + query);
			Object[] obj=new Object[]{branchCode};
			for(Object k:obj)
				logger.info("Args===>" + k);
			branch=this.mytemplate.queryForObject(query,obj, String.class).toString();
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return branch;
	}
	public List<Map<String, Object>> getDashBoard(String loginId,AdminMgtBean bean) {
		List<Map<String, Object>> result=null;
		try{
			String query=getQuery("GET_DASHBOARD_ADMIN");
			Object obj[]=new Object[]{"3",loginId};
			logger.info("getDashBoard Query ==>"+query);
			logger.info("getDashBoard Args ==>"+StringUtils.join(obj,", "));
			result=this.mytemplate.queryForList(query,obj);
			
			for(int i=0;i<result.size();i++){
				Map<String,Object> temp=(Map<String,Object>)result.get(i);
				if("PORTFOLIO".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
					bean.setOneOffPortFolio(temp.get("QUOTE_NO").toString());
					bean.setOneOffPremium(temp.get("PREMIUM").toString());
				}
		        if("UNAPPROVED".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
		        	bean.setOneOffPending(temp.get("QUOTE_NO").toString());
		        }
		        if("APPROVED".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
		        	bean.setOneOffAccepted(temp.get("QUOTE_NO").toString());
		        }
		        if("REJECT".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
		        	bean.setOneOffRejected(temp.get("QUOTE_NO").toString());
		        }
			}
			obj=new Object[]{"11",loginId};
			logger.info("getDashBoard Args ==>"+StringUtils.join(obj,", "));
			result=this.mytemplate.queryForList(query,obj);
			for(int i=0;i<result.size();i++){
				Map<String,Object> temp=(Map<String,Object>)result.get(i);
				if("PORTFOLIO".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
					bean.setOpenCoverPortFolio(temp.get("QUOTE_NO").toString());
					bean.setOpenCoverPremium(temp.get("PREMIUM").toString());
				}
		        if("UNAPPROVED".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
		        	bean.setOpenCoverPending(temp.get("QUOTE_NO").toString());
		        }
		        if("APPROVED".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
		        	bean.setOpenCoverAccepted(temp.get("QUOTE_NO").toString());
		        }
		        if("REJECT".equalsIgnoreCase(temp.get("TYPE")==null?"":temp.get("TYPE").toString())){
		        	bean.setOpenCoverRejected(temp.get("QUOTE_NO").toString());
		        }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
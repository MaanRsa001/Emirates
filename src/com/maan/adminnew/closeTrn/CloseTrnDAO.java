package com.maan.adminnew.closeTrn;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;

public class CloseTrnDAO extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(CloseTrnDAO.class);
	private String query="";
	private Object[] obj=null;
	
	public void viewTrnDetail(CloseTrnBean bean, String branchCode, String pid){
		List<Map<String, Object>> trnDetails=null;
		try {
			logger.info("Enter viewTrnDetail()");
			String query="select to_char(CLO_DATE_CLOSED,'mm/dd/yyyy') CLO_DATE_CLOSED, CLO_REMARKS, to_char(CLO_MONTHEND_DT,'mm/dd/yyyy') CLO_MONTHEND_DT, to_char(CLO_DATE_OPENED,'mm/dd/yyyy') CLO_DATE_OPENED from T_TRN_CLOSING where branch_code=? and PRODUCT_CORE_CODE='1'";
			trnDetails=this.mytemplate.queryForList(query,new Object[]{branchCode});
			logger.info("Query==>"+query);
			if(trnDetails!=null && trnDetails.size()>0){
				Map map=(Map)trnDetails.get(0);
				bean.setCloseDate(map.get("CLO_DATE_CLOSED")==null?"":map.get("CLO_DATE_CLOSED").toString());
				bean.setOpenDate(map.get("CLO_DATE_OPENED")==null?"":map.get("CLO_DATE_OPENED").toString());
				bean.setMonthCloseDate(map.get("CLO_MONTHEND_DT")==null?"":map.get("CLO_MONTHEND_DT").toString());
				bean.setRemarks(map.get("CLO_REMARKS")==null?"":map.get("CLO_REMARKS").toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		logger.info("Exit from viewTrnDetail()");
	}
	
	
	public int saveCLoseTrndetails(CloseTrnBean bean, String branchCode, String loginId, String cid){
		logger.info("Enter===>saveCLoseTrndetails");
		int count=0;
    	try{
    		count = this.mytemplate.queryForObject("select count(*) from T_TRN_CLOSING where BRANCH_CODE=? and PRODUCT_CORE_CODE=?",Integer.class,new Object[]{branchCode, "1"});
			if(count>0){
				query="update T_TRN_CLOSING set CLO_DATE_CLOSED=to_date(?,'dd/mm/yyyy'),CLO_REMARKS=?,CLO_PREPARED_DT=sysdate,CLO_DATE_OPENED=to_date(?,'dd/mm/yyyy'), CLO_MONTHEND_DT=to_date(?,'dd/mm/yyyy') where CLO_TRAN_CODE=1 and BRANCH_CODE=? and PRODUCT_CORE_CODE=?";
				obj=new Object[]{bean.getCloseDate(), bean.getRemarks(), bean.getOpenDate(), bean.getMonthCloseDate(), branchCode, "1"};
			}
			else{
				query="insert into T_TRN_CLOSING(CLO_TRAN_CODE,CLO_DATE_CLOSED,CLO_REMARKS,CLO_PREPARED_DT,CLO_DATE_OPENED,CLO_MONTHEND_DT,BRANCH_CODE,PRODUCT_CORE_CODE) values(1,to_date(?,'dd/mm/yyyy'),?,sysdate,to_date(?,'dd/mm/yyyy'),to_date(?,'dd/mm/yyyy'),?,?)";
				obj=new Object[]{bean.getCloseDate(), bean.getRemarks(), bean.getOpenDate(), bean.getMonthCloseDate(), branchCode, "1"};
			}
			logger.info("Query===>" + query);
			logger.info("args===>"+StringUtils.join(obj,","));
			count=this.mytemplate.update(query,obj);
			
			query = "select nvl(max(RUNNING_SNO)+1,0) from TRN_CLOSING_DETAILS where BRANCH_CODE=?";
			int maxRun = this.mytemplate.queryForObject(query,Integer.class, new Object[]{branchCode});
			
			query = "select to_char(sysdate,'Mon'||' '|| 'yyyy') description, '01/01/'|| to_char(sysdate, 'yyyy') future_date, to_char(to_date(?,'dd/mm/yyyy')+1, 'dd/mm/yyyy') closeNextDate from dual";
			Map map = this.mytemplate.queryForMap(query, new Object[]{bean.getCloseDate()});
			
			bean.setDescription(map.get("description")==null?"":map.get("description").toString());
			bean.setCloseNextDate(map.get("closeNextDate")==null?"":map.get("closeNextDate").toString());
			
			query = "insert into TRN_CLOSING_DETAILS values(?,?, to_date(?,'dd/mm/yyyy'), to_date(?,'dd/mm/yyyy'), to_date(?,'dd/mm/yyyy'), sysdate,?,?,?,?,?)";
			obj=new Object[]{maxRun,map.get("description"), bean.getOpenDate(), bean.getCloseDate(),map.get("future_date"),  bean.getRemarks(), "Y", branchCode, cid, "1"};
			this.mytemplate.update(query, obj);
			logger.info("Query===>" + query);
			logger.info("args===>"+StringUtils.join(obj,","));
    	}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
			e.printStackTrace();
		}
    	logger.info("Exit===>saveCLoseTrndetails");
    	return count;
	}
	
	
	public List<Map<String, Object>> listViewDetail(String branchCode){
		List<Map<String, Object>> trnDetails=null;
		try {
			logger.info("Enter listViewDetail()");
			String query="select RUNNING_SNO,MONTH_DESC,to_char(START_DATE, 'dd/mm/yyyy') START_DATE,to_char(FUTURE_DATE, 'dd/mm/yyyy') FUTURE_DATE, to_char(END_DATE, 'dd/mm/yyyy') END_DATE, REMARKS,to_char(SYS_DATE, 'dd/mm/yyyy') SYS_DATE from TRN_CLOSING_DETAILS where branch_code=? and PRODUCT_CORE_CODE='1' order by RUNNING_SNO desc";
			trnDetails=this.mytemplate.queryForList(query,new Object[]{branchCode});
			logger.info("Query==>"+query);
		} catch(Exception e) {
			e.printStackTrace();
		}
		logger.info("Exit from listViewDetail()");
		return trnDetails;
	}
}
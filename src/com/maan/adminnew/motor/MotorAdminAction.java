package com.maan.adminnew.motor;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.adminnew.motor.MotorAdminBean;
import com.maan.adminnew.motor.MotorAdminService;
import com.maan.common.LogUtil;
import com.maan.common.Validation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MotorAdminAction extends ActionSupport implements
		ModelDriven<MotorAdminBean> {
	private static final Logger logger = LogUtil.getLogger(MotorAdminAction.class);
	private static final long serialVersionUID = 1L;
	private MotorAdminBean bean = new MotorAdminBean();
	Validation validation = new Validation();
	MotorAdminService rservice = new MotorAdminService();
	Map<String, Object> session = ActionContext.getContext().getSession();
	String branchCode = (String) session.get("BranchCode");
	String productId="65";
	
	private Integer page= 0;
	private Integer rows=0;
	private String sord;
	private String sidx;
	private String searchField;
	private String searchString;
	private String searchOper;
	private Integer total= 0;
	private Integer records= 0;
	private String option;
	private String menuType;
	
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getSearchOper() {
		return searchOper;
	}
	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	public MotorAdminBean getModel() {
		return bean;
	}
	
	public List<MotorAdminBean> gridList(List<MotorAdminBean> list){
		List<MotorAdminBean>selectedList =  new ArrayList<MotorAdminBean>();
		try{
		    int to = (rows * page);
		    int from = to - rows;
		    records = list.size();
	        if(to > records)
	            to =records;
	       
	        for(int i=from;i<to;i++){
	        	selectedList.add(list.get(i));
	        }	       
		    total =(int) Math.ceil((double)records / (double)rows);
		}catch(Exception e){
			e.printStackTrace();
		}
	return selectedList;
}

public List<MotorAdminBean> getGridList(){
		List<MotorAdminBean> list=new ArrayList<MotorAdminBean>();
		if("area".equalsIgnoreCase(menuType))
		list=gridList(rservice.getAreaList(branchCode,searchField, searchString, searchOper));
		
		return list;
}
public List<MotorAdminBean> getMotorformList(){
	List<MotorAdminBean> list=new ArrayList<MotorAdminBean>();
	if("motorbody".equalsIgnoreCase(menuType))
	list=gridList(rservice.getMotorformList(branchCode,productId,searchField, searchString, searchOper));
	
	return list;
}

public String area()
{
	menuType="area";
	return "motorGrid";
}
public String motorbody()
{
	menuType="motorbody";
	return "motorGrid";
}

public void validateareacoverage()
{
	if(StringUtils.isBlank(bean.getAreaDesc()))
		addActionError(getText("areadesc.required"));
	if(StringUtils.isBlank(bean.getAreaCode()))
        addActionError(getText("areaid.required"));
	if(StringUtils.isBlank(bean.getEff_date()))
		this.addActionError(getText("date.required"));
	else{
	boolean bo=validateDate(bean.getEff_date());
	if(bo)
		this.addActionError(getText("date.invalid"));
	}
	if(StringUtils.isBlank(bean.getStatus()))
        addActionError(getText("status.required"));
	
}
public boolean validateDate(String val) 
{		
	boolean bo=false;
	try
	{
		logger.info("Enter Into ValidateDate()");
		String sysdate;
		Format formatter;
	    Date date=new Date(); 
	    formatter = new SimpleDateFormat("DD/MM/YYYY");
	    sysdate = formatter.format(date);
	   	String format = "DD/MM/YYYY"; 
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		java.util.Date date1 = sdf.parse(sysdate);
		java.util.Date date2 = sdf.parse(val); 
		if(date2.compareTo(date1) < 0)
		{
		bo=true;
		}
		logger.info(String.valueOf(bo));
		
	}	
	catch(Exception e)
	{
		logger.info(e);
	}
	logger.info("Exit from ValidateDate()");
	return bo;
}
/*
public String updateConveyance() {
	validatenewConveyance();
	String remarks = "";
	if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
		remarks = " ";
	else
		remarks = bean.getRemarks();
	if(!hasActionErrors())
	{
	if("edit".equals(bean.getMode()))
	{
		String string="";
		boolean bo = validateDate(bean.getPrevdate());
		if(bo==false)
		{				
			Object[] val={bean.getConveyName().toUpperCase(),bean.getConveyRate(),bean.getEff_date(),bean.getStatus(),remarks,branchCode,bean.getConveyID()};
			string="update";
			rservice.getUpdateConveyance(val,string);
		}

		else
		{
			Object[] val={bean.getConveyID(),bean.getTransID(),branchCode,bean.getConveyName(),bean.getConveyRate(),bean.getTransID(),bean.getEff_date(),branchCode,bean.getTransID(),bean.getConveyName(),branchCode,bean.getCode(),remarks,bean.getStatus()};
			string="insert";
			rservice.getUpdateConveyance(val,string);
		}
		this.addActionMessage(getText("update.success"));
	}
	else
	{
		int amend_id=0;	
		Object[] val={branchCode,bean.getTransID(),branchCode,bean.getConveyName().toUpperCase(),bean.getConveyRate(),bean.getTransID(),bean.getEff_date(),amend_id,branchCode,bean.getCode(),remarks,bean.getStatus()};
		for(Object k: val){
			logger.info("val.args===>" + k);
		}
		rservice.getNewConveyance(val);
		this.addActionMessage(getText("insert.success"));
	}
	    menuType="conveyance";
		return "commonList";
	}
	return "updateConveyance";
}
*/
public String updatearea()
{
	
	    validateareacoverage();
	    if (!hasActionErrors()) {
	    	if("edit".equals(bean.getMode()))
	    	{
	    		String string="";
	    		boolean bo = validateDate(bean.getPrevdate());
	    		if(bo==false)
	    		{
	    		 Object val[] ={bean.getAreaDesc(),bean.getArabic(),bean.getAreaCode(),bean.getEff_date(),bean.getStatus(),bean.getRemarks(),bean.getAreaID(),branchCode,bean.getAreaID()};
	    		 string="update";
	    		 rservice.updateArea(val,string);
	    		}
	    		else
	    		{
	    			Object val[] ={bean.getAreaID(),bean.getAreaDesc(),bean.getArabic(),bean.getAreaCode(),bean.getAreaID(),branchCode,bean.getEff_date(),bean.getStatus(),branchCode,bean.getRemarks()};
	    			string="insert";
					rservice.updateArea(val,string);
				}
	    		addActionMessage(getText("update.success"));
	    	}
	    	else
	    	{
	    		int amend_id=0;
	    		Object val[] ={branchCode,bean.getAreaDesc(),bean.getArabic(),bean.getAreaCode(),amend_id,bean.getEff_date(),bean.getStatus(),branchCode,bean.getRemarks()};
	    		rservice.insertArea(val);
	    		this.addActionMessage(getText("insert.success"));
	    	}
	    	menuType="area";
	    	return "motorGrid";
		} 
	    	return "editareacov";
}	    

public String editarea()
{
    if("edit".equals(bean.getMode()))
		{
    	rservice.getareacoverage(bean);
	}
    return "editareacov";
}
		
}

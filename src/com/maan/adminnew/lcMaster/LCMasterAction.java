package com.maan.adminnew.lcMaster;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LCMasterAction extends ActionSupport implements ModelDriven<LCMasterBean>
{
	//LCCreation/LCCreationBrokerList.jsp
	private static final long serialVersionUID = 1236479L;
	final LCMasterService service=new LCMasterService();
	final LCMasterBean bean=new LCMasterBean();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String belongingBranch=(String)session.get("BelongingBranch");
	String login_id=(String)session.get("user");
	String appId=(String)session.get("AppId");
	private String userType=(String) session.get("user1") ;
	private String lcAmtc;
	List<Map<String, Object>> lcList=new ArrayList<Map<String, Object>>();

	public List<Map<String, Object>> getLcList(){
		return lcList;
	}
	public void setLcList(List<Map<String, Object>> lcList){
		this.lcList = lcList;
	}
	public List<Map<String, Object>> getSaleList(){
		return service.getSaleList(belongingBranch);
	}
	public List<Map<String, Object>> getCurrencyList(){
		return service.getCurrencyList(branchCode);
	}
	public List<Map<String, Object>> getBankList(){
		return service.getBankList(branchCode);
	}
	
	public String list(){
		lcList=service.getLcList(branchCode);
		return "lclist";
	}
	
	public String lcView(){
		lcList=service.getLcDetail(bean.getBroker(), branchCode);
		bean.setDisplay("lcview");
		bean.setFrom1("lcView");
		return "lclist";
	}

	public String ocView(){
		if("admin".equalsIgnoreCase(userType)){
			lcList=service.getOcDetail(bean.getBroker(), branchCode);
			bean.setDisplay("ocview");
			bean.setFrom1("ocView");
			return "lclist";
		}else
			return "viewOpen";
	}
	
	public String add(){
		if(!"admin".equalsIgnoreCase(userType)){
			branchCode=(String)session.get("LoginBranchCode");
		}
		lcList=service.getLCOCDetail(branchCode, bean,"add");
		if(!"new".equals(bean.getFrom())){
			service.getlcInfo(bean, branchCode);
		}
		return "newlc";
	}
	public String save(){
		if(!"admin".equalsIgnoreCase(userType)){
			branchCode=(String)session.get("LoginBranchCode");
		}
		validLc();
		if(!hasActionErrors()){
			if("new".equals(bean.getFrom())){
				service.getLCSave(bean, branchCode);
				bean.setDisplay("successNew");
			}else if("edit".equals(bean.getFrom())){
				service.getLCUpdate(bean, branchCode);
				bean.setDisplay("successUpdate");
			}
		}
		lcList=service.getLCOCDetail(branchCode, bean,"add");
		return "newlc";
	}
	public String lcDetails(){
		if(!"admin".equalsIgnoreCase(userType)){
			branchCode=(String)session.get("LoginBranchCode");
			bean.setFrom1("ocView");
		}
			lcList=service.getLCOCDetail(branchCode, bean,"lcDetails");
		return "lcdetails";
	}
	
	public String lcDelete(){
		if(!"admin".equalsIgnoreCase(userType)){
			branchCode=(String)session.get("LoginBranchCode");
			bean.setFrom1("ocView");
		}
		service.lcDelete(bean);
		lcList=service.getLCOCDetail(branchCode, bean,"lcDetails");
		return "lcdetails";
	}
	
	public LCMasterBean getModel() {
		return bean;
	}
	
	public void validLc(){
		try{
			if(StringUtils.isBlank(bean.getBank()))
				addActionError(getText("error.bank.required"));
			if(StringUtils.isBlank(bean.getLcNum()))
				addActionError(getText("error.lcnum.required"));
			if(StringUtils.isBlank(bean.getStartDate()))
				addActionError(getText("error.startdate.required"));
			if(StringUtils.isBlank(bean.getExpDate()))
				addActionError(getText("error.expDate.required"));
			if(StringUtils.isNotBlank(bean.getStartDate()) && StringUtils.isNotBlank(bean.getExpDate())){
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		    	Date date1 = sdf.parse(bean.getStartDate());
		    	Date date2 = sdf.parse(bean.getExpDate());
		    	if(date1.compareTo(date2)>0)
		    		addActionError(getText("error.startdate.invalid"));
			}
			if(StringUtils.isBlank(bean.getLcCurrency()))
				addActionError(getText("error.lcCurrency.required"));
			if(StringUtils.isBlank(bean.getLcAmt()))
				addActionError(getText("error.lcAmt.required"));
			else if(!StringUtils.isNumeric(bean.getLcAmt()))
				addActionError(getText("error.lcAmt.invalid"));
			else if(Double.parseDouble(StringUtils.isBlank(bean.getUsedAmt())?"0":bean.getUsedAmt())>Double.parseDouble(StringUtils.isBlank(bean.getLcAmt())?"0":bean.getLcAmt()))
				addActionError(getText("error.lcAmt.high.usedamt"));
			if(StringUtils.isBlank(bean.getSale()))
				addActionError(getText("error.sale.required"));
			if(StringUtils.isBlank(bean.getLcNumYN()))
				addActionError(getText("error.lcNumYN.required"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public String useAmtDetails(){  
		 
		lcList=service.getLcAmtDetails(bean);
		bean.setDisplay("useamtView");
		return "lcdetails";
	}
	public void setLcAmtc(String lcAmtc) {
		this.lcAmtc = lcAmtc;
	}
	public String getLcAmtc() {
		return lcAmtc;
	}
}

package com.maan.adminnew.portfolio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang.StringUtils;

import com.maan.adminnew.common.CommonService;
import com.maan.common.LogUtil;
import com.maan.common.Validation;
import com.maan.webservice.dao.PolicyGenerationDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PortfolioAction extends ActionSupport implements ModelDriven<PortfolioBean> {
	private static final Logger logger = LogUtil.getLogger(PortfolioAction.class);
	private static final long serialVersionUID = 1L;
	private PortfolioBean bean=new PortfolioBean();
	PortfolioService pservice=new PortfolioService();
	CommonService cservice=new CommonService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String loginId=(String)session.get("user"); 
	Validation validation=new Validation();
	String freightStatus=(String)session.get("freightStatus")==null?"":(String)session.get("freightStatus");
	//String freightStatus=request.getParameter("freightStatus")==null?"":request.getParameter("freightStatus");
	private List<Map<String, Object>> portfolioList;
	private List<Map<String, Object>> viewList;
	List<String> policyno;
	
	public List<Map<String, Object>> getViewList() {
		return viewList;
	}
	public List<Map<String, Object>> getPortfolioList() {
		return portfolioList;
	}
	public List<Map<String, Object>> getProducts()
	{
		return pservice.getProducts(branchCode);
	}
	public PortfolioBean getModel() {
		return bean;
	}
	public String open()
	{
		return "portfolio";
	}
	public String check()
	{
		return "check";
	}
	public String byDate()
	{
		return "byDate";
	}
	public String information()
	{
		return "information";
	}
	
	public String getList()
	{
		//Object[] val={bean.getFromdate(),bean.getTodate(),"Normal","N","y",branchCode,};
		portfolioList=pservice.getPortFolio(bean.getFromdate(), bean.getTodate(), bean.getRep(), bean.getProductID(), branchCode, "", "",bean.getQuoteno());
		if(StringUtils.isNotBlank(bean.getFromdate()))
			bean.setFromdate(dateFormat(bean.getFromdate()));
		if(StringUtils.isNotBlank(bean.getTodate()))
			bean.setTodate(dateFormat(bean.getTodate()));
		return "portfolio";
	}
	public String getListAjax() {
		if(StringUtils.isBlank(bean.getQuoteno()) && StringUtils.isBlank(bean.getFromdate()) && StringUtils.isBlank(bean.getTodate())) {
			addActionError("Please atleast one to Proceed");
		}else if(StringUtils.isBlank(bean.getFromdate()) && StringUtils.isBlank(bean.getQuoteno())) {
			addActionError("Please Select the From Date");
		}else if(StringUtils.isBlank(bean.getTodate()) && StringUtils.isBlank(bean.getQuoteno())) {
			addActionError("Please Select the To Date");
		}
		
		if(!hasActionErrors()) {
			if("portfolioLists".equals(bean.getReqFrom())){
		    	portfolioList=pservice.getPortFolio(bean.getFromdate(), bean.getTodate(), bean.getRep(), bean.getProductID(), branchCode, "", "",bean.getQuoteno());
			}
		}
		 return "portfolioAjax";
	}
	public String getViewList1()
	{
		bean.setOptionMode("viewList");
		//Object[] val={bean.getViewdate(),branchCode};
		//viewList=pservice.getViewList(val);
		viewList=pservice.getPortfolioByDate(bean.getViewdate(), bean.getRep(), bean.getProductID(), branchCode, "", "");
		if(StringUtils.isNotBlank(bean.getFromdate()))
			bean.setFromdate(dateFormat(bean.getFromdate()));
		if(StringUtils.isNotBlank(bean.getTodate()))
			bean.setTodate(dateFormat(bean.getTodate()));
		return "portfolio";
	}
	public String view()
	{	
		//Object[] val={bean.getViewdate(),branchCode};
		//viewList=pservice.getViewList(val);
		viewList=pservice.getPortfolioByDate(bean.getViewdate(), bean.getRep(), bean.getProductID(), branchCode, "", "");
		return "portfolioAjax";
	}
	public String updatePolicy()
	{
		int i;
		List<String> policyno=bean.getPolicyNo();
		List<String> status=bean.getStatus();
		if(bean.getStatus()!=null){
		for(i=0;i<policyno.size();i++)
		{
			if("D".equalsIgnoreCase(status.get(i)))
			{
				Object[] val={status.get(i),policyno.get(i),branchCode};
				pservice.updatePortfolioPolicyCancelStatus(val);
			}
	       }
		}
		viewList=pservice.getPortfolioByDate(bean.getViewdate(), bean.getRep(), bean.getProductID(), branchCode, "", "");
		bean.setOptionMode("viewList");
		return "portfolio";
	}
	public String treaty(){
		String returnResult="treatyChange";
		if("view".equals(bean.getReqFrom1())){
			pservice.getPolicyTreatyInfo(bean);
		}if("save".equals(bean.getReqFrom1())){
			if(StringUtils.isBlank(bean.getRiCode()))
				addActionError("Please Select RI Code");
			if(StringUtils.isBlank(bean.getRiCodeValue()))
				addActionError("Please Select RI Code Value");
			if(!hasActionErrors()){
				int count=pservice.saveTreaty(bean);
				if(count>0){
					if("F".equals(bean.getRiCode())){
						pservice.facultativeInfo(bean);
						returnResult="faculative";
						bean.setRiList(pservice.getRiList());
					}else
						addActionError("RI information modified successfully.");
				}else
					addActionError("Error occured");
			}
		}
		return returnResult;
	}
	
	public String facultative(){
		try{
			validFacultative();
			if(!hasActionErrors()){
				String branchWiseCountry=pservice.getbranchWiseCountry(branchCode);
				List<String> riInsSi1=new ArrayList<String>();
				List<String> riInsNetPrem1=new ArrayList<String>();
				List<String> riInsCommission1=new ArrayList<String>();
				List<String> riInsTax1=new ArrayList<String>();
				List<String> riInsFixedStamp1=new ArrayList<String>();
				List<String> riInsSlipAmt1=new ArrayList<String>();
				Double totalSum=0.0, totalNet=0.0;
				Double si=0.0, remainSI=0.0, netPrem=0.0, remainNetPrem=0.0;
				for(int i=0; i<bean.getRiInsCode().size(); i++){
					si=Double.parseDouble(PolicyGenerationDAO.round(String.valueOf(Double.parseDouble(bean.getTotalSI())*(Double.parseDouble(bean.getRiInsShare().get(i))/100)), "1", "1",branchWiseCountry));
					riInsSi1.add(String.valueOf(si));
					totalSum+=si;
					netPrem=Double.parseDouble(PolicyGenerationDAO.round(String.valueOf(Double.parseDouble(bean.getTotalNetPrem())*(Double.parseDouble(bean.getRiInsShare().get(i))/100)), "1", "1",branchWiseCountry));;
					riInsNetPrem1.add(String.valueOf(netPrem));
					totalNet+=netPrem;
					riInsCommission1.add(PolicyGenerationDAO.round(String.valueOf(Double.parseDouble(bean.getCommission())*(Double.parseDouble(bean.getRiInsCommRate().get(i))/100)), "1", "1",branchWiseCountry));
					riInsTax1.add("0");
					riInsFixedStamp1.add("0");
					riInsSlipAmt1.add("0");
				}
				bean.setRiInsSI(riInsSi1);
				bean.setRiInsNetPrem(riInsNetPrem1);
				bean.setRiInsCommission(riInsCommission1);
				bean.setRiInsTax(riInsTax1);
				bean.setRiInsSI(riInsSi1);
				bean.setRiInsFixedStamp(riInsFixedStamp1);
				
				remainSI=Double.parseDouble(bean.getTotalSI())-totalSum;
				remainNetPrem=Double.parseDouble(bean.getTotalNetPrem())-totalNet;
				
				bean.setFsurPlusSI(PolicyGenerationDAO.round(String.valueOf(remainSI*(Double.parseDouble(bean.getFsurPlus())/100)), "1", "1",branchWiseCountry));
				bean.setSsurPlusSI(PolicyGenerationDAO.round(String.valueOf(remainSI*(Double.parseDouble(bean.getSsurPlus())/100)), "1", "1",branchWiseCountry));
				bean.setFsurPlusNetPrem(PolicyGenerationDAO.round(String.valueOf(remainNetPrem*(Double.parseDouble(bean.getSsurPlus())/100)), "1", "1",branchWiseCountry));
				bean.setSsurPlusNetPrem(PolicyGenerationDAO.round(String.valueOf(remainNetPrem*(Double.parseDouble(bean.getSsurPlus())/100)), "1", "1",branchWiseCountry));
				
				remainSI=remainSI-Double.parseDouble(bean.getFsurPlusSI())-Double.parseDouble(bean.getSsurPlusSI());
				remainNetPrem=remainNetPrem-Double.parseDouble(bean.getFsurPlusNetPrem())-Double.parseDouble(bean.getSsurPlusNetPrem());
				
				bean.setCompRetentCumSI(String.valueOf(remainSI*(Double.parseDouble(bean.getCompRetentShare())/100)));
				bean.setQuotaCumSI(String.valueOf(remainSI*(Double.parseDouble(bean.getQuotaShare())/100)));
				bean.setFsurPlusCumSI(bean.getFsurPlusSI());
				bean.setSsurPlusCumSI(bean.getSsurPlusSI());
				
				bean.setCompRetentSI(String.valueOf(remainSI*(Double.parseDouble(bean.getCompRetentShare())/100)));
				bean.setQuotaSI(String.valueOf(remainSI*(Double.parseDouble(bean.getQuotaShare())/100)));
				
				bean.setCompRetentNetPrem(PolicyGenerationDAO.round(String.valueOf(remainNetPrem*(Double.parseDouble(bean.getCompRetentShare())/100)), "1", "1",branchWiseCountry));
				bean.setQuotaNetPrem(PolicyGenerationDAO.round(String.valueOf(remainNetPrem*(Double.parseDouble(bean.getQuotaShare())/100)), "1", "1",branchWiseCountry));
				
				pservice.insertFacultativeinfo(bean); 
				bean.setReqFrom1("faculative");
				pservice.facultativeInfo(bean);
				
			} else{
				bean.setValidYN("Y");
				bean.setRiList(pservice.getRiList());
				pservice.facultativeInfo(bean);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "faculative";
	}
	public String facultativeBK(){
		bean.setReqFrom1("");
		return "faculative";
	}
	
	public void validFacultative(){
		if(StringUtils.isBlank(bean.getYearVal()))
			addActionError("Please enter Year");
		if(StringUtils.isBlank(bean.getCrt()))
			addActionError("Please enter CRT");
		if(StringUtils.isBlank(bean.getDateVal()))
			addActionError("Please select Date");
		List<String> riInsValue1=new ArrayList<String>();
		List<String> riInsShare1=new ArrayList<String>();
		List<String> riInsCommRate1=new ArrayList<String>();
		int count=1;
		double totalshare=0;
		for(int i=0; i<bean.getRiInsCode().size();i++){
			if(StringUtils.isNotBlank(bean.getRiInsCode().get(i)) || StringUtils.isNotBlank(bean.getRiInsShare().get(i)) || StringUtils.isNotBlank(bean.getRiInsCommRate().get(i))){
				if(StringUtils.isBlank(bean.getRiInsCode().get(i)))
					addActionError("Please select Reinsurance Cover in Row "+count);
				if(StringUtils.isBlank(bean.getRiInsShare().get(i)))
					addActionError("Please enter Share(%) in Row "+count);
				else if(!validation.decimalValidate(bean.getRiInsShare().get(i)))
					addActionError("Please enter valid Share(%) in Row "+count);
				if(StringUtils.isBlank(bean.getRiInsCommRate().get(i)))
					addActionError("Please enter Commission Rate(%) Cover in Row "+count);
				else if(!validation.decimalValidate(bean.getRiInsCommRate().get(i)))
					addActionError("Please enter valid Commission Rate(%) in Row "+count);
				if(riInsValue1.contains(bean.getRiInsCode().get(i)))
					addActionError("Dublicate Reinsurance Cover in Row "+count);
				riInsValue1.add(bean.getRiInsCode().get(i));
				riInsShare1.add(bean.getRiInsShare().get(i));
				riInsCommRate1.add(bean.getRiInsCommRate().get(i));
				if(!hasActionErrors())
					totalshare+=(Double.parseDouble(bean.getRiInsShare().get(i)));
				count+=1;
			}
		}
		bean.setRiInsCode(riInsValue1);
		bean.setRiInsShare(riInsShare1);
		bean.setRiInsCommRate(riInsCommRate1);
		
		if(totalshare>100)
			addActionError("Total ReInsurance cover should not exceeds 100%");
		if(StringUtils.isBlank(bean.getFsurPlus()))
			addActionError("Please enter First Surplus(%)");
		else if(!validation.decimalValidate(bean.getFsurPlus()))
			addActionError("Please enter valid First Surplus(%)");
		if(StringUtils.isBlank(bean.getSsurPlus()))
			addActionError("Please enter Second Surplus(%)");
		else if(!validation.decimalValidate(bean.getSsurPlus()))
			addActionError("Please enter valid Second Surplus(%)");
		if(StringUtils.isBlank(bean.getCompRetentShare()))
			addActionError("Please enter Company Retention(%)");
		else if(!validation.decimalValidate(bean.getCompRetentShare()))
			addActionError("Please enter valid Company Retention(%)");
		if(StringUtils.isBlank(bean.getQuotaShare()))
			addActionError("Please enter Quote Share(%)");
		else if(!validation.decimalValidate(bean.getQuotaShare()))
			addActionError("Please enter valid Quote Share(%)");
		if(!hasActionErrors()){
			if(Double.parseDouble(bean.getCompRetentShare())+Double.parseDouble(bean.getQuotaShare())!=100)
				addActionError("Sum of Company Retention and Quota Share should be equals to 100%");
		}
	}
	
	public String treatyBk(){
		try{
			pservice.facultativeInfo(bean);
			bean.setRiList(pservice.getRiList());
			bean.setReqFrom1("");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "faculative";
	}
	public String dateFormat(String name){
		String arr[]=name.split("/");
		 return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
	public String payment()
	{
		return "paymentReportAdmin";
	}
	
	public String getPayListAjax() {
		if(StringUtils.isBlank(bean.getQuoteno()) && StringUtils.isBlank(bean.getFromdate()) && StringUtils.isBlank(bean.getTodate())) {
			addActionError("Please atleast one to Proceed");
		}else if(StringUtils.isBlank(bean.getFromdate()) && StringUtils.isBlank(bean.getQuoteno())) {
			addActionError("Please Select the From Date");
		}else if(StringUtils.isBlank(bean.getTodate()) && StringUtils.isBlank(bean.getQuoteno())) {
			addActionError("Please Select the To Date");
		}
		
		if(!hasActionErrors()) {
			if("paymentsLists".equals(bean.getReqFrom())){
		    	portfolioList=pservice.getPaymentList(bean.getFromdate(), bean.getTodate(), bean.getRep(), bean.getProductID(), branchCode, "", "",bean.getQuoteno());
			}
		}
		 return "portfolioAjax";
	}
	public String viewPay() {
		if(StringUtils.isNotBlank(bean.getMerchantReference())) {
			pservice.setPaymentDetails(bean);
		}else
			return payment();
		return "viewpaymentmaster";
	}
	public String submitPay() {
		pservice.updateSubmitPay(bean);
		return payment();
	}
}

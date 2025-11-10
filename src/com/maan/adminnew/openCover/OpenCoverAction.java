package com.maan.adminnew.openCover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import rsa.opencoverpdf.ScheduleBean;
import com.maan.adminnew.common.CommonService;

import com.maan.common.Validation;
import com.maan.opencover.bean.OpenCoverDocGenerator;
import com.maan.opencover.bean.opencoverSummary;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OpenCoverAction extends ActionSupport implements ModelDriven<OpenCoverBean>
{
	private static final long serialVersionUID = 1L;
	private OpenCoverBean bean = new OpenCoverBean();
	private CommonService cservice=new CommonService();
	Validation validation=new Validation();
	OpenCoverService service=new OpenCoverService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	HttpServletRequest request= ServletActionContext.getRequest();
	HttpServletResponse responce= ServletActionContext.getResponse();
	List<Map<String, Object>> transDetList=null;
	ServletContext context=request.getSession().getServletContext();
	String branchCode=(String)session.get("BranchCode");
	String mainloginId=(String)session.get("user");
	String appId=(String)session.get("AppId");
	private List <Map<String, Object>> regQuoteList;
	private List <Map<String, Object>> portfolioList;
	private List <Map<String, Object>> customerList;
	
	public List<Map<String, Object>> getBrokerList() {
		return cservice.getAdminBrokerList(branchCode, appId);
	}
	public List<Map<String, Object>> getBusinessTypeList() {
		return cservice.getBusinessTypeList(branchCode);
	}
	public List<Map<String, Object>> getLcList() {
		return service.getLcList(branchCode,bean.getPolicynumber());
	}
	public List<Map<String, Object>> getCurrecyList() {
		return cservice.getCurrencyList();
	}
	public List<Map<String, Object>> getRegQuoteList() {
		return regQuoteList;
	}
	public void setRegQuoteList(List<Map<String, Object>> regQuoteList) {
		this.regQuoteList = regQuoteList;
	}
	public List<Map<String, Object>> getPortfolioList() {
		return portfolioList;
	}
	public void setPortfolioList(List<Map<String, Object>> portfolioList) {
		this.portfolioList = portfolioList;
	}

	public List<Map<String, Object>> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<Map<String, Object>> customerList) {
		this.customerList = customerList;
	}
	
	public String opencover() {
		if(bean.getAgencyCode()!=null){
			portfolioList=service.getPortfolio(bean.getLogin_Id());
		}
		return "brokeroc";
	}
	
	public String lcDetail() {
		return "lcdetail";
	}
	
	public void schedule(){
		try{
			String mode=request.getContextPath().indexOf("test")!=-1?"Test":"Live";
			String baseFilePath="/"+(mode.equalsIgnoreCase("Test")?"testpolicy":bean.getDocType())+"pdf/"+bean.getDocNo()+"OpenCover"+bean.getDocType()+".pdf";
			String filePath=context.getRealPath(baseFilePath);
			List<HashMap<String,Object>> list=opencoverSummary.getOpenCoverPolicyInfo(bean.getPolicynumber(), mainloginId, bean.getAmendId());
			OpenCoverDocGenerator docGen=new OpenCoverDocGenerator();
			String fontPath = request.getSession().getServletContext().getRealPath("/" + "ScheduleFont/arial.ttf");
			String imageURL=request.getSession().getServletContext().getRealPath("/"+ "/images/");
			if("debit".equalsIgnoreCase(bean.getDocType()))
			{
				docGen.writeDebitPDF(fontPath, bean.getPolicynumber(), filePath, imageURL, mode, list);
			}else if("credit".equalsIgnoreCase(bean.getDocType()))
			{
				docGen.writeCreditPDF(fontPath, bean.getPolicynumber(), filePath, imageURL, mode, list);
			}else if("schedule".equalsIgnoreCase(bean.getDocType()))
			{
				ScheduleBean bean1=new ScheduleBean();
				bean1.getPolicyInfoByProposalNo(branchCode, bean.getProposalNo(), imageURL, bean.getEndtstatus());
				bean1.setFontPath(fontPath);
				bean1.setFilePath(filePath);
				bean1.setOpenCoverNo(bean.getPolicynumber());
				if("Test".equalsIgnoreCase(mode))
					bean1.setDisplayText("TEST POLICY");
				bean1.createSchedulePDF();
				responce.sendRedirect(request.getContextPath()+baseFilePath);
			}
			return;
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	/*public String getregQuote() {
    	logger.info("getregQuote()===>Enter");
		String returnResult="opencover";
		regQuoteList=service.getregQuote(bean.getBroker());
		if("ajax".equals(bean.getFrom1())){
			returnResult="adminAjax";
			portfolioList=service.getPortfolio(bean.getBroker());
		}
    	logger.info("getregQuote()===>Exit");
		return returnResult;
	}
	public String getPortfolio() {
    	logger.info("getPortfolio()===>Enter");
		String returnResult="opencover";
		portfolioList=service.getPortfolio(bean.getBroker());
		if("ajax".equals(bean.getFrom1())){
			returnResult="adminAjax";
		}
    	logger.info("getPortfolio()===>Exit");
		return returnResult;
	}
	
	public String customerPopup() {
    	logger.info("customerPopup()===>Enter");
		String returnResult="customerSelect";
		customerList=service.getCustomerList(bean.getBroker(),appId);
    	logger.info("customerPopup()===>Exit");
		return returnResult;
	}
*/
	public OpenCoverBean getModel() {
		return bean;
	}
}
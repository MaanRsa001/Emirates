package com.maan.adminnew.referal;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.adminnew.common.CommonService;
import com.maan.common.LogUtil;
import com.maan.common.Validation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ReferalAction extends ActionSupport implements ModelDriven<ReferalBean>
{
	private static final Logger logger = LogUtil.getLogger(ReferalAction.class);
	private static final long serialVersionUID = 1L;
	private ReferalBean bean = new ReferalBean();
	private CommonService cservice=new CommonService();
	Validation validation=new Validation();
	ReferalService service=new ReferalService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String login_id=(String)session.get("user");
	private List <Map<String, Object>> occList;
	private List <Map<String,Object>> productDet;
	private List <Map<String, Object>> occListP;
	private List <Map<String, Object>> occListA;
	private List <Map<String, Object>> occListR;
	private List <Map<String, Object>> policyList;
	
	public List<Map<String, Object>> getPolicyList() {
		return policyList;
	}
	public void setPolicyList(List<Map<String, Object>> policyList) {
		this.policyList = policyList;
	}
	public List<Map<String, Object>> getOccListP() {
		return occListP;
	}
	public void setOccListP(List<Map<String, Object>> occListP) {
		this.occListP = occListP;
	}
	public List<Map<String, Object>> getOccListA() {
		return occListA;
	}
	public void setOccListA(List<Map<String, Object>> occListA) {
		this.occListA = occListA;
	}
	public List<Map<String, Object>> getOccListR() {
		return occListR;
	}
	public void setOccListR(List<Map<String, Object>> occListR) {
		this.occListR = occListR;
	}
	public List<Map<String, Object>> getOccList() {
		return occList;
	}
	public void setOccList(List<Map<String, Object>> occList) {
		this.occList = occList;
	}
	public List<Map<String,Object>> getProductDet() {
		return productDet;
	}
	public void setProductDet(List<Map<String,Object>> productDet) {
		this.productDet = productDet;
	}
	public ReferalBean getModel() {
		return bean;
	}
	
	public String getOCList() {
		logger.info("ENTER==>getOCList()");
		productDet=cservice.getProductsDET(branchCode,"");
		/*if(bean.getProductID()==null){
			bean.setProductID("3");
		}
		occListP=service.getOCList("pending",bean.getProductID(), branchCode);
		occListA=service.getOCList("approved",bean.getProductID(), branchCode);
		occListR=service.getOCList("rejected",bean.getProductID(), branchCode);*/
		logger.info("EXIST==>getOCList()");
		return "referalList";
	}
	
	public String getOCAjax() {
		String returnResult="referalList";
		productDet=cservice.getProductsDET(branchCode,"");
		if(StringUtils.isNotBlank(bean.getReqFrom()))
			occList=service.getOCList(bean.getReqFrom(),bean.getProductID(), branchCode, bean.getAgencyCode());
		if(bean.getRdate()!=null && !"".equals(bean.getRdate())){
			if("".equals(bean.getFrom1()) || "ajax".equalsIgnoreCase(bean.getFrom1()))
				returnResult="adminAjax";
			policyList=service.getPolicyList(branchCode, bean.getRdate(), bean.getProductID(), bean.getReqFrom(), bean.getAgencyCode());
		}else if("".equals(bean.getFrom1()) || "ajax".equalsIgnoreCase(bean.getFrom1())){
			returnResult="adminAjax";
    	}else{
    		if("pending".equals(bean.getReqFrom())){
    			occListP=occList;
    			occListA=service.getOCList("approved",bean.getProductID(), branchCode, bean.getAgencyCode());
    			occListR=service.getOCList("rejected",bean.getProductID(), branchCode, bean.getAgencyCode());
    		}else if("approved".equals(bean.getReqFrom())){
    			occListA=occList;
    			occListP=service.getOCList("pending",bean.getProductID(), branchCode, bean.getAgencyCode());
    			occListR=service.getOCList("rejected",bean.getProductID(), branchCode, bean.getAgencyCode());
    		}else if("rejected".equals(bean.getReqFrom())){
    			occListR=occList;
    			occListP=service.getOCList("pending",bean.getProductID(), branchCode, bean.getAgencyCode());
    			occListA=service.getOCList("approved",bean.getProductID(), branchCode, bean.getAgencyCode());
    		}
    	}
		return returnResult;
	}
}
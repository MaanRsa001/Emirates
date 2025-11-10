package com.maan.claim;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ClaimAction extends ActionSupport implements
		ModelDriven<ClaimBean> {
	private static final Logger logger = LogUtil.getLogger(ClaimAction.class);
	private static final long serialVersionUID = 1L;
	private ClaimService cservice = new ClaimService();
	ClaimBean bean=new ClaimBean();
	public String Details() {
		return "claimDetail";
	}

	public String SearchPolicyNo() {
		validatePolicyNo();
		if (hasActionErrors()) {
			return "claimDetail";
		} else {
			String policyNo=bean.getPolicyNo();
			cservice.getclaimDetails(bean,policyNo);
			return "viewClaimDetails";
		}
	}

	public String save() {
		System.out.println(bean.getPolicyNo());
		String policyNo=bean.getPolicyNo();
		validateDates();
		if (hasActionErrors()) {
			cservice.getclaimDetails(bean,policyNo);
			bean.setPolicyNo(policyNo);
			return "viewClaimDetails";			
		} else {
			cservice.saveLossDetails(bean);
			addActionMessage("Loss Details are saved successfully");
			bean.setPolicyNo("");
			return "claimDetail";
		}
	}

	private void validateDates() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yy");
		if(StringUtils.isEmpty(bean.getLossDate())){
			addActionError(getText("error.lossDate"));
		}
		else{
			try {
				System.out.println(bean.getStartDate());
				System.out.println(bean.getLossDate());
				Date d1 = (Date) format.parse(bean.getStartDate());
				Date d2 = (Date) format1.parse(bean.getLossDate());
				/*Date d3=new Date(); //sysdate
				int diffInDays = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
				int diffInDays1 = (int) ((d3.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24)); //sysdate-lossdate
				if (diffInDays < 0) {
					addActionError(getText("error.validLossDate"));
				}
				if(diffInDays1<0){
					addActionError(getText("error.validLossDate"));
				}*/
				if(d1.compareTo(d2)>0) {
					addActionError(getText("error.validLossDate"));
				}
			} catch (Exception e) {
				logger.debug("EXCEPTION @ { " + e + " }");
			}
		}
	}

	private void validatePolicyNo() {
		int count = cservice.countPolicyNo(bean);
		if (count <= 0) {
			addActionError(getText("Enter correct policy number"));
		}
		int count1=cservice.countClaimPolicyNo(bean);
		if(count1>0)
		{
			addActionError("Claim Pending Close The Claim");
		}
	}

	public String Intimation() {
		String result=null;
		System.out.println(bean.getReqFrom());
		System.out.println(bean.getPolicyNo());
		if((bean.getReqFrom()==null || bean.getReqFrom()=="Pending") && (bean.getRdate()==null || bean.getRdate().equals(""))){
			bean.setClaimIntimation(cservice.getClaimIntimation(bean));
			result="adminClaimIndimation";
		}
		else if(bean.getReqFrom().equals("claimPending") && bean.getRdate()!=null){
			bean.setClaimIntimation(cservice.getClaimPendingDetails(bean));
			result="adminAjax";
		}else if(bean.getReqFrom().equals("View") && bean.getPolicyNo()!=null){
			cservice.getclaimIntimationDetails(bean);
			result="viewDetail";
		}
		else if(bean.getReqFrom().equals("UpdateRemarks") && bean.getPolicyNo()!=null){
			int update=cservice.updateClaimIntimation(bean);
			String mode=null;
			bean.setReqFrom(mode);
			Intimation();
			result="adminClaimIndimation";
		}
		return result;
	}

	public ClaimBean getModel() {
		return bean;
	}

}

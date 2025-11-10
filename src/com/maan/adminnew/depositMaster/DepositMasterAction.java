package com.maan.adminnew.depositMaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang.StringUtils;

import com.maan.adminnew.common.CommonService;
import com.maan.common.LogUtil;
import com.maan.common.Validation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.LocalizedTextProvider;
import com.opensymphony.xwork2.ModelDriven;

public class DepositMasterAction extends ActionSupport implements ModelDriven<DepositMasterBean>{
	private static final Logger logger = LogUtil.getLogger(DepositMasterAction.class);
	private static final long serialVersionUID = 1L;
	Map<String, Object> session=ActionContext.getContext().getSession();
	DepositMasterService service=new DepositMasterService();
	CommonService cservice=new CommonService();
	DepositMasterBean bean=new DepositMasterBean();
	String branchCode=(String)session.get("BranchCode");
	String login_id=(String)session.get("user");
	String appId=(String)session.get("AppId");
	List<DepositMasterBean> depositList=new ArrayList<DepositMasterBean>();
	List<Map<String, Object>> detailList=new ArrayList<Map<String, Object>>();
	private Integer page= 0;
	private int id;
	private Integer rows=0;
	private String sord;
	private String sidx;
	private String searchField;
	private String searchString;
	private String searchOper;
	private Integer total= 0;
	private Integer records= 0;
	private String reqFrm;
	private String mode;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
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
	public List<Map<String, Object>> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<Map<String, Object>> detailList) {
		this.detailList = detailList;
	}
	public String getReqFrm() {
		return reqFrm;
	}
	public void setReqFrm(String reqFrm) {
		this.reqFrm = reqFrm;
	}
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String,Object>> getProductList(){
		return cservice.getProductsDET(branchCode,"");
	}
	
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String,Object>> getCustomerList(){
		return service.getCustomerList(branchCode, bean.getAgencyCode());
	}
	
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String,Object>> getBrokerList(){
		return service.getBrokerList(branchCode);
	}
	
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String,Object>> getOpenCoverList(){
		return service.getOpenCoverList(bean.getCustomerId(), branchCode);
	}
	
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<DepositMasterBean> getCbcList(){
		if(StringUtils.isNotBlank(bean.getCbcNo()))
			return service.getCbcList(branchCode, "", "", "", mode, bean);
		else
			return null;
	}
	
	public List<DepositMasterBean> gridRaja(List<DepositMasterBean> list){
		List<DepositMasterBean>selectedList =  new ArrayList<DepositMasterBean>();
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
		logger.info("selectedList===>"+selectedList.size());
		return selectedList;
	}
	
	public List<DepositMasterBean> getGridReport(){
		if("chequeDetail".equals(mode))
			depositList=service.getDepositList(branchCode, searchField, searchString, searchOper,mode, bean);
		else
			depositList=service.getCbcList(branchCode, searchField, searchString, searchOper,"view", bean);
		return gridRaja(depositList);
	}
	
	public String getJson(){
		return SUCCESS;
	}
	
	public String edit(){
		if(bean.getCbcNo()!=null && "edit".equals(mode))
			depositList=service.getCbcList(branchCode, searchField, searchString, searchOper,mode, bean);
		return "depositList";
	}
	
	public String view(){
		mode="view";
		return "depositList";
	}
	
	public DepositMasterBean getModel() {
		return bean;
	}
	
	public String custmerSelect(){
		bean.setOption("customerSelection");
		return "customerOption";
	}
	
	public String ocSelect(){
		return "openCoverSelect";
	}
	
	public String editCheque(){
		if(bean.getDepositNo()!=null && "editCheque".equals(bean.getOption()))
			service.getDepositList(branchCode, searchField, searchString, searchOper,mode, bean);
		return "addCheque";
	}
	public String getAjax(){
		logger.info("reqFrom==>"+bean.getReqFrom());
		return "adminajax";
	}
	
	public String chequeDetails(){
		mode="chequeDetail";
		return "depositList";
	}
	
	public String depositDetail(){
		mode="chequeDetail1";
		detailList=service.getChequeDetails(bean.getDepositNo(), branchCode);
		return "depositDetail";
	}
	
	public String saveCBC(){
		try{
			cbcvalidate();
			if(!hasActionErrors()){
				service.saveCBC(bean, this);
				if("new".equals(mode))
					mode="newSuccess";
				else
					mode="editSuccess";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "depositList";
	}
	
	public String saveCheque(){
		try{
			chequevalidate();
			if(!hasActionErrors()){
				service.saveCheque(bean, this);
				if("newCheque".equals(bean.getOption()))
					bean.setOption("newChequeSuccess");
				else
					bean.setOption("editChequeSuccess");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "customerOption";
	}
	
	public void chequevalidate(){
		if(StringUtils.isBlank(bean.getChequeNo()))
			addActionError(getText("error.chequeno.required"));
		else if(!StringUtils.isNumeric(bean.getChequeNo()))
			addActionError(getText("error.chequeno.invalid"));
		if(StringUtils.isBlank(bean.getChequeAmt()))
			addActionError(getText("error.chequeAmt.required"));
		else if(!Validation.decimalValidate(bean.getChequeAmt()))
			addActionError(getText("error.chequeAmt.invalid"));
		else if(Double.parseDouble(bean.getChequeAmt())<Double.parseDouble(StringUtils.isBlank(bean.getDepositUtilsed())?"0":bean.getDepositUtilsed()))
			addActionError(getText("error.chequeAmt.above.utilized")+" "+bean.getDepositUtilsed());
		if(StringUtils.isBlank(bean.getChequeBank()))
			addActionError(getText("error.chequeBank.required"));
		else if(!Validation.nameValidate(bean.getChequeBank()))
			addActionError(getText("error.chequeBank.invalid"));
		if(StringUtils.isBlank(bean.getChequeDate()))
			addActionError(getText("error.chequeDate.required"));
		if(!"newCheque".equals(bean.getOption())){
			if(StringUtils.isBlank(bean.getReceiptNo()))
				addActionError(getText("error.receiptNo.required"));
			else if(!StringUtils.isNumeric(bean.getReceiptNo()))
				addActionError(getText("error.receiptNo.invalid"));
		}
	}
	
	public void cbcvalidate(){
		if(StringUtils.isBlank(bean.getCbcNo()))
			addActionError(getText("error.cbcno.required"));
		else if(!StringUtils.isNumeric(bean.getCbcNo()))
			addActionError(getText("error.cbcno.invalid"));
		else if(!"edit".equals(mode) && service.getCbcList(branchCode, "cbcNo", bean.getCbcNo(), "eq", "view", bean).size()>0)
			addActionError(getText("error.cbcno.already.exist"));
		if(StringUtils.isBlank(bean.getDepositType()))
			addActionError(getText("error.depositType.required"));
		if(StringUtils.isBlank(bean.getAgencyCode()))
			addActionError(getText("error.agencyCode.required"));
		if(StringUtils.isBlank(bean.getAgencyCode()))
			addActionError(getText("error.agencyCode.required"));
		if(StringUtils.isBlank(bean.getCustomerOpt()))
			addActionError(getText("error.customeroption.required"));
		if(StringUtils.isBlank(bean.getCustomerId()))
			addActionError(getText("error.customerid.required"));
		if(bean.getProductsId()==null || bean.getProductsId().length<=0)
			addActionError(getText("error.product.required"));
		else if(bean.getProductsId()!=null && bean.getProductsId().length>0){
			for(String pid:bean.getProductsId()){
				if("11".equalsIgnoreCase(pid) && StringUtils.isBlank(bean.getOcNo()))
					addActionError(getText("error.ocno.required"));
			}
		}if(!hasActionErrors()){
			String cbc=service.getCbcValid(this, bean);
			if(StringUtils.isNotBlank(cbc)){
			    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
				addActionError(ltp.findDefaultText("error.deposit.already.exist", Locale.ENGLISH,new Object[]{cbc}));
			}
		}
	}
}

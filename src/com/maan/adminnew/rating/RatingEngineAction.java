package com.maan.adminnew.rating;

import java.util.Date;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.maan.services.util.runner;
import com.maan.common.ExcelDownload;
import com.maan.common.LogUtil;
import com.maan.adminnew.rating.RatingUploadService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.dao.DataIntegrityViolationException;
import com.maan.adminnew.common.CommonService;

import com.maan.common.StringHelper;
import com.maan.common.Validation;
import com.maan.common.util.StringUtil;
import com.maan.quotation.dao.QuotationDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RatingEngineAction extends ActionSupport implements ModelDriven<RatingEngineBean> {
	private static final Logger logger = LogUtil.getLogger(RatingEngineAction.class);
	private static final long serialVersionUID = 1L;
	private RatingEngineBean bean = new RatingEngineBean();
	Validation validation = new Validation();
	RatingEngineService rservice = new RatingEngineService();
	CommonService cservice = new CommonService();
	final RatingUploadService service=new RatingUploadService();
	Map<String, Object> session = ActionContext.getContext().getSession();
	String branchCode = (String) session.get("BranchCode");
	String appId = (String) session.get("AppId");
	String login_id = (String) session.get("user");
	HttpServletRequest request= ServletActionContext.getRequest();
	ServletContext context=request.getSession().getServletContext();
	private String FILE_PATH=context.getRealPath(getText("EXCEL_UPLOAD_PATH"));
	private String EXCEL_EXCHANGE_MASTER_PATH=context.getRealPath(getText("EXCEL_EXCHANGE_MASTER_PATH"));
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private List<Map<String, Object>> transportList;
	private List<Map<String, Object>> conveyanceList;
	private List<Map<String, Object>> countryList;
	private List<Map<String, Object>> bankList;
	private List<Map<String, Object>> materialList;
	private List<Map<String, Object>> warrateList;
	private List<Map<String, Object>> saletermList;
	private List<Map<String, Object>> toleranceList;
	private List<Map<String, Object>> comExList;
	private List<Map<String, Object>> vesselList;
	private List<Map<String, Object>> extraCoverList;
	private List<Map<String, Object>> cityList;
	private List<Map<String, Object>> constantList;
	private List<Map<String, Object>> countryDetList;
	private List<Map<String, Object>> coverages;
	private List<Map<String, Object>> covey;
	private List<Map<String, Object>> commodityList;
	private List<Map<String, Object>> currencyList;
	private List<Map<String, Object>> exchangeList;
	private List<Map<String, Object>> coverList;
	private List<Map<String, Object>> errorList;
	private List<Map<String, Object>> clauseIDList;
	private List<Map<String, Object>> warrantyList;
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
	private static final String OUTPUT_DATE_FORMAT= "MM/dd/yyyy hh:mm:ss";
	private static final String CVS_SEPERATOR_CHAR="\t";
	private static final String NEW_LINE_CHARACTER="\r\n";
	private InputStream inputStream;
	private List<Map<String, Object>> transDetList;
	private String tranId;
	private String downloadType;

	public String getDownloadType() {
		return downloadType;
	}
	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
	}
	public String getTranId() {
		return tranId;
	}
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public List<Map<String, Object>> getTransDetList() {
		return transDetList;
	}
	public void setTransDetList(List<Map<String, Object>> transDetList) {
		this.transDetList = transDetList;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCovey() {
		return covey;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCoverages() {
		return coverages;
	}
	/*@org.apache.struts2.json.annotations.JSON(serialize = false)
		public List<Map<String, Object>> getstagenames() {
		return stagenames;
	}*/
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCoverList() {
		return coverList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getExchangeList() {
		return exchangeList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCurrencyList() {
		return currencyList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCommodityList() {
		return commodityList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCountryDetList() {
		return countryDetList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getConstantList() {
		return constantList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCityList() {
		return cityList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getExtraCoverList() {
		return extraCoverList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getVesselList() {
		return vesselList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getComExList() {
		return comExList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getToleranceList() {
		return toleranceList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getSaletermList() {
		return saletermList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getWarrateList() {
		return warrateList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getBankList() {
		return bankList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getMaterialList() {
		return materialList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCountryList() {
		return countryList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getTransportList() {
		return transportList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getConveyanceList() {
		return conveyanceList;
	}
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
	public RatingEngineBean getModel() {
		return bean;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getWarrantyList() {
		return warrantyList;
	}
	//End

	/*public List<Map<String, Object>> getCommcategList(){
		return rservice.getCommcategList(branchCode);
	}*/

	/*public List<Map<String, Object>> getBaseRateList() {		
		return rservice.getBaserateCommodity(branchCode);		 
	}	*/

	/*public List<Map<String, Object>> getTransports() {
		return rservice.getTransports(branchCode);
	}*/
	
	public List<Map<String,Object>> getGoodsCategoryGroupList(){
		return new QuotationDAO().goodsCategoryGroupList();
	}

	public List<RatingEngineBean> gridList(List<RatingEngineBean> list){
		List<RatingEngineBean>selectedList =  new ArrayList<RatingEngineBean>();
		try{
			int to = (rows * page);
			int from = to - rows;
			records = list.size();
			if(to > records)
				to =records;

			for(int i=from;i<to;i++){
				selectedList.add(list.get(i));
			}
			/* if (selectedList != null && sord!= null && sord.equalsIgnoreCase("asc")) {
		            Collections.sort(selectedList, null);
		    }
		    if (sord!= null && sord.equalsIgnoreCase("desc")) {
		            Collections.sort(selectedList, null);
		            Collections.reverse(selectedList);
		    }*/
			total =(int) Math.ceil((double)records / (double)rows);
		}catch(Exception e){
			e.printStackTrace();
		}
		return selectedList;
	}
	
	public String jsonAjaxGrid() {
		List<RatingEngineBean> list=new ArrayList<RatingEngineBean>();
		
		if("conveyance".equalsIgnoreCase(menuType))
			list=rservice.getConveyList(branchCode,searchField, searchString, searchOper);
		else if("countrymaster".equalsIgnoreCase(menuType))
			list=rservice.getCountryList(searchField, searchString, searchOper);
		else if("country".equalsIgnoreCase(menuType))
			list=rservice.getCountryDetList(searchField, searchString, searchOper);
		else if("bankmaster".equalsIgnoreCase(menuType))
			list=rservice.getBankList(branchCode,searchField, searchString, searchOper);
		else if("materialmaster".equalsIgnoreCase(menuType))
			list=rservice.getMaterialsList(branchCode,  searchField,searchString,searchOper); 
		else if("warratemaster".equalsIgnoreCase(menuType))
			list=rservice.getWarrateList(searchField, searchString, searchOper);
		else if("commoditymaster".equalsIgnoreCase(menuType))
			list=rservice.getCommodityList(branchCode,searchField, searchString, searchOper);
		else if("covercommomaster".equalsIgnoreCase(menuType))
			list=rservice.getCovercommList(branchCode,searchField, searchString, searchOper);
		else if("categorymaster".equalsIgnoreCase(menuType))
			list=rservice.getCategoryList(branchCode,searchField, searchString, searchOper);
		else if("saletermmaster".equalsIgnoreCase(menuType))
			list=rservice.getSaleList(branchCode,searchField, searchString, searchOper);
		else if("tolerancemaster".equalsIgnoreCase(menuType))
			list=rservice.getToleList(branchCode,searchField, searchString, searchOper);
		else if("commodityexcess".equalsIgnoreCase(menuType))
			list=rservice.getComExList(branchCode,searchField, searchString, searchOper);
		else if("vesselmaster".equalsIgnoreCase(menuType))
			list=rservice.getVesselList(searchField, searchString, searchOper);
		else if("settlingagent".equalsIgnoreCase(menuType))
			list=rservice.getSettlingList(branchCode,searchField, searchString, searchOper);
		else if("exchangemaster".equalsIgnoreCase(menuType))
			list=rservice.getExchangeList(searchField, searchString, searchOper, branchCode);
		else if("currencymaster".equalsIgnoreCase(menuType))
			list=rservice.getCurrencyList(searchField, searchString, searchOper, branchCode);
		else if("extracover".equalsIgnoreCase(menuType))
			list=rservice.getExtraCoverList(branchCode,searchField, searchString, searchOper);
		else if("modeoftransport".equalsIgnoreCase(menuType))
			list=rservice.getTransportList(branchCode,searchField, searchString, searchOper);
		else if("warrantymaster".equalsIgnoreCase(menuType))
			list=rservice.getWarrantyList(branchCode,searchField, searchString, searchOper);
		else if("constantMaster".equalsIgnoreCase(menuType))
			list=rservice.getConstantMasterList(bean,branchCode,searchField, searchString, searchOper);
		else if("constantdetail".equalsIgnoreCase(menuType))
			list=rservice.getConstantList(bean,branchCode,searchField, searchString, searchOper);
		else if("exclusionmaster".equalsIgnoreCase(menuType))
			list=rservice.getExclusionList(branchCode,searchField, searchString, searchOper);
		else if("citymaster".equalsIgnoreCase(menuType))
			list=rservice.getCityList(searchField, searchString, searchOper);
		else if("wsrcc".equalsIgnoreCase(menuType))
			list=rservice.getWsrccList(bean,branchCode,searchField, searchString, searchOper);
		else if("covermaster".equalsIgnoreCase(menuType))
			list=rservice.getCoverList(branchCode,searchField, searchString, searchOper);
		else if("clause".equalsIgnoreCase(menuType))
			list=rservice.getClauseList(bean,branchCode,searchField, searchString, searchOper);
		else if("error".equalsIgnoreCase(menuType))
			list=rservice.getErrorList(branchCode,searchField, searchString, searchOper);
		else if("executivemaster".equalsIgnoreCase(menuType)) 
			list=rservice.getExecutiveMasterList(branchCode,searchField,searchString,searchOper);
//		gridJsonList = list;
//		setGridJsonList(list);
		bean.setGridJsonList(list);
		return "jsonAjax";
	}
	
	public List<RatingEngineBean> getGridList(){
		List<RatingEngineBean> list=new ArrayList<RatingEngineBean>();
		List<RatingEngineBean> gridJsonList=new ArrayList<RatingEngineBean>();
		if("conveyance".equalsIgnoreCase(menuType))
			list=gridList(rservice.getConveyList(branchCode,searchField, searchString, searchOper));
		else if("countrymaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getCountryList(searchField, searchString, searchOper));
		else if("country".equalsIgnoreCase(menuType))
			list=gridList(rservice.getCountryDetList(searchField, searchString, searchOper));
		else if("bankmaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getBankList(branchCode,searchField, searchString, searchOper));
		else if("materialmaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getMaterialsList(branchCode,  searchField,searchString,searchOper) ); 
		else if("warratemaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getWarrateList(searchField, searchString, searchOper));
		else if("commoditymaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getCommodityList(branchCode,searchField, searchString, searchOper));
		else if("covercommomaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getCovercommList(branchCode,searchField, searchString, searchOper));
		else if("categorymaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getCategoryList(branchCode,searchField, searchString, searchOper));
		else if("saletermmaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getSaleList(branchCode,searchField, searchString, searchOper));
		else if("tolerancemaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getToleList(branchCode,searchField, searchString, searchOper));
		else if("commodityexcess".equalsIgnoreCase(menuType))
			list=gridList(rservice.getComExList(branchCode,searchField, searchString, searchOper));
		else if("vesselmaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getVesselList(searchField, searchString, searchOper));
		else if("settlingagent".equalsIgnoreCase(menuType))
			list=gridList(rservice.getSettlingList(branchCode,searchField, searchString, searchOper));
		else if("exchangemaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getExchangeList(searchField, searchString, searchOper, branchCode));
		else if("currencymaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getCurrencyList(searchField, searchString, searchOper, branchCode));
		else if("extracover".equalsIgnoreCase(menuType))
			list=gridList(rservice.getExtraCoverList(branchCode,searchField, searchString, searchOper));
		else if("modeoftransport".equalsIgnoreCase(menuType))
			list=gridList(rservice.getTransportList(branchCode,searchField, searchString, searchOper));
		else if("warrantymaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getWarrantyList(branchCode,searchField, searchString, searchOper));
		else if("constantMaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getConstantMasterList(bean,branchCode,searchField, searchString, searchOper));
		else if("constantdetail".equalsIgnoreCase(menuType))
			list=gridList(rservice.getConstantList(bean,branchCode,searchField, searchString, searchOper));
		else if("exclusionmaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getExclusionList(branchCode,searchField, searchString, searchOper));
		else if("citymaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getCityList(searchField, searchString, searchOper));
		else if("wsrcc".equalsIgnoreCase(menuType))
			list=gridList(rservice.getWsrccList(bean,branchCode,searchField, searchString, searchOper));
		else if("covermaster".equalsIgnoreCase(menuType))
			list=gridList(rservice.getCoverList(branchCode,searchField, searchString, searchOper));
		else if("clause".equalsIgnoreCase(menuType))
			list=gridList(rservice.getClauseList(bean,branchCode,searchField, searchString, searchOper));
		else if("error".equalsIgnoreCase(menuType))
			list=gridList(rservice.getErrorList(branchCode,searchField, searchString, searchOper));
		else if("executivemaster".equalsIgnoreCase(menuType)) 
			list=gridList(rservice.getExecutiveMasterList(branchCode,searchField,searchString,searchOper));
		return list;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public String getTList() {
		logger.info("ENTER-->Method to getTList");
		//transportList = rservice.getAdminTransportList(branchCode);
		logger.info("getTList() - Exit" + login_id);
		return "transportList";
	}

	public String addNewCountryDet() {
		return "addNewCountryDet";
	}
	public String updateCountryDet() {
		validateCountryDet();

		if(!hasActionErrors()){
			String remarks="";
			String country_short_name="";
			String sp="";
			String ep="";
			String country_nat="";
			String startPlace="";
			String endPlace="";
			if("".equals(bean.getRemarks())||bean.getRemarks().equals(null))
			{remarks=" ";}
			else
			{remarks=bean.getRemarks();}

			if("".equals(bean.getCountryShortName())||bean.getCountryShortName().equals(null))
			{country_short_name="";}
			else
			{country_short_name=bean.getCountryShortName();}
			if("".equals(bean.getEp())||bean.getEp().equals(null))
			{ep=" ";}
			else
			{ep=bean.getEp();}
			if("".equals(bean.getSp())||bean.getSp().equals(null))
			{sp=" ";}
			else
			{sp=bean.getSp();}

			if(bean.getStartPlace().equals("1"))
				startPlace="Any Port";
			if ("".equals(bean.getEp()) || bean.getEp().equals(null)) 
				ep = " ";
			else
				ep = bean.getEp();

			if ("".equals(bean.getSp()) || bean.getSp().equals(null)) 
				sp = " ";
			else 
				sp = bean.getSp();

			if (bean.getStartPlace().equals("1")) 
				startPlace = "Any Port";
			else if (bean.getStartPlace().equals("2"))
				startPlace = "Warehouse";
			else if (bean.getStartPlace().equals("3"))
				startPlace = "Disabled";

			if (bean.getEndPlace().equals("1")) 
				endPlace = "Any Port";
			else if (bean.getEndPlace().equals("2"))
				endPlace = "Warehouse";
			else if (bean.getEndPlace().equals("3"))
				endPlace = "Disabled";

			if("edit".equals(bean.getMode())){
				String string="";
				boolean bo = validateDate(bean.getPrevdate());
				if(bo==false){
					//Object[] val={bean.getCountryID(),country_short_name,bean.getEff_date(),bean.getStatus(),bean.getWarRate(),endPlace,ep,country_nat,remarks,sp,startPlace,bean.getCode(),bean.getCountryDetID()};
					Object[] val={bean.getCountryID(),country_short_name,bean.getEff_date(),bean.getStatus(),bean.getWarRate(),endPlace,ep,country_nat,remarks,
							sp,startPlace,bean.getCode(),bean.getCrossvoyageyn(),bean.getCrossvoyageOrgin(),bean.getCrossvoyageDestn(),bean.getCountryDetID(),branchCode};
					string="update";
					rservice.getUpdateCountryDet(val,string);
				}
				else{
					Object[] val={bean.getCountryDetID(),bean.getCountryID(),country_short_name,bean.getWarRate(),startPlace,sp,endPlace,ep,bean.getStatus(),bean.getCode(),"",bean.getCountryDetSNO(),bean.getEff_date(),remarks,branchCode
							,bean.getCrossvoyageyn(),bean.getCrossvoyageOrgin(),bean.getCrossvoyageDestn()};
					logger.info("val.args===>" + StringUtils.join(val, ", "));
					string="insert";
					rservice.getUpdateCountryDet(val,string);
				}
				this.addActionMessage(getText("update.success"));
			}else if("add".equals(bean.getMode())){
				int amend_id=0;
				Object[] val={bean.getCountryDetID(),bean.getCountryDetID(),country_short_name,bean.getWarRate(),startPlace,sp,endPlace,ep,bean.getStatus(),bean.getCode(),"",amend_id,bean.getEff_date(),remarks,branchCode
						,bean.getCrossvoyageyn(),bean.getCrossvoyageOrgin(),bean.getCrossvoyageDestn()};
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getNewCountryDet(val);
				this.addActionMessage(getText("insert.success"));
			}
			menuType="country";
			return "commonList";
		}
		return "editCountryDet";
	}
	public String updateWarranty() {
		validateWarranty();
		String remarks = "";
		if (!hasActionErrors()) {	
			if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
				remarks = " ";
			else
				remarks = bean.getRemarks();

			if("add".equals(bean.getMode())){
				Object[] val = { branchCode, bean.getWarrantyDesc(),bean.getCode(), remarks, bean.getStatus(), branchCode,branchCode,bean.getCode() };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getNewWarranty(val);
				//warrantyList = rservice.getWarrantyList(branchCode);
				this.addActionMessage(getText("insert.success"));
			}
			else{
				Object[] val = { bean.getWarrantyDesc(), bean.getCode(), remarks,bean.getStatus(),bean.getCode(), bean.getWarrantyID(), branchCode };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getUpdateWarranty(val);
				//warrantyList = rservice.getWarrantyList(branchCode);
				this.addActionMessage(getText("update.success"));
			}
			menuType="warrantymaster";
			return "commonList";
		}
		return "editWarranty";
	}	

	public String updateConstantMaster() {
		String forward = "";
		try {
				validateUpdateConstantMaster();
			if(hasActionErrors()) {
				forward = "editConstantMaster";
			}
			else {
				menuType = "constantMaster";
				forward = "commonList";
				this.addActionMessage("Updated Successfully");
				rservice.getUpdateConstantMaster(bean);
			}

		}
		catch(Exception exception) {
			logger.debug("EXCEPTION @ updateConstantMaster { " + exception + " } ");
		}
		return forward;
	}

	
	 private void validateUpdateConstantMaster() {
		if(StringUtils.isBlank(bean.getCategoryName())) {
			this.addActionError("Please enter the category name");
		}
		else if(!StringUtils.isAlphaSpace(bean.getCategoryName()))
			this.addActionError("category.name.invalid");
		if(StringUtils.isBlank(bean.getRsacode())) {
			this.addActionError("Please enter the Core Application Code");
		}
		else if (!StringUtils.isAlphanumeric(bean.getRsacode()))
			this.addActionError("code.invalid");
		if(StringUtils.isBlank(bean.getStatus())) {
			this.addActionError("Please select the status");
		}
	}
	 
	public String updateConstant() {
		String remarks = "";
		if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
			remarks = " ";
		else
			remarks = bean.getRemarks();
		validateConstant();
		if (!hasActionErrors()) {	
			if("add".equals(bean.getMode())){
				Object[] val = { bean.getCategoryID(),bean.getCategoryID(),branchCode, bean.getDetailName(), remarks, bean.getStatus(),
						bean.getCode(), bean.getPercentage(), branchCode };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getNewConstant(val);
				//constantList = rservice.getConstantList(branchCode);
				this.addActionMessage(getText("insert.success"));
			}else{

				Object[] val = { bean.getDetailName(), bean.getPercentage(),
						remarks, bean.getCode(), bean.getStatus(),
						bean.getConstantID(),bean.getCategoryID(),branchCode  };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getUpdateConstant(val);
				//constantList = rservice.getConstantList(branchCode);
				this.addActionMessage(getText("update.success"));
			}
			menuType="constantdetail";
			return "commonList";
		}
		return "editConstant";
	}

	/*public String addVessel() {
		validateVessel();
		if (!hasActionErrors()) {
			String remarks = "";
			String vesselClass = "";
			if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
				remarks = " ";
			else
				remarks = bean.getRemarks();
			if ("".equals(bean.getVesselClass())
					|| bean.getVesselClass().equals(null))
				vesselClass = "";
			else
				vesselClass = bean.getVesselClass();
			Object[] val = { bean.getVesselName(), bean.getVesselType(),
					bean.getVesselYear(), vesselClass, bean.getCode(),
					bean.getStatus(), remarks,bean.getVesselRate()};
			logger.info("val.args===>" + StringUtils.join(val, ", "));
			rservice.getVessel(val);
			vesselList = rservice.getVesselList();
			this.addActionMessage(getText("insert.success"));
			return "vessel";
		}
		return "addNewVessel";
	}*/
	public String updateVessel() {
		String remarks = "";
		String vesselClass = "";
		if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
			remarks = " ";
		else
			remarks = bean.getRemarks();
		if ("".equals(bean.getVesselClass())
				|| bean.getVesselClass().equals(null))
			vesselClass = "";
		else
			vesselClass = bean.getVesselClass();
		validateVessel();
		if (!hasActionErrors()) {
			if("edit".equals(bean.getMode())){
				Object[] val = { bean.getVesselName(), bean.getVesselType(),
						bean.getVesselsYear(), vesselClass, bean.getCode(),
						bean.getStatus(), remarks,bean.getVesselRate(), bean.getVesselID() };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getUpdateVessel(val);
				//vesselList = rservice.getVesselList();
				this.addActionMessage(getText("update.success"));
			} else {
				Object[] val = { bean.getVesselName(), bean.getVesselType(),
						bean.getVesselsYear(), vesselClass, bean.getCode(),
						bean.getStatus(), remarks,bean.getVesselRate()};
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getVessel(val);
				//vesselList = rservice.getVesselList();
				this.addActionMessage(getText("insert.success"));
			}
			menuType="vesselmaster";
			return "commonList";
		}
		return "editVessel";
	}
	public String updateTransport() {
		validatenewTransport();
		if (!hasActionErrors())
		{
			if("add".equals(bean.getMode()))
			{
				Object[] val = { branchCode, bean.getTransDesc().toUpperCase(),
						bean.getTransRSA(), bean.getStatus(), bean.getTransDO(),
						branchCode };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getTransportMode(val);
				//transportList = rservice.getAdminTransportList(branchCode);
				this.addActionError(getText("insert.success"));

			}
			else
			{
				Object[] val = { bean.getTransDesc().toUpperCase(), bean.getTransDO(),
						bean.getTransRSA(), bean.getStatus(), bean.getTransID(),
						branchCode };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getUpdateTransportMode(val);
				//transportList = rservice.getAdminTransportList(branchCode);
				this.addActionError(getText("update.success"));
			}
			menuType="modeoftransport";
			return "commonList";
		}
		return "editTransport";
	}
	public String newConveyance() {
		return "newConveyance";
	}
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
				//boolean bo = validateDate(bean.getPrevdate());
				boolean bo=checkDate(bean.getPrevdate(),bean.getEff_date());

				if(bo)
				{				
					Object[] val={bean.getConveyName().toUpperCase(),bean.getConveyRate(),bean.getEff_date(),bean.getStatus(),remarks,bean.getCode(),branchCode,bean.getConveyID(),bean.getTransID()};
					string="update";
					rservice.getUpdateConveyance(val,string);
				}

				else
				{
					Object[] val={bean.getConveyID(),bean.getTransID(),branchCode,bean.getConveyName(),bean.getConveyRate(),bean.getTransID(),bean.getEff_date(),branchCode,bean.getSno(),branchCode,bean.getCode(),remarks,bean.getStatus()};
					string="insert";
					rservice.getUpdateConveyance(val,string);
				}
				this.addActionMessage(getText("update.success"));
			}
			else
			{
				int amend_id=0;	
				Object[] val={branchCode,bean.getTransID(),branchCode,bean.getConveyName().toUpperCase(),bean.getConveyRate(),bean.getTransID(),bean.getEff_date(),amend_id,branchCode,bean.getCode(),remarks,bean.getStatus()};
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getNewConveyance(val);
				this.addActionMessage(getText("insert.success"));
			}
			menuType="conveyance";
			return "commonList";
		}
		return "updateConveyance";
	}
	public String updateExtraCover() {
		String remarks = "";
		if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
			remarks = " ";
		else
			remarks = bean.getRemarks();
		validatenewExtraCover();
		if (!hasActionErrors()) {
			if("add".equals(bean.getMode()))
			{
				// int amend_id=0;
				Object[] val = { branchCode, bean.getTransID(),
						bean.getExtraCoverName(), bean.getCode(), branchCode,
						remarks, bean.getStatus() };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getNewExtraCover(val);

				//extraCoverList = rservice.getExtraCoverList(branchCode);
				this.addActionMessage(getText("insert.success"));
			}
			else
			{
				Object[] val = { bean.getExtraCoverName(), bean.getTransID(),
						remarks, bean.getCode(), bean.getStatus(),
						branchCode, bean.getExtraCoverId() };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getUpdateExraCover(val);

				//extraCoverList = rservice.getExtraCoverList(branchCode);
				this.addActionMessage(getText("update.success"));
			}
			menuType="extracover";
			return "commonList";
		}
		return "updateExtraCover";
	}
	public String updateCountry() {
		validateNewCountry();
		if (!hasActionErrors()) {
			if("add".equals(bean.getMode()))
			{
				String country_short_name = "", remarks = "";
				if ("".equals(bean.getCountryShortName())
						|| bean.getCountryShortName().equals(null))
					country_short_name = " ";
				else
					country_short_name = bean.getCountryShortName();
				if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
					remarks = " ";
				else
					remarks = bean.getRemarks();
				int amend_id = 0;
				Object[] val = { bean.getCountryName(), country_short_name,
						bean.getStatus(), bean.getCode(), bean.getCountryNat(),
						amend_id, bean.getEff_date(), bean.getGeoRate(), remarks };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getAddCountry(val);
				//countryList = rservice.getCountryList();
				this.addActionMessage(getText("insert.success"));
			}
			else
			{				
				String string="",country_short_name="",remarks="";
				if("".equals(bean.getCountryShortName())||bean.getCountryShortName().equals(null))
					country_short_name=" ";
				if("".equals(bean.getRemarks())||bean.getRemarks().equals(null))
					remarks=" ";
				//boolean bo = validateDate(bean.getPrevdate());
				boolean bo=checkDate(bean.getPrevdate(), bean.getEff_date());
				if(bo)
				{	
					/* Date date=new Date(); 
				 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				     String sysdate = formatter.format(date);
				   	String format = "MM/dd/yyyy"; 
		    		SimpleDateFormat sdf = new SimpleDateFormat(format);
		    		java.util.Date date1;
					try {
						date1 = sdf.parse(sysdate);
						java.util.Date date2 = sdf.parse(bean.getEff_date()); 
			    		if(date2.compareTo(date1)== 0){*/
					Object[] val={bean.getCountryName(),bean.getGeoRate(),bean.getEff_date(),bean.getStatus(),bean.getCountryShortName(),bean.getCountryNat(),bean.getCode() ,bean.getRemarks(),bean.getCountryID()};
					//Object[] val={bean.getCountryID(),bean.getCountryName(),bean.getCountryShortName(),bean.getStatus(),bean.getCode(),bean.getCountryNat(),bean.getCountryID() ,bean.getEff_date(),bean.getGeoRate(),bean.getRemarks()};
					string="update";
					rservice.getUpdateCountry(val,string);
					/*	}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/

				}
				else
				{
					Object[] val={bean.getCountryID(),bean.getCountryName(),bean.getCountryShortName(),bean.getStatus(),bean.getCode(),bean.getCountryNat(),bean.getCountryID(),bean.getEff_date(),bean.getGeoRate(),bean.getRemarks(),bean.getCountryID()};
					string="insert";
					rservice.getUpdateCountry(val,string);
				}
				//countryList = rservice.getCountryList();
				this.addActionMessage(getText("update.success"));
			}
			menuType="countrymaster";
			return "commonList";
		}
		return "editCountry";
	}
	public String updateMaterials() {
		validateNewMaterial();
		if(!hasActionErrors())
		{
			String remarks="",string="";

			if("".equals(bean.getRemarks())||bean.getRemarks().equals(null))
				remarks=" ";
			else

				remarks=bean.getRemarks();
			//boolean bo = validateDate(bean.getPrevdate());
			boolean bo=checkDate(bean.getPrevdate(),bean.getEff_date());
			if(bo)
			{

				Object[] val={bean.getMaterialName(),bean.getEff_date(),bean.getMaterialRate(),bean.getStatus(),remarks,bean.getCode(),bean.getMaterialID(),bean.getCoverID(),branchCode};
				/*Object[] val={branchCode,bean.getMaterialName(),bean.getMaterialRate(),
						bean.getEff_date(),branchCode,branchCode,bean.getMaterialID(),bean.getStatus(),bean.getRemarks()
						};*/
				string="update";
				rservice.getUpdateMaterial(val,string);
			}
			else
			{
				//Object[] val={bean.getMaterialID(),bean.getMaterialName(),bean.getMaterialRate(),bean.getEff_date(),bean.getMaterialName(),branchCode,bean.getCode(),bean.getStatus(),remarks};
				Object [] val={
						branchCode,bean.getMaterialID(),bean.getCoverID(),bean.getMaterialName(),bean.getMaterialRate(),bean.getEff_date(),branchCode,bean.getMaterialID(),
						branchCode,bean.getMaterialID(),bean.getStatus(),bean.getRemarks() 
				};
				string="insert";
				rservice.getUpdateMaterial(val,string);
			}
			//materialList=rservice.getMaterialList(branchCode);
			this.addActionMessage(getText("update.success"));

			menuType="materialmaster";
			return "commonList";
		}
		return "editMaterial";
	}

	public String addNewMaterial() {
		return "addNewMaterial";
	}
	public String updateBank() {
		validateNewBank();
		if (!hasActionErrors()) {
			if("add".equals(bean.getMode()))
			{
				String remarks = "";

				if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
					remarks = " ";
				else
					remarks = bean.getRemarks();
				int amend_id = 0;
				Object[] val = { bean.getBankName(), bean.getEff_date(), amend_id,
						remarks, bean.getStatus(), bean.getCode(),
						bean.getCountryID() };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getAddBank(val);
				this.addActionMessage(getText("insert.success"));
			}
			else
			{
				String remarks="",string="";

				if("".equals(bean.getRemarks())||bean.getRemarks().equals(null))
					remarks=" ";

				else

					remarks=bean.getRemarks();
				boolean bo = validateDate(bean.getPrevdate());
				if(bo==false)
				{

					Object[] val={bean.getBankName(),bean.getEff_date(),bean.getStatus(),bean.getCode(),remarks,bean.getBankID()};
					string="update";
					rservice.getUpdateBank(val,string);
				}
				else
				{
					Object[] val={bean.getBankID(),bean.getBankName(),bean.getEff_date(),bean.getBankID(),remarks,bean.getStatus(),bean.getCode(),bean.getCountryID()};
					string="insert";
					rservice.getUpdateBank(val,string);

				}

				//bankList = rservice.getBankList();
				this.addActionMessage(getText("update.success"));
			}
			menuType="bankmaster";
			return "commonList";
		}
		return "editBank";
	}
	public String addMaterials() {
		validateNewMaterial();
		if (!hasActionErrors()) {
			String remarks = "";

			if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
				remarks = " ";
			else
				remarks = bean.getRemarks();
			int amend_id = 0;
			Object[] val = { branchCode, bean.getMaterialName(),
					bean.getMaterialRate(), bean.getEff_date(), amend_id,
					branchCode, bean.getCode(), bean.getStatus(), remarks };
			logger.info("val.args===>" + StringUtils.join(val, ", "));
			rservice.getNewMaterial(val);
			//materialList = rservice.getMaterialList(branchCode);
			this.addActionMessage(getText("insert.success"));

			return "material";
		}
		return "addNewMaterial";
	}
	public String updateWar() {
		String remarks = "";
		if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
			remarks = " ";
		else
			remarks = bean.getRemarks();
		validateNewWar();
		if (!hasActionErrors()) {	
			if("add".equals(bean.getMode()))
			{
				int amend_id = 0;
				Object[] val = { branchCode, bean.getWarName(), bean.getWarRate(),
						bean.getTransID(), bean.getEff_date(), amend_id,
						branchCode, bean.getCode(), bean.getStatus(), remarks };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getNewWar(val);
				//warrateList = rservice.getWarrateList(branchCode);
				this.addActionMessage(getText("insert.success"));
			}
			else
			{
				String string="";						
				boolean bo = validatePrevDate(bean.getPrevdate());
				if(bo==false)
				{				
					//Object[] val={bean.getWarRate(),remarks,bean.getEff_date(),bean.getStatus(),branchCode,bean.getWarID()};
					Object[] val={bean.getWarName(),bean.getTransID(),bean.getWarRate(),remarks,bean.getEff_date(),bean.getCode(),bean.getStatus(),branchCode,bean.getWarID()};
					string="update";
					rservice.getUpdateWar(val,string);
					logger.info("val.args===>" + StringUtils.join(val, ", "));
				}
				else
				{
					Object[] val={bean.getWarID(),bean.getWarName(),bean.getWarRate(),bean.getTransID(),bean.getEff_date(),branchCode,bean.getWarID(),bean.getTransID(),branchCode,bean.getCode(),bean.getStatus(),remarks};
					string="insert";
					rservice.getUpdateWar(val,string);
					logger.info("val.args===>" + StringUtils.join(val, ", "));
				}
				//warrateList=rservice.getWarrateList(branchCode);
				this.addActionMessage(getText("update.success"));
			}
			menuType="warratemaster";
			return "commonList";
		}
		return "editWar";
	}
	public String updateSale() {
		validateNewSale();
		if (!hasActionErrors()) {
			if("add".equals(bean.getMode()))
			{
				String remarks = "";

				if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
					remarks = " ";
				else
					remarks = bean.getRemarks();

				Object[] val = { branchCode, bean.getSaleName(),
						bean.getSaleValue(), bean.getCode(), remarks,
						bean.getStatus(), branchCode,branchCode,branchCode};
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getNewSale(val);
				//saletermList = rservice.getSaletermList(branchCode);
				this.addActionMessage(getText("insert.success"));
			}
			else
			{
				String remarks = "";

				if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
					remarks = " ";
				else
					remarks = bean.getRemarks();

				Object[] val = { bean.getSaleName(), bean.getSaleValue(),
						bean.getCode(), bean.getStatus(),
						remarks, bean.getSaleID(), branchCode, bean.getSaleID() };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getUpdateSale(val);
				//saletermList = rservice.getSaletermList(branchCode);
				this.addActionMessage(getText("update.success"));
			}
			menuType="saletermmaster";
			return "commonList";
		}
		return "editSale";
	}
	public String updateTole() {
		validateNewTole();
		if (!hasActionErrors()) {
			if("add".equals(bean.getMode()))
			{
				String remarks = "";

				if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
					remarks = " ";
				else
					remarks = bean.getRemarks();

				Object[] val = { branchCode, bean.getToleName(),
						bean.getToleValue(), bean.getCode(), remarks,
						bean.getStatus(), branchCode, branchCode };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getAddTole(val);
				//toleranceList = rservice.getToleranceList(branchCode);
				this.addActionMessage(getText("insert.success"));
			}
			else
			{
				String remarks = "";

				if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
					remarks = " ";
				else
					remarks = bean.getRemarks();

				Object[] val = { bean.getToleName(), bean.getToleValue(),
						bean.getCode(), remarks, bean.getStatus(), branchCode,
						bean.getToleID() };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getUpdateTole(val);
				//toleranceList = rservice.getToleranceList(branchCode);
				this.addActionMessage(getText("update.success"));
			}
			menuType="tolerancemaster";
			return "commonList";
		}
		return "editTole";
	}
	public String updateComEx() {
		validatenewComEx();
		if (!hasActionErrors()) {
			if("add".equals(bean.getMode()))
			{
				int amend_id = 0;

				/*Object[] val = { bean.getStartAmt(), bean.getEndAmt(),
						bean.getDeductible(), bean.getComExRate(), amend_id,
						bean.getEff_date(), branchCode, bean.getStatus(),
						bean.getCode() };*/
				Object[] val={branchCode,bean.getStartAmt(),bean.getEndAmt(),bean.getDeductible(),bean.getComExRate(),bean.getEff_date(),branchCode,bean.getStatus()
				};
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getAddComEx(val);
				//comExList = rservice.getComExList(branchCode);
				this.addActionMessage(getText("insert.success"));
			}
			else
			{
				String string="";
				//boolean bo = validateDate(bean.getPrevdate());
				//boolean bo = validateDate(bean.getEff_date());
				Date date=new Date(); 
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				String sysdate = formatter.format(date);
				boolean bo=checkDate(bean.getPrevdate(),bean.getEff_date());
				if(bo)
				{

					Object[] val={bean.getDeductible(),bean.getComExRate(),bean.getStatus(),bean.getEff_date(),bean.getComExID(),bean.getComExID(),branchCode,branchCode};
					//Object[] val={bean.getComExID(),bean.getComExID(),branchCode,bean.getStartAmt(),bean.getEndAmt(),bean.getDeductible(),bean.getComExRate(),bean.getComExID(),branchCode,bean.getEff_date(),branchCode,bean.getStatus()};
					string="update";
					rservice.getUpdateComEx(val,string);
				}
				else
				{
					//Object[] val={bean.getDeductible(),bean.getComExRate(),bean.getStatus(),bean.getEff_date(),bean.getCode(),bean.getComExID()};
					//Object[] val={bean.getComExID(),bean.getComExID(),branchCode,bean.getStartAmt(),bean.getEndAmt(),bean.getDeductible(),bean.getComExRate(),bean.getComExID(),branchCode,bean.getEff_date(),branchCode,bean.getStatus()};
					Object[] val={bean.getComExID(),bean.getStartAmt(),bean.getEndAmt(),bean.getDeductible(),bean.getComExRate(),branchCode,bean.getComExID(),bean.getEff_date(),branchCode,bean.getStatus()};

					/*for(Object k: val)
				{
					logger.info("val.args===>" + k);

				}*/
					string="insert";
					rservice.getUpdateComEx(val,string);
				}

				//comExList = rservice.getComExList(branchCode);
				this.addActionMessage(getText("update.success"));
			}
			menuType="commodityexcess";
			return "commonList";
		}
		return "editComEx";
	}
	public String updateCity() {
		String remarks = "";
		if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
			remarks = " ";
		else
			remarks = bean.getRemarks();
		validatenewCity();
		if (!hasActionErrors()) {
			if("add".equals(bean.getMode()))
			{
				Object[] val = { bean.getCityName(), bean.getCountryID(),
						bean.getCode(), remarks, bean.getStatus() };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getNewCity(val);

				//cityList = rservice.getCityList(bean);
				this.addActionMessage(getText("insert.success"));
			}
			else
			{
				Object[] val = { bean.getCityName(), bean.getCountryID(),
						bean.getCode(), remarks, bean.getStatus(),
						bean.getCityID() };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getUpdateCity(val);

				//cityList = rservice.getCityList(bean);
				this.addActionMessage(getText("update.success"));
			}
			menuType="citymaster";
			return "commonList";
		}
		countryList = rservice.getCountryList();
		return "updateCity";
	}

	private List<Map<String, Object>> agentList;

	public List<Map<String, Object>> getAgentList() {
		return agentList;
	}	
	public String updateAgent() {
		String remarks = "";
		if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null))
			remarks = " ";
		else
			remarks = bean.getRemarks();
		validatenewAgent();
		if (!hasActionErrors()) {
			if("add".equals(bean.getMode()))
			{
				Object[] val = { branchCode,branchCode, bean.getAgentName(),
						bean.getCountryID(), "", bean.getShortName(),
						bean.getAddress1(), bean.getAddress2(), bean.getAddress3(),
						bean.getZipCode(), bean.getTeleNo(), bean.getFaxNo(),
						bean.getMobileNo(), bean.getEmail(), bean.getCode(),
						StringUtils.isEmpty(bean.getRemarks())?"":bean.getRemarks() , bean.getStatus(), branchCode, branchCode,bean.getCityName() };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getNewAgent(val);

				agentList = rservice.getAgentList(branchCode);
				this.addActionMessage(getText("insert.success"));
			}	
			else
			{
				Object[] val = { bean.getAgentName(), bean.getCountryID(),
						"", bean.getShortName(), bean.getAddress1(),
						bean.getAddress2(), bean.getAddress3(),bean.getCityName(), bean.getZipCode(),
						bean.getTeleNo(), bean.getFaxNo(), bean.getMobileNo(),
						bean.getEmail(), bean.getCode(), remarks,
						bean.getStatus(), branchCode, bean.getAgentID() };

				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getUpdateAgent(val);

				agentList = rservice.getAgentList(branchCode);
				this.addActionMessage(getText("update.success"));
			}
			menuType="settlingagent";
			return "commonList";
		}  
		countryList = rservice.getCountryList();
		cityList = rservice.getCityListAjax1(bean.getCountryID());		
		return "updateAgent";
	}
	public String baserateCalculate() {		
		validatebaserateCalculate1(); 
		String result1="baserate1";		
		if (!hasActionErrors()) {
			rservice.getCommodityRate(bean, bean.getTransID(),
					bean.getCoverageID(), bean.getCoveyID(), bean
					.getBaserateCountryID(), bean.getBaserateCommodityID(),
					bean.getFragile(), Double.parseDouble(bean.getSumrate()),
					branchCode);
			validatebaserateCalculate(bean.getBaserateCommodityID(),bean.getBaserateCountryID(),Double.parseDouble(bean.getSumrate()),branchCode);
			result1="calculate";			
		}		
		return result1;

	}
	public void validatebaserateCalculate1() throws NumberFormatException {
		try {		
			if ("-1".equals(bean.getTransID()))
				this.addActionError(getText("select.transportName"));
			if ("-1".equals(bean.getCoverageID()))
				this.addActionError(getText("select.coverName"));
			if ("-1".equals(bean.getCoveyID()))
				this.addActionError(getText("select.conveyanceName"));
			if ("-1".equals(bean.getBaserateCountryID()))
				this.addActionError(getText("select.countryName"));
			if ("-1".equals(bean.getBaserateCommodityID()))
				this.addActionError(getText("select.commodityName"));  
			if (StringUtils.isBlank(bean.getSumrate()))
				this.addActionError(getText("sumRate.required"));						
		} catch (NumberFormatException e) {
			logger.info("Conversion Error"+e);
		}		
	}		
	public void validatebaserateCalculate(String commodity,String destCountryId,double localSumInsured,String branch) throws NumberFormatException {
		String commodityReferal = runner.singleSelection("select status from commoditymaster where  COMMODITY_ID = "+commodity+" and branch_code = "+branch +" and amend_id= (select max(amend_id) from commoditymaster where branch_code = "+branch +" and commodity_id="+commodity+" )");
		String sumInsuredReferal = runner.singleSelection("select decode(count(*),0,'REFERAL','NONREFERAL') status from commodity_excess_premium cp where branch_code="+branchCode+" and amend_id=(select max(amend_id) from commodity_excess_premium where cp.start_sum_insured=start_sum_insured and branch_code="+branchCode+" and trunc(effective_date) <=trunc(sysdate)) and trunc(effective_date) <=trunc(sysdate) and "+localSumInsured+" between start_sum_insured and end_sum_insured");
		String countryReferal=runner.singleSelection("select decode(count(*),1,'REFERAL','NONREFERAL') status from constant_detail dc,country_master cm where category_id=9 and cm.country_id="+destCountryId+" and branch_code="+branch+" and lower(dc.detail_name)=lower(cm.COUNTRY_NAME)");	
		logger.info("commodityReferal===>" +commodityReferal );		
		logger.info("sumInsuredReferal===>" +sumInsuredReferal );	
		logger.info("countryReferal===>" +countryReferal );	

		if(countryReferal.equalsIgnoreCase("REFERAL"))
			this.addFieldError("countryRate", getText("invalid.countryReferal"));
		if (commodityReferal.equalsIgnoreCase("R")) 
			this.addFieldError("commodityRate", getText("invalid.commodityReferal"));
		if (sumInsuredReferal.equalsIgnoreCase("REFERAL"))
			this.addFieldError("sumRRate",getText("invalid.sumInsuredReferal")); 		 

	}
	/*public String updateCommodity() {
		validateCommodity();
		if (!hasActionErrors()) {
			if("add".equals(bean.getMode()))
			{			
				String txtchkICC_A_SEAClause_1 = "";
				String txtchkICC_A_SEAExclusive_1 = "";
				String txtchkICC_A_SEAWarranty_1 = "";
				String txtchkICC_A_SEAWarCover_1 = "";

				String txtchkICC_A_SEAClause_2 = "";
				String txtchkICC_A_SEAExclusive_2 = "";
				String txtchkICC_A_SEAWarranty_2 = "";
				String txtchkICC_A_SEAWarCover_2 = "";

				String txtchkICC_A_SEAClause_3 = "";
				String txtchkICC_A_SEAExclusive_3 = "";
				String txtchkICC_A_SEAWarranty_3 = "";
				String txtchkICC_A_SEAWarCover_3 = "";

				String txtchkICC_A_SEAClause_8 = "";
				String txtchkICC_A_SEAExclusive_8 = "";
				String txtchkICC_A_SEAWarranty_8 = "";
				String txtchkICC_A_SEAWarCover_8 = "";

				String txtchkICC_A_SEAClause_9 = "";
				String txtchkICC_A_SEAExclusive_9 = "";
				String txtchkICC_A_SEAWarranty_9 = "";
				String txtchkICC_A_SEAWarCover_9 = "";

				String txtchkICC_A_SEAClause_10 = "";
				String txtchkICC_A_SEAExclusive_10 = "";
				String txtchkICC_A_SEAWarranty_10 = "";
				String txtchkICC_A_SEAWarCover_10 = "";

				String txtchkICC_A_SEAClause_11 = "";
				String txtchkICC_A_SEAExclusive_11 = "";
				String txtchkICC_A_SEAWarranty_11 = "";
				String txtchkICC_A_SEAWarCover_11 = "";


				if ("".equals(bean.getTxtchkICC_A_SEAClause_1())
						|| bean.getTxtchkICC_A_SEAClause_1().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_1().equals("null")) {
					txtchkICC_A_SEAClause_1 = "";
				} else {
					txtchkICC_A_SEAClause_1 = bean.getTxtchkICC_A_SEAClause_1();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_1())
						|| bean.getTxtchkICC_A_SEAExclusive_1().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_1().equals("null")) {
					txtchkICC_A_SEAExclusive_1 = "";
				} else {
					txtchkICC_A_SEAExclusive_1 = bean
					.getTxtchkICC_A_SEAExclusive_1();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_1())
						|| bean.getTxtchkICC_A_SEAWarranty_1().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_1().equals("null")) {
					txtchkICC_A_SEAWarranty_1 = "";
				} else {
					txtchkICC_A_SEAWarranty_1 = bean.getTxtchkICC_A_SEAWarranty_1();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_1())
						|| bean.getTxtchkICC_A_SEAWarCover_1().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_1().equals("null")) {
					txtchkICC_A_SEAWarCover_1 = "";
				} else {
					txtchkICC_A_SEAWarCover_1 = bean.getTxtchkICC_A_SEAWarCover_1();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAClause_2())
						|| bean.getTxtchkICC_A_SEAClause_2().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_2().equals("null")) {
					txtchkICC_A_SEAClause_2 = "";
				} else {
					txtchkICC_A_SEAClause_2 = bean.getTxtchkICC_A_SEAClause_2();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_2())
						|| bean.getTxtchkICC_A_SEAExclusive_2().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_2().equals("null")) {
					txtchkICC_A_SEAExclusive_2 = "";
				} else {
					txtchkICC_A_SEAExclusive_2 = bean
					.getTxtchkICC_A_SEAExclusive_2();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_2())
						|| bean.getTxtchkICC_A_SEAWarranty_2().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_2().equals("null")) {
					txtchkICC_A_SEAWarranty_2 = "";
				} else {
					txtchkICC_A_SEAWarranty_2 = bean.getTxtchkICC_A_SEAWarranty_2();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_2())
						|| bean.getTxtchkICC_A_SEAWarCover_2().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_2().equals("null")) {
					txtchkICC_A_SEAWarCover_2 = "";
				} else {
					txtchkICC_A_SEAWarCover_2 = bean.getTxtchkICC_A_SEAWarCover_2();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAClause_3())
						|| bean.getTxtchkICC_A_SEAClause_3().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_3().equals("null")) {
					txtchkICC_A_SEAClause_3 = "";
				} else {
					txtchkICC_A_SEAClause_3 = bean.getTxtchkICC_A_SEAClause_3();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_3())
						|| bean.getTxtchkICC_A_SEAExclusive_3().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_3().equals("null")) {
					txtchkICC_A_SEAExclusive_3 = "";
				} else {
					txtchkICC_A_SEAExclusive_3 = bean
					.getTxtchkICC_A_SEAExclusive_3();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_3())
						|| bean.getTxtchkICC_A_SEAWarranty_3().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_3().equals("null")) {
					txtchkICC_A_SEAWarranty_3 = "";
				} else {
					txtchkICC_A_SEAWarranty_3 = bean.getTxtchkICC_A_SEAWarranty_3();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_3())
						|| bean.getTxtchkICC_A_SEAWarCover_3().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_3().equals("null")) {
					txtchkICC_A_SEAWarCover_3 = "";
				} else {
					txtchkICC_A_SEAWarCover_3 = bean.getTxtchkICC_A_SEAWarCover_3();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAClause_8())
						|| bean.getTxtchkICC_A_SEAClause_8().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_8().equals("null")) {
					txtchkICC_A_SEAClause_8 = "";
				} else {
					txtchkICC_A_SEAClause_8 = bean.getTxtchkICC_A_SEAClause_8();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_8())
						|| bean.getTxtchkICC_A_SEAExclusive_8().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_8().equals("null")) {
					txtchkICC_A_SEAExclusive_8 = "";
				} else {
					txtchkICC_A_SEAExclusive_8 = bean
					.getTxtchkICC_A_SEAExclusive_8();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_8())
						|| bean.getTxtchkICC_A_SEAWarranty_8().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_8().equals("null")) {
					txtchkICC_A_SEAWarranty_8 = "";
				} else {
					txtchkICC_A_SEAWarranty_8 = bean.getTxtchkICC_A_SEAWarranty_8();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_8())
						|| bean.getTxtchkICC_A_SEAWarCover_8().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_8().equals("null")) {
					txtchkICC_A_SEAWarCover_8 = "";
				} else {
					txtchkICC_A_SEAWarCover_8 = bean.getTxtchkICC_A_SEAWarCover_8();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAClause_9())
						|| bean.getTxtchkICC_A_SEAClause_9().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_9().equals("null")) {
					txtchkICC_A_SEAClause_9 = "";
				} else {
					txtchkICC_A_SEAClause_9 = bean.getTxtchkICC_A_SEAClause_9();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_9())
						|| bean.getTxtchkICC_A_SEAExclusive_9().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_9().equals("null")) {
					txtchkICC_A_SEAExclusive_9 = "";
				} else {
					txtchkICC_A_SEAExclusive_9 = bean
					.getTxtchkICC_A_SEAExclusive_9();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_9())
						|| bean.getTxtchkICC_A_SEAWarranty_9().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_9().equals("null")) {
					txtchkICC_A_SEAWarranty_9 = "";
				} else {
					txtchkICC_A_SEAWarranty_9 = bean.getTxtchkICC_A_SEAWarranty_9();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_9())
						|| bean.getTxtchkICC_A_SEAWarCover_9().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_9().equals("null")) {
					txtchkICC_A_SEAWarCover_9 = "";
				} else {
					txtchkICC_A_SEAWarCover_9 = bean.getTxtchkICC_A_SEAWarCover_9();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAClause_10())
						|| bean.getTxtchkICC_A_SEAClause_10().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_10().equals("null")) {
					txtchkICC_A_SEAClause_10 = "";
				} else {
					txtchkICC_A_SEAClause_10 = bean.getTxtchkICC_A_SEAClause_10();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_10())
						|| bean.getTxtchkICC_A_SEAExclusive_10().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_10().equals("null")) {
					txtchkICC_A_SEAExclusive_10 = "";
				} else {
					txtchkICC_A_SEAExclusive_10 = bean
					.getTxtchkICC_A_SEAExclusive_10();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_10())
						|| bean.getTxtchkICC_A_SEAWarranty_10().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_10().equals("null")) {
					txtchkICC_A_SEAWarranty_10 = "";
				} else {
					txtchkICC_A_SEAWarranty_10 = bean
					.getTxtchkICC_A_SEAWarranty_10();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_10())
						|| bean.getTxtchkICC_A_SEAWarCover_10().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_10().equals("null")) {
					txtchkICC_A_SEAWarCover_10 = "";
				} else {
					txtchkICC_A_SEAWarCover_10 = bean
					.getTxtchkICC_A_SEAWarCover_10();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAClause_11())
						|| bean.getTxtchkICC_A_SEAClause_11().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_11().equals("null")) {
					txtchkICC_A_SEAClause_11 = "";
				} else {
					txtchkICC_A_SEAClause_11 = bean.getTxtchkICC_A_SEAClause_11();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_11())
						|| bean.getTxtchkICC_A_SEAExclusive_11().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_11().equals("null")) {
					txtchkICC_A_SEAExclusive_11 = "";
				} else {
					txtchkICC_A_SEAExclusive_11 = bean
					.getTxtchkICC_A_SEAExclusive_11();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_11())
						|| bean.getTxtchkICC_A_SEAWarranty_11().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_11().equals("null")) {
					txtchkICC_A_SEAWarranty_11 = "";
				} else {
					txtchkICC_A_SEAWarranty_11 = bean
					.getTxtchkICC_A_SEAWarranty_11();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_11())
						|| bean.getTxtchkICC_A_SEAWarCover_11().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_11().equals("null")) {
					txtchkICC_A_SEAWarCover_11 = "";
				} else {
					txtchkICC_A_SEAWarCover_11 = bean
					.getTxtchkICC_A_SEAWarCover_11();
				}
				/*if ("".equals(bean.getTxtchkICC_A_SEAClause_12())
						|| bean.getTxtchkICC_A_SEAClause_12().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_12().equals("null")) {
					txtchkICC_A_SEAClause_12 = "";
				} else {
					txtchkICC_A_SEAClause_12 = bean.getTxtchkICC_A_SEAClause_12();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_12())
						|| bean.getTxtchkICC_A_SEAExclusive_12().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_12().equals("null")) {
					txtchkICC_A_SEAExclusive_12 = "";
				} else {
					txtchkICC_A_SEAExclusive_12 = bean
							.getTxtchkICC_A_SEAExclusive_12();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_12())
						|| bean.getTxtchkICC_A_SEAWarranty_12().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_12().equals("null")) {
					txtchkICC_A_SEAWarranty_12 = "";
				} else {
					txtchkICC_A_SEAWarranty_12 = bean
							.getTxtchkICC_A_SEAWarranty_12();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_12())
						|| bean.getTxtchkICC_A_SEAWarCover_12().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_12().equals("null")) {
					txtchkICC_A_SEAWarCover_12 = "";
				} else {
					txtchkICC_A_SEAWarCover_12 = bean
							.getTxtchkICC_A_SEAWarCover_12();
				}  
				String remarks = "";

				if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null)
						|| bean.getRemarks().equals("null"))
					remarks = " ";
				else
					remarks = bean.getRemarks();

				String ICC_A_Sea_Rate = "";
				ICC_A_Sea_Rate = txtchkICC_A_SEAClause_1 + "~"
				+ txtchkICC_A_SEAExclusive_1 + "~"
				+ txtchkICC_A_SEAWarranty_1 + "~"
				+ txtchkICC_A_SEAWarCover_1 + "~"
				+ bean.getRadDetective_1();
				String ICC_B_Sea_Rate = "";
				ICC_B_Sea_Rate = txtchkICC_A_SEAClause_2 + "~"
				+ txtchkICC_A_SEAExclusive_2 + "~"
				+ txtchkICC_A_SEAWarranty_2 + "~"
				+ txtchkICC_A_SEAWarCover_2 + "~"
				+ bean.getRadDetective_2();
				String ICC_C_Sea_Rate = "";
				ICC_C_Sea_Rate = txtchkICC_A_SEAClause_3 + "~"
				+ txtchkICC_A_SEAExclusive_3 + "~"
				+ txtchkICC_A_SEAWarranty_3 + "~"
				+ txtchkICC_A_SEAWarCover_3 + "~"
				+ bean.getRadDetective_3();
				String ICC_Air_Cargo_Air = "";
				ICC_Air_Cargo_Air = txtchkICC_A_SEAClause_8 + "~"
				+ txtchkICC_A_SEAExclusive_8 + "~"
				+ txtchkICC_A_SEAWarranty_8 + "~"
				+ txtchkICC_A_SEAWarCover_8 + "~"
				+ bean.getRadDetective_8();
				String All_Risks_Land_Transit = "";
				All_Risks_Land_Transit = txtchkICC_A_SEAClause_9 + "~"
				+ txtchkICC_A_SEAExclusive_9 + "~"
				+ txtchkICC_A_SEAWarranty_9 + "~"
				+ txtchkICC_A_SEAWarCover_9 + "~"
				+ bean.getRadDetective_9();
				String Land_Transit_Land = "";
				Land_Transit_Land = txtchkICC_A_SEAClause_10 + "~"
				+ txtchkICC_A_SEAExclusive_10 + "~"
				+ txtchkICC_A_SEAWarranty_10 + "~"
				+ txtchkICC_A_SEAWarCover_10 + "~"
				+ bean.getRadDetective_10();
				String All_Risks_Parcel_Post_Air = "";
				All_Risks_Parcel_Post_Air = txtchkICC_A_SEAClause_11 + "~"
				+ txtchkICC_A_SEAExclusive_11 + "~"
				+ txtchkICC_A_SEAWarranty_11 + "~"
				+ txtchkICC_A_SEAWarCover_11 + "~"
				+ bean.getRadDetective_11();
				/*String All_Risks_Sea_and_Air = "";
				All_Risks_Sea_and_Air = txtchkICC_A_SEAClause_12 + "~"
						+ txtchkICC_A_SEAExclusive_12 + "~"
						+ txtchkICC_A_SEAWarranty_12 + "~"
						+ txtchkICC_A_SEAWarCover_12 + "~"
						+ bean.getRadDetective_12(); 
				Object[] val = { bean.getCommodityName(), bean.getCommodityRates(),
						bean.getCoverage_Referal(), ICC_A_Sea_Rate, ICC_B_Sea_Rate,
						ICC_C_Sea_Rate,ICC_Air_Cargo_Air,
						All_Risks_Land_Transit, Land_Transit_Land,
						All_Risks_Parcel_Post_Air, "", "",
						"", "", remarks, bean.getStatus(), bean.getCode(),
						bean.getEff_date(), 0, bean.getCommodityTypeID(),
						branchCode, "" };

				rservice.getNewCommodity(val);
				Object[] val1 = { branchCode, branchCode };
				commodityList = rservice.getCommodityList(val1);
				this.addActionMessage(getText("insert.success"));
			}
			else
			{
				String txtchkICC_A_SEAClause_1 = "";
				String txtchkICC_A_SEAExclusive_1 = "";
				String txtchkICC_A_SEAWarranty_1 = "";
				String txtchkICC_A_SEAWarCover_1 = "";

				String txtchkICC_A_SEAClause_2 = "";
				String txtchkICC_A_SEAExclusive_2 = "";
				String txtchkICC_A_SEAWarranty_2 = "";
				String txtchkICC_A_SEAWarCover_2 = "";

				String txtchkICC_A_SEAClause_3 = "";
				String txtchkICC_A_SEAExclusive_3 = "";
				String txtchkICC_A_SEAWarranty_3 = "";
				String txtchkICC_A_SEAWarCover_3 = "";

				String txtchkICC_A_SEAClause_8 = "";
				String txtchkICC_A_SEAExclusive_8 = "";
				String txtchkICC_A_SEAWarranty_8 = "";
				String txtchkICC_A_SEAWarCover_8 = "";

				String txtchkICC_A_SEAClause_9 = "";
				String txtchkICC_A_SEAExclusive_9 = "";
				String txtchkICC_A_SEAWarranty_9 = "";
				String txtchkICC_A_SEAWarCover_9 = "";

				String txtchkICC_A_SEAClause_10 = "";
				String txtchkICC_A_SEAExclusive_10 = "";
				String txtchkICC_A_SEAWarranty_10 = "";
				String txtchkICC_A_SEAWarCover_10 = "";

				String txtchkICC_A_SEAClause_11 = "";
				String txtchkICC_A_SEAExclusive_11 = "";
				String txtchkICC_A_SEAWarranty_11 = "";
				String txtchkICC_A_SEAWarCover_11 = "";


				if ("".equals(bean.getTxtchkICC_A_SEAClause_1())
						|| bean.getTxtchkICC_A_SEAClause_1().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_1().equals("null")) {
					txtchkICC_A_SEAClause_1 = "";
				} else {
					txtchkICC_A_SEAClause_1 = bean.getTxtchkICC_A_SEAClause_1();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_1())
						|| bean.getTxtchkICC_A_SEAExclusive_1().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_1().equals("null")) {
					txtchkICC_A_SEAExclusive_1 = "";
				} else {
					txtchkICC_A_SEAExclusive_1 = bean
					.getTxtchkICC_A_SEAExclusive_1();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_1())
						|| bean.getTxtchkICC_A_SEAWarranty_1().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_1().equals("null")) {
					txtchkICC_A_SEAWarranty_1 = "";
				} else {
					txtchkICC_A_SEAWarranty_1 = bean.getTxtchkICC_A_SEAWarranty_1();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_1())
						|| bean.getTxtchkICC_A_SEAWarCover_1().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_1().equals("null")) {
					txtchkICC_A_SEAWarCover_1 = "";
				} else {
					txtchkICC_A_SEAWarCover_1 = bean.getTxtchkICC_A_SEAWarCover_1();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAClause_2())
						|| bean.getTxtchkICC_A_SEAClause_2().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_2().equals("null")) {
					txtchkICC_A_SEAClause_2 = "";
				} else {
					txtchkICC_A_SEAClause_2 = bean.getTxtchkICC_A_SEAClause_2();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_2())
						|| bean.getTxtchkICC_A_SEAExclusive_2().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_2().equals("null")) {
					txtchkICC_A_SEAExclusive_2 = "";
				} else {
					txtchkICC_A_SEAExclusive_2 = bean
					.getTxtchkICC_A_SEAExclusive_2();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_2())
						|| bean.getTxtchkICC_A_SEAWarranty_2().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_2().equals("null")) {
					txtchkICC_A_SEAWarranty_2 = "";
				} else {
					txtchkICC_A_SEAWarranty_2 = bean.getTxtchkICC_A_SEAWarranty_2();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_2())
						|| bean.getTxtchkICC_A_SEAWarCover_2().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_2().equals("null")) {
					txtchkICC_A_SEAWarCover_2 = "";
				} else {
					txtchkICC_A_SEAWarCover_2 = bean.getTxtchkICC_A_SEAWarCover_2();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAClause_3())
						|| bean.getTxtchkICC_A_SEAClause_3().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_3().equals("null")) {
					txtchkICC_A_SEAClause_3 = "";
				} else {
					txtchkICC_A_SEAClause_3 = bean.getTxtchkICC_A_SEAClause_3();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_3())
						|| bean.getTxtchkICC_A_SEAExclusive_3().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_3().equals("null")) {
					txtchkICC_A_SEAExclusive_3 = "";
				} else {
					txtchkICC_A_SEAExclusive_3 = bean
					.getTxtchkICC_A_SEAExclusive_3();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_3())
						|| bean.getTxtchkICC_A_SEAWarranty_3().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_3().equals("null")) {
					txtchkICC_A_SEAWarranty_3 = "";
				} else {
					txtchkICC_A_SEAWarranty_3 = bean.getTxtchkICC_A_SEAWarranty_3();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_3())
						|| bean.getTxtchkICC_A_SEAWarCover_3().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_3().equals("null")) {
					txtchkICC_A_SEAWarCover_3 = "";
				} else {
					txtchkICC_A_SEAWarCover_3 = bean.getTxtchkICC_A_SEAWarCover_3();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAClause_8())
						|| bean.getTxtchkICC_A_SEAClause_8().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_8().equals("null")) {
					txtchkICC_A_SEAClause_8 = "";
				} else {
					txtchkICC_A_SEAClause_8 = bean.getTxtchkICC_A_SEAClause_8();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_8())
						|| bean.getTxtchkICC_A_SEAExclusive_8().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_8().equals("null")) {
					txtchkICC_A_SEAExclusive_8 = "";
				} else {
					txtchkICC_A_SEAExclusive_8 = bean
					.getTxtchkICC_A_SEAExclusive_8();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_8())
						|| bean.getTxtchkICC_A_SEAWarranty_8().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_8().equals("null")) {
					txtchkICC_A_SEAWarranty_8 = "";
				} else {
					txtchkICC_A_SEAWarranty_8 = bean.getTxtchkICC_A_SEAWarranty_8();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_8())
						|| bean.getTxtchkICC_A_SEAWarCover_8().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_8().equals("null")) {
					txtchkICC_A_SEAWarCover_8 = "";
				} else {
					txtchkICC_A_SEAWarCover_8 = bean.getTxtchkICC_A_SEAWarCover_8();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAClause_9())
						|| bean.getTxtchkICC_A_SEAClause_9().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_9().equals("null")) {
					txtchkICC_A_SEAClause_9 = "";
				} else {
					txtchkICC_A_SEAClause_9 = bean.getTxtchkICC_A_SEAClause_9();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_9())
						|| bean.getTxtchkICC_A_SEAExclusive_9().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_9().equals("null")) {
					txtchkICC_A_SEAExclusive_9 = "";
				} else {
					txtchkICC_A_SEAExclusive_9 = bean
					.getTxtchkICC_A_SEAExclusive_9();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_9())
						|| bean.getTxtchkICC_A_SEAWarranty_9().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_9().equals("null")) {
					txtchkICC_A_SEAWarranty_9 = "";
				} else {
					txtchkICC_A_SEAWarranty_9 = bean.getTxtchkICC_A_SEAWarranty_9();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_9())
						|| bean.getTxtchkICC_A_SEAWarCover_9().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_9().equals("null")) {
					txtchkICC_A_SEAWarCover_9 = "";
				} else {
					txtchkICC_A_SEAWarCover_9 = bean.getTxtchkICC_A_SEAWarCover_9();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAClause_10())
						|| bean.getTxtchkICC_A_SEAClause_10().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_10().equals("null")) {
					txtchkICC_A_SEAClause_10 = "";
				} else {
					txtchkICC_A_SEAClause_10 = bean.getTxtchkICC_A_SEAClause_10();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_10())
						|| bean.getTxtchkICC_A_SEAExclusive_10().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_10().equals("null")) {
					txtchkICC_A_SEAExclusive_10 = "";
				} else {
					txtchkICC_A_SEAExclusive_10 = bean
					.getTxtchkICC_A_SEAExclusive_10();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_10())
						|| bean.getTxtchkICC_A_SEAWarranty_10().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_10().equals("null")) {
					txtchkICC_A_SEAWarranty_10 = "";
				} else {
					txtchkICC_A_SEAWarranty_10 = bean
					.getTxtchkICC_A_SEAWarranty_10();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_10())
						|| bean.getTxtchkICC_A_SEAWarCover_10().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_10().equals("null")) {
					txtchkICC_A_SEAWarCover_10 = "";
				} else {
					txtchkICC_A_SEAWarCover_10 = bean
					.getTxtchkICC_A_SEAWarCover_10();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAClause_11())
						|| bean.getTxtchkICC_A_SEAClause_11().equals(null)
						|| bean.getTxtchkICC_A_SEAClause_11().equals("null")) {
					txtchkICC_A_SEAClause_11 = "";
				} else {
					txtchkICC_A_SEAClause_11 = bean.getTxtchkICC_A_SEAClause_11();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAExclusive_11())
						|| bean.getTxtchkICC_A_SEAExclusive_11().equals(null)
						|| bean.getTxtchkICC_A_SEAExclusive_11().equals("null")) {
					txtchkICC_A_SEAExclusive_11 = "";
				} else {
					txtchkICC_A_SEAExclusive_11 = bean
					.getTxtchkICC_A_SEAExclusive_11();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarranty_11())
						|| bean.getTxtchkICC_A_SEAWarranty_11().equals(null)
						|| bean.getTxtchkICC_A_SEAWarranty_11().equals("null")) {
					txtchkICC_A_SEAWarranty_11 = "";
				} else {
					txtchkICC_A_SEAWarranty_11 = bean
					.getTxtchkICC_A_SEAWarranty_11();
				}
				if ("".equals(bean.getTxtchkICC_A_SEAWarCover_11())
						|| bean.getTxtchkICC_A_SEAWarCover_11().equals(null)
						|| bean.getTxtchkICC_A_SEAWarCover_11().equals("null")) {
					txtchkICC_A_SEAWarCover_11 = "";
				} else {
					txtchkICC_A_SEAWarCover_11 = bean
					.getTxtchkICC_A_SEAWarCover_11();
				}
				/*if ("".equals(bean.getTxtchkICC_A_SEAClause_12())
					|| bean.getTxtchkICC_A_SEAClause_12().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_12().equals("null")) {
				txtchkICC_A_SEAClause_12 = "";
			} else {
				txtchkICC_A_SEAClause_12 = bean.getTxtchkICC_A_SEAClause_12();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_12())
					|| bean.getTxtchkICC_A_SEAExclusive_12().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_12().equals("null")) {
				txtchkICC_A_SEAExclusive_12 = "";
			} else {
				txtchkICC_A_SEAExclusive_12 = bean
						.getTxtchkICC_A_SEAExclusive_12();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_12())
					|| bean.getTxtchkICC_A_SEAWarranty_12().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_12().equals("null")) {
				txtchkICC_A_SEAWarranty_12 = "";
			} else {
				txtchkICC_A_SEAWarranty_12 = bean
						.getTxtchkICC_A_SEAWarranty_12();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_12())
					|| bean.getTxtchkICC_A_SEAWarCover_12().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_12().equals("null")) {
				txtchkICC_A_SEAWarCover_12 = "";
			} else {
				txtchkICC_A_SEAWarCover_12 = bean
						.getTxtchkICC_A_SEAWarCover_12();
			}*/
	/*String remarks = "";

				if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null)
						|| bean.getRemarks().equals("null"))
					remarks = " ";
				else
					remarks = bean.getRemarks();

				String ICC_A_Sea_Rate = "";
				ICC_A_Sea_Rate = txtchkICC_A_SEAClause_1 + "~"
				+ txtchkICC_A_SEAExclusive_1 + "~"
				+ txtchkICC_A_SEAWarranty_1 + "~"
				+ txtchkICC_A_SEAWarCover_1 + "~"
				+ bean.getRadDetective_1();
				String ICC_B_Sea_Rate = "";
				ICC_B_Sea_Rate = txtchkICC_A_SEAClause_2 + "~"
				+ txtchkICC_A_SEAExclusive_2 + "~"
				+ txtchkICC_A_SEAWarranty_2 + "~"
				+ txtchkICC_A_SEAWarCover_2 + "~"
				+ bean.getRadDetective_2();
				String ICC_C_Sea_Rate = "";
				ICC_C_Sea_Rate = txtchkICC_A_SEAClause_3 + "~"
				+ txtchkICC_A_SEAExclusive_3 + "~"
				+ txtchkICC_A_SEAWarranty_3 + "~"
				+ txtchkICC_A_SEAWarCover_3 + "~"
				+ bean.getRadDetective_3();
				String ICC_Air_Cargo_Air = "";
				ICC_Air_Cargo_Air = txtchkICC_A_SEAClause_8 + "~"
				+ txtchkICC_A_SEAExclusive_8 + "~"
				+ txtchkICC_A_SEAWarranty_8 + "~"
				+ txtchkICC_A_SEAWarCover_8 + "~"
				+ bean.getRadDetective_8();
				String All_Risks_Land_Transit = "";
				All_Risks_Land_Transit = txtchkICC_A_SEAClause_9 + "~"
				+ txtchkICC_A_SEAExclusive_9 + "~"
				+ txtchkICC_A_SEAWarranty_9 + "~"
				+ txtchkICC_A_SEAWarCover_9 + "~"
				+ bean.getRadDetective_9();
				String Land_Transit_Land = "";
				Land_Transit_Land = txtchkICC_A_SEAClause_10 + "~"
				+ txtchkICC_A_SEAExclusive_10 + "~"
				+ txtchkICC_A_SEAWarranty_10 + "~"
				+ txtchkICC_A_SEAWarCover_10 + "~"
				+ bean.getRadDetective_10();
				String All_Risks_Parcel_Post_Air = "";
				All_Risks_Parcel_Post_Air = txtchkICC_A_SEAClause_11 + "~"
				+ txtchkICC_A_SEAExclusive_11 + "~"
				+ txtchkICC_A_SEAWarranty_11 + "~"
				+ txtchkICC_A_SEAWarCover_11 + "~"
				+ bean.getRadDetective_11();
				/*String All_Risks_Sea_and_Air = "";
			All_Risks_Sea_and_Air = txtchkICC_A_SEAClause_12 + "~"
					+ txtchkICC_A_SEAExclusive_12 + "~"
					+ txtchkICC_A_SEAWarranty_12 + "~"
					+ txtchkICC_A_SEAWarCover_12 + "~"
					+ bean.getRadDetective_12();		 

				String string="";
				boolean bo = validateDate(bean.getPrevdate());
				if(bo==false) 
				{							
					string="update";

					Object[] val = {bean.getCommodityName(),
							bean.getCommodityRates(), bean.getCoverage_Referal(),
							ICC_A_Sea_Rate, ICC_B_Sea_Rate, ICC_C_Sea_Rate, 
							ICC_Air_Cargo_Air, All_Risks_Land_Transit,
							Land_Transit_Land, All_Risks_Parcel_Post_Air,remarks,
							bean.getStatus(), bean.getCode(), bean.getEff_date(),
							bean.getCommodityTypeID(),bean.getCommodityID(),branchCode};

					rservice.getUpdateCommodity(val,string);
				}
				else
				{				
					string="insert";
					Object[] val = { bean.getCommodityID(), bean.getCommodityName(),
							bean.getCommodityRates(), bean.getCoverage_Referal(),
							ICC_A_Sea_Rate, ICC_B_Sea_Rate, ICC_C_Sea_Rate,
							ICC_Air_Cargo_Air, All_Risks_Land_Transit,
							Land_Transit_Land, All_Risks_Parcel_Post_Air, "", "", "", "", remarks,
							bean.getStatus(), bean.getCode(), bean.getEff_date(),
							Integer.parseInt(bean.getAmendID()) + 1,
							bean.getCommodityTypeID(), branchCode, "" };			

					rservice.getUpdateCommodity(val,string);
				}		

				Object[] val1 = { branchCode, branchCode };
				//commodityList = rservice.getCommodityList(val1);
				this.addActionMessage(getText("update.success"));
			}
			menuType="commoditymaster";
			return "commonList";
		}
		return "editCommodity";
	}*/
	public String updateCurrency() {
		String remarks = "";
		if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null)
				|| bean.getRemarks().equals("null"))
			remarks = " ";
		else
			remarks=bean.getRemarks();
		validateCurrency();
		if (!hasActionErrors()) {	
			if("add".equals(bean.getMode()))
			{
				Object[] val={bean.getCurrencyType(),0,bean.getEff_date(),remarks,bean.getStatus(),bean.getCode(),branchCode,branchCode,bean.getCurrencyShortName(),bean.getRfactor(),bean.getSubCurrency()};
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getNewCurrency(val);
				//currencyList = rservice.getCurrencyList();
				this.addActionMessage(getText("insert.success"));
			}
			else
			{
				String string="";
				//boolean bo = validateDate(bean.getPrevdate());
				boolean bo=checkDate(bean.getPrevdate(), bean.getEff_date());
				if(bo)
				{

					Object[] val={bean.getCurrencyType(),bean.getEff_date(),remarks,bean.getRfactor(),bean.getCode(),bean.getCurrencyShortName(),bean.getStatus(),bean.getSubCurrency(),bean.getCurrencyID(),branchCode};
					string="update";
					rservice.getUpdateCurrency(val,string);
				}
				else
				{
					Object[] val={bean.getCurrencyID(),bean.getCurrencyType(),Integer.parseInt(bean.getAmendID())+1,bean.getEff_date(),remarks,bean.getStatus(),bean.getCode(),branchCode,branchCode,bean.getCurrencyShortName(),bean.getRfactor(),bean.getSubCurrency()};
					logger.info("val.args===>" + StringUtils.join(val, ", "));
					string="insert";
					rservice.getUpdateCurrency(val,string);
				}
				//currencyList = rservice.getCurrencyList();
				this.addActionMessage(getText("update.success"));
			}
			menuType="currencymaster";
			return "commonList";
		}

		return "editCurrency";
	}
	public String updateExchange() {
		String remarks = "";
		if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null)
				|| bean.getRemarks().equals("null"))
			remarks = " ";
		else

			remarks=bean.getRemarks();
		validateExchange();
		if (!hasActionErrors()) {	
			if("add".equals(bean.getMode()))
			{
				Object[] val = { bean.getExchangeRate(), bean.getCurrencyID(), 0,
						bean.getEff_date(), remarks, bean.getStatus(),
						bean.getCode(), branchCode };
				rservice.getNewExchange(val);
				//exchangeList = rservice.getExchangeList();
				this.addActionMessage(getText("insert.success"));
			}
			else
			{
				String string="";
				//boolean bo = validateDate(bean.getPrevdate());
				boolean bo=checkDate(bean.getPrevdate(), bean.getEff_date());
				if(bo)
				{
					Object[] val={bean.getExchangeRate(),bean.getEff_date(),bean.getStatus(),remarks,bean.getCode(),bean.getExchangeID()};
					string="update";
					rservice.getUpdateExchange(val,string);
				}
				else
				{
					Object[] val={bean.getExchangeID(),bean.getExchangeRate(),bean.getCurrencyID(),Integer.parseInt(bean.getAmendID())+1,bean.getEff_date(),remarks,bean.getStatus(),bean.getCode(),branchCode};
					logger.info("val.args===>" + StringUtils.join(val, ", "));
					string="insert";
					rservice.getUpdateExchange(val,string);
				}
				//exchangeList = rservice.getExchangeList();
				this.addActionMessage(getText("update.success"));
			}
			menuType="exchangemaster";
			return "commonList";
		}
		return "editExchange";
	}

	private List<Map<String, Object>> exclusionList;

	public List<Map<String, Object>> getExclusionList() {
		return exclusionList;
	}
	public String updateExclusion() {
		String remarks = "";
		if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null)
				|| bean.getRemarks().equals("null"))
			remarks = " ";
		else
			remarks=bean.getRemarks();
		validatenewExclusion();
		if (!hasActionErrors()) {
			if("add".equals(bean.getMode()))
			{
				Object[] val = { branchCode, bean.getExclusionName(),
						bean.getCode(), remarks, bean.getStatus(),
						branchCode, branchCode,bean.getCode() };
				logger.info("val.args===>" + StringUtils.join(val, ", "));				rservice.getNewExclusion(val);
				exclusionList = rservice.getExclusionList(branchCode);
				this.addActionMessage(getText("insert.success"));
			}
			else
			{
				Object[] val = { bean.getExclusionName(), remarks,
						bean.getCode(), bean.getStatus(),bean.getCode(), bean.getExclusionID(),
						branchCode };
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				rservice.getUpdateExclusion(val);

				exclusionList = rservice.getExclusionList(branchCode);
				this.addActionMessage(getText("update.success"));
			}
			menuType="exclusionmaster";
			return "commonList";
		}
		return "updateExclusion";
	}
	public String updatecategory()
	{
		validateCategory();
		if (!hasActionErrors()) {

			if("edit".equals(bean.getMode()))
			{
				String string="";
				boolean bo = validateDate(bean.getPrevdate());
				if(bo==false)
				{
					Object val[] ={bean.getCatname(),bean.getCatrate(),bean.getEff_date(),bean.getEff_date(),bean.getExp_date(),bean.getStatus(),bean.getRemarks(),bean.getCatID(),branchCode,bean.getCatID(),branchCode};
					string="update";
					rservice.updatcateg(val,string);
				}
				else
				{
					Object val[] ={bean.getCatID(),bean.getCatname(),bean.getCatrate(),bean.getCatID(),branchCode,bean.getEff_date(),bean.getEff_date(),bean.getExp_date(),bean.getStatus(),branchCode,bean.getRemarks(),bean.getCatname()};
					string="insert";
					rservice.updatcateg(val,string);
				}
				addActionMessage(getText("update.success"));
			}
			else
			{
				int amend_id=0;
				Object val[] ={branchCode,bean.getCatname(),bean.getCatrate(),amend_id,bean.getEff_date(),bean.getEff_date(),bean.getExp_date(),bean.getStatus(),branchCode,bean.getRemarks()==null?"":bean.getRemarks()};
				rservice.insertcateg(val);
				this.addActionMessage(getText("insert.success"));
			}
			menuType="categorymaster";
			return "commonList";		
		}

		return "editCategory";
	}
	public String updatecovercomm()
	{
		validatecovercomm();
		if (!hasActionErrors()) {

			/*if("edit".equals(bean.getMode()))
			{
				Object val[] ={branchCode,bean.getCoverageID(),bean.gettransID(),bean.getCommodityID(),branchCode,bean.getCoverageID()
						,bean.gettransID(),bean.getCommodityID(),branchCode,bean.getEff_date(),bean.getCoverRate(),bean.getReferralStatus() ,bean.getRemarks(),bean.getStatus(),bean.getCommodityID(),bean.getCatrate(),bean.getCoverageID(),bean.gettransID(),branchCode};
				rservice.updatcovercomm(val);				
				addActionMessage(getText("update.success"));
			}
			else
			{
				int amend_id=0;
				Object val[] ={branchCode,amend_id,bean.getEff_date(),bean.getCoverRate(),bean.getReferralStatus(),bean.getRemarks(),bean.getStatus(),bean.getCatrate(),bean.getCoverageID(),bean.gettransID(),branchCode};
				rservice.insertcovercomm(val);
				this.addActionMessage(getText("insert.success"));
			}*/
			rservice.insertcovercomm(bean,branchCode);
			this.addActionMessage(getText("insert.success"));
			menuType="covercommomaster";
			return "commonList";		
		}
		coverages = new ArrayList();
		return "editCoverComm";
	}

	public String updateCover() {
		validateCover();
		if (!hasActionErrors()) {
			/*if("add".equals(bean.getMode()))
		{
				String remarks = "";
				if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null)
						|| bean.getRemarks().equals("null"))
					remarks = " ";
				else
					remarks = bean.getRemarks();
				Object[] val = { branchCode, bean.getCoverName(),
						bean.getTransID(), bean.getCoverDesc(),branchCode, 0,
						bean.getEff_date(), branchCode, branchCode,
						bean.getCoverRate(),bean.getReferralStatus(), remarks, bean.getStatus() };

				rservice.getNewCover(val);
				Object[] val1 = { branchCode, branchCode };
				//coverList = rservice.getCoverList(val1);
				this.addActionMessage(getText("insert.success"));
		}
		else
		{
			String remarks = "";
			if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null)
					|| bean.getRemarks().equals("null"))
				remarks = " ";
			else
				remarks = bean.getRemarks();
			//Object[] val = {bean.getCoverID(),bean.getCovername(),bean.getModeTransportId(),bean.getCoverDesc(),bean.getAmendID(),bean.getEff_date(),bean.getBranchID(),bean.getCoverRate(),bean.getReferralStatus(),bean.getRemarks(),bean.getStatus()};
			//Object[] val = {bean.getCoverID(),bean.getCoverName(),bean.getTransID(),bean.getCoverDesc(),bean.getAmendID(),bean.getEff_date(),branchCode,bean.getCoverRate(),bean.getReferralStatus(),bean.getRemarks(),bean.getStatus()};

			rservice.getUpdateCover(bean,branchCode);
			Object[] val1 = { branchCode, branchCode };
			//coverList = rservice.getCoverList(val1);
			this.addActionMessage(getText("update.success"));*/
			if("add".equals(bean.getMode()))
			{
				String remarks = "";
				if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null)
						|| bean.getRemarks().equals("null"))
					remarks = " ";
				else
					remarks = bean.getRemarks();
				Object[] val = { branchCode, bean.getCoverName(),bean.getTransID(), bean.getCoverDesc(),bean.getCode(), 0,bean.getEff_date(), branchCode, branchCode,bean.getCoverRate(),bean.getReferralStatus(), remarks, bean.getStatus() };
				rservice.getNewCover(val);
				Object[] val1 = { branchCode, branchCode };
				//coverList = rservice.getCoverList(val1);
				this.addActionMessage(getText("insert.success"));
			}
			else
			{   
				String str="";
				boolean bo = validatePrevDate(bean.getPrevdate());
				if(bo==false)
				{ 
					str="update";
					String remarks = "";
					if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null)
							|| bean.getRemarks().equals("null"))
						remarks = " ";
					else
						remarks = bean.getRemarks();
					rservice.getUpdateCover(bean,branchCode,str);
					Object[] val1 = { branchCode, branchCode };
					this.addActionMessage(getText("update.success"));
				}
				else{
					str="insert";
					String remarks = "";
					if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null)
							|| bean.getRemarks().equals("null"))
						remarks = " ";
					else
						remarks = bean.getRemarks();
					rservice.getUpdateCover(bean,branchCode,str);
					Object[] val1 = { branchCode, branchCode };
					this.addActionMessage(getText("update.success"));
				}
			}
			menuType="covermaster";
			return "commonList";
		}
		return "editCover";
	}
	/*public String getConveyModeListAjax()
	{
		if("conveyanceLists".equals(bean.getReqFrom())){
			conveyanceList=rservice.getConveyModeListAjax(branchCode, bean.getModeTransportId());
    	}
    	return "adminAjax";

	}
	public String getCountryListAjax()
	{
		if("countryLists".equals(bean.getReqFrom())){
			countryList=rservice.getCountryListAjax(bean.getSearchBy(), bean.getSearchValue());

		}
		return "adminAjax";
	}*/
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public String getListAjax() {
		if ("bankLists".equals(bean.getReqFrom())) {
			bankList = rservice.getBankListAjax(bean.getSearchBy(), bean
					.getSearchValue());
		} else if ("materialLists".equals(bean.getReqFrom())) {
			materialList = rservice.getMaterialListAjax(branchCode, bean
					.getSearchBy(), bean.getSearchValue());
		} else if ("warrateLists".equals(bean.getReqFrom())) {
			warrateList = rservice.getWarrateListAjax(branchCode, bean
					.getSearchBy(), bean.getSearchValue());
		} else if ("saletermLists".equals(bean.getReqFrom())) {
			saletermList = rservice.getSaletermListAjax(branchCode, bean
					.getSearchBy(), bean.getSearchValue());
		} else if ("toleranceLists".equals(bean.getReqFrom())) {
			toleranceList = rservice.getToleranceListAjax(branchCode, bean
					.getSearchBy(), bean.getSearchValue());
		} else if ("comExLists".equals(bean.getReqFrom())) {
			comExList = rservice.getComExListAjax(branchCode, bean
					.getSearchBy(), bean.getSearchValue());
		} else if ("extraCoverLists".equals(bean.getReqFrom())) {
			extraCoverList = rservice.getExtraCoverListAjax(branchCode, bean
					.getSearchBy(), bean.getSearchValue());
		} else if ("cityLists".equals(bean.getReqFrom())) {
			cityList = rservice.getCityListAjax(bean.getSearchBy(), bean
					.getSearchValue());
		} else if ("vesselLists".equals(bean.getReqFrom())) {
			vesselList = rservice.getVesselListAjax(bean.getSearchBy(), bean
					.getSearchValue());
		} else if ("warrantyLists".equals(bean.getReqFrom())) {
			warrantyList = rservice.getWarrantyListAjax(branchCode, bean
					.getSearchBy(), bean.getSearchValue());
		} else if ("constantLists".equals(bean.getReqFrom())) {
			constantList = rservice.getConstantListAjax(branchCode, bean
					.getSearchBy(), bean.getSearchValue());
		} else if ("countryDetLists".equals(bean.getReqFrom())) {
			countryDetList = rservice.getCountryDetListAjax(bean.getSearchBy(),
					bean.getSearchValue());

		} else if ("baserates".equals(bean.getReqFrom())) {

			coverages = rservice.getCoverages(bean.getSearchValue(), branchCode);			

		} else if ("baseratesadd".equals(bean.getReqFrom())) {

			coverages = rservice.getCoveragesadd(bean.getSearchValue(), branchCode);			

		}
		else if ("baserates1".equals(bean.getReqFrom())) {

			covey = rservice.getCovey(bean.getSearchValue(), branchCode);

		}  
		else if ("agentLists".equalsIgnoreCase(bean.getReqFrom())) {
			agentList = rservice.getAgentListAjax(branchCode, bean
					.getSearchBy(), bean.getSearchValue());
		} else if ("cityIDs".equalsIgnoreCase(bean.getReqFrom())) {
			cityList = rservice.getCityListAjax1(bean.getCountryID());
		} else if ("commodityLists".equals(bean.getReqFrom())) {
			Object[] val = { branchCode, branchCode, bean.getSearchValue() };
			commodityList = rservice.getCommodityListAjax(val, bean
					.getSearchBy());
		} else if ("currencyLists".equals(bean.getReqFrom())) {
			currencyList = rservice.getCurrenctListAjax(bean.getSearchValue());
		} else if ("exchangeLists".equals(bean.getReqFrom())) {
			exchangeList = rservice.getExchangeListAjax(bean.getSearchValue());
		} else if ("coverLists".equals(bean.getReqFrom())) {
			coverList = rservice.getCoverListAjax(branchCode, bean
					.getSearchValue());
		} else if ("exclusionLists".equalsIgnoreCase(bean.getReqFrom())) {
			exclusionList = rservice.getExclusionListAjax(branchCode, bean
					.getSearchBy(), bean.getSearchValue());
		} /*else if ("stagenames".equals(bean.getReqFrom())) {

			stagenames = rservice.getStagenames(bean);
		}  */
		return "adminAjax";
	}

	public void validatenewExclusion() throws NumberFormatException {
		String str = "exclusion";
		if (StringUtils.isBlank(bean.getExclusionName()))
			this.addActionError(getText("exclusionName.required"));
		/*else if(!StringUtils.isAlphaSpace(bean.getExclusionName()))
			this.addActionError(getText("exclusionName.invalid"));*/
		else if ("add".equals(bean.getMode())) {
			Object[] name = { bean.getExclusionName(), branchCode };
			if (rservice.getNameExist(name, str) != 0)
				this.addActionError(getText("exclusionName.already"));
		}
		if (StringUtils.isBlank(bean.getCode()))
			this.addActionError(getText("code.required"));
		else if(!StringUtils.isAlphanumeric(bean.getCode()))
			this.addActionError(getText("code.invalid"));
		else if ("add".equals(bean.getMode())) {
			Object[] val = { bean.getCode(), branchCode };
			if (cservice.getExist(val, str) != 0)
				this.addActionError(getText("transRSA.already"));
		}
	}
	public void validatenewTransport() {
		String str = "transport";
		if (StringUtils.isBlank(bean.getTransDesc()))
			this.addActionError(getText("transDesc.required"));
		else if (StringUtils.isNumeric(bean.getTransDesc()))
			this.addActionError(getText("transDesc.valid"));
		else if ("add".equals(bean.getMode())) {
			Object[] name = { bean.getTransDesc(), branchCode };
			if (rservice.getNameExist(name, str) != 0)
				this.addActionError(getText("transDesc.already"));
		}
		if (StringUtils.isBlank(bean.getTransRSA()))
			this.addActionError(getText("transRSA.required"));		
		else if ("add".equals(bean.getMode())) {
			Object[] val = { bean.getTransRSA(), branchCode };
			if (cservice.getExist(val, str) != 0)
				this.addActionError(getText("transRSA.already"));
		}
		if (StringUtils.isBlank(bean.getTransDO()))
			this.addActionError(getText("transDO.required"));
		else if (!StringUtils.isNumeric(bean.getTransDO()))
			this.addActionError(getText("transDO.invalid"));
	}
	public void validatenewCity() throws NumberFormatException {
		if (StringUtils.isBlank(bean.getCityName()))
			this.addActionError(getText("cityName.required"));
		else if (!StringUtils.isAlphaSpace(bean.getCityName()))
			this.addActionError(getText("cityName.invalid"));
		else
		{
			String str = "city1";
			Object[] val = {bean.getCountryID(), bean.getCityName()};

			if ("add".equals(bean.getMode())) {

				if (rservice.getNameExist(val, str) != 0)
					this.addActionError(getText("city.already"));
			}
		}
		if ("-1".equals(bean.getCountryID())) {
			this.addActionError(getText("country.select"));
		}
		if (StringUtils.isBlank(bean.getCode()))
			this.addActionError(getText("code.required"));
		else if(!StringUtils.isAlphanumeric(bean.getCode()))
			this.addActionError(getText("code.invalid"));
		else {
			String str = "city";			
			Object[] val = { bean.getCode(), bean.getCountryID()};
			logger.info("val.args===>" + StringUtils.join(val, ", "));
			if ("add".equalsIgnoreCase(bean.getMode())) {
				if (cservice.getExist(val, str) != 0)
					this.addActionError(getText("transRSA.already"));
			}			

		}
	}
	public void validateNewCountry() throws NumberFormatException {
		try {
			String str = "country";
			Object[] val = { bean.getCode() };
			Object[] name = { bean.getCountryName() };
			if (StringUtils.isBlank(bean.getCountryName()))
				this.addActionError(getText("country.required"));
			else if (!StringUtils.isAlphaSpace(bean.getCountryName()))
				this.addActionError(getText("country.invalid"));
			else {
				if ("add".equals(bean.getMode())) {
					logger.info("val.args===>" + StringUtils.join(val, ", "));
					if (rservice.getNameExist(name, str) != 0)
						this.addActionError(getText("country.already"));
				}
			}
			if(!StringUtils.isAlphaSpace(bean.getCountryShortName()))
				this.addActionError(getText("country.shortname.invalid"));

			if (StringUtils.isBlank(bean.getCountryNat()))
				this.addActionError(getText("countrynat.required"));
			else if(!StringUtils.isAlphaSpace(bean.getCountryNat()))
				this.addActionError(getText("countrynat.invalid"));
			if (StringUtils.isBlank(bean.getCode()))
				this.addActionError(getText("code.required"));
			else if(!StringUtils.isAlphanumeric(bean.getCode()))
				this.addActionError(getText("code.invalid"));
			else {
				if ("add".equals(bean.getMode())) {
					logger.info("val.args===>" + StringUtils.join(val, ", "));
					if (cservice.getExist(val, str) != 0)
						this.addActionError(getText("transRSA.already"));
				}
			}


			if(StringUtils.isBlank(bean.getEff_date()))
				this.addActionError(getText("date.required"));
			else{
				boolean bo=validateDate(bean.getEff_date());
				if(bo)
					this.addActionError(getText("date.invalid"));
			}	
			if(StringUtils.isBlank(bean.getGeoRate()))
				this.addActionError(getText("geoRate.required"));
			else if(!"".equals(validation.validDouble(bean.getGeoRate())))
				this.addActionError(getText("geoRate.invalid"));
			else 
			{
				boolean bo = validString(bean.getGeoRate(),2);
				if(bo)
					this.addActionError(getText("geoRate.required"));	
			}
		}
		catch(NumberFormatException e)
		{

			logger.info("Conversion Error");
		}
	}

	public void validateNewBank() {
		String str = "bank";
		//String str1="country1";
		Object[] val = { bean.getCode() };
		logger.info("val.args===>" + StringUtils.join(val, ", "));
		if (StringUtils.isBlank(bean.getBankName()))
			this.addActionError(getText("bank.required"));
		else if(!StringUtils.isAlphaSpace(bean.getBankName()))
			this.addActionError(getText("bank.invalid"));
		if (StringUtils.isBlank(bean.getCode()))
			this.addActionError(getText("code.required"));
		else if(!StringUtils.isAlphanumeric(bean.getCode()))
			this.addActionError(getText("code.invalid"));
		else {
			if ("add".equals(bean.getMode())) {
				if (cservice.getExist(val, str) != 0)
					this.addActionError(getText("transRSA.already"));
			}
		}
		if (StringUtils.isBlank(bean.getEff_date()))
			this.addActionError(getText("date.required"));

		else{
			boolean bo=validateDate(bean.getEff_date());
			if(bo)
				this.addActionError(getText("date.invalid"));
		}

		Object[] name = { bean.getBankName(), bean.getCountryID() };
		if ("-1".equals(bean.getCountryID()))
			this.addActionError(getText("country.select"));
		else {
			if ("add".equals(bean.getMode())) {
				if (rservice.getNameExist(name, str) != 0)
					this.addActionError(getText("bank.already"));
			}
		}
		/*Object[] name1 = {bean.getCountryID() };
		if ("add".equals(bean.getMode())) {
			if (rservice.getNameExist(name1, str1) != 0)
				this.addActionError(getText("country.already"));
		}*/
	}

	public void validateNewMaterial() throws NumberFormatException {
		try {
			String str = "material";
			Object[] val = { bean.getCode(), branchCode };
			Object[] name = { bean.getMaterialName(), branchCode };
			logger.info("val.args===>" + StringUtils.join(val, ", "));
			if (!"edit".equals(bean.getMode())) {
				if (cservice.getExist(val, str) != 0)
					this.addActionError(getText("transRSA.already"));
				/*if (rservice.getNameExist(name, str) != 0)
					this.addActionError(getText("material.name.already"));*/
			}
			if(StringUtils.isBlank(bean.getCoverID())) {
				this.addActionError("Please select the cover");
			}
			if (!"edit".equals(bean.getMode())) {
				if(StringUtils.isNotBlank(bean.getMaterialName()) && StringUtils.isNotBlank(bean.getCoverID()) && rservice.validateNewMaterial(bean,branchCode)>0) {
					this.addActionError("Already Exists");
				}
			}
			if(StringUtils.isBlank(bean.getMaterialName()))
				this.addActionError(getText("material.required"));
			if(StringUtils.isBlank(bean.getMaterialRate()))
				this.addActionError(getText("material.rate.invalid"));
			/*else if(!StringUtils.isNumeric(bean.getMaterialRate()))
				this.addActionError(getText("material.rate.invalid"));*/
			else if(Double.parseDouble(bean.getMaterialRate())<0)
				this.addActionError(getText("material.rate.invalid"));
			else 
			{
				boolean bo = validString(bean.getMaterialRate(),4);
				if(bo)
					this.addActionError(getText("material.rate.required"));	
			}
			if(StringUtils.isBlank(bean.getCode()))
				this.addActionError(getText("code.required"));
			else if(!StringUtils.isAlphanumeric(bean.getCode()))
				this.addActionError(getText("code.invalid"));
			if(StringUtils.isBlank(bean.getEff_date()))
				this.addActionError(getText("date.required"));
			else{
				boolean bo=validateDate(bean.getEff_date());
				if(bo)
					this.addActionError(getText("date.invalid"));
			}

		}

		catch(NumberFormatException e)
		{
			logger.info("Conversion Error");
		}

	}

	public void validateNewWar() throws NumberFormatException {
		try {
			String str = "war";
			Object[] val = { bean.getCode(), branchCode };
			Object[] name = { bean.getTransID(), branchCode };
			if (StringUtils.isBlank(bean.getWarName()))
				this.addActionError(getText("warDesc.required"));
			else {
				if ("add".equals(bean.getMode())) {
					if (rservice.getNameExist(name, str) != 0)
						this.addActionError(getText("war.already"));
				}
			}

			if(StringUtils.isBlank(bean.getWarRate()))
				this.addActionError(getText("warRate.required"));
			else if(StringUtils.isAlpha(bean.getWarRate()))
				this.addActionError(getText("warRate.invalid"));
			else 
			{
				boolean bo = validString(bean.getWarRate(),4);
				if(bo)
					this.addActionError(getText("warRate.invalid"));	
			}
			if(StringUtils.isBlank(bean.getCode()))
				this.addActionError(getText("code.required"));
			else if(!StringUtils.isAlphanumeric(bean.getCode()))
				this.addActionError(getText("code.invalid"));
			if(StringUtils.isBlank(bean.getEff_date()))
				this.addActionError(getText("date.required"));
			else{
				boolean bo=validateDate(bean.getEff_date());
				if(bo)
					this.addActionError(getText("date.invalid"));
			}
			if("-1".equals(bean.getTransID()))
				this.addActionError(getText("rating.mode.transport.invalid"));

			logger.info("val.args===>" + StringUtils.join(val, ", "));
			if("add".equals(bean.getMode()))
			{
				if(cservice.getExist(val,str)!=0)
					this.addActionError(getText("transRSA.already"));		
			}
		}
		catch(NumberFormatException e)
		{
			logger.info("Conversion Error");
		}

	}

	public void validateNewSale() {
		String str = "sale";
		Object[] val = { bean.getCode() };
		Object[] name = { bean.getSaleName(), branchCode };

		logger.info("val.args===>" + StringUtils.join(val, ", "));
		if (StringUtils.isBlank(bean.getSaleName()))
			this.addActionError(getText("saleName.required"));
		else {
			if ("add".equals(bean.getMode())) {
				logger.info("val.args===>" + StringUtils.join(val, ", "));
				if (rservice.getNameExist(name, str) != 0)
					this.addActionError(getText("saleName.already"));
			}
		}
		if (StringUtils.isBlank(bean.getSaleValue()))
			this.addActionError(getText("saleValue.required"));
		else if (StringUtils.isAlpha(bean.getSaleValue()))
			this.addActionError(getText("SaleValue.invalid"));
		if (StringUtils.isBlank(bean.getCode()))
			this.addActionError(getText("code.required"));
		else if(!StringUtils.isAlphanumeric(bean.getCode()))
			this.addActionError(getText("code.invalid"));

		if("add".equals(bean.getMode()))
		{
			if(cservice.getExist(val,str)!=0)

				this.addActionError(getText("transRSA.already"));
		}

	}

	public void validateNewTole() throws NumberFormatException {
		if (StringUtils.isBlank(bean.getToleName()))
			this.addActionError(getText("toleName.required"));
		if (StringUtils.isBlank(bean.getToleValue()))
			this.addActionError(getText("toleValue.required"));
		else if (Double.parseDouble(bean.getToleValue()) < 0)
			this.addActionError(getText("toleValue.invalid"));
		if (StringUtils.isBlank(bean.getCode()))
			this.addActionError(getText("code.required"));
		else if(!StringUtils.isAlphanumeric(bean.getCode()))
			this.addActionError(getText("code.invalid"));
		String str = "tole";
		Object[] val = { branchCode, bean.getCode() };
		logger.info("val.args===>" + StringUtils.join(val, ", "));
		if ("add".equals(bean.getMode())) {
			if (cservice.getExist(val, str) != 0)
				this.addActionError(getText("transRSA.already"));
		}
		Object[] name = { branchCode, bean.getToleName() };
		logger.info("val.args===>" + StringUtils.join(val, ", "));
		if ("add".equals(bean.getMode())) {
			if (rservice.getNameExist(name, str) != 0) {
				this.addActionError(getText("toleName.already"));
			}
		}
	}
	public void validatenewComEx()throws NumberFormatException,NullPointerException,DataIntegrityViolationException
	{
		try
		{
			if(StringUtils.isBlank(bean.getStartAmt()))
				this.addActionError(getText("startAmt.required"));
			else if(!StringUtils.isNumeric(bean.getStartAmt()))
				this.addActionError(getText("startAmt.invalid"));
			else if(Double.parseDouble(bean.getStartAmt())<0)
				this.addActionError(getText("startAmt.invalid"));
			if(StringUtils.isBlank(bean.getEndAmt()))
				this.addActionError(getText("endAmt.required"));
			else if(!StringUtils.isNumeric(bean.getEndAmt()))
				this.addActionError(getText("endAmt.invalid"));
			else if(Double.parseDouble(bean.getEndAmt())<0)
				this.addActionError(getText("endAmt.invalid"));
			else
			{
				if(Double.parseDouble(bean.getEndAmt())<Double.parseDouble(bean.getStartAmt()))
					this.addActionError(getText("amt.invalid"));
				else if(Double.parseDouble(bean.getEndAmt())==Double.parseDouble(bean.getStartAmt()))
					this.addActionError(getText("amt.invalid"));

				if(!"edit".equals(bean.getMode())){
					Object[] val={bean.getStartAmt(),branchCode};
					List list=cservice.isBetweenTwo(val);
					if(list!=null && list.size()>0)
					{
						this.addActionError(getText("amt.isbetween"));
					}
				}

			}	
			if(StringUtils.isBlank(bean.getDeductible()))
				this.addActionError(getText("deductible.required"));
			if(StringUtils.isBlank(bean.getComExRate()))
				this.addActionError(getText("comExRate.required"));
			else if(StringUtils.isAlpha(bean.getComExRate()))
				this.addActionError(getText("comExRate.invalid"));
			else if(Double.parseDouble(bean.getComExRate())<0)
				this.addActionError(getText("comExRate.invalid"));
			/*if(StringUtils.isBlank(bean.getCode()))
			this.addActionError(getText("code.required"));*/
			if(StringUtils.isBlank(bean.getEff_date()))
				this.addActionError(getText("date.required"));
			else{
				boolean bo=validateDate(bean.getEff_date());
				if(bo)
					this.addActionError(getText("date.invalid"));

			}

			String str="comEx";
			Object[] val={branchCode,bean.getComExID()};
			logger.info("val.args===>" + StringUtils.join(val, ","));
			if("add".equals(bean.getMode()))
			{
				if(cservice.getExist(val,str)!=0)
					this.addActionError(getText("transRSA.already"));
			}
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		catch(DataIntegrityViolationException e)
		{
			this.addActionError(getText("amount.invalid"));
		}
	}

	public void validateVessel() {
		String str = "vessel";
		if (StringUtils.isBlank(bean.getVesselName()))
			this.addActionError(getText("vessel.required"));
		else if(!StringUtils.isAlphaSpace(bean.getVesselName()))
			this.addActionError(getText("vessel.invalid"));
		if (StringUtils.isBlank(bean.getVesselType()))
			this.addActionError(getText("vessel.type.required"));
		else if (StringUtils.isNumeric(bean.getVesselType()))
			this.addActionError(getText("vessel.type.invalid"));
		if (StringUtils.isBlank(bean.getVesselsYear()))
			this.addActionError(getText("vessel.year.required"));
		else if (!StringUtils.isNumeric(bean.getVesselsYear()))
			this.addActionError(getText("vessel.year.invalid"));
		if (StringUtils.isBlank(bean.getVesselClass()))
			this.addActionError(getText("vessel.class.required"));
		else if(!StringUtils.isAlpha(bean.getVesselClass()))
			this.addActionError(getText("vessel.class.invalid"));
		if (StringUtils.isBlank(bean.getVesselRate()))
			this.addActionError(getText("vessel.rate.required"));
		else if(!StringUtils.isNumeric(bean.getVesselRate()))
			this.addActionError(getText("vessel.rate.invalid"));
		if (StringUtils.isBlank(bean.getCode()))
			this.addActionError(getText("code.required"));
		else if(!StringUtils.isAlphanumeric(bean.getCode()))
			this.addActionError(getText("code.invalid"));
		else if (!"edit".equals(bean.getMode())) {
			Object[] val = { bean.getCode() };
			if (cservice.getExist(val, str) != 0)
				this.addActionError(getText("transRSA.already"));
		}
	}

	public void validateWarranty() {
		String str = "warranty";
		if (StringUtils.isBlank(bean.getWarrantyDesc()))
			this.addActionError(getText("warranty.required"));
		else if ("add".equals(bean.getMode())) {
			Object[] name = { branchCode, bean.getWarrantyDesc() };
			if (rservice.getNameExist(name, str) != 0) {
				this.addActionError(getText("warranty.already"));
			}
		}
		if (StringUtils.isBlank(bean.getCode()))
			this.addActionError(getText("code.required"));
		else if(!StringUtils.isAlphanumeric(bean.getCode()))
			this.addActionError(getText("code.invalid"));
		if ("add".equals(bean.getMode())) {
			Object[] val = { branchCode, bean.getCode() };
			if (cservice.getExist(val, str) != 0)
				this.addActionError(getText("transRSA.already"));
		}
	}

	public void validateConstant() {
		String str = "constant";
		if ("-1".equals(bean.getCategoryID()))
			this.addActionError(getText("category.select"));
		if (StringUtils.isBlank(bean.getDetailName()))
			this.addActionError(getText("detailName.required"));
		else if ("add".equals(bean.getMode())) {
			Object[] name = { branchCode, bean.getDetailName() };
			if (rservice.getNameExist(name, str) != 0)
				this.addActionError(getText("detailName.already"));
		}
		if (StringUtils.isBlank(bean.getPercentage()))
			this.addActionError(getText("percentage.required"));
		else if (StringUtils.isAlpha(bean.getPercentage()))
			this.addActionError(getText("percentage.invalid"));
		if (StringUtils.isBlank(bean.getCode()))
			this.addActionError(getText("transRSA.required"));
		if(StringUtils.isNotBlank(bean.getCode()) && !StringUtils.isAlphanumeric(bean.getCode()))
			this.addActionError(getText("code.invalid"));
		else if ("add".equals(bean.getMode())) {
			Object[] val = { branchCode, bean.getCode() };
			if (cservice.getExist(val, str) != 0)
				this.addActionError(getText("transRSA.already"));
		}
	}

	public void validatenewExtraCover() throws NumberFormatException {

		if (StringUtils.isBlank(bean.getExtraCoverName()))
			this.addActionError(getText("extraCoverName.required"));
		else {
			if("add".equalsIgnoreCase(bean.getMode())){
				String str = "extra";
				Object[] name = { bean.getTransID(), branchCode,bean.getExtraCoverName() };
				if (rservice.getNameExist(name, str) != 0)
					this.addActionError(getText("extra.already"));
			}
		}
		if ("-1".equals(bean.getTransID())) {
			this.addActionError(getText("select.mode"));
		}
		if (StringUtils.isBlank(bean.getCode()))
			this.addActionError(getText("code.required"));
		else if ("add".equalsIgnoreCase(bean.getMode())) {
			String str = "extraCover";
			Object[] val = { branchCode, bean.getCode() };
			if (cservice.getExist(val, str) != 0)
				this.addActionError(getText("transRSA.already"));
		}
	}
	public void validateCountryDet() throws NumberFormatException {
		try {
			String str = "countryDet";
			if ("-1".equals(bean.getCountryDetID()))
				this.addActionError(getText("country.select"));
			/*else if (!"edit".equals(bean.getMode())) {
				Object[] country = { bean.getCountryID() };
				if (rservice.getNameExist(country, str) != 0)
					this.addActionError(getText("countryDet.already"));
			}*/
			if (!StringUtils.isAlphaSpace(bean.getCountryShortName()))
				this.addActionError(getText("country.shortname.invalid"));
			if (StringUtils.isBlank(bean.getWarRate()))
				this.addActionError(getText("warRate.required"));
			else 
			{
				boolean bo = validString(bean.getConveyRate(),2);
				if(bo)
					this.addActionError(getText("warRate.invalid"));	
			}

			if ("-1".equals(bean.getStartPlace()))
				this.addActionError(getText("sp.select"));
			if ("-1".equals(bean.getEndPlace()))
				this.addActionError(getText("ep.select"));

			if (StringUtils.isBlank(bean.getCode()))
				this.addActionError(getText("transRSA.required"));
			else if (!"edit".equals(bean.getMode())) {
				Object[] val = { bean.getCode() };
				if (cservice.getExist(val, str) != 0)
					this.addActionError(getText("transRSA.already"));
			}
			/*if (StringUtils.isBlank(bean.getEff_date()))
				this.addActionError(getText("date.required"));*/

			if(StringUtils.isBlank(bean.getEff_date()))
				this.addActionError(getText("date.required"));
			else{
				boolean bo=validateDate(bean.getEff_date());
				if(bo)
					this.addActionError(getText("date.invalid"));
			}

		}
		catch(NumberFormatException e)
		{

			e.printStackTrace();
		}
	}
	public void validatenewAgent() throws NumberFormatException {
		if ("-1".equals(bean.getCountryID()))
			this.addActionError(getText("select.countryName"));
		if ("-1".equals(bean.getCityID()))
			this.addActionError(getText("select.cityName"));
		if (!bean.getEmail().equals("")) {
			String status = validation.emailValidate(bean.getEmail());
			if (status.equalsIgnoreCase("invalid"))
				this.addActionError(getText("invalid.email"));
		}
		if (!bean.getMobileNo().equals("")) {
			String status = validation.validInteger(bean.getMobileNo());
			if (status.equalsIgnoreCase("invalid"))
				this.addActionError(getText("invalid.mobileno"));
		}
		if (StringUtils.isBlank(bean.getAgentName()))
			this.addActionError(getText("agentName.required"));
		if (StringUtils.isBlank(bean.getCode()))
			this.addActionError(getText("code.required"));
		else if ("add".equalsIgnoreCase(bean.getMode())) {
			String str = "agent";
			Object[] val = { branchCode, bean.getCode() };
			if (cservice.getExist(val, str) != 0)
				this.addActionError(getText("transRSA.already"));
		}
		if(StringUtils.isEmpty(bean.getCityName()))
			this.addActionError(getText("city.select"));
		else if(!StringUtils.isAlphaSpace(bean.getCityName()))
			this.addActionError(getText("cityName.invalid"));
	}

	public void validateCommodity() throws NumberFormatException {
		try {
			String str = "commodity";
			if(StringUtils.isBlank(bean.getCommoditygroupId()))
				this.addActionError(getText("error.commodity.group"));
			if (StringUtils.isBlank(bean.getCommodityName()))
				this.addActionError(getText("commodity.required"));
			else if ("add".equals(bean.getMode())) {
				Object[] val = { bean.getCommodityName(), branchCode };
				if (rservice.getNameExist(val, str) != 0)
					this.addActionError(getText("commodity.already"));
			}
			if ("-1".equals(bean.getCommodityTypeID()))
				this.addActionError(getText("Please Select RAG"));
			if (StringUtils.isBlank(bean.getCommodityRates()))
				this.addActionError(getText("commodityRate.required"));
			else if (StringUtils.isAlpha(bean.getCommodityRates()))
				this.addActionError(getText("commodityRate.invalid"));
			else if (Double.parseDouble(bean.getCommodityRates()) < 0)
				this.addActionError(getText("commodityRate.invalid"));

			if (StringUtils.isBlank(bean.getCode()))
				this.addActionError(getText("transRSA.required"));
			else if ("add".equals(bean.getMode())) {
				Object[] val = { bean.getCode(), branchCode };
				if (cservice.getExist(val, str) != 0)
					this.addActionError(getText("transRSA.already"));
			}
			if (StringUtils.isBlank(bean.getEff_date()))
				this.addActionError(getText("date.required"));
			else{
				boolean bo=validateDate(bean.getEff_date());
				if(bo)
					this.addActionError(getText("date.invalid"));

			}
		}
		catch(Exception e)
		{

			e.printStackTrace();
		}

	}

	public void validateCurrency() throws NumberFormatException {
		try {
			String str = "currency";
			if (StringUtils.isBlank(bean.getCurrencyType()))
				this.addActionError(getText("currencyType.required"));
			else if (!StringUtils.isAlphaSpace(bean.getCurrencyType()))
				this.addActionError(getText("currencyType.invalid"));
			else if ("add".equals(bean.getMode())) {
				Object[] val = { bean.getCurrencyType() };
				if (rservice.getNameExist(val, str) != 0)
					this.addActionError(getText("currency.already"));
			}

			if(StringUtils.isBlank(bean.getRfactor()))
				this.addActionError(getText("rfactor.required"));
			else if(Double.parseDouble(bean.getRfactor())<0)
				this.addActionError(getText("rfactor.invalid"));
			if(StringUtils.isBlank(bean.getSubCurrency()))
				this.addActionError(getText("subcurrency.required"));
			else if(!StringUtils.isAlphaSpace(bean.getSubCurrency()))
				this.addActionError(getText("subcurrency.invalid"));
			if(StringUtils.isBlank(bean.getCurrencyShortName()))
				this.addActionError(getText("currencyShortName.required"));
			else if(!StringUtils.isAlphaSpace(bean.getCurrencyShortName()))
				this.addActionError(getText("currencyShortName.invalid"));	
			if(StringUtils.isBlank(bean.getEff_date()))
				this.addActionError(getText("date.required"));
			else{
				boolean bo=validateDate(bean.getEff_date());
				if(bo)
					this.addActionError(getText("date.invalid"));
			}
			if(StringUtils.isBlank(bean.getCode()))
				this.addActionError(getText("transRSA.required"));
			else if(!StringUtils.isAlphanumeric(bean.getCode()))
				this.addActionError(getText("code.invalid"));
			else if("add".equals(bean.getMode()))
			{
				Object[] val={bean.getCode()};
				if(cservice.getExist(val, str)!=0)

					this.addActionError(getText("transRSA.already"));
			}
		} catch (NumberFormatException e) {

		}
	}

	public void validateExchange() throws NumberFormatException
	{
		try
		{
			String str="exchange";
			if(StringUtils.isBlank(bean.getExchangeRate()))
				this.addActionError(getText("exchangeRate.required"));
			else if(Double.parseDouble(bean.getExchangeRate())<0)
				this.addActionError(getText("exchangeRate.invalid"));

			if("-1".equals(bean.getCurrencyID()))
				this.addActionError(getText("currency.select"));
			else if("add".equals(bean.getMode()))
			{
				Object[] val={bean.getCurrencyID()};
				if(rservice.getNameExist(val, str)!=0)
					this.addActionError(getText("currency.already"));
			}
			if(StringUtils.isBlank(bean.getEff_date()))
				this.addActionError(getText("date.required"));
			else{
				boolean bo=validateDate(bean.getEff_date());
				if(bo)
					this.addActionError(getText("date.invalid"));
			}
			if(StringUtils.isBlank(bean.getCode()))
				this.addActionError(getText("transRSA.required"));
			else if(!StringUtil.isAlphaNumeric(bean.getCode()))
				this.addActionError(getText("code.invalid"));
			else if("add".equals(bean.getMode()))
			{
				Object[] val={bean.getCode()};
				if(cservice.getExist(val, str)!=0)

					this.addActionError(getText("transRSA.already"));
			}
		} catch (NumberFormatException e) {

		}
	}

	public void validateCover() throws NumberFormatException {
		try {
			String str = "cover";
			if (StringUtils.isBlank(bean.getCoverName()))
				this.addActionError(getText("cover.required"));
			else if ("add".equals(bean.getMode())) {
				Object[] val = { bean.getCoverName(), branchCode };
				if (rservice.getNameExist(val, str) != 0)
					this.addActionError(getText("coverName.already"));
			}

			if (StringUtils.isBlank(bean.getCoverDesc()))
				this.addActionError(getText("coverDesc.required"));
			if (StringUtils.isBlank(bean.getCoverRate()))
				this.addActionError(getText("coverRate.required"));
			else if (Double.parseDouble(bean.getCoverRate()) < 0)
				this.addActionError(getText("coverRate.invalid"));
			if (StringUtils.isBlank(bean.getEff_date()))
				this.addActionError(getText("date.required"));
			/*else{
				boolean bo=validateDate(bean.getEff_date());
				if(bo)
					this.addActionError(getText("date.invalid"));
				}*/
			if (!"add".equals(bean.getMode())) {
				String string = "CHECK_DATE_COVER";
				Object[] date = { bean.getCoverID(), branchCode,
						bean.getCoverID(), branchCode, bean.getEff_date(),bean.getEff_date() };
				if (rservice.getDateExist(date, string) == 0)
					this.addActionError(getText("date.invalid"));
			}
			if (StringUtils.isBlank(bean.getCode()))
				this.addActionError(getText("transRSA.required"));
			else if ("add".equals(bean.getMode())) {
				Object[] val = { bean.getCode(), branchCode };
				if (cservice.getExist(val, str) != 0)
					this.addActionError(getText("transRSA.already"));
			}
		} catch (NumberFormatException e) {

		}
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCoverNames() {
		return rservice.getcoverNames(bean, branchCode);
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getStagenames() {
		return rservice.getStagenames(bean);
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getProductnames() {
		return rservice.getProductnames(branchCode);
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getTypeOfVessels() {
		return rservice.getTypeOfVessel();
	}	
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List getVesselYears()
	{
		boolean bo=validateDate(bean.getEff_date());
		if(bo)
			this.addActionError(getText("date.invalid"));

		List vesselYear= new LinkedList();
		int y=Calendar.getInstance().get(Calendar.YEAR);
		for(int year=(y-28);year<=(y+1);year++)
		{
			vesselYear.add(year+"");
		}
		return vesselYear;  
	}  
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getErrorList() {
		return errorList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getClauseIDList() {
		return clauseIDList;
	}	

	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public String getErrorListAjax() {
		if ("errorLists".equals(bean.getReqFrom()))
			errorList = rservice.getErrorListAjax(bean.getSearchBy(), bean
					.getSearchValue());
		return "adminAjax";
	}

	public void validatenewError() throws NumberFormatException {
		try {
			String str = "error";
			if ("-1".equals(bean.getProductname()))
				addActionError(getText("productname.required"));
			if ("stagename".equals(bean.getStagename()))
				addActionError(getText("stagename.required"));
			if (StringUtils.isBlank(bean.getErrorDesc()))
				addActionError(getText("errorDesc.required"));
			else if (!"edit".equals(bean.getMode())) {
				Object val[] = { bean.getProductname(), bean.getStagename(),
						bean.getErrorDesc() };
				Object aobj[];
				int j = (aobj = val).length;
				for (int i = 0; i < j; i++) {
					Object k = aobj[i];
					logger.info((new StringBuilder("val.args11===>"))
							.append(k).toString());
				}

				if (rservice.getNameExist(val, str) != 0)
					addActionError(getText("errorName.already"));
			}
		} catch (NumberFormatException e) {
			logger.info("Conversion Error");
		}
		if (StringUtils.isBlank(bean.getStatus()))
			addActionError(getText("status.required"));
	}
	public String updateError() {
		validatenewError();
		String remarks = "";
		if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null)
				|| bean.getRemarks().equals("null"))
			remarks = " ";
		else
			remarks=bean.getRemarks();
		if (!hasActionErrors()) {
			if("edit".equals(bean.getMode()))
			{		
				Object val[] = { bean.getErrorDesc(), bean.getProductname(),
						bean.getStagename(), remarks, bean.getStatus(),
						bean.getErrorID() };
				Object aobj[];
				int j = (aobj = val).length;
				for (int i = 0; i < j; i++) {
					Object k = aobj[i];
					logger.info((new StringBuilder("val.args===>")).append(k)
							.toString());
				}
				rservice.getUpdateError(val);
				addActionMessage(getText("update.success"));
			}
			else
			{ 
				/*Object val[] = { bean.getErrorDesc(), bean.getStagename(),
						bean.getProductname(),branchCode,bean.getProductname(),branchCode,remarks, bean.getStatus() };*/
				Object val[] = { bean.getErrorDesc(), bean.getStagename(),bean.getProductname(),remarks, bean.getStatus() };
				Object aobj[];
				int j = (aobj = val).length;
				for (int i = 0; i < j; i++) {
					Object k = aobj[i];				
					logger.info((new StringBuilder("val.args===>")).append(k)
							.toString());

				}
				rservice.getNewError(val);
				addActionMessage(getText("insert.success"));	
			}
			menuType="error";
			return "commonList";
		} else {
			return "editError";
		}
	}

	public String updateClauseID() {
		validatenewClauseID();
		int Extraid=0;
		if (!hasActionErrors()) {
			try {
				String pdfName = "";
				if(StringUtils.isNotBlank(uploadFileName)) {
					String name = FilenameUtils.getBaseName(uploadFileName);
					final Calendar cal = Calendar.getInstance();
					final SimpleDateFormat sdf = new SimpleDateFormat("'on'ddMMMyyyy h.mm.ss a");
					final String date = sdf.format(cal.getTime());
					File destFile = new File(context.getRealPath("\\clauses\\") + "\\" + name + date + ".pdf");
					pdfName = destFile.getName();
					FileUtils.copyFile(upload, destFile);
				}
				else if( "Y".equalsIgnoreCase(bean.getWordingYN()) && StringUtils.isNotBlank(bean.getWordingFileName())) {
					pdfName = bean.getWordingFileName();
				}
				if("edit".equals(bean.getMode()))
				{
					Object val[] = { bean.getClauseDesc(),
							bean.getStatus(), bean.getRsacode(),
							bean.getDisplayorder(),bean.getRemarks(), pdfName, bean.getCode(),
							bean.getClauseID(), branchCode,Extraid,bean.getCovername()};
					logger.info("Args==> " + StringUtils.join(val,", "));
					rservice.getUpdateClauseID(val);
					//clauseIDList = rservice.getClauseIDList(branchCode);
					addActionMessage(getText("update.success"));
				}
				else if("add".equals(bean.getMode()))
				{
					Object val[] = { branchCode, bean.getCovername(), branchCode,
							bean.getClauseDesc(), Extraid, bean.getRsacode(),
							bean.getDisplayorder(),bean.getRemarks(), bean.getStatus(), branchCode, pdfName, bean.getRsacode() };
					logger.info("Args==> " + StringUtils.join(val,", "));
					rservice.getNewClauseID(val);
					//clauseIDList = rservice.getClauseDesc();
					addActionMessage(getText("insert.success"));
				}
			}
			catch(Exception ex) {
				logger.debug("Exception @ { " + ex + " } ");
			}
			menuType="clause";
			return "commonList";
		} else {
			return "updateClauseID";
		}
	}
	public String editWsrcc(){
		validateWsrccCover();
		int count = 0;
		if(!hasActionErrors()){
			logger.info("Add Wsrcc");
			String pdfName = "";
			try {
				if(StringUtils.isNotBlank(uploadFileName)) {
					String name = FilenameUtils.getBaseName(uploadFileName);
					final Calendar cal = Calendar.getInstance();
					final SimpleDateFormat sdf = new SimpleDateFormat("'on'ddMMMyyyy h.mm.ss a");
					final String date = sdf.format(cal.getTime());
					File destFile = new File(context.getRealPath("\\clauses\\") + "\\" + name + date + ".pdf");
					pdfName = destFile.getName();
					FileUtils.copyFile(upload, destFile);
				}
				else if( "Y".equalsIgnoreCase(bean.getWordingYN()) && StringUtils.isNotBlank(bean.getWordingFileName())) {
					pdfName = bean.getWordingFileName();
				}
			} 
			catch(Exception ex) {
				logger.debug("Exception @ editWsrcc { " + ex + " } ");
			}
			count =rservice.wsrccModify(bean,branchCode,bean.getMode(),pdfName);
			if(count==0) {
				addActionError("Error in Update");
				return "editWsrcc";
			}
			if("edit".equals(bean.getMode()))
				addActionMessage(getText("update.success"));
			else
				addActionMessage(getText("insert.success"));	
			menuType="wsrcc";
			return "commonList";
		} 
		return "editWsrcc";

	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public String getClauseIDListAjax() {
		if ("clauseIDLists".equals(bean.getReqFrom()))
			clauseIDList = rservice.getClauseIDListAjax(bean.getSearchBy(),
					bean.getSearchValue());
		return "adminAjax";
	}

	public String conveyance()
	{
		bean.getCategoryID();
		menuType="conveyance";
		return "commonList";
	}
	public String countrymaster()
	{
		menuType="countrymaster";
		return "commonList";
	}
	public String country()
	{
		menuType="country";
		return "commonList";
	}
	public String bankmaster()
	{
		menuType="bankmaster";
		return "commonList";
	}
	public String materialmaster()
	{
		menuType="materialmaster";
		return "commonList";
	}
	public String warratemaster()
	{
		menuType="warratemaster";
		return "commonList";
	}
	public String commoditymaster()
	{
		menuType="commoditymaster";
		return "commonList";
	}
	public String covercommomaster()
	{
		menuType="covercommomaster";
		return "commonList";
	}
	public String categorymaster()
	{
		menuType="categorymaster";
		return "commonList";
	}
	public String saletermmaster()
	{
		menuType="saletermmaster";
		return "commonList";
	}
	public String tolerancemaster()
	{
		menuType="tolerancemaster";
		return "commonList";
	}
	public String commodityexcess()
	{
		menuType="commodityexcess";
		return "commonList";
	}
	public String vesselmaster()
	{
		menuType="vesselmaster";
		return "commonList";
	}
	public String settlingagent()
	{
		menuType="settlingagent";
		return "commonList";
	}
	public String exchangemaster()
	{
		menuType="exchangemaster";
		return "commonList";
	}
	public String currencymaster()
	{
		menuType="currencymaster";
		return "commonList";
	}
	public String extracover()
	{
		menuType="extracover";
		return "commonList";
	}
	public String modeoftransport()
	{
		menuType="modeoftransport";
		return "commonList";
	}
	public String warrantymaster()
	{
		menuType="warrantymaster";
		return "commonList";
	}
	public String constantMaster(){
		menuType="constantMaster";
		return "commonList";
	}
	public String constantdetail()
	{
		menuType="constantdetail";
		return "commonList";
	}
	public String exclusionmaster()
	{
		menuType="exclusionmaster";
		return "commonList";
	}
	public String citymaster()
	{
		menuType="citymaster";
		return "commonList";
	}
	public String wsrcc()
	{
		menuType="wsrcc";
		return "commonList";

	}
	public String covermaster()
	{
		menuType="covermaster";
		return "commonList";
	}
	public String clause()
	{
		menuType="clause";
		return "commonList";
	}
	public String error()
	{
		menuType="error";
		return "commonList";

	}
	public String engine()
	{
		menuType="modeoftransport";
		return "commonList";	
	}
	public String editconveyance()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSeletedConveyance(bean);
		}
		return "updateConveyance";
	}
	public String editcountrymaster()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedCountry(bean);
		}
		return "editCountry";
	}
	public String editcountry()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedCountryDet(bean);
		}
		return "editCountryDet";
	}
	public String editbankmaster()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectBank(bean,branchCode);
		}
		return "editBank";
	}
	public String editmaterialmaster()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedMaterial(bean);
			//return "editMaterial";
		}

		//menuType="materialmaster";
		//return "commonList";        	
		return "editMaterial";
	}
	public String editwarratemaster()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedWar(bean);
		}
		return "editWar";
	}
	public String editcategorymaster()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedCat(bean);
		}
		return "editCategory";
	}
	public String editcovercommomaster()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedCovercomm(bean);
		}
		else {
			coverages = new ArrayList();
		}
		return "editCoverComm";
	}
	/*public String editcommoditymaster()
	{
		if("edit".equals(bean.getMode()))
		{
			Object[] val = { bean.getCommodityID(), branchCode };
			rservice.getSelectedCommdity(bean, val);
		}
		return "editCommodity";
	}*/
	public String editsaletermmaster()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedSale(bean);
		}
		return "editSale";
	}
	public String edittolerancemaster()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedTole(bean);
		}
		return "editTole";
	}
	public String editcommodityexcess()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getselectedComEx(bean);
		}
		return "editComEx";
	}
	public String editvesselmaster()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedVessel(bean);
		}
		return "editVessel";
	}
	public String editsettlingagent()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedAgent(bean);

			countryList = rservice.getCountryList();
			cityList = rservice.getCityListAjax1(bean.getCountryID());

		}
		else
		{
			countryList = rservice.getCountryList();
		}
		return "editAgent";
	}
	public String editexchangemaster()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedExchange(bean,branchCode);
		}
		return "editExchange";
	}
	public String editcurrencymaster()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedCurrency(bean,branchCode);
		}
		return "editCurrency";
	}
	public String editextracover()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedExtraCover(bean);
		}
		return "editExtraCover";
	}
	public String editmodeoftransport()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSeletedTransport(bean);
		}
		return "editTransport";
	}
	public String editwarrantymaster()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedWarranty(bean);
		}
		return "editWarranty";
	}
	public String editconstantMaster() {
		if("edit".equals(bean.getMode())) {
			rservice.getSelectedConstantMaster(bean);
		}
		return "editConstantMaster";
	}
	public String editconstantdetail()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedConstant(bean);
		}
		return "editConstant";
	}
	public String editexclusionmaster()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedExclusion(bean);
		}
		return "editExclusion";
	}
	public String editcitymaster()
	{
		if("edit".equals(bean.getMode()))
		{
			rservice.getSelectedCity(bean);
		}
		countryList = rservice.getCountryList();
		return "editCity";
	}
	public String editwsrcc() {
		if("edit".equals(bean.getMode())) 
			rservice.wsrccEdit(bean,branchCode);

		return "editWsrcc";
	}
	public String editcovermaster(){
		if("edit".equals(bean.getMode()))
			rservice.getSelectedCover(bean);
		return "editCover";
	}
	public String editclause(){
		try {
			if("edit".equals(bean.getMode()))
				rservice.getSeletedClauseID(bean);
		}
		catch(Exception ex) {
			logger.debug("Exception @ editclause() { " + ex + " } ");
		}
		return "editClauseID";
	}
	public String editerror(){
		if("edit".equals(bean.getMode()))
			rservice.getSeletedError(bean);
		return "editError";
	}

	public void validatenewConveyance() {
		try{
			if("".equals(bean.getTransID()))
				this.addActionError(getText("select.mode"));
			if(StringUtils.isBlank(bean.getConveyName()))
				this.addActionError(getText("conveyName.required"));
			else if(!StringUtils.isAlphaSpace(bean.getConveyName()))
				this.addActionError(getText("conveyName.invalid"));
			if(StringUtils.isBlank(bean.getConveyRate()))
				this.addActionError(getText("conveyRate.required"));
			else if (StringUtils.isAlpha(bean.getConveyRate()))
				this.addActionError(getText("conveyRate.invalid"));
			else if(validString(bean.getConveyRate(),6))
				this.addActionError(getText("conveyRate.invalid"));
			else {
				boolean bo = validString(bean.getConveyRate(),2);
				if(bo)
					this.addActionError(getText("conveyRate.invalid"));	
			}
			if(StringUtils.isBlank(bean.getCode()))
				this.addActionError(getText("code.required"));
			else if(!StringUtils.isAlphanumeric(bean.getCode()))
				this.addActionError(getText("code.invalid"));
			if(StringUtils.isBlank(bean.getEff_date()))
				this.addActionError(getText("date.required"));
			else{
				boolean bo=validateDate(bean.getEff_date());
				if(bo)
					this.addActionError(getText("date.invalid"));
			}

			String str="convey";
			Object[] val={bean.getCode(),branchCode,bean.getTransID()};
			Object[] name={bean.getConveyName(),branchCode};
			logger.info("val.args===>" + StringUtils.join(val, ", "));
			if(!"edit".equals(bean.getMode())){
				if(cservice.getExist(val,str)!=0)
					this.addActionError(getText("transRSA.already"));
				if(rservice.getNameExist(name,str)!=0)
					this.addActionError(getText("conveyName.already"));
			}
		}catch(Exception e){
			logger.info("Conversion Error");
		}
	}
	private void validateCategory(){
		if(StringUtils.isBlank(bean.getCatname()))
			addActionError(getText("catname.required"));
		else if(bean.getCatname().trim().length()<3)
			this.addActionError(getText("catname.valid"));
		String str="categ";
		Object[] val={bean.getCatname(),branchCode};
		if(!"edit".equals(bean.getMode())){
			if(cservice.getExist(val,str)!=0)
				this.addActionError(getText("categ.already"));			
		}
		if(StringUtils.isBlank(bean.getCatrate()))
			addActionError(getText("catrate.required"));
		else if(!StringUtils.isNumeric(bean.getCatrate()))
			addActionError(getText("catrate.valid"));
		else if(validString(bean.getCatrate(),6))
			this.addActionError(getText("catrate.valid"));
		if(StringUtils.isBlank(bean.getEff_date()))
			this.addActionError(getText("date.required"));    	
		else if(validateDate(bean.getEff_date()))
			this.addActionError(getText("date.invalid"));    	
		if(StringUtils.isBlank(bean.getExp_date()))
			this.addActionError(getText("expirydate.required"));    	
		else if(checkDate(bean.getEff_date(),bean.getExp_date()))
			this.addActionError(getText("expirydate.invalid"));
		if(StringUtils.isBlank(bean.getStatus()))
			addActionError(getText("status.required"));    	
	}
	private void validatecovercomm(){
		if("-1".equals(bean.gettransID()))
			addActionError(getText("select.mode"));
		else {
			coverages = rservice.getCoverages(bean.gettransID(), branchCode);
		}
		if("-1".equals(bean.getCoverageID()))
			addActionError(getText("covernames.required"));
		if("-1".equals(bean.getCommodityID()))
			addActionError(getText("catcomm.required"));
		String str="covcomm";
		Object[] val={bean.gettransID(),bean.getCoverageID(),bean.getCommodityID(),branchCode};
		if(!"edit".equals(bean.getMode())){
			if(cservice.getExist(val,str)!=0)
				this.addActionError(getText("rec.already"));
			else{
				if(StringUtils.isBlank(bean.getCatrate()))
					addActionError(getText("catrate.required"));
				else if(!StringUtils.isNumeric(bean.getCatrate()))
					addActionError(getText("catrate.valid"));
				else if(validString(bean.getCatrate(),6))
					this.addActionError(getText("catrate.valid"));
				if(StringUtils.isBlank(bean.getCoverRate()))
					addActionError(getText("coverrate.required"));
				else if(!StringUtils.isNumeric(bean.getCoverRate()))
					addActionError(getText("coverrate.valid"));
				if(StringUtils.isBlank(bean.getEff_date()))
					this.addActionError(getText("date.required"));    	
				else if(validateDate(bean.getEff_date()))
					this.addActionError(getText("date.invalid"));  
				if(StringUtils.isBlank(bean.getReferralStatus()))
					this.addActionError(getText("refstatus.required")); 
				if(StringUtils.isBlank(bean.getStatus()))
					addActionError(getText("status.required"));
			}
		}
		else{
			if(StringUtils.isBlank(bean.getCatrate()))
				addActionError(getText("catrate.required"));
			else if(validString(bean.getCatrate(),6))
				this.addActionError(getText("catrate.valid"));
			if(StringUtils.isBlank(bean.getCoverRate()))
				addActionError(getText("coverrate.required"));
			else if(!StringUtils.isNumeric(bean.getCoverRate()))
				addActionError(getText("coverrate.valid"));
			if(StringUtils.isBlank(bean.getEff_date()))
				this.addActionError(getText("date.required"));    	
			else if(validateDate(bean.getEff_date()))
				this.addActionError(getText("date.invalid"));  
			if(StringUtils.isBlank(bean.getReferralStatus()))
				this.addActionError(getText("refstatus.required")); 
			if(StringUtils.isBlank(bean.getStatus()))
				addActionError(getText("status.required"));
		}    	
	}
	private void validatenewClauseID(){
		String str="clauseID";
		String str1="display";
		if("transID".equals(bean.getTransID()))
			this.addActionError(getText("select.mode"));
		if("-1".equals(bean.getCovername()))
			addActionError(getText("covername.required"));
		if(StringUtils.isBlank(bean.getClauseDesc()))
			addActionError(getText("clauseDesc.required"));
		if(StringUtils.isBlank(bean.getRsacode()))
			addActionError(getText("rsacode.required"));
		else {
			boolean bo = validString(bean.getRsacode(),5);
			if(bo)
				this.addActionError(getText("rsacode.invalid"));	
		}
		if(StringUtils.isBlank(bean.getDisplayorder()))
			addActionError(getText("displayorder.required"));
		else if(!StringUtils.isNumeric(bean.getDisplayorder()))
			addActionError(getText("displayorder.invalid"));
		if(!"edit".equals(bean.getMode())){
			Object[] val={branchCode,bean.getDisplayorder()};
			if(cservice.getExist(val, str1)!=0){
				this.addActionError(getText("displayorder.already"));	 
			}
		}
		if(StringUtils.isBlank(bean.getStatus()))
			addActionError(getText("status.required"));
		if(!"edit".equals(bean.getMode())){
			Object[] val={bean.getRsacode(),branchCode};
			if(cservice.getExist(val, str)!=0)

				this.addActionError(getText("transRSA.already"));
		}
		if(StringUtils.isNotBlank(uploadFileName) && !"application/pdf".equalsIgnoreCase(uploadContentType)) {
			this.addActionError(getText("error.clauses.policyWording.invalid"));
		}
		
		if(!bean.getWordingYN().equalsIgnoreCase("Y")) {
			if(upload==null) {
				this.addActionError("Please Select Policy Wording");
			}
		}
	}
	
	

	private void validateWsrccCover() {
		String str="clauseID";
		String str1="display";
		if("transID".equals(bean.getTransID()))
			this.addActionError(getText("select.mode"));
		if(bean.getCovername().equals("-1"))
			addActionError(getText("covername.required"));
		if(StringUtils.isBlank(bean.getWsrccdesc()))
			addActionError(getText("clauseDesc.required"));
		if(StringUtils.isBlank(bean.getRsacode()))
			addActionError(getText("rsacode.required"));
		else {
			boolean bo = validString(bean.getRsacode(),5);
			if(bo)
				this.addActionError(getText("rsacode.invalid"));	
		}
		if(StringUtils.isBlank(bean.getDisplayorder()))
			addActionError(getText("displayorder.required"));
		else if(StringUtils.isAlpha(bean.getDisplayorder()))
			addActionError(getText("displayorder.invalid"));
		if(!"edit".equals(bean.getMode())){
			Object[] val={branchCode,bean.getDisplayorder()};
			if(cservice.getExist(val, str1)!=0){
				this.addActionError(getText("displayorder.already"));	 
			}
		}
		if(StringUtils.isBlank(bean.getStatus()))
			addActionError(getText("status.required"));
		if(!"edit".equals(bean.getMode())){
			Object[] val={bean.getRsacode(),branchCode};
			if(cservice.getExist(val, str)!=0)
				this.addActionError(getText("transRSA.already"));
		}
		if(StringUtils.isNotBlank(uploadFileName) && !"application/pdf".equalsIgnoreCase(uploadContentType)) {
			this.addActionError(getText("error.clauses.policyWording.invalid"));
		}
		if(!bean.getWordingYN().equalsIgnoreCase("Y")) {
			if(upload==null) {
				this.addActionError("Please Select Policy Wording");
			}
		}
		
	}
	public boolean validateDate(String val){		
		boolean bo=false;
		try{
			logger.info("Enter Into ValidateDate()");
			String sysdate;
			Format formatter;
			Date date=new Date(); 
			formatter = new SimpleDateFormat("MM/dd/yyyy");
			sysdate = formatter.format(date);
			String format = "MM/dd/yyyy"; 
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			java.util.Date date1 = sdf.parse(sysdate);
			java.util.Date date2 = sdf.parse(val); 
			logger.info(date2.compareTo(date1)+"");
			if(date2.compareTo(date1) < 0)
				bo=true;
			logger.info(String.valueOf(bo));

		}catch(Exception e){
			logger.info(e);
		}
		logger.info("Exit from ValidateDate()");
		return bo;
	}
	public boolean validatePrevDate(String val){		
		boolean bo=false;
		try{
			logger.info("Enter Into ValidateDate()");
			String sysdate;
			Format formatter;
			Date date=new Date(); 
			formatter = new SimpleDateFormat("MM/dd/yyyy");
			sysdate = formatter.format(date);
			String format = "MM/dd/yyyy"; 
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			java.util.Date date1 = sdf.parse(sysdate);
			java.util.Date date2 = sdf.parse(val); 
			if(date2.compareTo(date1) <= 0)
				bo=true;
			logger.info(String.valueOf(bo));

		}catch(Exception e){
			logger.info(e);
		}
		logger.info("Exit from ValidateDate()");
		return bo;
	}
	public boolean validString(String value,int format){
		boolean bo=false;
		int count=0,c=0;
		try{
			value=value.trim();
			String validChar,validno,validextra=null,validCode,validDot;
			String string=new String("");

			validChar="abcdefghijklmnopqrstuvwxyz ";
			validno="1234567890.-";
			validextra="1234567890.";
			validDot=".1234567890";
			validCode="abcdefghijklmnopqrstuvwxyz1234567890";
			value=value.toLowerCase();
			if(format==1)
				string=new String(validChar);
			else if(format==2)
				string=new String(validno);
			else if(format==3)
				string=validChar+validno;
			else if(format==4)
				string=new String(validextra);
			else if(format==5)
				string=new String(validCode);
			else if(format==6)
				string=new String(validDot);				
			for(int i=0;i<value.length();i++){
				if(string.indexOf(value.charAt(i))== -1)
					bo=true;
				if(value.charAt(i)=='.')
					count++;	
				if(value.charAt(i)=='-')
					c++;
			}
			if(count>=2 || c>=2)
				bo=true;
		}catch(Exception e){
			return bo;
		}
		return bo;
	}
	public boolean checkDate(String val1,String val2)  {		
		boolean bo=false;
		try{
			logger.info("Enter Into ValidateDate()");
			String sysdate;
			Format formatter;
			Date date=new Date(); 
			formatter = new SimpleDateFormat("MM/dd/yyyy");
			sysdate = formatter.format(date);
			String format = "MM/dd/yyyy"; 
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			java.util.Date date1 = sdf.parse(val1);
			java.util.Date date2 = sdf.parse(val2);
			logger.info(date2.compareTo(date1)+"");
			if(date2.compareTo(date1) <= 0)
			{
				bo=true;
			}
			logger.info(String.valueOf(bo));

		}	catch(Exception e){
			logger.info(e);
		}
		logger.info("Exit from ValidateDate()");
		return bo;
	}

	public String upload() {
		logger.info("upload() - Enter");
		String returnResult="ratingFileUpload";
		bean.setDisplay("polDoc");
		if("exchangeupload".equals(menuType))
			bean.setTitle("Exchange Master Upload");
		if(StringUtils.isNotBlank(bean.getReqFrom())){
			try  {
				Map <Object,Object> resultMap=null;
				String error=null;
				validateFileds();
				if(getActionErrors().size()<=0) {
					String typeId=getText(bean.getReqFrom());
					tranId=service.getTranactionId((String)session.get("user"),uploadFileName,typeId, FILE_PATH, bean.getEff_date(), branchCode);
					uploadFileName=StringHelper.getFileNameFormat(uploadFileName, tranId).replaceAll(" ", "");

					File fileToCreate = new File((ServletActionContext.getServletContext().getRealPath(getText("EXCEL_UPLOAD_PATH"))),this.uploadFileName);
					FileUtils.copyFile(this.upload, fileToCreate);
					logger.info("UPLOAD EXCEL FILE PATH===>"+(ServletActionContext.getServletContext().getRealPath(getText("EXCEL_UPLOAD_PATH")))+"/"+uploadFileName);
					String csvfilename="";
					if(uploadFileName.contains(".xlsx")){
						csvfilename=uploadFileName.replaceAll(".xlsx", ".csv");
						RatingEngineAction.excelXToCSV(ServletActionContext.getServletContext().getRealPath(getText("EXCEL_UPLOAD_PATH"))+"/"+uploadFileName,(this.request.getSession().getServletContext().getRealPath(getText("CSV_UPLOAD_PATH")))+"/"+csvfilename);
					}else if(uploadFileName.contains(".xls")){
						csvfilename=uploadFileName.replaceAll(".xls", ".csv");
						RatingEngineAction.excelToCSV(ServletActionContext.getServletContext().getRealPath(getText("EXCEL_UPLOAD_PATH"))+"/"+uploadFileName,(this.request.getSession().getServletContext().getRealPath(getText("CSV_UPLOAD_PATH")))+"/"+csvfilename);
					}
					logger.info("UPLOAD CSV PATH NAME===>"+(ServletActionContext.getServletContext().getRealPath(getText("CSV_UPLOAD_PATH")))+"/"+csvfilename);
					File csvFile=new File(ServletActionContext.getServletContext().getRealPath(getText("CSV_UPLOAD_PATH"))+"/"+csvfilename);
					if(csvFile.exists() && csvFile.canRead()){
						resultMap=service.insertRecords(typeId, csvFile, tranId);
						error=(String)resultMap.get("ERROR");
					}else
						error=getText("error.upload.conversion");
					if(StringUtils.isNotEmpty(error))
						addActionError(error);
					else{
						request.setAttribute("UPLOAD_RESULT", resultMap.get("UPLOAD_RESULT"));
						bean.setDisplay("result");
					}
				}
			}catch (Exception e){
				addActionError(e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("upload() - Exit ");
		return returnResult;
	}

	public void validateFileds(){
		if("exchangeupload".equals(bean.getReqFrom())){
			if(StringUtils.isBlank(bean.getEff_date()))
				addActionError("Please Enter Effective Date");
			else if(rservice.checkEffDate(bean.getEff_date())<=0)
				addActionError("Effective Date should be greater than or equals to sysdate");
			/*if(rservice.checkExchangeEffDate(bean.getEff_date(), (String)session.get("user"))>0)
				addActionError("Some other exchange values available for this Effective Date. Please check with Exchange Rate Report");*/
		}
		if(upload==null)
			addActionError("Please select Upload File");
		else if(!uploadFileName.contains(".xls")) {
			this.addActionError("Please select the valid file format");
		}
	}


	public String download(){
		logger.info("download() - Enter");
		try {
			if("exchangeupload".equals(bean.getReqFrom())){
				inputStream=new FileInputStream(new File(EXCEL_EXCHANGE_MASTER_PATH));
				uploadFileName="ExchangeMasterUploadFormat.xls";
			}
		} catch (Exception e) {
			addActionError(e.getMessage());
			e.printStackTrace();
		}
		logger.info("download() - Exit");
		return "download";
	}

	public String transDetails(){
		bean.setDisplay("TransHome");
		if("exchangeupload".equals(menuType))
			bean.setTitle("Exchange Master Transaction Details");
		try{
			if(StringUtils.isNotBlank(bean.getReqFrom())){
				validateTrans();
				if (getActionErrors().size() <= 0) {
					transDetList=service.getTransDetList(bean.getStartDate(), bean.getEndDate(),getText(bean.getReqFrom()));
					bean.setDisplay("result");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "transDetail";
	}

	public void validateTrans() {
		if(!Validation.isValidDate(bean.getStartDate())){
			addActionError("Please select valid Start Date");
		}if(!Validation.isValidDate(bean.getEndDate())){
			addActionError("Please select valid End Date");
		}else if(!Validation.checkDateDiff(bean.getStartDate(), bean.getEndDate())){
			addActionError("End Date should be greater that Start Date");
		}
	}

	public String downloadTrans(){
		logger.info("downloadTrans() - Enter || menuType: "+menuType+ " tranId: "+tranId);
		try {
			String typeId=getText(bean.getReqFrom());
			Map<Object,Object> downloadInfo=service.getDownloadInfo(typeId, tranId, downloadType);
			List<Map<String, Object>> recordsList = service.getDownloadData(tranId, downloadInfo, downloadType);
			String[] headers=((String)downloadInfo.get("EXCEL_HEADERS")).split(",");
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			ExcelDownload.writeExcel(headers, recordsList, bos);
			inputStream=new ByteArrayInputStream(bos.toByteArray());
			uploadFileName=bean.getReqFrom()+".xls";
			//File fileToCreate=new File(getText("EXCEL_DOWNLOAD_PATH"), ());
			//FileUtils.writeByteArrayToFile(fileToCreate, bos.toByteArray());
		} catch (Exception e) {
			addActionError(e.getMessage());
			e.printStackTrace();
		}
		logger.info("downloadTrans() - Exit");
		return "download";
	}

	public static void excelToCSV(String excelFileName,String csvFileName) throws Exception{
		checkValidFile(excelFileName);
		HSSFWorkbook myWorkBook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(excelFileName)));
		HSSFSheet mySheet = myWorkBook.getSheetAt(0);
		Iterator  rowIter =  mySheet.rowIterator();
		String csvData="";
		int count=0;
		int firstCount=0;
		while (rowIter.hasNext()) {
			HSSFRow myRow = (HSSFRow) rowIter.next();
			count++;
			if(count==1){
				firstCount=myRow.getLastCellNum();
			}for ( int i=0;i<firstCount;i++){
				csvData += getCellData(myRow.getCell(i));
			}
			csvData=csvData.substring(0, csvData.length()-1);
			csvData+=NEW_LINE_CHARACTER;
			//System.out.println(csvData);
		}
		writeCSV(csvFileName, csvData);
	}

	private static void writeCSV(String csvFileName,String csvData) throws Exception{
		FileOutputStream writer = new FileOutputStream(csvFileName);
		writer.write(csvData.getBytes());
		writer.close();
	}
	private static String getCellData( HSSFCell myCell) throws Exception{
		String cellData="";
		if ( myCell== null){
			cellData += CVS_SEPERATOR_CHAR;
		}else{
			switch(myCell.getCellType() ){
			case  HSSFCell.CELL_TYPE_STRING  :
			case  HSSFCell.CELL_TYPE_BOOLEAN  :
				cellData +=  myCell.getRichStringCellValue ()+CVS_SEPERATOR_CHAR;
				break;
			case HSSFCell.CELL_TYPE_NUMERIC :
				cellData += getNumericValue(myCell);
				break;
			case  HSSFCell.CELL_TYPE_FORMULA :
				cellData +=  getFormulaValue(myCell);
			default:
				cellData += CVS_SEPERATOR_CHAR;;
			}
		}
		return cellData;
	}

	private static String getFormulaValue(HSSFCell myCell) throws Exception{
		String cellData="";
		if ( myCell.getCachedFormulaResultType() == HSSFCell.CELL_TYPE_STRING  || myCell.getCellType () ==HSSFCell.CELL_TYPE_BOOLEAN) {
			cellData +=  myCell.getRichStringCellValue ()+CVS_SEPERATOR_CHAR;
		}else  if ( myCell.getCachedFormulaResultType() == HSSFCell.CELL_TYPE_NUMERIC ) {
			cellData += getNumericValue(myCell)+CVS_SEPERATOR_CHAR;
		}
		return cellData;
	}
	private static String getNumericValue(HSSFCell myCell) throws Exception {
		String cellData="";
		if ( HSSFDateUtil.isCellDateFormatted(myCell) ){
			cellData += new SimpleDateFormat(OUTPUT_DATE_FORMAT).format(myCell.getDateCellValue()) +CVS_SEPERATOR_CHAR;
		}else{
			cellData += new BigDecimal(myCell.getNumericCellValue()).toString()+CVS_SEPERATOR_CHAR ;
		}
		return cellData;
	}
	public static void excelXToCSV(String excelFileName,String csvFileName) throws Exception{
		checkValidFile(excelFileName);
		XSSFWorkbook myWorkBook = new XSSFWorkbook(new FileInputStream(excelFileName));
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		String csvData="";

		int rows = mySheet.getPhysicalNumberOfRows();
		for (int eachRow = 0;eachRow<rows;eachRow++) {
			XSSFRow myRow = (XSSFRow) mySheet.getRow(eachRow);
			for ( int i=0;i<myRow.getLastCellNum();i++){
				csvData += getXLSXCellData(myRow.getCell(i));
			}
			csvData+=NEW_LINE_CHARACTER;
		}
		writeCSV(csvFileName, csvData);
	}

	private static String getXLSXCellData( XSSFCell myCell) throws Exception{
		String cellData="";
		if ( myCell== null){
			cellData += CVS_SEPERATOR_CHAR;;
		}else{
			switch(myCell.getCellType() ){
			case  XSSFCell.CELL_TYPE_STRING  :
			case  XSSFCell.CELL_TYPE_BOOLEAN  :
				DataFormatter formatter = new DataFormatter();

				//System.out.println( " formatter.formatCellValue(myCell).toString()="+ formatter.formatCellValue(myCell).toString() );
				cellData +=  myCell.getStringCellValue()+CVS_SEPERATOR_CHAR;
				break;
			case XSSFCell.CELL_TYPE_NUMERIC :
				cellData += getXLSXNumericValue(myCell);
				break;
			case  XSSFCell.CELL_TYPE_FORMULA :
				cellData +=  getXLSXFormulaValue(myCell);
			default:
				cellData += CVS_SEPERATOR_CHAR;;
			}
		}
		return cellData;
	}private static String getXLSXNumericValue(XSSFCell myCell) throws Exception {
		String cellData="";
		if ( HSSFDateUtil.isCellDateFormatted(myCell) ){
			cellData += new SimpleDateFormat(OUTPUT_DATE_FORMAT).format(myCell.getDateCellValue()) +CVS_SEPERATOR_CHAR;
		}else{
			cellData += new BigDecimal(myCell.getNumericCellValue()).toString()+CVS_SEPERATOR_CHAR ;
		}
		return cellData;
	}
	private static String getXLSXFormulaValue(XSSFCell myCell) throws Exception{
		String cellData="";
		if ( myCell.getCachedFormulaResultType() == XSSFCell.CELL_TYPE_STRING  || myCell.getCellType () ==XSSFCell.CELL_TYPE_BOOLEAN) {
			cellData +=  myCell.getRichStringCellValue ()+CVS_SEPERATOR_CHAR;
		}else  if ( myCell.getCachedFormulaResultType() == XSSFCell.CELL_TYPE_NUMERIC ) {
			cellData += getXLSXNumericValue(myCell)+CVS_SEPERATOR_CHAR;
		}
		return cellData;
	}
	private static void checkValidFile(String fileName){
		boolean valid=true;
		try{
			File f = new File(fileName);
			if ( !f.exists() || f.isDirectory() ){
				valid=false;
			}
		}catch(Exception e){
			valid=false;
		}
		if ( !valid){
			System.out.println("File doesn't exist: " + fileName);
			System.exit(0);
		}
	}
	public String updateCommodity() {
		validateCommodity();
		if (!hasActionErrors()) {
			String txtchkICC_A_SEAClause_1 = "";
			String txtchkICC_A_SEAExclusive_1 = "";
			String txtchkICC_A_SEAWarranty_1 = "";
			String txtchkICC_A_SEAWarCover_1 = "";

			String txtchkICC_A_SEAClause_2 = "";
			String txtchkICC_A_SEAExclusive_2 = "";
			String txtchkICC_A_SEAWarranty_2 = "";
			String txtchkICC_A_SEAWarCover_2 = "";

			String txtchkICC_A_SEAClause_3 = "";
			String txtchkICC_A_SEAExclusive_3 = "";
			String txtchkICC_A_SEAWarranty_3 = "";
			String txtchkICC_A_SEAWarCover_3 = "";

			String txtchkICC_A_SEAClause_4 = "";
			String txtchkICC_A_SEAExclusive_4 = "";
			String txtchkICC_A_SEAWarranty_4 = "";
			String txtchkICC_A_SEAWarCover_4 = "";

			String txtchkICC_A_SEAClause_5 = "";
			String txtchkICC_A_SEAExclusive_5 = "";
			String txtchkICC_A_SEAWarranty_5 = "";
			String txtchkICC_A_SEAWarCover_5 = "";

			String txtchkICC_A_SEAClause_6 = "";
			String txtchkICC_A_SEAExclusive_6 = "";
			String txtchkICC_A_SEAWarranty_6 = "";
			String txtchkICC_A_SEAWarCover_6 = "";

			String txtchkICC_A_SEAClause_7 = "";
			String txtchkICC_A_SEAExclusive_7 = "";
			String txtchkICC_A_SEAWarranty_7 = "";
			String txtchkICC_A_SEAWarCover_7 = "";

			String txtchkICC_A_SEAClause_8 = "";
			String txtchkICC_A_SEAExclusive_8 = "";
			String txtchkICC_A_SEAWarranty_8 = "";
			String txtchkICC_A_SEAWarCover_8 = "";

			String txtchkICC_A_SEAClause_9 = "";
			String txtchkICC_A_SEAExclusive_9 = "";
			String txtchkICC_A_SEAWarranty_9 = "";
			String txtchkICC_A_SEAWarCover_9 = "";

			String txtchkICC_A_SEAClause_10 = "";
			String txtchkICC_A_SEAExclusive_10 = "";
			String txtchkICC_A_SEAWarranty_10 = "";
			String txtchkICC_A_SEAWarCover_10 = "";

			String txtchkICC_A_SEAClause_11 = "";
			String txtchkICC_A_SEAExclusive_11 = "";
			String txtchkICC_A_SEAWarranty_11 = "";
			String txtchkICC_A_SEAWarCover_11 = "";

			String txtchkICC_A_SEAClause_12 = "";
			String txtchkICC_A_SEAExclusive_12 = "";
			String txtchkICC_A_SEAWarranty_12 = "";
			String txtchkICC_A_SEAWarCover_12 = "";

			String txtchkICC_A_SEAClause_13 = "";
			String txtchkICC_A_SEAExclusive_13 = "";
			String txtchkICC_A_SEAWarranty_13 = "";
			String txtchkICC_A_SEAWarCover_13 = "";

			String txtchkICC_A_SEAClause_14 = "";
			String txtchkICC_A_SEAExclusive_14 = "";
			String txtchkICC_A_SEAWarranty_14 = "";
			String txtchkICC_A_SEAWarCover_14 = "";

			if ("".equals(bean.getTxtchkICC_A_SEAClause_1())
					|| bean.getTxtchkICC_A_SEAClause_1().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_1().equals("null")) {
				txtchkICC_A_SEAClause_1 = "";
			} else {
				txtchkICC_A_SEAClause_1 = bean.getTxtchkICC_A_SEAClause_1();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_1())
					|| bean.getTxtchkICC_A_SEAExclusive_1().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_1().equals("null")) {
				txtchkICC_A_SEAExclusive_1 = "";
			} else {
				txtchkICC_A_SEAExclusive_1 = bean
				.getTxtchkICC_A_SEAExclusive_1();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_1())
					|| bean.getTxtchkICC_A_SEAWarranty_1().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_1().equals("null")) {
				txtchkICC_A_SEAWarranty_1 = "";
			} else {
				txtchkICC_A_SEAWarranty_1 = bean.getTxtchkICC_A_SEAWarranty_1();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_1())
					|| bean.getTxtchkICC_A_SEAWarCover_1().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_1().equals("null")) {
				txtchkICC_A_SEAWarCover_1 = "";
			} else {
				txtchkICC_A_SEAWarCover_1 = bean.getTxtchkICC_A_SEAWarCover_1();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAClause_2())
					|| bean.getTxtchkICC_A_SEAClause_2().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_2().equals("null")) {
				txtchkICC_A_SEAClause_2 = "";
			} else {
				txtchkICC_A_SEAClause_2 = bean.getTxtchkICC_A_SEAClause_2();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_2())
					|| bean.getTxtchkICC_A_SEAExclusive_2().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_2().equals("null")) {
				txtchkICC_A_SEAExclusive_2 = "";
			} else {
				txtchkICC_A_SEAExclusive_2 = bean
				.getTxtchkICC_A_SEAExclusive_2();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_2())
					|| bean.getTxtchkICC_A_SEAWarranty_2().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_2().equals("null")) {
				txtchkICC_A_SEAWarranty_2 = "";
			} else {
				txtchkICC_A_SEAWarranty_2 = bean.getTxtchkICC_A_SEAWarranty_2();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_2())
					|| bean.getTxtchkICC_A_SEAWarCover_2().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_2().equals("null")) {
				txtchkICC_A_SEAWarCover_2 = "";
			} else {
				txtchkICC_A_SEAWarCover_2 = bean.getTxtchkICC_A_SEAWarCover_2();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAClause_3())
					|| bean.getTxtchkICC_A_SEAClause_3().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_3().equals("null")) {
				txtchkICC_A_SEAClause_3 = "";
			} else {
				txtchkICC_A_SEAClause_3 = bean.getTxtchkICC_A_SEAClause_3();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_3())
					|| bean.getTxtchkICC_A_SEAExclusive_3().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_3().equals("null")) {
				txtchkICC_A_SEAExclusive_3 = "";
			} else {
				txtchkICC_A_SEAExclusive_3 = bean
				.getTxtchkICC_A_SEAExclusive_3();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_3())
					|| bean.getTxtchkICC_A_SEAWarranty_3().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_3().equals("null")) {
				txtchkICC_A_SEAWarranty_3 = "";
			} else {
				txtchkICC_A_SEAWarranty_3 = bean.getTxtchkICC_A_SEAWarranty_3();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_3())
					|| bean.getTxtchkICC_A_SEAWarCover_3().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_3().equals("null")) {
				txtchkICC_A_SEAWarCover_3 = "";
			} else {
				txtchkICC_A_SEAWarCover_3 = bean.getTxtchkICC_A_SEAWarCover_3();
			}


			if ("".equals(bean.getTxtchkICC_A_SEAClause_4())
					|| bean.getTxtchkICC_A_SEAClause_4().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_4().equals("null")) {
				txtchkICC_A_SEAClause_4 = "";
			} else {
				txtchkICC_A_SEAClause_4 = bean.getTxtchkICC_A_SEAClause_4();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_4())
					|| bean.getTxtchkICC_A_SEAExclusive_4().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_4().equals("null")) {
				txtchkICC_A_SEAExclusive_4 = "";
			} else {
				txtchkICC_A_SEAExclusive_4 = bean
				.getTxtchkICC_A_SEAExclusive_4();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_4())
					|| bean.getTxtchkICC_A_SEAWarranty_4().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_4().equals("null")) {
				txtchkICC_A_SEAWarranty_4 = "";
			} else {
				txtchkICC_A_SEAWarranty_4 = bean.getTxtchkICC_A_SEAWarranty_4();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_4())
					|| bean.getTxtchkICC_A_SEAWarCover_4().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_4().equals("null")) {
				txtchkICC_A_SEAWarCover_4 = "";
			} else {
				txtchkICC_A_SEAWarCover_4 = bean.getTxtchkICC_A_SEAWarCover_4();
			}


			if ("".equals(bean.getTxtchkICC_A_SEAClause_5())
					|| bean.getTxtchkICC_A_SEAClause_5().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_5().equals("null")) {
				txtchkICC_A_SEAClause_5 = "";
			} else {
				txtchkICC_A_SEAClause_5 = bean.getTxtchkICC_A_SEAClause_5();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_5())
					|| bean.getTxtchkICC_A_SEAExclusive_5().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_5().equals("null")) {
				txtchkICC_A_SEAExclusive_5 = "";
			} else {
				txtchkICC_A_SEAExclusive_5 = bean
				.getTxtchkICC_A_SEAExclusive_5();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_5())
					|| bean.getTxtchkICC_A_SEAWarranty_5().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_5().equals("null")) {
				txtchkICC_A_SEAWarranty_5 = "";
			} else {
				txtchkICC_A_SEAWarranty_5 = bean.getTxtchkICC_A_SEAWarranty_5();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_5())
					|| bean.getTxtchkICC_A_SEAWarCover_5().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_5().equals("null")) {
				txtchkICC_A_SEAWarCover_5 = "";
			} else {
				txtchkICC_A_SEAWarCover_5 = bean.getTxtchkICC_A_SEAWarCover_5();
			}


			if ("".equals(bean.getTxtchkICC_A_SEAClause_6())
					|| bean.getTxtchkICC_A_SEAClause_6().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_6().equals("null")) {
				txtchkICC_A_SEAClause_6 = "";
			} else {
				txtchkICC_A_SEAClause_6 = bean.getTxtchkICC_A_SEAClause_6();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_6())
					|| bean.getTxtchkICC_A_SEAExclusive_6().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_6().equals("null")) {
				txtchkICC_A_SEAExclusive_6 = "";
			} else {
				txtchkICC_A_SEAExclusive_6 = bean
				.getTxtchkICC_A_SEAExclusive_6();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_6())
					|| bean.getTxtchkICC_A_SEAWarranty_6().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_6().equals("null")) {
				txtchkICC_A_SEAWarranty_6 = "";
			} else {
				txtchkICC_A_SEAWarranty_6 = bean.getTxtchkICC_A_SEAWarranty_6();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_6())
					|| bean.getTxtchkICC_A_SEAWarCover_6().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_6().equals("null")) {
				txtchkICC_A_SEAWarCover_6 = "";
			} else {
				txtchkICC_A_SEAWarCover_6 = bean.getTxtchkICC_A_SEAWarCover_6();
			}


			if ("".equals(bean.getTxtchkICC_A_SEAClause_7())
					|| bean.getTxtchkICC_A_SEAClause_7().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_7().equals("null")) {
				txtchkICC_A_SEAClause_7 = "";
			} else {
				txtchkICC_A_SEAClause_7 = bean.getTxtchkICC_A_SEAClause_7();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_7())
					|| bean.getTxtchkICC_A_SEAExclusive_7().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_7().equals("null")) {
				txtchkICC_A_SEAExclusive_7 = "";
			} else {
				txtchkICC_A_SEAExclusive_7 = bean
				.getTxtchkICC_A_SEAExclusive_7();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_7())
					|| bean.getTxtchkICC_A_SEAWarranty_7().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_7().equals("null")) {
				txtchkICC_A_SEAWarranty_7 = "";
			} else {
				txtchkICC_A_SEAWarranty_7 = bean.getTxtchkICC_A_SEAWarranty_7();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_7())
					|| bean.getTxtchkICC_A_SEAWarCover_7().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_7().equals("null")) {
				txtchkICC_A_SEAWarCover_7 = "";
			} else {
				txtchkICC_A_SEAWarCover_7 = bean.getTxtchkICC_A_SEAWarCover_7();
			}


			if ("".equals(bean.getTxtchkICC_A_SEAClause_8())
					|| bean.getTxtchkICC_A_SEAClause_8().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_8().equals("null")) {
				txtchkICC_A_SEAClause_8 = "";
			} else {
				txtchkICC_A_SEAClause_8 = bean.getTxtchkICC_A_SEAClause_8();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_8())
					|| bean.getTxtchkICC_A_SEAExclusive_8().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_8().equals("null")) {
				txtchkICC_A_SEAExclusive_8 = "";
			} else {
				txtchkICC_A_SEAExclusive_8 = bean
				.getTxtchkICC_A_SEAExclusive_8();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_8())
					|| bean.getTxtchkICC_A_SEAWarranty_8().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_8().equals("null")) {
				txtchkICC_A_SEAWarranty_8 = "";
			} else {
				txtchkICC_A_SEAWarranty_8 = bean.getTxtchkICC_A_SEAWarranty_8();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_8())
					|| bean.getTxtchkICC_A_SEAWarCover_8().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_8().equals("null")) {
				txtchkICC_A_SEAWarCover_8 = "";
			} else {
				txtchkICC_A_SEAWarCover_8 = bean.getTxtchkICC_A_SEAWarCover_8();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAClause_9())
					|| bean.getTxtchkICC_A_SEAClause_9().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_9().equals("null")) {
				txtchkICC_A_SEAClause_9 = "";
			} else {
				txtchkICC_A_SEAClause_9 = bean.getTxtchkICC_A_SEAClause_9();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_9())
					|| bean.getTxtchkICC_A_SEAExclusive_9().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_9().equals("null")) {
				txtchkICC_A_SEAExclusive_9 = "";
			} else {
				txtchkICC_A_SEAExclusive_9 = bean
				.getTxtchkICC_A_SEAExclusive_9();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_9())
					|| bean.getTxtchkICC_A_SEAWarranty_9().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_9().equals("null")) {
				txtchkICC_A_SEAWarranty_9 = "";
			} else {
				txtchkICC_A_SEAWarranty_9 = bean.getTxtchkICC_A_SEAWarranty_9();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_9())
					|| bean.getTxtchkICC_A_SEAWarCover_9().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_9().equals("null")) {
				txtchkICC_A_SEAWarCover_9 = "";
			} else {
				txtchkICC_A_SEAWarCover_9 = bean.getTxtchkICC_A_SEAWarCover_9();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAClause_10())
					|| bean.getTxtchkICC_A_SEAClause_10().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_10().equals("null")) {
				txtchkICC_A_SEAClause_10 = "";
			} else {
				txtchkICC_A_SEAClause_10 = bean.getTxtchkICC_A_SEAClause_10();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_10())
					|| bean.getTxtchkICC_A_SEAExclusive_10().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_10().equals("null")) {
				txtchkICC_A_SEAExclusive_10 = "";
			} else {
				txtchkICC_A_SEAExclusive_10 = bean
				.getTxtchkICC_A_SEAExclusive_10();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_10())
					|| bean.getTxtchkICC_A_SEAWarranty_10().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_10().equals("null")) {
				txtchkICC_A_SEAWarranty_10 = "";
			} else {
				txtchkICC_A_SEAWarranty_10 = bean
				.getTxtchkICC_A_SEAWarranty_10();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_10())
					|| bean.getTxtchkICC_A_SEAWarCover_10().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_10().equals("null")) {
				txtchkICC_A_SEAWarCover_10 = "";
			} else {
				txtchkICC_A_SEAWarCover_10 = bean
				.getTxtchkICC_A_SEAWarCover_10();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAClause_11())
					|| bean.getTxtchkICC_A_SEAClause_11().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_11().equals("null")) {
				txtchkICC_A_SEAClause_11 = "";
			} else {
				txtchkICC_A_SEAClause_11 = bean.getTxtchkICC_A_SEAClause_11();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_11())
					|| bean.getTxtchkICC_A_SEAExclusive_11().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_11().equals("null")) {
				txtchkICC_A_SEAExclusive_11 = "";
			} else {
				txtchkICC_A_SEAExclusive_11 = bean
				.getTxtchkICC_A_SEAExclusive_11();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_11())
					|| bean.getTxtchkICC_A_SEAWarranty_11().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_11().equals("null")) {
				txtchkICC_A_SEAWarranty_11 = "";
			} else {
				txtchkICC_A_SEAWarranty_11 = bean
				.getTxtchkICC_A_SEAWarranty_11();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_11())
					|| bean.getTxtchkICC_A_SEAWarCover_11().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_11().equals("null")) {
				txtchkICC_A_SEAWarCover_11 = "";
			} else {
				txtchkICC_A_SEAWarCover_11 = bean
				.getTxtchkICC_A_SEAWarCover_11();
			}

			if ("".equals(bean.getTxtchkICC_A_SEAClause_12())
					|| bean.getTxtchkICC_A_SEAClause_12().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_12().equals("null")) {
				txtchkICC_A_SEAClause_12 = "";
			} else {
				txtchkICC_A_SEAClause_12 = bean.getTxtchkICC_A_SEAClause_12();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_12())
					|| bean.getTxtchkICC_A_SEAExclusive_12().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_12().equals("null")) {
				txtchkICC_A_SEAExclusive_12 = "";
			} else {
				txtchkICC_A_SEAExclusive_12 = bean
				.getTxtchkICC_A_SEAExclusive_12();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_12())
					|| bean.getTxtchkICC_A_SEAWarranty_12().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_12().equals("null")) {
				txtchkICC_A_SEAWarranty_12 = "";
			} else {
				txtchkICC_A_SEAWarranty_12 = bean
				.getTxtchkICC_A_SEAWarranty_12();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_12())
					|| bean.getTxtchkICC_A_SEAWarCover_12().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_12().equals("null")) {
				txtchkICC_A_SEAWarCover_12 = "";
			} else {
				txtchkICC_A_SEAWarCover_12 = bean
				.getTxtchkICC_A_SEAWarCover_12();
			}

			if ("".equals(bean.getTxtchkICC_A_SEAClause_13())
					|| bean.getTxtchkICC_A_SEAClause_13().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_13().equals("null")) {
				txtchkICC_A_SEAClause_13 = "";
			} else {
				txtchkICC_A_SEAClause_13 = bean.getTxtchkICC_A_SEAClause_13();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_13())
					|| bean.getTxtchkICC_A_SEAExclusive_13().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_13().equals("null")) {
				txtchkICC_A_SEAExclusive_13 = "";
			} else {
				txtchkICC_A_SEAExclusive_13 = bean
				.getTxtchkICC_A_SEAExclusive_13();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_13())
					|| bean.getTxtchkICC_A_SEAWarranty_13().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_13().equals("null")) {
				txtchkICC_A_SEAWarranty_13 = "";
			} else {
				txtchkICC_A_SEAWarranty_13 = bean.getTxtchkICC_A_SEAWarranty_13();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_13())
					|| bean.getTxtchkICC_A_SEAWarCover_13().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_13().equals("null")) {
				txtchkICC_A_SEAWarCover_13 = "";
			} else {
				txtchkICC_A_SEAWarCover_13 = bean.getTxtchkICC_A_SEAWarCover_13();
			}


			if ("".equals(bean.getTxtchkICC_A_SEAClause_14())
					|| bean.getTxtchkICC_A_SEAClause_14().equals(null)
					|| bean.getTxtchkICC_A_SEAClause_14().equals("null")) {
				txtchkICC_A_SEAClause_14 = "";
			} else {
				txtchkICC_A_SEAClause_14 = bean.getTxtchkICC_A_SEAClause_14();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAExclusive_14())
					|| bean.getTxtchkICC_A_SEAExclusive_14().equals(null)
					|| bean.getTxtchkICC_A_SEAExclusive_14().equals("null")) {
				txtchkICC_A_SEAExclusive_14 = "";
			} else {
				txtchkICC_A_SEAExclusive_14 = bean
				.getTxtchkICC_A_SEAExclusive_14();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarranty_14())
					|| bean.getTxtchkICC_A_SEAWarranty_14().equals(null)
					|| bean.getTxtchkICC_A_SEAWarranty_14().equals("null")) {
				txtchkICC_A_SEAWarranty_14 = "";
			} else {
				txtchkICC_A_SEAWarranty_14 = bean.getTxtchkICC_A_SEAWarranty_14();
			}
			if ("".equals(bean.getTxtchkICC_A_SEAWarCover_14())
					|| bean.getTxtchkICC_A_SEAWarCover_14().equals(null)
					|| bean.getTxtchkICC_A_SEAWarCover_14().equals("null")) {
				txtchkICC_A_SEAWarCover_14 = "";
			} else {
				txtchkICC_A_SEAWarCover_14 = bean.getTxtchkICC_A_SEAWarCover_14();
			}


			String remarks = "";

			if ("".equals(bean.getRemarks()) || bean.getRemarks().equals(null)
					|| bean.getRemarks().equals("null"))
				remarks = " ";
			else
				remarks = bean.getRemarks();

			String ICC_A_Sea_Rate = "";
			ICC_A_Sea_Rate = txtchkICC_A_SEAClause_1 + "~"
			+ txtchkICC_A_SEAExclusive_1 + "~"
			+ txtchkICC_A_SEAWarranty_1 + "~"
			+ txtchkICC_A_SEAWarCover_1 + "~"
			+ bean.getRadDetective_1();
			String ICC_B_Sea_Rate = "";
			ICC_B_Sea_Rate = txtchkICC_A_SEAClause_2 + "~"
			+ txtchkICC_A_SEAExclusive_2 + "~"
			+ txtchkICC_A_SEAWarranty_2 + "~"
			+ txtchkICC_A_SEAWarCover_2 + "~"
			+ bean.getRadDetective_2();
			String ICC_C_Sea_Rate = "";
			ICC_C_Sea_Rate = txtchkICC_A_SEAClause_3 + "~"
			+ txtchkICC_A_SEAExclusive_3 + "~"
			+ txtchkICC_A_SEAWarranty_3 + "~"
			+ txtchkICC_A_SEAWarCover_3 + "~"
			+ bean.getRadDetective_3();

			String ICC_C_ND_Sea_Rate = "";
			ICC_C_ND_Sea_Rate = txtchkICC_A_SEAClause_4 + "~"
			+ txtchkICC_A_SEAExclusive_4 + "~"
			+ txtchkICC_A_SEAWarranty_4 + "~"
			+ txtchkICC_A_SEAWarCover_4 + "~"
			+ bean.getRadDetective_4();

			String ICC_A_Frozen_Meat_Sea_Rate = "";
			ICC_A_Frozen_Meat_Sea_Rate = txtchkICC_A_SEAClause_5 + "~"
			+ txtchkICC_A_SEAExclusive_5 + "~"
			+ txtchkICC_A_SEAWarranty_5 + "~"
			+ txtchkICC_A_SEAWarCover_5 + "~"
			+ bean.getRadDetective_5();

			String ICC_A_Frozen_Food_Sea_Rate = "";
			ICC_A_Frozen_Food_Sea_Rate = txtchkICC_A_SEAClause_6 + "~"
			+ txtchkICC_A_SEAExclusive_6 + "~"
			+ txtchkICC_A_SEAWarranty_6 + "~"
			+ txtchkICC_A_SEAWarCover_6 + "~"
			+ bean.getRadDetective_6();


			String ICC_C_Frozen_Meat_Sea_Rate = "";
			ICC_C_Frozen_Meat_Sea_Rate = txtchkICC_A_SEAClause_7 + "~"
			+ txtchkICC_A_SEAExclusive_7 + "~"
			+ txtchkICC_A_SEAWarranty_7 + "~"
			+ txtchkICC_A_SEAWarCover_7 + "~"
			+ bean.getRadDetective_7();

			String ICC_C_Frozen_Food_Sea_Rate = "";
			ICC_C_Frozen_Food_Sea_Rate = txtchkICC_A_SEAClause_8 + "~"
			+ txtchkICC_A_SEAExclusive_8 + "~"
			+ txtchkICC_A_SEAWarranty_8 + "~"
			+ txtchkICC_A_SEAWarCover_8 + "~"
			+ bean.getRadDetective_8();


			String Icc_Air_Cargo_Air_Risks  = "";
			Icc_Air_Cargo_Air_Risks= txtchkICC_A_SEAClause_9 + "~"
			+ txtchkICC_A_SEAExclusive_9 + "~"
			+ txtchkICC_A_SEAWarranty_9 + "~"
			+ txtchkICC_A_SEAWarCover_9 + "~"
			+ bean.getRadDetective_9();

			String  Icc_Air_Cargo_All_Risks = "";
			Icc_Air_Cargo_All_Risks = txtchkICC_A_SEAClause_10 + "~"
			+ txtchkICC_A_SEAExclusive_10 + "~"
			+ txtchkICC_A_SEAWarranty_10 + "~"
			+ txtchkICC_A_SEAWarCover_10 + "~"
			+ bean.getRadDetective_10();

			String All_Risks_Land_Transit = "";
			All_Risks_Land_Transit = txtchkICC_A_SEAClause_11 + "~"
			+ txtchkICC_A_SEAExclusive_11 + "~"
			+ txtchkICC_A_SEAWarranty_11 + "~"
			+ txtchkICC_A_SEAWarCover_11 + "~"
			+ bean.getRadDetective_11();

			String Land_Transit_land = "";
			Land_Transit_land = txtchkICC_A_SEAClause_12 + "~"
			+ txtchkICC_A_SEAExclusive_12 + "~"
			+ txtchkICC_A_SEAWarranty_12 + "~"
			+ txtchkICC_A_SEAWarCover_12 + "~"
			+ bean.getRadDetective_12();

			String All_Risks_parcel_post = "";
			All_Risks_parcel_post = txtchkICC_A_SEAClause_13 + "~"
			+ txtchkICC_A_SEAExclusive_13 + "~"
			+ txtchkICC_A_SEAWarranty_13 + "~"
			+ txtchkICC_A_SEAWarCover_13 + "~"
			+ bean.getRadDetective_13();

			String All_Risks_Sea_and_Air  = "";
			All_Risks_Sea_and_Air = txtchkICC_A_SEAClause_14 + "~"
			+ txtchkICC_A_SEAExclusive_14 + "~"
			+ txtchkICC_A_SEAWarranty_14 + "~"
			+ txtchkICC_A_SEAWarCover_14 + "~"
			+ bean.getRadDetective_14();

			if("add".equals(bean.getMode())){
				Object[] val = {bean.getCommoditygroupId(),bean.getCommodityName(), bean.getCommodityRates(), bean.getCoverage_Referal(),
						ICC_A_Sea_Rate, ICC_B_Sea_Rate, ICC_C_Sea_Rate, ICC_C_ND_Sea_Rate,
						ICC_A_Frozen_Meat_Sea_Rate,ICC_A_Frozen_Food_Sea_Rate,ICC_C_Frozen_Meat_Sea_Rate,ICC_C_Frozen_Food_Sea_Rate
						,Icc_Air_Cargo_Air_Risks,Icc_Air_Cargo_All_Risks,All_Risks_Land_Transit,
						Land_Transit_land,All_Risks_parcel_post,All_Risks_Sea_and_Air, "", "", "", "", remarks,
						bean.getStatus(), bean.getCode(), bean.getEff_date(),
						"0", bean.getCommodityTypeID(), branchCode, "" };
				rservice.getNewCommodity(val);
				Object[] val1 = { branchCode, branchCode};
				commodityList = rservice.getCommodityList(val1);
				this.addActionMessage(getText("insert.success"));
			}
			else
			{
				String string="";
				boolean bo = validateDate(bean.getPrevdate());
				if(bo==false)  {							
					string="update";

					Object[] val = {bean.getCommoditygroupId(),bean.getCommodityName(),  bean.getCommodityRates(), bean.getCoverage_Referal(),ICC_A_Sea_Rate, ICC_B_Sea_Rate, ICC_C_Sea_Rate,
							ICC_C_ND_Sea_Rate,ICC_A_Frozen_Meat_Sea_Rate,ICC_A_Frozen_Food_Sea_Rate,ICC_C_Frozen_Meat_Sea_Rate,ICC_C_Frozen_Food_Sea_Rate
							,Icc_Air_Cargo_Air_Risks,Icc_Air_Cargo_All_Risks,All_Risks_Land_Transit,Land_Transit_land,All_Risks_parcel_post,
							All_Risks_Sea_and_Air, remarks, bean.getStatus(), bean.getCode(), bean.getEff_date(),bean.getCommodityTypeID(),bean.getCommodityID(),
							branchCode};
					rservice.getUpdateCommodity(val,string);
				}
				else {				
					string="insert";
					Object[] val = {bean.getCommoditygroupId(),bean.getCommodityID(), bean.getCommodityName(), bean.getCommodityRates(), bean.getCoverage_Referal(),
							ICC_A_Sea_Rate, ICC_B_Sea_Rate, ICC_C_Sea_Rate, ICC_C_ND_Sea_Rate,
							ICC_A_Frozen_Meat_Sea_Rate,ICC_A_Frozen_Food_Sea_Rate,ICC_C_Frozen_Meat_Sea_Rate,ICC_C_Frozen_Food_Sea_Rate                       
							,Icc_Air_Cargo_Air_Risks,Icc_Air_Cargo_All_Risks,All_Risks_Land_Transit,
							Land_Transit_land,All_Risks_parcel_post,All_Risks_Sea_and_Air, "", "", "", "", remarks,
							bean.getStatus(), bean.getCode(), bean.getEff_date(),
							Integer.parseInt(bean.getAmendID()) + 1,
							bean.getCommodityTypeID(), branchCode, "" };

					rservice.getUpdateCommodity(val,string);
				}		

				Object[] val1 = { branchCode, branchCode };
				//commodityList = rservice.getCommodityList(val1);
				this.addActionMessage(getText("update.success"));
			}
			menuType="commoditymaster";
			return "commonList";
		}
		return "editCommodity";
	}
	public String editcommoditymaster()
	{
		if("edit".equals(bean.getMode()))
		{
			Object[] val = { bean.getCommodityID(), branchCode,bean.getCommodityID(), branchCode };
			rservice.getSelectedCommdity(bean, val);
		}
		return "editCommodity";
	}
	public String executivemaster()
	{
		menuType="executivemaster";
		return "commonList";		
	}
	public String editexecutivemaster() {
		if("edit".equals(bean.getMode())) {
			rservice.getExecutivemaster(bean,branchCode);
		}
		return "editExecutive";
	}
	public String saveexecutivemaster() {
		validateexecutivemaster();
		if(this.getActionErrors().isEmpty()) {
			rservice.updateintoExecutiveMaster(bean);
			if(bean.getMode().equalsIgnoreCase("edit")) 
				this.addActionMessage(getText("message.commoditymaster.update"));
			else
				this.addActionMessage(getText("message.commoditymaster.inserted"));
			menuType="executivemaster";
			return "commonList";
		}
		return "editExecutive";
	}
	public void validateexecutivemaster(){
		if(StringUtils.isBlank(bean.getExecutiveName()))
			this.addActionError(getText("error.executivemaster.executivemasterEmpty"));
		/*else if(!StringUtils.isAlphanumeric(bean.getExecutiveName())){
			this.addActionError(getText("error.executivemaster.executivemasterString"));
		}*/
		if(StringUtils.isNotBlank(bean.getOtherpartyCode()) && !StringUtils.isAlphanumeric(bean.getOtherpartyCode()))
			this.addActionError(getText("error.executivemaster.otherPartyacodevalid"));
		/*if(StringUtils.isEmpty(bean.getExmProduct()))
    		this.addActionError(getText("error.executivemaster.polocyTypeEmpty"));*/
		if(StringUtils.isBlank(bean.getExmCommission())) {
			this.addActionError(getText("error.executive.commission"));
		}
		else {
			try {
				if(Double.parseDouble(bean.getExmCommission())<0 || Double.parseDouble(bean.getExmCommission())>100) {
					this.addActionError(getText("error.executive.commission.invalid"));
				}
			}
			catch(Exception excpetion) {
				this.addActionError(getText("error.executive.commission.invalid"));
			}
		}
		if(StringUtils.isBlank(bean.getExmOpenCoverCommission())) {
			this.addActionError(getText("error.executive.OpenCoverCommission"));
		}
		else {
			try {
				if(Double.parseDouble(bean.getExmOpenCoverCommission())<0 || Double.parseDouble(bean.getExmOpenCoverCommission())>100) {
					this.addActionError(getText("error.executive.OpenCoverCommission.invalid"));
				}
			}
			catch(Exception excpetion) {
				this.addActionError(getText("error.executive.OpenCoverCommission.invalid"));
			}
		}
		if(StringUtils.isEmpty(bean.getExmEffectiveDate()))
			this.addActionError(getText("error.executivemaster.exmmEffectiveDateEmpty"));
		else if(validateDate(bean.getExmEffectiveDate()))
			this.addActionError(getText("error.executivemaster.exmEffectiveDateFormat"));
		if(this.getActionErrors().isEmpty()) {
			if(!bean.getMode().equalsIgnoreCase("edit")){
				if(rservice.validateOtherPartyCode(bean.getOtherpartyCode(),bean.getMode(),bean.getExmexecutiveId())!=0)
					this.addActionError(getText("error.executivemaster.otherpartycoderExists"));
			}
		}
	}
}
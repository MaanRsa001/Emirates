package com.maan.copyquote;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import com.maan.copyquote.service.CopyQuoteService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CopyQuoteAction extends ActionSupport
{
	HttpServletRequest request=ServletActionContext.getRequest();
	Map<String, Object> session=ActionContext.getContext().getSession();
	CopyQuoteService service=new CopyQuoteService();
	private static final long serialVersionUID = 1L;

	private String searchType;
	private String searchValue;	
	private String display;
	private List<Map<String, Object>> searchResult;
	private String copyQuoteValue;
	private String loginId;
	private String status;
	private String customerName;
	private String openCover=getText("OPEN_COVER");
	private String productId=(String) session.get("product_id");
	private String branchCode=(String)session.get("LoginBranchCode");
	private String openCoverNo=(String)session.get("openCoverNo");
	private String issuer=(String)session.get("RSAISSUER");
	private String loginID=(String)session.get("user");
	private String openCoverSearchValue;
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * @return the issuer
	 */
	public String getIssuer() {
		return issuer;
	}

	/**
	 * @param issuer the issuer to set
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/**
	 * @return the openCoverSearchValue
	 */
	public String getOpenCoverSearchValue() {
		return openCoverSearchValue;
	}

	/**
	 * @param openCoverSearchValue the openCoverSearchValue to set
	 */
	public void setOpenCoverSearchValue(String openCoverSearchValue) {
		this.openCoverSearchValue = openCoverSearchValue;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the newCopyQuote
	 */
	public String getNewCopyQuote() {
		return newCopyQuote;
	}

	/**
	 * @param newCopyQuote the newCopyQuote to set
	 */
	public void setNewCopyQuote(String newCopyQuote) {
		this.newCopyQuote = newCopyQuote;
	}
	private String newCopyQuote;
	


	/**
	 * @return the copyQuoteValue
	 */
	public String getCopyQuoteValue() {
		return copyQuoteValue;
	}

	/**
	 * @param copyQuoteValue the copyQuoteValue to set
	 */
	public void setCopyQuoteValue(String copyQuoteValue) {
		this.copyQuoteValue = copyQuoteValue;
	}

	/**
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * @param display the display to set
	 */
	public void setDisplay(String display) {
		this.display = display;
	}

	/**
	 * @return the searchResult
	 */
	public List<Map<String, Object>> getSearchResult() {
		return searchResult;
	}

	/**
	 * @param searchResult the searchResult to set
	 */
	public void setSearchResult(List<Map<String, Object>> searchResult) {
		this.searchResult = searchResult;
	}

	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String init()
	{	
		display="init";
		return SUCCESS;
	}
	public String search()
	{	
		validateField();
		if(!hasActionErrors()){
			searchResult=service.getCopyQuoteSearch(searchType, searchValue,openCoverNo,productId);	
			display="search";
		}else{
			display="init";
		}		
		return SUCCESS;	
	}

	public String copy()
	{
		try{
			if((StringUtils.isEmpty(copyQuoteValue))) {
				addActionError(getText("error.copyQuote.empty"));
			}
		    if(!hasActionErrors()){	
				loginId=(String)session.get("user");		
				
					Map <String ,Object> map=service.copyQuote((StringUtils.isEmpty(issuer)?loginId:issuer), copyQuoteValue,"Normal","", StringUtils.isBlank(issuer)?"1":issuer);		
					newCopyQuote=map.get("QUOTE_NO").toString()==null?"":map.get("QUOTE_NO").toString();
					status=(String)map.get("ERROR_STATUS")==null?"":(String)map.get("ERROR_STATUS");
					customerName=(String)map.get("CUSTOMER_NAME")==null?"":(String)map.get("CUSTOMER_NAME");
					if(status!=null && "Y".equalsIgnoreCase(status)){
						msg = getText("copyQuote.successmsg", new String[]{newCopyQuote,customerName});
					}else{
						msg = getText("copyQuote.errormsg", new String[]{newCopyQuote,status});	
					}
					display="copyquote";
		    }else {
		    	searchResult=service.getCopyQuoteSearch(searchType, searchValue,openCoverNo,productId);
		    	display="search";
		    }
		}catch(Exception e){
			display="copyquote";
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String redirect()
	{
		loginId=(String)session.get("user");	
		return "redirect";
	}
	private void validateField() {
		if((StringUtils.isEmpty(searchType)))
		{
			addActionError(getText("error.copyQuoteOption.empty"));
		}
		if(StringUtils.isEmpty(searchValue) && !("policyNo".equals(searchType) && openCover.equals(productId)))
		{
			addActionError(getText("error.copyQuoteValue.empty"));
		}
		if(!(StringUtils.isNumeric(searchValue)) &&  StringUtils.isNotEmpty(searchValue) && "quoteNo".equals(searchType))
		{
			addActionError(getText("error.copyQuoteValue.notvalid"));
		}
	}
}

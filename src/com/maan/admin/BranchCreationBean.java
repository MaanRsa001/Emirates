package com.maan.admin;

import com.maan.services.util.runner;

public class BranchCreationBean extends com.maan.common.error.ErrorInfo{
    private int sno;
    private String quoteTypeId;
    private String policyTypeId;
    private String debitTypeId;
    private String proposalNoTypeId;
    private String dataTransferTypeId;
    private String transferTypeId;
    private String claimTypeId;
    private String customerTypeId;
    private String applicationTypeId;
    private String openCoverNoTypeId;
    private String certificateSlNoTypeId;
    private String certificateNoTypeId;
    private String receiptTypeId;
    private String branchName;
    private String branchCode;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String country;
    private String currencyAbbre;
    private String currencyAcronym;
    private String curDecimalDigit;
    private String curDecimalName;
    private String currencyName;
    private String decimalPlaces;
    private String desCountryId;
    private String email;
    private String fax;
    private String headerImage;
    private String footerImage;
    private String originCountryId;
    private String phone;
    private String stamp;
    private String tax ;
    private String effectiveDate;
    private String status;
    private String signImage;
    private String remarks;
    
    //product related
    
    private String productCode;
    private String productName;

	public String getApplicationTypeId() {
		return applicationTypeId;
	}

	public void setApplicationTypeId(String applicationTypeId) {
		this.applicationTypeId = applicationTypeId;
	}

	public String getCertificateNoTypeId() {
		return certificateNoTypeId;
	}

	public void setCertificateNoTypeId(String certificateNoTypeId) {
		this.certificateNoTypeId = certificateNoTypeId;
	}

	public String getCertificateSlNoTypeId() {
		return certificateSlNoTypeId;
	}

	public void setCertificateSlNoTypeId(String certificateSlNoTypeId) {
		this.certificateSlNoTypeId = certificateSlNoTypeId;
	}

	public String getClaimTypeId() {
		return claimTypeId;
	}

	public void setClaimTypeId(String claimTypeId) {
		this.claimTypeId = claimTypeId;
	}

	public String getCustomerTypeId() {
		return customerTypeId;
	}

	public void setCustomerTypeId(String customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public String getDataTransferTypeId() {
		return dataTransferTypeId;
	}

	public void setDataTransferTypeId(String dataTransferTypeId) {
		this.dataTransferTypeId = dataTransferTypeId;
	}

	public String getDebitTypeId() {
		return debitTypeId;
	}

	public void setDebitTypeId(String debitTypeId) {
		this.debitTypeId = debitTypeId;
	}

	public String getOpenCoverNoTypeId() {
		return openCoverNoTypeId;
	}

	public void setOpenCoverNoTypeId(String openCoverNoTypeId) {
		this.openCoverNoTypeId = openCoverNoTypeId;
	}

	public String getPolicyTypeId() {
		return policyTypeId;
	}

	public void setPolicyTypeId(String policyTypeId) {
		this.policyTypeId = policyTypeId;
	}	

	public String getProposalNoTypeId() {
		return proposalNoTypeId;
	}

	public void setProposalNoTypeId(String proposalNoTypeId) {
		this.proposalNoTypeId = proposalNoTypeId;
	}

	public String getQuoteTypeId() {
		return quoteTypeId;
	}

	public void setQuoteTypeId(String quoteTypeId) {
		this.quoteTypeId = quoteTypeId;
	}

	public String getReceiptTypeId() {
		return receiptTypeId;
	}

	public void setReceiptTypeId(String receiptTypeId) {
		this.receiptTypeId = receiptTypeId;
	}

	public String getTransferTypeId() {
		return transferTypeId;
	}

	public void setTransferTypeId(String transferTypeId) {
		this.transferTypeId = transferTypeId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(final String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(final String branchName) {
		this.branchName = branchName;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(final int no) {
		this.sno = no;
	}

    public String getAddress1() {
		return address1;
	}

	public void setAddress1(final String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(final String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getCurDecimalDigit() {
		return curDecimalDigit;
	}

	public void setCurDecimalDigit(final String curDecimalDigit) {
		this.curDecimalDigit = curDecimalDigit;
	}

	public String getCurDecimalName() {
		return curDecimalName;
	}

	public void setCurDecimalName(final String curDecimalName) {
		this.curDecimalName = curDecimalName;
	}

	public String getCurrencyAbbre() {
		return currencyAbbre;
	}

	public void setCurrencyAbbre(final String currencyAbbre) {
		this.currencyAbbre = currencyAbbre;
	}

	public String getCurrencyAcronym() {
		return currencyAcronym;
	}

	public void setCurrencyAcronym(final String currencyAcronym) {
		this.currencyAcronym = currencyAcronym;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(final String currencyName) {
		this.currencyName = currencyName;
	}

	public String getDecimalPlaces() {
		return decimalPlaces;
	}

	public void setDecimalPlaces(final String decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}

	public String getDesCountryId() {
		return desCountryId;
	}

	public void setDesCountryId(final String desCountryId) {
		this.desCountryId = desCountryId;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(final String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(final String fax) {
		this.fax = fax;
	}

	public String getFooterImage() {
		return footerImage;
	}

	public void setFooterImage(final String footerImage) {
		this.footerImage = footerImage;
	}

	public String getHeaderImage() {
		return headerImage;
	}

	public void setHeaderImage(final String headerImage) {
		this.headerImage = headerImage;
	}

	public String getOriginCountryId() {
		return originCountryId;
	}

	public void setOriginCountryId(final String originCountryId) {
		this.originCountryId = originCountryId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public String getSignImage() {
		return signImage;
	}

	public void setSignImage(final String signImage) {
		this.signImage = signImage;
	}

	public String getStamp() {
		return stamp;
	}

	public void setStamp(final String stamp) {
		this.stamp = stamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(final String tax) {
		this.tax = tax;
	}

     public String[][] getBranchList(){
        String[][] result = new String[0][0];
        try{
            result = runner.multipleSelection("select SNO__,BRANCH_NAME,BRANCH_CODE from BRANCH_MASTER order by branch_name");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public String[][] getBranchViewDetails(final String sno){
         String[][] result = new String[0][0];
         try{
             result=runner.multipleSelection("select SNO__,BRANCH_CODE,BRANCH_NAME,STATUS,ADDRESS1,ADDRESS2,ADDRESS3,CITY,COUNTRY,PHONE,FAX,EMAIL,AMEND_ID,to_char(EFFECTIVE_DATE,'dd-mm-yyyy'),REMARKS,HEADER_IMG,FOOTER_IMG, SIGN_IMG,STAMP,CURRENCY_NAME,CURRENCY_ABBREVIATION,CURRENCY_ACRONYM,ORIGINATION_COUNTRY_ID,DESTINATION_COUNTRY_ID,CURRENCY_DECIMAL_NAME,CURRENCY_DECIMAL_DIGIT,DECIMAL_PLACES,TAX,REMARKS from BRANCH_MASTER where BRANCH_CODE = "+sno);
         }
         catch(Exception ex){
             ex.printStackTrace();
         }
         return result;
    }
    
    public void validateBranchDetails(){
    	for(int i=0;i<branchCode.length();i++){
    		if(!Character.isDigit(branchCode.charAt(i))){
    			setError("Branch code allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<branchName.length();i++){
    		if(Character.isDigit(branchName.charAt(i))){
    			setError("Branch name does not allows numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<city.length();i++){
    		if(Character.isLetter(city.charAt(i)) || Character.isSpaceChar(city.charAt(i)) || (city.charAt(i)=='.')){
    			continue;
    		}
    		else {
    			setError("Invalid entry in City");
    			break;
    		}
    	}
    	
    	for(int i=0;i<country.length();i++){
    		if(Character.isLetter(country.charAt(i)) || Character.isSpaceChar(country.charAt(i)) || (country.charAt(i)=='.')){
    			continue;
    		}
    		else {
    			setError("Invalid entry in Country");
    			break;
    		}
    	}
    	
    	for(int i=0;i<currencyAbbre.length();i++){
    		if(!Character.isLetter(currencyAbbre.charAt(i))){
    			setError("CURRENCY ABBREVIATION allows only text");
    			break;
    		}
    	}
    	
    	for(int i=0;i<currencyAcronym.length();i++){
    		if(!Character.isLetter(currencyAcronym.charAt(i))){
    			setError("CURRENCY ACRONYM allows only text");
    			break;
    		}
    	}
    	
    	for(int i=0;i<curDecimalDigit.length();i++){
    		if(!Character.isDigit(curDecimalDigit.charAt(i))){
    			setError("CURRENCY DECIMAL DIGIT allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<curDecimalName.length();i++){
    		if(!Character.isLetter(curDecimalName.charAt(i))){
    			setError("CURRENCY DECIMAL NAME allows only text");
    			break;
    		}
    	}
    	
    	for(int i=0;i<currencyName.length();i++){
    		if(!Character.isLetter(currencyName.charAt(i))){
    			setError("CURRENCY NAME allows only text");
    			break;
    		}
    	}
    	
    	if(decimalPlaces.length()>1){
    		setError("Decimal places allow only one digit");
    	}
    	
    	for(int i=0;i<decimalPlaces.length();i++){
    		if(!Character.isDigit(decimalPlaces.charAt(i))){
    			setError("DECIMAL PLACES only allows numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<desCountryId.length();i++){
    		if(!Character.isDigit(desCountryId.charAt(i))){
    			setError("DESTINATION COUNTRY ID only allows numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<fax.length();i++){
    		if(!Character.isDigit(fax.charAt(i))) {
    			if(!Character.isSpaceChar(fax.charAt(i))){
    				setError("FAX only allows numbers and space");
    				break;
    		}
    			
    		}
    	}
    	
    	for(int i=0;i<footerImage.length();i++){
    		
    		if(Character.isLetter(footerImage.charAt(i)) || Character.isDigit(footerImage.charAt(i))  || (footerImage.charAt(i)=='.') || (footerImage.charAt(i)=='_')){
    			continue;
    		}
    		else {
    			setError("Invalid entry in FOOTER_IMG");
    			break;
    		}
    	}
    	
    	for(int i=0;i<headerImage.length();i++){
    		
    		if(Character.isLetter(headerImage.charAt(i)) || Character.isDigit(headerImage.charAt(i))  || (headerImage.charAt(i)=='.') || (headerImage.charAt(i)=='_')){
    			continue;
    		}
    		else {
    			setError("Invalid entry in HEADER_IMG");
    			break;
    		}
    	}
    	
    	for(int i=0;i<signImage.length();i++){
    		
    		if(Character.isLetter(signImage.charAt(i)) || Character.isDigit(signImage.charAt(i))  || (signImage.charAt(i)=='.') || (signImage.charAt(i)=='_')){
    			continue;
    		}
    		else {
    			setError("Invalid entry in SIGN_IMG");
    			break;
    		}
    	}
    	
    	for(int i=0;i<stamp.length();i++){
    		
    		if(Character.isLetter(stamp.charAt(i)) || Character.isDigit(stamp.charAt(i))  || (stamp.charAt(i)=='.') || (stamp.charAt(i)=='_')){
    			continue;
    		}
    		else {
    			setError("Invalid entry in STAMP");
    			break;
    		}
    	}
    	
    	for(int i=0;i<originCountryId.length();i++){
    		if(!Character.isDigit(originCountryId.charAt(i))){
    			setError("ORIGINATION COUNTRY ID allows number only");
    			break;
    		}
    	}
    	
    	for(int i=0;i<phone.length();i++){
    		if(!Character.isDigit(phone.charAt(i))){
    			setError("PHONE allows number only");
    			break;
    		}
    	}
    	
    	if(tax.length()>8){
    		setError("TAX should should not exceed 8 digit");
    	}
    	
    	for(int i=0;i<tax.length();i++){
    		if(!Character.isDigit(tax.charAt(i))){
    			if(tax.charAt(i)=='.'){
    			setError("TAX allows number and dot only");
    			break;
    			}
    		}
    	}
    
    	
    }
    
    public String insertOrUpdateBranch(String branchCode,String branchName,String address1,String address2,String address3,String city,String country,String currencyAbbre,String currencyAcronym,String curDecimalDigit,String curDecimalName,String currencyName,String decimalPlaces,String desCountryId,String email,String fax,String headerImage,String footerImage,String originCountryId,String phone,String stamp,String tax,String status,String signImage,String remarks,String mode){
    	String flag="NO";
    	if(mode.equalsIgnoreCase("add")){
    	String snoqry="select max(sno__+1) from branch_master";
    	String maxsno="";
    	String qry="insert into branch_master(SNO__,BRANCH_CODE,BRANCH_NAME,STATUS,ADDRESS1,ADDRESS2,ADDRESS3,CITY,COUNTRY,PHONE,FAX,EMAIL,AMEND_ID,EFFECTIVE_DATE,REMARKS,HEADER_IMG,FOOTER_IMG,SIGN_IMG,STAMP,CURRENCY_NAME,CURRENCY_ABBREVIATION,CURRENCY_ACRONYM,ORIGINATION_COUNTRY_ID,DESTINATION_COUNTRY_ID,CURRENCY_DECIMAL_NAME,CURRENCY_DECIMAL_DIGIT,DECIMAL_PLACES,TAX) values(?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	try {
    		System.out.println("BeanCreation--bean---->");
    		maxsno=runner.singleSelection(snoqry);
    		String arg[]={maxsno,branchCode,branchName,status,address1,address2,address3,city,country,phone,fax,email,"0",remarks,headerImage,footerImage,signImage,stamp,currencyName,currencyAbbre,currencyAcronym,originCountryId,desCountryId,curDecimalName,curDecimalDigit,decimalPlaces,tax};
    		runner.multipleInsertion(qry, arg);
    		flag="YES";
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	}
    	else if(mode.equalsIgnoreCase("edit")){
    		String arg[]={branchName,status,address1,address2,address3,city,country,phone,fax,email,"0",remarks,headerImage,footerImage,signImage,stamp,currencyName,currencyAbbre,currencyAcronym,originCountryId,desCountryId,curDecimalName,curDecimalDigit,decimalPlaces,tax,branchCode};
    		String qry="update branch_master set BRANCH_NAME=?,STATUS=?,ADDRESS1=?,ADDRESS2=?,ADDRESS3=?,CITY=?,COUNTRY=?,PHONE=?,FAX=?,EMAIL=?,AMEND_ID=?,REMARKS=?,HEADER_IMG=?,FOOTER_IMG=?,SIGN_IMG=?,STAMP=?,CURRENCY_NAME=?,CURRENCY_ABBREVIATION=?,CURRENCY_ACRONYM=?,ORIGINATION_COUNTRY_ID=?,DESTINATION_COUNTRY_ID=?,CURRENCY_DECIMAL_NAME=?,CURRENCY_DECIMAL_DIGIT=?,DECIMAL_PLACES=?,TAX=? where BRANCH_CODE=?";
    		runner.multipleUpdation(qry, arg);
    		flag="YES";
    	}
    	
    	return flag;
    }
    
    public String[][] getProductList(){
    	String result[][]=new String[0][0];
    	
    	String qry="select p.PRODUCT_ID,p.PRODUCT_NAME from PRODUCT_MASTER P";
    	try{
    		result=runner.multipleSelection(qry);
    		
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return result;
    }
    
    public String[][] getBranchDetailMaster(String branchCode,String productId){
    	String result[][]=new String[0][0];
    	String arg[]={branchCode,productId};
    	
    	String qry="select BRANCH_CODE,PRODUCT_ID,EFFECTIVE_DATE,REMARKS,STATUS,QUOTE_TYPE_ID,POLICY_TYPE_ID,DEBIT_TYPE_ID,PROPOSAL_NO_TYPE_ID,DATA_TRANSFER_TYPE_ID,TRANSFER_TYPE_ID,CLAIM_TYPE_ID,CUSTOMER_TYPE_ID,APPLICATION_TYPE_ID,OPENCOVER_NO_TYPE_ID,CERTIFICATE_SLNO_TYPE_ID,CERTIFICATE_NO_TYPE_ID,HEADER_IMG,FOOTER_IMG,SIGN_IMG,STAMP_IMG,RECEIPT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?";
    	try {
    		result=runner.multipleSelection(qry, arg);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return result;
    }
    
    public void validateBranchWiseProductDetails(){
    	
    	for(int i=0;i<branchCode.length();i++){
    		if(!Character.isDigit(branchCode.charAt(i))){
    			setError("Branch code allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<productCode.length();i++){
    		if(!Character.isDigit(productCode.charAt(i))){
    			setError("Product code allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<quoteTypeId.length();i++){
    		if(!Character.isDigit(quoteTypeId.charAt(i))){
    			setError("quoteTypeId allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<policyTypeId.length();i++){
    		if(!Character.isDigit(policyTypeId.charAt(i))){
    			setError("policyTypeId allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<debitTypeId.length();i++){
    		if(!Character.isDigit(debitTypeId.charAt(i))){
    			setError("debitTypeId allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<proposalNoTypeId.length();i++){
    		if(!Character.isDigit(proposalNoTypeId.charAt(i))){
    			setError("proposalNoTypeId allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<dataTransferTypeId.length();i++){
    		if(!Character.isDigit(dataTransferTypeId.charAt(i))){
    			setError("dataTransferTypeId allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<transferTypeId.length();i++){
    		if(!Character.isDigit(transferTypeId.charAt(i))){
    			setError("transferTypeId allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<claimTypeId.length();i++){
    		if(!Character.isDigit(claimTypeId.charAt(i))){
    			setError("claimTypeId allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<customerTypeId.length();i++){
    		if(!Character.isDigit(customerTypeId.charAt(i))){
    			setError("customerTypeId allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<applicationTypeId.length();i++){
    		if(!Character.isDigit(applicationTypeId.charAt(i))){
    			setError("applicationTypeId allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<openCoverNoTypeId.length();i++){
    		if(!Character.isDigit(openCoverNoTypeId.charAt(i))){
    			setError("openCoverNoTypeId allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<certificateSlNoTypeId.length();i++){
    		if(!Character.isDigit(certificateSlNoTypeId.charAt(i))){
    			setError("certificateSlNoTypeId allows only numbers");
    			break;
    		}
    	}
    	
    	for(int i=0;i<certificateNoTypeId.length();i++){
    		if(!Character.isDigit(certificateNoTypeId.charAt(i))){
    			setError("certificateNoTypeId allows only numbers");
    			break;
    		}
    	}
    	
for(int i=0;i<footerImage.length();i++){
    		
    		if(Character.isLetter(footerImage.charAt(i)) || Character.isDigit(footerImage.charAt(i))  || (footerImage.charAt(i)=='.') || (footerImage.charAt(i)=='_')){
    			continue;
    		}
    		else {
    			setError("Invalid entry in FOOTER_IMG");
    			break;
    		}
    	}
    	
    	for(int i=0;i<headerImage.length();i++){
    		
    		if(Character.isLetter(headerImage.charAt(i)) || Character.isDigit(headerImage.charAt(i))  || (headerImage.charAt(i)=='.') || (headerImage.charAt(i)=='_')){
    			continue;
    		}
    		else {
    			setError("Invalid entry in HEADER_IMG");
    			break;
    		}
    	}
    	
    	for(int i=0;i<signImage.length();i++){
    		
    		if(Character.isLetter(signImage.charAt(i)) || Character.isDigit(signImage.charAt(i))  || (signImage.charAt(i)=='.') || (signImage.charAt(i)=='_')){
    			continue;
    		}
    		else {
    			setError("Invalid entry in SIGN_IMG");
    			break;
    		}
    	}
    	
    	for(int i=0;i<stamp.length();i++){
    		
    		if(Character.isLetter(stamp.charAt(i)) || Character.isDigit(stamp.charAt(i))  || (stamp.charAt(i)=='.') || (stamp.charAt(i)=='_')){
    			continue;
    		}
    		else {
    			setError("Invalid entry in STAMP");
    			break;
    		}
    	}
    	
    	for(int i=0;i<receiptTypeId.length();i++){
    		if(!Character.isDigit(receiptTypeId.charAt(i))){
    			setError("receiptTypeId allows only numbers");
    			break;
    		}
    	}
    	
    }
    
    public String insertOrUpdateBranchDetail(String branchCode,String productId,String effectiveDate,String remarks,String status,String quoteTypeId,String policyTypeId,String debitTypeId,String proposalNoTypeId,String dataTransferTypeId,String transferTypeId,String claimTypeId,String customerTypeId,String applicationTypeId,String openCoverNoTypeId,String certificateSlNoTypeId,String certificateNoTypeId,String headerImg,String footerImg,String signImg,String stampImg,String receiptTypeId,String mode){
    	String flag="NO";
    	String argUpdate[]={effectiveDate,remarks,status,quoteTypeId,policyTypeId,debitTypeId,proposalNoTypeId,dataTransferTypeId,transferTypeId,claimTypeId,customerTypeId,applicationTypeId,openCoverNoTypeId,certificateSlNoTypeId,certificateNoTypeId,headerImg,footerImg,signImg,stampImg,receiptTypeId,branchCode,productId};
    	String qryInsert="insert into BRANCH_DETAIL(SNO__,BRANCH_CODE,AMEND_ID,PRODUCT_ID,EFFECTIVE_DATE,REMARKS,STATUS,QUOTE_TYPE_ID,POLICY_TYPE_ID,DEBIT_TYPE_ID,PROPOSAL_NO_TYPE_ID,DATA_TRANSFER_TYPE_ID,TRANSFER_TYPE_ID,CLAIM_TYPE_ID,CUSTOMER_TYPE_ID,APPLICATION_TYPE_ID,OPENCOVER_NO_TYPE_ID,CERTIFICATE_SLNO_TYPE_ID,CERTIFICATE_NO_TYPE_ID,HEADER_IMG,FOOTER_IMG,SIGN_IMG,STAMP_IMG,RECEIPT_TYPE_ID)VALUES(?,?,'0',?,?,?,?,?,?,?,,?,?,?,?,?,?,?,,?,?,?,?,?,?,?)";
    	String qryUpdate="update BRANCH_DETAIL set EFFECTIVE_DATE=?,REMARKS=?,STATUS=?,QUOTE_TYPE_ID=?,POLICY_TYPE_ID=?,DEBIT_TYPE_ID=?,PROPOSAL_NO_TYPE_ID=?,DATA_TRANSFER_TYPE_ID=?,TRANSFER_TYPE_ID=?,CLAIM_TYPE_ID=?,CUSTOMER_TYPE_ID=?,APPLICATION_TYPE_ID=?,OPENCOVER_NO_TYPE_ID=?,CERTIFICATE_SLNO_TYPE_ID=?,CERTIFICATE_NO_TYPE_ID=?,HEADER_IMG=?,FOOTER_IMG=?,SIGN_IMG=?,STAMP_IMG=?,RECEIPT_TYPE_ID=? where BRANCH_CODE=? and PRODUCT_ID=?";
    	try{
    		String maxsno=runner.singleSelection("select max(sno__+1) from BRANCH_DETAIL");
    		String argInsert[]={maxsno,branchCode,productId,effectiveDate,remarks,status,quoteTypeId,policyTypeId,debitTypeId,proposalNoTypeId,dataTransferTypeId,transferTypeId,claimTypeId,customerTypeId,applicationTypeId,openCoverNoTypeId,certificateSlNoTypeId,certificateNoTypeId,headerImg,footerImg,signImg,stampImg,receiptTypeId};
    		
    	if(mode.equalsIgnoreCase("create")){
    		runner.multipleInsertion(qryInsert,argInsert);
    		flag="YES";
    	}
    	else if(mode.equalsIgnoreCase("edit")){
    		runner.multipleUpdation(qryUpdate, argUpdate);
    		flag="YES";
    	}
    	}
    	catch(Exception e){
    	e.printStackTrace();    	
    	}
    	return flag;
    }
}
package com.maan.report.service;

import java.io.File;
import java.util.List; 
import java.util.Map;

import com.maan.report.ReportAction;
import com.maan.report.dao.ReportDAO;

public class ReportService {

	ReportDAO report=new ReportDAO();
	
	public List<Map<String, Object>> getReportList(String loginId,String productId,String issuer,String menuType,String openCoverNo,String startDate,String endDate, String quoteNo, String policyNo,String searchBy,String searchValue, String searchField,String searchString,String searchOper, String branchCode)
	{
		return report.getReportList(loginId, productId, issuer, menuType,openCoverNo,startDate,endDate, quoteNo, policyNo,searchBy,searchValue, searchField, searchString, searchOper, branchCode);
	}
	public List<Map<String, Object>> getUserList(String loginId,String productId,String issuer, String searchBy, String branchCode)	
	{
		return report.getUserList(loginId, productId, issuer, searchBy, branchCode);
	}
	public List<Map<String, Object>> getDeclarationPolicyList(String policyNo)	
	{
		return report.getDeclarationPolicyList(policyNo);
	}
	public List<Map<String, Object>> getLapsedReason(String branchCode)	
	{
		return report.getLapsedReason(branchCode);
	}
	public List<Map<String, Object>> getLapsedQuote(String quoteNo,String productId)	
	{
		return report.getLapsedQuote(quoteNo,productId);
	}
	public int updateLabsed(String quoteNo,String remarks,String loginId,String productId)
	{
		return report.updateLabsed(quoteNo, remarks, loginId,productId);
	}
	public int activeLapsed(String quoteNo, String productId)	
	{
		return report.activeLapsed(quoteNo,productId);
	}
	public List<Map<String, Object>> getSearchResult(ReportAction action, String userType)	
	{
		return report.getSearchResult(action, userType);
	}
	public List<Map<String, Object>> getEndTypeList(String productId)	
	{
		return report.getEndTypeList(productId);
	}
	public List<Map<String, Object>> getConstantList(String categoryId, String branchCode)	
	{
		return report.getConstantList(categoryId, branchCode);
	}
	public List<Map<String, Object>> getEndtPolicyInfo(String branchCode, String policyNo)	
	{
		return report.getEndtPolicyInfo(branchCode, policyNo);
	}
	public List<Map<String, Object>> getPolicyEndtTypeList(String endtType)	
	{
		return report.getPolicyEndtTypeList(endtType);
	}
	public String getOriginalPolicyNo(String openCoverNo){
		return report.getOriginalPolicyNo(openCoverNo);
	}
	public int[] getTRPortfolio(String loginId,String productId,String issuer,String openCoverNo){
		return report.getTRPortfolio(loginId,productId,issuer,openCoverNo);
	}
	public int[] getTRLapsed(String loginId,String productId,String issuer,String openCoverNo){
		return report.getTRLapsed(loginId,productId,issuer,openCoverNo);
	}
	public int[] getTRExisting(String loginId,String productId,String issuer,String openCoverNo){
		return report.getTRExisting(loginId,productId,issuer,openCoverNo);
	}
	public int[] getTRUnapproved(String loginId,String productId,String issuer,String openCoverNo){
		return report.getTRUnapproved(loginId,productId,issuer,openCoverNo);
	}
	public int[] getTRApproved(String loginId,String productId,String issuer,String openCoverNo){
		return report.getTRApproved(loginId,productId,issuer,openCoverNo);
	}
	public int[] getTRReject(String loginId,String productId,String issuer,String openCoverNo){
		return report.getTRReject(loginId,productId,issuer,openCoverNo);
	}
	public List getSingleInfo(String option, String[] args) {
		return report.getSingleInfo(option,args);
	}
	public String getEndtQuoteNo(String quoteNo) {
		return report.getEndtQuoteNo(quoteNo);
	}
	public Map<String ,Object> getQuoteInfo(String quoteNo) {
		return report.getQuoteInfo(quoteNo);
	}
	public void getUpdateEndtStatus(String quoteNo, String cancelRemarks) {
		report.getUpdateEndtStatus(quoteNo, cancelRemarks);
	}
	public List getLcUploadDetails(String policyNo) {
		return report.getLcUploadDetails(policyNo);
	}
	public void insertLcFileDtls(String policyNo,String loginId,List<String> uploadFileName,File fileToCreate,List<String> lcdocremarks,String lcFilePath,List<File> upload){
		report.insertLcFileDtls(policyNo,loginId,uploadFileName,fileToCreate,lcdocremarks,lcFilePath,upload);
	}
	public List<Map<String, Object>> getLcFileList(String policyNo){
		return report.getLcFileList(policyNo);
	}
	public void deleteLcFile(String policyNo,String uploadId){
		report.deleteLcFile(policyNo,uploadId);
	}
	public List getLcUploadPolicy(String policyNo) {
		return report.getLcUploadPolicy(policyNo);
	}
	public List getLcUploadPloDtls(String policyNo) {
		return report.getLcUploadPloDtls(policyNo);
	}
	public List<Map<String, Object>> getAllDocuments(String policyNo) {
		return report.getAllDocuments(policyNo);
	}
	public void updateVatRegNo(String custMissippiCode, String vatRegNo) {
		report.updateVatRegNo( custMissippiCode,  vatRegNo);
		
	}
}

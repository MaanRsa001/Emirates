package com.maan.quotation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.maan.common.exception.BaseException;
import com.maan.quotation.dao.DeclarationDAO;
import com.maan.services.policyInfo;

public class DeclarationService {

	DeclarationDAO dec=new DeclarationDAO();
	public List<Map<String, Object>> getDeclarationList(String option, String reqFrom, String[] args) throws  BaseException
	{		
		return dec.getDeclarationList(option,reqFrom, args);		
	}
	public List<Map<String, Object>> getDeclarationList(String option, String[] args) throws  BaseException
	{		
		return dec.getDeclarationList(option,args);		
	}
	public List<Map<String, Object>> getDeclarationList(List<Object> selectedQuote) throws  BaseException
	{		
		return dec.getDeclarationList(selectedQuote);		
	}
	public List<Map<String, Object>> policyGeneration(String option, String[] args) throws  BaseException
	{		
		return dec.getDeclarationList(option,args);		
	}
	public int updatePolicyInfo(List<Object> selectedQuote, String generateStatus, String stampStatus)throws  BaseException{
		 return dec.updatePolicyInfo(selectedQuote,generateStatus,stampStatus);
	}
	public String policyGeneration(String loginId, String productId, String openCoverNo,List<Object> selectedQuote,String branchCode, String rubberStamp, String noteType, String paymentMode, String currencyId, String debitCustomerId, String currencyValue, String issuer)throws  BaseException{
		HashMap<String, String> map=new HashMap<String, String>();
		String quoteNos="";
		List<String> validateFields=null;
		List<String> validateExcessPremium=null;
		if(selectedQuote!=null && !selectedQuote.isEmpty())
		{
			for(int i=0;i<selectedQuote.size();i++){
				map.put(""+(i+1),(String)selectedQuote.get(i));
				quoteNos=quoteNos+selectedQuote.get(i)+",";
				map.put("quote"+(i+1),(String)selectedQuote.get(i));
			}
			quoteNos=quoteNos.substring(0,(quoteNos.length()-1));
			map.put("MultiQuoteStatus","YES");
			map.put("conCatQuotes",quoteNos);
			map.put("count",""+selectedQuote.size());
		}
		else{
			map.put("MultiQuoteStatus","NIL");
		}/*
		return new policyInfo().PolicyGenerationMulti("", loginId, "NO", "NO", "YES", "NO", "NO", productId, openCoverNo, map, branchCode, "", "N", rubberStamp, branchCode, noteType, paymentMode, currencyId, debitCustomerId, currencyValue, issuer);
	}*/
		 
		 return new policyInfo().PolicyGenerationMulti("", loginId, "NO", "NO", "YES", "NO", "NO", productId, openCoverNo, map, branchCode, "", "N", rubberStamp, branchCode, noteType, paymentMode, currencyId, debitCustomerId, currencyValue, issuer);
		 
	}
	public List<String>  validateExcessPremium(List<Object> selectedQuotes){
		return dec.validateExcessPremium(selectedQuotes);
	}
	public List<String> validateFields(List<Object> selectedQuotes){
		return dec.validateFields(selectedQuotes);
	}
}

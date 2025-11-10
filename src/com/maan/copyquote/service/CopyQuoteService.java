package com.maan.copyquote.service;

import java.util.List;
import java.util.Map;

import com.maan.copyquote.dao.CopyQuoteDAO;

public class CopyQuoteService {
	
CopyQuoteDAO copyquote=new CopyQuoteDAO();
	public List<Map<String, Object>> getCopyQuoteSearch(String type,String quoteNo,String openCoverNo,String productId)
	{
		return copyquote.getCopyQuoteSearch(type, quoteNo,openCoverNo,productId);
	}
	public Map<String, Object> copyQuote(String loginId,String quoteNo, String type, String typeId, String issuer)
	{
		return copyquote.copyQuote(loginId, quoteNo, type, typeId, issuer);
	}

}

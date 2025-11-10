package com.maan.webservice.dao;

import java.util.List;

import com.maan.common.*;
import com.maan.services.util.runner;

public class QuotationLoadDAO 
{
	private String sql = "SELECT * FROM LOAD_RESPONSE";

	public List getList(){
		List result = runner.getResultList(sql);
		return result;
	}
	
} 
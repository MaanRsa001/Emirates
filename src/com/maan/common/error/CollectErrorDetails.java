package com.maan.common.error;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.maan.services.util.runner;

public class CollectErrorDetails {
	public static StringBuffer getErrorInfo(int productId,int stageId, List errorCodeList)
	{
		String[][] result=null;
		StringBuffer errorlist=new StringBuffer();
		try
		{
			String qry = "select ERROR_ID,ERROR_DESC from ERROR_MASTER where STAGE_ID="+stageId+" and PRODUCT_ID="+productId+"";
			result=runner.multipleSelection(qry);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(result.length>0)
		{
		for (int i = 0; i < result.length; i++) {
			if(errorCodeList.contains(result[i][0]))
			{
				errorlist.append("* "+result[i][1]+"<br>");
			}
		}
		}
		return errorlist;
	}
}

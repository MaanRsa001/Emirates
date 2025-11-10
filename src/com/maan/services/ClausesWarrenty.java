package com.maan.services;
import com.maan.services.util.runner;
public class ClausesWarrenty 
{
	public String[][] getClause(String loginBra) 
	{
		String[][] clauses = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try 
		{
			args[0] = loginBra;
			sql = "select clauses_id,clauses_description from clauses_master where status='R' and BRANCH_CODE=?";
			clauses = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return clauses;
	}
}
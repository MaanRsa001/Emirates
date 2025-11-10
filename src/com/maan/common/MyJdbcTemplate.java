/*
 * Created on Jan 16, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.maan.common;

import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.maan.DBCon.*;

/**
 * Abstract class from which every entity/usecase-specific DAO should extend.
 * Offers a variety of database related functions like getting a connection,
 * query execution, query execution embedded with paging logic, updation etc.
 */
 
public class MyJdbcTemplate {
	private static final Logger logger = LogUtil.getLogger(MyJdbcTemplate.class);
	protected JdbcTemplate mytemplate;

	public MyJdbcTemplate() {
		try {
			mytemplate = DBConnection.getInstance().gettemplate();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public JdbcTemplate getMytemplate() {
		return mytemplate;
	}

	public void setMytemplate(final JdbcTemplate mytemplate) {
		this.mytemplate = mytemplate;
	}
	public String getQuery(String key){
		String query;
		query=ResourceLocator.getInstance().getDBBundle().getString(key);
		return query;
		
	}
}
package com.maan.admin.DAO;

import java.util.Vector;
import java.io.*;
import com.maan.services.util.runner;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.password.passwordEnc;

public class admLoginNew extends MyJdbcTemplate
{
	private String user = "";
	private String nuuser = "";
	private String pass = "";
	private String nupass = "";
	private String nurepass = "";
	private String nusrnam = "";
	private String cont_no = "";
	private PrintWriter out = null;
	private int sdd = 0;
	private int smm = 0;
	private int syear = 0;
	private int edd = 0;
	private int emm = 0;
	private int eyear = 0;

	private String[] rght = new String[0];	

	public void setUser(String user) {
		this.user = user;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getUser() {
		return this.user;
	}
	public void setNuuser(String nuuser) {
		this.nuuser = nuuser;
	}
	public String getNuuser() {
		return this.nuuser;
	}
	public void setNusrnam(String nusrnam) {
		this.nusrnam = nusrnam;
	}
	public String getNusrnam() {
		return this.nusrnam;
	}
	public void setCont_no(String cont_no) {
		this.cont_no = cont_no;
	}
	public String getCont_no() {
		return this.cont_no;
	}
	public void setNupass(String nupass) {
		this.nupass = nupass;
	}
	public void setSdd(int sdd) {
		this.sdd = sdd;
	}
	public void setSmm(int smm) {
		this.smm = smm;
	}
	public void setSyear(int syear) {
		this.syear = syear;
	}
	public void setEdd(int edd) {
		this.edd = edd;
	}
	public void setEmm(int emm) {
	   this.emm = emm;
	}
	public void setEyear(int eyear) {
		this.eyear = eyear;
	}
	public String getNupass() {
		return this.nupass;
	}
	public void setNurepass(String nurepass) {
		this.nurepass = nurepass;
	}
	public void setRght(String[] rght) {
		this.rght = rght;
	}
	public String[] getRght() {
		return this.rght;
	}
	public String getNurepass() {
		return this.nurepass;
	}
	public int getSdd() {
		return this.sdd;
	}
	public int getSmm() {
		return this.smm;
	}
	public int getSyear() {
		return this.syear;
	}
	public int getEdd() {
		return this.edd;
	}
	public int getEmm() {
		return this.emm;
	}
	public int getEyear() {
		return this.eyear;
	}
	public String getPass() {
		return this.pass;
	}
	public void setOut(PrintWriter out)
	{
		this.out = out;
	}

	public PrintWriter getOut() {
		return this.out;
	}
	public void invdses(String ses, String usr) 
	{
        String[] args = new String[3];
        args[0] = "N";
        args[1] = usr;
        args[2] = ses;
		String sql = "update session_master set status = ?  where username = ? and session_id = ?";
		try{
             runner.multipleUpdation(sql,args);
		}
		catch (Exception e) {
            e.printStackTrace();
		}		
	}

	public void strtSession(String ses, String usr) 
	{
        String[] args = new String[3];
        args[0] = ses;
        args[1] = usr;
        args[2] = "Y";
        String temp = "sysdate";
		//temp = runner.getSysdate(runner.getuserBranch(usr));
		String sql = "insert into session_master values(?,?,"+temp+","+temp+",?)";
		try {
			runner.multipleInsertion(sql,args);			
		}
        catch (Exception e) {
            e.printStackTrace();
		}		
	}

	public boolean validate() throws Exception 
	{			
        String[] args = new String[3];
        args[0] = user;
        args[1] = pass;
        args[2] = "Y";
		String sql = "select login_id from login_master where login_id = ? and password = ? and status = ?";
        String[][] result = new String[0][0];
		boolean available = false;
		try{
            result = runner.multipleSelection(sql,args);
            result = result==null?new String[0][0]:result;
            if(result.length > 0){
      			available = true;
            }             
		}
		catch(Exception e){	
            e.printStackTrace();
        }	
		return available;
	}

	public String nuname() throws Exception 
	{
        String[] args = new String[2];
        args[0] = nuuser ;
        args[1] = "Y";
		String sql = "select login_id from login_master where login_id = ?  and status = ?";
        String[][] result = new String[0][0];
		try{
			result = runner.multipleSelection(sql,args);
            result = result==null?new String[0][0]:result;

            if(result.length > 0){
                  sql = "true";
            }
            else{
                sql = "false";
            }
			//sql = "" + un;
		}
		catch(Exception e){	
            e.printStackTrace();
        }		
		return sql;
	}

	public boolean uname() throws Exception 
	{		
        String[] args = new String[2];
        args[0] = user;
        args[1] = "Y";
        String[][] result = new String[0][0];
		String sql = "select login_id from login_master where login_id = ? and status = ?";
		boolean un =false;
		try{
            result = runner.multipleSelection(sql,args);
            result = result==null?new String[0][0]:result;

            if(result.length >0){
                un = true;
            }            			
		}
		catch(Exception e){	
            e.printStackTrace();
        }
		return un;
	}

	public String LogStatus() throws Exception 
	{		
		String status = "";
        String[] args = new String[1];
        args[0] = user;
		String sql = "select status from login_master where login_id = ?";		
        String[][] result = new String[0][0];
		try{
			result = runner.multipleSelection(sql,args);
            result = result==null?new String[0][0]:result;
            if(result.length > 0){
                	status = result[0][0];
            }	
		}
		catch(Exception e){	
            e.printStackTrace();
        }		
		return status;
	}

	public Vector getDispName(String user) 
	{
		Vector dn = new Vector();
        String[] args = new String[1];
        args[0] = user;
		String sql = "select user_type, display_name from login_master where login_id = ?";		
        String[][] result = new String[0][0];
		try {
			result = runner.multipleSelection(sql,args);
            result = result==null?new String[0][0]:result;

            for(int i=0;i<result.length;i++){
                dn.add(result[i][0]);
				dn.add(result[i][1]);
            }			
		}
		catch (Exception e) {
            e.printStackTrace();
		}	
		return dn;
	}

	/*public String[][] rights(String user) {
        
		String sql = "select rights,status from login_master where login_id='"	+ user + "'";
		String[][] record = new String[0][0];
		String newRights[][] = new String[0][0];
		String Rghts[][] = new String[0][0];		
		int a = 0;
        try {
			Smt = connection.createStatement();
			QueryBuilder qry = new QueryBuilder(Smt);
			record = qry.getResultSet(sql);
			if (record.length > 0) 
			{
				CroselConstantBean constant = new CroselConstantBean();
				String arr[] = constant.split(record[0][0], "~");
				String queryStr = constant.queryFormat(arr).toString();
				String query = "select right_name,concat(link,'?gid=',group_id),remarks from user_rights where id in ("
						+ queryStr + ") order by id";
				Rghts = qry.getResultSet(query);
			}
		}

		catch (Exception e) {
		}		
		return Rghts;
	} */

	/*public StringBuffer vhed(String user, Connection con) 
	{		
		StringBuffer abc = new StringBuffer();
		String[][] newRights = new String[0][0];
		try {
			Smt = connection.createStatement();
			newRights = rights(user);
			if (newRights.length != 0) 
			{
				abc.append("<table width='350' border='0' cellspacing='0' cellpadding='0' align='center'><tr bgcolor='#FFFFFF'><td><table bordercolorlight='#666666' bordercolordark='#CCCCCC' width='100%' border='1' cellspacing='3' cellpadding='0' align='center'><tr background='../images/cell_head1.gif'> <td background='../images/cell_head1.gif' class='tb' width = '100%' align = 'center' height = '30'><font color = '#FFFFFF'>Select the Operation to be Performed</font></td></tr>");
				for (int i = 0; i < newRights.length; i++) 
				{
					if (newRights[i][2].equalsIgnoreCase("head")) 
					{
						abc.append("<tr background='/sell/images/cell_head1.gif' ><td height = '30' align = 'center' class = 't' bgcolor = '#E5E5E5' ><a href = "
										+ newRights[i][1]
										+ "&user="
										+ user
										+ " onmouseover = \"status='"
										+ newRights[i][0]
										+ "';return true\" onMouseOut = \"status=''\"><font color = '#000066'>"
										+ newRights[i][0]
										+ "</font></a></td></tr> ");
					}
				}
				abc.append("</table>");
			}
		} catch (Exception e) {
			abc.append("Exception e : " + e);
		}		
		return abc;
	} */

	/*public StringBuffer subhd(String gid, String selname, String user) 
	{	
		Calendar sysDate = Calendar.getInstance();
		DateFunction date = new DateFunction();
		StringBuffer subtbl = new StringBuffer();
		String subhed[][] = new String[0][0];
		String ds_img, sql, chpwd;
		sql = ds_img = chpwd = "";
		int dtdiff;
		try{
			Smt = connection.createStatement();
			QueryBuilder qry = new QueryBuilder(Smt);
			ResultSet rsdt = Smt.executeQuery("select date_format(pass_dt,'%d-%m-%Y') from login_master where login_id='"
							+ user + "'");
			dtdiff = (int) date.getDayDifference(date.getCalendar(rsdt.getString(1)), sysDate) + 1;
			if (dtdiff > 3) {
				chpwd = "<a href='#' onclick=\"javascript:window.open('/sell/admin/chpas.jsp?user="
						+ user
						+ "','','width=500,height=300, scrollbars=yes, status=yes')\" onmouseover=\"status='Click to Change Password';return true\" onMouseOut=\"status=''\"><b><font color='#000000'>Change Password</font></b></a>";
			}
			sql = "select rights from login_master where login_id='" + user
					+ "'";
			ResultSet rs = Smt.executeQuery(sql);
			String rrs = rs.getString(1);
			while (rs.next()) 
			{
				CroselConstantBean constant = new CroselConstantBean();
				String arr[] = constant.split(rrs, "~");
				String queryStr = constant.queryFormat(arr).toString();
				sql = "select img from user_rights where remarks='head' and group_id='"
						+ gid + "'";
				ResultSet imgrs = Smt.executeQuery(sql);
				while (imgrs.next()) {
					ds_img = imgrs.getString(1);
				}
				sql = "select right_name,concat(link,'?gid=',group_id),link from user_rights where id in ("
						+ queryStr
						+ ") and group_id = '"
						+ gid
						+ "' and remarks != 'head' order by id";
				subhed = qry.getResultSet(sql);
				if (subhed.length != 0) {
					int a = 100 / subhed.length;
					subtbl.append("<table width='750' border='0' cellspacing='0' cellpadding='0' align='center'><tr><td height='7' colspan='6'><img src='../images/1pix.gif' width='1' height='1'></td></tr><tr><td ><img src='"
									+ ds_img
									+ "' width='116' height='25'></td><td width='650' align='right' class='t'>&nbsp;"
									+ chpwd
									+ "</td></tr><tr bgcolor='#0078B3'><td colspan='2' height='1'><img src='../images/1pix.gif' width='1' height='1'></td></tr> <tr bgcolor='#FFFFFF'><td colspan='6'><table width='100%' border='1' cellspacing='3' cellpadding='0' bordercolorlight='#666666' bordercolordark='#CCCCCC'><tr bgcolor='#E5E5E5' align='center'>");
					for (int i = 0; i < subhed.length; i++) {
						if (subhed[i][2].equalsIgnoreCase("")) {
							if (subhed[i][0].equalsIgnoreCase(selname)) {
								subtbl.append("<td bgcolor='#006699' class='link1' height='18' width = '"
												+ a
												+ "%' align='center'><font color='#FFFFFF'>"
												+ subhed[i][0] + "</font></td>");
							} else {
								subtbl.append("<td bgcolor='#E5E5E5' class='link1' height='18' width = '"
												+ a
												+ "%' align='center'><font color = '#000066'>"
												+ subhed[i][0] + "</font></td>");
							}
						} else {
							if (subhed[i][0].equalsIgnoreCase(selname)) {
								subtbl.append("<td bgcolor='#006699' class='link1' height='18' width='"											+ a
												+ "%' align='center'><a href='"
												+ subhed[i][1]
												+ "&user="
												+ user
												+ "' onmouseover = \"status='"
												+ subhed[i][0]
												+ "';return true\" onMouseOut = \"status=''\"> <font color='#FFFFFF'>"
												+ subhed[i][0]
												+ "</font></a></td>");
							} else {
								subtbl.append("<td bgcolor='#E5E5E5' class='link1' height='18' width='"
												+ a
												+ "%' align='center'><a href='"
												+ subhed[i][1]
												+ "&user="
												+ user
												+ "' onmouseover = \"status='"
												+ subhed[i][0]
												+ "';return true\" onMouseOut = \"status=''\"><font color = '#000066'>"
												+ subhed[i][0]
												+ "</font></a> </td>");
							}
						}
					}
					subtbl.append("</tr></table></td></tr><tr bgcolor='#0078B3'><td colspan='2' height='1'><img src='../images/1pix.gif' width='1' height='1'></td></tr></table>");
				}
			}
			sql = "insert into activity_log values(sysdate(),'" + user + "','"+ selname + "')";
			int trs = Smt.executeUpdate(sql);
		}

		catch (Exception e) 
		{
			subtbl.append("<br>This is the error message : " + e);
		}
		finally
		{
			try
			{
				if(Smt!=null)
					Smt.close();
				if(connection!=null)
					connection.close();
			}
			catch(Exception e)
			{
			}
		}
		return subtbl;
	} */

	/*public StringBuffer vsubhd(String gid, String user) 
	{
		Connection connection = null;
		//com.maan.DBCon.DBConnection  db = new com.maan.DBCon.DBConnection();
		connection = DBConnection.getInstance().getDBConnection();
		StringBuffer sbtbl = new StringBuffer();
		String sbhed[][] = new String[0][0];
		String sql = "";
		try 
		{
			Smt = connection.createStatement();
			QueryBuilder qry = new QueryBuilder(Smt);
			sql = "select rights from login_master where login_id='" + user
					+ "'";
			ResultSet rs = Smt.executeQuery(sql);
			String rrs = rs.getString(1);
			while (rs.next()) 
			{
				CroselConstantBean constant = new CroselConstantBean();
				String arr[] = constant.split(rrs, "~");
				String queryStr = constant.queryFormat(arr).toString();

				sql = "select right_name,concat(link,'?gid=',group_id),link from user_rights where id in ("
						+ queryStr
						+ ") and group_id = '"
						+ gid
						+ "' and remarks != 'head' order by id";
				sbhed = qry.getResultSet(sql);
				if (sbhed.length != 0) 
				{
					sbtbl.append("<table width='350' border='0' cellspacing='0' cellpadding='0' align='center'> \n");
					sbtbl.append("<tr bgcolor='#FFFFFF'><td><table bordercolorlight='#666666' bordercolordark='#CCCCCC' width='100%' border='1' cellspacing='3' cellpadding='0' align='center'> \n");
					sbtbl.append("<tr background='../images/cell_head1.gif'><td background='../images/cell_head1.gif' class='tb' width = '100%' align = 'center' height = '30'><font color = '#FFFFFF'>Select the Operation to be Performed</font></td></tr>\n");
					for (int i = 0; i < sbhed.length; i++) 
					{
						if (sbhed[i][2].equalsIgnoreCase("")) 
						{
							sbtbl.append("<tr bgcolor='#E5E5E5' align='center'><td bgcolor='#E5E5E5' class='t' height='25' align='center'><font color = '#000066'>"
											+ sbhed[i][0]
											+ "</font></td></tr>\n");
						} 
						else 
						{
							sbtbl.append("<tr bgcolor='#E5E5E5' align='center'><td bgcolor='#E5E5E5' class='t' height='25' align ='center'><a href='"
											+ sbhed[i][1]
											+ "&user="
											+ user
											+ "' onmouseover = \"status='"
											+ sbhed[i][0]
											+ "';return true\" onMouseOut = \"status=''\"><font color = '#000066'>"
											+ sbhed[i][0]
											+ "</font></a></td></tr>\n");
						}
					}

					sbtbl.append("</td></tr></table></table>\n");
				}
			}
		}
		catch (Exception e) {
			sbtbl.append("<br>This is the error message : " + e);
		}
		finally
		{
			try
			{
				if(connection!=null)
					connection.close();
			}
			catch(Exception e)
			{
			}
		}
		return sbtbl;
	}*/

	/*public String getSN(String gid) 
	{
		String seln = "";
		Connection connection = null;
		//com.maan.DBCon.DBConnection  db = new com.maan.DBCon.DBConnection();
		connection = DBConnection.getInstance().getDBConnection();
		ResultSet rs = null;
		try 
		{
			Smt = connection.createStatement();
			String sql = "select right_name from user_rights where id = '"+ gid + "'";
			seln += sql;
			rs = Smt.executeQuery(sql);
			while (rs.next()) 
			{
				seln = rs.getString(1);
			}
		}

		catch (Exception e) {
			seln += "None<br>" + e;
		}
		finally
		{
			try
			{
				if(Smt!=null)	
					Smt.close();
				if(rs!=null)	
					rs.close();
				if(connection!=null)
					connection.close();
			}
			catch(Exception e)
			{
			}
		}
		return seln;
	}*/

	/*public StringBuffer getRights() 
	{
		
		Connection connection = null;
		//com.maan.DBCon.DBConnection  db = new com.maan.DBCon.DBConnection();
		connection = DBConnection.getInstance().getDBConnection();
		StringBuffer rghTbl = new StringBuffer();
		StringBuffer hdTbl = new StringBuffer();
		String sql = "select right_name,remarks,group_id,id from user_rights where group_id!='99' order by group_id";
		try 
		{
			Smt = connection.createStatement();
			QueryBuilder qry = new QueryBuilder(Smt);
			String[][] subTbl = qry.getResultSet(sql);
			if (subTbl.length > 0) 
			{
				Hashtable rhtht = getRightHash();
				String tempGid = subTbl[0][2];
				hdTbl.append("<table align='center' border='0' width='100%' cellspacing='0' cellpadding=0'><tr><td valign='top'><table border='0' width='99%' cellspacing='0' cellpadding='0'> \n");
				for (int i = 0; i < subTbl.length; i++) 
				{
					if (!tempGid.equalsIgnoreCase(subTbl[i][2])) 
					{
						if (i > 0) 
						{
							hdTbl.append("</table></td><td valign='top'> \n");
						}
						hdTbl.append("<table border='0' width='99%' cellspacing='0' cellpadding='0'> \n");
					}
					if (subTbl[i][1].equalsIgnoreCase("head")) 
					{
						hdTbl.append("<tr><td colspan='2' height = '30' class='tb' background = '../images/cell_head1.gif'><font color = '#FFFFFF'>&nbsp;&nbsp;&nbsp;"
										+ subTbl[i][0] + "</font></td></tr>\n");
					} 
					else if (!(subTbl[i][1].equalsIgnoreCase("nul"))) 
					{
						String checkStr = "";
						if (rhtht.get(subTbl[i][3]) != null) {
							checkStr = "checked";
						}
						hdTbl.append("<tr><td align='center' width='20%'><input type='checkbox' "
										+ checkStr
										+ " name='rght' value='"
										+ subTbl[i][3]
										+ "'></td><td width='80%' height = '30' align='left' class='t'>"
										+ subTbl[i][0] + "</td></tr>\n");
					}
					tempGid = subTbl[i][2];
				}
				hdTbl.append("</td></tr></table></td></tr></table>\n");
				rghTbl.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center'><tr><td> \n");
				rghTbl.append(hdTbl + "\n");
				rghTbl.append("</td></tr></table>\n");
			} 
			else 
			{
				rghTbl.append("<br>Cannot Proceed Further");
			}
		} 
		catch (Exception e) 
		{
			rghTbl.append("<br>The Error started here.<br>" + e);
		}
		finally
		{
			try
			{
				if(connection!=null)
					connection.close();
			}
			catch(Exception e)
			{
			}
		}
		return rghTbl;
	}*/
	/*public StringBuffer getERights(Hashtable rghthsh) 
	{
		
		Connection connection = null;
		//com.maan.DBCon.DBConnection  db = new com.maan.DBCon.DBConnection();
		connection = DBConnection.getInstance().getDBConnection();
		StringBuffer rghTbl = new StringBuffer();
		StringBuffer hdTbl = new StringBuffer();
		String sql = "select right_name,remarks,group_id,id from user_rights where group_id!='99' order by group_id, id";
		try 
		{
			Smt = connection.createStatement();
			QueryBuilder qry = new QueryBuilder(Smt);
			String[][] subTbl = qry.getResultSet(sql);
			if (subTbl.length > 0) 
			{
				Hashtable rhtht = rghthsh;
				String tempGid = subTbl[0][2];
				hdTbl.append("<table align='center' border='0' width='100%' cellspacing='0' cellpadding=0'><tr><td valign='top'><table border='0' width='99%' cellspacing='0' cellpadding='0'> \n");
				for (int i = 0; i < subTbl.length; i++) 
				{
					if (!tempGid.equalsIgnoreCase(subTbl[i][2])) 
					{
						if (i > 0) 
						{
							hdTbl.append("</table></td><td valign='top'> \n");
						}
						hdTbl.append("<table border='0' width='100%' cellspacing='0' cellpadding='0'> \n");
					}
					if (subTbl[i][1].equalsIgnoreCase("head")) 
					{
						hdTbl.append("<tr><td colspan='2' background='../images/cell_head1.gif' align = 'center' height='30' class='tb'><font color = '#FFFFFF'>"
										+ subTbl[i][0] + "</font></td></tr>\n");
					}
					else if (!(subTbl[i][1].equalsIgnoreCase("nul"))) 
					{
						String checkStr = "";
						if (rhtht.get(subTbl[i][3]) != null) {
							checkStr = "checked";
						}
						hdTbl.append("<tr><td align='center' width='5%'><input type='checkbox' name='rght' "
										+ checkStr
										+ " value='"
										+ subTbl[i][3]
										+ "'></td><td width='95%' height = '35' align='left' class='t'>"
										+ subTbl[i][0] + "</td></tr>\n");
					}
					tempGid = subTbl[i][2];
				}
				hdTbl.append("</td></tr></table></td></tr></table>\n");
				rghTbl.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center'><tr><td> \n");
				rghTbl.append(hdTbl + "\n");
				rghTbl.append("</td></tr></table>\n");
			} 
			else 
			{
				rghTbl.append("<br>Cannot Proceed Further");
			}
		} 
		catch (Exception e) 
		{
			rghTbl.append("<br>The Error started here.<br>" + e);
		}
		finally
		{
			try
			{
				if(connection!=null)
					connection.close();
			}
			catch(Exception e)
			{
			}
		}
		return rghTbl;
	}

	public Hashtable getRightHash() 
	{
		Hashtable hash = new Hashtable();

		for (int i = 0; i < rght.length; i++) {
			hash.put(rght[i], rght[i]);
		}

		return hash;
	}

	public StringBuffer creaUser(String[] rgt) 
	{
		Connection connection = null;
		//com.maan.DBCon.DBConnection  db = new com.maan.DBCon.DBConnection();
		connection = DBConnection.getInstance().getDBConnection();
		Hashtable hash1 = new Hashtable();
		Hashtable hash2 = new Hashtable();
		Hashtable finlRghts = new Hashtable();
		String finrght = "";
		StringBuffer frght = new StringBuffer();
		String sql = "select id,group_id from user_rights where remarks!='head' order by group_id";
		try 
		{
			Smt = connection.createStatement();
			QueryBuilder qry = new QueryBuilder(Smt);
			String[][] arhd = qry.getResultSet(sql);
			if (arhd.length > 0) 
			{
				for (int i = 0; i < arhd.length; i++) 
				{
					hash2.put(arhd[i][0], arhd[i][1]);
				}
				for (int i = 0; i < rght.length; i++) 
				{
					if (frght.toString().indexOf(hash2.get(rght[i]) + "~") == -1) 
					{
						frght.append(hash2.get(rght[i]) + "~");
					}
				}
			}
		} catch (Exception e) {
			frght.append("<br>There is an error. The error is : " + e);
		}
		finally
		{
			try
			{
				if(connection!=null)
					connection.close();
			}
			catch(Exception e)
			{
			}
		}
		return frght;
	}

	public String insUser(StringBuffer fr) 
	{
		Connection connection = null;
		//com.maan.DBCon.DBConnection  db = new com.maan.DBCon.DBConnection();
		connection = DBConnection.getInstance().getDBConnection();

		String s = "";
		String newstr = fr.toString();
		String insrght = newstr;
		s = s + nuuser + nupass + insrght + nusrnam;
		try 
		{
			CroselConstantBean constant = new CroselConstantBean();
			String arr[] = constant.split(newstr, "~");
			Hashtable fulhash = getRightHash();

			String retEight = (String) fulhash.get("8");
			String retTwoThree = (String) fulhash.get("23");
			String retEighten = (String) fulhash.get("18");
			if (retEight != null && retEight.equalsIgnoreCase("8")) {
				fr.append("~9");
				// fulhash.put("9","9");
			}

			if (retTwoThree != null && retTwoThree.equalsIgnoreCase("23")) {
				fr.append("~24~25~26");
				/*
				 * fulhash.put("24","24"); fulhash.put("25","25");
				 * fulhash.put("26","26");
				 */
			//}

			/*if (retEighten != null && retEighten.equalsIgnoreCase("18")) {
				fr.append("~19~20");
				/*
				 * fulhash.put("19","19"); fulhash.put("20","20");
				 */
			/*}

			// insrght = insrght + "~" + constant.toString(fulhash,"~");
			String sql = "insert into login_master (login_id,password,user_type,rights,status,username,pass_dt) values('"
					+ nuuser
					+ "','"
					+ nupass
					+ "','user','"
					+ fr /* insrght */
				/*	+ "','FL','" + nusrnam + "',sysdate())";
			Smt = connection.createStatement();
			Smt.executeUpdate(sql);
		} catch (Exception e) {
			s = s + "Exception is : " + e;
		}
		finally
		{
			try
			{
				if(Smt!=null)
					Smt.close();
				if(connection!=null)
					connection.close();
			}
			catch(Exception e)
			{
			}
		}
		return s;
	}

	public String modUser(StringBuffer fr, String chk) 
	{
		Connection connection = null;
		//com.maan.DBCon.DBConnection  db = new com.maan.DBCon.DBConnection();
		connection = DBConnection.getInstance().getDBConnection();

		String s, chkd, sql;
		s = chkd = sql = "";
		String newstr = fr.toString();
		String insrght = newstr;
		s = s + nuuser + nupass + insrght + nusrnam;
		try 
		{
			CroselConstantBean constant = new CroselConstantBean();
			String arr[] = constant.split(newstr, "~");
			Hashtable fulhash = getRightHash();
			String retEight = (String) fulhash.get("8");
			String retTwoThree = (String) fulhash.get("23");
			String retEighten = (String) fulhash.get("18");

			if (retEight != null && retEight.equalsIgnoreCase("8")) 
			{
				fr.append("~9");
				// fulhash.put("9","9");
			}
			if (retTwoThree != null && retTwoThree.equalsIgnoreCase("23")) 
			{
				fr.append("~24~25~26");
				/*
				 * fulhash.put("24","24"); fulhash.put("25","25");
				 * fulhash.put("26","26");
				 */
			/*}
			if (retEighten != null && retEighten.equalsIgnoreCase("18")) {
				fr.append("~19~20");
				/*
				 * fulhash.put("19","19"); fulhash.put("20","20");
				 */
			/*}
			// insrght = insrght + "~" + constant.toString(fulhash,"~");

			if ((chk.equalsIgnoreCase("loc")) || (chk.equalsIgnoreCase("l"))) {
				chkd = "Y";
			} else if (chk.equalsIgnoreCase("n")) {
				chkd = "N";
			} else if (chk.equals("")) {
				sql = "select status from login_master where login_id='"
						+ nuuser + "'";
				ResultSet rs = Smt.executeQuery(sql);
				while (rs.next()) {
					chkd = rs.getString(1);
				}
			} else {
				chkd = chk;
			}
			sql = "update login_master set rights = '" + fr + "',status = '"
					+ chkd + "',username = '" + nusrnam
					+ "' where login_id = '" + nuuser + "'";
			Smt = connection.createStatement();
			Smt.executeUpdate(sql);
		} catch (Exception e) {
			s = s + "<br>Exception is : " + e + "<br>";
		}
		finally
		{
			try
			{
				if(Smt!=null)
					Smt.close();
				if(connection!=null)
					connection.close();
			}
			catch(Exception e)
			{
			}
		}
		return s;
	}

	public StringBuffer getactiv(String sd, String ed) 
	{
		Connection connection = null;
		//com.maan.DBCon.DBConnection  db = new com.maan.DBCon.DBConnection();
		connection = DBConnection.getInstance().getDBConnection();

		StringBuffer actTbl = new StringBuffer();
		try 
		{
			Smt = connection.createStatement();

			DateFunction df = new DateFunction();

			String sds = df.changeDateFormatDMY(sd);
			String eds = df.changeDateFormatDMY(ed);
			String sql = "select * from activity_log where time_stamp between date_sub('"
					+ sd
					+ "', interval -1 day) and date_add('"
					+ ed
					+ "', interval 1 day)";

			QueryBuilder qry = new QueryBuilder(Smt);

			String[][] bldTbl = qry.getResultSet(sql);
			if (bldTbl.length > 0) 
			{
				actTbl.append("<br><center><input type='image' onclick=window.open('prnactivity.jsp?sd="
								+ sd
								+ "&ed="
								+ ed
								+ "','new','width=750,height=450,scrollbars=yes'); src='../images/print_btn.gif' border='0' alt='Click Here to Print' name='image'></center><br>\n");
				actTbl.append("<table width='600' border='0' cellspacing='0' cellpadding='1' align='center'><tr><td bgcolor='#0078B3'> \n");
				actTbl.append("<table align='center' border='0' width='100%' cellspacing='0' cellpadding=0'><tr><td bgcolor='#FFFFFF' align='center' colspan='4' height='27' class='tb'>Start Date : "
								+ sds
								+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;End Date : "
								+ eds + " </td></tr> \n");
				actTbl.append("<tr height='1'><td></td></tr><tr> <td><table align='center' border='0' width='100%' cellspacing='0' cellpadding=0'> \n");
				actTbl.append("<tr><td bgcolor='#FFFFFF' align='center' width='10%' height='27' class='tb'>S. No.</td><td bgcolor='#FFFFFF' align='center' width='33%' height='27' class='tb'>User</td><td bgcolor='#FFFFFF' width='30%' height='27' align='center' class='tb'>Date and Time</td><td bgcolor='#FFFFFF' width='27%' height='27' align='center' class='tb'>Action Performed</td></tr><tr><td colspan='4' height='1' bgcolor='#0078B3'></td></tr>\n");

				int j = 1;
				for (int i = 0; i < bldTbl.length; i++) 
				{
					actTbl.append("<tr><td height='27' class='t' bgcolor='#FFFFFF' align='right' width='10%'>"
									+ j
									+ ".</td><td height='27' class='t' bgcolor='#FFFFFF' align='center' width='33%'>"
									+ bldTbl[i][1] + "</td>\n");
					actTbl.append("<td height='27' class='t' bgcolor='#FFFFFF' align='center' width='30%'>"
									+ bldTbl[i][0]
									+ "</td><td height='27' class='t' align='center' bgcolor='#FFFFFF' width='27%'>"
									+ bldTbl[i][2] + "</td></tr>\n");
					j++;
				}
				actTbl.append("</table></td></tr></table></td></tr></table>\n");
				actTbl.append("<br><center><input type='image' onclick=window.open('prnactivity.jsp?sd="
								+ sd
								+ "&ed="
								+ ed
								+ "','new','width=750,height=450,scrollbars=yes'); src='../images/print_btn.gif' border='0' alt='Click Here to Print' name='image'></center><br>\n");
			}
		}

		catch (Exception e) {
			actTbl.append("<br><br><br>Exception is : " + e);
		}
		finally
		{
			try
			{
				if(connection!=null)
					connection.close();
			}
			catch(Exception e)
			{
			}
		}
		return actTbl;
	}

	public StringBuffer gtactiv(String sd, String ed, Connection con)
			throws Exception 
	{
		Connection connection = null;
		//com.maan.DBCon.DBConnection  db = new com.maan.DBCon.DBConnection();
		connection = DBConnection.getInstance().getDBConnection();

		DateFunction df = new DateFunction();
		StringBuffer actTbl = new StringBuffer();
		String sds = df.changeDateFormatDMY(sd);
		String eds = df.changeDateFormatDMY(ed);
		try 
		{
			Smt = con.createStatement();

			String sql = "select * from activity_log where time_stamp between date_sub('"
					+ sd
					+ "', interval -1 day) and date_add('"
					+ ed
					+ "', interval 1 day) order by time_stamp";

			QueryBuilder qry = new QueryBuilder(Smt);

			String[][] bldTbl = qry.getResultSet(sql);
			if (bldTbl.length > 0) 
			{
				actTbl.append("<table width='400' border='0' cellspacing='0' cellpadding='1' align='center'><tr><td bgcolor='#0078B3'>\n");
				actTbl.append("<table align='center' border='0' width='100%' cellspacing='0' cellpadding=0'><tr><td bgcolor='#FFFFFF' align='center' colspan='4' height='27' class='tb'>Start Date : "
								+ sds
								+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;End Date : "
								+ eds + " </td></tr>\n");
				actTbl.append("<tr><td></td><tr height='1'><td><table align='center' border='0' width='100%' cellspacing='0' cellpadding=0'><tr><td bgcolor='#FFFFFF' align='center' width='10%' height='27' class='tb'>S. No.</td><td bgcolor='#FFFFFF' align='center' width='33%' height='27' class='tb'>User</td><td bgcolor='#FFFFFF' width='30%' height='27' align='center' class='tb'>Date and Time</td><td bgcolor='#FFFFFF' width='27%' height='27' align='center' class='tb'>Action Performed</td></tr><tr><td colspan='4' height='1' bgcolor='#0078B3'></td></tr>\n");
				int j = 1;
				for (int i = 0; i < bldTbl.length; i++) 
				{
					actTbl.append("<tr><td height='27' class='t' bgcolor='#FFFFFF' align='right' width='10%'>"
									+ j
									+ ".</td><td height='27' class='t' bgcolor='#FFFFFF' align='center' width='33%'>"
									+ bldTbl[i][1]
									+ "</td><td height='27' class='t' bgcolor='#FFFFFF' align='center' width='30%'>"
									+ bldTbl[i][0]
									+ "</td><td height='27' class='t' align='center' bgcolor='#FFFFFF' width='27%'>"
									+ bldTbl[i][2] + "</td></tr>\n");
					j++;
				}
				actTbl.append("</table></td></tr></table>\n");
			}
		}

		catch (Exception e) {
			actTbl.append("<br><br><br>Exception is : " + e);
		}
		finally
		{
			try
			{
				if(connection!=null)
					connection.close();
			}
			catch(Exception e)
			{
			}
		}
		return actTbl;
	}*/

	public StringBuffer getUsr() {		
		StringBuffer UsrLst = new StringBuffer();
		try {
			String sql = "select username,status from login_master";		
			String[][] bldTbl = runner.multipleSelection(sql);
			if (bldTbl.length > 0) {
				for (int i = 0; i < bldTbl.length; i++) {
					UsrLst.append("<option value = '" + bldTbl[i][0] + "'>"+ bldTbl[i][0] + "</option>\n");
				}
			}
		}
        catch (Exception e) {
            e.printStackTrace();
		}		
		return UsrLst;
	}

	public String[][] getusers(String nusr) throws Exception 
	{
		String[][] nuusr = new String[0][0];	
        String[] args = new String[1];
        args[0] = nusr;
		String sql = "select password, rights, status, login_id from login_master where username = ?";
		try{	
			nuusr = runner.multipleSelection(sql,args);;
		}
		catch(Exception e){
            e.printStackTrace();
        }		
		return nuusr;
	}

	public boolean chkpas(String pas, String pass, String brahma)throws Exception 
	{		
		passwordEnc pass1 = new passwordEnc();
		String encpass = pass1.crypt(pass.substring(0,3), pass);
		boolean retpas = false;		
		String sql = "";
        String[] args = new String[1];
        args[0] = pas;
		sql = "select password,lpass1,lpass2,lpass3,lpass4,lpass5,lpass6,lpass7,lpass8,lpass9,lpass10 from login_master where login_id = ?";
		try{			
			String[][] upas = runner.multipleSelection(sql,args);
			if (upas.length > 0) {
				upas[0][0] = upas[0][0] == null? " " : upas[0][0];
				upas[0][1] = upas[0][1] == null? " " : upas[0][1];
				upas[0][2] = upas[0][2] == null? " " : upas[0][2];
				upas[0][3] = upas[0][3] == null? " " : upas[0][3];
				upas[0][4] = upas[0][4] == null? " " : upas[0][4];
				upas[0][5] = upas[0][5] == null? " " : upas[0][5];
				upas[0][6] = upas[0][6] == null? " " : upas[0][6];
				upas[0][7] = upas[0][7] == null? " " : upas[0][7];
				upas[0][8] = upas[0][8] == null? " " : upas[0][8];
				upas[0][9] = upas[0][9] == null? " " : upas[0][9];
				upas[0][10] = upas[0][10] == null? " " : upas[0][10];

				if ((encpass.equals(upas[0][0])) || (encpass.equals(upas[0][1])) || (encpass.equals(upas[0][2])) 		|| (encpass.equals(upas[0][3])) || (encpass.equals(upas[0][4])) || (encpass.equals(upas[0][5])) || (encpass.equals(upas[0][6])) || (encpass.equals(upas[0][7])) || (encpass.equals(upas[0][8])) || (encpass.equals(upas[0][9])) || (encpass.equals(upas[0][10]))) {						
					retpas = true;
				} 
				else {
					retpas = false;
				}
			}
		}
		catch(Exception e){			
			e.printStackTrace();
		}		
		return retpas;
	}

	public void cpas(String nusr, String cps, String brahma) throws Exception 
	{
		passwordEnc pass = new passwordEnc();
		String encpass = pass.crypt(cps.substring(0, 3), cps);
		String a = "ne";
		String[][] qry1 = new String[0][0];
        String[] args = new String[1];
        args[0] = nusr;
        String sql = "select password,lpass1,lpass2,lpass3,lpass4,lpass5,lpass6,lpass7,lpass8,lpass9 from login_master where login_id = ?";
		try{			
			qry1 = runner.multipleSelection(sql,args);
			if (qry1.length == 0) {
				a = "upd";
                args = new String[1];
                args[0] =  nusr;
				sql = "select password,lpass1,lpass2,lpass3,lpass4,lpass5,lpass6,lpass7,lpass8,lpass9 from login_master where username = ? ";
				qry1 = runner.multipleSelection(sql,args);				
			}
			if (a.equalsIgnoreCase("ne")) {
                args = new String[13];
                args[0] = qry1[0][9];
                args[1] = qry1[0][8];
                args[2] = qry1[0][7];
                args[3] = qry1[0][6];
                args[4] = qry1[0][5];
                args[5] = qry1[0][4];
                args[6] = qry1[0][3];
                args[7] = qry1[0][2];
                args[8] = qry1[0][1];
                args[9] = qry1[0][0];
                args[10] = encpass;
                args[11] = "Y";
                args[12] = nusr;
				//sql = "update login_master set  lpass5 =  ?,  lpass4 =  ?, lpass3=  ?,lpass2 =  ?,lpass1 =  ?,password =  ?, passdate=DATEADD(day, +45, sysdatetime()) ,status = ? where login_id = ?";
                sql=this.getQuery("UPD_SWAPED_PSW_ID");
			}
            else if (a.equalsIgnoreCase("upd")) {
                args = new String[13];
                args[0] = qry1[0][9];
                args[1] = qry1[0][8];
                args[2] = qry1[0][7];
                args[3] = qry1[0][6];
                args[4] = qry1[0][5];
                args[5] = qry1[0][4];
                args[6] = qry1[0][3];
                args[7] = qry1[0][2];
                args[8] = qry1[0][1];
                args[9] = qry1[0][0];
                args[10] = encpass;
                args[11] = "Y";
                args[12] = nusr;
				//sql = "update login_master set lpass5 = ? , lpass4 = ?,lpass3 = ? ,lpass2 = ?,lpass1 = ? ,password = ? ,pass_dt=sysdatetime(), status = ? where username = ?";
				sql=this.getQuery("UPD_SWAPED_PSW_USER");
			}
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e){
            e.printStackTrace();
        }		
	}
	
	public void cfpas(String nusr, String cps) throws Exception 
	{
        passwordEnc pass = new passwordEnc();
		String encpass = pass.crypt(cps.substring(0, 3), cps);
        String[] args = new String[2];
        args[0] = encpass;
        args[1] = nusr;
        String temp = "sysdate";
		//temp = runner.getSysdate(runner.getuserBranch(nusr));
		try{			
			String sql = "update login_master set password = ? ,pass_dt="+temp+", status='Y' where login_id = ?";            	
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e){
            e.printStackTrace();
        }		
	}

	public void locklo(String user) throws Exception 
	{		
        String[] args = new String[1];
		try{		
            args[0] = user;
			String sql = "update login_master set status='L' where agency_code=(select agency_code from login_master where login_id  = ?)";
			runner.multipleUpdation(sql,args);
            args[0] = user;
			String sql1 = "update personal_info set status='L' where agency_code=(select agency_code from login_master where login_id = ?)";
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e){
            e.printStackTrace();
        }		
	}

	public String[][] getLockedUsers() 
	{
		String[][] qry1 = new String[0][0];		
		try {		
			String sql = "select login_id,password,status from login_master where status='L'";						
			qry1 = runner.multipleSelection(sql);	
		} 
        catch (Exception e) {			
            e.printStackTrace();
		}		   
		return qry1;
	}

	public void activateId() {		
        String[] args = new String[2];
        args[0] = "Y";
        args[1] = user;
		try{			
			String sql = "update login_master set status = ? where login_id = ?";
			runner.multipleUpdation(sql,args);
		} 
        catch (Exception e) {
			e.printStackTrace();
		}		
	}
	//Created by Rajesh For MainNew.jsp under admin on 26/10//
	public String[][] getUserandStatus(String user)
	{		
		String result[][] = new String[0][0];
        String[] args = new String[1];
		try{			
            args[0] = user;
			String sql = "select status,userid from login_master where login_id = ?";			
			result = runner.multipleSelection(sql,args);			
		}
		catch(Exception e){
			e.printStackTrace();
		}		
		return result;
	}
	public String getPassdate(String user)
	{
		String result = "";
        String[] args = new String[1];
        args[0] = user;
		try{
			String sql = "select PASSDATE from login_master where login_id = ?";
			result = runner.singleSelection(sql,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
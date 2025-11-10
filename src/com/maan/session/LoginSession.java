package com.maan.session;

import java.util.Vector;
import java.sql.SQLException;
import com.maan.services.util.runner;
import com.maan.common.util.*;

public class LoginSession
{
   	private String user = "";
	private String nuuser ="";
	private String pass = "";
	private String nupass = "";
	private String nurepass = "";
	private String nusrnam = "";
	private String cont_no = "";
	private String contNumber = "";
	private String employeeId = "";
	private int companyId = 0;

	private int sdd = 0;
	private int smm = 0;
	private int syear = 0;
	private int edd = 0;
	private int emm = 0;
	private int eyear = 0;
	private String[] rght = new String[0];

	boolean available = false;
	private String oldPass = "";
	private String newPass = "";
	private String rNewPass = "";
	private String logExp = "";
	private StringBuffer sError = new StringBuffer();	

	public void setUser(String user){
		this.user = user;
	}
    public String getUser(){
		return this.user;
	}
	public void setEmployeeId(String employeeId){
		  this.employeeId = employeeId;
	}
    public String getEmployeeId(){
		  return employeeId;
	}
	public void setCompanyId(int companyId){
		  this.companyId = companyId;
	}
    public int getCompanyId(){
		  return companyId;
	}     
	public void setPass(String pass){
		this.pass = pass;
	}	
	/*public void setOut(PrintWriter out){
		this.out = out;
	}*/
	public void setNuuser(String nuuser){
		this.nuuser = nuuser;
	}
	public String getNuuser(){
		return this.nuuser;
	}
	public void setNusrnam(String nusrnam){
		this.nusrnam = nusrnam;
	}
	public String getNusrnam(){
		return this.nusrnam;
	}
	public void setCont_no(String cont_no){
		this.cont_no = cont_no;
	}
	public String getCont_no(){
		return this.cont_no;
	}
	public void setContNumber(String contNumber){
		this.contNumber = contNumber;
	}
	public String getContNumber(){
		return this.contNumber;
	}
	public void setNupass(String nupass){
		this.nupass = nupass;
	}
	public void setSdd(int sdd){
		this.sdd = sdd;
	}
	public void setSmm(int smm){
		this.smm = smm;
	}
	public void setSyear(int syear){
		this.syear = syear;
	}
	public void setEdd(int edd){
		this.edd = edd;
	}
	public void setEmm(int emm){
		this.emm = emm;
	}
	public void setEyear(int eyear){
		this.eyear = eyear;
	}
	public String getNupass(){
		return this.nupass;
	}
	public void setNurepass(String nurepass){
		this.nurepass = nurepass;
	}
	public void setRght(String[] rght){
		this.rght = rght;
	}
	public String[] getRght(){
		return this.rght;
	}
	public String getNurepass(){
		return this.nurepass;
	}
	public int getSdd(){
		return this.sdd;
	}
	public int getSmm(){
		return this.smm;
	}
	public int getSyear(){
		return this.syear;
	}
	public int getEdd(){
		return this.edd;
	}
	public int getEmm(){
		return this.emm;
	}
	public int getEyear(){
		return this.eyear;
	}
	public String getPass(){
		return this.pass;
	}

    private String msg="";
	public void setOldPass(String oldPass){
		this.oldPass = oldPass;
	}
	public void setNewPass(String newPass){
		this.newPass = newPass;
	}
	public void setRNewPass(String rNewPass){
		this.rNewPass = rNewPass;
	}
	public String getMsg(){
			return msg;
	}
	public void invdses(String ses, String usr){		
		String sql = "";
        String[] args = new String[2];
        args[0] = usr;
        args[1] = ses;
		try{
			sql = "update session_master set status='N' where login_id = ? and session_id = ?";
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e){	
            e.printStackTrace();
        }
	}

	public void strtSession(String ses, String usr,String ipAdd)
	{	
        String[] args = new String[4];
        args[0] = ses;
        args[1] = usr;
        args[2] = "Y";
        args[3] = ipAdd;  
		String sql = "insert into session_master(session_id,login_id,start_time,end_time,status,sys_ip_address)  values(?,?,SYSDATE,SYSDATE,?,?)";	
		try{			
			runner.multipleInsertion(sql,args);
		}
		catch(Exception e){
           e.printStackTrace();
        }
	}

	public void insertLoginStatus(String usr,String loginStatus,String sessionId)
	{
        String[] args = new String[4];
		if(getInsertStatus(usr) > 0){
			updateSessionId(usr,sessionId,"loggedout");
		}
		else{
            args[0] = usr;
            args[1] = loginStatus;
            args[2] = sessionId;
            args[3] = "Y";
			String sql = "insert into login_status(login_id,log_status,session_id,status)  values(?,?,?,?)";			
			try{				
				runner.multipleInsertion(sql,args);
			}
			catch(Exception e){
				e.printStackTrace();
			}		
		}
	}

	public String getLoginStatus(String usr)
	{		
        String[] args = new String[2];
        args[0] = usr;
        args[1] = "Y";
		String loggedStatus="";
		String sql = "select log_status from login_status where login_id = ? and status = ?";		
		try{	
            loggedStatus =  runner.singleSelection(sql,args);
		}
		catch(Exception e){	
            e.printStackTrace();
        }		
	    return loggedStatus;
	}

	public int getInsertStatus(String usr)
	{
        String[] args = new String[2];
		int loggedStatus=0;
        String res = "";
		String sql = "select count(*) from login_status where login_id = ? and status =  ?";
        args[0] = usr;
        args[1] = "Y";
		try{			
                res =   runner.singleSelection(sql,args);
				loggedStatus = Integer.parseInt(res);
		}
		catch(Exception e){	
			e.printStackTrace();
		}		
		return loggedStatus;
	}

	public void updateSessionId(String usr,String sessionId,String loginStatus)
	{
		String[] args = new String[4];
        args[0] = sessionId;
        args[1] = loginStatus;
        args[2] = usr;
        args[3] = "Y";
		String sql = "update login_status set session_id = ? , log_status = ? " +
				"where login_id = ? and status = ?";		
		try{			
			runner.multipleUpdation(sql,args);			
		}
		catch(Exception e){
           e.printStackTrace();
        }	
	}

	public void updateLoginStatus(String usr,String loginStatus,String sessionId)
	{
        String[] args = new String[3]; 
        args[0] = loginStatus;
        args[1] = usr;
        args[2] = "Y";
		String sql = "update login_status set log_status = ? " +
		" where login_id  = ? and status = ?";		
		try{			
			runner.multipleUpdation(sql,args);			
		}
		catch(Exception e){	
           e.printStackTrace();
        }	
	}

	public void endSession(String ses, String usr)
	{
        String[] args = new String[3];
        args[0] = ses;
        args[1] = usr;
        args[2] =  "Y";
		String sql = "update session_master set end_time=SYSDATE where session_id = ? and login_id = ? and status = ?";		
		try{		
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e){	
            e.printStackTrace();
        }
	}

	public int checkSessionTimeOut(String ses,String usr,String min)
	{			
		int countCheck=0;
        String[] args = new String[3];
        args[0] = ses;
        args[1] = usr;
        args[2] = "Y";
        String res = "";
		String sql ="select count(*) from session_master where session_id = ? and login_id = ? and sysdate >= (end_time + ("+min+"/1440)) and status = ?";
		try{			
                res = runner.singleSelection(sql,args);
                if(res == null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase("") || res.equalsIgnoreCase("0")){
                        countCheck = 0;
                }
                else{
				        countCheck = Integer.parseInt(res);
                }
		}
		catch(Exception e){	
            e.printStackTrace();
        }		
		return countCheck;
	}

	public int checkUserSessionTimeOut(String usr,String min)
	{			
        String res = "";
        int countCheck=0;
		String ses="";
        String[] args = new String[2];
        args[0] = usr;
        args[1] = "Y";
		String sql_user ="select session_id from login_status where login_id = ? and status = ?";		
		try{					
            ses = runner.singleSelection(sql_user,args);
            ses = ses==null?"":ses;

            args = new String[3];
            args[0] = ses;
            args[1] = usr;
            args[2] = "Y";
            sql_user ="select count(*) from session_master where session_id = ? and login_id = ? and sysdate >= (end_time + ("+min+"/1440)) and status = ?";
            res = runner.singleSelection(sql_user,args);

             if(res == null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase("") || res.equalsIgnoreCase("0")){
                        countCheck = 0;
             }
             else{
                 countCheck = Integer.parseInt(res);
             }         
		}
		catch(Exception e){	
            e.printStackTrace();
        }		
		if(countCheck > 0){
			updateLoginStatus(usr,"loggedout",ses);			
		}
		return countCheck;
	}

	public boolean validateSession(String ses, String usr) throws Exception
	{		
        String[] args = new String[3];
        String[][] result = new String[0][0];
        args[0] = usr;
        args[1] = ses;
        args[2] =  "Y";
        String sql = "select login_id, session_id, status from session_master where login_id = ?  and session_id = ? and status = ?";
		try{				
            result = runner.multipleSelection(sql,args);	
            result = result==null?new String[0][0]:result;
			
            if(result.length > 0 ){
				available = true;
            }
			else {
				available = false;
            }
		}
		catch(Exception e){
            e.printStackTrace();
        }		
		return available;
	}

	public boolean sessionTracking(String ses, String usr) throws Exception
	{
        String[][] result = new String[0][0];
        String[] args = new String[3];
        args[0] =  usr;
        args[1] = ses;
        args[2] = "N";
        String sql = "select login_id, session_id, status from session_master where login_id = ? and session_id = ?  and status != ?";
		try{						 
			 result = runner.multipleSelection(sql,args);
             result = result==null?new String[0][0]:result;

			 if(result.length > 0 ){
				available = true;
            }
			else {
				available = false;
            }
		}
		catch(Exception e){
            e.printStackTrace();
        }		
		return available;
	}

	public boolean validate() throws Exception
	{		
		boolean available = false;
        String[] args = new String[0];
        String [][] result = new String[0][0];
		try{
			if (pass.length()>=3) {
                args = new String[2];
                args[0] = user;
                args[1] =  new com.maan.common.password.passwordEnc().crypt(pass.substring(0,3),pass);

				String sql = "select login_id,password,accesstype from login_master where login_id = ? and password = ?";  
				String login_id = "";
				String password = "";
				String accessType = "";
                result = runner.multipleSelection(sql,args);
                result = result==null?new String[0][0]:result;
                  
               for(int i=0;i<result.length;i++){
                    login_id       = result[i][0];
					password     = result[i][1];
					accessType  = result[i][2];
                }                   

				if (login_id.equals(user) && password.equals(new com.maan.common.password.passwordEnc().crypt(pass.substring(0,3),pass)) && (accessType.equals("BOTH") || accessType.equals("FIRE"))){
					available = true;
				}
				else{
                    args = new String[2];
                    args[0] = user;
                    args[1] = pass;
					sql = "select login_id,password,accesstype from login_master where login_id = ? and password = ?";			
					login_id = "";
					password = "";
					available = false;

					result = runner.multipleSelection(sql,args);
                     result = result==null?new String[0][0]:result;
                    
				    for(int i=0;i<result.length;i++){
                            login_id       = result[i][0];
					        password     = result[i][1];
					        accessType  = result[i][2];
                    }                   

					if (login_id.equals(user) && password.equals(pass) && (accessType.equals("BOTH") || accessType.equals("FIRE")) )
						available = true;
					else
						available = false;
					}
				}
			}
			catch(Exception e){
                 e.printStackTrace();
            }			
		return available;
	}

	public String nuname() throws Exception
	{	
		String sql = "";	
        String[][] result = new String[0][0];
        String[] args = new String[2];
        args[0] = nuuser;
        args[1] = "Y";
		try{
			sql = "select login_id from login_master where login_id = ? and status = ?";			            
            result = runner.multipleSelection(sql,args);
            result = result==null?new String[0][0]:result;

            if(result.length > 0 ){
                sql = "true";
            }
            else{
                sql = "false";
            } 
			//boolean un = rs.next();
			//sql = "" + un;
		}
		catch(Exception e){
        }		
		return sql;
	}

	public void setLogExp(String logExp){
		this.logExp = logExp;
	}
	public String getLogExp(){
		return this.logExp;
	}

	public String getUserTypes(String user1){	
        String[] args = new String[2];
        args[0] = user1;
        args[1] = "Y";
		String checkSql = "select USERTYPE from login_master where login_id = ? and status = ?";
        String cusLog = "";
        try{
		        cusLog = runner.singleSelection(checkSql,args);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
		return cusLog;
	}

	
	
	public boolean uname() throws Exception {

		String empid=getEmployeeIdentity();
		String useridentity=getUserid(user,empid);		
		String userType = getUserTypes(user);
		String sql="";
        String[] args = new String[0];
        String[][] result = new String[0][0];
		boolean un = false;	
		try{
			if(!userType.equalsIgnoreCase("Customer")){
				if(useridentity.equals("2")){
                    args = new String[1];
                    args[0] = user;
					sql = "select log.login_id from login_master log where log.login_id = ?";
					msg=sql;	
				}
				else{
                    args = new String[2];
                    args[0] = user;
                    args[1] = "Y";
					sql = "select log.login_id from login_master log where log.login_id = ? and log.status = ?";
					msg=sql;
				}
			}
			else if(userType.equalsIgnoreCase("Customer")){
                args = new String[2];
                args[0] = user;
                args[1] = "Y";
				sql = "select login_id from login_master where login_id = ? and status = ? and expiry_date>(select sysdate from dual)";
				setLogExp("Login Expired");
			}	
            result = runner.multipleSelection(sql,args);
            result = result==null?new String[0][0]:result;

            if(result.length > 0){
                 un = true;
            }
            else{
                 un = false;
            }			
			//rs = Smt.executeQuery(sql);
			//un = rs.next();
		}
		catch(Exception e){
            e.printStackTrace();
        }		
		return un;
	}

	public Vector getDispName(String user)
	{		
		Vector dn = new Vector();
        String[][] result = new String[0][0];
        String[] args = new String[1];
        args[0] = user;
		String sql = "select user_type, display_name from login_master where login_id = ?";
		try{
            result = runner.multipleSelection(sql,args);
            result = result==null?new String[0][0]:result;

			for(int i=0;i<result.length;i++){
                dn.add(result[i][0]);
                dn.add(result[i][1]);
            }			
		}
		catch(Exception e){	
            e.printStackTrace();
        }		
		return dn;
	}

	/*public String[][] rights(String user)
	{		
        String[] args = new String[1];
        args[0] = user;
		String sql = "select rights,status from login_master where login_id = ?";
		String[][] record = new String[0][0];
		String newRights[][] = new String[0][0];
		String Rghts[][] = new String[0][0];	
		int a = 0;
		try{
			record = runner.multipleSelection(sql,args);
			if(record.length > 0){
				String arr[] = constant.split(record[0][0],"~");
				String queryStr = constant.queryFormat(arr).toString();
				String query = "select right_name,concat(link,'?gid=',group_id),remarks from user_rights where id in (" + queryStr + ") order by id";
				Rghts = runner.multipleSelection(query);
			}
		}
		catch(Exception e){		
            e.printStackTrace();
        }		
		return Rghts;
	}*/

	/*public StringBuffer vhed(String user, Connection con)
	{
		StringBuffer abc = new StringBuffer();
		String[][] newRights = new String[0][0];
		try{
			newRights = rights(user);
			if(newRights.length != 0)	{
				abc.append("<table width='350' border='0' cellspacing='0' cellpadding='0' align='center'><tr bgcolor='#FFFFFF'><td>\n");
				abc.append("<table bordercolorlight='#666666' bordercolordark='#CCCCCC' width='100%' border='1' cellspacing='3' cellpadding='0' align='center'>\n");
				abc.append("<tr background='../images/cell_head1.gif'> <td background='../images/cell_head1.gif' class='tb' width = '100%' align = 'center' height = '30'><font color = '#FFFFFF'>Select the Operation to be Performed</font></td></tr>\n");
				for(int i=0;i<newRights.length;i++)
				{
					if(newRights[i][2].equalsIgnoreCase("head"))
					{
						abc.append("<tr background='/sell/images/cell_head1.gif' ><td height = '30' align = 'center' class = 't' bgcolor = '#E5E5E5' ><a href = " + newRights[i][1] + "&user=" + user + " onmouseover = \"status='" + newRights[i][0] + "';return true\" onMouseOut = \"status=''\"><font color = '#000066'>" + newRights[i][0] + "</font></a></td></tr>\n ");
					}
				}
				abc.append("</table>\n");
			}
		}
		catch(Exception e){
			abc.append("Exception e : " + e);
            e.printStackTrace();
		}		
		return abc;
	}*/
	
	/*public StringBuffer subhd(String gid, String selname, String user)
	{		
		Calendar sysDate = Calendar.getInstance();
		DateFunction date = new DateFunction();
		StringBuffer subtbl = new StringBuffer();
		String subhed[][] = new String[0][0];
		String ds_img, sql, chpwd;
		sql = ds_img = chpwd = "";
		int dtdiff;
        String[] args = new String[1];
        String strDate = ""; //rsdt.getString(1)
		try{			
		    args[0] = user;
            strDate = runner.singleSelection("select date_format(pass_dt,'%d-%m-%Y') from login_master where login_id = ?",args);	
            strDate = strDate==null?"":strDate;
			dtdiff = (int)date.getDayDifference(date.getCalendar(strDate), sysDate) + 1;
			if(dtdiff > 3)
			{
				chpwd = "<a href='#' onclick=\"javascript:window.open('/sell/admin/chpas.jsp?user=" + user + "','','width=500,height=300, scrollbars=yes, status=yes')\" onmouseover=\"status='Click to Change Password';return true\" onMouseOut=\"status=''\"><b><font color='#000000'>Change Password</font></b></a>";
			}
            args[0] = user;
			sql = "select rights from login_master where login_id = '" + user + "'";
			ResultSet rs = Smt.executeQuery(sql);
			String rrs = rs.getString(1);
			while(rs.next())	{
				String arr[] = constant.split(rrs,"~");
				String queryStr = constant.queryFormat(arr).toString();
				sql = "select img from user_rights where remarks='head' and group_id='" + gid + "'";
				ResultSet imgrs = Smt.executeQuery(sql);
				while(imgrs.next()){
					ds_img = imgrs.getString(1);
				}
				sql = "select right_name,concat(link,'?gid=',group_id),link from user_rights where id in ("+queryStr+") and group_id = '" + gid + "' and remarks != 'head' order by id";
				subhed = qry.getResultSet(sql);
				if(subhed.length != 0){
					int a = 100/subhed.length;
					subtbl.append("<table width='95%' border='0' cellspacing='0' cellpadding='0' align='center'> \n");
					subtbl.append("<tr><td height='7' colspan='6'><img src='../images/1pix.gif' width='1' height='1'></td></tr> \n");
					subtbl.append("<tr><td ><img src='" + ds_img + "' width='116' height='25'></td><td width='650' align='right' class='t'>&nbsp;" + chpwd + "</td></tr> \n");
					subtbl.append("<tr bgcolor='#0078B3'><td colspan='2' height='1'><img src='../images/1pix.gif' width='1' height='1'></td></tr>\n");
					subtbl.append(" <tr bgcolor='#FFFFFF'><td colspan='6'><table width='100%' border='1' cellspacing='3' cellpadding='0' bordercolorlight='#666666' bordercolordark='#CCCCCC'><tr bgcolor='#E5E5E5' align='center'> \n");			

					for(int i = 0; i < subhed.length ; i++){
						if(subhed[i][2].equalsIgnoreCase("")){
							if(subhed[i][0].equalsIgnoreCase(selname)){
								subtbl.append("<td bgcolor='#006699' class='link1' height='18' width = '" + a + "%' align='center'>\n");
								subtbl.append("<font color='#FFFFFF'>" + subhed[i][0] + "</font></td> \n");
							}
							else
							{
								subtbl.append("<td bgcolor='#E5E5E5' class='link1' height='18' width = '" + a + "%' align='center'>\n");
								subtbl.append("<font color = '#000066'>" + subhed[i][0] + "</font></td>\n");
							}
						}
						else
						{
							if(subhed[i][0].equalsIgnoreCase(selname))
							{
								subtbl.append("<td bgcolor='#006699' class='link1' height='18' width='" + a + "%' align='center'>\n");
								subtbl.append("<a href='" + subhed[i][1] + "&user=" + user + "' onmouseover = \"status='" + subhed[i][0] + "';return true\" onMouseOut = \"status=''\"> <font color='#FFFFFF'>" + subhed[i][0] + "</font></a></td> \n");
							}
							else
							{
								subtbl.append("<td bgcolor='#E5E5E5' class='link1' height='18' width='" + a + "%' align='center'>\n");
								subtbl.append("<a href='" + subhed[i][1] + "&user=" + user + "' onmouseover = \"status='" + subhed[i][0] + "';return true\" onMouseOut = \"status=''\"><font color = '#000066'>" + subhed[i][0] + "</font></a> </td>\n");
							}
						}
					}
					subtbl.append("</tr></table></td></tr><tr bgcolor='#0078B3'><td colspan='2' height='1'><img src='../images/1pix.gif' width='1' height='1'></td></tr></table>\n");
				}
			}
			sql = "insert into activity_log values(sysdate(),'" + user + "','" + selname + "')";
			int trs = Smt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			subtbl.append("<br>This is the error message1 : " + e);

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
		return subtbl;
	}*/

	/*public StringBuffer subhd(String gid, String selname, String user, String contno)
	{
		Connection connection = null;
		connection = DBConnection.getInstance().getDBConnection();
		Calendar sysDate = Calendar.getInstance();
		DateFunction date = new DateFunction();
		StringBuffer subtbl = new StringBuffer();
		String subhed[][] = new String[0][0];
		String ds_img, sql, chpwd;
		sql = ds_img = chpwd = "";
		int dtdiff;
		try
		{
			Smt = connection.createStatement();
			QueryBuilder qry = new QueryBuilder(Smt);
			ResultSet rsdt = Smt.executeQuery("select date_format(pass_dt,'%d-%m-%Y') from login_master where login_id='" + user + "'");
			dtdiff = (int)date.getDayDifference(date.getCalendar(rsdt.getString(1)), sysDate) + 1;
			if(dtdiff > 3)
			{
				chpwd = "<a href='#' onclick=\"javascript:window.open('/sell/admin/chpas.jsp?user=" + user + "','','width=500,height=300, scrollbars=yes, status=yes')\" onmouseover=\"status='Click to Change Password';return true\" onMouseOut=\"status=''\"><b><font color='#000000'>Change Password</font></b></a>\n";
			}
			sql = "select rights from login_master where login_id='" + user + "'";
			ResultSet rs = Smt.executeQuery(sql);
			String rrs = rs.getString(1);
			while(rs.next())
			{
				String arr[] = constant.split(rrs,"~");
				String queryStr = constant.queryFormat(arr).toString();
				sql = "select img from user_rights where remarks='head' and group_id='" + gid + "'";
				ResultSet imgrs = Smt.executeQuery(sql);
				while(imgrs.next())
				{
					ds_img = imgrs.getString(1);
				}
				sql = "select right_name,concat(link,'?gid=',group_id),link from user_rights where id in ("+queryStr+") and group_id = '" + gid + "' and remarks != 'head' order by id";
				subhed = qry.getResultSet(sql);
				if(subhed.length != 0)
				{
					int a = 100/subhed.length;
					subtbl.append("<table width='95%' border='0' cellspacing='0' cellpadding='0' align='center'><tr><td height='7' colspan='6'><img src='../images/1pix.gif' width='1' height='1'></td></tr><tr>\n");
					subtbl.append("<td ><img src='" + ds_img + "' width='116' height='25'></td><td width='650' align='right' class='t'>&nbsp;" + chpwd + "</td></tr>\n");
					subtbl.append("<tr bgcolor='#0078B3'><td colspan='2' height='1'><img src='../images/1pix.gif' width='1' height='1'></td></tr> \n");
					subtbl.append("<tr bgcolor='#FFFFFF'><td colspan='6'><table width='100%' border='1' cellspacing='3' cellpadding='0' bordercolorlight='#666666' bordercolordark='#CCCCCC'><tr bgcolor='#E5E5E5' align='center'>\n");
					for(int i = 0; i < subhed.length ; i++)
					{
						if(subhed[i][2].equalsIgnoreCase(""))
						{
							if(subhed[i][0].equalsIgnoreCase(selname))
							{
								subtbl.append("<td bgcolor='#006699' class='link1' height='18' width = '" + a + "%' align='center'><font color='#FFFFFF'>" + subhed[i][0] + "</font></td>\n");
							}
							else
							{
								subtbl.append("<td bgcolor='#E5E5E5' class='link1' height='18' width = '" + a + "%' align='center'><font color = '#000066'>" + subhed[i][0] + "</font></td>\n");
							}
						}
						else
						{
							if(subhed[i][0].equalsIgnoreCase(selname))
							{
								subtbl.append("<td bgcolor='#006699' class='link1' height='18' width='" + a + "%' align='center'>\n");
								subtbl.append("<a href='" + subhed[i][1] + "&user=" + user + "' onmouseover = \"status='" + subhed[i][0] + "';return true\" onMouseOut = \"status=''\"> <font color='#FFFFFF'>" + subhed[i][0] + "</font></a></td>\n");
							}
							else
							{
								subtbl.append("<td bgcolor='#E5E5E5' class='link1' height='18' width='" + a + "%' align='center'>\n");
								subtbl.append("<a href='" + subhed[i][1] + "&user=" + user + "' onmouseover = \"status='" + subhed[i][0] + "';return true\" onMouseOut = \"status=''\"><font color = '#000066'>" + subhed[i][0] + "</font></a> </td>\n");
							}
						}
					}
					subtbl.append("</tr></table></td></tr><tr bgcolor='#0078B3'><td colspan='2' height='1'><img src='../images/1pix.gif' width='1' height='1'></td></tr></table>\n");
				}
			}
		
		}

		catch(Exception e)
		{

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
		return subtbl;
	}*/

	/*public StringBuffer vsubhd(String gid, String user)
	{
		Connection connection = null;
		connection = DBConnection.getInstance().getDBConnection();

		StringBuffer sbtbl = new StringBuffer();
		String sbhed[][] = new String[0][0];
		String sql = "";
		try
		{
			Smt = connection.createStatement();
			QueryBuilder qry = new QueryBuilder(Smt);
			sql = "select rights from login_master where login_id='" + user + "'";
			ResultSet rs = Smt.executeQuery(sql);
			String rrs = rs.getString(1);
			while(rs.next())
			{
				String arr[] = constant.split(rrs,"~");
				String queryStr = constant.queryFormat(arr).toString();
				sql = "select right_name,concat(link,'?gid=',group_id),link from user_rights where id in ("+queryStr+") and group_id = '" + gid + "' and remarks != 'head' order by id";
				sbhed = qry.getResultSet(sql);
				if(sbhed.length != 0)
				{
					sbtbl.append("<table width='350' border='0' cellspacing='0' cellpadding='0' align='center'><tr bgcolor='#FFFFFF'><td><table bordercolorlight='#666666' bordercolordark='#CCCCCC' width='100%' border='1' cellspacing='3' cellpadding='0' align='center'>\n");
					sbtbl.append("<tr background='../images/cell_head1.gif'><td background='../images/cell_head1.gif' class='tb' width = '100%' align = 'center' height = '30'><font color = '#FFFFFF'>Select the Operation to be Performed</font></td></tr>\n");
					for(int i = 0; i < sbhed.length ; i++)
					{
						if(sbhed[i][2].equalsIgnoreCase(""))
						{
							sbtbl.append("<tr bgcolor='#E5E5E5' align='center'><td bgcolor='#E5E5E5' class='t' height='25' align='center'><font color = '#000066'>" + sbhed[i][0] + "</font></td></tr>\n");
						}
						else
						{
							sbtbl.append("<tr bgcolor='#E5E5E5' align='center'><td bgcolor='#E5E5E5' class='t' height='25' align ='center'><a href='" + sbhed[i][1] + "&user=" + user + "' onmouseover = \"status='" + sbhed[i][0] + "';return true\" onMouseOut = \"status=''\"><font color = '#000066'>" + sbhed[i][0] + "</font></a></td></tr>\n");
						}
					}

					sbtbl.append("</td></tr></table></table>\n");
				}
			}
		}
		catch(Exception e)
		{
			sbtbl.append("<br>This is the error message3 : " + e);
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
		Connection connection = null;
		connection = DBConnection.getInstance().getDBConnection();
		String seln = "";
		try
		{
			Smt = connection.createStatement();
			String sql = "select right_name from user_rights where id = '" + gid + "'";
			seln += sql;
			ResultSet rs = Smt.executeQuery(sql);
			while(rs.next())
			{
				seln = rs.getString(1);
			}
		}

		catch(Exception e)
		{
			seln += "None<br>" + e;
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
		return seln;
	}*/

	/*public StringBuffer getRights()
	{
		Connection connection = null;
		connection = DBConnection.getInstance().getDBConnection();

		StringBuffer rghTbl = new StringBuffer();
		StringBuffer hdTbl = new StringBuffer();
		String sql = "select right_name,remarks,group_id,id from user_rights where group_id!='99' order by group_id";
		try
		{
			Smt = connection.createStatement();
			QueryBuilder qry = new QueryBuilder(Smt);
			String[][] subTbl = qry.getResultSet(sql);
			if(subTbl.length > 0)
			{
				Hashtable rhtht = getRightHash();
				String tempGid = subTbl[0][2];
				hdTbl.append("<table align='center' border='0' width='100%' cellspacing='0' cellpadding=0'><tr><td valign='top'><table border='0' width='99%' cellspacing='0' cellpadding='0'> \n");
				for(int i = 0; i < subTbl.length; i++)
				{
					if(!tempGid.equalsIgnoreCase(subTbl[i][2]))
					{
						if(i > 0)
						{
							hdTbl.append("</table></td><td valign='top'> \n");
						}
						hdTbl.append("<table border='0' width='99%' cellspacing='0' cellpadding='0'> \n");
					}
					if(subTbl[i][1].equalsIgnoreCase("head"))
					{
						hdTbl.append("<tr><td colspan='2' height = '30' class='tb' background = '../images/cell_head1.gif'><font color = '#FFFFFF'>&nbsp;&nbsp;&nbsp;" + subTbl[i][0] + "</font></td></tr>\n");
					}
					else if(!(subTbl[i][1].equalsIgnoreCase("nul")))
					{
						String checkStr = "";
						if(rhtht.get(subTbl[i][3]) != null)
						{
							checkStr = "checked";
						}
						hdTbl.append("<tr><td align='center' width='20%'><input type='checkbox' "+checkStr+" name='rght' value='" + subTbl[i][3] + "'></td><td width='80%' height = '30' align='left' class='t'>" + subTbl[i][0] + "</td></tr>\n");
					}
					tempGid = subTbl[i][2];
				}
				hdTbl.append("</td></tr></table></td></tr></table>\n");
				rghTbl.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center'><tr><td>\n");
				rghTbl.append(hdTbl+"\n");
				rghTbl.append("</td></tr></table>\n");
			}
			else
			{
				rghTbl.append("<br>Cannot Proceed Further");
			}
		}
		catch(Exception e)
		{
			rghTbl.append("<br>The Error started here in getrights.<br>" + e);
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
		return  rghTbl;
	} */

	/*public StringBuffer getERights(Hashtable rghthsh)
	{
		Connection connection = null;
		connection = DBConnection.getInstance().getDBConnection();
		StringBuffer rghTbl = new StringBuffer();
		StringBuffer hdTbl = new StringBuffer();
		String sql = "select right_name,remarks,group_id,id from user_rights where group_id!='99' order by group_id, id";
		try
		{
			Smt = connection.createStatement();
			QueryBuilder qry = new QueryBuilder(Smt);
			String[][] subTbl = qry.getResultSet(sql);
			if(subTbl.length > 0)
			{
				Hashtable rhtht = rghthsh;

				String tempGid = subTbl[0][2];
				hdTbl.append("<table align='center' border='0' width='100%' cellspacing='0' cellpadding=0'><tr><td valign='top'><table border='0' width='99%' cellspacing='0' cellpadding='0'> \n");
				for(int i = 0; i < subTbl.length; i++)
				{
					if(!tempGid.equalsIgnoreCase(subTbl[i][2]))
					{
						if(i > 0)
						{
							hdTbl.append("</table></td><td valign='top'> \n");
						}
						hdTbl.append("<table border='0' width='100%' cellspacing='0' cellpadding='0'> \n");
					}
					if(subTbl[i][1].equalsIgnoreCase("head"))
					{
						hdTbl.append("<tr><td colspan='2' background='../images/cell_head1.gif' align = 'center' height='30' class='tb'><font color = '#FFFFFF'>" + subTbl[i][0] + "</font></td></tr>\n");
					}
					else if(!(subTbl[i][1].equalsIgnoreCase("nul")))
					{
						String checkStr = "";
						if(rhtht.get(subTbl[i][3]) != null)
						{
							checkStr = "checked";
						}
						hdTbl.append("<tr><td align='center' width='5%'><input type='checkbox' name='rght' " + checkStr + " value='" + subTbl[i][3] + "'></td><td width='95%' height = '35' align='left' class='t'>" + subTbl[i][0] + "</td></tr>\n");
					}
					tempGid = subTbl[i][2];
				}
				hdTbl.append("</td></tr></table></td></tr></table>\n");
				rghTbl.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center'><tr><td>\n");
				rghTbl.append(hdTbl+"\n");
				rghTbl.append("</td></tr></table>\n");
			}
			else
			{
				rghTbl.append("<br>Cannot Proceed Further");
			}
		}
		catch(Exception e)
		{
			rghTbl.append("<br>The Error started here in getErights.<br>" + e);
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
		return  rghTbl;
	}*/

/*	public Hashtable getRightHash()
	{
		Hashtable hash = new Hashtable();

		for(int i=0; i < rght.length; i++)
		{
			hash.put(rght[i], rght[i]);
		}

		return hash;
	} */

	/*public StringBuffer creaUser(String[] rgt)
	{
		Connection connection = null;
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
			if(arhd.length > 0)
			{
				for(int i=0;i<arhd.length;i++)
				{
					hash2.put(arhd[i][0],arhd[i][1]);
				}

				for(int i = 0; i < rght.length; i++)
				{
					if(frght.toString().indexOf(hash2.get(rght[i]) + "~") == -1)
					{
						frght.append(hash2.get(rght[i]) + "~");
					}
				}
			}
		}
		catch(Exception e)
		{
			frght.append("<br>There is an error4444. The error is : " + e);
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
	}*/
/*
	public String insUser(StringBuffer fr)
	{
		Connection connection = null;
		connection = DBConnection.getInstance().getDBConnection();
		String s = "";
		String newstr = fr.toString();
		String insrght = newstr;
		s = s + nuuser + nupass + insrght + nusrnam;
		try
		{
			String arr[] = constant.split(newstr,"~");
			Hashtable fulhash = getRightHash();
			String retEight = (String) fulhash.get("8");
			String retTwoThree = (String) fulhash.get("23");
			String retEighten = (String) fulhash.get("18");
			if(retEight != null && retEight.equalsIgnoreCase("8"))
			{
				fr.append("~9");
			}
			if(retTwoThree != null && retTwoThree.equalsIgnoreCase("23"))
			{
				fr.append("~24~25~26");

			}
			if(retEighten != null && retEighten.equalsIgnoreCase("18"))
			{
				fr.append("~19~20");
			}
			String sql = "insert into login_master (login_id,password,user_type,rights,status,username,pass_dt) values('" + nuuser + "','" + nupass + "','user','" + fr /* insrght *//* + "','FL','" + nusrnam + "',sysdate())";
			Smt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			s = s + "Exception is : " + e;
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
		return s;
	} */

/*public String modUser(StringBuffer fr, String chk)
	{
		Connection connection = null;
		connection = DBConnection.getInstance().getDBConnection();
		String s, chkd, sql;
		s = chkd = sql = "";
		String newstr = fr.toString();
		String insrght = newstr;
		s = s + nuuser + nupass + insrght + nusrnam;
		try
		{
			String arr[] = constant.split(newstr,"~");
			Hashtable fulhash = getRightHash();
			String retEight = (String) fulhash.get("8");
			String retTwoThree = (String) fulhash.get("23");
			String retEighten = (String) fulhash.get("18");
			if(retEight != null && retEight.equalsIgnoreCase("8"))
			{
				fr.append("~9");
			}
			if(retTwoThree != null && retTwoThree.equalsIgnoreCase("23"))
			{
				fr.append("~24~25~26");
			}
			if(retEighten != null && retEighten.equalsIgnoreCase("18"))
			{
				fr.append("~19~20");
			}
			if((chk.equalsIgnoreCase("loc")) || (chk.equalsIgnoreCase("l")))
			{
				chkd = "Y";
			}
			else if(chk.equalsIgnoreCase("n"))
			{
				chkd = "N";
			}
			else if(chk.equals(""))
			{
				sql = "select status from login_master where login_id='" +  nuuser + "'";
				ResultSet rs = Smt.executeQuery(sql);
				while(rs.next())
				{
					chkd = rs.getString(1);
				}
			}
			else
			{
				chkd = chk;
			}
			sql = "update login_master set rights = '" + fr + "',status = '" + chkd + "',username = '" + nusrnam + "' where login_id = '" + nuuser + "'";
			Smt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			s = s + "<br>Exception is : " + e + "<br>";
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
		return s;
	} */

	/*public StringBuffer getactiv(String sd, String ed)
	{
		Connection connection = null;
		connection = DBConnection.getInstance().getDBConnection();
		StringBuffer actTbl = new StringBuffer();
		try
		{
			Smt = connection.createStatement();
			DateFunction df = new DateFunction();
			String sds = df.changeDateFormatDMY(sd);
			String eds = df.changeDateFormatDMY(ed);
			String sql = "select * from activity_log where time_stamp between date_sub('" + sd + "', interval -1 day) and date_add('" + ed + "', interval 1 day)";

			QueryBuilder qry = new QueryBuilder(Smt);

			String[][] bldTbl =qry.getResultSet(sql);
			if(bldTbl.length > 0)
			{
				actTbl.append("<br><center><input type='image' onclick=window.open('prnactivity.jsp?sd=" + sd + "&ed=" + ed + "','new','width=750,height=450,scrollbars=yes'); src='../images/print_btn.gif' border='0' alt='Click Here to Print' name='image'></center><br>\n");
				actTbl.append("<table width='600' border='0' cellspacing='0' cellpadding='1' align='center'><tr><td bgcolor='#0078B3'><table align='center' border='0' width='100%' cellspacing='0' cellpadding=0'><tr><td bgcolor='#FFFFFF' align='center' colspan='4' height='27' class='tb'>Start Date : " + sds + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;End Date : " + eds + " </td></tr>\n");
				actTbl.append("<tr height='1'><td></td></tr><tr> <td><table align='center' border='0' width='100%' cellspacing='0' cellpadding=0'><tr><td bgcolor='#FFFFFF' align='center' width='10%' height='27' class='tb'>S. No.</td><td bgcolor='#FFFFFF' align='center' width='33%' height='27' class='tb'>User</td><td bgcolor='#FFFFFF' width='30%' height='27' align='center' class='tb'>Date and Time</td><td bgcolor='#FFFFFF' width='27%' height='27' align='center' class='tb'>Action Performed</td></tr><tr><td colspan='4' height='1' bgcolor='#0078B3'></td></tr>\n");

				int j = 1;
				for(int i  = 0; i < bldTbl.length ; i++)
				{
					actTbl.append("<tr><td height='27' class='t' bgcolor='#FFFFFF' align='right' width='10%'>" + j + ".</td><td height='27' class='t' bgcolor='#FFFFFF' align='center' width='33%'>" + bldTbl[i][1] + "</td>\n");
					actTbl.append("<td height='27' class='t' bgcolor='#FFFFFF' align='center' width='30%'>" + bldTbl[i][0] + "</td><td height='27' class='t' align='center' bgcolor='#FFFFFF' width='27%'>" + bldTbl[i][2] + "</td></tr>\n");
					j++;
				}
				actTbl.append("</table></td></tr></table></td></tr></table>\n");
				actTbl.append("<br><center><input type='image' onclick=window.open('prnactivity.jsp?sd=" + sd + "&ed=" + ed + "','new','width=750,height=450,scrollbars=yes'); src='../images/print_btn.gif' border='0' alt='Click Here to Print' name='image'></center><br>\n");
			}
		}

		catch(Exception e)
		{
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

	/*public StringBuffer gtactiv(String sd, String ed, Connection con) throws Exception
	{
		Connection connection = null;
		connection = DBConnection.getInstance().getDBConnection();

		DateFunction df = new DateFunction();
		StringBuffer actTbl = new StringBuffer();
		String sds = df.changeDateFormatDMY(sd);
		String eds = df.changeDateFormatDMY(ed);
		try
		{
			Smt = con.createStatement();

			String sql = "select * from activity_log where time_stamp between date_sub('" + sd + "', interval -1 day) and date_add('" + ed + "', interval 1 day) order by time_stamp";

			QueryBuilder qry = new QueryBuilder(Smt);

			String[][] bldTbl =qry.getResultSet(sql);
			if(bldTbl.length > 0)
			{
				actTbl.append("<table width='400' border='0' cellspacing='0' cellpadding='1' align='center'><tr><td bgcolor='#0078B3'><table align='center' border='0' width='100%' cellspacing='0' cellpadding=0'><tr><td bgcolor='#FFFFFF' align='center' colspan='4' height='27' class='tb'>Start Date : " + sds + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;End Date : " + eds + " </td></tr>\n");
				actTbl.append("<tr><td></td><tr height='1'><td><table align='center' border='0' width='100%' cellspacing='0' cellpadding=0'><tr><td bgcolor='#FFFFFF' align='center' width='10%' height='27' class='tb'>S. No.</td>\n");
				actTbl.append("<td bgcolor='#FFFFFF' align='center' width='33%' height='27' class='tb'>User</td><td bgcolor='#FFFFFF' width='30%' height='27' align='center' class='tb'>Date and Time</td><td bgcolor='#FFFFFF' width='27%' height='27' align='center' class='tb'>Action Performed</td></tr><tr><td colspan='4' height='1' bgcolor='#0078B3'></td></tr>\n");
				int j = 1;
				for(int i  = 0; i < bldTbl.length ; i++)
				{
					actTbl.append("<tr><td height='27' class='t' bgcolor='#FFFFFF' align='right' width='10%'>" + j + ".</td><td height='27' class='t' bgcolor='#FFFFFF' align='center' width='33%'>" + bldTbl[i][1] + "</td>\n");
					actTbl.append("<td height='27' class='t' bgcolor='#FFFFFF' align='center' width='30%'>" + bldTbl[i][0] + "</td><td height='27' class='t' align='center' bgcolor='#FFFFFF' width='27%'>" + bldTbl[i][2] + "</td></tr>\n");
					j++;
				}
				actTbl.append("</table></td></tr></table>\n");
			}
		}
		catch(Exception e)
		{
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

	public StringBuffer getUsr()
	{
		StringBuffer UsrLst = new StringBuffer();
		try{
			
			String sql = "select username,status from login_master";		
			String[][] bldTbl = runner.multipleSelection(sql);
			if(bldTbl.length > 0)
			{
				for(int i = 0; i < bldTbl.length ; i++)
				{
					UsrLst.append("<option value = '" + bldTbl[i][0] + "'>" + bldTbl[i][0] + "</option>");
				}
			}
		}
		catch(Exception e){			
            e.printStackTrace();
        }		
		return UsrLst;
	}

	public String[][] getusers(String nusr) throws Exception
	{
		String[][] nuusr = new String[0][0];
		String args[] = new String[1];
		String sql = "";
		try{
			args[0] = nusr;
			sql = "select password, rights, status, login_id from login_master where username = ?";
			nuusr = runner.multipleSelection(sql,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return nuusr;
	}

	public boolean chkpas(String pas, String pass) throws Exception
	{
		boolean retpas = false;
		String[][] upas = new String[0][0];
		String sql = "";
		String args[] = new String[0];
		try{
			args[0] = pas;
			sql =  "select password, lpass1, lpass2, lpass3 from login_master where username=?";
			upas = runner.multipleSelection(sql,args);
			if(upas.length > 0){
				if((pass.equals(upas[0][0])) || (pass.equals(upas[0][1])) || (pass.equals(upas[0][2])) || (pass.equals(upas[0][3]))){
					retpas = true;
				}
				else{
					retpas = false;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return retpas;
	}

	public void cpas(String nusr, String cps) throws Exception
	{
		String a = "ne";
		String[][] qry1 = new String[0][0];
		String sql = "";
		String args[] = new String[0];
		try{
			args = new String[1];
			args[0] = nusr;
			sql = "select password, lpass1, lpass2 from login_master where login_id=?";
			qry1 = runner.multipleSelection(sql,args);
			if(qry1.length == 0){
				args = new String[1];
				args[0] = nusr;
				a = "upd";
				sql = "select password, lpass1, lpass2 from login_master where username=?";
				qry1 = runner.multipleSelection(sql,args);
			}
			if(a.equalsIgnoreCase("ne"))	{
				args = new String[5];
				args[0] = qry1[0][2];
				args[1] = qry1[0][1];
				args[2] = qry1[0][0];
				args[3] = new com.maan.common.password.passwordEnc().crypt(cps.substring(0,3),cps);
				args[4] = nusr;
				sql = "update login_master set lpass3=?,lpass2=?,lpass1=?,password=?,passdate=SYSDATE,status='Y' where login_id=?";
			}
			else if(a.equalsIgnoreCase("upd")){
				args = new String[5];
				args[0] = qry1[0][2];
				args[1] = qry1[0][1];
				args[2] = qry1[0][0];
				args[3] = new com.maan.common.password.passwordEnc().crypt(cps.substring(0,3),cps);
				args[4] = nusr;
				sql = "update login_master set lpass3=?,lpass2=?,lpass1=?,password=?, passdate=SYSDATE,status='Y' where username=?";
			}
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void cfpas(String nusr, String cps) throws Exception
	{
		String sql = "";
		String args[] = new String[2];
		try{
			args[0] = cps;
			args[1] = nusr;
			sql = "update login_master set password=? ,pass_dt=SYSDATE, status='Y' where login_id=?";
			runner.multipleUpdation(sql,args);			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void locklo(String user) throws Exception{
		String sql = "";
		String args[] = new String[1];
		try{	
			args[0] = user;
			sql = "update login_master set status='L' where login_id=?";
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

    public boolean isValidEntry() throws Exception{	
		boolean rt_value = false;
        String[][] result = new String[0][0];
        String[] args = new String[2];
        args[0] = user;
        args[1] = "Y";
		try{			
			String sql = "select login_id from login_master where login_id = ? and status = ?";
            result = runner.multipleSelection(sql,args);			
            result = result ==null?new String[0][0]:result;

            if(result.length>0){
                rt_value = true;
            }
            else{
                rt_value = false;
            }			
		}
		catch(Exception e){
            e.printStackTrace();
        }		
        return rt_value;
	} 

    public String getUserType() throws Exception
	{
	  String rt_value = "";
	  String sql = "";
	  String args[] = new String[1];
		try
		{
			args[0] = user;
			sql = "select usertype from login_master where login_id = ?";
			rt_value = runner.singleSelection(sql,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return rt_value;
	}   

	public String getUserName() throws Exception
	{
    	String rt_value = "";
		String sql = "";
		String args[] = new String[1];
		try{
            args[0] = user;
			sql = "select username from login_master where login_id = ? and status = 'Y'";
			rt_value = runner.singleSelection(sql,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return rt_value;
	}

	public String getDisclaimerStatus() throws Exception
	{
		String rt_value = "";
		String sql = "";
		String args[] = new String[1];
		try{
			args[0] = user;
			sql = "select status from login_master where login_id = ?";
			rt_value = runner.singleSelection(sql,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return rt_value;
	}

	public void changeDisclaimerStatus() throws Exception
	{	
		String[] args = new String[1];
        args[0] = user;
		try{	
			String sql = "update login_master set status='Y' where login_id = ?";
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e){
            e.printStackTrace();
        }		
	}

    public String  getEmployeeIdentity() throws Exception
	{		
		String rt_value = "";
        String[][] result = new String[0][0];
        String[] args = new String[2];
        args[0] = user;
        args[1] = "Y";
		try{			
			String sql = "select * from login_master where login_id = ? and status = ?";
            result= runner.multipleSelection(sql,args);
            result = result==null?new String[0][0]:result;			
			
			if(result.length > 0){
                rt_value = result[0][0];			
            }			 
		}
		catch(Exception e){
            e.printStackTrace();
        }		
        return rt_value;
	}

    public int getCompanyIdentity() throws Exception
	{		
		int rt_value = 0;
        String[][] result = new String[0][0];
        String[] args = new String[2];
		try{	
            args[0] = user;
            args[1] = "Y";            
			String sql = "select company_id from login_master where login_id = ? and status = ?";	
            result = runner.multipleSelection(sql,args);
            result = result==null?new String[0][0] :result;

            if(result.length>0){
                rt_value = Integer.parseInt(result[0][0]);
            }	
		}	
		catch(Exception e){
             e.printStackTrace();
        }		
        return rt_value;
	}

	public String getUserid(String loginid,String userid)throws SQLException
	{		
		String rt_value = "";
        String[] args = new String[1];
        args[0] = loginid;
		try{		
			    String strQry="Select userid from login_master where login_id = ?";		
			    rt_value = runner.singleSelection(strQry,args);
		}
		catch(Exception e){
            e.printStackTrace();
        }		
        return rt_value;
	}

	/*public String getCurrentCompanyname()throws SQLException
	{	
		String rt_value = "";
		String shortname ="";
		try{
			Smt = connection.createStatement();
			String strQry="select short_name from company_master where status='Y'";
			ResultSet rs = Smt.executeQuery(strQry); 
			if(rs.next())
			 shortname = rs.getString("short_name");
		}
		catch(Exception e){}		
        return shortname;
	}*/

	public String isValidPassword()
	{
        String[] args = new String[2];
        args[0] = user;
        args[1] = new com.maan.common.password.passwordEnc().crypt(oldPass.substring(0,3),oldPass);
        String[][] result = new String[0][0];
        String[][] result1 = new String[0][0];
		String sql="select password from login_master where login_id = ? and password = ?";
	   try{
             result = runner.multipleSelection(sql,args);
             result = result ==null?new String[0][0]:result;   		
			
			 if(result.length==0)
			{
                  args = new String[2];
                  args[0] = user;
                  args[1] = oldPass;
				  sql="select password from login_master where login_id = ?  and password = ?";
                  result1 = runner.multipleSelection(sql,args);				
				  if(result1.length == 0)
					return "<br>&nbsp;Old Password given is in correct. <br>";
			}
			if((newPass.length()==0)||(oldPass.length()==0))
			   return "<br>&nbsp; New Password should not be Empty. <br>";
			if(newPass.length()<6)
				  return "<br>&nbsp; New Password must be atleast 6 Characters. <br>";
	  
			if(!newPass.equals(rNewPass))
			   return "<br>&nbsp; New Password retyped doesn't match. <br>";		 
		}
		catch(Exception e){
            e.printStackTrace();
		}
		return "";
	}
	public boolean isAdminType()
	{
		boolean result = false;
		String sql = "select count(*) from constant_detail where category_id='40' and lower(detail_name)=(select lower(usertype) from login_master where login_id=?) and branch_code=(select nvl(branch_code,'0') from login_master where login_id=?)";
		String args[] = new String[2];
		args[0] = user;
		args[1] = user;
		try
		{
			String temp = runner.singleSelection(sql,args);
			int res = 0;
			if(temp.length()>0)
				res = Integer.parseInt(temp);
			if(res>0)
				result = true;
			else
				result = false;

		}
		catch(Exception e)
		{
			 e.printStackTrace();
		}
		return result;
	}
} 
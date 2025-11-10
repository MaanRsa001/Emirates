package com.maan.Mail.DAO;


import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.error.ErrorInfo;
import com.maan.common.exception.BaseException;
import com.maan.premium.DAO.PremiumLogic;
import com.maan.services.util.runner;

public class MailBean extends ErrorInfo
{
	private static final Logger logger = LogUtil.getLogger(MailBean.class);

	private String applicationNo = "";
	private String applNo = "";
	private String cid = "";
	private String loginBra = "";
	private String productId = "";
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	public void setLoginBra(final String loginBra){
		this.loginBra=loginBra;
	}
	public String getLoginBra(){
		return this.loginBra;
	}
	public void setCid(final String cid){
		this.cid=cid;
	}
	public String getCid(){
		return this.cid;
	}

	public Map getPremiumDetails()throws BaseException{
		logger.info("getPremiumDetails mailBean method()");
		logger.debug(ENTER);
			final PremiumLogic logic = new PremiumLogic();
			logic.setApplicationNo(applicationNo);
			logic.setLoginBra(loginBra);
			logic.setCid(cid);
			final Map premiumDetails = logic.getPremiumDetails();
		logger.debug(EXIT);
		
		return premiumDetails;
	}
	public void print(final String methodName, final String information, final String type) {
		logger.info(methodName + "<--->" + type + "<---->" + information);
	}

	/**
	 * @return Returns the applicationNo.
	 */
	public String getApplicationNo() {
		return applicationNo;
	}

	/**
	 * @param applicationNo
	 *            The applicationNo to set.
	 */
	public void setApplicationNo(final String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public void updateMarineData(final String premium, final String totalWarPremium,final String toalSaleTerm,
			final String remarks) throws BaseException{
		logger.info("updateMarineData mailBean method()");
		logger.debug(ENTER);
			final String values[] = {premium,remarks,totalWarPremium,toalSaleTerm,applNo};
			final String sql = "update marine_data set premium=?,remarks=?,total_war_charges=?,total_sale_term_charges=? where application_no=?";
			runner.multipleUpdation(sql,values);
		logger.debug(EXIT);
		
	}
	public String getStartingPlace(final String applNo,final String cid) throws BaseException{
		logger.info("getStartingPlace mailBean method()");
		logger.debug(ENTER);
			final PremiumLogic logic = new PremiumLogic();
			logic.setApplicationNo(applNo);
			logic.setCid(cid);
			final String res = logic.getStartingPlace(cid);
		logger.debug(EXIT);
		
		return res;
	}
	public String getEndingPlace(final String applNo,final String cid) throws BaseException{
		logger.info("getEndingPlace mailBean method()");
		logger.debug(ENTER);
			final PremiumLogic logic = new PremiumLogic();
			logic.setApplicationNo(applNo);
			logic.setCid(cid);
			final String res = logic.getEndingPlace(cid);
		logger.debug(EXIT);
		
		return res;
	}
	public String getNames(final String cusId) throws BaseException{
		logger.info("getNames mailBean method()");
		logger.debug(ENTER);
			final String values[]={cusId};
			final String result = runner.singleSelection("select nvl(first_name,company_name) from personal_info " +
				"where customer_id=?",values);
		logger.debug(EXIT);
		
		return result;
	}
	public String getOpenCoverNo(final String quoteNo) throws BaseException
	{
		logger.info("getOpenCoverNo mailBean method()");
		logger.debug(ENTER);
			final String args[] = {quoteNo};
			final String openCoverNo = runner.singleSelection("select open_cover_no from position_master where quote_no=?",args);
		logger.debug(EXIT);
		
		return openCoverNo;
	}
	public String getApplNo() {
		return applNo;
	}
	public void setApplNo(final String applNo) {
		this.applNo = applNo;
	}
	//From MailClause.java
	public String[][] getClauseInfor(final String openCoverNo, final String coverId) throws BaseException
	{
		logger.info("getClauseInfor mailclause method()");
		logger.debug(ENTER);
			final String values[] = {coverId,openCoverNo,openCoverNo,coverId};
			final String sqlQuery = "select clauses_id,clauses_description from open_cover_clauses where status='Y' and cover_id =? and extra_cover_id='0' " +
						"and proposal_no = (select proposal_no from open_cover_position_master where open_cover_no = ?) " +
						"and amend_id = (select max(amend_id) from open_cover_clauses where status='Y' " +
						"and proposal_no = (select proposal_no from open_cover_position_master where open_cover_no =?) " +
						"and cover_id =?)";
			final String result[][] = runner.multipleSelection(sqlQuery,values);
		logger.debug(EXIT);
		
		return result;
	}
	public String[][] getExcluInfor(final String openCoverNo) throws BaseException
	{
		logger.info("getExcluInfor mailclause method()");
		logger.debug(ENTER);
			final String values[] = {openCoverNo,openCoverNo};
			final String sqlQuery = "select occmss.exclusion_id,occmss.exclusion_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_exclusions occmss,open_cover_position_master ocpmss " +
					"where ocpmss.proposal_no =(select proposal_no from open_cover_position_master where open_cover_no = ?) and " +
					"ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_exclusions occms,open_cover_position_master ocpms " +
					"where ocpms.proposal_no =(select proposal_no from open_cover_position_master where open_cover_no = ?) and " +
					"ocpms.proposal_no=occms.proposal_no)";
			final String result[][] = runner.multipleSelection(sqlQuery,values);
		logger.debug(EXIT);
		
		return result;
	}
	public String[][] getOpenCover(final String quoteNo) throws BaseException
	{
		logger.info("getOpenCover mailclause method()");
		logger.debug(ENTER);
			final String values[] = {quoteNo};
			final String sqlQuery = "select md.cover_id,md.open_cover_no,md.extra_cover_id from marine_data md where md.application_no=" +
						"(select application_no from position_master where quote_no = ?)";
			final String result[][] = runner.multipleSelection(sqlQuery,values);
		logger.debug(EXIT);
		
		return result;
	}
	public String[][] getSeaOption(final String quoteNo,final String brokerBranch)throws BaseException
	{
		logger.info("getSeaOption mailclause method()");
		logger.debug(ENTER);
			final String values[] = {quoteNo,brokerBranch};
			final String sqlQuery = "select md.cover_id,md.open_cover_no,md.extra_cover_id,md.mode_of_transport, nvl(md.sea_options,'2000'), ecm.extra_cover_name from marine_data md, extra_cover_master ecm " +
					"where md.application_no=(select application_no from position_master where quote_no = ?) " +
					"and md.extra_cover_id=ecm.extra_cover_id and ecm.branch_code=?";
			final String result[][] = runner.multipleSelection(sqlQuery,values);
		logger.debug(EXIT);
		
		return result;
	}
	public String[][] getWsrccFromOpenCoverMasterResult(final int coverId, final int coverId1,final int coverId2,
			final String openCoverNo,final String brokerBranch,final String cover) throws BaseException
	{
		logger.info("getWsrccFromOpenCoverMasterResult mailclause method()");
		logger.debug(ENTER);
			final String values[] = {openCoverNo,openCoverNo,cover,brokerBranch,cover,brokerBranch};
			final String sqlQuery = "select occmss.clauses_id,occmss.clauses_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where " +
						"ocpmss.proposal_no =(select proposal_no from open_cover_position_master where open_cover_no=?) and " +
						"ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occmss.amend_id) from " +
						"open_cover_clauses occmss where occmss.proposal_no =(select proposal_no from open_cover_position_master " +
						"where open_cover_no=?)  and occmss.extra_cover_id in (select extra_cover_id from clauses_master " +
						"where extra_cover_id != 0 and cover_id=? and branch_code=?) ) and ocpmss.proposal_no=occmss.proposal_no and " +
						"occmss.extra_cover_id in (select extra_cover_id from clauses_master where extra_cover_id != 0  and cover_id=? and branch_code=?) " +
						"and occmss.extra_cover_id in ("+coverId+","+coverId1+","+coverId2+")";
				
			final String result[][] = runner.multipleSelection(sqlQuery,values);
		logger.debug(EXIT);
		
		return result;
	}
	public String[][] showCoverages(final String openCoverNo)  throws BaseException
	{
		logger.info("showCoverages mailclause method()");
		logger.debug(ENTER);
			final String values[]= {openCoverNo,openCoverNo};
			final String showcovers[][] = runner.multipleSelection("select a.amend_id,a.cover_id,a.sea_options,b.cover_name,a.mode_transport_id from open_cover_sub_detail a,cover_master b where a.proposal_no=(select proposal_no from open_cover_position_master where open_cover_no = ?)  and a.amend_id=(select max(amend_id) from open_cover_sub_detail where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no = ?) and b.cover_id=a.cover_id order by a.mode_transport_id",values);
		logger.debug(EXIT);
		
		return showcovers;
	}
	public String[][] getPrintPage(final String openCoverNo,final String brokerBranch) throws BaseException
	{
		logger.info("getPrintPage mailclause method()");
		logger.debug(ENTER);
			final String values[]= {openCoverNo,openCoverNo,brokerBranch,brokerBranch};
			final String sqlQuery = "select a.mode_transport_id,a.cover_id,b.transport_description,c.cover_name from open_cover_sub_detail a,mode_of_transport b,cover_master c where a.proposal_no= (select proposal_no from open_cover_position_master where open_cover_no = ?) and a.amend_id=(select max(amend_id) from open_cover_sub_detail where proposal_no= (select proposal_no from open_cover_position_master where open_cover_no =?)) and a.status='Y' " +
					"and  c.cover_id=a.cover_id and b.mode_transport_id=a.mode_transport_id and (b.branch_code=? and c.branch_code=?) order by a.cover_id";
			final String[][] printCover = runner.multipleSelection(sqlQuery,values);
		logger.debug(EXIT);
		
		return printCover;
	}
	public String[][] getWSRCC(final String openCoverNo)  throws BaseException
	{
		logger.info("getWSRCC mailclause method()");
		logger.debug(ENTER);
			final String values[]= {openCoverNo,openCoverNo};
			final String wsrcc[][] = runner.multipleSelection("select warranty_id,warranty_description,extra_cover_id from open_cover_warranties where	 proposal_no=(select proposal_no from open_cover_position_master where open_cover_no = ?) and amend_id=(select max(amend_id) from	open_cover_warranties where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no = ?))",values);
		logger.debug(EXIT);
		
		return wsrcc;
	}
	public String[][] getFreeTextSummary(final String openCoverNo) throws BaseException
	{
		logger.info("getFreeTextSummary mailclause method()");
		logger.debug(ENTER);
			final String values[]={openCoverNo,openCoverNo};
			final String wsrcc[][] = runner.multipleSelection("select free_text_id,free_text_description from open_cover_free_text where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no = ?)  and amend_id=(select max(amend_id) from open_cover_free_text where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no = ?))",values);
		logger.debug(EXIT);
		
		return wsrcc;
	}
	public String[][] getFreeTextBulk(final String openCoverNo, final String seaoption) throws BaseException
	{
		logger.info("getFreeTextBulk mailclause method()");
		logger.debug(ENTER);
			final String values[]={openCoverNo,openCoverNo,seaoption};
			final String wsrcc[][] = runner.multipleSelection("select free_text_id,free_text_description from open_cover_free_text where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no = ?) and amend_id=(select max(amend_id) from open_cover_free_text where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no =?) and cover_id = ? )",values);
		logger.debug(EXIT);
		
		return wsrcc;
	}
	public String[][] getFreeText(final String openCoverNo, final String coverId) throws BaseException
	{
		logger.info("getFreeText mailclause method()");
		logger.debug(ENTER);
			final String values[]={openCoverNo,openCoverNo,coverId};
			final String wsrcc[][] = runner.multipleSelection("select free_text_id,free_text_description,cover_id from open_cover_free_text where proposal_no=  (select proposal_no from open_cover_position_master where open_cover_no = ?)  and amend_id=(select max(amend_id) from open_cover_free_text where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no =?) and cover_id = ?)",values);
		logger.debug(EXIT);
		
		return wsrcc;
	}
	public String[][] getExclusions(final String openCoverNo) throws BaseException
	{
		logger.info("getExclusions mailclause method()");
		logger.debug(ENTER);
			final String values[]={openCoverNo,openCoverNo};
			final String wsrcc[][] = runner.multipleSelection("select exclusion_id,exclusion_description,extra_cover_id from open_cover_exclusions where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no = ?) and amend_id=(select max(amend_id) from open_cover_exclusions where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no = ?))",values);
		logger.debug(EXIT);
		
		return wsrcc;
	}
	public String[][] getWarCoverClauses(final String coverId,final String extraCoverId,final String openCoverNo)throws BaseException
	{
		logger.info("getWarCoverClauses mailclause method()");
		logger.debug(ENTER);
			final String values[]={openCoverNo,coverId,extraCoverId,openCoverNo,coverId,extraCoverId};
			final String sql = "select occmss.clauses_id,occmss.clauses_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and (occmss.cover_id in(?) and  occmss.extra_cover_id=?) and occmss.status in ('Y') and occmss.clauses_id  in(select cm.clauses_id from clauses_master cm,extra_cover_master ecm where cm.extra_cover_id=ecm.extra_cover_id and ecm.extra_cover_id not in (0) and cm.status in ('Y','R')) and  ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no =? and (occmss.cover_id in(?) and  occmss.extra_cover_id=?) and occms.status in ('Y') and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) order by occmss.clauses_id";
			final String result[][] = runner.multipleSelection(sql,values);
		logger.debug(EXIT);
		
		return result;
	}
	public String[][] getCoverClauses(final String coverId,final String extraCoverId,final String openCoverNo)throws BaseException
	{
		logger.info("getCoverClauses mailclause method()");
		logger.debug(ENTER);
		final String values[] = {openCoverNo,coverId,openCoverNo,coverId,openCoverNo,coverId};
		final String sql ="select occmss.clauses_id,occmss.clauses_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate " +
				"from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and occmss.cover_id=?" +
				" and occmss.extra_cover_id='0' and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select " +
				"max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no =? and " +
				"occms.cover_id=? and occmss.extra_cover_id='0' and (occms.amend_id in( select max(occmss.amend_id) from " +
				"open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and occmss.cover_id=? and " +
				"occmss.extra_cover_id='0' and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-mm-YYYY')" +
				"<= to_date(sysdate,'dd-mm-YYYY')) )  and ocpms.proposal_no=occms.proposal_no and " +
				"to_date(occms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))  order by occmss.clauses_id";
		final String result[][]  = runner.multipleSelection(sql,values);
		logger.debug(EXIT);
		
		return result;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
}//Class

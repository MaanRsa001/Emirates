package com.maan.adminnew.rating;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;


import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.services.util.runner;
import com.opensymphony.xwork2.ActionContext;

public class RatingEngineDAO extends MyJdbcTemplate {
	private static final Logger logger = LogUtil.getLogger(RatingEngineDAO.class);
	private String query = "";
	Map<String, Object> session = ActionContext.getContext().getSession();
	String branchCode = (String) session.get("BranchCode");
	String countryId = (String) session.get("AdminCountryId");

	public void getTransportMode(Object[] val) {
		try {
			logger.info("Method to getTransportMode()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INS_MODE_OF_TRANSPORT");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getTransportMode() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getUpdateTransportMode(Object[] val) {
		try {
			logger.info("Method to getUpdateTransportMode()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("UPDATE_MODE_OF_TRANSPORT");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getUpdateTransportMode() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTransports(String branchCode) {
		logger.info("Method to getTransports");
		List<Map<String, Object>> transports = null;
		try {
			logger.info("Method to getTransports()");
			query = getQuery("GET_TRANSPORT_MODE_LIST");
			Object[] obj = new Object[1];
			obj[0] = branchCode;
			query +=" order by transport_description";
			logger.info("Query===>" + query);
			transports = this.mytemplate.queryForList(query, obj);
			logger.info("getTransports() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return transports;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCommcategList(String branchCode) {
		logger.info("Method to getCommcategList");
		List<Map<String, Object>> commcateg = null;
		try {
			logger.info("Method to getCommcategList()");
			query = getQuery("GET_COMM_CATEG_LIST");
			Object[] obj = new Object[1];
			obj[0] = branchCode;
			logger.info("Query===>" + query);
			commcateg = this.mytemplate.queryForList(query, obj);
			logger.info("getCommcategList() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return commcateg;
	}

	@SuppressWarnings("unchecked")
	public void getSeletedTransport(final RatingEngineBean bean) {
		logger.info("Method to getSelectedTransport()");
		@SuppressWarnings("unused")
		List editTransport = null;
		try {
			logger.info("Mode===>" + bean.getTransID());
			query = getQuery("GET_TRANSPORT_MODE");
			Object[] obj = new Object[2];
			obj[0] = branchCode;
			obj[1] = bean.getTransID();

			editTransport = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setTransDesc(rs.getString("TRANSPORT_DESCRIPTION"));
					bean.setTransRSA(rs.getString("RSACODE"));
					bean.setTransDO(rs.getString("DISPLAY_ORDER"));
					bean.setTransID(rs.getString("MODE_TRANSPORT_ID"));
					bean.setStatus(rs.getString("STATUS"));
					return null;
				}
			});
			logger.info("Mode===>" + bean.getTransDesc());
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getProductsDET(String branchCode) {
		logger.info("Method to getProductsDET");
		List<Map<String, Object>> product_Det = null;
		try {
			query = getQuery("GET_PRODUCT_DET");
			String[] args = { branchCode };
			logger.info("Query===>" + args[0]);
			product_Det = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return product_Det;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getStage() {
		logger.info("Method to getStage()");
		List<Map<String, Object>> stage_Det = null;
		final RatingEngineBean bean = new RatingEngineBean();
		try {
			query = getQuery("GET_STAGE_LIST");
			String[] args = { bean.getProductID() };
			logger.info("Query===>" + args[0]);
			stage_Det = this.mytemplate.queryForList(query, args);
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return stage_Det;
	}

	public void getNewError(Object[] val) {
		try {
			logger.info("Method to getNewError()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INS_ERROR_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getNewError() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	public void getNewConveyance(Object[] val) {
		try {
			logger.info("Method to getNewConveyance()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INS_CONVEYAN_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getNewConveyance() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getNewExtraCover(Object[] val) {
		try {
			logger.info("Method to getNewConveyance()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INS_EXTRA_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getNewConveyance() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getConveyance(String branchCode) {

		logger.info("Method to getConveyance()");
		List<Map<String, Object>> conveyance = null;
		try {
			logger.info("Method to getTransports()");
			query = getQuery("GET_CONVEYAN_MASTER");
			Object[] obj = new Object[2];
			obj[0] = branchCode;
			obj[1] = branchCode;
			logger.info("Query===>" + query);
			conveyance = this.mytemplate.queryForList(query, obj);
			logger.info("getConveyance() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return conveyance;
	}

	@SuppressWarnings("unchecked")
	public void getSeletedConveyance(final RatingEngineBean bean) {
		logger.info("Method to getSelectedConveyance()");
		@SuppressWarnings("unused")
		List editConveyance = null;
		try {
			logger.info("Mode Conveyan===>" + bean.getConveyID());
			logger.info("Mode of transport===>" + bean.getTransID());
			query = getQuery("EDIT_CONVEYAN_MASTER");
			Object[] obj = new Object[3];
			obj[0] = branchCode;
			obj[1] = bean.getConveyID();
			obj[2] = bean.getTransID();

			logger.info("Query==> " + query);
			logger.info("Args==> " + StringUtils.join(obj,","));

			editConveyance = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setSno(rs.getString("SNO"));
					bean.setConveyName(rs.getString("CONVDESC"));
					bean.setTransID(rs.getString("MODE_TRANSPORT_ID"));
					bean.setConveyRate(rs.getString("CONV_RATE"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("CORE_APP_ID"));
					bean.setEff_date(rs.getString("EFFECTIVE_DATE"));
					bean.setPrevdate(rs.getString("EFFECTIVE_DATE"));
					bean.setStatus(rs.getString("STATUS"));
					return null;
				}
			});
			logger.info("Mode===>" + bean.getConveyName());
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
			e.printStackTrace();
		}

	}

	public void getUpdateConveyance(Object[] val, String string) {
		try {
			logger.info("Method to getUpdateConveyance()");
			logger.info("BranchCode===>" + branchCode);
			if (string.equals("insert")) {
				query = getQuery("UPDATE_CONVEYAN_MASTER");
			} else if (string.equals("update")) {
				query = getQuery("UPDATE_CONVEYAN_MASTER_UPDATE");
			}
			logger.info("Args ->" + StringUtils.join(val, ","));
			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getUpdateConveyance() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getAddCountry(Object[] val) {
		try {
			logger.info("Method to getAddCountry()");

			query = getQuery("INS_COUNTRY_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getAddCountry() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCountries(String branchCode) {
		logger.info("Method to getCountries()");
		List<Map<String, Object>> countries = null;
		try {
			Object[] obj = new Object[1];
			obj[0] = branchCode;
			query = getQuery("GET_Countries");

			logger.info("Query===>" + query);
			countries = this.mytemplate.queryForList(query, obj);
			logger.info("getCountries() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return countries;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCountry() {
		logger.info("Method to getCountry()");
		List<Map<String, Object>> countryList = null;
		try {

			query = getQuery("GET_Country_List");

			logger.info("Query===>" + query);
			countryList = this.mytemplate.queryForList(query);
			logger.info("getCountry() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return countryList;

	}

	@SuppressWarnings("unchecked")
	public void getSeletedCountry(final RatingEngineBean bean) {
		logger.info("Method to getSelectedCountry()");
		@SuppressWarnings("unused")
		List editCountry = null;
		try {
			logger.info("Mode===>" + bean.getCountryID());
			query = getQuery("EDIT_COUNTRY_MASTER");
			Object[] obj = new Object[1];
			obj[0] = bean.getCountryID();

			editCountry = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setCountryName(rs.getString("COUNTRY_NAME"));
					bean
					.setCountryShortName(rs
							.getString("COUNTRY_SHORT_NAME"));
					bean.setCountryNat(rs.getString("NATIONALITY_NAME"));
					bean.setGeoRate(rs.getString("GEO_RATE"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setEff_date(rs.getString("EFFECTIVE_DATE"));
					bean.setPrevdate(rs.getString("EFFECTIVE_DATE"));
					bean.setStatus(rs.getString("STATUS"));
					return null;
				}
			});
			logger.info("Mode===>" + bean.getCountryID());
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getUpdateCountry(Object[] val, String string) {
		try {
			if (string.equals("insert")) {
				query = getQuery("UPDATE_COUNTRY_MASTER");
			} else {
				query = getQuery("UPDATE_COUNTRY_MASTER_UPDATE");
			}
			logger.info("Args -->" + StringUtils.join(val, ","));
			logger.info("Query===>" + query);
			this.mytemplate.update(query, val);
			logger.info("getUpdateCountry() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	public void getAddBank(Object[] val) {
		try {
			logger.info("Method to getAddBank()");

			query = getQuery("INS_BANK_MASTER");
			logger.info("Query===>" + query);
			this.mytemplate.update(query, val);

			logger.info("getAddBank() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBank() {
		logger.info("Method to getBank()");
		List<Map<String, Object>> bank = null;
		try {

			query = getQuery("GET_BANK");

			logger.info("Query===>" + query);
			bank = this.mytemplate.queryForList(query);
			logger.info("getBank() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return bank;
	}

	@SuppressWarnings("unchecked")
	public void getSelectedBank(final RatingEngineBean bean, String branchCode) {
		logger.info("Method to getSelectedBank()");
		@SuppressWarnings("unused")
		List editBank = null;
		try {
			logger.info("Mode===>" + bean.getBankID());
			query = getQuery("EDIT_BANK_MASTER");
			Object[] obj = new Object[2];
			obj[0] = bean.getBankID();
			obj[1]=branchCode;
			logger.info("Query===>" + query);
			editBank = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setBankName(rs.getString("BANK_NAME"));
					bean.setCountryID(rs.getString("BELONGING_COUNTRY_ID"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setEff_date(rs.getString("EFFECTIVE_DATE"));
					bean.setPrevdate(rs.getString("EFFECTIVE_DATE"));
					bean.setStatus(rs.getString("STATUS"));
					return null;
				}
			});
			logger.info("Mode===>" + bean.getBankID());
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getUpdateBank(Object[] val, String string) {
		logger.info("Method to getUpdateBank()");
		try {
			if (string.equals("insert")) {
				query = getQuery("UPDATE_BANK_MASTER");
			} else {
				query = getQuery("UPDATE_BANK_MASTER_UPDATE");
			}
			logger.info("Query===>" + query);
			logger.info("Args===>" + val);
			this.mytemplate.update(query, val);
			logger.info("getUpdateBank() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBranch() {
		logger.info("Method to getBranch()");
		List<Map<String, Object>> branch = null;
		try {

			query = getQuery("GET_BRANCH");

			logger.info("Query===>" + query);
			branch = this.mytemplate.queryForList(query);
			logger.info("getBranch() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return branch;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMaterials(String branchCode) {
		logger.info("Method to getMaterial()");
		List<Map<String, Object>> material = null;
		try {

			query = getQuery("GET_MATERIAL");
			Object[] obj = new Object[2];
			obj[0] = branchCode;
			obj[1] = branchCode;
			logger.info("Query===>" + query);
			material = this.mytemplate.queryForList(query, obj);
			logger.info("getMaterial() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return material;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getWarrates(String brachCode) {
		logger.info("Method to getWarrates()");
		List<Map<String, Object>> warrates = null;
		try {

			query = getQuery("GET_WAR_RATE");
			Object[] obj = new Object[2];
			obj[0] = branchCode;
			obj[1] = branchCode;
			logger.info("Query===>" + query);
			warrates = this.mytemplate.queryForList(query, obj);
			logger.info("getWarrates() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return warrates;

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSaleterms(String BranchCode) {
		logger.info("Method to getSaleterms()");
		List<Map<String, Object>> salesterms = null;
		try {

			query = getQuery("GET_SALE_TERM");
			Object[] obj = new Object[1];
			obj[0] = branchCode;

			logger.info("Query===>" + query);
			salesterms = this.mytemplate.queryForList(query, obj);
			logger.info("getSaleterms() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return salesterms;
	}

	public void getSeletedMaterial(final RatingEngineBean bean) {
		logger.info("Method to getSelectedMaterial()");

		try {
			query = getQuery("EDIT_MATERIAL_TYPE_MASTER");
			Object[] obj = new Object[3];
			obj[0] = branchCode;
			obj[1] = bean.getMaterialID();
			obj[2] = bean.getCoverID();

			logger.info("Query==> " + query);
			logger.info("Args==> " + StringUtils.join(obj,","));

			Map<String,Object> map = this.mytemplate.queryForMap(query,obj);

			bean.setMaterialName(map.get("MATERIAL_DESC")==null?"":map.get("MATERIAL_DESC").toString());
			bean.setMaterialRate(map.get("MATERIAL_RATE")==null?"":map.get("MATERIAL_RATE").toString());
			bean.setRemarks(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
			bean.setCode(map.get("CORE_APP_ID")==null?"":map.get("CORE_APP_ID").toString());
			bean.setEff_date(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
			bean.setPrevdate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
			bean.setStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());

		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getNewMaterial(Object[] val) {
		try {
			logger.info("Method to getNewMaterial()");

			query = getQuery("INS_MATERIAL_TYPE_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getNewMaterial() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getNewSale(Object[] val) {
		try {
			logger.info("Method to getNewSale()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INS_SALETERM");
			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getNewSale() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getUpdateMaterial(Object[] val, String string) {
		try {
			logger.info("Method to getUpdateMatrial()");

			if (string.equals("insert")) {
				query = getQuery("UPDATE_MATERIAL_TYPE_MASTER");
			} else {
				query = getQuery("UPDATE_MATERIAL_TYPE_MASTER_UPDATE");
			}

			logger.info("Arg --->"+StringUtils.join(val,","));
			logger.info("Query===>" + query);

			this.mytemplate.update(query, val);

			logger.info("getUpdateMatrial() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	public void getUpdateSale(Object[] val) {
		try {
			logger.info("Method to getUpdateSale()");

			query = getQuery("UPDATE_SALETERM_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getUpdateSale() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	public void getNewWar(Object[] val) {
		try {
			logger.info("Method to getNewWar()");

			query = getQuery("INS_WAR_RATE_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getNewWar() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	public void getSelectedWar(final RatingEngineBean bean) {
		logger.info("Method to getSelectedWar()");

		try {
			logger.info("Mode===>" + bean.getWarID());
			query = getQuery("EDIT_WAR_RATE_MASTER");
			Object[] obj = new Object[2];
			obj[0] = branchCode;
			obj[1] = bean.getWarID();

			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setTransID(rs.getString("MODE_TRANSPORT_ID"));
					bean.setWarName(rs.getString("WAR_DESC"));
					bean.setWarRate(rs.getString("WAR_RATE"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("CORE_APP_ID"));
					bean.setEff_date(rs.getString("EFFECTIVE_DATE"));
					bean.setPrevdate(rs.getString("EFFECTIVE_DATE"));
					bean.setStatus(rs.getString("STATUS"));
					return null;
				}
			});
			logger.info("Mode===>" + bean.getWarID());
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	public void getSelectedCat(final RatingEngineBean bean) {
		logger.info("Method to getSelectedCat()");

		try {
			logger.info("Mode===>" + bean.getCatID());
			query=getQuery("GET_CATLIST_EDIT");
			//			Object[] obj=new Object[3];
			//			obj[0]=branchCode;
			//			obj[1]=branchCode;
			//			obj[2]=bean.getCatID();

			Object[] obj=new Object[2];
			obj[0]=branchCode;
			obj[1]=bean.getCatID();

			logger.info("Query===>" + query);
			logger.info("Args===>" + StringUtils.join(obj,","));

			Map<String,Object> map = this.mytemplate.queryForMap(query,obj);

			if(map.size()>0) {
				bean.setCatID(map.get("COMMODITY_TYPEID")==null?"":map.get("COMMODITY_TYPEID").toString());
				bean.setCatname(map.get("DETAIL_NAME")==null?"":map.get("DETAIL_NAME").toString());
				bean.setCatrate(map.get("COMMODITY_TYPE_RATE")==null?"":map.get("COMMODITY_TYPE_RATE").toString());
				bean.setEff_date(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				bean.setPrevdate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				bean.setExp_date(map.get("EXPIRY_DATE")==null?"":map.get("EXPIRY_DATE").toString());
				bean.setStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setRemarks(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
			}

			/*this.mytemplate.query(query,obj,new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

					bean.setCatID(rs.getString("COMMODITY_TYPEID") == null ? ""
							: rs.getString("COMMODITY_TYPEID"));
					bean.setCatname(rs.getString("DETAIL_NAME") == null ? ""
							: rs.getString("DETAIL_NAME"));
					bean
							.setCatrate(rs.getString("COMMODITY_TYPE_RATE") == null ? ""
									: rs.getString("COMMODITY_TYPE_RATE"));
					bean
							.setEff_date(rs.getString("EFFECTIVE_DATE") == null ? ""
									: rs.getString("EFFECTIVE_DATE"));
					bean
							.setPrevdate(rs.getString("EFFECTIVE_DATE") == null ? ""
									: rs.getString("EFFECTIVE_DATE"));
					bean.setExp_date(rs.getString("EXPIRY_DATE") == null ? ""
							: rs.getString("EXPIRY_DATE"));
					bean.setStatus(rs.getString("STATUS") == null ? "" : rs
							.getString("STATUS"));
					bean.setRemarks(rs.getString("REMARKS") == null ? "" : rs
							.getString("REMARKS"));
					return null;
				}
			});*/
			//logger.info("Mode===>" + bean.getCatID());
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	public void getSelectedCovercomm(final RatingEngineBean bean) {
		logger.info("Method to getSelectedCovercomm()");

		try {
			logger.info("Mode===>" + bean.getCatID());
			query = getQuery("GET_COVERCOMM_EDIT");
			Object[] obj = new Object[3];
			obj[0] = bean.getCoverageID();
			obj[1] = branchCode;
			obj[2] = bean.getCommodityID();
			logger.info("Query==> " + query);
			logger.info("Args==> " + StringUtils.join(obj,","));
			Map<String,Object> resultMap = this.mytemplate.queryForMap(query,obj);
			bean.settransID(resultMap.get("MODE_TRANSPORT_ID") == null ? "" : resultMap.get("MODE_TRANSPORT_ID").toString());
			bean.setCoverageID(resultMap.get("COVER_ID") == null ? "" : resultMap.get("COVER_ID").toString());
			bean.setCoverName(resultMap.get("COVER_NAME") == null ? "" : resultMap.get("COVER_NAME").toString());
			bean.setCommodityID(resultMap.get("COMMODITY_TYPEID") == null ? "" : resultMap.get("COMMODITY_TYPEID").toString());
			bean.setCatrate(resultMap.get("CATEGORY_RATE") == null ? "" : resultMap.get("CATEGORY_RATE").toString());
			bean.setCoverRate(resultMap.get("COVER_RATE") == null ? "" : resultMap.get("COVER_RATE").toString());
			bean.setEff_date(resultMap.get("EFFECTIVE_DATE") == null ? "" : resultMap.get("EFFECTIVE_DATE").toString());
			bean.setReferralStatus(resultMap.get("REFERAL_STATUS") == null ? "" : resultMap.get("REFERAL_STATUS").toString());
			bean.setStatus(resultMap.get("STATUS") == null ? "" : resultMap.get("STATUS").toString());
			bean.setRemarks(resultMap.get("REMARKS") == null ? "" : resultMap.get("REMARKS").toString());

			/*this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean
					.settransID(rs.getString("MODE_TRANSPORT_ID") == null ? ""
							: rs.getString("MODE_TRANSPORT_ID"));
					bean.setCoverageID(rs.getString("COVER_ID") == null ? ""
							: rs.getString("COVER_ID"));
					bean.setCoverName(rs.getString("COVER_NAME") == null ? ""
							: rs.getString("COVER_NAME"));
					bean
					.setCommodityID(rs.getString("COMMODITY_TYPEID") == null ? ""
							: rs.getString("COMMODITY_TYPEID"));
					bean.setCatrate(rs.getString("CATEGORY_RATE") == null ? ""
							: rs.getString("CATEGORY_RATE"));
					bean.setCoverRate(rs.getString("COVER_RATE") == null ? ""
							: rs.getString("COVER_RATE"));
					bean
					.setEff_date(rs.getString("EFFECTIVE_DATE") == null ? ""
							: rs.getString("EFFECTIVE_DATE"));
					bean
					.setReferralStatus(rs.getString("REFERAL_STATUS") == null ? ""
							: rs.getString("REFERAL_STATUS"));
					bean.setStatus(rs.getString("STATUS") == null ? "" : rs
							.getString("STATUS"));
					bean.setRemarks(rs.getString("REMARKS") == null ? "" : rs
							.getString("REMARKS"));
					return null;
				}
			});*/
			//logger.info("Mode===>" + bean.getSno());
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	public void getSelectedSale(final RatingEngineBean bean) {
		logger.info("Method to getSelectedSale()");
		try {
			logger.info("Mode===>" + bean.getSaleID());
			query = getQuery("EDIT_SALETERM_MASTER");
			Object[] obj = new Object[2];
			obj[0] = branchCode;
			obj[1] = bean.getSaleID();

			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setSaleName(rs.getString("SALE_TERM_NAME"));
					bean.setSaleValue(rs.getString("SALE_TERM_VALUE"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setStatus(rs.getString("STATUS"));
					bean.setSaleID(rs.getString("SALE_TERM_ID"));
					return null;
				}
			});
			logger.info("Mode===>" + bean.getSaleID());
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getUpdateWar(Object[] val, String string) {

		try {
			logger.info("Method to getUpdateWar()");
			if (string.equals("update")) {
				query = getQuery("UPDATE_WAR_RATE_MASTER");
			} else {
				query = getQuery("UPDATE_WAR_RATE_MASTER_INSERT");
			}
			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getUpdateWar() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAddSale(Object[] val) {
		try {
			logger.info("Method to getAddSale()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INS_SALE_TERM");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getAddSale() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getAddTole(Object[] val) {
		try {
			logger.info("Method to getAddTole()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INS_TOLERANCE_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getAddTole() - Exit");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getUpdateTole(Object[] val) {
		try {
			logger.info("Method to getUpdateTole()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("UPDATE_TOLERANCE_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getUpdateTole() - Exit");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSelectedTole(final RatingEngineBean bean) {
		logger.info("Method to getSelectedTole()");

		try {
			logger.info("Mode===>" + bean.getToleID());
			query = getQuery("EDIT_TOLERANCE_MASTER");
			Object[] obj = new Object[2];
			obj[0] = branchCode;
			obj[1] = bean.getToleID();
			logger.info("Query==> "+query);
			logger.info("Argas==> "+StringUtils.join(obj,","));
			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setToleName(rs.getString("TOLERANCE_NAME"));
					bean.setToleValue(rs.getString("TOLERANCE_VALUE"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("RSACODE"));

					bean.setStatus(rs.getString("STATUS"));
					bean.setToleID(rs.getString("TOLERANCE_ID"));
					return null;
				}
			});
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public int getNameExist(Object[] val, String str)
	throws EmptyResultDataAccessException,
	IncorrectResultSizeDataAccessException {
		int checkList = 0;
		try {
			logger.info("Method to getNameExist()");
			logger.info("BranchCode===>" + branchCode);
			if (str.equals("transport")) {
				query = getQuery("Check_name_mode_of_transport");
			} else if (str.equals("convey")) {
				query = getQuery("Check_name_convey");
			} else if (str.equals("country")) {
				query = getQuery("Check_name_Country");
			} else if (str.equals("bank")) {
				query = getQuery("Check_name_Bank");
			} else if (str.equals("material")) {
				query = getQuery("Check_name_material");
			} else if (str.equals("war")) {
				query = getQuery("Check_name_War");
			} else if (str.equals("sale")) {
				query = getQuery("Check_name_Sale");
			} else if (str.equals("tole")) {
				query = getQuery("Check_name_tole");
			} else if (str.equals("warranty")) {
				query = getQuery("Check_name_warranty");
			} else if (str.equals("constant")) {
				query = getQuery("Check_constant_name");
			} else if (str.equals("countryDet")) {
				query = getQuery("Check_countryDet_name");
			} else if (str.equals("commodity")) {
				query = getQuery("Check_commodity_name");
			} else if (str.equals("currency")) {
				query = getQuery("Check_currency_name");
			} else if (str.equals("exchange")) {
				query = getQuery("Check_exchange_name");
			} else if (str.equals("cover")) {
				query = getQuery("Check_cover_name");
			} else if (str.equals("clauseid")) {
				query = getQuery("Check_display_order");
			} else if (str.equals("error")) {
				query = getQuery("Check_name_error");
			} else if (str.equals("extra")) {
				query = getQuery("Check_extra_name");
			} else if (str.equals("exclusion")) {
				query = getQuery("Check_exclusion_name");
			} else if (str.equals("vessel")) {
				query = getQuery("Check_vessel_name");
			} else if (str.equals("city1")) {
				query = getQuery("Check_city_name");
			}else if (str.equals("country1")) {
				query = getQuery("Check_country1");
			}

			checkList = this.mytemplate.queryForObject(query,Integer.class, val);
			logger.info("Query===>" + query);
			logger.info("getNameExist() - Exit");
		}

		catch (EmptyResultDataAccessException e) {
			logger.info("Name Not Exist");

		} catch (IncorrectResultSizeDataAccessException e) {
			logger.info("Name Not Exist");

		}
		return checkList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVesselList() {
		List<Map<String, Object>> vesselList = null;
		try {
			String query = getQuery("GET_VESSEL_LIST");
			logger.info("Query===>" + query);

			vesselList = this.mytemplate.queryForList(query);
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return vesselList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVesselListAjax(String searchBy, String searchValue) {
		List<Map<String, Object>> vesselList = null;
		String query = "";
		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_VESSEL_LIST_BY_NAME");
			}

			logger.info("Query===>" + query);

			vesselList = this.mytemplate.queryForList(query,
					new Object[] { searchValue });
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return vesselList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAdminTransportList(String branchCode) {
		List<Map<String, Object>> transportList = null;
		try {
			String query = getQuery("GET_TRANSPORT_LIST");
			logger.info("Query===>" + query);
			logger.info("branchCode===>" + branchCode);
			transportList = this.mytemplate.queryForList(query,
					new Object[] { branchCode });
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return transportList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getComExList(String branchCode) {
		List<Map<String, Object>> comExList = null;
		try {
			String query = getQuery("GET_COMEX_LIST");
			logger.info("Query===>" + query);
			logger.info("branchCode===>" + branchCode);
			comExList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, branchCode });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comExList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getConveyList(String branchCode) {
		List<Map<String, Object>> conveyanceList = null;
		try {
			query = getQuery("GET_CONVEYAN_MASTER_LIST");
			Object[] obj = new Object[1];
			obj[0] = branchCode;
			query += "order by CONVDESC";
			logger.info("Query===>" + query);
			logger.info("branchCode===>" + branchCode);
			conveyanceList = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return conveyanceList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getConveyList(String branchCode,
			String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List conveyanceList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_CONVEYAN_MASTER_LIST");
			if (searchField != null && searchString != null && searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				
				if ("conveyName".equalsIgnoreCase(searchField))
					query += " and upper(E.CONVDESC) " + str;
				else if ("transDesc".equalsIgnoreCase(searchField))
					query += " and upper(MT.TRANSPORT_DESCRIPTION)" + str;

				obj = new Object[] { branchCode, searchString };
			} else {
				obj = new Object[] {branchCode };
			}
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k).toString());
			}
			query += "order by CONVDESC";
			logger.info((new StringBuilder("Query==>")).append(query).toString());
			conveyanceList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rs, final int idVal)
				throws SQLException {
					RatingEngineBean bean = new RatingEngineBean();
					bean.setTransID(rs.getString("MODE_TRANSPORT_ID") == null ? "": rs.getString("MODE_TRANSPORT_ID"));
					bean.setTransDesc(rs.getString("TRANSPORT_DESCRIPTION") == null ? "" : rs.getString("TRANSPORT_DESCRIPTION"));
					bean.setConveyID(rs.getString("CONV_MEAN") == null ? "" : rs.getString("CONV_MEAN"));
					bean.setConveyName(rs.getString("CONVDESC") == null ? "": rs.getString("CONVDESC"));
					bean.setConveyRate(rs.getString("CONV_RATE") == null ? "": rs.getString("CONV_RATE"));
					bean.setEff_date(rs.getString("EFFECTIVE_DATE") == null ? "": rs.getString("EFFECTIVE_DATE"));
					bean.setStatus(rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
					bean.setEdit((rs.getString("CONV_MEAN") == null ? "" : rs.getString("CONV_MEAN"))+ "&transID="+ (rs.getString("MODE_TRANSPORT_ID") == null ? "": rs.getString("MODE_TRANSPORT_ID")));
					return bean;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conveyanceList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getExtraCoverList(String branchCode) {
		List<Map<String, Object>> extraCoverList = null;
		try {
			query = getQuery("GET_EXTRA_COVER_LIST");
			Object[] obj = new Object[2];
			obj[0] = branchCode;
			obj[1] = branchCode;

			logger.info("Query===>" + query);
			logger.info("branchCode===>" + branchCode);
			extraCoverList = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return extraCoverList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getToleranceList(String branchCode) {
		List<Map<String, Object>> toleranceList = null;
		try {
			query = getQuery("GET_TOLERANCE_MASTER_LIST");
			Object[] obj = new Object[1];
			obj[0] = branchCode;

			logger.info("Query===>" + query);
			logger.info("branchCode===>" + branchCode);
			toleranceList = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toleranceList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMaterialList(String branchCode) {
		List<Map<String, Object>> materialList = null;
		try {
			query = getQuery("GET_MATERIAL_MASTER_LIST");
			Object[] obj = new Object[1];
			obj[0] = branchCode;

			logger.info("Query===>" + query);
			logger.info("branchCode===>" + branchCode);
			materialList = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return materialList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTransportListAjax(String branchCode,
			String searchBy, String searchValue) {
		List<Map<String, Object>> transportList = null;
		String query = "";
		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_TRANSPORT_LIST_BY_NAME");
			}

			logger.info("Query===>" + query);
			logger.info("branchCode===>" + branchCode);
			transportList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transportList;
	}

	/*
	 * @SuppressWarnings("unchecked") public
	 * List<RatingEngineBean>getConveyListAjax(String branchCode,String
	 * searchBy,String searchValue) { List<RatingEngineBean>
	 * conveyanceList=null; String query=""; try { if("name".equals(searchBy)) {
	 * query=getQuery("GET_CONVEYAN_LIST_BY_NAME"); }
	 * 
	 * logger.info("Query==>"+query); logger.info("branchCode===>" +
	 * branchCode); conveyanceList=this.mytemplate.queryForList(query,new
	 * Object[]{branchCode,searchValue}); } catch(Exception e) {
	 * e.printStackTrace(); } return conveyanceList; }
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getConveyModeListAjax(String branchCode, String mode) {
		List<Map<String, Object>> conveyanceList = null;
		String query = "";
		int id = Integer.parseInt(mode);
		try {
			query = getQuery("GET_CONVEYAN_MODE_LIST");

			logger.info("Query==>" + query);
			logger.info("branchCode===>" + branchCode);
			conveyanceList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, id });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conveyanceList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCountryListAjax(String searchBy, String searchValue) {
		List<Map<String, Object>> countryList = null;
		String query = "";
		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_COUNTRY_LIST_BY_NAME");
			}

			logger.info("Query==>" + query);

			countryList = this.mytemplate.queryForList(query,
					new Object[] { searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBankList() {
		logger.info("Method to getBankList()");
		List<Map<String, Object>> bankList = null;
		try {

			query = getQuery("GET_BANK_LIST");

			logger.info("Query===>" + query);
			bankList = this.mytemplate.queryForList(query);
			logger.info("getBankList() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return bankList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBankListAjax(String searchBy, String searchValue) {
		List<Map<String, Object>> bankList = null;
		String query = "";
		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_BANK_LIST_BY_NAME");
			}

			logger.info("Query==>" + query);

			bankList = this.mytemplate.queryForList(query,
					new Object[] { searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMaterialListAjax(String branchCode, String searchBy,
			String searchValue) {
		List<Map<String, Object>> materialList = null;
		String query = "";
		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_MATERIAL_LIST_BY_NAME");
			}

			logger.info("Query==>" + query);

			materialList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return materialList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getWarrateList(String branchCode) {
		logger.info("Method to getWarrateList()");
		List<Map<String, Object>> warrateList = null;
		try {

			query = getQuery("GET_WARRATE_LIST");

			logger.info("Query===>" + query);
			warrateList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, branchCode });
			logger.info("getWarrateList() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return warrateList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getWarrateListAjax(String branchCode, String searchBy,
			String searchValue) {
		List<Map<String, Object>> warrateList = null;
		String query = "";
		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_WAR_LIST_BY_NAME");
			} else if ("desc".equals(searchBy)) {
				query = getQuery("GET_WAR_LIST_BY_DESC");
			}

			logger.info("Query==>" + query);

			warrateList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, branchCode, branchCode, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return warrateList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSaletermList(String branchCode) {
		logger.info("Method to getSaletermList()");
		List<Map<String, Object>> saletermList = null;
		try {

			query = getQuery("GET_SALETERM_LIST");

			logger.info("Query===>" + query);
			saletermList = this.mytemplate.queryForList(query,
					new Object[] { branchCode });
			logger.info("getSaletermList() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return saletermList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSaletermListAjax(String branchCode, String searchBy,
			String searchValue) {
		List<Map<String, Object>> saletermList = null;
		String query = "";
		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_SALETERM_LIST_BY_NAME");
			}

			logger.info("Query==>" + query);

			saletermList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saletermList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getToleranceListAjax(String branchCode,
			String searchBy, String searchValue) {
		List<Map<String, Object>> toleranceList = null;
		String query = "";
		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_TOLERANCE_LIST_BY_NAME");
			}

			logger.info("Query==>" + query);

			toleranceList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toleranceList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getComExListAjax(String branchCode, String searchBy,
			String searchValue) {
		List<Map<String, Object>> comExList = null;
		String query = "";
		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_COMEX_LIST_BY_NAME");
			} else if ("value".equals(searchBy)) {
				query = getQuery("GET_COMEX_LIST_BY_VALUE");
			}

			logger.info("Query==>" + query);

			comExList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comExList;
	}

	public void getAddComEx(Object[] val) {
		try {
			logger.info("Method to getAddComEx()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INS_COMEX");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getAddComEx() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getUpdateComEx(Object[] val, String string) {
		try {
			logger.info("Method to getUpdateComEx()");
			logger.info("BranchCode===>" + branchCode);
			//"INSERT INTO COMMODITY_EXCESS_PREMIUM (SNO,START_SUM_INSURED,END_SUM_INSURED,DEDUCTIBLE , RATE, AMEND_ID, EFFECTIVE_DATE,BRANCH_CODE,STATUS) VALUES((SELECT MAX(SNO)+1 FROM COMMODITY_EXCESS_PREMIUM),?,?,?,?,( SELECT MAX(AMEND_ID)+1 FROM COMMODITY_EXCESS_PREMIUM WHERE BRANCH_CODE=?  AND START_SUM_INSURED=? AND END_SUM_INSURED=? ),TO_DATE(?,'MM/DD/YYYY'),?,?)" 			
			if (string.equals("insert"))
				query = getQuery("UPDATE_COMEX_INSERT");
			else if (string.equals("update"))
				query = getQuery("UPDATE_COMEX");
			logger.info("Values ->"+StringUtils.join(val,","));
			logger.info("Query===>" + query);
			this.mytemplate.update(query, val);
			logger.info("getUpdateComEx() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getselectedComEx(final RatingEngineBean bean) {

		logger.info("Method to getselectedComEx()");

		try {
			logger.info("Mode===>" + bean.getComExID());
			query = getQuery("EDIT_COMEX");
			Object[] obj = new Object[2];
			obj[0] = branchCode;
			obj[1] = bean.getComExID();

			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setStartAmt(rs.getString("START_SUM_INSURED"));
					bean.setEndAmt(rs.getString("END_SUM_INSURED"));
					bean.setDeductible(rs.getString("DEDUCTIBLE"));
					bean.setComExRate(rs.getString("RATE"));
					bean.setEff_date(rs.getString("EFFECTIVE_DATE"));
					bean.setPrevdate(rs.getString("EFFECTIVE_DATE"));
					//bean.setCode(rs.getString("RSACODE"));
					bean.setStatus(rs.getString("STATUS"));
					bean.setComExID(rs.getString("SNO"));
					return null;
				}
			});
			logger.info("Mode===>" + bean.getComExID());
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getSelectedVessel(final RatingEngineBean bean) {
		logger.info("Method to getSelectedVessel()");

		try {
			logger.info("Mode===>" + bean.getVesselID());
			query = getQuery("EDIT_VESSEL");
			Object[] obj = new Object[1];
			obj[0] = bean.getVesselID();

			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setVesselName(rs.getString("VESSEL_NAME"));
					bean.setVesselClass(rs.getString("VESSEL_CLASS"));
					bean.setVesselsYear(rs.getString("MANUFACTURE_YEAR"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setStatus(rs.getString("STATUS"));
					bean.setVesselID(rs.getString("VESSEL_ID"));
					bean.setVesselType(rs.getString("VESSEL_TYPE"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setVesselRate(rs.getString("VESSEL_RATE"));
					return null;
				}
			});
			logger.info("Mode===>" + bean.getVesselID());
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getExtraCoverListAjax(String branchCode,
			String searchBy, String searchValue) {
		List<Map<String, Object>> extraCoverList = null;
		String query = "";
		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_EXTRACOVER_LIST_BY_NAME");
			}

			logger.info("Query==>" + query);

			extraCoverList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, branchCode, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return extraCoverList;
	}

	public void getSelectedExtraCover(final RatingEngineBean bean) {
		logger.info("Method to getSelectedExtraCover()");

		try {
			query = getQuery("EDIT_EXTRA_COVER_MASTER");
			Object[] obj = new Object[2];
			obj[0] = branchCode;
			obj[1] = bean.getExtraCoverId();

			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setExtraCoverName(rs.getString("EXTRA_COVER_NAME"));
					bean.setTransID(rs.getString("MODE_TRANSPORT_ID"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setStatus(rs.getString("STATUS"));
					bean.setExtraCoverId(rs.getString("EXTRA_COVER_ID"));
					return null;
				}
			});

		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getUpdateExraCover(Object[] val) {
		try {
			logger.info("Method to getUpdateConveyance()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("UPDATE_EXTRA_COVER_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getUpdateConveyance() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCityList(RatingEngineBean bean) {
		List<Map<String, Object>> cityList = null;
		try {
			query = getQuery("GET_CITY_LIST");

			logger.info("Query===>" + query);
			logger.info("branchCode===>" + branchCode);
			cityList = this.mytemplate.queryForList(query);
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return cityList;

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCityListAjax(String searchBy, String searchValue) {
		List<Map<String, Object>> cityList = null;
		String query = "";
		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_CITY_LIST_BY_NAME");
			}

			logger.info("Query==>" + query);

			cityList = this.mytemplate.queryForList(query,
					new Object[] { searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList;

	}

	public void getNewCity(Object[] val) {
		try {
			logger.info("Method to getNewCiti()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INS_CITI_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getNewCiti() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getSelectedCity(final RatingEngineBean bean) {
		logger.info("Method to getSelectedCity()");

		try {
			query = getQuery("EDIT_CITY_MASTER");
			Object[] obj = new Object[1];
			obj[0] = bean.getCityID();
			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setCityName(rs.getString("CITY_NAME"));
					bean.setCountryID(rs.getString("COUNTRY_ID"));

					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setStatus(rs.getString("STATUS"));
					bean.setCityID(rs.getString("CITY_ID"));
					System.out.println("Country" + rs.getString("CITY_ID"));
					return null;
				}
			});
			logger.info("Query==>" + query);
			logger.info("Method to getSelectedCity()-Exit");
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getVessel(Object[] val) {
		try {
			logger.info("Method to getVessel()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INS_VESSEL");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("Args"+StringUtils.join(val,","));
			logger.info("getVessel() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getUpdateVessel(Object[] val) {
		try {
			logger.info("Method to getUpdateVessel()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("UPDATE_VESSEL");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getUpdateVessel() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getNewWarranty(Object[] val) {
		try {
			logger.info("Method to getNewWarranty()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INS_WARRANTY");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getNewWarranty() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getUpdateWarranty(Object[] val) {
		try {
			logger.info("Method to getUpdateWarranty()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("UPDATE_WARRANTY");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getUpdateWarranty() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getSelectedWarranty(final RatingEngineBean bean) {

		logger.info("Method to getSelectedWarranty()");

		try {
			query = getQuery("EDIT_WARRANTY");
			Object[] obj = new Object[2];
			obj[0] = branchCode;
			obj[1] = bean.getWarrantyID();

			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setWarrantyDesc(rs.getString("WARRANTY_DESCRIPTION"));
					bean.setWarrantyID(rs.getString("WARRANTY_ID"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setStatus(rs.getString("STATUS"));

					return null;
				}
			});
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getWarrantyList(String branchCode) {
		List<Map<String, Object>> warrantyList = null;
		String query = "";
		try {
			query = getQuery("GET_WARRANTY_LIST");
			logger.info("Query==>" + query);

			warrantyList = this.mytemplate.queryForList(query,
					new Object[] { branchCode });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return warrantyList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getWarrantyListAjax(String branchCode, String searchBy,
			String searchValue) {
		List<Map<String, Object>> warrantyList = null;
		String query = "";
		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_WARRANTY_LIST_NAME");
			}

			logger.info("Query==>" + query);

			warrantyList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return warrantyList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getConstantList(String branchCode) {
		List<Map<String, Object>> constantList = null;
		String query = "";
		try {
			query = getQuery("GET_CONSTANT_LIST");
			logger.info("Query==>" + query);

			constantList = this.mytemplate.queryForList(query,
					new Object[] { branchCode });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return constantList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getConstantListAjax(String branchCode, String searchBy,
			String searchValue) {
		List<Map<String, Object>> constantList = null;
		String query = "";
		try {
			query = getQuery("GET_CONSTANT_LIST_NAME");
			logger.info("Query==>" + query);

			constantList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return constantList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCategories(String branchCode) {
		logger.info("Method to getCategories");
		List<Map<String, Object>> categories = null;
		try {
			logger.info("Method to getCategories()");
			query = getQuery("GET_CATEGORY");
			Object[] obj = new Object[1];
			obj[0] = branchCode;
			logger.info("Query===>" + query);
			categories = this.mytemplate.queryForList(query, obj);
			logger.info("getCategories() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return categories;
	}
	public void getUpdateConstantMaster(RatingEngineBean bean) {
		try {
			String query = "";
			Object[] obj = null;
			if("add".equals(bean.getMode())) {
				query = getQuery("INS_CONSTANT_MASTER");
				obj = new Object[5];
				obj[0] = branchCode;
				obj[1] = bean.getCategoryName();
				obj[2] = bean.getRsacode();
				obj[3] = bean.getStatus();
				obj[4] = branchCode;
			}
			else {
				query = getQuery("UPD_CONSTANT_MASTER");
				obj = new Object[5];
				obj[0] = bean.getCategoryName();
				obj[1] = bean.getStatus();
				obj[2] = bean.getRsacode();
				obj[3] = bean.getCategoryId();
				obj[4] = branchCode;
			}
			logger.info("Query==> " + query);
			logger.info("Args==> " + StringUtils.join(obj,","));

			this.mytemplate.update(query,obj);
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	public void getNewConstant(Object[] val) {
		try {
			logger.info("Method to getNewConstant()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INS_CONSTANT");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getNewConstant() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getUpdateConstant(Object[] val) {
		try {
			logger.info("Method to getUpdateConstant()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("UPDATE_CONSTANT");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getUpdateConstant() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void getSelectedConstantMaster(RatingEngineBean bean) {
		try {
			String query = getQuery("EDIT_CONSTANT_MASTER");
			Object[] obj = new Object[2];
			obj[0] = bean.getCategoryId();
			obj[1] = branchCode;

			logger.info("Query==> " + query);
			logger.info("Args==> " + StringUtils.join(obj,","));

			Map<String,Object> map = this.mytemplate.queryForMap(query,obj);
			if(map!=null && map.size()>0) {
				bean.setCategoryName(map.get("CATEGORY_NAME")==null?"":map.get("CATEGORY_NAME").toString());
				bean.setStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
				bean.setRsacode(map.get("RSACODE")==null?"":map.get("RSACODE").toString());
			}
		}
		catch(Exception exception) {
			logger.info("EXCEPTION @ getUpdateConstant { " + exception + " } ");
		}
	}
	public void getSelectedConstant(final RatingEngineBean bean) {
		logger.info("Method to getSelectedConstant()");

		try {
			query = getQuery("EDIT_CONSTANT");
			Object[] obj = new Object[3];
			obj[0] = bean.getCategoryID();
			obj[1] =bean.getConstantID(); 
			obj[2] = branchCode;

			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setDetailName(rs.getString("DETAIL_NAME"));
					bean.setPercentage(rs.getString("PERCENT"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setStatus(rs.getString("STATUS"));
					bean.setConstantID(rs.getString("CATEGORY_DETAIL_ID"));
					bean.setCategoryID(rs.getString("CATEGORY_ID"));
					return null;
				}
			});
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getUpdateCity(Object[] val) {

		try {
			logger.info("Method to getUpdateCity()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("UPDATE_CITY_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getUpdateCity() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCountryDetList() {
		List<Map<String, Object>> countryDetList = null;
		String query = "";
		try {
			query = getQuery("GET_COUNTRY_DETAIL");
			logger.info("Query==>" + query);
			query += "order by COUNTRY_NAME";
			countryDetList = this.mytemplate.queryForList(query,
					new Object[] { countryId });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryDetList;
	}

	public List<RatingEngineBean> getCountryDetList(String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List countryDetList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_COUNTRY_DETAIL");
			if (StringUtils.isNotBlank(searchField) && StringUtils.isNotBlank(searchString) && StringUtils.isNotBlank(searchOper)) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("countryID".equalsIgnoreCase(searchField))
					query += " and upper(C.COUNTRY_NAME) " + str;
				else if ("transDesc".equalsIgnoreCase(searchField))
					query += " and upper(MT.TRANSPORT_DESCRIPTION)" + str;
				else if ("startPlace".equalsIgnoreCase(searchField))
					query += " and upper(C.STARTING_PLACE)" + str;
				else if ("endPlace".equalsIgnoreCase(searchField))
					query += " and upper(C.ENDING_PLACE)" + str;

				obj = new Object[] { countryId, searchString };
			} else {
				obj = new Object[] { countryId };
			}
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			query += " order by COUNTRY_NAME";
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			countryDetList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rs, final int idVal)
				throws SQLException {
					RatingEngineBean bean = new RatingEngineBean();
					bean
					.setCountryDetID(rs.getString("COUNTRY_ID") == null ? "": rs.getString("COUNTRY_ID"));
					bean.setCountryID(rs.getString("COUNTRY_NAME") == null ? "": rs.getString("COUNTRY_NAME"));
					bean.setWarRate(rs.getString("WAR_RATE") == null ? "" : rs.getString("WAR_RATE"));
					bean.setStartPlace(rs.getString("STARTING_PLACE") == null ? "": rs.getString("STARTING_PLACE"));
					bean.setEndPlace(rs.getString("ENDING_PLACE") == null ? "": rs.getString("ENDING_PLACE"));
					bean.setStatus(rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
					bean.setEdit((rs.getString("COUNTRY_ID") == null ? "" : rs.getString("COUNTRY_ID"))+ "&countryDetSNO="+ (rs.getString("SNO__") == null ? "" : rs.getString("SNO__")));
					return bean;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryDetList;
	}

	/*@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCommodityList(Object[] val) {
		List<Map<String, Object>> commodityList = null;
		String query = "";
		try {
			query = getQuery("GET_COMMODITY_DETAIL");
			query += "ORDER BY CM.COMMODITY_NAME";
			logger.info("Query==>" + query);

			commodityList = this.mytemplate.queryForList(query, val);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commodityList;
	}*/

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCommodityListAjax(Object[] val, String searchBy) {
		List<Map<String, Object>> commodityList = null;
		String query = "";
		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_COMMODITY_DETAIL_NAME");
			}
			logger.info("Query==>" + query);

			commodityList = this.mytemplate.queryForList(query, val);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commodityList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCountryDetListAjax(String searchBy,
			String searchValue) {
		List<Map<String, Object>> countryDetList = null;
		String query = "";
		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_COUNTRY_DETAIL_NAME");
			}

			logger.info("Query==>" + query);

			countryDetList = this.mytemplate.queryForList(query, new Object[] {
					countryId, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryDetList;
	}

	public void getNewCountryDet(Object[] val) {

		try {
			logger.info("Method to getNewCountryDet()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INS_COUNTRYDET");
			logger.info("Query===>" + query);
			this.mytemplate.update(query, val);
			logger.info("getNewCountryDet() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSelectedCountryDet(final RatingEngineBean bean) {
		logger.info("Method to getSelectedCountryDet() - Enter");

		try {
			query = getQuery("EDIT_COUNTRY_DET");
			Object[] obj = new Object[2];
			obj[0] = bean.getCountryDetID();
			obj[1] = bean.getCountryDetSNO();

			logger.info("Country Detail ID" + obj[0]);
			logger.info("Country Detail Sno" + obj[1]);
			logger.info("Query" + query);

			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setCountryDetSNO(rs.getString("SNO__"));
					bean.setCountryDetID(rs.getString("COUNTRY_ID"));
					bean.setCountryID(rs.getString("COUNTRY_NAME"));
					bean
					.setCountryShortName(rs
							.getString("COUNTRY_SHORT_NAME"));
					bean.setWarRate(rs.getString("WAR_RATE"));
					bean.setStartPlace(rs.getString("STARTING_PLACE"));
					bean.setSp(rs.getString("SP_WARRANTIES_CONDITIONS"));
					bean.setEndPlace(rs.getString("ENDING_PLACE"));
					bean.setEp(rs.getString("EP_WARRANTIES_CONDITIONS"));
					bean.setStatus(rs.getString("STATUS"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setCountryNat(rs.getString("NATIONALITY_NAME"));
					bean.setEff_date(rs.getString("EFFECTIVE_DATE"));
					bean.setPrevdate(rs.getString("EFFECTIVE_DATE"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCrossvoyageyn(rs.getString("CROSS_VOYAGE_YN"));
					bean.setCrossvoyageOrgin(rs.getString("CROSS_VOYAGE_ORG_COUNTRY"));
					bean.setCrossvoyageDestn(rs.getString("CROSS_VOYAGE_DEST_COUNTRY")); 
					return null;
				}
			});
		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		logger.info("Method to getSelectedCountryDet()- Exit");
	}

	public void getUpdateCountryDet(Object[] val, String string) {
		try {
			logger.info("Method to getUpdateCountryDet()");
			logger.info("BranchCode===>" + branchCode);
			if (string.equals("insert")) {
				query = getQuery("UPDATE_COUNTRYDET");
			} else if (string.equals("update")) {
				query = getQuery("UPDATE_COUNTRYDET_UPDATE");
			}
			logger.info("Query===>" + query);
			logger.info("Args"+StringUtils.join(val,","));
			int result = this.mytemplate.update(query, val);
			logger.info("Result==> " + result);
			logger.info("getUpdateCountryDet() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String[][] getClause(String branch, String clauseid,
			String clausevalue) {
		String[][] comDetails = new String[0][0];
		System.out.println("clausevalue" + clausevalue);
		String sql = "";
		String args[] = new String[2];
		String args1[] = new String[1];
		try {

			if (clausevalue.equalsIgnoreCase("C")) {
				args[0] = branch;
				args[1] = clauseid;
				sql = "select rsacode,clauses_description from CLAUSES_MASTER where  branch_code=?  and  EXTRA_COVER_ID=0 and COVER_ID=? ";
				comDetails = runner.multipleSelection(sql, args);
			} else if (clausevalue.equalsIgnoreCase("W")) {
				args1[0] = branch;
				sql = "select WARRANTY_ID,WARRANTY_DESCRIPTION from WARRANTY_MASTER where status='Y' and branch_code=? order by WARRANTY_ID";
				comDetails = runner.multipleSelection(sql, args1);
			} else if (clausevalue.equalsIgnoreCase("E")) {
				args1[0] = branch;

				sql = "select EXCLUSION_ID,EXCLUSION_DESCRIPTION from EXCLUSION_MASTER where status in ('Y','R') and branch_code=? order by EXCLUSION_ID";
				comDetails = runner.multipleSelection(sql, args1);
			} else {
				args[0] = branch;
				args[1] = clauseid;
				sql = "select rsacode,clauses_description from CLAUSES_MASTER where branch_code=? and COVER_ID=? and  EXTRA_COVER_ID<>0 order by COVER_ID";
				comDetails = runner.multipleSelection(sql, args);
			}
			System.out.println("hello" + sql + "clausevalue" + clausevalue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return comDetails;
	}

	public void getCommodityRate(RatingEngineBean bean, String modeOfTransport,
			String coverage, String conveyanceType, String destCountryId,
			String commodity, String fragile, double localSumInsured,
			String branch) {
		double baseRate = 0, factor1 = 0, factor2 = 0;
		double factor3 = 0;
		double factor4 = 0;
		double factor5 = 0;
		double wsrccRates = 0;
		double result = 0;
		String returnVal = "";// , query ="", value = "";
		try {

			fragile = fragile.equalsIgnoreCase("on") ? "1" : "2";
			System.out.println("Fragile:" + fragile);
			System.out.println("commodity:" + commodity);

			String commodityReferal = runner
			.singleSelection("select status from commoditymaster where  COMMODITY_ID = "
					+ commodity
					+ " and branch_code = "
					+ branch
					+ " and amend_id= (select max(amend_id) from commoditymaster where branch_code = "
					+ branch + " and commodity_id=" + commodity + " )");

			System.out.println("REFEREAL::" + commodityReferal);
			// if(!commodityReferal.equalsIgnoreCase("R")){

			String baseVal = runner
			.singleSelection("SELECT COVER_RATE FROM COVER_MASTER WHERE COVER_ID ='"
					+ coverage
					+ "' AND BRANCH_CODE='"
					+ branch
					+ "' AND BRANCH_CODE='"
					+ branch
					+ "' AND AMEND_ID=(SELECT max(amend_id) from COVER_MASTER where cover_id='"
					+ coverage
					+ "'  AND BRANCH_CODE='"
					+ branch
					+ "' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)) AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)");
			baseRate = Double.parseDouble(baseVal.equalsIgnoreCase("") ? "0"
					: baseVal);

			String fact1Val = runner
			.singleSelection("SELECT RATE from COMMODITY_EXCESS_PREMIUM where START_SUM_INSURED<="
					+ localSumInsured
					+ " and  END_SUM_INSURED>="
					+ localSumInsured
					+ " AND BRANCH_CODE='"
					+ branch
					+ "' AND AMEND_ID=(SELECT max(amend_id) from COMMODITY_EXCESS_PREMIUM where START_SUM_INSURED<="
					+ localSumInsured
					+ " and  END_SUM_INSURED>="
					+ localSumInsured
					+ "  AND BRANCH_CODE='"
					+ branch
					+ "' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)) AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)");
			factor1 = Double.parseDouble(fact1Val.equalsIgnoreCase("") ? "0"
					: fact1Val);

			String fact2Val = runner
			.singleSelection("SELECT   MATERIAL_RATE  FROM   MATERIAL_TYPE_MASTER WHERE   MATERIAL_ID ='"
					+ fragile
					+ "' AND BRANCH_CODE ='"
					+ branch
					+ "' AND AMEND_ID= (SELECT   MAX (amend_id) FROM   MATERIAL_TYPE_MASTER WHERE   MATERIAL_ID ='"
					+ fragile
					+ "' AND BRANCH_CODE ='"
					+ branch
					+ "'  AND TRUNC (EFFECTIVE_DATE) <= TRUNC (SYSDATE)) AND STATUS = 'Y' AND TRUNC (EFFECTIVE_DATE) <= TRUNC (SYSDATE)");
			factor2 = Double.parseDouble(fact2Val.equalsIgnoreCase("") ? "0"
					: fact2Val);

			String fact3Val = runner
			.singleSelection("SELECT GEO_RATE  FROM  country_master where country_id=41  AND AMEND_ID=(SELECT max(amend_id) from country_master where country_id=41 AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)) AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)");
			factor3 = Double.parseDouble(fact3Val.equalsIgnoreCase("") ? "0"
					: fact3Val);

			String fact4Val = runner
			.singleSelection("SELECT CONV_RATE FROM CONVEYAN_MASTER WHERE CONV_MEAN ='"
					+ conveyanceType
					+ "' AND MODE_TRANSPORT_ID = "
					+ modeOfTransport
					+ " AND BRANCH_CODE='"
					+ branch
					+ "' AND AMEND_ID=(SELECT max(amend_id) from CONVEYAN_MASTER where CONV_MEAN ='"
					+ conveyanceType
					+ "' AND MODE_TRANSPORT_ID = "
					+ modeOfTransport
					+ "  AND BRANCH_CODE='"
					+ branch
					+ "' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)) AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)");
			System.out
			.println("SELECT CONV_RATE FROM CONVEYAN_MASTER WHERE CONVDESC ='"
					+ conveyanceType
					+ "' AND MODE_TRANSPORT_ID = "
					+ modeOfTransport
					+ " AND BRANCH_CODE='"
					+ branch
					+ "' AND AMEND_ID=(SELECT max(amend_id) from CONVEYAN_MASTER where CONVDESC ='"
					+ conveyanceType
					+ "' AND MODE_TRANSPORT_ID = "
					+ modeOfTransport
					+ "  AND BRANCH_CODE='"
					+ branch
					+ "') AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)");
			factor4 = Double.parseDouble(fact4Val.equalsIgnoreCase("") ? "0"
					: fact4Val);

			String fact5Val = runner
			.singleSelection("SELECT commodity_rate FROM commoditymaster WHERE commodity_id ='"
					+ commodity
					+ "' AND BRANCH_CODE='"
					+ branch
					+ "' AND AMEND_ID=(SELECT max(amend_id) from commoditymaster where  commodity_id ='"
					+ commodity
					+ "'  AND BRANCH_CODE='"
					+ branch
					+ "' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)) AND STATUS='"
					+ commodityReferal
					+ "' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)");
			System.out
			.println("SELECT commodity_rate FROM commoditymaster WHERE commodity_id ='"
					+ commodity
					+ "' AND BRANCH_CODE='"
					+ branch
					+ "' AND AMEND_ID=(SELECT max(amend_id) from commoditymaster where  commodity_id ='"
					+ commodity
					+ "'  AND BRANCH_CODE='"
					+ branch
					+ "') AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)");
			factor5 = Double.parseDouble(fact5Val.equalsIgnoreCase("") ? "0"
					: fact5Val);

			String wsrcVal = runner
			.singleSelection("SELECT WAR_RATE FROM WAR_RATE_MASTER WHERE MODE_TRANSPORT_ID ='"
					+ modeOfTransport
					+ "' AND BRANCH_CODE='"
					+ branch
					+ "' AND AMEND_ID=(SELECT max(amend_id) from WAR_RATE_MASTER where MODE_TRANSPORT_ID='"
					+ modeOfTransport
					+ "'  AND BRANCH_CODE='"
					+ branch
					+ "' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)) AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)");
			wsrccRates = Double.parseDouble(wsrcVal.equalsIgnoreCase("") ? "0"
					: wsrcVal);

			System.out.println("baseRate:" + baseRate + "factor1(sum insured):"
					+ factor1 + "factor2(fragile/non fragile):" + factor2
					+ "factor3(dest country):" + factor3
					+ "factor4(Conveyance):" + factor4 + "factor5(commodity):"
					+ factor5 + "wsrccRates:" + wsrccRates);

			result = (baseRate * factor1 * factor2 * factor3 * factor4 * factor5) / 10;
			DecimalFormat df = new DecimalFormat("#.####");
			returnVal = df.format(result);

			System.out.println("baseRate:" + baseRate + "factor1(sum insured):"
					+ factor1 + "factor2(fragile/non fragile):" + factor2
					+ "factor3(dest country):" + factor3
					+ "factor4(Conveyance):" + factor4 + "factor5(commodity):"
					+ factor5 + "wsrccRates:" + wsrccRates);
			// returnVal=Double.toString(result);

			bean.setTransportRate(String.valueOf(baseRate));
			bean.setSumRRate(String.valueOf(factor1));
			bean.setFragileRate(String.valueOf(factor2));
			bean.setCountryRate(String.valueOf(factor3));
			bean.setConveyanceRate(String.valueOf(factor4));
			bean.setCommodityRate(String.valueOf(factor5));
			bean.setWsrccRate(String.valueOf(wsrccRates));
			bean.setFinalRate(String.valueOf(returnVal));
			// returnVal =
			// baseRate+"~"+factor1+"~"+factor2+"~"+factor3+"~"+factor4+"~"+factor5+"~"+wsrccRates+"~"+returnVal;
			logger.info("baseRate==>" + baseRate);
			logger.info("SumRRate==>" + factor1);
			logger.info("FragileRate==>" + factor2);
			logger.info("CountryRate==>" + factor3);
			logger.info("ConveyanceRate==>" + factor4);
			logger.info("CommodityRate==>" + factor5);
			logger.info("WsrccRate==>" + wsrccRates);
			logger.info("FinalRate==>" + result);

		} catch (Exception e) {

			logger.info("Exception in getCommodityRate .." + e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCoverages(String searchValue, String branchCode) {

		List<Map<String, Object>> coverages = null;
		String query = "";
		try {
			query = getQuery("GET_BASERATE_COVERAGE");
			logger.info("Query==>" + query);

			coverages = this.mytemplate.queryForList(query, new Object[] {
					searchValue, searchValue, branchCode, branchCode });

		} catch (Exception e) {
			e.printStackTrace();
		}
		return coverages;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCoveragesadd(String searchValue, String branchCode) {

		List<Map<String, Object>> coverages = null;
		String query = "";
		try {
			query = getQuery("GET_BASERATENOTIN_COVERAGE");
			logger.info("Query==>" + query);

			coverages = this.mytemplate.queryForList(query, new Object[] {
					searchValue, searchValue, branchCode, branchCode });

		} catch (Exception e) {
			e.printStackTrace();
		}
		return coverages;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCovey(String searchValue, String branchCode) {
		List<Map<String, Object>> covey = null;
		String query = "";
		try {
			query = getQuery("GET_BASERATE_CONVEY");
			logger.info("Query==>" + query);

			covey = this.mytemplate.queryForList(query, new Object[] {
					searchValue, branchCode, branchCode, searchValue });
			logger.info("coney size====>" + covey.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return covey;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBaserateCountry() {
		// List<Map<String, Object>> baserateCountry=new ArrayList();
		List<Map<String, Object>> baserateCountry = null;

		String query = "";
		try {
			query = getQuery("GET_BASERATE_COUNTRY");
			logger.info("Query==>" + query);

			baserateCountry = this.mytemplate.queryForList(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baserateCountry;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBaserateCommodity(String branchCode) {
		List baserateCommodity = new ArrayList();
		String query = "";
		try {
			query = getQuery("GET_BASERATE_COMMODITY");
			logger.info("Query==>" + query);

			baserateCommodity = this.mytemplate.queryForList(query,
					new Object[] { branchCode, branchCode });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baserateCommodity;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAgentList(String branchCode2) {
		List<Map<String, Object>> agentList = null;
		try {
			Object[] obj = new Object[1];
			obj[0] = branchCode;
			query = getQuery("GET_AGENT_LIST");

			logger.info("Query===>" + query);
			logger.info("branchCode===>" + branchCode);
			agentList = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return agentList;

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAgentListAjax(String branchCode2, String searchBy,
			String searchValue) {
		List<Map<String, Object>> agentList = null;
		String query = "";

		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_AGENT_BY_NAME");
			}

			logger.info("Query==>" + query);

			agentList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentList;

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCityListAjax1(String countryID) {
		List<Map<String, Object>> cityList = null;
		String query = "";

		try {
			query = getQuery("GET_CITY_BY_COUNTRY");

			logger.info("Query==>" + query);

			cityList = this.mytemplate.queryForList(query,
					new Object[] { countryID });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList;

	}

	public void getNewAgent(Object[] val) {
		try {
			logger.info("Method to getNewAgent()");
			logger.info("BranchCode===>" + branchCode);

			query = getQuery("INS_AGENT_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getNewAgent() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getSelectedAgent(final RatingEngineBean bean) {
		logger.info("Method to getSelectedAgent()");

		try {
			query = getQuery("EDIT_SETTLING_AGENT_MASTER");
			Object[] obj = new Object[2];
			obj[0] = branchCode;
			obj[1] = bean.getAgentID();
			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setAgentID(rs.getString("SETTLING_AGENT_ID"));
					bean.setAgentName(rs.getString("SETTLING_AGENT_NAME"));
					bean.setCountryID(rs.getString("COUNTRY_ID"));
					bean.setCityID(rs.getString("CITY_ID"));
					bean.setShortName(rs.getString("SHORT_NAME"));
					bean.setAddress1(rs.getString("ADDRESS1"));
					bean.setAddress2(rs.getString("ADDRESS2"));
					bean.setAddress3(rs.getString("ADDRESS3"));
					bean.setCityName(rs.getString("CITY_NAME"));
					bean.setZipCode(rs.getString("ZIP_CODE"));
					bean.setTeleNo(rs.getString("TELEPHONE_NO"));
					bean.setFaxNo(rs.getString("FAX_NO"));
					bean.setMobileNo(rs.getString("MOBILE_NO"));
					bean.setEmail(rs.getString("EMAIL"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setStatus(rs.getString("STATUS"));
					return null;
				}
			});

		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getUpdateAgent(Object[] val) {
		try {
			logger.info("Method to getUpdateAgent()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("UPDATE_SETTLING_AGENT_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getUpdateAgent() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCommodityType(String branchCode) {
		List<Map<String, Object>> commodityType = null;
		String query = "";
		try {
			logger.info("Method to getCommodityType()");
			query = getQuery("GET_COMMODITY_TYPE");
			commodityType = this.mytemplate.queryForList(query,
					new Object[] { branchCode });
			logger.info("Query===>" + query);
			logger.info("getCommodityType() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commodityType;
	}

	/*public void getNewCommodity(Object[] val) {
		try {
			logger.info("Method to getNewCommodity()");

			query=getQuery("INS_COMMODITY_MASTER");

			logger.info("Query===>" + query);
			logger.info("Args===>" + StringUtils.join(val,","));

			this.mytemplate.update(query,val);
			//query = getQuery("INS_COMMODITY_MASTER");
			//this.mytemplate.update(query, val);

			logger.info("getNewCommodity() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 */
	/*public void getUpdateCommodity(Object[] val, String string) {
		try {
			logger.info("Method to getUpdateCommodity()");

			if (string.equals("insert")) {
				query = getQuery("UPDATE_COMMODITY_MASTER");
			} else if (string.equals("update")) {
				query = getQuery("UPDATE_COMMODITY_MASTER_UPDATE");
			}

			logger.info("Query===>" + query);
			logger.info("Args===>" + StringUtils.join(val,","));

			this.mytemplate.update(query, val);

			logger.info("getUpdateCommodity() - Exit");
		} catch (Exception e) {
			logger.info("getUpdateCommodity() - Exception" + e);
		}
	}*/

	/*public void getSelectedCommodity(final RatingEngineBean bean, Object[] val) {
		logger.info("Method to getSelectedCommodity()");

		try {
			query = getQuery("EDIT_COMMODITY_MASTER");

			this.mytemplate.query(query, val, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setCommodityID(rs.getString("COMMODITY_ID"));
					bean.setCommodityTypeID(rs.getString("COMMODITY_TYPE_ID"));
					bean.setCommodityName(rs.getString("COMMODITY_NAME"));
					bean.setCommodityRates(rs.getString("COMMODITY_RATE"));
					bean.setCoverage_Referal(rs.getString("COVERAGE_REFERAL"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setStatus(rs.getString("STATUS"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setEff_date(rs.getString("EFFECTIVE_DATE"));
					bean.setPrevdate(rs.getString("EFFECTIVE_DATE"));
					bean.setAmendID(rs.getString("AMEND_ID"));
					String ICC_A_Sea_Rate = rs.getString("ICC_A_SEA");
					String ICC_B_Sea_Rate = rs.getString("ICC_B_SEA");
					String ICC_C_Sea_Rate = rs.getString("ICC_C_SEA");
					String ICC_Air_Cargo_Air = rs.getString("ICC_AIR_CARGO");
					String All_Risks_Land_Transit = rs.getString("TOTAL_LOSS");
					String Land_Transit_Land = rs.getString("TRUCK_A");
					String All_Risks_Parcel_Post_Air = rs.getString("TRUCK_C");
					// String
					// All_Risks_Sea_and_Air=rs.getString("ALL_RISKS_SEA_AND_AIR");
					String[] common = ICC_A_Sea_Rate.split("~");
					String[] common1 = ICC_B_Sea_Rate.split("~");
					String[] common2 = ICC_C_Sea_Rate.split("~");
					String[] common3 = ICC_Air_Cargo_Air.split("~");
					String[] common4 = All_Risks_Land_Transit.split("~");
					String[] common5 = Land_Transit_Land.split("~");
					String[] common6 = All_Risks_Parcel_Post_Air.split("~");
					// String[] common7=All_Risks_Sea_and_Air.split("~");
					String[] test = new String[5];
					String[] test1 = new String[5];
					String[] test2 = new String[5];
					String[] test3 = new String[5];
					String[] test4 = new String[5];
					String[] test5 = new String[5];
					String[] test6 = new String[5];
					// String[] test7=new String[5];
					for (int i = 0; i <= 4; i++) {
						test[i] = common[i];
						test1[i] = common1[i];
						test2[i] = common2[i];
						test3[i] = common3[i];
						test4[i] = common4[i];
						test5[i] = common5[i];
						test6[i] = common6[i];
						// test7[i]=common7[i];

					}

					bean.setTxtchkICC_A_SEAClause_1(test[0]);
					bean.setTxtchkICC_A_SEAExclusive_1(test[1]);
					bean.setTxtchkICC_A_SEAWarranty_1(test[2]);
					bean.setTxtchkICC_A_SEAWarCover_1(test[3]);
					bean.setRadDetective_1(test[4]);
					bean.setTxtchkICC_A_SEAClause_2(test1[0]);
					bean.setTxtchkICC_A_SEAExclusive_2(test1[1]);
					bean.setTxtchkICC_A_SEAWarranty_2(test1[2]);
					bean.setTxtchkICC_A_SEAWarCover_2(test1[3]);
					bean.setRadDetective_2(test1[4]);
					bean.setTxtchkICC_A_SEAClause_3(test2[0]);
					bean.setTxtchkICC_A_SEAExclusive_3(test2[1]);
					bean.setTxtchkICC_A_SEAWarranty_3(test2[2]);
					bean.setTxtchkICC_A_SEAWarCover_3(test2[3]);
					bean.setRadDetective_3(test2[4]);
					bean.setTxtchkICC_A_SEAClause_8(test3[0]);
					bean.setTxtchkICC_A_SEAExclusive_8(test3[1]);
					bean.setTxtchkICC_A_SEAWarranty_8(test3[2]);
					bean.setTxtchkICC_A_SEAWarCover_8(test3[3]);
					bean.setRadDetective_8(test3[4]);
					bean.setTxtchkICC_A_SEAClause_9(test4[0]);
					bean.setTxtchkICC_A_SEAExclusive_9(test4[1]);
					bean.setTxtchkICC_A_SEAWarranty_9(test4[2]);
					bean.setTxtchkICC_A_SEAWarCover_9(test4[3]);
					bean.setRadDetective_9(test4[4]);
					bean.setTxtchkICC_A_SEAClause_10(test5[0]);
					bean.setTxtchkICC_A_SEAExclusive_10(test5[1]);
					bean.setTxtchkICC_A_SEAWarranty_10(test5[2]);
					bean.setTxtchkICC_A_SEAWarCover_10(test5[3]);
					bean.setRadDetective_10(test5[4]);
					bean.setTxtchkICC_A_SEAClause_11(test6[0]);
					bean.setTxtchkICC_A_SEAExclusive_11(test6[1]);
					bean.setTxtchkICC_A_SEAWarranty_11(test6[2]);
					bean.setTxtchkICC_A_SEAWarCover_11(test6[3]);
					bean.setRadDetective_11(test6[4]);
					/*
	 * bean.setTxtchkICC_A_SEAClause_12(test7[0]);
	 * bean.setTxtchkICC_A_SEAExclusive_12(test7[1]);
	 * bean.setTxtchkICC_A_SEAWarranty_12(test7[2]);
	 * bean.setTxtchkICC_A_SEAWarCover_12(test7[3]);
	 * bean.setRadDetective_12(test7[4]);

					return null;
				}
			});

		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}*/

	public int getDateExist(Object[] val) {
		int date = 0;
		try {
			String query = getQuery("Check_Date");
			logger.info("getDateExist() - Enter");
			date = this.mytemplate.queryForObject(query,Integer.class, val);
			logger.info("Query===>" + query);
			logger.info("getDateExist() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCurrencyList() {
		List<Map<String, Object>> currencyList = null;
		String query = "";
		try {
			logger.info("Method to getCurrencyList()");
			query = getQuery("GET_CURRENCY_NAME");
			currencyList = this.mytemplate.queryForList(query);
			logger.info("Query===>" + query);
			logger.info("getCurrencyList() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currencyList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCurrencyListAjax(String searchValue) {
		List<Map<String, Object>> currencyList = null;
		String query = "";
		try {
			logger.info("Method to getCurrencyListAjax()");
			query = getQuery("GET_CURRENCY_NAME_LIST");
			currencyList = this.mytemplate.queryForList(query,
					new Object[] { searchValue });
			logger.info("Query===>" + query);
			logger.info("getCurrencyListAjax() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currencyList;
	}

	public void getNewCurrency(Object[] val) {
		try {
			logger.info("Method to getNewCurrency()");
			query = getQuery("INS_CURRENCY_MASTER");
			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getNewCurrency() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getNewCurrency() - Exception");
		}

	}

	public void getUpdateCurrency(Object[] val, String string) {
		try {
			logger.info("Method to getUpdateCurrency()");
			if (string.equals("insert"))
				query = getQuery("UPDATE_CURRENCY_MASTER");
			else if (string.equals("update"))
				query = getQuery("UPDATE_CURRENCY_MASTER_UPDATE");
			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getUpdateCurrency() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getSelectedCurrency(final RatingEngineBean bean, String branchCode) {
		logger.info("Method to getSelectedCurrency()");

		try {
			query = getQuery("EDIT_CURRENCY_MASTER");
			Object[] obj = new Object[2];

			obj[0] = bean.getCurrencyID();
			obj[1] = branchCode;
			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {

					bean.setCurrencyType(rs.getString("CURRENCY_NAME"));
					bean.setAmendID(rs.getString("AMEND_ID"));
					bean.setEff_date(rs.getString("EFFECTIVE_DATE"));
					bean.setPrevdate(rs.getString("EFFECTIVE_DATE"));
					bean.setCurrencyShortName(rs.getString("SHORT_NAME"));
					bean.setRfactor(rs.getString("RFACTOR"));
					bean.setSubCurrency(rs.getString("SUB_CURRENCY"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setStatus(rs.getString("STATUS"));
					bean.setCurrencyID(rs.getString("CURRENCY_ID"));
					return null;
				}
			});

		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getExchangeList() {
		List<Map<String, Object>> exchangeList = null;
		logger.info("Method to getExchangeList() - Enter");
		try {
			String query = getQuery("GET_EXCHANGE_MASTER");
			exchangeList = this.mytemplate.queryForList(query);
			logger.info("Query ==>" + query);
			logger.info("Method to getExchangeList()- Exit");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exchangeList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getExchangeListAjax(String searchValue) {
		List<Map<String, Object>> exchangeList = null;
		logger.info("Method to getExchangeListAjax() - Enter");
		try {
			String query = getQuery("GET_EXCHANGE_MASTER_NAME");
			exchangeList = this.mytemplate.queryForList(query,
					new Object[] { searchValue });
			logger.info("Query ==>" + query);
			logger.info("Method to getExchangeListAjax()- Exit");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exchangeList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getExchanges(String branchCode) {
		List<Map<String, Object>> exchanges = null;
		String query = "";
		logger.info("Method to getExchages() - Enter");
		try {
			query = getQuery("GET_EXCHANGES");
			exchanges = this.mytemplate.queryForList(query,
					new Object[] { branchCode });
			logger.info("Query==>" + query);
			logger.info("Method to getExchanges() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exchanges;
	}

	public void getNewExchange(Object[] val) {
		logger.info("Method to getNewExchange() - Enter");
		try

		{
			String query = getQuery("INS_EXCHANGE_MASTER");
			this.mytemplate.update(query, val);
			logger.info("Query ==>" + query);
			logger.info("Method to getNewExchange() - Exit");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getUpdateExchange(Object[] val, String string) {
		logger.info("Method to getUpdateExchange() - Enter");
		String query = "";
		try
		{
			if (string.equals("insert")) {
				query = getQuery("UPDATE_EXCHANGE_MASTER");
			} else if (string.equals("update")) {
				query = getQuery("UPDATE_EXCHANGE_MASTER_UPDATE");
			}

			logger.info("Values: ->"+StringUtils.join(val,","));
			logger.info("Query ==>" + query);

			this.mytemplate.update(query, val);

			logger.info("Method to getUpdateExchange() - Exit");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getSelectedExchange(final RatingEngineBean bean,String branchCode) {
		logger.info("Method to getSelectedExchange()");

		try {
			query = getQuery("EDIT_EXCHANGE_MASTER");
			Object[] obj = new Object[2];
			obj[0] = bean.getExchangeID();
			obj[1] = branchCode;
			logger.info("Query==> " + query);
			logger.info("Args==> " + StringUtils.join(obj,","));
			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {

					bean.setCurrencyID(rs.getString("CURRENCY_ID"));
					bean.setAmendID(rs.getString("AMEND_ID"));
					bean.setEff_date(rs.getString("EFFECTIVE_DATE"));
					bean.setPrevdate(rs.getString("EFFECTIVE_DATE"));
					bean.setExchangeRate(rs.getString("EXCHANGE_RATE"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setStatus(rs.getString("STATUS"));
					bean.setExchangeID(rs.getString("EXCHANGE_ID"));
					return null;
				}
			});

		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCoverList(Object[] val) {
		List<Map<String, Object>> coverList = null;
		try {
			logger.info("Method to getCoverList() - Enter");
			String query = getQuery("GET_COVER_NAME");
			coverList = this.mytemplate.queryForList(query, val);
			logger.info("Query ==> " + query);
			logger.info("Method to getCoverList() - Exit");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return coverList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCoverListAjax(String branchCode, String searchValue) {
		List<Map<String, Object>> coverList = null;
		try {
			logger.info("Method to getCoverListAjax() - Enter");
			String query = getQuery("GET_COVER_NAME_LIST");
			coverList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, branchCode, searchValue });
			logger.info("Query ==> " + query);
			logger.info("Method to getCoverListAjax() - Exit");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return coverList;
	}

	public void getSelectedCover(final RatingEngineBean bean) {
		logger.info("Method to getSelectedCover()");

		try {
			query = getQuery("EDIT_COVER_MASTER");
			Object[] obj = new Object[2];
			obj[0] = bean.getCoverID();
			obj[1] = branchCode;
			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setCoverID(rs.getString("COVER_ID"));
					bean.setAmendID(rs.getString("AMEND_ID"));
					bean.setEff_date(rs.getString("EFFECTIVE_DATE"));
					bean.setPrevdate(rs.getString("EFFECTIVE_DATE"));
					bean.setCoverRate(rs.getString("COVER_RATE"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setStatus(rs.getString("STATUS"));
					bean.setCoverName(rs.getString("COVER_NAME"));
					bean.setCoverDesc(rs.getString("DESCRIPTION"));
					bean.setTransID(rs.getString("MODE_TRANSPORT_ID"));
					bean.setReferralStatus(rs.getString("REFERAL_STATUS"));
					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public void getUpdateCover(RatingEngineBean bean, String branchCode)
	{
		try
		{
			logger.info("Method to getUpdateCover() - Enter");
			String query=getQuery("UPDATE_COVER_MASTER");
			this.mytemplate.update(query,val);
			logger.info("Query ==>"+query);
			String query = "Select * from COVER_MASTER cm where cm.cover_id=? and cm.branch_code=? and amend_id=(Select max(amend_id) from COVER_MASTER c where cm.cover_id=c.cover_id and C.branch_code=cm.branch_code )";
			Map<String, Object> map = this.mytemplate.queryForMap(query,
					new Object[] { bean.getCoverID(), branchCode });

			String rsaCode = map.get("RSACODE").toString();
			int amendId = Integer.valueOf(map.get("AMEND_ID").toString()) + 1;
			String displayorder = "" + map.get("DISPLAY_ORDER");

			//Object[] args = new Object[17];
			Object[] args = new Object[15];
			args[0] = bean.getCoverID();
			args[1] = bean.getCoverName();
			args[2] = bean.getTransID();
			args[3] = bean.getCoverDesc();
			args[4] = rsaCode;
			args[5] = amendId;
			args[6] = bean.getEff_date();
			args[7] = displayorder;
			args[8] = branchCode;
			args[9] = bean.getCoverRate();
			args[10] = bean.getReferralStatus();
			args[11] = "";
			args[12] = bean.getRemarks();
			args[13] = bean.getStatus();
			args[14] = "";
			//args[15] = "";
			//args[16] = "";
			query = getQuery("UPDATE_COVER_MASTER");
			// query =
			// "insert into COVER_MASTER (   SNO__, COVER_ID, COVER_NAME,MODE_TRANSPORT_ID, DESCRIPTION, RSACODE,AMEND_ID, EFFECTIVE_DATE, DISPLAY_ORDER,BRANCH_CODE, COVER_RATE, REFERAL_STATUS,REFERAL_VALUE, REMARKS, STATUS,COVER_ARABIC, COMMODITY_TYPEID, CATEGORY_RATE) values ((select MAX(SNO__)+1 from COVER_MASTER),?,?,?,?,?,?,TO_DATE(?,'MM/DD/YYYY'),?,?,?,?,?,?,?,?,?,?)";
			logger.info("Query==> " + query);
			logger.info("Args==> " + StringUtils.join(args, ","));
			int result = this.mytemplate.update(query, args);

			System.out.println("Result==> " + result);
			logger.info("Method to getUpdateCover() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/
	public void getUpdateCover(RatingEngineBean bean, String branchCode,String str)
	{
		try
		{
			logger.info("Method to getUpdateCover() - Enter");
			/*String query=getQuery("UPDATE_COVER_MASTER");
			this.mytemplate.update(query,val);
			logger.info("Query ==>"+query);*/
			String query = "Select * from COVER_MASTER cm where cm.cover_id=? and cm.branch_code=? and amend_id=(Select max(amend_id) from COVER_MASTER c where cm.cover_id=c.cover_id and C.branch_code=cm.branch_code )";
			Map<String,Object> map = this.mytemplate.queryForMap(query,new Object[]{bean.getCoverID(),branchCode});

			String rsaCode= 	map.get("RSACODE").toString();
			int  amendId= Integer.valueOf(map.get("AMEND_ID").toString())+1;
			String displayorder=""+map.get("DISPLAY_ORDER");

			Object[] args;
			if("insert".equalsIgnoreCase(str)){
				args = new Object[15];
				args[0] = bean.getCoverID();
				args[1] = bean.getCoverName();
				args[2] = bean.getTransID();
				args[3] = bean.getCoverDesc();
				args[4] = bean.getCode();
				args[5] = amendId;
				args[6] = bean.getEff_date();
				args[7] = displayorder;
				args[8] = branchCode;
				args[9] = bean.getCoverRate();
				args[10] = bean.getReferralStatus();
				args[11] = "";
				args[12] = bean.getRemarks();
				args[13] = bean.getStatus();
				args[14] = "";
				query=getQuery("UPDATE_COVER_MASTER_INSERT");
			}else{
				args = new Object[12];

				args[0] = bean.getCoverName();
				args[1] = bean.getTransID();
				args[2] = bean.getCoverDesc();
				args[3] = rsaCode;
				args[4] = bean.getEff_date();
				args[5] = displayorder;
				args[6] = bean.getCoverRate();
				args[7] = bean.getReferralStatus();
				args[8] = bean.getRemarks();
				args[9] = bean.getStatus();
				args[10] = bean.getCoverID();
				args[11] = branchCode;
				query=getQuery("UPDATE_COVER_MASTER");
			}
			//query = "insert into COVER_MASTER (   SNO__, COVER_ID, COVER_NAME,MODE_TRANSPORT_ID, DESCRIPTION, RSACODE,AMEND_ID, EFFECTIVE_DATE, DISPLAY_ORDER,BRANCH_CODE, COVER_RATE, REFERAL_STATUS,REFERAL_VALUE, REMARKS, STATUS,COVER_ARABIC, COMMODITY_TYPEID, CATEGORY_RATE) values ((select MAX(SNO__)+1 from COVER_MASTER),?,?,?,?,?,?,TO_DATE(?,'MM/DD/YYYY'),?,?,?,?,?,?,?,?,?,?)";
			logger.info("Query==> " + query);
			logger.info("Args==> " + StringUtils.join(args,","));
			int result = this.mytemplate.update(query,args);

			System.out.println("Result==> " + result);
			logger.info("Method to getUpdateCover() - Exit");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public void getNewCover(Object[] val) {
		logger.info("Method to getNewCover() - Enter");
		try

		{
			String query = getQuery("INS_COVER_MASTER");
			this.mytemplate.update(query, val);
			logger.info("Query ==>" + query);
			logger.info("Method to getNewCover() - Exit");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getExclusionList(String branchCode) {
		List<Map<String, Object>> exclusionList = null;
		try {
			Object[] obj = new Object[1];
			obj[0] = branchCode;
			query = getQuery("GET_EXCLUSION_LIST");

			logger.info("Query===>" + query);
			logger.info("branchCode===>" + branchCode);
			exclusionList = this.mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return exclusionList;

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getExclusionListAjax(String branchCode,
			String searchBy, String searchValue) {
		List<Map<String, Object>> exclusionList = null;
		String query = "";

		try {
			if ("name".equals(searchBy)) {
				query = getQuery("GET_EXCLUSION_BY_NAME");
			}

			logger.info("Query==>" + query);

			exclusionList = this.mytemplate.queryForList(query, new Object[] {
					branchCode, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exclusionList;
	}

	public void getNewExclusion(Object[] val) {
		try {
			logger.info("Method to getNewExclusion()");
			query = getQuery("INS_EXCLUSION_MASTER");
			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getNewExclusion() - Exit");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getSelectedExclusion(final RatingEngineBean bean) {
		logger.info("Method to getSelectedExtraCover()");

		try {
			query = getQuery("EDIT_EXCLUSION_MASTER");
			Object[] obj = new Object[2];
			obj[0] = branchCode;
			obj[1] = bean.getExclusionID();

			this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean
					.setExclusionName(rs
							.getString("EXCLUSION_DESCRIPTION"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setCode(rs.getString("RSACODE"));
					bean.setStatus(rs.getString("STATUS"));
					bean.setExclusionID(rs.getString("EXCLUSION_ID"));
					return null;
				}
			});

		}

		catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}

	public void getUpdateExclusion(Object[] val) {
		try {
			logger.info("Method to getUpdateExclusion()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("UPDATE_EXCLUSION_MASTER");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("getUpdateExclusion() - Exit");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatcateg(Object val[], String ss) {
		try {
			logger.info("Method to updatcateg()");
			logger.info("BranchCode===>" + branchCode);
			if (ss.equals("insert")) {
				query = getQuery("INSERT_UPDATE_CATEGORY");
			} else if (ss.equals("update")) {
				query = getQuery("UPDATE_CATEGORY");
			}
			this.mytemplate.update(query, val);

			logger.info("Query===>" + query);
			logger.info("updatcateg() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	public void updatcovercomm(Object val[]) {
		try {
			logger.info("Method to updatcovercomm()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INSERT_UPDATE_COVERCOMM");
			int result = this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("updatcovercomm() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	public void insertcateg(Object[] val) {
		try {
			logger.info("Method to NewInsert()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("INSERT_CATEGORY");

			this.mytemplate.update(query, val);
			logger.info("Query===>" + query);
			logger.info("NewInsert() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	public void insertcovercomm(RatingEngineBean bean, String branchCode) {
		try {
			logger.info("Method to NewInsert()");
			logger.info("BranchCode===>" + branchCode);
			// query=getQuery("INSERT_COVERCOMM");
			Object[] args = null;

			/*query = "select decode(count(*),0,1,max(COMMODITY_TYPEID)) COMMODITY_TYPEID,decode(count(*),0,0,max(amend_id)+1) amend_id from COVER_COMMODITY_MASTER where cover_id=? and branch_code=?";
			args = new Object[2];
			args[0]= bean.getCoverageID();
			args[1]= branchCode;

			logger.info("Query==> " + query);
			logger.info("Args===> " + StringUtils.join(args,","));

			Map<String,Object> map1 = this.mytemplate.queryForMap(query,args);*/
			if("edit".equals(bean.getMode())) {
				//query = "select to_char(EFFECTIVE_DATE,'MM/DD/YYYY')EFFECTIVE_DATE from COVER_COMMODITY_MASTER CCM where CCM.COVER_ID=? and CCM.branch_code=? and amend_id=(select max(amend_id) from COVER_COMMODITY_MASTER CC where CC.cover_id=CCM.cover_id and CC.branch_code=CCM.branch_code)";
				query = "SELECT   TO_CHAR (EFFECTIVE_DATE, 'MM/DD/YYYY') EFFECTIVE_DATE FROM  COVER_COMMODITY_MASTER CCM WHERE  CCM.COVER_ID = ? AND CCM.branch_code = ? and CCM.COMMODITY_TYPEID=? AND amend_id =(SELECT  MAX (amend_id) FROM   COVER_COMMODITY_MASTER CC WHERE   CC.cover_id = CCM.cover_id AND CC.branch_code = CCM.branch_code AND CCM.COMMODITY_TYPEID=CC.COMMODITY_TYPEID)";
				args = new Object[3];
				args[0] = bean.getCoverageID();
				args[1] = branchCode;
				args[2]=bean.getCommodityID();

				logger.info("Query==> " + query);
				logger.info("Args===> " + StringUtils.join(args,","));

				String prevEff = (String) this.mytemplate.queryForObject(query,args,String.class);
				String sysdate;
				Format formatter;
				Date date=new Date(); 
				formatter = new SimpleDateFormat("MM/dd/yyyy");
				sysdate = formatter.format(date);
				String format = "MM/dd/yyyy"; 
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				java.util.Date date1 = sdf.parse(sysdate);
				java.util.Date date2 = sdf.parse(prevEff); 
				if(date2.compareTo(date1) > 0) {
					query = "update COVER_COMMODITY_MASTER CCM set CATEGORY_RATE=?,COVER_RATE=?,EFFECTIVE_DATE=to_date(?,'MM/DD/YYYY'),REFERAL_STATUS=?,REMARKS=?,STATUS=? where CCM.COVER_ID=? and CCM.branch_code=?  And COMMODITY_TYPEID=? and amend_id=(select max(amend_id) from COVER_COMMODITY_MASTER CC where CC.cover_id=CCM.cover_id and CC.COMMODITY_TYPEID=CCM.COMMODITY_TYPEID and CC.branch_code=CCM.branch_code)";
					args = new Object[9];
					args[0] = bean.getCatrate();
					args[1] = bean.getCoverRate();
					args[2] = bean.getEff_date();
					args[3] = bean.getReferralStatus();
					args[4] = bean.getRemarks();
					args[5] = bean.getStatus();
					args[6] = bean.getCoverageID();
					args[7] = branchCode;
					args[8] = bean.getCommodityID();

					logger.info("Query==> " + query);
					logger.info("Args===> " + StringUtils.join(args,","));

					int result = this.mytemplate.update(query,args);

					logger.info("Result==> " + result);
				}
				else {
					query = "Select decode(count(*),0,1,max(Rsacode)) Rsacode,decode(count(*),0,1,max(Display_Order)) Display_Order, DECODE (COUNT ( * ), 0, 0, MAX (amend_id) + 1) amend_id From Cover_Commodity_Master Ccm Where Cover_Id=? And Branch_Code=? And COMMODITY_TYPEID=? And Amend_Id=(Select Max(Amend_Id) From Cover_Commodity_Master Cc Where Ccm.Cover_Id=Cc.Cover_Id And Cc.Branch_Code=Ccm.Branch_Code and Cc.COMMODITY_TYPEID=Ccm.COMMODITY_TYPEID )";
					args = new Object[3];
					args[0]= bean.getCoverageID();
					args[1]= branchCode;
					args[2]= bean.getCommodityID();

					logger.info("Query==> " + query);
					logger.info("Args===> " + StringUtils.join(args,","));
					Map<String,Object> map2 = this.mytemplate.queryForMap(query,args);
					//query = "INSERT into COVER_COMMODITY_MASTER(SNO__,COVER_ID,COVER_NAME,MODE_TRANSPORT_ID ,DESCRIPTION,RSACODE,AMEND_ID,EFFECTIVE_DATE,DISPLAY_ORDER,BRANCH_CODE, COVER_RATE,REFERAL_STATUS,REFERAL_VALUE,REMARKS,STATUS,COVER_ARABIC, COMMODITY_TYPEID,CATEGORY_RATE) SELECT ( SELECT MAX(SNO__)+1 FROM COVER_COMMODITY_MASTER where BRANCH_CODE=?),COVER_ID,COVER_NAME,MODE_TRANSPORT_ID,DESCRIPTION, ?,?,to_date(?,'MM/DD/YYYY'),?,BRANCH_CODE,?,?, REFERAL_VALUE,?,?,COVER_ARABIC, ?,? from COVER_MASTER cm where COVER_ID=? and MODE_TRANSPORT_ID=? and BRANCH_CODE=? and amend_id=(select max(amend_id) from cover_master c where c.COVER_ID=cm.COVER_ID and c.MODE_TRANSPORT_ID=cm.MODE_TRANSPORT_ID and c.BRANCH_CODE=cm.BRANCH_CODE)";
					query = "INSERT into COVER_COMMODITY_MASTER(SNO__,COVER_ID,COVER_NAME,MODE_TRANSPORT_ID ,DESCRIPTION,RSACODE,AMEND_ID,EFFECTIVE_DATE,DISPLAY_ORDER,BRANCH_CODE, COVER_RATE,REFERAL_STATUS,REFERAL_VALUE,REMARKS,STATUS,COVER_ARABIC, COMMODITY_TYPEID,CATEGORY_RATE) SELECT ( SELECT MAX(SNO__)+1 FROM COVER_COMMODITY_MASTER),COVER_ID,COVER_NAME,MODE_TRANSPORT_ID,DESCRIPTION, ?,?,to_date(?,'MM/DD/YYYY'),?,BRANCH_CODE,?,?, REFERAL_VALUE,?,?,COVER_ARABIC, ?,? from COVER_MASTER cm where COVER_ID=? and MODE_TRANSPORT_ID=? and BRANCH_CODE=? and amend_id=(select max(amend_id) from cover_master c where c.COVER_ID=cm.COVER_ID and c.MODE_TRANSPORT_ID=cm.MODE_TRANSPORT_ID and c.BRANCH_CODE=cm.BRANCH_CODE)";
					/*args = new Object[14];
					args[0] = branchCode;
					args[1] = map2.get("Rsacode");
					args[2] = map2.get("amend_id");
					args[3] = bean.getEff_date();
					args[4] = map2.get("Display_Order");
					args[5] = bean.getCoverRate();
					args[6] = bean.getReferralStatus();
					args[7] = bean.getRemarks();
					args[8] = bean.getStatus();
					args[9] = bean.getCommodityID();
					args[10] = bean.getCatrate();
					args[11] = bean.getCoverageID();
					args[12] = bean.getTransID();
					args[13] = branchCode;*/
					args = new Object[13];
					args[0] = map2.get("Rsacode");
					args[1] = map2.get("amend_id");
					args[2] = bean.getEff_date();
					args[3] = map2.get("Display_Order");
					args[4] = bean.getCoverRate();
					args[5] = bean.getReferralStatus();
					args[6] = bean.getRemarks();
					args[7] = bean.getStatus();
					args[8] = bean.getCommodityID();
					args[9] = bean.getCatrate();
					args[10] = bean.getCoverageID();
					args[11] = bean.getTransID();
					args[12] = branchCode;

					logger.info("Query==> " + query);
					logger.info("Args===> " + StringUtils.join(args,","));

					int count = this.mytemplate.update(query,args);

					logger.info("Result==> " + count);
				}
			}
			else {
				query = "Select decode(count(*),0,1,max(Rsacode)) Rsacode,decode(count(*),0,1,max(Display_Order)) Display_Order, DECODE (COUNT ( * ), 0, 0, MAX (amend_id) + 1) amend_id From Cover_Commodity_Master Ccm Where Cover_Id=? And Branch_Code=? And COMMODITY_TYPEID=? And Amend_Id=(Select Max(Amend_Id) From Cover_Commodity_Master Cc Where Ccm.Cover_Id=Cc.Cover_Id And Cc.Branch_Code=Ccm.Branch_Code and Cc.COMMODITY_TYPEID=Ccm.COMMODITY_TYPEID )";
				args = new Object[3];
				args[0]= bean.getCoverageID();
				args[1]= branchCode;
				args[2]= bean.getCommodityID();

				logger.info("Query==> " + query);
				logger.info("Args===> " + StringUtils.join(args,","));
				Map<String,Object> map2 = this.mytemplate.queryForMap(query,args);
				//query = "INSERT into COVER_COMMODITY_MASTER(SNO__,COVER_ID,COVER_NAME,MODE_TRANSPORT_ID ,DESCRIPTION,RSACODE,AMEND_ID,EFFECTIVE_DATE,DISPLAY_ORDER,BRANCH_CODE, COVER_RATE,REFERAL_STATUS,REFERAL_VALUE,REMARKS,STATUS,COVER_ARABIC, COMMODITY_TYPEID,CATEGORY_RATE) SELECT ( SELECT MAX(SNO__)+1 FROM COVER_COMMODITY_MASTER where BRANCH_CODE=?),COVER_ID,COVER_NAME,MODE_TRANSPORT_ID,DESCRIPTION, ?,?,to_date(?,'MM/DD/YYYY'),?,BRANCH_CODE,?,?, REFERAL_VALUE,?,?,COVER_ARABIC, ?,? from COVER_MASTER cm where COVER_ID=? and MODE_TRANSPORT_ID=? and BRANCH_CODE=? and amend_id=(select max(amend_id) from cover_master c where c.COVER_ID=cm.COVER_ID and c.MODE_TRANSPORT_ID=cm.MODE_TRANSPORT_ID and c.BRANCH_CODE=cm.BRANCH_CODE)";
				query = "INSERT into COVER_COMMODITY_MASTER(SNO__,COVER_ID,COVER_NAME,MODE_TRANSPORT_ID ,DESCRIPTION,RSACODE,AMEND_ID,EFFECTIVE_DATE,DISPLAY_ORDER,BRANCH_CODE, COVER_RATE,REFERAL_STATUS,REFERAL_VALUE,REMARKS,STATUS,COVER_ARABIC, COMMODITY_TYPEID,CATEGORY_RATE) SELECT ( SELECT MAX(SNO__)+1 FROM COVER_COMMODITY_MASTER),COVER_ID,COVER_NAME,MODE_TRANSPORT_ID,DESCRIPTION, ?,?,to_date(?,'MM/DD/YYYY'),?,BRANCH_CODE,?,?, REFERAL_VALUE,?,?,COVER_ARABIC, ?,? from COVER_MASTER cm where COVER_ID=? and MODE_TRANSPORT_ID=? and BRANCH_CODE=? and amend_id=(select max(amend_id) from cover_master c where c.COVER_ID=cm.COVER_ID and c.MODE_TRANSPORT_ID=cm.MODE_TRANSPORT_ID and c.BRANCH_CODE=cm.BRANCH_CODE)";
				/*args = new Object[14];
				args[0] = branchCode;
				args[1] = map2.get("Rsacode");
				args[2] = map2.get("amend_id");
				args[3] = bean.getEff_date();
				args[4] = map2.get("Display_Order");
				args[5] = bean.getCoverRate();
				args[6] = bean.getReferralStatus();
				args[7] = bean.getRemarks();
				args[8] = bean.getStatus();
				args[9] = bean.getCommodityID();
				args[10] = bean.getCatrate();
				args[11] = bean.getCoverageID();
				args[12] = bean.getTransID();
				args[13] = branchCode;*/
				args = new Object[13];
				args[0] = map2.get("Rsacode");
				args[1] = map2.get("amend_id");
				args[2] = bean.getEff_date();
				args[3] = map2.get("Display_Order");
				args[4] = bean.getCoverRate();
				args[5] = bean.getReferralStatus();
				args[6] = bean.getRemarks();
				args[7] = bean.getStatus();
				args[8] = bean.getCommodityID();
				args[9] = bean.getCatrate();
				args[10] = bean.getCoverageID();
				args[11] = bean.getTransID();
				args[12] = branchCode;

				logger.info("Query==> " + query);
				logger.info("Args===> " + StringUtils.join(args,","));

				int count = this.mytemplate.update(query,args);

				logger.info("Result==> " + count);
			}
			logger.info("NewInsert() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getErrorList(String branchCode) {
		List<Map<String, Object>> errorList = null;
		try {
			query = getQuery("GET_ERROR_MASTER");
			Object obj[] = new Object[1];
			obj[0] = branchCode;
			logger.info((new StringBuilder("Query===>")).append(query)
					.toString());
			logger.info((new StringBuilder("branchCode===>")).append(
					branchCode).toString());
			errorList = mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.info((new StringBuilder("EXCEPTION @ { ")).append(e)
					.append(" }").toString());
		}
		return errorList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getError() {
		logger.info("Method to getError()");
		List<Map<String, Object>> errorList = null;
		try {
			logger.info("Method to getError()");
			query = getQuery("GET_ERROR_MASTER");
			logger.info((new StringBuilder("Query===>")).append(query)
					.toString());
			errorList = mytemplate.queryForList(query,
					new Object[] { branchCode });
			logger.info("getError() - Exit");
		} catch (Exception e) {
			logger.info((new StringBuilder("EXCEPTION @ { ")).append(e)
					.append(" }").toString());
		}
		return errorList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getErrorListAjax(String branchCode, String searchBy,
			String searchValue) {
		List<Map<String, Object>> errorList = null;
		String query = "";
		try {
			if ("name".equals(searchBy))
				query = getQuery("GET_ERROR_LIST_BY_NAME");
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			logger.info((new StringBuilder("branchCode===>")).append(
					branchCode).toString());
			errorList = mytemplate.queryForList(query, new Object[] {
					branchCode, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;
	}

	@SuppressWarnings("unchecked")
	public void getSeletedError(final RatingEngineBean bean) {
		logger.info("Method to getSelectedError()");
		@SuppressWarnings("unused")
		List editError = null;
		try {
			logger.info((new StringBuilder("Mode===>")).append(
					bean.getErrorID()).toString());
			query = getQuery("EDIT_ERROR_MASTER");
			Object obj[] = new Object[1];
			obj[0] = bean.getErrorID();
			editError = mytemplate.query(query, obj, new RowMapper() {

				public Object mapRow(ResultSet rs, int rowNum)
				throws SQLException {
					bean.setErrorDesc(rs.getString("ERROR_DESC"));
					bean.setProductname(rs.getString("PRODUCT_ID"));
					bean.setStagename(rs.getString("STAGE_ID"));
					bean.setRemarks(rs.getString("REMARKS"));
					bean.setStatus(rs.getString("STATUS"));
					bean.setErrorID(rs.getString("ERROR_ID"));
					return null;
				}
			});
			logger.info((new StringBuilder("Mode===>")).append(
					bean.getErrorDesc()).toString());
			logger.info((new StringBuilder("Mode===>")).append(
					bean.getProductname()).toString());
			logger.info((new StringBuilder("Mode===>")).append(
					bean.getStagename()).toString());
		} catch (Exception e) {
			logger.info((new StringBuilder("EXCEPTION @ { ")).append(e)
					.append(" }").toString());
		}
	}

	public void getUpdateError(Object val[]) {
		try {
			logger.info("Method to getUpdateError()");
			logger.info((new StringBuilder("BranchCode===>")).append(
					branchCode).toString());
			query = getQuery("UPDATE_ERROR_MASTER");
			mytemplate.update(query, val);
			logger.info((new StringBuilder("Query===>")).append(query)
					.toString());
			logger.info("getUpdateError() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getErrorListAjax(String searchBy, String searchValue) {
		List<Map<String, Object>> errorList = null;
		String query = "";
		try {
			if ("name".equals(searchBy))
				query = getQuery("GET_ERROR_LIST_NAME");
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			errorList = mytemplate.queryForList(query, new Object[] {
					branchCode, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getProductnames(String branchCode) {
		logger.info("Method to getProductname");
		List<Map<String, Object>> productname = null;
		try {
			logger.info("Method to getProductname()");
			query = getQuery("GET_PRODUCT_NAME_LIST");
			Object obj[] = new Object[1];
			obj[0] = branchCode;
			logger.info((new StringBuilder("Query===>")).append(query)
					.toString());
			productname = mytemplate.queryForList(query, obj);
			logger.info("getProductname() - Exit");
		} catch (Exception e) {
			logger.info((new StringBuilder("EXCEPTION @ { ")).append(e)
					.append(" }").toString());
		}
		return productname;
	}

	public List<Map<String, Object>> getTypeOfVessel() {
		logger.info("Method to getTypeOfVessel");
		List<Map<String, Object>> TypeOfVessels = null;
		try {

			logger.info("Method to getTypeOfVessel()");
			query = getQuery("GET_VESSEL_TYPE_LIST");
			logger.info("Query==>" + query);
			TypeOfVessels = mytemplate.queryForList(query);
			logger.info("getTypeOfVessel() - Exit");
		} catch (Exception e) {
			logger.info((new StringBuilder("EXCEPTION @ { ")).append(e)
					.append(" }").toString());
		}
		return TypeOfVessels;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getStagenames(RatingEngineBean bean) {
		logger.info("Method to getStagename");
		List<Map<String, Object>> stagenames = null;
		try {
			logger.info("Method to getStagename()");
			query = getQuery("GET_STAGE_NAME_LIST");
			Object obj[] = new Object[1];
			obj[0] = bean.getProductname();
			logger.info((new StringBuilder("Query===>")).append(query)
					.toString());
			stagenames = mytemplate.queryForList(query, obj);
			logger.info("getStagename() - Exit");
		} catch (Exception e) {
			logger.info((new StringBuilder("EXCEPTION @ { ")).append(e)
					.append(" }").toString());
		}
		return stagenames;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getstageListAjax1(String stagename) {
		List<Map<String, Object>> stageList = null;
		String query = "";
		try {
			query = getQuery("GET_STAGE_NAME_LIST");
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			stageList = mytemplate.queryForList(query,
					new Object[] { stageList });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stageList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getClauseIDListAjax(String searchBy, String searchValue) {
		List<Map<String, Object>> clauseIDList = null;
		String query = "";
		try {
			if ("name".equals(searchBy))
				query = getQuery("GET_CLAUSE_ID_LIST_NAME");
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			clauseIDList = mytemplate.queryForList(query, new Object[] {
					branchCode, branchCode, searchValue });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clauseIDList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCovernames(RatingEngineBean bean, String branchCode) {
		logger.info("Method to getCovername");
		List<Map<String, Object>> covernames = null;
		String tranid = bean.getTransID() == null ? "" : bean.getTransID();
		if (tranid.equals("transID"))
			tranid = "";
		try {
			logger.info("Method to getCovername()");
			query = getQuery("GET_COVER_NAME_LIST");
			Object obj[] = { branchCode, tranid, branchCode };
			logger.info((new StringBuilder("Query===>")).append(query)
					.toString());
			Object aobj[];
			int j = (aobj = obj).length;
			for (int i = 0; i < j; i++) {
				Object k = aobj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}

			covernames = mytemplate.queryForList(query, obj);
			logger.info("getCovername() - Exit");
		} catch (Exception e) {
			logger.info((new StringBuilder("EXCEPTION @ { ")).append(e)
					.append(" }").toString());
		}
		return covernames;
	}

	public List<Map<String, Object>> coverName(RatingEngineBean bean, String branchCode) {
		List<Map<String, Object>> covernames = null;
		try {
			logger.info("Method to Covername()");
			query = getQuery("GET_COVER");
			Object obj[] = { branchCode, bean.getTransID(), branchCode };
			logger.info((new StringBuilder("Query===>")).append(query)
					.toString());
			covernames = mytemplate.queryForList(query, obj);
			logger.info("Covername() - Exit");
		} catch (Exception e) {
			logger.info((new StringBuilder("EXCEPTION @ { ")).append(e)
					.append(" }").toString());
		}
		return covernames;
	}

	public void getNewClauseID(Object val[]) {
		try {
			logger.info("Method to getNewClauseID()");
			logger.info((new StringBuilder("BranchCode===>")).append(
					branchCode).toString());
			query = getQuery("INS_CLAUSE_ID_MASTER");
			mytemplate.update(query, val);
			logger.info((new StringBuilder("Query===>")).append(query)
					.toString());
			logger.info("getNewClauseID() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getClauseIDList(String branchCode) {
		List<Map<String, Object>> clauseIDList = null;
		try {
			query = getQuery("GET_CLAUSE_ID_MASTER");
			Object obj[] = new Object[2];
			obj[0] = branchCode;
			obj[1] = branchCode;
			logger.info((new StringBuilder("Query===>")).append(query)
					.toString());
			logger.info((new StringBuilder("branchCode===>")).append(
					branchCode).toString());
			clauseIDList = mytemplate.queryForList(query, obj);
		} catch (Exception e) {
			logger.info((new StringBuilder("EXCEPTION @ { ")).append(e)
					.append(" }").toString());
		}
		return clauseIDList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getClauseID() {
		logger.info("Method to getClauseID()");
		List<Map<String, Object>> clauseIDList = null;
		try {
			logger.info("Method to getClauseID()");
			query = getQuery("GET_CLAUSE_ID_MASTER");
			logger.info((new StringBuilder("Query===>")).append(query)
					.toString());
			clauseIDList = mytemplate.queryForList(query, new Object[] {
					branchCode, branchCode });
			logger.info("getClauseID() - Exit");
		} catch (Exception e) {
			logger.info((new StringBuilder("EXCEPTION @ { ")).append(e)
					.append(" }").toString());
		}
		return clauseIDList;
	}

	public void getSelectedClauseID(final RatingEngineBean bean) {
		logger.info("Method to getSelectedClauseID()");
		try {
			logger.info("Mode===> " + bean.getClauseID());
			String query = getQuery("EDIT_CLAUSE_ID_MASTER");
			Object obj[] = new Object[3];
			obj[0] = branchCode;
			obj[1] = bean.getClauseID();
			obj[2] = branchCode;
			
			Map<String,Object> resultMap = this.mytemplate.queryForMap(query, obj);
			bean.setTransID(resultMap.get("MODE_TRANSPORT_ID")==null?"":resultMap.get("MODE_TRANSPORT_ID").toString());
			bean.setClauseID(resultMap.get("CLAUSES_ID")==null?"":resultMap.get("CLAUSES_ID").toString());
			bean.setClauseDesc(resultMap.get("CLAUSES_DESCRIPTION")==null?"":resultMap.get("CLAUSES_DESCRIPTION").toString());
			bean.setCovername(resultMap.get("COVER_ID")==null?"":resultMap.get("COVER_ID").toString());
			bean.setRsacode(resultMap.get("RSACODE")==null?"":resultMap.get("RSACODE").toString());
			bean.setDisplayorder(resultMap.get("DISPLAY_ORDER")==null?"":resultMap.get("DISPLAY_ORDER").toString());
			bean.setStatus(resultMap.get("STATUS")==null?"":resultMap.get("STATUS").toString());
			bean.setRemarks(resultMap.get("REMARKS")==null?"":resultMap.get("REMARKS").toString());
			bean.setWordingFileName(resultMap.get("PDF_LOCATION")==null?"":resultMap.get("PDF_LOCATION").toString());
			if(StringUtils.isNotBlank(bean.getWordingFileName())) {
				bean.setWordingYN("Y");
			}
		} catch (Exception e) {
			logger.info("Exception @ getSelectedClauseID() { " + e + " } ");
		}
	}

	public void getUpdateClauseID(Object val[]) {
		try {
			logger.info("Method to getUpdateClauseID()");
			logger.info((new StringBuilder("BranchCode===>")).append(
					branchCode).toString());
			query = getQuery("UPDATE_CLAUSE_ID_MASTER");
			mytemplate.update(query, val);
			logger.info((new StringBuilder("Query===>")).append(query)
					.toString());
			logger.info("getUpdateClauseID() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
	}

	public int getDateExist(Object[] val, String str)
	throws EmptyResultDataAccessException,
	IncorrectResultSizeDataAccessException {
		int checkList = 0;
		try {
			logger.info("Method to getNameExist()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery(str);
			checkList = this.mytemplate.queryForObject(query,Integer.class, val);
			logger.info("Query===>" + query);
			logger.info("Argas===>" + StringUtils.join(val, ","));
			logger.info("getNameExist() - Exit");
		}

		catch (EmptyResultDataAccessException e) {
			logger.info("Name Not Exist");

		} catch (IncorrectResultSizeDataAccessException e) {
			logger.info("Name Not Exist");

		}
		return checkList;
	}

	public List<Map<String, Object>> getCountriesDetail() {
		logger.info("Method to getCountries()");
		List<Map<String, Object>> countriesDetail = null;
		try {
			query = getQuery("GET_COUNTRY_NAME");
			logger.info("Query===>" + query);
			countriesDetail = this.mytemplate.queryForList(query);
			logger.info("getCountries() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return countriesDetail;
	}

	public List<Map<String, Object>> getNewcountriesDetail() {
		logger.info("Method to getCountries()");
		List<Map<String, Object>> newcountriesDetail = null;
		try {
			query = getQuery("GET_COUNTRY_NEW_NAME");
			logger.info("Query===>" + query);
			newcountriesDetail = this.mytemplate.queryForList(query);
			logger.info("getCountries() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return newcountriesDetail;
	}

	public List<Map<String, Object>> getCountriesDet1() {
		logger.info("Method to getCountriesDet()");
		List<Map<String, Object>> countriesDet1 = null;
		try {
			query = getQuery("GET_COUNTRY_NAME_DET");
			logger.info("Query===>" + query);
			countriesDet1 = this.mytemplate.queryForList(query);
			logger.info("getCountriesDet() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return countriesDet1;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getCountryList(String searchField,
			String searchString, String searchOper) {
		Object obj[] = null;
		List countryList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_COUNTRY_LIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";
				else if ("nq".equalsIgnoreCase(searchOper))
					str = " != upper(?)";

				if ("countryName".equalsIgnoreCase(searchField))
					query += " and upper(E.COUNTRY_NAME) " + str;
				else if ("countryNat".equalsIgnoreCase(searchField))
					query += " and upper(E.NATIONALITY_NAME) " + str;

				obj = new Object[] { searchString };
			} else {
				obj = new Object[] {};
			}
			query += "order by COUNTRY_NAME";
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			countryList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1
					.setCountryID(rset.getString("COUNTRY_ID") == null ? ""
							: rset.getString("COUNTRY_ID"));
					bean1
					.setCountryName(rset.getString("COUNTRY_NAME") == null ? ""
							: rset.getString("COUNTRY_NAME"));
					bean1
					.setCountryNat(rset.getString("NATIONALITY_NAME") == null ? ""
							: rset.getString("NATIONALITY_NAME"));
					bean1
					.setEff_date(rset.getString("EFFECTIVE_DATE") == null ? ""
							: rset.getString("EFFECTIVE_DATE"));
					bean1.setEdit(rset.getString("COUNTRY_ID") == null ? ""
							: rset.getString("COUNTRY_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return countryList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getBankList(String branchcode,String searchField,
			String searchString, String searchOper) {
		logger.info("Method to getBankList()");
		Object obj[] = null;
		List bankList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_BANKLIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";
				else if ("nq".equalsIgnoreCase(searchOper))
					str = " != upper(?)";

				if ("bankName".equalsIgnoreCase(searchField))
					query += " and upper(E.BANK_NAME) " + str;

				obj = new Object[] { searchString };
			} else {
				obj = new Object[] {};
			}
			query += "order by BANK_NAME";
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			bankList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1.setBankID(rset.getString("BANK_ID") == null ? ""
							: rset.getString("BANK_ID"));
					bean1.setBankName(rset.getString("BANK_NAME") == null ? ""
							: rset.getString("BANK_NAME"));
					bean1
					.setEff_date(rset.getString("EFFECTIVE_DATE") == null ? ""
							: rset.getString("EFFECTIVE_DATE"));
					bean1.setEdit(rset.getString("BANK_ID") == null ? "" : rset
							.getString("BANK_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
			logger.info("getBankList() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return bankList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getMaterialsList(String branchCode) {
		logger.info("Method to getMaterialList()");
		List getMaterialList = null;
		String query = "";
		try {
			Object[] obj = new Object[1];
			obj[0] = branchCode;
			query = getQuery("GET_MATERIAL_MASTER_LIST");
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			getMaterialList = this.mytemplate.query(query, obj,
					new RowMapper() {
				public Object mapRow(final ResultSet rset,
						final int idVal) throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1
					.setMaterialID(rset
							.getString("MATERIAL_ID") == null ? ""
									: rset.getString("MATERIAL_ID"));
					bean1.setMaterialName(rset
							.getString("MATERIAL_DESC") == null ? ""
									: rset.getString("MATERIAL_DESC"));
					bean1.setMaterialRate(rset
							.getString("MATERIAL_RATE") == null ? ""
									: rset.getString("MATERIAL_RATE"));
					bean1
					.setEff_date(rset
							.getString("EFFECTIVE_DATE") == null ? ""
									: rset.getString("EFFECTIVE_DATE"));
					bean1
					.setEdit(rset.getString("MATERIAL_ID") == null ? ""
							: rset.getString("MATERIAL_ID"));
					bean1
					.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
			logger.info("getMaterialList() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return getMaterialList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getWarrateList(String searchField,
			String searchString, String searchOper) {
		Object obj[] = null;
		List warrateList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_WARRATE_LIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("warName".equalsIgnoreCase(searchField))
					query += " and upper(w.war_desc) " + str;
				else if ("transID".equalsIgnoreCase(searchField))
					query += " and upper(m.transport_description) " + str;

				obj = new Object[] { branchCode, branchCode, searchString };
			} else {
				obj = new Object[] { branchCode, branchCode };
			}
			query += " ORDER BY w.war_id";
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			warrateList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1.setWarID(rset.getString("war_id") == null ? "" : rset
							.getString("war_id"));
					bean1.setWarName(rset.getString("war_desc") == null ? ""
							: rset.getString("war_desc"));
					bean1.setWarRate(rset.getString("war_rate") == null ? ""
							: rset.getString("war_rate"));
					bean1
					.setEff_date(rset.getString("effective_date") == null ? ""
							: rset.getString("effective_date"));
					bean1
					.setTransID(rset.getString("transport_description") == null ? ""
							: rset.getString("transport_description"));
					bean1.setEdit(rset.getString("war_id") == null ? "" : rset
							.getString("war_id"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return warrateList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getClauseList(RatingEngineBean bean,
			String branchCode, String searchField, String searchString,
			String searchOper) {
		Object obj[] = null;
		List wsrccList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_CLAUSE_ID_MASTER");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("clauseDesc".equalsIgnoreCase(searchField))
					query += " and upper(C.CLAUSES_DESCRIPTION) " + str;
				else if ("transDesc".equalsIgnoreCase(searchField))
					query += " and upper(MT.TRANSPORT_DESCRIPTION) " + str;
				else if ("coverName".equalsIgnoreCase(searchField))
					query += " and upper(COV.COVER_NAME) " + str;

				obj = new Object[] { branchCode, branchCode, branchCode,
						searchString };
			} else {
				obj = new Object[] { branchCode, branchCode, branchCode };
			}
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			wsrccList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1.setClauseID(rset.getString("CLAUSES_ID") == null ? ""
							: rset.getString("CLAUSES_ID"));
					bean1.setCoverID(rset.getString("COVER_ID") == null ? ""
							: rset.getString("COVER_ID"));
					bean1
					.setTransID(rset.getString("MODE_TRANSPORT_ID") == null ? ""
							: rset.getString("MODE_TRANSPORT_ID"));
					bean1
					.setCoverName(rset.getString("COVER_NAME") == null ? ""
							: rset.getString("COVER_NAME"));
					bean1
					.setTransDesc(rset
							.getString("TRANSPORT_DESCRIPTION") == null ? ""
									: rset.getString("TRANSPORT_DESCRIPTION"));
					bean1
					.setClauseDesc(rset
							.getString("CLAUSES_DESCRIPTION") == null ? ""
									: rset.getString("CLAUSES_DESCRIPTION"));
					bean1.setRsacode(rset.getString("RSACODE") == null ? ""
							: rset.getString("RSACODE"));
					bean1
					.setDisplayorder(rset.getString("DISPLAY_ORDER") == null ? ""
							: rset.getString("DISPLAY_ORDER"));
					bean1.setEdit(rset.getString("CLAUSES_ID") == null ? ""
							: rset.getString("CLAUSES_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					bean1.setRemarks(rset.getString("REMARKS") == null ? ""
							: rset.getString("REMARKS"));

					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wsrccList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getCommodityList(String branchcode,
			String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List commodityList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_COMMODITY_DETAIL");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("commodityName".equalsIgnoreCase(searchField))
					query += " and upper(CM.COMMODITY_NAME) " + str;

				obj = new Object[] { branchcode, branchcode, searchString };
			} else {
				obj = new Object[] { branchcode, branchcode };
			}
			int j =  obj.length;
			query+="ORDER BY CM.COMMODITY_NAME";
			/*for(int i = 0; i < j; i++)
			{
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}*/

			logger.info("Query==> " + query);
			logger.info("Args==> " + StringUtils.join(obj,","));

			commodityList = this.mytemplate.query(query,obj,new RowMapper() {
				public Object mapRow(final ResultSet rset,final int idVal) throws SQLException {
					RatingEngineBean bean1=new RatingEngineBean();
					bean1.setCommodityID(rset.getString("COMMODITY_ID")==null?"":rset.getString("COMMODITY_ID"));
					bean1.setCommodityName(rset.getString("COMMODITY_NAME")==null?"":rset.getString("COMMODITY_NAME"));
					bean1.setEff_date(rset.getString("EFFECTIVE_DATE")==null?"":rset.getString("EFFECTIVE_DATE"));
					bean1.setEdit(rset.getString("COMMODITY_ID")==null?"":rset.getString("COMMODITY_ID"));
					bean1.setStatus(rset.getString("STATUS")==null?"":rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commodityList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getCovercommList(String branchcode,
			String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List<RatingEngineBean> coverList = new ArrayList<RatingEngineBean>();
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_COVERCOMM_DETAIL");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("coverName".equalsIgnoreCase(searchField))
					query += " and upper(COVER_NAME) " + str;

				obj = new Object[] { branchcode, branchcode, searchString };
			} else {
				obj = new Object[] { branchcode, branchcode };
			}
			query +="order by CATEGORY_DESCRIPTION";
			//int j =  obj.length;
			/*query+=" order by cc.COMMODITY_TYPEID,COVER_ID";
			for(int i = 0; i < j; i++)
			{
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}*/
			logger.info((new StringBuilder("Query==>")).append(query).toString());
			logger.info("Args==> " +  StringUtils.join(obj,","));
			/*coverList = this.mytemplate.query(query,obj,new RowMapper() {
				public Object mapRow(final ResultSet rset,final int idVal) throws SQLException {
					RatingEngineBean bean1=new RatingEngineBean();
					bean1.setCatname(rset.getString("CATEGORY_DESCRIPTION")==null?"":rset.getString("CATEGORY_DESCRIPTION"));
					bean1.setCoverName(rset.getString("COVER_NAME")==null?"":rset.getString("COVER_NAME"));
					bean1.setCoverDesc(rset.getString("COVER_DESCRIPTION")==null?"":rset.getString("COVER_DESCRIPTION"));
					bean1.setCoverRate1(rset.getDouble("COVER_RATE"));
					bean1.setCatrate1(rset.getDouble("CATEGORY_RATE"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					bean1.setEdit(rset.getString("SNO__") == null ? "" : rset
							.getString("SNO__"));
					return bean1;
				}
			});*/
			List<Map<String,Object>> list = this.mytemplate.queryForList(query,obj);
			for(int i=0  ; i<list.size() ; i++) {
				Map<String,Object> tempMap = list.get(i);
				RatingEngineBean tempBean = new RatingEngineBean();
				tempBean.setCatname(tempMap.get("CATEGORY_DESCRIPTION")==null?"":tempMap.get("CATEGORY_DESCRIPTION").toString());
				tempBean.setCoverName(tempMap.get("COVER_NAME")==null?"":tempMap.get("COVER_NAME").toString());
				tempBean.setCoverDesc(tempMap.get("COVER_DESCRIPTION")==null?"":tempMap.get("COVER_DESCRIPTION").toString());
				tempBean.setCoverRate1(Double.valueOf(tempMap.get("COVER_RATE").toString()));
				tempBean.setCatrate1(Double.valueOf(tempMap.get("CATEGORY_RATE").toString()));
				tempBean.setStatus(tempMap.get("STATUS") == null ? "":tempMap.get("STATUS").toString());
				//tempBean.setEdit(tempMap.get("SNO__") == null ? "" : tempMap.get("SNO__").toString());
				String CoverageID = tempMap.get("COVER_ID") == null ? "" : tempMap.get("COVER_ID").toString();
				String commodityID = tempMap.get("COMMODITY_TYPEID") == null ? "" : tempMap.get("COMMODITY_TYPEID").toString();
				tempBean.setEdit("CoverageID="+CoverageID+"&commodityID="+commodityID);
				coverList.add(tempBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coverList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getCategoryList(String branchcode,
			String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List categoryList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_CAT_LIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("catname".equalsIgnoreCase(searchField))
					query += " and upper(DETAIL_NAME) " + str;

				//obj=new Object[] {branchcode,branchcode,searchString};
				obj=new Object[] {branchcode,searchString};
			}else
			{
				//obj=new Object[] {branchcode,branchcode};
				obj=new Object[] {branchcode};
			}
			/*int j =  obj.length;		            
			for(int i = 0; i < j; i++)
			{
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}*/
			logger.info("Args==> " + StringUtils.join(obj,","));
			query += " order by COMMODITY_TYPEID";
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			categoryList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1
					.setCatID(rset.getString("COMMODITY_TYPEID") == null ? ""
							: rset.getString("COMMODITY_TYPEID"));
					bean1.setCatname(rset.getString("DETAIL_NAME") == null ? ""
							: rset.getString("DETAIL_NAME"));
					bean1
					.setCatrate(rset.getString("COMMODITY_TYPE_RATE") == null ? ""
							: rset.getString("COMMODITY_TYPE_RATE"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					bean1
					.setEdit(rset.getString("COMMODITY_TYPEID") == null ? ""
							: rset.getString("COMMODITY_TYPEID"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getSaleList(String branchcode,
			String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List saleList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_SALETERM_LIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("saleName".equalsIgnoreCase(searchField))
					query += " and upper(SALE_TERM_NAME) " + str;

				obj = new Object[] { branchcode, searchString };
			} else {
				obj = new Object[] { branchcode };
			}
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString()+" order by SALE_TERM_NAME");
			saleList = this.mytemplate.query(query+" order by SALE_TERM_NAME", obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1.setSaleID(rset.getString("SALE_TERM_ID") == null ? ""
							: rset.getString("SALE_TERM_ID"));
					bean1
					.setSaleName(rset.getString("SALE_TERM_NAME") == null ? ""
							: rset.getString("SALE_TERM_NAME"));
					bean1
					.setSaleValue(rset.getString("SALE_TERM_VALUE") == null ? ""
							: rset.getString("SALE_TERM_VALUE"));
					bean1.setEdit(rset.getString("SALE_TERM_ID") == null ? ""
							: rset.getString("SALE_TERM_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saleList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getToleList(String branchcode,
			String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List toleList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_TOLERANCE_MASTER_LIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("toleName".equalsIgnoreCase(searchField))
					query += " and upper(TOLERANCE_NAME) " + str;

				obj = new Object[] { branchcode, searchString };
			} else {
				obj = new Object[] { branchcode };
			}
			query+="order by TOLERANCE_ID";
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			toleList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1.setToleID(rset.getString("TOLERANCE_ID") == null ? ""
							: rset.getString("TOLERANCE_ID"));
					bean1
					.setToleName(rset.getString("TOLERANCE_NAME") == null ? ""
							: rset.getString("TOLERANCE_NAME"));
					bean1
					.setToleValue(rset.getString("TOLERANCE_VALUE") == null ? ""
							: rset.getString("TOLERANCE_VALUE"));
					bean1.setEdit(rset.getString("TOLERANCE_ID") == null ? ""
							: rset.getString("TOLERANCE_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toleList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getComExList(String branchcode,
			String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List vesselList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_COMEXLIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||?||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = ?";

				if ("startAmt".equalsIgnoreCase(searchField))
					query += " and START_SUM_INSURED " + str;
				else if ("endAmt".equalsIgnoreCase(searchField))
					query += " and END_SUM_INSURED " + str;

				obj = new Object[] { branchcode, branchcode, searchString };
			} else {
				obj = new Object[] { branchcode, branchcode };
			}
			int j = obj.length;
			query += "ORDER BY SNO";
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			vesselList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1.setComExID(rset.getString("SNO") == null ? "" : rset
							.getString("SNO"));
					bean1
					.setStartAmt(rset.getString("START_SUM_INSURED") == null ? ""
							: rset.getString("START_SUM_INSURED"));
					bean1
					.setEndAmt(rset.getString("END_SUM_INSURED") == null ? ""
							: rset.getString("END_SUM_INSURED"));
					bean1
					.setDeductible(rset.getString("DEDUCTIBLE") == null ? ""
							: rset.getString("DEDUCTIBLE"));
					bean1.setComExRate(rset.getString("RATE") == null ? ""
							: rset.getString("RATE"));
					bean1
					.setEff_date(rset.getString("EFFECTIVE_DATE") == null ? ""
							: rset.getString("EFFECTIVE_DATE"));
					bean1.setEdit(rset.getString("SNO") == null ? "" : rset
							.getString("SNO"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vesselList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getVesselList(String searchField,
			String searchString, String searchOper) {
		Object obj[] = null;
		List vesselList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_VESSEL_LIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("vesselName".equalsIgnoreCase(searchField))
					query += " where upper(VESSEL_NAME) " + str;
				else if ("vesselClass".equalsIgnoreCase(searchField))
					query += " where upper(VESSEL_CLASS) " + str;

				obj = new Object[] { searchString };
			} else {
				obj = new Object[] {};
			}
			int j = obj.length;
			query += " ORDER BY VESSEL_NAME";
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			vesselList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1.setVesselID(rset.getString("VESSEL_ID") == null ? ""
							: rset.getString("VESSEL_ID"));
					bean1
					.setVesselName(rset.getString("VESSEL_NAME") == null ? ""
							: rset.getString("VESSEL_NAME"));
					bean1
					.setVesselClass(rset.getString("VESSEL_CLASS") == null ? ""
							: rset.getString("VESSEL_CLASS"));
					bean1.setEdit(rset.getString("VESSEL_ID") == null ? ""
							: rset.getString("VESSEL_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vesselList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getSettlingList(String branchcode,
			String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List settlingList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_AGENT_LIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("agentName".equalsIgnoreCase(searchField))
					query += " and upper(SM.SETTLING_AGENT_NAME) " + str;
				else if ("countryID".equalsIgnoreCase(searchField))
					query += " and upper(CM.COUNTRY_NAME) " + str;
				else if ("cityID".equalsIgnoreCase(searchField))
					query += " and upper(CITY_NAME) " + str;

				obj = new Object[] { branchcode, searchString };
			} else {
				obj = new Object[] { branchcode };
			}
			int j = obj.length;
			query += " order by CM.COUNTRY_NAME";
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			settlingList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1
					.setAgentID(rset.getString("SETTLING_AGENT_ID") == null ? ""
							: rset.getString("SETTLING_AGENT_ID"));
					bean1
					.setAgentName(rset.getString("SETTLING_AGENT_NAME") == null ? ""
							: rset.getString("SETTLING_AGENT_NAME"));
					bean1
					.setCountryID(rset.getString("COUNTRY_NAME") == null ? ""
							: rset.getString("COUNTRY_NAME"));
					// bean1.setCityID(rset.getString("CITY_NAME")==null?"":rset.getString("CITY_NAME"));
					bean1
					.setEdit(rset.getString("SETTLING_AGENT_ID") == null ? ""
							: rset.getString("SETTLING_AGENT_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return settlingList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getExchangeList(String searchField,
			String searchString, String searchOper, String branchCode) {
		Object obj[] = null;
		List exchangeList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_EXCHANGE_MASTER");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("currencyID".equalsIgnoreCase(searchField))
					query += " and upper(CM.CURRENCY_NAME) " + str;

				obj = new Object[] { branchCode, searchString };
			} else {
				obj = new Object[] { branchCode };
			}
			query +=" order by CM.CURRENCY_NAME";
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			exchangeList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1
					.setExchangeID(rset.getString("EXCHANGE_ID") == null ? ""
							: rset.getString("EXCHANGE_ID"));
					bean1
					.setCurrencyID(rset.getString("CURRENCY_NAME") == null ? ""
							: rset.getString("CURRENCY_NAME"));
					bean1
					.setExchangeRate(rset.getString("EXCHANGE_RATE") == null ? ""
							: rset.getString("EXCHANGE_RATE"));
					bean1.setEdit(rset.getString("EXCHANGE_ID") == null ? ""
							: rset.getString("EXCHANGE_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exchangeList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getCurrencyList(String searchField, String searchString, String searchOper, String branchCode) {
		Object obj[] = null;
		List currencyList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_CURRENCY_NAME");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("currencyType".equalsIgnoreCase(searchField))
					query += " and upper(CM.CURRENCY_NAME) " + str;

				obj = new Object[] {branchCode, searchString};
			} else {
				obj = new Object[] {branchCode};
			}
			query += " ORDER BY CM.CURRENCY_NAME";
			logger.info("Args===>" + StringUtils.join(obj,","));
			logger.info("Query==>" + query);
			currencyList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1
					.setCurrencyID(rset.getString("CURRENCY_ID") == null ? ""
							: rset.getString("CURRENCY_ID"));
					bean1
					.setCurrencyType(rset.getString("CURRENCY_NAME") == null ? ""
							: rset.getString("CURRENCY_NAME"));
					bean1
					.setSubCurrency(rset.getString("SUB_CURRENCY") == null ? ""
							: rset.getString("SUB_CURRENCY"));
					bean1
					.setCurrencyShortName(rset.getString("SHORT_NAME") == null ? ""
							: rset.getString("SHORT_NAME"));
					bean1.setEdit(rset.getString("CURRENCY_ID") == null ? ""
							: rset.getString("CURRENCY_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currencyList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getExtraCoverList(String branchcode,
			String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List extracoverList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_EXTRA_COVER_LIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("extraCoverName".equalsIgnoreCase(searchField))
					query += " and upper(E.EXTRA_COVER_NAME) " + str;
				else if ("transID".equalsIgnoreCase(searchField))
					query += " and upper(m.transport_description) " + str;

				obj = new Object[] { branchCode, branchCode, searchString };
			} else {
				obj = new Object[] { branchCode, branchCode };
			}
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			extracoverList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1
					.setExtraCoverId(rset.getString("EXTRA_COVER_ID") == null ? ""
							: rset.getString("EXTRA_COVER_ID"));
					bean1
					.setExtraCoverName(rset
							.getString("EXTRA_COVER_NAME") == null ? ""
									: rset.getString("EXTRA_COVER_NAME"));
					bean1
					.setTransID(rset.getString("transport_description") == null ? ""
							: rset.getString("transport_description"));
					bean1.setEdit(rset.getString("EXTRA_COVER_ID") == null ? ""
							: rset.getString("EXTRA_COVER_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return extracoverList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getTransportList(String branchcode,
			String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List cityList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_TRANSPORT_LIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("transDesc".equalsIgnoreCase(searchField))
					query += " and upper(TRANSPORT_DESCRIPTION) " + str;

				obj = new Object[] { branchcode, searchString };
			} else {
				obj = new Object[] { branchcode };
			}
			query +=" order By TRANSPORT_DESCRIPTION";
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			cityList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1
					.setTransID(rset.getString("MODE_TRANSPORT_ID") == null ? ""
							: rset.getString("MODE_TRANSPORT_ID"));
					bean1
					.setTransDesc(rset
							.getString("TRANSPORT_DESCRIPTION") == null ? ""
									: rset.getString("TRANSPORT_DESCRIPTION"));
					bean1
					.setEdit(rset.getString("MODE_TRANSPORT_ID") == null ? ""
							: rset.getString("MODE_TRANSPORT_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getWarrantyList(String branchcode,
			String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List cityList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_WARRANTY_LIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("warrantyDesc".equalsIgnoreCase(searchField))
					query += " and upper(WARRANTY_DESCRIPTION) " + str;

				obj = new Object[] { branchcode, searchString };
			} else {
				obj = new Object[] { branchcode };
			}
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			cityList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1
					.setWarrantyID(rset.getString("WARRANTY_ID") == null ? ""
							: rset.getString("WARRANTY_ID"));
					bean1.setWarrantyDesc(rset
							.getString("WARRANTY_DESCRIPTION") == null ? ""
									: rset.getString("WARRANTY_DESCRIPTION"));
					bean1.setEdit(rset.getString("WARRANTY_ID") == null ? ""
							: rset.getString("WARRANTY_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList;
	}

	public List<RatingEngineBean> getConstantMasterList(RatingEngineBean bean, String branchCode, String searchField, String searchString, String searchOper) {
		List<RatingEngineBean> finalList = new ArrayList<RatingEngineBean>();
		try {
			String query = getQuery("GET_CONTANTMASTER_LIST");
			String str = "";
			Object obj[] = null;
			if (StringUtils.isNotBlank(searchField) && StringUtils.isNotBlank(searchString) && StringUtils.isNotBlank(searchOper)) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("categoryName".equalsIgnoreCase(searchField))
					query += " and upper(CATEGORY_NAME) " + str;
				obj = new Object[] { branchCode, searchString };
			}
			else {
				obj = new Object[] { branchCode };
			}
			query += " ORDER BY CATEGORY_NAME";
			logger.info("Query==> " + query);
			logger.info("Args==> " + StringUtils.join(obj,","));

			List<Map<String,Object>> list = this.mytemplate.queryForList(query,obj);
			for(int i=0 ; i<list.size() ; i++) {
				Map<String,Object> tempMap = list.get(i);
				RatingEngineBean tempBean = new RatingEngineBean();
				tempBean.setCategoryId(tempMap.get("CATEGORY_ID")==null?"":tempMap.get("CATEGORY_ID").toString());
				tempBean.setCategoryName(tempMap.get("CATEGORY_NAME")==null?"":tempMap.get("CATEGORY_NAME").toString());
				tempBean.setStatus(tempMap.get("STATUS")==null?"":tempMap.get("STATUS").toString());
				tempBean.setEdit(tempMap.get("CATEGORY_ID")==null?"":tempMap.get("CATEGORY_ID").toString());
				if("Active".equalsIgnoreCase(tempBean.getStatus())) {
					tempBean.setAddDetail("<a href='constantdetailRating.action?menuType=constantdetail&categoryID="+tempBean.getCategoryId()+"'>Detail</a>");
				}
				else {
					tempBean.setAddDetail("");
				}
				finalList.add(tempBean);
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return finalList;
	}

	public List<RatingEngineBean> getConstantList(RatingEngineBean bean, String branchcode, String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List<RatingEngineBean> cityList = new ArrayList<RatingEngineBean>();
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_CONSTANT_LIST");
			if (StringUtils.isNotBlank(searchField) && StringUtils.isNotBlank(searchString) && StringUtils.isNotBlank(searchOper)) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("detailName".equalsIgnoreCase(searchField))
					query += " and upper(DETAIL_NAME) " + str;

				obj = new Object[] { branchcode, bean.getCategoryID(), searchString };
			} else {
				obj = new Object[] { branchcode, bean.getCategoryID() };
			}
			query += " ORDER BY DETAIL_NAME";
			logger.info("Query==> " + query);
			logger.info("Args===> " + StringUtils.join(obj,","));

			List<Map<String,Object>> list = this.mytemplate.queryForList(query,obj);
			for(int i=0 ; i<list.size() ; i++) {
				Map<String,Object> tempMap = list.get(i);
				RatingEngineBean bean1 = new RatingEngineBean();
				bean1.setCategory_detail_id(tempMap.get("CATEGORY_DETAIL_ID") == null ? "" : tempMap.get("CATEGORY_DETAIL_ID").toString());
				bean1.setDetailName(tempMap.get("DETAIL_NAME") == null ? "" : tempMap.get("DETAIL_NAME").toString());
				bean1.setEdit(bean1.getCategory_detail_id() + "&categoryID=" + (tempMap.get("CATEGORY_ID") == null ? "" : tempMap.get("CATEGORY_ID").toString()));
				bean1.setStatus(tempMap.get("STATUS") == null ? "" : tempMap.get("STATUS").toString());
				cityList.add(bean1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getExclusionList(String branchcode,
			String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List cityList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_EXCLUSION_LIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("exclusionName".equalsIgnoreCase(searchField))
					query += " and upper(EXCLUSION_DESCRIPTION) " + str;

				obj = new Object[] { branchcode, searchString };
			} else {
				obj = new Object[] { branchcode };
			}
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			cityList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1
					.setExclusionID(rset.getString("EXCLUSION_ID") == null ? ""
							: rset.getString("EXCLUSION_ID"));
					bean1.setExclusionName(rset
							.getString("EXCLUSION_DESCRIPTION") == null ? ""
									: rset.getString("EXCLUSION_DESCRIPTION"));
					bean1.setEdit(rset.getString("EXCLUSION_ID") == null ? ""
							: rset.getString("EXCLUSION_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getCityList(String searchField,
			String searchString, String searchOper) {
		Object obj[] = null;
		List cityList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_CITY_LIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("cityName".equalsIgnoreCase(searchField))
					query += " and upper(CI.CITY_NAME) " + str;
				else if ("countryID".equalsIgnoreCase(searchField))
					query += " and upper(CO.COUNTRY_NAME) " + str;

				obj = new Object[] { searchString };
			} else {
				obj = new Object[] {};
			}
			query+=" order by CITY_NAME";
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			cityList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1.setCityID(rset.getString("CITY_ID") == null ? ""
							: rset.getString("CITY_ID"));
					bean1.setCityName(rset.getString("CITY_NAME") == null ? ""
							: rset.getString("CITY_NAME"));
					bean1
					.setCountryID(rset.getString("COUNTRY_NAME") == null ? ""
							: rset.getString("COUNTRY_NAME"));
					bean1.setEdit(rset.getString("CITY_ID") == null ? "" : rset
							.getString("CITY_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getWsrccList(RatingEngineBean bean,
			String branchCode, String searchField, String searchString,
			String searchOper) {
		Object obj[] = null;
		List wsrccList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_WSRCC_LIST");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";
				else if ("nq".equalsIgnoreCase(searchOper))
					str = " != upper(?)";

				if ("wsrccdesc".equalsIgnoreCase(searchField))
					query += " and upper(C.CLAUSES_DESCRIPTION) " + str;
				else if ("transDesc".equalsIgnoreCase(searchField))
					query += " and upper(mt.TRANSPORT_DESCRIPTION) " + str;
				else if ("coverName".equalsIgnoreCase(searchField))
					query += " and upper(COV.COVER_NAME) " + str;

				obj = new Object[] { branchCode, branchCode, branchCode,
						searchString };
			} else {
				obj = new Object[] { branchCode, branchCode, branchCode };
			}
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			wsrccList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1.setWsrccid(rset.getString("CLAUSES_ID") == null ? ""
							: rset.getString("CLAUSES_ID"));
					bean1.setCoverID(rset.getString("COVER_ID") == null ? ""
							: rset.getString("COVER_ID"));
					bean1
					.setTransID(rset.getString("MODE_TRANSPORT_ID") == null ? ""
							: rset.getString("MODE_TRANSPORT_ID"));
					bean1
					.setCoverName(rset.getString("COVER_NAME") == null ? ""
							: rset.getString("COVER_NAME"));
					bean1
					.setTransDesc(rset
							.getString("TRANSPORT_DESCRIPTION") == null ? ""
									: rset.getString("TRANSPORT_DESCRIPTION"));
					bean1
					.setWsrccdesc(rset.getString("CLAUSES_DESCRIPTION") == null ? ""
							: rset.getString("CLAUSES_DESCRIPTION"));
					bean1.setRsacode(rset.getString("RSACODE") == null ? ""
							: rset.getString("RSACODE"));
					bean1
					.setDisplayorder(rset.getString("DISPLAY_ORDER") == null ? ""
							: rset.getString("DISPLAY_ORDER"));
					bean1.setEdit(rset.getString("CLAUSES_ID") == null ? ""
							: rset.getString("CLAUSES_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					bean1.setRemarks(rset.getString("REMARKS") == null ? ""
							: rset.getString("REMARKS"));

					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wsrccList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getCoverList(String branchCode,
			String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List coverList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_COVER_NAME");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("coverName".equalsIgnoreCase(searchField))
					query += " and upper(C.COVER_NAME) " + str;
				else if ("transID".equalsIgnoreCase(searchField))
					query += " and upper(M.TRANSPORT_DESCRIPTION) " + str;

				obj=new Object[] {branchCode, branchCode, searchString};
			}else
			{
				//obj=new Object[] {branchCode, branchCode};
				obj=new Object[] {branchCode};
			}
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			coverList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1.setCoverID(rset.getString("COVER_ID") == null ? ""
							: rset.getString("COVER_ID"));
					bean1
					.setCoverName(rset.getString("COVER_NAME") == null ? ""
							: rset.getString("COVER_NAME"));
					bean1
					.setTransID(rset.getString("TRANSPORT_DESCRIPTION") == null ? ""
							: rset.getString("TRANSPORT_DESCRIPTION"));
					bean1
					.setCoverRate(rset.getString("COVER_RATE") == null ? ""
							: rset.getString("COVER_RATE"));
					bean1.setEdit(rset.getString("COVER_ID") == null ? ""
							: rset.getString("COVER_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coverList;
	}

	@SuppressWarnings("unchecked")
	public List<RatingEngineBean> getErrorList(String branchcode,
			String searchField, String searchString, String searchOper) {
		Object obj[] = null;
		List errorList = null;
		String query = "", str = "like '%'||upper(?)||'%'";
		try {
			query = getQuery("GET_ERROR_MASTER");
			if (searchField != null && searchString != null
					&& searchOper != null) {
				if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("errorDesc".equalsIgnoreCase(searchField))
					query += " and upper(EM.ERROR_DESC) " + str;

				obj = new Object[] { branchCode, searchString };
			} else {
				obj = new Object[] { branchCode };
			}
			int j = obj.length;
			for (int i = 0; i < j; i++) {
				Object k = obj[i];
				logger.info((new StringBuilder("Args===>")).append(k)
						.toString());
			}
			logger.info((new StringBuilder("Query==>")).append(query)
					.toString());
			errorList = this.mytemplate.query(query, obj, new RowMapper() {
				public Object mapRow(final ResultSet rset, final int idVal)
				throws SQLException {
					RatingEngineBean bean1 = new RatingEngineBean();
					bean1.setErrorID(rset.getString("ERROR_ID") == null ? ""
							: rset.getString("ERROR_ID"));
					bean1
					.setErrorDesc(rset.getString("ERROR_DESC") == null ? ""
							: rset.getString("ERROR_DESC"));
					bean1
					.setStagename(rset.getString("STAGE_DESC") == null ? ""
							: rset.getString("STAGE_DESC"));
					bean1
					.setProductname(rset.getString("PRODUCT_NAME") == null ? ""
							: rset.getString("PRODUCT_NAME"));
					bean1.setEdit(rset.getString("ERROR_ID") == null ? ""
							: rset.getString("ERROR_ID"));
					bean1.setStatus(rset.getString("STATUS") == null ? ""
							: rset.getString("STATUS"));
					return bean1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;
	}

	public int getAmountExist(String startAmt, String endAmt, String branchCode) {
		Object[] obj1 = new Object[2];
		obj1[0] = branchCode;
		obj1[1] = startAmt;
		Object[] obj2 = new Object[2];
		obj2[0] = branchCode;
		obj2[1] = endAmt;
		int checkList = 0;
		int result1 = 0;
		int result2 = 0;
		try {
			logger.info("Method to getAmountExist()");
			logger.info("BranchCode===>" + branchCode);
			query = getQuery("CHECK_COM_EX_COUNT_START");
			result1 = this.mytemplate.queryForObject(query,Integer.class, obj1);
			query = getQuery("CHECK_COM_EX_COUNT_END");
			if (result1 == 0 && result2 == 0) {
				checkList = 0;
			} else {
				checkList = 1;
			}
			result2 = this.mytemplate.queryForObject(query,Integer.class, obj2);
			logger.info("Query==>" + query);
			logger.info("args==>" + StringUtils.join(obj2, ", "));
			logger.info("getAmountExist() - Exit");
		} catch (Exception e) {
			logger.info("Amount Not Exist");
		}
		return checkList;
	}

	public int checkCover(RatingEngineBean bean, String branchcode) {
		Object obj[] = null;
		int result = 0;
		try {
			logger.info("Method to checkCover()");
			query = getQuery("CHECK_COVER_NAME");
			obj = new Object[] { branchcode, bean.getTransID(), branchcode,
					bean.getCoverID(), branchcode };
			logger.info("Query==>" + query);
			logger.info("args==>" + StringUtils.join(obj, ", "));
			result = this.mytemplate.queryForObject(query,Integer.class, obj);
			logger.info("checkCover() - Exit");
		} catch (Exception e) {
			logger.info(e);
		}
		return result;
	}

	public int wsrccModify(final RatingEngineBean bean, String branchcode,String operation, String pdfName) {
		logger.info("WRSCC - Enter ");
		int countval = 0;
		int Extrid = 1;
		Object[] obj = null;

		try {
			if ("add".equalsIgnoreCase(operation)) {
				query = getQuery("INS_CLAUSE_ID_MASTER");
				obj = new Object[] { branchcode, bean.getCovername(),
						branchcode, bean.getWsrccdesc(), Extrid,
						bean.getRsacode(), bean.getDisplayorder(),bean.getRemarks(),
						bean.getStatus(), branchcode, pdfName, bean.getRsacode()};
			} else if ("edit".equalsIgnoreCase(operation)) {
				query = getQuery("UPDATE_CLAUSE_ID_MASTER");
				obj = new Object[] { bean.getWsrccdesc(),
						bean.getStatus(), bean.getRsacode(),
						bean.getDisplayorder(),bean.getRemarks(), pdfName,bean.getRsacode(), bean.getWsrccid(), branchcode,
						Extrid, bean.getCovername() };
			}
			logger.info("Query==>" + query);
			logger.info("args==>" + StringUtils.join(obj, ", "));
			countval = this.mytemplate.update(query, obj);
		} catch (Exception e) {
			logger.info(e);
		}
		logger.info("WSRCC - Exit");
		return countval;
	}

	public void wsrccEdit(final RatingEngineBean bean, String branchcode) {
		logger.info("Method to wsrccEdit()");
		try {
			logger.info((new StringBuilder("Mode===>")).append(
					bean.getWsrccid()).toString());
			query = getQuery("EDIT_CLAUSE_ID_MASTER");
			Object obj[] = new Object[3];
			obj[0] = branchcode;
			obj[1] = bean.getWsrccid();
			obj[2] = branchcode;
			logger.info("Query==>" + query);
			logger.info("args==>" + StringUtils.join(obj, ", "));
			Map<String,Object> resultMap = mytemplate.queryForMap(query, obj);
			bean.setTransID(resultMap.get("MODE_TRANSPORT_ID")==null?"":resultMap.get("MODE_TRANSPORT_ID").toString());
			bean.setWsrccid(resultMap.get("CLAUSES_ID")==null?"":resultMap.get("CLAUSES_ID").toString());
			bean.setWsrccdesc(resultMap.get("CLAUSES_DESCRIPTION")==null?"":resultMap.get("CLAUSES_DESCRIPTION").toString());
			bean.setCovername(resultMap.get("COVER_ID")==null?"":resultMap.get("COVER_ID").toString());
			bean.setRsacode(resultMap.get("RSACODE")==null?"":resultMap.get("RSACODE").toString());
			bean.setDisplayorder(resultMap.get("DISPLAY_ORDER")==null?"":resultMap.get("DISPLAY_ORDER").toString());
			bean.setStatus(resultMap.get("STATUS")==null?"":resultMap.get("STATUS").toString());
			bean.setRemarks(resultMap.get("REMARKS")==null?"":resultMap.get("REMARKS").toString());
			bean.setWordingFileName(resultMap.get("PDF_LOCATION")==null?"":resultMap.get("PDF_LOCATION").toString());
			if(StringUtils.isNotBlank(bean.getWordingFileName())) {
				bean.setWordingYN("Y");
			}
		} catch (Exception e) {
			logger.info((new StringBuilder("EXCEPTION @ { ")).append(e)
					.append(" }").toString());
		}
	}

	public int checkExchangeEffDate(String effDate, String loginId) {
		int cnt = 0;
		try {
			query = getQuery("CHK_EXCHANGE_MASTER_EFF_DATE");
			Object[] obj = new Object[] { effDate, loginId };
			logger.info("Query==>" + query);
			logger.info("args==>" + StringUtils.join(obj, ", "));
			cnt = this.mytemplate.queryForObject(query,Integer.class, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	public int checkEffDate(String effDate) {
		int cnt = 0;
		try {
			query = getQuery("CHK_EFF_DATE");
			Object[] obj = new Object[] { effDate };
			logger.info("Query==>" + query);
			logger.info("args==>" + StringUtils.join(obj, ", "));
			cnt = this.mytemplate.queryForObject(query,Integer.class, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	public List<RatingEngineBean> getMaterialsList(String branchCode, String searchField, String searchString, String searchOper) {
		logger.info("Method to getMaterialList()");
		List<RatingEngineBean> getMaterialList = new ArrayList<RatingEngineBean>();
		String query = "", str = "like '%'||upper(?)||'%'";;
		try {
			Object[] obj = null;
			query = getQuery("GET_MATERIAL_MASTER_LIST");
			if (StringUtils.isNotBlank(searchField) && StringUtils.isNotBlank(searchString) && StringUtils.isNotBlank(searchOper)) {
				if ("nc".equalsIgnoreCase(searchOper))
					str = "not like '%'||upper(?)||'%'";
				else if ("cn".equalsIgnoreCase(searchOper))
					str = "like '%'||upper(?)||'%'";
				else if ("eq".equalsIgnoreCase(searchOper))
					str = " = upper(?)";

				if ("materialName".equalsIgnoreCase(searchField))
					query += " and upper(E.MATERIAL_DESC) " + str;

				obj = new Object[] { branchCode, searchString };
			} 
			else {
				obj = new Object[] { branchCode};
			}
			logger.info("Query==> " + query);
			logger.info("Args==> " + StringUtils.join(obj,","));
			List<Map<String,Object>> list = this.mytemplate.queryForList(query,obj);
			for(int i=0 ; i<list.size() ; i++) {
				Map<String,Object> tempMap = list.get(i);
				RatingEngineBean bean1 = new RatingEngineBean();
				bean1.setMaterialID(tempMap.get("MATERIAL_ID") == null ? "" : tempMap.get("MATERIAL_ID").toString());
				bean1.setCoverName(tempMap.get("COVER_NAME") == null ? "" : tempMap.get("COVER_NAME").toString());
				bean1.setCoverID(tempMap.get("COVER_ID") == null ? "" : tempMap.get("COVER_ID").toString());
				bean1.setMaterialName(tempMap.get("MATERIAL_DESC") == null ? "" : tempMap.get("MATERIAL_DESC").toString());
				bean1.setMaterialRate(tempMap.get("MATERIAL_RATE") == null ? "" : tempMap.get("MATERIAL_RATE").toString());
				bean1.setEff_date(tempMap.get("EFFECTIVE_DATE") == null ? "" : tempMap.get("EFFECTIVE_DATE").toString());
				bean1.setEdit(bean1.getMaterialID()+"&coverID="+bean1.getCoverID());
				bean1.setStatus(tempMap.get("STATUS") == null ? "": tempMap.get("STATUS").toString());
				getMaterialList.add(bean1);
			}
			logger.info("getMaterialList() - Exit");
		} catch (Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}
		return getMaterialList;
	}

	public List<Map<String,Object>> getCoverList1(String branchCode) {
		List<Map<String,Object>> list = null;
		try {
			query = "select COVER_ID,COVER_NAME FROM COVER_MASTER CM WHERE CM.BRANCH_CODE=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM COVER_MASTER CM1 WHERE CM1.COVER_ID=CM.COVER_ID AND CM1.BRANCH_CODE=CM.BRANCH_CODE)";
			list = this.mytemplate.queryForList(query,new Object[]{branchCode});
		}
		catch(Exception e) {
			logger.info("Exception @ getCoverList1 { " + e + " } ");
		}
		return list;
	}

	public int validateNewMaterial(RatingEngineBean bean, String branchCode) {
		int count=0; 
		try {
			query = "SELECT COUNT(*) FROM MATERIAL_TYPE_MASTER WHERE MATERIAL_ID=? AND COVER_ID=? AND BRANCH_CODE=?";
			count = this.mytemplate.queryForObject(query,Integer.class,new String[]{bean.getMaterialID(),bean.getCoverID(),branchCode});
		}
		catch(Exception exception) {
			logger.info("Exception @ validateNewMaterial { " + exception + " } ");
		}
		return count;
	}

	public List<Map<String, Object>> getCommodityList(Object[] val)
	{
		List<Map<String, Object>> commodityList=null;
		String query="";
		try
		{
			query=getQuery("GET_COMMODITY_DETAIL");
			query+="ORDER BY CM.COMMODITY_NAME";
			logger.info("Query==>"+query);

			commodityList=this.mytemplate.queryForList(query,val);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return commodityList;
	}

	public void getNewCommodity(Object[] val) 
	{
		try
		{
			logger.info("Method to getNewCommodity()");
			query=getQuery("INS_COMMODITY_MASTER");
			this.mytemplate.update(query,val);
			logger.info("Query===>" + query);
			logger.info("getNewCommodity() - Exit");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public void getUpdateCommodity(Object[] val,String string) 
	{
		try
		{
			logger.info("Method to getUpdateCommodity()");

			if(string.equals("insert"))
			{
				query=getQuery("UPDATE_COMMODITY_MASTER");
			}
			else if(string.equals("update"))
			{
				query=getQuery("UPDATE_COMMODITY_MASTER_UPDATE");
			}			

			this.mytemplate.update(query,val);
			logger.info("Query===>" + query);
			logger.info("getUpdateCommodity() - Exit");
		}
		catch(Exception e)
		{
			logger.info("getUpdateCommodity() - Exception"+e);
		}
	}


	public void getSelectedCommodity(final RatingEngineBean bean,Object[] val) {
		logger.info("Method to getSelectedCommodity()");

		try
		{
			query=getQuery("EDIT_COMMODITY_MASTER");

			List coverList=this.mytemplate.queryForList(query,val);
			if(coverList!=null && coverList.size()>0){
				Map rs=(Map)coverList.get(0);
				bean.setCommoditygroupId(rs.get("GROUP_ID")==null?"":rs.get("GROUP_ID").toString());
				bean.setCommodityID(rs.get("COMMODITY_ID")==null?"":rs.get("COMMODITY_ID").toString());					
				bean.setCommodityTypeID(rs.get("RAG")==null?"":rs.get("RAG").toString());			
				bean.setCommodityName(rs.get("COMMODITY_NAME")==null?"":rs.get("COMMODITY_NAME").toString());
				bean.setCommodityRates(rs.get("COMMODITY_RATE")==null?"":rs.get("COMMODITY_RATE").toString());
				bean.setCoverage_Referal(rs.get("COVERAGE_REFERAL")==null?"":rs.get("COVERAGE_REFERAL").toString());
				bean.setRemarks(rs.get("REMARKS")==null?"":rs.get("REMARKS").toString());
				bean.setStatus(rs.get("STATUS")==null?"":rs.get("STATUS").toString());
				bean.setCode(rs.get("RSACODE")==null?"":rs.get("RSACODE").toString());
				bean.setEff_date(rs.get("EFFECTIVE_DATE")==null?"":rs.get("EFFECTIVE_DATE").toString());
				bean.setPrevdate(rs.get("EFFECTIVE_DATE")==null?"":rs.get("EFFECTIVE_DATE").toString());
				bean.setAmendID(rs.get("AMEND_ID")==null?"":rs.get("AMEND_ID").toString());
				String ICC_A_Sea_Rate=rs.get("ICC_A_SEA")==null?"":rs.get("ICC_A_SEA").toString();
				String ICC_B_Sea_Rate=rs.get("ICC_B_SEA")==null?"":rs.get("ICC_B_SEA").toString();
				String ICC_C_Sea_Rate=rs.get("ICC_C_SEA")==null?"":rs.get("ICC_C_SEA").toString();
				String ICC_C_ND_Sea_Rate=rs.get("ICC_C_NONDELIVERY")==null?"":rs.get("ICC_C_NONDELIVERY").toString();

				String ICC_A_Frozen_Meat_Sea_Rate=rs.get("ICC_A_FROZEN_MEAT_SEA")==null?"":rs.get("ICC_A_FROZEN_MEAT_SEA").toString();
				String ICC_A_Frozen_Food_Sea_Rate=rs.get("ICC_A_FROZEN_FOOD_SEA")==null?"":rs.get("ICC_A_FROZEN_FOOD_SEA").toString();
				String ICC_C_Frozen_Meat_Sea_Rate=rs.get("ICC_C_FROZEN_MEAT_SEA")==null?"":rs.get("ICC_C_FROZEN_MEAT_SEA").toString();
				String ICC_C_Frozen_Food_Sea_Rate=rs.get("ICC_C_FROZEN_FOOD_SEA")==null?"":rs.get("ICC_C_FROZEN_FOOD_SEA").toString();

				String ICC_Air_Cargo_Air=rs.get("ICC_AIR_CARGO_AIR")==null?"":rs.get("ICC_AIR_CARGO_AIR").toString();
				String All_Risks_Land_Transit=rs.get("ALL_RISKS_LAND_TRANSIT_LAND")==null?"":rs.get("ALL_RISKS_LAND_TRANSIT_LAND").toString();
				String Land_Transit_Land=rs.get("LAND_TRANSIT_LAND")==null?"":rs.get("LAND_TRANSIT_LAND").toString();
				String All_Risks_Parcel_Post_Air=rs.get("ALL_RISKS_PARCEL_POST_AIR")==null?"":rs.get("ALL_RISKS_PARCEL_POST_AIR").toString();
				String All_Risks_Sea_and_Air=rs.get("ALL_RISKS_SEA_AND_AIR")==null?"":rs.get("ALL_RISKS_SEA_AND_AIR").toString();
				String Icc_Air_Cargo_All_Risks=rs.get("ICC_AIR_CARGO_ALL_RISKS")==null?"":rs.get("ICC_AIR_CARGO_ALL_RISKS").toString();

				String[] common=ICC_A_Sea_Rate.split("~");
				String[] common1=ICC_B_Sea_Rate.split("~");
				String[] common2=ICC_C_Sea_Rate.split("~");
				String[] common3=ICC_C_ND_Sea_Rate.split("~");
				String[] common4=ICC_A_Frozen_Meat_Sea_Rate.split("~");
				String[] common5=ICC_A_Frozen_Food_Sea_Rate.split("~");
				String[] common6=ICC_C_Frozen_Meat_Sea_Rate.split("~");
				String[] common7=ICC_C_Frozen_Food_Sea_Rate.split("~");
				String[] common8=ICC_Air_Cargo_Air.split("~");
				String[] common9=Icc_Air_Cargo_All_Risks.split("~");
				String[] common10=All_Risks_Land_Transit.split("~");
				String[] common11=Land_Transit_Land.split("~");
				String[] common12=All_Risks_Parcel_Post_Air.split("~");
				String[] common13=All_Risks_Sea_and_Air.split("~");

				//				String[] common8=ICC_C_ND_Sea_Rate.split("~");



				String[] test=new String[5];
				String[] test1=new String[5];
				String[] test2=new String[5];
				String[] test3=new String[5];
				String[] test4=new String[5];
				String[] test5=new String[5];
				String[] test6=new String[5];
				String[] test7=new String[5];
				String[] test8=new String[5];
				String[] test9=new String[5];
				String[] test10=new String[5];
				String[] test11=new String[5];
				String[] test12=new String[5];
				String[] test13=new String[5];
				for(int i=0;i<=4;i++)
				{
					test[i]=common.length>i?common[i]:"";
					test1[i]=common1.length>i?common1[i]:"";
					test2[i]=common2.length>i?common2[i]:"";
					test3[i]=common3.length>i?common3[i]:"";
					test4[i]=common4.length>i?common4[i]:"";
					test5[i]=common5.length>i?common5[i]:"";
					test6[i]=common6.length>i?common6[i]:"";
					test7[i]=common7.length>i?common7[i]:"";
					test8[i]=common8.length>i?common8[i]:"";
					test9[i]=common9.length>i?common9[i]:"";
					test10[i]=common10.length>i?common10[i]:"";
					test11[i]=common11.length>i?common11[i]:"";
					test12[i]=common12.length>i?common12[i]:"";
					test13[i]=common13.length>i?common13[i]:"";

				}

				bean.setTxtchkICC_A_SEAClause_1(test[0]);
				bean.setTxtchkICC_A_SEAExclusive_1(test[1]);
				bean.setTxtchkICC_A_SEAWarranty_1(test[2]);
				bean.setTxtchkICC_A_SEAWarCover_1(test[3]);
				bean.setRadDetective_1(test[4]);
				bean.setTxtchkICC_A_SEAClause_2(test1[0]);
				bean.setTxtchkICC_A_SEAExclusive_2(test1[1]);
				bean.setTxtchkICC_A_SEAWarranty_2(test1[2]);
				bean.setTxtchkICC_A_SEAWarCover_2(test1[3]);
				bean.setRadDetective_2(test1[4]);

				bean.setTxtchkICC_A_SEAClause_3(test2[0]);
				bean.setTxtchkICC_A_SEAExclusive_3(test2[1]);
				bean.setTxtchkICC_A_SEAWarranty_3(test2[2]);
				bean.setTxtchkICC_A_SEAWarCover_3(test2[3]);
				bean.setRadDetective_3(test2[4]);

				bean.setTxtchkICC_A_SEAClause_4(test3[0]);
				bean.setTxtchkICC_A_SEAExclusive_4(test3[1]);
				bean.setTxtchkICC_A_SEAWarranty_4(test3[2]);
				bean.setTxtchkICC_A_SEAWarCover_4(test3[3]);
				bean.setRadDetective_4(test3[4]);

				bean.setTxtchkICC_A_SEAClause_5(test4[0]);
				bean.setTxtchkICC_A_SEAExclusive_5(test4[1]);
				bean.setTxtchkICC_A_SEAWarranty_5(test4[2]);
				bean.setTxtchkICC_A_SEAWarCover_5(test4[3]);
				bean.setRadDetective_5(test4[4]);

				bean.setTxtchkICC_A_SEAClause_6(test5[0]);
				bean.setTxtchkICC_A_SEAExclusive_6(test5[1]);
				bean.setTxtchkICC_A_SEAWarranty_6(test5[2]);
				bean.setTxtchkICC_A_SEAWarCover_6(test5[3]);
				bean.setRadDetective_6(test5[4]);

				bean.setTxtchkICC_A_SEAClause_7(test6[0]);
				bean.setTxtchkICC_A_SEAExclusive_7(test6[1]);
				bean.setTxtchkICC_A_SEAWarranty_7(test6[2]);
				bean.setTxtchkICC_A_SEAWarCover_7(test6[3]);
				bean.setRadDetective_7(test6[4]);

				bean.setTxtchkICC_A_SEAClause_8(test7[0]);
				bean.setTxtchkICC_A_SEAExclusive_8(test7[1]);
				bean.setTxtchkICC_A_SEAWarranty_8(test7[2]);
				bean.setTxtchkICC_A_SEAWarCover_8(test7[3]);
				bean.setRadDetective_8(test7[4]);

				bean.setTxtchkICC_A_SEAClause_9(test8[0]);
				bean.setTxtchkICC_A_SEAExclusive_9(test8[1]);
				bean.setTxtchkICC_A_SEAWarranty_9(test8[2]);
				bean.setTxtchkICC_A_SEAWarCover_9(test8[3]);
				bean.setRadDetective_9(test8[4]);

				bean.setTxtchkICC_A_SEAClause_10(test9[0]);
				bean.setTxtchkICC_A_SEAExclusive_10(test9[1]);
				bean.setTxtchkICC_A_SEAWarranty_10(test9[2]);
				bean.setTxtchkICC_A_SEAWarCover_10(test9[3]);
				bean.setRadDetective_10(test9[4]);

				bean.setTxtchkICC_A_SEAClause_11(test10[0]);
				bean.setTxtchkICC_A_SEAExclusive_11(test10[1]);
				bean.setTxtchkICC_A_SEAWarranty_11(test10[2]);
				bean.setTxtchkICC_A_SEAWarCover_11(test10[3]);
				bean.setRadDetective_11(test10[4]);

				bean.setTxtchkICC_A_SEAClause_12(test11[0]);
				bean.setTxtchkICC_A_SEAExclusive_12(test11[1]);
				bean.setTxtchkICC_A_SEAWarranty_12(test11[2]);
				bean.setTxtchkICC_A_SEAWarCover_12(test11[3]);
				bean.setRadDetective_12(test11[4]);

				bean.setTxtchkICC_A_SEAClause_13(test12[0]);
				bean.setTxtchkICC_A_SEAExclusive_13(test12[1]);
				bean.setTxtchkICC_A_SEAWarranty_13(test12[2]);
				bean.setTxtchkICC_A_SEAWarCover_13(test12[3]);
				bean.setRadDetective_13(test12[4]);

				bean.setTxtchkICC_A_SEAClause_14(test13[0]);
				bean.setTxtchkICC_A_SEAExclusive_14(test13[1]);
				bean.setTxtchkICC_A_SEAWarranty_14(test13[2]);
				bean.setTxtchkICC_A_SEAWarCover_14(test13[3]);
				bean.setRadDetective_14(test13[4]);
			}
		}
		catch(Exception e) {
			logger.info("EXCEPTION @ { " + e + " }");
		}

	}
	public List<RatingEngineBean> getExecutiveMasterList(String branchCode, String searchField, String searchString,String searchOper) {

		Object obj[]=null;
		List<RatingEngineBean> executiveMasterList = new ArrayList<RatingEngineBean>();
		String query = "",str="like '%'||upper(?)||'%'";
		try
		{
			query = getQuery("GET_EXECUTIVE_MASTER");
			logger.info((new StringBuilder("Query==>")).append(query).toString());
			if(searchField!=null && searchString!=null && searchOper!=null){
				if("cn".equalsIgnoreCase(searchOper))
					str="like '%'||upper(?)||'%'";
				else if("eq".equalsIgnoreCase(searchOper))
					str=" = upper(?)";
				if("executiveName".equalsIgnoreCase(searchField))
					query+=" AND upper(AC_EXECUTIVE_NAME) "+str;
				else if("otherpartyCode".equalsIgnoreCase(searchField))
					query+=" AND upper(OA_CODE) "+str;
				/*else if("exmProduct".equalsIgnoreCase(searchField))
					query+=" AND upper(Product_Name) "+str;*/
				else if("exmRegion".equalsIgnoreCase(searchField))
					query+=" AND upper(REGION) "+str;
				obj=new Object[] {searchString};

			}
			query+=" order by AC_EXECUTIVE_NAME";
			logger.info("Query==> " + query);
			List<Map<String,Object>> list = this.mytemplate.queryForList(query,obj);
			for(int i=0 ; i<list.size() ; i++) {
				Map<String,Object> tempMap = list.get(i);
				RatingEngineBean tempBean = new RatingEngineBean();
				tempBean.setExmexecutiveId(tempMap.get("AC_EXECUTIVE_ID")==null?"":tempMap.get("AC_EXECUTIVE_ID").toString());
				tempBean.setExecutiveName(tempMap.get("AC_EXECUTIVE_NAME")==null?"":tempMap.get("AC_EXECUTIVE_NAME").toString());
				tempBean.setOtherpartyCode(tempMap.get("OA_CODE")==null?"":tempMap.get("OA_CODE").toString());
				tempBean.setExmProduct(tempMap.get("Product_Name")==null?"":tempMap.get("Product_Name").toString());
				tempBean.setExmEffectiveDate(tempMap.get("EFFECTIVE_DATE")==null?"":tempMap.get("EFFECTIVE_DATE").toString());
				tempBean.setExmStatus(tempMap.get("STATUS")==null?"":tempMap.get("STATUS").toString());
				tempBean.setEdit(tempMap.get("AC_EXECUTIVE_ID")==null?"":tempMap.get("AC_EXECUTIVE_ID").toString());
				executiveMasterList.add(tempBean);
			}
		}
		catch(Exception e)
		{
			logger.info((new StringBuilder("EXCEPTION @ { ")).append(e).append(" }").toString());
		}
		return executiveMasterList;
	}
	public void getExecutivemaster(final RatingEngineBean bean, String branchCode) {
		logger.info("Method to getExecutivemaster()");
		try
		{
			query=getQuery("EDIT_EXECUTIVE_MASTER");
			Object[] obj=new Object[1];
			obj[0]=bean.getExmexecutiveId();
			Map<String,Object> map = this.mytemplate.queryForMap(query,obj);
			bean.setExecutiveName(map.get("AC_EXECUTIVE_NAME")==null?"":map.get("AC_EXECUTIVE_NAME").toString());
			bean.setOtherpartyCode(map.get("OA_CODE")==null?"":map.get("OA_CODE").toString());
			bean.setExmProduct(map.get("PRODUCT_ID")==null?"":map.get("PRODUCT_ID").toString());
			bean.setExmEffectiveDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
			bean.setExmStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			bean.setExmRemarks(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
			bean.setExmCommission(map.get("COMMISSION")==null?"":map.get("COMMISSION").toString());
			bean.setExmOpenCoverCommission(map.get("OPENCOVER_COMMISSION")==null?"":map.get("OPENCOVER_COMMISSION").toString());
		}
		catch(Exception e)
		{
			logger.info("Exception @ { " + e + " } ");
		}

	}
	public int validateOtherPartyCode(String otherpartyCode, String mode, String executiveId) {
		int count=0;
		try {
			logger.info("COUNT_OTHERPARTYMASTER");
			query=getQuery("COUNT_OTHERPARTYMASTER");
			if("edit".equalsIgnoreCase(executiveId)) {
				query += " AND AC_EXECUTIVE_ID!='"+executiveId+"'";
			}
			logger.info((new StringBuilder("Query==>")).append(query).toString());
			Object obj[]={otherpartyCode};
			count=this.mytemplate.queryForObject(query,Integer.class, obj);
		}
		catch(Exception e) {
			logger.info((new StringBuilder("EXCEPTION @ { ")).append(e).append(" }").toString());
		}
		return count;
	}
	public void updateintoExecutiveMaster(RatingEngineBean bean) {
		Object args[]=null;
		try {
			if(bean.getMode().equalsIgnoreCase("edit")) {
				logger.info("UPDATE_INTO_EXECUTIVE_MASTER");
				query=getQuery("UPDATE_INTO_EXECUTIVE_MASTER");
				args=new Object[8];
				args[0]=bean.getExecutiveName();
				args[1]=bean.getOtherpartyCode();
				args[2]=bean.getExmEffectiveDate();
				args[3]=bean.getExmStatus();
				args[4]=bean.getExmRemarks();
				args[5]=bean.getExmCommission();
				args[6]=bean.getExmOpenCoverCommission();
				args[7]=bean.getExmexecutiveId();
			}
			else {
				args=new Object[7];
				args[0]=bean.getExecutiveName();
				args[1]=bean.getOtherpartyCode();
				args[2]=bean.getExmEffectiveDate();
				args[3]=bean.getExmStatus();
				args[4]=bean.getExmRemarks();
				args[5]=bean.getExmCommission();
				args[6]=bean.getExmOpenCoverCommission();
				logger.info("INSERT_INTO_EXECUTIVE_MASTER");
    			query=getQuery("INSERT_INTO_EXECUTIVE_MASTER");
			}
			logger.info("Inserted " + this.mytemplate.update(query, args));
		}
		catch(Exception e) {
			logger.info((new StringBuilder("EXCEPTION @ { ")).append(e).append(" }").toString());
		}
	}
}

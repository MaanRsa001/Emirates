/**
 * @Program Author name 	---karthikkeyan
 * @Creation Date & Time 	---28-04-2007 4 : 11 PM
 * @Company Name			---MaanSarovar Softwate P Limited
 * @Module					---Premium Inputs Bean
 */

package com.maan.premium.DAO;
import java.util.*;
import javax.servlet.http.HttpSession;
import proj.sql.QueryBuilder;
import com.maan.common.error.ErrorInfo;
import com.maan.common.util.OracleDateConversion;
import com.maan.common.util.StringUtil;
import com.maan.services.util.runner;

public class TransitTransactions extends ErrorInfo 
{
	
	private String sqlQuery_ = "";
	private String country = "";
	private String city = "";
	HttpSession session;
	private StringBuffer cityName = new StringBuffer();
	private StringBuffer categoryMasterName = new StringBuffer();
	private StringBuffer cityId = new StringBuffer();
	private StringBuffer countryId = new StringBuffer();
	private StringBuffer categoryMainMasterId = new StringBuffer();

	public String[][] getsCoutrys() 
	{
		String[][] getsCountry = new String[0][0];
		String sql = "";
		try 
		{
			sql ="select country_id,country_name from country_master order by country_name";
			getsCountry = runner.multipleSelection(sql);
		}
		catch (Exception e) {
               e.printStackTrace();
		}
		return getsCountry;
	}

	public String[][] getsCitys() 
	{
		String[][] getsCountry = new String[0][0];
		String sql = "";
		try 
		{
			sql = "select country_id,city_id,city_name from city_master order by city_name";
			getsCountry = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("The Get City Value Is --------------->>>>" + e);
			e.printStackTrace();
		}
		return getsCountry;
	}

	public String[][] getCityList() 
	{

		sqlQuery_ = "select distinct(cm.country_id),(dgd.data_name||'#'||cm.country_id||'$'||ct.city_name||'*'||ct.city_id||'~'||ct.city_name)as countrycity_NameId,upper(dgd.data_from) from data_group_details dgd,country_master cm,city_master ct where dgd.rating_factor_id='7' and lower(dgd.data_name)=lower(cm.country_name) and ct.country_id=cm.country_id and lower(dgd.data_from)=lower(ct.city_name) and dgd.status='Y' order by cm.country_id";

		StringBuffer busi_sub = new StringBuffer();
		String businessSubList[][] = new String[0][0];
		try 
		{

			businessSubList = runner.multipleSelection(sqlQuery_);
			for (int i = 0; i < businessSubList.length; i++) 
			{
				businessSubList[i][2] = StringUtil.upperFirstChar(businessSubList[i][2].length() > 50 ? businessSubList[i][2].substring(0, 50): businessSubList[i][2]);
				countryId.append("'" + businessSubList[i][0] + "',");
				cityId.append("'" + businessSubList[i][1] + "',");
				cityName.append("'" + businessSubList[i][2] + "',");// dou
			}
			countryId.deleteCharAt(countryId.length() - 1);
			cityId.deleteCharAt(cityId.length() - 1);
			cityName.deleteCharAt(cityName.length() - 1);
		}
		catch (Exception e) {
			System.out.println("545 Exception getCityList : " + e);
			e.printStackTrace();
		}
		return businessSubList;
	}

	public String getCityStatus(String cityId) 
	{
		String cityIdStatus = "";
		String args[] = new String[1];
		String result = "";
		try
		{
			sqlQuery_ = "select city_name as cityName from city_master where city_id=?";
			args[0] = cityId;
			result = runner.singleSelection(sqlQuery_,args);
			if(result.length() > 0)
			{
				cityIdStatus = result;
			}		
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getCityStatus Infor :"+ e.toString());
			e.printStackTrace();
		}
		return cityIdStatus;
	}

	public StringBuffer getCountryId() {
		return countryId;
	}
	public void setCountryId(StringBuffer countryId) {
		this.countryId = countryId;
	}

	public void setCityId(StringBuffer cityId) {
		this.cityId = cityId;
	}
	public StringBuffer getCityId() {
		return cityId;
	}

	public void setCityName(StringBuffer cityName) {
		this.cityName = cityName;
	}
	public StringBuffer getCityName() {
		return cityName;
	}

	public String[][] getCountryList() 
	{
	
		sqlQuery_ = "select distinct(cm.country_id),upper(dgd.data_name) from data_group_details dgd,country_master cm,city_master ct where dgd.rating_factor_id='7' and lower(dgd.data_name)=lower(cm.country_name) and ct.country_id=cm.country_id and  dgd.status='Y' order by cm.country_id ";

		String businessList[][] = new String[0][0];
		StringBuffer busi = new StringBuffer();
		try 
		{
			businessList = runner.multipleSelection(sqlQuery_);
			for (int i = 0; i < businessList.length; i++) 
			{
				categoryMainMasterId.append("'" + businessList[i][0] + "',");
				categoryMasterName.append("'" + businessList[i][1] + "',");
			}
			categoryMainMasterId.deleteCharAt(categoryMainMasterId.length() - 1);
			categoryMasterName.deleteCharAt(categoryMasterName.length() - 1);
		}
		catch (Exception e) 
		{
			System.out.println("2746 Exception getCountryList : " + e);	e.printStackTrace();
		}
		return businessList;
	}

	public void print(String methodName, String information, String type)
	{
		System.out.println(methodName + "<--->" + type + "<---->" + information);
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public HashMap getTransitCountryCityInfo(String countryId,String cityName, String place, String countryStatus,HashMap editDetails)
	{
		HashMap transitCountryCityInfo = editDetails;
		print(cityName + "--CountryID--" + countryId, "---SIZE is"+ transitCountryCityInfo.size(), "STATUS---" + countryStatus);
		String countryCityInfo = "";
		String result[][] = new String[0][0];
		try 
		{
			String args[] = new String[1];
			args[0] = cityName.toLowerCase();
			String sql = "select ct.country_id,ct.city_id,ct.city_name,cm.country_name from city_master ct,country_master cm where lower(ct.city_name)=? and ct.country_id=cm.country_id";
			result = runner.multipleSelection(sql,args);
			if(result.length >0) 
			{
				print("TRANSIT CountryCityInfo", "TRANSIT OTHERS LOOP","getTransitCountryCityInfo");
			}
			else 
			{
				cityName = "Others";
			}
		}
		catch (Exception e)
		{
			System.out.println("The Exception TRANSIT in getTransitCountryCityInfo--"+ e.toString());
			e.printStackTrace();
		}		
		// Others Id

		try
		{
			String sql = "select ct.country_id,ct.city_id as cityId,ct.city_name,cm.country_name from city_master ct,country_master cm where lower(ct.city_name)=? and cm.country_id=? and ct.country_id=cm.country_id";

			print("TRANSIT TRANSIT CountryCityInfo", sqlQuery_, "QUERY");
			String args[] = new String[2];
			String result1[][] = new String[0][0];
			args[0] = cityName.toLowerCase();
			args[1] = countryId;
			result1 = runner.multipleSelection(sql,args);
			
			for(int i=0;i<result1.length;i++) 
			{
				if ("F".equalsIgnoreCase(countryStatus)) 
				{
					
					try
					{
						transitCountryCityInfo.remove("transitCityId");
						transitCountryCityInfo.remove("transitCountryId");
						transitCountryCityInfo.remove("transitFrom");
					}
					catch (Exception ex) 
					{
						System.out.println("Exception while Transit Removing"+ ex.toString());
						ex.printStackTrace();
					}
					
					transitCountryCityInfo.put("transitCityId", result1[i][1]);
					transitCountryCityInfo.put("transitCountryId", result1[i][0]);
					transitCountryCityInfo.put("transitFrom", place);
				}
				else if ("T".equalsIgnoreCase(countryStatus)) 
				{
					try
					{
						transitCountryCityInfo.remove("finalCityId");
						transitCountryCityInfo.remove("finalCountryId");
						transitCountryCityInfo.remove("finalDestination");
					}
					catch (Exception ex) 
					{
						System.out.println("Exception while DESTINATION Removing"+ ex.toString());
                        ex.printStackTrace();   
					}
					transitCountryCityInfo.put("finalCityId", result1[i][1]);
					transitCountryCityInfo.put("finalCountryId", result1[i][0]);
					transitCountryCityInfo.put("finalDestination", place);
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("The Exception occured in getTransitCountryCityInfo--"+ e.toString());e.printStackTrace();
		}
		return transitCountryCityInfo;
	}
}

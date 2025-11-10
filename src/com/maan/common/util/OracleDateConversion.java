package com.maan.common.util;
import java.util.StringTokenizer;

public class OracleDateConversion
{
	static String oa_code	=	"WA000001";
	static String campaign_code	=	"Z001";

	public static String ConvertDate(String date)
	{
		String sdate="";
		String day="";
		String month="";
		String year="";
		String hours="00";
		String min="00";
		String seconds="00";

		String[][] monthArr={{"1","Jan"},{"2","Feb"},{"3","Mar"},{"4","Apr"},{"5","May"},{"6","Jun"},{"7","Jul"},{"8","Aug"},{"9","Sep"},{"10","Oct"},{"11","Nov"},{"12","Dec"}};
		String[][] dateArr={{"1","01"},{"2","02"},{"3","03"},{"4","04"},{"5","05"},{"6","06"},{"7","07"},{"8","08"},{"9","09"},{"10","10"},{"11","11"},{"12","12"},{"13","13"},{"14","14"},{"15","15"},{"16","16"},{"17","17"},{"18","18"},{"19","19"},{"20","20"},{"21","21"},{"22","22"},{"23","23"},{"24","24"},{"25","25"},{"26","26"},{"27","27"},{"28","28"},{"29","29"},{"30","30"},{"31","31"}};

		//System.out.println("Date for Change :"+date);
		
		try
		{
			if(date.length()>0)
			{
				StringTokenizer st=new StringTokenizer(date,"-");
				if(st.hasMoreTokens())
				{
					day	=	st.nextToken();
					month	=	st.nextToken();
					year		=	st.nextToken();
				}

				//System.out.println("Month before Change :"+month);

				for(int k=0;k<monthArr.length;k++)
				{
					//System.out.println("Integer (month):"+Integer.parseInt(month));
					//System.out.println("Integer (monthArr[k][0]):"+Integer.parseInt(monthArr[k][0]));
				
					if(Integer.parseInt(month)==Integer.parseInt((monthArr[k][0])))
					{
						month	=	monthArr[k][1];
						//System.out.println("If month Equals :"+month);
						break;
					}
				}

				//System.out.println("Month after Change :"+month);

				for(int l=0;l<dateArr.length;l++)
				{
					//System.out.println("Integer (day):"+Integer.parseInt(day));
					//System.out.println("Integer (dateArr[l][0]):"+Integer.parseInt(dateArr[l][0]));
				
					if(Integer.parseInt(day)==Integer.parseInt((dateArr[l][0])))
					{
						day	=	dateArr[l][1];
						//System.out.println("if day equals :"+day);
						break;
					}
				}
			
				sdate	=	day+"/"+month+"/"+year;
				//System.out.println("Date Converted :"+sdate);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in e :"+e.getMessage());
		}
		return sdate;
	}

	public static String ConvertDateTime(String datetime)
	{
		String sdate="sda";
		String day="";
		String month="";
		String year="";
		String hours="00";
		String min="00";
		String seconds="00";

		String[][] monthArr={{"1","Jan"},{"2","Feb"},{"3","Mar"},{"4","Apr"},{"5","May"},{"6","Jun"},{"7","Jul"},{"8","Aug"},{"9","Sep"},{"10","Oct"},{"11","Nov"},{"12","Dec"}};
		String[][] dateArr={{"1","01"},{"2","02"},{"3","03"},{"4","04"},{"5","05"},{"6","06"},{"7","07"},{"8","08"},{"9","09"},{"10","10"},{"11","11"},{"12","12"},{"13","13"},{"14","14"},{"15","15"},{"16","16"},{"17","17"},{"18","18"},{"19","19"},{"20","20"},{"21","21"},{"22","22"},{"23","23"},{"24","24"},{"25","25"},{"26","26"},{"27","27"},{"28","28"},{"29","29"},{"30","30"},{"31","31"}};

		try
		{
			StringTokenizer st=new StringTokenizer(datetime,"-");
			if(st.hasMoreTokens())
			{
				year	=	st.nextToken();
				month	=	st.nextToken();
				day		=	st.nextToken();
			}

			for(int k=0;k<monthArr.length;k++)
			{
				
				if(month.equals(monthArr[k][0]))
				{
					month	=	monthArr[k][1];
				}
			}

			if(day.length()>2)
			{
				//System.out.println("Before split day :"+day);
				//System.out.println("Time :"+day.substring(1,3));
				
				StringTokenizer st1=new StringTokenizer(day.substring(3,day.length()),":");

				if(st1.hasMoreTokens())
				{
					hours	=	st1.nextToken();
					min		=	st1.nextToken();
					seconds	=	st1.nextToken();
				}

				//System.out.println("Hours :"+hours);
				//System.out.println("min :"+min);
				//System.out.println("seconds :"+seconds);
				day	=	day.substring(1,2);
			}

			for(int l=0;l<dateArr.length;l++)
			{
				if(Integer.parseInt(day)==Integer.parseInt((dateArr[l][0])))
				{
					day	=	dateArr[l][1];
				}
			}

			//System.out.println("After Split day :"+day);

			//sdate	=	day+"/"+month+"/"+year+" "+hours+":"+min+":"+seconds;
			
			sdate	=	day+"-"+month+"-"+year;

		}
		catch(Exception e)
		{
			System.out.println("Exception in e :"+e.getMessage());
		}
		return sdate;
	}
}

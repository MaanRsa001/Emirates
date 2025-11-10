package rsa.opencoverpdf;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;

public class StringFunction
{
	private static final Logger logger = LogUtil.getLogger(StringFunction.class);
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";

	public String initCap(final String str) throws BaseException
	{
		logger.info("initCap method(String)");
		logger.debug(ENTER);

		StringBuffer retStr = new StringBuffer(12000);
		StringTokenizer token = new StringTokenizer(str, " ");
		while (token.hasMoreTokens()) {
			String temp = token.nextToken();
			retStr.append(temp.substring(0, 1).toUpperCase(Locale.ENGLISH));
			retStr.append(temp.substring(1).toLowerCase(Locale.ENGLISH));
			retStr.append(' ');
		}

		logger.debug(EXIT);
		

		return retStr.toString();
	}

	public String[] split(final String str) throws BaseException
	{
		logger.info("split method(String)");
		logger.debug(ENTER);

		StringTokenizer stoken = new StringTokenizer(str, ",");
		String[] arr = new String[stoken.countTokens()];
		int iValue = 0;
		while (stoken.hasMoreTokens()) {
			arr[iValue++] = stoken.nextToken();
		}
		if(arr.length == 0){
			arr = new String[1];
			arr[0] = str;
		}

		logger.debug(EXIT);
		

		return arr;
	}

	public String[] split(final String str,final String delimiter) throws BaseException
	{
		logger.info("split method(String,string)");
		logger.debug(ENTER);

		StringTokenizer stVal = new StringTokenizer(str, delimiter);
		String[] arr = new String[stVal.countTokens()];
		int iValue = 0;
		while (stVal.hasMoreTokens()) {
			arr[iValue++] = stVal.nextToken();
		}
		if (arr.length == 0) {
			arr= new String[1];
			arr[0] = str;
		}

		logger.debug(EXIT);
		

		return arr;
	}

	public StringBuffer queryFormat(final String str[]) throws BaseException
	{
		logger.info("queryFormat method(String,string)");
		logger.debug(ENTER);

		StringBuffer format = new StringBuffer(10000);

		for (int i = 0; i < str.length; i++) {
			format.append("'");
			format.append(str[i]);
			format.append("',");
		}

		logger.debug(EXIT);
		

		return (format.length() > 0) ? new StringBuffer(format.substring(0,format.length() - 1)) : format;
	}

	public StringBuffer getCommaSeparated(final String str[]) throws BaseException
	{
		logger.info("getCommaSeparated method(String,string)");
		logger.debug(ENTER);

		StringBuffer format = new StringBuffer();

		for (int i = 0; i < str.length; i++) {
			if (!"".equals(str[i])){
				format.append(str[i]);
				format.append(',');
			}
		}

		logger.debug(EXIT);
		

		return (format.length() > 0) ? new StringBuffer(format.substring(0,	format.length() - 1)) : format;
	}

	public StringBuffer getSeparated(final String str[],final String delim) throws BaseException
	{
		logger.info("getSeparated method(String,string)");
		logger.debug(ENTER);

		StringBuffer format = new StringBuffer(10000);

		for (int i = 0; i < str.length; i++) {
			if (!"".equals(str[i])){
				format.append(str[i]);
				format.append(delim);
			}
		}

		logger.debug(EXIT);
		

		return (format.length() > 0) ? new StringBuffer(format.substring(0,format.length() - 1)) : format;
	}

	public String[] toArray(final String str[][],final int index) throws BaseException
	{
		logger.info("toArray method(String,string)");
		logger.debug(ENTER);

		String[] retStr = new String[str.length];

		for (int i = 0; i < str.length; i++) {
			retStr[i] = str[i][index];
		}

		logger.debug(EXIT);
		

		return retStr;
	}

	public Hashtable convertHash(final String[] arr) throws BaseException
	{
		logger.info("convertHash method(String,string)");
		logger.debug(ENTER);

		Hashtable hash = new Hashtable();
		for (int i = 0; i < arr.length; i++) {
			hash.put(arr[i], arr[i]);
		}

		logger.debug(EXIT);
		

		return hash;
	}

	public Hashtable convertHash(final String[][] arr)   throws BaseException
	{
		logger.info("convertHash method(String)");
		logger.debug(ENTER);

		Hashtable hash = new Hashtable();
		for (int i = 0; i < arr.length; i++) {
			hash.put(arr[i][0], arr[i][1]);
		}

		logger.debug(EXIT);
		

		return hash;
	}

	public Hashtable convertHashFull(final String[][] arr)  throws BaseException
	{
		logger.info("convertHashFull method(String)");
		logger.debug(ENTER);

		Hashtable hash = new Hashtable();
		for (int i = 0; i < arr.length; i++) {
			hash.put(arr[i][0], arr[i]);
		}

		logger.debug(EXIT);
		

		return hash;
	}

	public String toString(final Hashtable hash,final  String delimiter) throws BaseException
	{
		logger.info("toString method(String)");
		logger.debug(ENTER);

		StringBuffer buffer = new StringBuffer();
		Enumeration enumeration = hash.elements();
		while(enumeration.hasMoreElements())
		{
			buffer.append(enumeration.nextElement());
			buffer.append(delimiter);
		}

		int len = buffer.length();

		logger.debug(EXIT);
		

		return len > 0 ? buffer.substring(0, len-1) : "";
	}

	// Added By Karthy
	public String toStringAppend(final String[][] twoArray,final String delimiter,final String status) throws BaseException
	{
		logger.info("toStringAppend method(String)");
		logger.debug(ENTER);

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < twoArray.length; i++) {
			if ("NORMAL".equalsIgnoreCase(status)) {
				buffer.append(twoArray[i][0] + delimiter);

			} else {
				buffer.append(twoArray[i][1] + delimiter);
			}
		}
		int len = buffer.length();

		logger.debug(EXIT);
		

		return len > 0 ? buffer.substring(0, len - 1) : "";
	}

	public String toString(final Hashtable hash,final  String delimiter,final  String enclosed) throws BaseException
	{
		logger.info("toString method(String)");
		logger.debug(ENTER);

		StringBuffer buffer = new StringBuffer();
		Enumeration enumeration = hash.elements();
		while(enumeration.hasMoreElements()){
			buffer.append(enclosed);
			buffer.append(enumeration.nextElement());
			buffer.append(enclosed);
			buffer.append(delimiter);
		}

		int len = buffer.length();

		logger.debug(EXIT);
		

		return len > 0 ? buffer.substring(0, len-1) : "";
	}
	public String[][] toTwoStringArray(final String content,final String delimiter) throws BaseException
	{
		logger.info("toString method(String)");
		logger.debug(ENTER);

		StringTokenizer stoken = new StringTokenizer(content,delimiter);
		String[][] twoArray = new String[stoken.countTokens()][1];
		int tValue=0;
		while(stoken.hasMoreTokens()){
			String tokens = stoken.nextToken().trim();
			if(tokens!=null && tokens.length()>0 && !"null".equalsIgnoreCase(tokens)){
				twoArray[tValue++][0] = tokens;
			}
		}

		logger.debug(EXIT);
		

		return twoArray;
	}
}
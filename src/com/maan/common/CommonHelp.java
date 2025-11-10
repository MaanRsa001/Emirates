package com.maan.common;

import com.maan.common.exception.BaseException;

public class CommonHelp {
	public static String notNullCheck(final String pStr){
		return pStr == null ?"" :pStr;
	}
	
	public static String notNullCheckZero(final String pStr){
		return pStr == null ?"0" :pStr;
	}
	
	public static String notNullCheckArgs(final String pStr,final String args){
		return pStr == null ?args :pStr;
	}
	
	public static String removeLastChar(final String content, final char delimeter) throws BaseException{
		final StringBuffer contents = new StringBuffer();
		if (content.length() > 0){
			contents.append(content.substring(0, content.lastIndexOf(delimeter)));
		}
		return contents.toString();
	}
	
	public static StringBuffer removeLastCharSB(final StringBuffer content, final String delimeter) throws BaseException{
		final StringBuffer contents = new StringBuffer();
		if (content.length() > 0){
			contents.append(content.substring(0, content.lastIndexOf(delimeter)));
		}
		return contents;
	}
	
	public static String getStringFromArray(final String[][] arrayString) throws BaseException{
		final StringBuffer makeStr = new StringBuffer();
		for (int i = 0; i < arrayString.length; i++) {
			makeStr.append("'"+arrayString[i][0]);
			makeStr.append("',");
		}
		return makeStr.toString();
	}
	
	public static String getStringFromArray(final String[][] arrayString,final int index) throws BaseException{
		final StringBuffer makeStr = new StringBuffer();
		for (int i = 0; i < arrayString.length; i++) {
			makeStr.append("'"+arrayString[i][index]);
			makeStr.append("',");
		}
		return makeStr.toString();
	}
}

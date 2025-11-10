package com.maan.proj.util;

import java.io.*;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Common file for all projects.
  * <li> Purpose : To read the html files and also replace the "%%" contents
  * </ul>
  */

public class HtmlReader 
{
	/**
	  * To replace the old string to the new string in a given line
	  * @param line String which has a full line of text.
	  * @param oldStr String to be replace.
	  * @param newStr String which has orginal value.
	  * @return a age as int.
	  * @Exception Exception.
	  */

	private static String replace(String line, String oldstr, String newstr) throws Exception
	{
		int index = 0;
		
		// untill the occurance of the oldstring in the line, replace the old string with the new string.
		while((index = line.indexOf(oldstr,index)) >= 0)
		{
			line = line.substring(0,index)+ newstr + line.substring(index + oldstr.length());
			index += newstr.length();
		}

		// return the line with newly replaced string.
		return line+"\n";
	}

	
	/**
	  * To replace the occurance of first column value of the two dimension with the second colum value
	  * in the given file.
	  * @param filename name of the file to be read.
	  * @param array Two dimension array which has old string in first column and new string in second column.
	  * @return a age as int.
	  * @Exception Exception.
	  */

	public static StringBuffer replaceValue(String filename, String[][] array)  
	{
		StringBuffer str = new StringBuffer();
		
		// get the array length.
		int row = array.length;
		try
		{
			// get a BufferedReader to the given filename.
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line = null;
			
			// untill the end of file, read line by line.
			while((line = in.readLine()) != null)
			{
				// check the occurance of oldString in each line and replace with the new string by using replace() method
				for(int i=0; i < row;i++)
				{
					if(array[i][1] == null)
						array[i][1] = "";
					line = replace(line,array[i][0],array[i][1]);
				}
				str.append(line.trim() +"\n");
			}
		}
		catch(Exception e)
		{
			return new StringBuffer(""+e);
		}

		// return the new Html file with the replaced values.
		return str;
	}
}
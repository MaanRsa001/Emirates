package com.maan.proj.math;

import java.math.*;
import java.text.*;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Common file for all projects.
  * <li> Purpose : This class has functions which used to round of a float or double value.
  * </ul>
  */

public class Round
{
	/**
	  * Get the nearest integer for a given floating point value.
	  * returns 10, if f is 10.5 and below, else return 11
	  * @param f given float.
	  * @return int value
	  */

	public int roundOf(float f)  
	{
		 // truncate the floating point value and get integer.
		 int i = (int) f;
		 float rem = f - (float) i;
		 
		 // check for the decimal point greater than 0.50, if greater increment i. 
		 if( rem > 0.50)
			 i++;
		 
		 // return the i value which has the nearest integer for given float.
		 return i;
	}

	
	/**
	  * Get the nearest long value for a given double value.
	  * returns 10, if f is 10.5 and below, else return 11
	  * @param d given double.
	  * @return long value
	  */

	public long roundOf(double d)
	{
		 // truncate the double value and get long.
		 long i = (long) d;
		 double rem = d - (double) i;

		 // check for the decimal point greater than 0.50, if greater increment i. 
		 if( rem > 0.50)
			 i++;
		 
		 // return the i value which has the nearest integer for given float.
		 return i;
	}


	/**
	  * Get the two decimal place of float or double in String format.
	  * ex. if f is 2946.4469999 returns 2946.45.          
	  * @param f_str String form of double or float.
	  * @return String value
	  */

	public String twoDecimalPlace(String f_str) 
	{											
		int f_length = f_str.length();
		
		// get the index of "." in the given string.
		int index = f_str.indexOf(".");
		
		// if no "." available then put ".00" with the string.
		if (index == -1)
			return f_str+".00";
		
		int len = f_length - index;
		
		// if the string has ".0" only in the string put another zero at the end.
		if( len  == 2)
		  return f_str+"0";
		
		// return the two decimal placed string.
		return f_str;
	}
	

	/**
	  * Get the two decimal placed double value.
	  * @param d double value.
	  * @return String value.
	  */

	public String fixTwoDecimal(double d)
	{	
		// get the Instance of NumberFormat. 
		NumberFormat nf = NumberFormat.getInstance(); 
		
		// set the maximum integer part.
		nf.setMaximumIntegerDigits(15);

		// set the maximum fraction part.
		nf.setMaximumFractionDigits(2);
		nf.setGroupingUsed(false);
		
		// call the twoDecimalPlace method and fix the two decimal for the return string.
		return twoDecimalPlace(nf.format(d));
	}

	
	/**
	  * Get the two decimal placed float value.
	  * @param d float value.
	  * @return String value.
	  */

	public String fixTwoDecimal(float d) // fixes two decimal places for a double value
	{	
		// get the Instance of NumberFormat. 
		NumberFormat nf = NumberFormat.getInstance(); 
		
		// set the maximum integer part.
		nf.setMaximumIntegerDigits(15);

		// set the maximum fraction part.
		nf.setMaximumFractionDigits(2);
		nf.setGroupingUsed(false);
		
		// call the twoDecimalPlace method and fix the two decimal for the return string.
		return twoDecimalPlace(nf.format(d));
	}


	/**
	  * Get the double with the desire decimal place.
	  * @param d float value.
	  * @param p number of position need.
	  * @return double value.
	  */

	public double truncate(double d,int p)  
    {
        double point = Math.pow(10,p);
        return (double)Math.round(d * point) / point;
    }
}
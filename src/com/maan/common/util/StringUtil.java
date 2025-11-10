
package com.maan.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import com.maan.common.util.FastStringBuffer;


/*
 * StringUtil.java
 *
 * Copyright 2006 Maan Sarovar Software Pvt. Ltd.
 */

/**
 * This is class that consists of static methods, utilities for
 * manipulations of <code>java.lang.String</code>s.
 *
 */
public final class StringUtil {
    public static final String EMPTY_STRING = "";


    /**
     * Make sure a string is not null.  This is a convenience method, but it
     * is not necessarily recommended if you do  a lot of processing for theMessage
     * parameter.  It is recommended that you assertNotBlank, catch IllegalArgumentException
     * and then throw an appropriate exception with your message.
     *
     * @param theString  Any string, possibly null
     * @param theMessage The error message to use if the argument is not null.
     */
    public static final void assertNotBlank(String theString, String theMessage) {
        if (theString == null) {
            throw new IllegalArgumentException("Null argument not allowed: " +
                    theMessage);
        }
        if (theString.trim().equals(EMPTY_STRING)) {
            throw new IllegalArgumentException("Blank argument not allowed: " +
                    theMessage);
        }
    } /* assertNotBlank(String, String) */

    /**
     * Make sure a string is not null.
     *
     * @param theString Any string, possibly null
     * @return An empty string if the original was null, else the original
     */
    public static final String notNull(String theString) {
        if (theString == null) {
            return EMPTY_STRING;
        }

        return theString;
    } /* notNull(String) */


    /**
     * Throws an exception if theString can't be mapped to a boolean value.
     *
     * @param theString  the string to check
     * @param theMessage the message to have in the IllegalArgumentException
     *                   if the conditions aren't met.
     */
    public static final void assertBoolean(String theString, String theMessage) {
        assertNotBlank(theString, theMessage);

        if (!(theString.equalsIgnoreCase("yes") ||
                theString.equalsIgnoreCase("true") ||
                theString.equalsIgnoreCase("no") ||
                theString.equalsIgnoreCase("false") ||
                theString.equals("1") ||
                theString.equals("0") ||
                theString.equalsIgnoreCase("y") ||
                theString.equalsIgnoreCase("n"))) {
            throw new IllegalArgumentException(theMessage);
        }
    }

    /**
     * parse a boolean value
     *
     * @param theString the string to parse
     * @return a boolean value parse from input; if input cannot be parsed, return false
     */
    public static final boolean toBoolean(String theString) {
        boolean result = false;
        if (theString != null) {
            // Rearranged with commons conditions first
            result = theString.equalsIgnoreCase("true")
                    || theString.equals("1")
                    || theString.equalsIgnoreCase("yes")
                    || theString.equalsIgnoreCase("y")
                    || theString.equalsIgnoreCase("on")
                    || theString.equalsIgnoreCase("set");
        }

        return result;
    }

    /**
     * Same thing but using a serializable string as the parameter instead
     *
     * @param theString The string to check against null
     * @return theString or an empty string if it is null
     */
    public static String notNull(SerializableString theString) {
        if (theString == null) {
            return EMPTY_STRING;
        }

        return theString.toString();
    }

    /**
     * This method is useful for creating lists that use letters instead of
     * numbers, such as a, b, c, d...instead of 1, 2, 3, 4.
     * Valid numbers are from 1 to 26, corresponding to the 26 letters of the alphabet.
     * By default, the letter is returned as a lowercase, but if the boolean
     * upperCaseFlag is true, the letter will be returned as an uppercase.
     * Creation date: (23/05/06 16:23:30 PM)
     *
     * @param number        The number to convert
     * @param upperCaseFlag True if you want the final data to be uppercase
     * @return java.lang.String
     */
    public static String numberToLetter(int number, boolean upperCaseFlag)
            throws Exception {

        //add nine to bring the numbers into the right range (in java, a= 10, z = 35)
        if (number < 1 || number > 26) {
            throw new Exception("The number is out of the proper range (1 to " +
                    "26) to be converted to a letter.");
        }

        int modnumber = number + 9;
        char thechar = Character.forDigit(modnumber, 36);

        if (upperCaseFlag) {
            thechar = Character.toUpperCase(thechar);
        }

        return "" + thechar;
    } /* numberToLetter(int, boolean) */


    /**
     * replace substrings within string.
     *
     * @param s    The string to work with
     * @param sub  The string to substitude the occurances of
     * @param with The string to replace with
     * @return A processed java.util.String
     */
//Included By Brahmaiah ****************************
public static  boolean checkSpecial(String str)
		{
			boolean splChar =false;
			if(!(str.trim().length()==0 ))
			{
						String value=str.trim();
						char []valuechar1=value.toCharArray();
						for(int k=0;k<value.length();k++)
						{						
							if(valuechar1[k] !='_'  && valuechar1[k] !='-' && valuechar1[k]  !=' ')
							{
								int number=Character.getNumericValue(valuechar1[k]);
								int val=valuechar1[0];
								//char under='_';
								if(number<0)
								{
									splChar=true;
									break;
								}	//end of numer if										
							}
						}	//
			}else{ splChar =false;}
			return splChar;
	}
//Special Charectares checking Ends Here 

    public static String replace(String s, String sub, String with) {
        if (s == null) return null;
        int c = 0;
        int i = s.indexOf(sub, c);

        if (i == -1) {
            return s;
        }

        FastStringBuffer buf = new FastStringBuffer(s.length() + with.length());

        do {
            buf.append(s.substring(c, i));
            buf.append(with);
            c = i + sub.length();
        } while ((i = s.indexOf(sub, c)) != -1);

        if (c < s.length()) {
            buf.append(s.substring(c, s.length()));
        }

        return buf.toString();
    } /* replace(String, String, String) */

    /**
     * Formats the string to an XML/XHTML escaped character.  Useful for &'s, etc
     *
     * @param s the String to format
     * @return The escaped formatted String.
     * @see org.apache.xml.serialize.BaseMarkupSerializer for example of original
     *      code.
     */
    public static String xmlEscape(String s) {
        int length = s.length();
        FastStringBuffer fsb = new FastStringBuffer(length);

        for (int i = 0; i < length; i++) {
            fsb = printEscaped(s.charAt(i), fsb);
        }

        return fsb.toString();
    }

    /**
     * Formats a particular character to something workable in xml  Helper to xmlEscape()
     *
     * @param ch  the character to print.
     * @param fsb The FastStringBuffer to add this to.
     * @return a FastStringBuffer that is modified
     */
    protected static FastStringBuffer printEscaped(char ch,FastStringBuffer fsb) {
        String charRef;

        // If there is a suitable entity reference for this
        // character, print it. The list of available entity
        // references is almost but not identical between
        // XML and HTML.
        charRef = getEntityRef(ch);

        if (charRef != null) {
            fsb.append('&');
            fsb.append(charRef);
            fsb.append(';');

            //ch<0xFF == isPrintable()
        } else if ((ch >= ' ' && ch < 0xFF && ch != 0xF7) || ch == '\n' ||
                ch == '\r' || ch == '\t') {

            // If the character is not printable, print as character reference.
            // Non printables are below ASCII space but not tab or line
            // terminator, ASCII delete, or above a certain Unicode threshold.
            if (ch < 0x10000) {
                fsb.append(ch);
            } else {
                fsb.append((char) ((((int) ch - 0x10000) >> 10) + 0xd800));
                fsb.append((char) ((((int) ch - 0x10000) & 0x3ff) + 0xdc00));
            }
        } else {
            fsb.append("&#x");
            fsb.append(Integer.toHexString(ch));
            fsb.append(';');
        }

        return fsb;
    }

    /**
     * Helper to xmlEscape()
     *
     * @param ch the character to escape
     * @return A modified string representing the tanlation of the character
     *         or null if there is no translation for it.
     */
    protected static String getEntityRef(int ch) {

        // Encode special XML characters into the equivalent character references.
        // These five are defined by default for all XML documents.
        switch (ch) {
            case '<':
                return "lt";

            case '>':
                return "gt";

            case '"':
                return "quot";

            case '\'':
                return "apos";

            case '&':
                return "amp";
        }

        return null;
    }

    /**
     * HTML code for ellipses (3 dots, like '...' as one character)
     * used for appending to a truncate() line if necessary
     */
    public static String ELLIPSES = "&#133";

    /**
     * truncate a string at the given length if necessary,
     * adding an ellipses at the end if truncation occurred;
     * uses ELLIPSES static String from this class
     *
     * @param str The string to process
     * @param len The maximum length to process the string to.
     * @return the appropriately trimmed string.
     */
    public static String truncate(String str, int len) {
        String result = str;
        if (str.length() > len) {
            result = str.substring(0, len) + ELLIPSES;
        }
        return result;
    }


    /**
     * Replace all comma by  dot
     * <p/>
     *
     * @param str String to change
     * @return String  The result of substitution
     */
    public static String convertCommaToDot(String str) {
        if (str == null) return null;

        return str.replace(',', '.');
    }

    /**
     * Compare 2 decimals string
     * <p/>
     *
     * @param decim1 First string to compare
     * @param decim2 Second string to  compare
     * @return int return 1 if decim1 > decim2<BR> retourne 0 if decim1 == decim2<BR> return -1 if decim1 < decim2
     */
    public static int compareDecimals(String decim1, String decim2) {
        BigDecimal dec1 = new BigDecimal(decim1);
        BigDecimal dec2 = new BigDecimal(decim2);
        return dec1.compareTo(dec2);
    }

    /**
     * Compare 2 integers string.
     * <p/>
     *
     * @param int1 first string to compare
     * @param int2 second string to compare
     * @return int return 1 if decim1 > decim2<BR> return 0 if decim1 == decim2<BR> return -1 if decim1 < decim2
     */
    public static int compareIntegers(String int1, String int2) {
        BigInteger dec1 = new BigInteger(int1);
        BigInteger dec2 = new BigInteger(int2);
        return dec1.compareTo(dec2);
    }

    /**
     * Check if string is alphanumeric or not.
     * <p/>
     *
     * @param s String to check.
     * @return boolean true if alphanumeric, false if not.
     */
    public static boolean isAlphaNumeric(String s) {
        return isAlphaNumeric(s, "");
    }

    /**
     * Check if string is alphanumeric with addons chararcters or not.
     * <p/>
     *
     * @param str        string to check
     * @param otherChars extra characters to check with
     * @return boolean true if parameter string contains only alpha numerics,<BR> plus addons characters and false if not.
     */
    public static boolean isAlphaNumeric(String str, String otherChars) {
       /* String alphaNum =
                "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" + otherChars;
        for (int i = 0; i < str.length(); i++) {
            if (alphaNum.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        return true;*/
    	boolean result = true;
    	str = str.trim();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!Character.isDigit(c) && !Character.isLetter(c)){
				result = false;
				break;
			}
		}
		return result;
    }

	public static boolean isAlphabets(String str) {
        String alphaNum ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        for (int i = 0; i < str.length(); i++) {
            if (alphaNum.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }
	
	public static boolean isNumberValue(String str) {
        String alphaNum ="1234567890";
        for (int i = 0; i < str.length(); i++) {
            if (alphaNum.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }
	
	public static boolean chkOnlySpecial(String str) {
       // String spl =".+-*/~!@#$%^&*()_+=|;:?><,";
		 String spl ="~!@$%^<>*_+={};:?[]|";
        for (int i = 0; i < str.length(); i++) {
            if (spl.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

	public static boolean addressValidation(String value) // mentioned chars are present it will return false otherwise true.
	{
		boolean flag=true;
		String invalidChar=null;
		try
		{
			value=value.trim();
			if(value.length()>0 && value!=null )
			{
				invalidChar="~!@$%^<>&*()_+={};?[]|";
				for(int i=0;i<value.length();i++)
				{
					if(invalidChar.indexOf(value.charAt(i))!= -1)
					{
						flag = false;
						break;
					}
				}
			}
			else
				return flag;
		}
		catch(Exception e)
		{
			System.out.println("addressValidation in customerInfo.java1 "+value);
			return flag;
		}
		System.out.println("addressValidation  "+ flag);
		return flag;
	}

    /**
     * Check if an input string is an empty string, just contains
     * whitespace characters, or is <code>null</code>. If it is then
     * return <code>true</code>
     *
     * @param str the input string
     * @return boolean true if the string is blank or null
     */
    public static boolean isBlankOrNull(String str) {
        if (str == null)
            return true;
        if (str.trim().length() < 1)
            return true;
        else
            return false;
    }

    /**
     * Check if decimal number
     * <p/>
     *
     * @param s string to check.
     * @return boolean true if the value is decimal number false if not
     */
    public static boolean isDecimal(String s) {
        try {
            new BigDecimal(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Check if string pass is a vlaid email address
     * <p/>
     *
     * @param email string to check
     * @return int 0 if valid address, 1 more than 2 tokens (".", "@")<BR> and 2 if the second token is not "." .
     */
    public static int isEmail(String email) {
        StringTokenizer st = new StringTokenizer(email, "@");

        if (st.countTokens() != 2) {
            return 1;
        }

        st.nextToken();
        if (st.nextToken().indexOf(".") == -1) {
            return 2;
        }

        return 0;
    }

    /**
     * Check if integer string
     * <p/>
     *
     * @param str string to check
     * @return boolean true if all characters is digit
     */
    public static boolean isInteger(String str) {
        try {
            new BigInteger(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Check if number
     *
     * @param str to check
     * @return boolean
     */
    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Check French company siren number.
     * <p/>
     *
     * @param siren the company number
     * @return boolean true if ok.
     */
    public static boolean isSiren(String siren) {
        StringBuffer temp = new StringBuffer("");
        int value = 0;

        // Check if numeric digit.
        try {
            Double.parseDouble(siren);
        } catch (NumberFormatException nbe) {
            return false;
        }

        // string length must be 9
        if (siren.length() != 9) {
            return false;
        }

        //
        for (int i = 0; i < 9; i++) {
            int n =
                    Integer.parseInt(siren.substring(i, i + 1)) * (((i % 2) != 0) ? 2 : 1);
            temp.append(n);
        }

        // sum of all digits
        for (int i = 0; i < (temp.length()); i++) {
            value += Integer.parseInt(temp.substring(i, i + 1));
        }

        //value must divide by 10
        if ((value % 10) != 0) {
            return false;
        }

        return true;
    }

    /**
     * Check French company siret number
     * <p/>
     *
     * @param siret The siret number
     * @return boolean true if equivalent to siret number false if not.
     */
    public static boolean isSiret(String siret) {
        // Check the length
        if (siret.length() != 14) {
            return false;
        }

        //
        try {
            Double.parseDouble(siret);
            return isSiren(siret.substring(0, 9));
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Return empty string if parameter is null
     * <p/>
     *
     * @param s the parameter to check
     * @return String    return the parameter if empty or if the string == null
     */
    public static String nullToEmpty(String s) {
        if (s == null) {
            return "";
        } else {
            return s;
        }
    }

    /**
     * Reformat string by converting "," by "."
     * <p/>
     *
     * @param str string to reformat
     * @return String  return reformatted string.
     */
    public static String reformatDecimalString(String str) {
        if (str == null) return null;

        // replace all ',' by '.'
        str = str.replace(',', '.');
        try {
            Double.parseDouble(str);
            return str;
        } catch (NumberFormatException nbe) {
            return "";
        }
    }

    /**
     * Replace all dots byt comma
     * <p/>
     *
     * @param str string to change
     * @return String     reformatted
     */
    public static String convertDotToComma(String str) {
        return str.replace('.', ',');
    }

    /**
     * Replace all occurences of key by value in text.
     * <p/>
     *
     * @param text  string in
     * @param key   occurence to replace
     * @param value string to use
     * @return String with the replace value
     */
    public static String replaceAll(String text, String key, String value) {
        if (text == null) return null;

        String buffer = text;
        if (buffer != null && key != null && value != null) {
            int length = key.length();
            for (int start = buffer.indexOf(key);
                 start != -1;
                 start = buffer.indexOf(key, start + value.length())) {
                buffer = buffer.substring(0, start) + value + buffer.substring(start + length);
            }
        }
        return buffer;
    }

    /**
     * Substituate once str1 by str2 in text
     * Commentaire Anglais
     * <p/>
     *
     * @param text search and replace in
     * @param str1 to search for
     * @param str2 to replace with
     * @return String replaced
     */
    static public String replaceStringOnce(String text, String str1, String str2) {
        return replaceString(text, str1, str2, 1);
    }

    /**
     * Substituate all occurence of str1 by str2 in text
     * <p/>
     *
     * @param text search and replace in
     * @param str1 search for
     * @param str2 replace with
     * @return String with all values replaced
     */
    static public String replaceString(String text, String str1, String str2) {
        return replaceString(text, str1, str2, -1);
    }

    /**
     * Replace  n occurences of str1 in text by str2.
     * if n = -1 all occurrences are replaced
     * <p/>
     *
     * @param text search and replace in
     * @param str1 search for
     * @param str2 replace with
     * @param max  int values of occrrences to replace
     * @return String replaced
     */
    static public String replaceString(String text, String str1, String str2, int max) {
        if (text == null) {
            return null;
        }

        StringBuffer buffer = new StringBuffer(text.length());
        int start = 0;
        int end = 0;
        while ((end = text.indexOf(str1, start)) != -1) {
            buffer.append(text.substring(start, end)).append(str2);
            start = end + str1.length();
            if (--max == 0) {
                break;
            }
        }
        buffer.append(text.substring(start));
        return buffer.toString();
    }

    /**
     * Convert string to list
     * <p/>
     *
     * @param s String comma delimited string to format
     * @return List
     */
    public static List string2List(String s) {
        return string2List(s, ",");
    }

    /**
     * Convert string to list using sep separator to divide
     * <p/>
     *
     * @param s   String comma delimited string to format
     * @param sep a string containing the seprator characters
     * @return List
     */
    public static List string2List(String s, String sep) {
        return string2List(s, sep, s != null ? s.length() : Integer.MAX_VALUE);
    }

    /**
     * Convert string to list using sep separator to divide
     * <p/>
     *
     * @param s       String comma delimited string to format
     * @param sep     a string containing the seprator characters
     * @param maxSize the maximum size of the list
     * @return List
     */
    public static List string2List(String s, String sep, int maxSize) {
        List l = null;
        if (s != null) {
            l = new Vector();
            for (int i = 0; i < maxSize;) {
                int index = s.indexOf(sep, i);
                String token;
                if (index != -1) {
                    token = s.substring(i, index);
                } else {
                    token = s.substring(i);
                }
                if (token.length() > 0 && !token.equals(sep)) {
                    l.add(token.trim());
                }
                i += token.length() + sep.length();
            }
        }
        return l;
    }

    /**
     * convert the first character of string in lower case
     * <p/>
     *
     * @param str String to un-upper case the first character
     * @return String
     */
    static public String unUpperFirstChar(String str) {
        if (str == null) return null;

        FastStringBuffer fsb = null;
        ;
        try {
            fsb = FastStringBuffer.getInstance();
            fsb.append(Character.toLowerCase(str.charAt(0)));
            fsb.append(str.substring(1));
            return fsb.toString();
        } finally {
            if (fsb != null) {
                fsb.release();
            }
        }
    }

    /**
     * convert the first character of the string upper case
     * <p/>
     *
     * @param str String to make the first character upper.
     * @return String
     */
    static public String upperFirstChar(String str) {
        if (str == null) return null;

        FastStringBuffer fsb = FastStringBuffer.getInstance();
        try {
            fsb.append(Character.toTitleCase(str.charAt(0)));
            fsb.append(str.substring(1));
            return fsb.toString();
        } finally {
            fsb.release();
        }

        // return "" + Character.toTitleCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * Repeat str n time to format another string.
     * <p/>
     *
     * @param str String  to repeat
     * @param n   int n repeat
     * @return String
     */
    static public String repeatString(String str, int n) {
        if (str == null) return null;

        FastStringBuffer buffer = FastStringBuffer.getInstance();
        try {
            //FastStringBuffer is preallocated for 1K, so we may not
            //need to do any memory allocation here.
            int val = n * str.length();
            if (val > buffer.capacity()) {
                buffer.ensureCapacity(val);
            }
            //            StringBuffer buffer = new StringBuffer(n * str.length());
            for (int i = 0; i < n; i++) {
                buffer.append(str);
            }
            return buffer.toString();
        } finally {
            buffer.release();
        }
    }

    /**
     * enclosed the string with padding character.
     * Space is padding character
     * <p/>
     *
     * @param str String str  string to center padding
     * @param n   int n       size of the new string
     * @return String Result
     */
    static public String centerPad(String str, int n) {
        return centerPad(str, n, " ");
    }

    /**
     * Enclosed the string with padding character
     * <p/>
     *
     * @param str   String str  string to pad with
     * @param n     int    n size of the final string
     * @param delim String delim  padding character
     * @return String  result of the center padding
     */
    static public String centerPad(String str, int n, String delim) {
        if (str == null) return null;

        int sz = str.length();
        int p = n - sz;
        if (p < 1) {
            return str;
        }
        str = leftPad(str, sz + p / 2, delim);
        str = rightPad(str, n, delim);
        return str;
    }

    /**
     * Right padding with delimiter
     * <p/>
     *
     * @param str   String
     * @param n     int    size of the final string
     * @param delim padding character
     * @return String padding string
     */
    static public String rightPad(String str, int n, String delim) {
        if (str == null) return null;

        int sz = str.length();
        n = (n - sz) / delim.length();
        if (n > 0) {
            str += repeatString(delim, n);
        }
        return str;
    }

    /**
     * Right padding
     * delimiter is space
     * <p/>
     *
     * @param str String
     * @param n   int    size of the new string
     * @return String
     */
    static public String rightPad(String str, int n) {
        return rightPad(str, n, " ");
    }

    /**
     * Left padding
     * padding character is space
     * <p/>
     *
     * @param str String
     * @param n   int    size of the new string
     * @return String
     */
    static public String leftPad(String str, int n) {
        return leftPad(str, n, " ");
    }

    /**
     * Left padding
     * <p/>
     *
     * @param str   String
     * @param n     int    size of the new string
     * @param delim padding character
     * @return String result
     */
    static public String leftPad(String str, int n, String delim) {
        if (str == null) return null;
        int sz = str.length();
        n = (n - sz) / delim.length();
        if (n > 0) {
            str = repeatString(delim, n) + str;
        }
        return str;
    }

    /**
     * Reverse the String.
     *
     * @param str the String to reverse.
     * @return a reversed string
     */
    static public String reverseString(String str) {
        if (str == null) return null;
        FastStringBuffer fsb = FastStringBuffer.getInstance();
        try {
            fsb.append(str);
            return fsb.reverse().toString();
        } finally {
            fsb.release();
        }
    }

    /**
     * Reverse the character case in the string
     * <p/>
     *
     * @param str String
     * @return String
     */
    static public String swapCase(String str) {
        if (str == null) return null;

        int sz = str.length();
        FastStringBuffer buffer = FastStringBuffer.getInstance();
        // StringBuffer buffer = new StringBuffer(sz);
        try {
            if (sz > buffer.capacity()) {
                buffer.ensureCapacity(sz);
            }
            boolean whiteSpace = false;
            char ch = 0;
            char tmp = 0;
            for (int i = 0; i < sz; i++) {
                ch = str.charAt(i);
                if (Character.isUpperCase(ch)) {
                    tmp = Character.toLowerCase(ch);
                } else if (Character.isTitleCase(ch)) {
                    tmp = Character.toLowerCase(ch);
                } else if (Character.isLowerCase(ch)) {
                    if (whiteSpace) {
                        tmp = Character.toTitleCase(ch);
                    } else {
                        tmp = Character.toUpperCase(ch);
                    }
                }
                buffer.append(tmp);
                whiteSpace = Character.isWhitespace(ch);
            }
            return buffer.toString();
        } finally {
            buffer.release();
        }
    }

    /**
     * Create a random string
     *
     * @param count size of string.
     * @return randomly generated string of size count
     */
    static public String random(int count) {
        return random(count, false, false);
    }

    /**
     * Create a random Ascii String
     *
     * @param count the size of the string
     * @return randomly generated string of size count
     */
    static public String randomAscii(int count) {
        return random(count, 32, 127, false, false);
    }

    /**
     * Create a random character only string
     *
     * @param count size of string
     * @return randomly generated string of size count
     */
    static public String randomAlphabetic(int count) {
        return random(count, true, false);
    }

    /**
     * Create a random alpha numeric string
     *
     * @param count the size of the string
     * @return randomly generated string of size count
     */
    static public String randomAlphanumeric(int count) {
        return random(count, true, true);
    }

    /**
     * Create a random numeric string
     *
     * @param count the size of the final string
     * @return randomly generated string of size count
     */
    static public String randomNumeric(int count) {
        return random(count, false, true);
    }

    /**
     * Create a random numeric string where you have control over size, and
     * whether you want letters, numbers, or both.
     * <p/>
     *
     * @param count   the size of the string
     * @param letters true if you want letters included
     * @param numbers true if you want numbers included
     * @return randomly generated string of size count
     */
    static public String random(int count, boolean letters, boolean numbers) {
        return random(count, 0, 0, letters, numbers);
    }

    /**
     * Create a random numeric string where you have control over size, and
     * whether you want letters, numbers, as well as ANSI minimum and maximum values
     * of the characters.
     *
     * @param count   the size of the string
     * @param start   int minimum 'value' of the character
     * @param end     maximum 'value' of the character
     * @param letters true if you want letters included
     * @param numbers true if you want numbers included
     * @return randomly generated string of size count
     */
    static public String random(int count, int start, int end, boolean letters, boolean numbers) {
        return random(count, start, end, letters, numbers, null);
    }

    /**
     * Create a random numeric string where you have control over size, and
     * whether you want letters, numbers, as well as ANSI minimum and maximum values
     * of the characters.
     *
     * @param count   the size of the string
     * @param start   int minimum 'value' of the character
     * @param end     maximum 'value' of the character
     * @param letters true if you want letters included
     * @param numbers true if you want numbers included
     * @param set     the set of possible characters that you're willing to let
     *                the string contain.  may be null if all values are open.
     * @return randomly generated string of size count
     * @todo make this cryptographically string
     */
    static public String random(int count, int start, int end, boolean letters, boolean numbers, char[] set) {
        if ((start == 0) && (end == 0)) {
            end = (int) 'z';
            start = (int) ' ';
            if (!letters && !numbers) {
                start = 0;
                end = Integer.MAX_VALUE;
            }
        }
        Random rnd = new Random();
        // StringBuffer buffer = new StringBuffer();
        FastStringBuffer buffer = FastStringBuffer.getInstance();
        try {
            int gap = end - start;
            while (count-- != 0) {
                char ch;
                if (set == null) {
                    ch = (char) (rnd.nextInt(gap) + start);
                } else {
                    ch = set[rnd.nextInt(gap) + start];
                }
                if ((letters && numbers && Character.isLetterOrDigit(ch))
                        || (letters && Character.isLetter(ch))
                        || (numbers && Character.isDigit(ch))
                        || (!letters && !numbers)) {
                    buffer.append(ch);
                } else {
                    count++;
                }
            }
            return buffer.toString();
        } finally {
            buffer.release();
        }
    }

    /**
     * Create a random string
     *
     * @param count the size of the string
     * @param set   the set of characters that are allowed
     * @return randomly generated string of size count
     */
    static public String random(int count, String set) {
        return random(count, set.toCharArray());
    }

    /**
     * Create a random string
     *
     * @param count the size of the string
     * @param set   the set of characters that are allowed
     * @return randomly generated string of size count
     */
    static public String random(int count, char[] set) {
        return random(count, 0, set.length - 1, false, false, set);
    }

    /**
     * return empty string the string is null
     * <p/>
     *
     * @param str The string to split String
     * @param lg  the length to subgstring
     * @return a substring of parameter str.
     */
    public static String substring(String str, int lg) {
        return substring(str, 0, lg);
    }

    /**
     * return empty string the string is null
     * <p/>
     *
     * @param str   The string to split String
     * @param start the location to start
     * @param end   the end location of the substring
     * @return a substring of parameter str.
     */
    public static String substring(String str, int start, int end) {
        if (str == null || str.length() <= start) {
            return null;
        } else if (str.length() >= end) {
            return str.substring(start, end);
        } else {
            return str.substring(start);
        }
    }

    /**
     * utility to get just name of class
     *
     * @param obj the object of the given class, the name of which will be returned
     * @return name of class, less any package prefix
     */
    public static String omitPackages(Object obj) {
        int i = obj.getClass().getPackage().getName().length();
        if (i > 0) i++;
        return obj.getClass().getName().substring(i);
    }

    /**
     * utility to get just name of class
     *
     * @param theclass the given class, the name of which will be returned
     * @return name of class, less any package prefix
     */
    public static String omitPackages(Class theclass) {
        int i = theclass.getPackage().getName().length();
        if (i > 0) i++;
        return theclass.getName().substring(i);
    }


    /**
     * A utility method to return an exception stacktrace as a String
     * <p/>
     */
    public static String getStackTraceAsString(Throwable t) {
        StringWriter swriter = new StringWriter();
        PrintWriter pwriter = new PrintWriter(swriter);
        t.printStackTrace(pwriter);
        pwriter.flush();
        return swriter.toString();
    }

    /**
     * Concatenate array of string into one string with constant delimiter <code>", "</code>
     * <p/>
     *
     * @param input the input array string
     * @return the joined string
     */
    public static String join(String[] input) {
        return join(input, ", ");
    }

    /**
     * Concatenate array of string into one string with delimiter
     * <p/>
     *
     * @param input     the input array string
     * @param delimiter the delimiter
     * @return the joined string
     */
    public static String join(String[] input, String delimiter) {
        if (input == null) return "";

        StringBuffer buf = new StringBuffer();
        for (int q = 0; q < input.length; ++q) {
            if (q > 0) {
                buf.append(delimiter);
            }

            buf.append(input[q]);
        }

        return buf.toString();
    }

	

    /**
     * Take an input string S, break the string into parts of N up to
     * 72 characters long which word wrapped with respective to blank
     * space characters and separated with the newline
     * characters. This static call has parameters set up for
     * electronic mail standards.
     * <p/>
     *
     * @param input the input string
     * @return wrapped broken string
     * @see #createWordWrappedString( String input, int fixedLength, String delimiter )
     */
    public static String createWordWrappedString(String input) {
        return createWordWrappedString(input, 72, "\n");
    }

    /**
     * Take an input string S, break the string into parts of N up to
     * F characters long which word wrapped with respective to blank
     * space characters and separated with the supplied delimiter D.
     * <p/>
     *
     * @param input       the input string
     * @param fixedLength the maximum fixed length
     * @param delimiter   the delimiter
     * @return wrapped broken string
     */
    public static String createWordWrappedString(String input, int fixedLength, String delimiter) {
        int inputLength = input.length();
        char c;

        // Case #1 Initial string is smaller than the fixed length
        if (inputLength < fixedLength)
            return input;

        // Case #2 Word wrapping is required
        StringBuffer buf = new StringBuffer(1024);
        int pos = 0;				// current cursor position
        int lastPos = 0;			// the last non-blank space position.
        int lineLength = 0;			// the line length
        while (pos < inputLength) {
            // System.out.println( "start:"+start+" end:"+end+" buf:["+buf.toString()+"]" );

            // Read the white space
            c = input.charAt(pos);
            while (pos < inputLength && Character.isWhitespace(c)) {
                buf.append(c);
                if (c == '\t')
                // If current character is a tab increase the line length
                    lineLength += 8;
                else if (c == '\n')
                // If current character is a new line, reset the line length
                    lineLength = 0;
                else
// Increase line space by one
                    ++lineLength;

                ++pos;				/*1*/
                if (pos < inputLength) c = input.charAt(pos);	/*2*/
            }

            // Record position for a beginning of a word
            lastPos = pos;

            // Read the word
            StringBuffer word = new StringBuffer(64);
            if (pos < inputLength) c = input.charAt(pos);
            while (pos < inputLength && !Character.isWhitespace(c)) {
                word.append(c);
                ++pos;				/*1*/
                if (pos < inputLength) c = input.charAt(pos);	/*2*/
            }

            int wordLength = word.length();
            if (lineLength + wordLength >= fixedLength) {
                // Force a word wrap here, because the length of the
                // text line exceeds the fixed length
                buf.append(delimiter);
                lineLength = 0;
            }

            // Add the word
            buf.append(word);
            lineLength += wordLength;
        }

        return buf.toString();
    }

	
    public static void main(String args[]) {
        //             01234567890123456789
        String text = "CAT SAT ON THE MAT";
        System.out.println("Result "+isAlphaNumeric("test123", ""));
        

    }

   
} /* StringUtil */

package com.maan.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.axis.encoding.Base64;

public final class PasswordService
{
  private static PasswordService instance;

  public PasswordService(){}

  public synchronized String encrypt(String plaintext) throws SystemUnavailableException
  {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA"); //step 2
    }
    catch(NoSuchAlgorithmException e) {
      throw new SystemUnavailableException(e.getMessage());
    }

    try {
      md.update(plaintext.getBytes("UTF-8")); //step 3
    }
    catch(UnsupportedEncodingException e) {
      throw new SystemUnavailableException(e.getMessage());
    }

    byte raw[] = md.digest(); //step 4
    String hash = Base64.encode(raw); //step 5
    return hash; //step 6
  }
  
  

  public static synchronized PasswordService getInstance() { //step 1
    if(instance == null)
    {
       instance = new PasswordService(); 
    } 
    return instance;
  }

  public static void main(String[] args)  {
    try {
        
		
		if (args != null) {
            System.out.println(PasswordService.getInstance().encrypt(args[0]));
        }
        else {
            System.out.println("Please give a password argument");
        }
    } catch(SystemUnavailableException e) {System.out.println(e.getMessage());}
  }
}

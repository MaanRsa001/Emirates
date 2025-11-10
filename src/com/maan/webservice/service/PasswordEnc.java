package com.maan.webservice.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
 

public class PasswordEnc {
   
    /** Default Constructor.*/
    private PasswordEnc() {}

    public static final String crypt(String salt, String original) {
        String encryptedString = "";
        try {
            encryptedString = encrypt(original); 
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        return encryptedString;
        
    }

    public static final String crypt(String original) {
        return crypt("",original);
    }

    public static void main(String args[]) {
      /** Sample usage java jcrypt password */
    	System.out.println(PasswordEnc.crypt("quo", "quote123"));
      if(args.length >= 2) {
    	  System.out.println
         (
            "[" + args[0] + "] [" + args[1] + "] => [" +
            PasswordEnc.crypt(args[0], args[1]) + "]"
         );
      }
    }

    public static synchronized String encrypt(String plaintext) throws Exception{
      MessageDigest md = null;
      try {
        md = MessageDigest.getInstance("SHA"); //step 2
      }
      catch(NoSuchAlgorithmException e) {
        throw new Exception(e.getMessage());
      }

      try {
        md.update(plaintext.getBytes("UTF-8")); //step 3
      }
      catch(UnsupportedEncodingException e) {
        throw new Exception(e.getMessage());
      }

      byte raw[] = md.digest(); //step 4
      String hash =  Base64.getEncoder().encodeToString(raw); //step 5
      return hash; //step 6
    }
}

/**
 *  
 *  @File   : jcrypt.java
 *  @Desc   : Java-based implementation of the DES cryptology.
 *  @Version: 1.0
 *  Company : Maan Sarovar Software Private Limited.
 *
 *  Start Date      : 03/09/06
 *
*/

package com.maan.common.util;

import com.maan.common.util.SystemUnavailableException;
import com.maan.common.util.PasswordService;


/**
 *
 *  A Java Bean class responsible for crypting the given String 
 *  based on DES crypting algorithm.
 *
 *  Sample Usage in a java bean file:
 *  <pre>
 *  ----------------------------------------------------------------
 * 	    import wms.jcrypt;
 *
 *      String password = "somepassword";
 *      String cryptedPassword = jcrypt.crypt
 *                              (password.substring(0,3),password);
 *  -----------------------------------------------------------------
 *  </pre>
 *
 *  
**/
public class jcrypt {
   
    /** Default Constructor.*/
    private jcrypt() {}

    public static final String crypt(String salt, String original) {
        String encryptedString = "";
        try {
            PasswordService password = new PasswordService();
            encryptedString = password.encrypt(original); 
        } catch(SystemUnavailableException e) {System.out.println(e.getMessage());}
        return encryptedString;
        
    }

    public static final String crypt(String original) {
        return crypt("",original);
    }

    public static void main(String args[]) {
      /** Sample usage java jcrypt password */
      if(args.length >= 2) {
         System.out.println
         (
            "[" + args[0] + "] [" + args[1] + "] => [" +
            jcrypt.crypt(args[0], args[1]) + "]"
         );
      }
    }

}
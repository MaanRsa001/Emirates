package com.maan.common;

import org.apache.logging.log4j.Logger;

/**
 *
 * A Java Bean class responsible for crypting the given String based on DES
 * crypting algorithm.
 *
 * Sample Usage in a java bean file:
 * <pre>
 *  ----------------------------------------------------------------
 * 	    import wms.Jcrypt;
 *
 *      String password = "somepassword";
 *      String cryptedPassword = Jcrypt.crypt
 *                              (password.substring(0,3),password);
 *  -----------------------------------------------------------------
 * </pre>
 *
 *
 *
 */
public class Jcrypt {
	private static final Logger logger = LogUtil.getLogger(Jcrypt.class);
    /**
     * Default Constructor.
     */
    private Jcrypt() {
    }

    public static final String crypt(String salt, String original) {
        String encryptedString = "";
        try {
            PasswordService password = new PasswordService();
            encryptedString = password.encrypt(original);
        } catch (Exception e) {
            logger.error("Encryption Exception => ", e);
        }
        return encryptedString;

    }

    public static final String crypt(String original) {
        return crypt("", original);
    }
    
    public static void main(String[] args){
    	System.out.println("Enc=>"+crypt("approvar2"));
    }
}